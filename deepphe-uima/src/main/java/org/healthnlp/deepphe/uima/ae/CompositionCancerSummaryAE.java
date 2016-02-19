package org.healthnlp.deepphe.uima.ae;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Condition;
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;


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
			//System.out.println(report.getSummaryText());
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
		Disease cancerDx = null;
		for(Disease c: report.getDiagnoses()){
			if(hasTrigger(cancerTriggers,c)){
				cancer = createCancerSummary(report,c);
				cancerDx = c;
				list.add(cancer);
				break;
			}
		}
		
		// create tumor summary if we talke about cancer in trigger document
		if(cancer != null && documentTypeTriggers.contains(report.getType())){
			tumor = createTumorSummary(report,cancerDx,cancer);
			cancer.addTumor(tumor);
		}
		
		// if tumor not yet defined, try searching for one
		if(tumor == null){
			for(Element e: report.getReportElements()){
				// if we have a tumor trigger
				if(e instanceof Condition && hasTrigger(tumorTriggers, (Condition)e)){
					tumor = createTumorSummary(report,(Condition) e,cancer);
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

	
	/**
	 * create cancer summary
	 * @param report
	 * @param diagnosis
	 * @return
	 */
	private CancerSummary createCancerSummary(Report report, Disease diagnosis){
		CancerSummary cancer = new CancerSummary();
		CancerSummary.CancerPhenotype phenotype = cancer.getPhenotype();
		//cancer.addPhenotype(phenotype);
		
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
		
		// adnecarcionma vs sarcoma
		CodeableConcept ct = getCancerType(diagnosis);
		if(ct != null)
			phenotype.setCancerType(ct);
		
		// insitu vs invasive 
		CodeableConcept te = getTumorExtent(diagnosis);
		if(te != null)
			phenotype.setTumorExtent(te);  
	
		//add treatment cancer.addTreatment(treatment);  //chemo
		IClass treatmentCls = ontology.getClass(""+FHIRConstants.TREATMENT_URI);
		for(Element treatment: report.getReportElements()){
			if(isValueSet(treatment.getCode(), treatmentCls)){
				cancer.addTreatment(treatment.getCode()); 
			}
		}
		
		//cancer.addOutcome(outcome); 	   // death
			
		return cancer;
	}


	private TumorSummary createTumorSummary(Report report, Condition diagnosis, CancerSummary cancer){
		TumorSummary tumor = new TumorSummary();
		TumorSummary.TumorPhenotype phenotype = tumor.getPhenotype();
		
		if(diagnosis != null){
			// add body location
			for(CodeableConcept cc: diagnosis.getBodySite()){
				tumor.addBodySite(cc);
			}
			
			// manifistation: 
			//phenotype.addManifistation(null);  // Receptor Status, Tumor size 
			//List<IClass> manifestationClasses = getManifestationForTumor();
			IClass manifistationCls = ontology.getClass(""+FHIRConstants.MANIFISTATION_URI);
			for(Element element: report.getReportElements()){
				if(isValueSet(element.getCode(),manifistationCls)){
					phenotype.addManifestation(element.getCode()); 
				}
			}
			
			//phenotype.addHistologicType(null); // ductal, lobular infered from DX
			CodeableConcept ht = getHistologicType(diagnosis);
			if(ht != null)
				phenotype.addHistologicType(ht);
			
			// insitu vs invasive 
			//phenotype.addTumorExtent(null);    // insitu vs invasive
			CodeableConcept te = getTumorExtent(diagnosis);
			if(te != null)
				phenotype.addTumorExtent(te);  
			
			
			
			// add treatment (// chemo)
			IClass treatmentCls = ontology.getClass(""+FHIRConstants.TREATMENT_URI);
			for(Element treatment: report.getReportElements()){
				if(isValueSet(treatment.getCode(), treatmentCls)){
					tumor.addTreatment(treatment.getCode()); 
				}
			}
			
			// document type RULE
			// if cancer location is the same as tumor location, then tumor is 'primary'
			// else tumor type is 'recurent' if they are not the same
			// cancer has to be more specific then a tumor, then infer
			// else can't infer a type
			//tumor.setTumorType(null);  // primary vs local recurance, distance recurance  infered from rules
			if(cancer != null){
				URI tumorType = null;
				if(hasCommonBodySite(cancer.getBodySites(),tumor.getBodySites())){
					tumorType = FHIRConstants.PRIMARY_TUMOR_URI;
				}else{
					tumorType = FHIRConstants.RECURRENT_TUMOR_URI;
				}
				if(tumorType != null)
					tumor.setTumorType(FHIRUtils.getCodeableConcept(tumorType));
			}
			
			
			//tumor.addOutcome(null); // tumor is gone
			//tumor.addTumorSequenceVarient(null); // don't have one
			
		}

		return tumor;
	}
	
	// ductal, lobular infered from DX
	private CodeableConcept getHistologicType(Condition diagnosis) {
		return getLexicalPartValue(diagnosis,""+FHIRConstants.HISTOLOGIC_TYPE_URI);
	}
	
	// adnecarcionma vs sarcoma
	private CodeableConcept getCancerType(Condition dx){
		return getLexicalPartValue(dx,""+FHIRConstants.CANCER_TYPE_URI);
	}
	
	// insitu vs invasive 
	private CodeableConcept getTumorExtent(Condition diagnosis) {
		return getLexicalPartValue(diagnosis, ""+FHIRConstants.TUMOR_EXTENT_URI);
	}
	
	/**
	 * get lexical part value
	 * @param diagnosis
	 * @param value
	 * @return
	 */
	private CodeableConcept getLexicalPartValue(Condition diagnosis, String value) {
		// get values from histologictype and search all synonyms up the tree 
		IClass dx = ontology.getClass(""+FHIRUtils.getConceptURI(diagnosis.getCode()));
		if(dx != null){
			for(IClass cls : ontology.getClass(value).getSubClasses()){
				if(isLexicalPartOf(cls,dx)){
					return FHIRUtils.getCodeableConcept(cls.getURI());
				}
			}
		}
		return null;
	}
	

	private boolean hasCommonBodySite(List<CodeableConcept> aa, List<CodeableConcept> bb){
		for(CodeableConcept ca: aa){
			URI ua = FHIRUtils.getConceptURI(ca);
			for(CodeableConcept cb: bb){
				URI ub = FHIRUtils.getConceptURI(cb);
				// if equal, or first argument is more specific theen the second
				if(ua.equals(ub) || ontology.getClass(ua.toString()).hasSuperClass(ontology.getClass(ub.toString()))){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * is class a lexical part of a given diagnosis
	 * @param cls
	 * @param dx
	 * @return
	 */
	private boolean isLexicalPartOf(IClass cls, IClass dx) {
		// create a list of words for each 
		List<List<String>> partWordList = new ArrayList<List<String>>();
		for(String t: cls.getConcept().getSynonyms()){
			partWordList.add(TextTools.getWords(t.toLowerCase()));
		}
		
		// create list of terms of diagnosis and its parent
		Set<String> terms = new LinkedHashSet<String>();
		Collections.addAll(terms,dx.getConcept().getSynonyms());
		for(IClass parent: dx.getSuperClasses()){
			Collections.addAll(terms,parent.getConcept().getSynonyms());
		}
		
		// now see if one is part of the other
		for(String term: terms){
			List<String> words = TextTools.getWords(term.toLowerCase());
			for(List<String> w: partWordList){
				if(words.containsAll(w)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * is somethin in a value set
	 * @param c
	 * @param classes
	 * @return
	 */
	private boolean isValueSet(CodeableConcept c, List<IClass> classes){
		IClass cls = ontology.getClass(""+FHIRUtils.getConceptURI(c));
		if(cls == null)
			return false;
		for(IClass parent: classes){
			if(cls.hasSuperClass(parent))
				return true;
		}
		return false;
	}
	/**
	 * is somethin in a value set
	 * @param c
	 * @param classes
	 * @return
	 */
	private boolean isValueSet(CodeableConcept c, IClass parent){
		return isValueSet(c,Collections.singletonList(parent));
	}
}
