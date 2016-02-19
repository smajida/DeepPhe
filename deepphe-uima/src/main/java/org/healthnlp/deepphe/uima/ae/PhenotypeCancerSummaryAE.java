package org.healthnlp.deepphe.uima.ae;

import java.util.Collection;
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
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.FHIRUtils;

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
		
		// for now, lets assume there is only one cancer summary
		PatientSummary patientSummary = new PatientSummary();
		patientSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);
		
		CancerSummary cancerSummary =  new CancerSummary();
		cancerSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);
		
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){
			// append patient summary
			PatientSummary p = report.getPatientSummary();
			if(p != null && patientSummary.isAppendable(p)){
				patientSummary.append(p);
			}
			
			//append cancer summary
			for(CancerSummary cs: report.getCancerSummaries()){
				if(cancerSummary.isAppendable(cs)){
					cancerSummary.append(cs);
				}else{
					//TODO: well we have another cancer summary, will ignore it for now
				}
			}
			
			//append tumor summaries that are by themselves
			for(TumorSummary ts: report.getTumorSummaries()){
				cancerSummary.append(ts);
			}
		
			
			
			//TODO: this is where you can start with RULES
			// this is a temporary merge of all summaries together without
			// much thought until we have something better
		}

		// lets print out cancer summary
		System.out.println("\n\n\n---| Medical Record Cancer Summary |---");
		System.out.println(cancerSummary.getSummaryText());
		
		//this is where you save your work back to CAS
		PhenotypeResourceFactory.saveMedicalRecordPatientSummary(patientSummary, jcas);
		PhenotypeResourceFactory.saveMedicalRecordCancerSummary(cancerSummary, jcas);
		
	}

}
