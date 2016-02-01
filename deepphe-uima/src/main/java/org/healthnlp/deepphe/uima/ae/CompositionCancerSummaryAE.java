package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.util.*;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.*;
import org.healthnlp.deepphe.fhir.summary.*;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;


/**
 * create composition cancer and tumor suammries on document level
 * @author tseytlin
 *
 */

public class CompositionCancerSummaryAE extends JCasAnnotator_ImplBase {
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";
	private IOntology ontology;
	public static final List<String> tumorTriggers  = Arrays.asList("Lesion","Neoplasm");
	public static final List<String> cancerTriggers = Arrays.asList("Malignant_Breast_Neoplasm"); //"Metastatic_Neoplasm"
	public static final List<String> documentTypeTriggers = Arrays.asList("SP");
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			ontology = OOntology.loadOntology((String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH));
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}

	/**
	 * process each composition to generate cancer/tumor summaries
	 */
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){
			
			PatientSummary patient = createPatientSummary(report.getPatient());
			List<Summary> summaries = createSummaries(report);
			
			report.addCompositionSummaries(summaries);
			report.addCompositionSummary(patient);
			
			PhenotypeResourceFactory.saveReport(report,jcas);
			
			// print out
			System.out.println(report.getSummaryText());
		}
	}

	private PatientSummary createPatientSummary(Patient loadPatient) {
		PatientSummary ps = new PatientSummary();
		// TODO: actually fill it out
		return ps;
	}

	
	private boolean hasTrigger(List<String> triggers, Condition cond){
		for(String s: triggers){
			IClass trigger = ontology.getClass(s);
			IClass cls = ontology.getClass(""+FHIRUtils.getConceptURI(cond.getCode()));
			if(cls != null && (cls.hasSuperClass(trigger) || cls.equals(trigger))){
				return true;
			}
		}
		return false;
	}
	

	/**
	 * create Tumor and Cancer summaries
	 * @param report
	 * @return
	 */
	private List<Summary> createSummaries(Report report) {
		List<Summary> list = new ArrayList<Summary>();
		CancerSummary cancer = null;
		TumorSummary tumor = null;
		
		// doc1 - cancer + tumor
		// doc2 - cancer (for now)
		// doc3 - cancer (metastatic neoplasm) - tumor (for 
		
		// explicit mentions mentions of cancer, carcinoma, any disease under 'malignant breast neoplasm' class "metastatic neoplasm"
		// If in pathology report, then generate tumor instance as well.
		for(Disease c: report.getDiagnoses()){
			if(hasTrigger(cancerTriggers,c)){
				cancer = createCancerSummary(c);
				list.add(cancer);
				break;
			}
		}
		
		// create tumor summary if we talke about cancer in trigger document
		if(cancer != null && documentTypeTriggers.contains(report.getType())){
			tumor = createTumorSummary(null);
			cancer.addTumor(tumor);
		}
		
		// if tumor not yet defined, try searching for one
		if(tumor == null){
			for(Element e: report.getReportElements()){
				// if we have a tumor trigger
				if(e instanceof Condition && hasTrigger(tumorTriggers, (Condition)e)){
					tumor = createTumorSummary((Condition) e);
					if(cancer != null){
						cancer.addTumor(tumor);
					}else{
						list.add(tumor);
					}
					break;
				}
			}
		}
				
		return list;
	}

	private CancerSummary createCancerSummary(Disease diagnosis){
		CancerSummary cancer = new CancerSummary();
		CancerSummary.CancerPhenotype phenotype = new CancerSummary.CancerPhenotype();
		cancer.addPhenotype(phenotype);
		
		// add body location
		for(CodeableConcept cc: diagnosis.getBodySite()){
			cancer.addBodySite(cc);
		}
		
		
		// add TNM stage infromation
		Stage stage = diagnosis.getStage();
		if(stage != null){
			phenotype.setCancerStage(stage.getSummary());
			phenotype.setPrimaryTumorClassification(stage.getPrimaryTumorStageCode());
			phenotype.setRegionalLymphNodeClassification(stage.getPrimaryTumorStageCode());
			phenotype.setDistantMetastasisClassification(stage.getDistantMetastasisStageCode());
		}
		
		//TODO: infor type and extend
		//phenotype.setCancerType(null);   // adnecarcionma vs sarcoma
		//phenotype.setTumorExtent(null);  // insitu vs invasive 
	
		
		//cancer.addOutcome(outcome); 	   // death
		//cancer.addTreatment(treatment);  //chemo
		
		return cancer;
	}
	
	private TumorSummary createTumorSummary(Condition diagnosis){
		TumorSummary tumor = new TumorSummary();
		TumorSummary.TumorPhenotype phenotype = new TumorSummary.TumorPhenotype();
		tumor.setPhenotype(phenotype);
		
		// add body location
		for(CodeableConcept cc: diagnosis.getBodySite()){
			tumor.addBodySite(cc);
		}
		
		// manifistation: 
		//phenotype.addHistologicType(null); // ductal, lobular infered from DX
		//phenotype.addManifistation(null);  // Receptor Status, Tumor size 
		//phenotype.addTumorExtent(null);    // insitu vs invasive
		
		
		//tumor.addOutcome(null); // tumor is gone
		//tumor.addTreatment(null); // chemo
		//tumor.addTumorSequenceVarient(null); // don't have one
		//tumor.setTumorType(null);  // primary vs local recurance, distance recurance  infered from rules
		
		
		//TODO:
		return tumor;
	}
}
