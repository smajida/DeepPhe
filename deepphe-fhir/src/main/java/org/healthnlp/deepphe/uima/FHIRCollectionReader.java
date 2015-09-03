package org.healthnlp.deepphe.uima;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;

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
		
		//go into directory 
		for(File f: directory.listFiles()){
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
			ArrayList<Report> reports = new ArrayList<Report>();
			for(File f: file.listFiles()){
				if(f.isDirectory()){
					Report r = ResourceFactory.loadReport(f);
					if(r != null)
						reports.add(r);
				}
			}
			
			// create a document
			DocumentID documentIDAnnotation = new DocumentID(jcas);
			String docID = createDocID(file);
			documentIDAnnotation.setDocumentID(docID);
			documentIDAnnotation.addToIndexes();

			
			// we don't have text
			byte[] serializedPatient = SerializationUtils.serialize(reports);
			jcas.setDocumentText(Base64.getEncoder().encodeToString(serializedPatient));
			
		} catch (Exception e) {
			throw new CollectionException(e);
		} finally {
			this.iv_currentIndex += 1;
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
				Report r = ResourceFactory.loadReport(f);
				if(r != null)
					System.out.println(r.getSummary());
			}
		}
		
	}
}
