package org.healthnlp.deepphe.uima.ae;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Condition;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.DefaultFactList;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.fact.TextMention;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorPhenotype;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.drools.Domain;
import org.healthnlp.deepphe.uima.drools.DroolsEngine;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.kie.api.runtime.KieSession;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.ILogicExpression;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.IRestriction;
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
			if(!OntologyUtils.hasInstance()){
				ontology = OOntology.loadOntology((String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH));
				OntologyUtils.getInstance(ontology);
			}else{
				ontology = OntologyUtils.getInstance().getOntology();
			}
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		// for now, lets assume there is only one cancer summary
		MedicalRecord record = PhenotypeResourceFactory.loadMedicalRecord(jcas);
		Patient patient = record.getPatient();
		
		PatientSummary patientSummary = new PatientSummary();
		patientSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);
		
		CancerSummary cancerSummary =  new CancerSummary(patient.getPatientName());
		cancerSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);
		
System.out.println("&&: "+cancerSummary.getResourceIdentifier());
		
		// record to load into drools
		record.setPatientSummary(patientSummary);
		record.setCancerSummary(cancerSummary);
		
		// merge stuff around
		/*
		for(Report report: record.getReports()){

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

		}
		*/
		
		// check ancestors
		checkAncestors(record.getReportLevelFacts());

		/*for(Fact f: record.getReportLevelFacts()){
			System.out.println(f.getInfo());
		}*/


		//Olga: for TMN and Stage testing empty *Classification and Cancer stage
		/*record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_CANCER_STAGE);
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_T_CLASSIFICATION);
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_N_CLASSIFICATION);
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_M_CLASSIFICATION);*/

		
		//insert record into drools
		DroolsEngine de = new DroolsEngine();
		KieSession droolsSession = null;
		try {
			droolsSession = de.getSession();
			//droolsSession.addEventListener( new DebugAgendaEventListener() );
			//insert new medical record
			droolsSession.insert(new Domain("Breast"));
			droolsSession.insert(record);					
			for(Fact f: record.getReportLevelFacts()){
				try{
				if(!f.getCategory().equalsIgnoreCase("wasDerivedFrom")){
					System.out.println(f.getInfo());
					droolsSession.insert(f);
				}
				}catch (NullPointerException e){
				//	System.err.println("NO Category for F: "+f.getInfo());
				}
			}		
			droolsSession.fireAllRules();
			droolsSession.dispose();
			

			//System.out.println("DROOLS TIME: "+(System.currentTimeMillis() - stT)/1000+"  sec");
			
			System.out.println("RECORD Summary: "+record.getSummaryText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//this is where you save your work back to CAS

		PhenotypeResourceFactory.saveMedicalRecord(record, jcas);
		
	}

	public void checkAncestors(Collection<Fact> facts){
		for(Fact f:	facts){
			if(f.getAncestors().isEmpty())
				OntologyUtils.getInstance().addAncestors(f);
		}
	}
	
	
	/**
	 * should this restriction be used for summarization
	 * @param r
	 * @return
	 */
	/*
	
	}
	*/

}
