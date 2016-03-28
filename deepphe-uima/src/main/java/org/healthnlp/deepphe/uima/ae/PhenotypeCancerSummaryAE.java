package org.healthnlp.deepphe.uima.ae;

import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.drools.DroolsEngine;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieSession;

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
		
		MedicalRecord record = new MedicalRecord();
		record.setPatient(PhenotypeResourceFactory.loadPatient(jcas));
		record.setPatientSummary(patientSummary);
		record.setCancerSummary(cancerSummary);
		
		
		
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){
			record.addReport(report);
			
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
		}

		//insert record into drools
		/*long stT = System.currentTimeMillis();	
		DroolsEngine de = new DroolsEngine();
		KieSession droolsSession = null;
		try {
			droolsSession = de.getSession();
			droolsSession.addEventListener( new DebugAgendaEventListener() );
			
			droolsSession.insert(record);
					
			for(Fact f: record.getReportLevelFacts()){
				System.out.println(f.getInfo());
				droolsSession.insert(f);
			}
			
			droolsSession.fireAllRules();
			droolsSession.dispose();
			System.out.println("DROOLS TIME: "+(System.currentTimeMillis() - stT)/1000+"  sec");
			System.out.println("Patient from MR: "+record.getPatient());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		//this is where you save your work back to CAS
		PhenotypeResourceFactory.saveMedicalRecord(record, jcas);
		
	}

}
