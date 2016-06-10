package org.healthnlp.deepphe.uima.ae;

import org.apache.ctakes.core.ae.XMIWriter;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.uima.fhir.DocumentResourceFactory;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * create FHIR represetnation of documen mention level data in 
 * document centric cTAKES pipeline
 * @author tseytlin
 *
 */

public class DocumentSummarizerAE extends JCasAnnotator_ImplBase {
	public static final String FHIR_TYPE = "FHIR";
	public static final String XMI_TYPE = "XMI";
	private String outputDir;

	public static final String PARAM_OUTPUTDIR = "OUTPUT_DIR";
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		outputDir = (String) aContext.getConfigParameterValue(PARAM_OUTPUTDIR);

		//String ontologyPath = (String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH);

		// File ontologyFile = new File( ontologyPath );
		/*IOntology ontology;
		try {
			ontology = OOntology.loadOntology(ontologyPath);
			//resourceFactory = new ResourceFactory(ontology);
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}*/
	}

	private static LinkedHashMap<String, Integer> patientNameIDMap = new LinkedHashMap<String, Integer>();
	private static int patientID = 0;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		try {

			Patient patient = DocumentResourceFactory.getPatient(jcas);

			String namedID = patient != null?patient.getPatientName():"unknown";
			Report report = DocumentResourceFactory.getReport(jcas);
			
			System.out.println("PROCESSING patient: " + namedID +" document: "+report.getTitle()+" ..");
			
			// save FHIR related data
			File patientDir = new File(new File(outputDir, FHIR_TYPE), namedID);
			if (!patientDir.exists()) {
				patientDir.mkdirs();
			}
			report.save(patientDir);

			// save XMI
			patientDir = new File(new File(outputDir, XMI_TYPE), namedID);
			if (!patientDir.exists()) {
				patientDir.mkdirs();
			}
			XMIWriter.writeXmi(jcas.getCas(), new File(patientDir, report.getTitle().replace('/', '_') + ".xmi"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// _xmiWriter.process( jcas );

	}

}
