package org.healthnlp.deepphe.uima.cr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.uima.ae.DocumentSummarizerAE;
import org.healthnlp.deepphe.uima.fhir.DocumentResourceFactory;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;

public class FHIRCollectionReader extends CollectionReader_ImplBase {
	public static final String PARAM_INPUTDIR = "InputDirectory";
	protected ArrayList<File> subjectDirectories;
	protected int iv_currentIndex;
	private String iv_rootPath;

	public FHIRCollectionReader() {
		this.iv_rootPath = "";
	}

	public void initialize() throws ResourceInitializationException {
		File directory = new File((String) getConfigParameterValue("InputDirectory"));
		this.iv_currentIndex = 0;
		this.iv_rootPath = directory.getPath();

		if ((!(directory.exists())) || (!(directory.isDirectory()))) {
			throw new ResourceInitializationException("directory_not_found", new Object[] { "InputDirectory",
					getMetaData().getName(), directory.getPath() });
		}

		this.subjectDirectories = new ArrayList();
		
		// check if there is a FHIR subdirectory, otherwiswe assume that that is it
		if(new File(directory,DocumentSummarizerAE.FHIR_TYPE).exists())
			directory = new File(directory,DocumentSummarizerAE.FHIR_TYPE);
		
		File [] files =  directory.listFiles();
		Arrays.sort(files);
		//go into directory 
		for(File f: files){
			if(f.isDirectory()){
				subjectDirectories.add(f);
			}
		}
	}

	public boolean hasNext() {
		return (this.iv_currentIndex < getNumberOfDocuments());
	}

	public void getNext(CAS aCAS) throws IOException, CollectionException {
		try {
			JCas jcas = aCAS.getJCas();
			File file = (File) this.subjectDirectories.get(this.iv_currentIndex);

			// now we have a subject directory, it should containe report directories.
			//ArrayList<Report> reports = new ArrayList<Report>();
			//Patient patient = new Patient();
			//p.setPath(f.getAbsolutePath());
			
			
		
			StringBuffer reportText = new StringBuffer();
			
			List<Report> reports = new ArrayList<Report>();
			for(File f: file.listFiles()){
				if(f.isDirectory()){
					Report r = DocumentResourceFactory.loadReport(f);
					if(r != null){
						reports.add(r);
					}
				}
			}
			// sort by date
			Collections.sort(reports);
			
			// add reports to TS
			int offset = 0;
			for(Report r: reports){
				// add report to an uber report
				String text = r.getReportText()+"\n";
				reportText.append(text);
				r.setOffset(offset);
				offset += text.length();
				
				// persist into CAS
				//System.out.println(r.getSummaryText());
				PhenotypeResourceFactory.saveReport(r,jcas);
			}
			
			
			
			// create a document
			DocumentID documentIDAnnotation = new DocumentID(jcas);
			String docID = createDocID(file);
			documentIDAnnotation.setDocumentID(docID);
			documentIDAnnotation.addToIndexes();
			
			// we don't have text (for now)
			//jcas.setDocumentText(Base64.getEncoder().encodeToString(SerializationUtils.serialize(patient)));
			jcas.setDocumentText(reportText.toString());
		} catch (Exception e) {
			throw new CollectionException(e);
		} finally {
			this.iv_currentIndex += 1;
		}
	}

	private void reIdentifyDAG(KbPatient p) {
		p.setId(KbIdentified.idGenerator++);
		p.setSequence(p.getId());
		for (KbEncounter e : p.getEncounters()) {
			e.setId(KbIdentified.idGenerator++);
			e.setSequence(e.getId());
			e.setPatientId(p.getId());
		}
		for (KbSummary s : p.getSummaries()) {
			s.setId(KbIdentified.idGenerator++);
			s.setSummarizableId(p.getId());
		}
		for (KbEncounter e : p.getEncounters()) {
			for (KbSummary s : e.getSummaries()) {
				s.setId(KbIdentified.idGenerator++);
				s.setSummarizableId(e.getId());
			}
		}
	}
	
	private String createDocID(File file) {
		String docID = file.getPath();
		if ((this.iv_rootPath.endsWith("" + File.separator)) || (this.iv_rootPath.equals(""))) {
			docID = docID.substring(this.iv_rootPath.length());
		} else
			docID = docID.substring(this.iv_rootPath.length() + 1);
		return docID;
	}

	public void close() throws IOException {
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(this.iv_currentIndex, getNumberOfDocuments(), "entities") };
	}

	public int getNumberOfDocuments() {
		return subjectDirectories == null?0:subjectDirectories.size();
	}

	
	public static void main(String [] args) throws Exception{
		File file = new File("/home/tseytlin/Work/DeepPhe/data/sample/output/FHIR/Jane Doe");
		for(File f: file.listFiles()){
			if(f.isDirectory()){
				System.out.println(f.getName());
				Report r = DocumentResourceFactory.loadReport(f);
				if(r != null){
					System.out.println(r.getSummaryText());
					r.save(new File("/home/tseytlin/Output/FHIR/"));
				}
			}
		}
		
	}
}
