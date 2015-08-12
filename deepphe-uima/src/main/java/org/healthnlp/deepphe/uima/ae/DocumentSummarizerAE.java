package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.SummaryFactory;
import org.healthnlp.deepphe.util.TextUtils;

import com.google.common.io.Files;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;



public class DocumentSummarizerAE extends JCasAnnotator_ImplBase {
	private ResourceFactory resourceFactory;

	private String outputDir;
	
	public static final String PARAM_OUTPUTDIR = "OUTPUT_DIR";
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";
	
	
	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		outputDir = 
		        (String) aContext.getConfigParameterValue(PARAM_OUTPUTDIR);
	
		String ontologyPath = 
		        (String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH);
		
		File ontologyFile = new File(ontologyPath);
		IOntology ontology;
		try {
			ontology = OOntology.loadOntology(ontologyFile);
			resourceFactory = new ResourceFactory(ontology);
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}


	

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		try {
			
			for ( DocumentID docID : JCasUtil.select(jcas, DocumentID.class)) {
				Report report = resourceFactory.getReport(jcas);
				report.setTitleSimple(TextUtils.stripSuffix(docID.getDocumentID()));
				
				Encounter e = SummaryFactory.getEncounter(report);
				File reportdir = new File(new File(outputDir),TextUtils.stripSuffix(report.getTitleSimple()));
				saveSerialized(reportdir,e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			

		
	}


	public void saveSerialized(File dir, Encounter e) throws Exception{
		
		File file = new File(dir,"report.data");
		Files.createParentDirs(file);
		Files.touch(file);
		
		// Write to disk with FileOutputStream
		FileOutputStream f_out = new 
			FileOutputStream(file);

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new
			ObjectOutputStream (f_out);

		// Write object out to disk
		obj_out.writeObject ( e );
		
		obj_out.close();
		f_out.close();
	}
}
