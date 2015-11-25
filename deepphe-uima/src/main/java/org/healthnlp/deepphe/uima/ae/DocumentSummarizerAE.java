package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;

import org.apache.commons.lang.SerializationUtils;
import org.apache.ctakes.cancer.ae.XMIWriter;
import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.CasIOUtil;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.SummaryFactory;
import org.healthnlp.deepphe.util.TextUtils;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;

public class DocumentSummarizerAE extends JCasAnnotator_ImplBase {
	public static final String POJO_TYPE = "POJO";
	public static final String FHIR_TYPE = "FHIR";
	public static final String XMI_TYPE = "XMI";

	private ResourceFactory resourceFactory;

	private String outputDir;

	public static final String PARAM_OUTPUTDIR = "OUTPUT_DIR";
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		outputDir = (String) aContext.getConfigParameterValue(PARAM_OUTPUTDIR);

		String ontologyPath = (String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH);

		// File ontologyFile = new File( ontologyPath );
		IOntology ontology;
		try {
			ontology = OOntology.loadOntology(ontologyPath);
			resourceFactory = new ResourceFactory(ontology);
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}

	private static LinkedHashMap<String, Integer> patientNameIDMap = new LinkedHashMap<String, Integer>();
	private static int patientID = 0;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		try {

			for (DocumentID docID : JCasUtil.select(jcas, DocumentID.class)) {
				Patient patient = resourceFactory.getPatient(jcas);

				String namedID = patient != null?patient.getPatientName():"unknown";
				/*
				 * Integer id = patientNameIDMap.get(namedID);
				 * 
				 * if(id==null){ patientID++; patientNameIDMap.put(namedID,
				 * patientID);
				 * 
				 * id = patientID; }
				 */
				Report report = resourceFactory.getReport(jcas);
				report.setTitle(TextUtils.stripSuffix(docID.getDocumentID()));
				//System.out.println("\n\n-------------------------------------------------------------------\n");
				//System.out.println(report.getSummaryText());
				//System.out.println("\n===================================================================\n\n");

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// _xmiWriter.process( jcas );

	}

}
