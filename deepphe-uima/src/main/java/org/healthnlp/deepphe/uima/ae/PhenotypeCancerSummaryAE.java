package org.healthnlp.deepphe.uima.ae;

import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;

/**
 * this is Rule based phenotype cancer summary AE
 * it takes Composition Cancer/Tumor and Patient Summary objects and creates
 * a combined phenotype summary object
 * @author tseytlin
 *
 */

public class PhenotypeCancerSummaryAE extends JCasAnnotator_ImplBase {
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";
	private IOntology ontology;
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			ontology = OOntology.loadOntology((String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH));
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		PatientSummary patientSummary = null;
		CancerSummary cancerSummary =  null;
		
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){
			List<Summary> summaries = report.getCompositionSummary(); 
			
			//TODO: this is where you can start with RULES
			
		}

		//TODO: this is where you save your work back to CAS
	}

}
