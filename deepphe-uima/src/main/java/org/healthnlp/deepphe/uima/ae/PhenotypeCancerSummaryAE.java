package org.healthnlp.deepphe.uima.ae;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Condition;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
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

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;

/**
 * this is Rule based phenotype cancer summary AE it takes Composition
 * Cancer/Tumor and Patient Summary objects and creates a combined phenotype
 * summary object
 * 
 * @author tseytlin
 *
 */

public class PhenotypeCancerSummaryAE extends JCasAnnotator_ImplBase {
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";
	private IOntology ontology;

	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			if (!OntologyUtils.hasInstance()) {
				ontology = OOntology.loadOntology((String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH));
				OntologyUtils.getInstance(ontology);
			} else {
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

		CancerSummary cancerSummary = new CancerSummary(patient.getPatientName());
		cancerSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);

		

		// record to load into drools
		record.setPatientSummary(patientSummary);
		record.setCancerSummary(cancerSummary);

		// merge stuff around
		/*for(Report report: record.getReports()){
			PatientSummary p = report.getPatientSummary();
			if(p != null && patientSummary.isAppendable(p)){
				patientSummary.append(p); 
			}
			for(CancerSummary c: report.getCancerSummaries()){
				if(cancerSummary.isAppendable(c)){
					cancerSummary.append(c);
				}
			}
			
		}*/
		
		// check ancestors
		List<Fact> reportFacts = record.getReportLevelFacts();
		checkAncestors(reportFacts);

		System.out.println("PROCESSING phenotype for " + cancerSummary.getResourceIdentifier() +" ..");
		System.out.println("loading "+reportFacts.size()+" facts into Rules Engine ...");
		
		/* for(Fact f: record.getReportLevelFacts()){
		  System.out.println(f.getInfo()); 
		  }*/
		
		// insert record into drools
		DroolsEngine de = new DroolsEngine();
		KieSession droolsSession = null;
		try {
			// duplicates care
			List<String> dupList = new ArrayList<String>();
			droolsSession = de.getSession();
			// droolsSession.addEventListener( new DebugAgendaEventListener() );
			// insert new medical record
			droolsSession.insert(new Domain("Breast"));
			droolsSession.insert(record);
			boolean doWrite = false;
			FileWriter fw = null;
			if (doWrite)
				fw = new FileWriter("/home/opm1/devSrc/deepPhe_Data/DeepPhe/droolsInput/droolsInput_Patient02.txt");
			for (Fact f : reportFacts) {
				try {
					if (!f.getCategory().equalsIgnoreCase("wasDerivedFrom") && !dupList.contains(f.getInfo())) {
						dupList.add(f.getInfo());
						if (doWrite)
							fw.write(f.getInfo());

						// System.out.println(f.getInfo());
						droolsSession.insert(f);
					}
				} catch (NullPointerException e) {
					// System.err.println("NO Category for F: "+f.getInfo());
				}
			}
			if (fw != null)
				fw.close();
			dupList.clear();
			dupList = null;
			droolsSession.fireAllRules();
			droolsSession.dispose();

			// System.out.println("DROOLS TIME: "+(System.currentTimeMillis() -
			// stT)/1000+" sec");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// debug system out
		System.out.println("**************************");
		System.out.println("RECORD Summary: " + record.getSummaryText());
		System.out.println("**************************");
		
		// reset fact level resource IDs that rules have created
		for(Fact f: record.getRecordLevelFacts()){
			 f.setIdentifier(record.getClass().getSimpleName()+"_"+f.getName()+"_"+Math.round(1000*Math.random())); 
			// System.out.println(f.getInfo()); 
		}
		
		
		// this is where you save your work back to CAS
		PhenotypeResourceFactory.saveMedicalRecord(record, jcas);

	}

	public void checkAncestors(Collection<Fact> facts) {
		for (Fact f : facts) {
			if (f.getAncestors().isEmpty())
				OntologyUtils.getInstance().addAncestors(f);
		}
	}
}
