package org.healthnlp.deepphe.uima.ae;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Condition;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.BodySiteFact;
import org.healthnlp.deepphe.fhir.fact.ConditionFact;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactFactory;
import org.healthnlp.deepphe.fhir.fact.TNMFact;
import org.healthnlp.deepphe.fhir.summary.CancerPhenotype;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorPhenotype;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.hl7.fhir.instance.model.CodeableConcept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;

/**
 * create composition cancer and tumor summaries on document level
 * @author tseytlin
 *
 */

public class CompositionCancerSummaryAE extends JCasAnnotator_ImplBase {
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";
	private IOntology ontology;
	
	//TODO: don't hard-code this
	public static final List<String> tumorTriggers  = Arrays.asList("Lesion","Neoplasm");
	public static final List<String> cancerTriggers = Arrays.asList("Malignant_Breast_Neoplasm"); //"Metastatic_Neoplasm"
	public static final List<String> documentTypeTriggers = Arrays.asList("SP");
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			ontology = OOntology.loadOntology((String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH));
			OntologyUtils.getInstance(ontology);
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}

	/**
	 * process each composition to generate cancer/tumor summaries
	 */
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		MedicalRecord record = new MedicalRecord();
		record.setPatient(PhenotypeResourceFactory.loadPatient(jcas));
		
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){
			PatientSummary patient = createPatientSummary(report.getPatient());
			List<Summary> summaries = createSummaries(report);
			
			report.addCompositionSummaries(summaries);
			report.addCompositionSummary(patient);
			
			PhenotypeResourceFactory.saveReport(report,jcas);
			
			// print out
			record.addReport(report);
			//System.out.println(report.getSummaryText());
		}
		
		PhenotypeResourceFactory.saveMedicalRecord(record, jcas);
	}


	
	private PatientSummary createPatientSummary(Patient loadPatient) {
		PatientSummary ps = new PatientSummary();
		ps.loadTemplate(ontology);
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
	 * get conditions by locations
	 * @param report
	 * @return
	 */
	private Map<BodySiteFact,ConditionFact> getConditionsByLocation(Report report){
		Map<BodySiteFact,ConditionFact> map = new LinkedHashMap<BodySiteFact,ConditionFact>();
		for(Element e: report.getReportElements()){
			// if we have a tumor trigger
			if(e instanceof Condition && hasTrigger(tumorTriggers, (Condition)e)){
				Condition c = (Condition) e;
				for(CodeableConcept cc: c.getBodySite()){
					BodySiteFact site = (BodySiteFact) FactFactory.createFact(cc);
					//System.err.println(c.getDisplayText()+"\tat\t"+site.getSummaryText());
					addConditionToMap(site,c,map);
				}
			}
		}
		// now lets look at it
		System.out.println("tumors with locations in "+report.getTitle());
		for(BodySiteFact f: map.keySet()){
			System.out.println("\t"+f.getSummaryText()+"\t\t -> \t"+map.get(f).getSummaryText());
		}
		
		return map;
	}
	
	
	/**
	 * add condition map
	 * @param site
	 * @param c
	 * @param map
	 */
	private void addConditionToMap(BodySiteFact site, Condition c, Map<BodySiteFact,ConditionFact> map) {
		ConditionFact tumor = FactFactory.createFact(c);
		boolean added = false;
		for(BodySiteFact s: new HashSet<BodySiteFact>(map.keySet())){
			BodySiteFact merged_site = mergeFact(site,s);
			ConditionFact merged_condition = mergeFact(tumor,map.get(s));
			// if merge was succesfull, then replace the old value in map
			if(merged_site != null && merged_condition != null){
				map.remove(s);
				map.put(merged_site, merged_condition);
				added = true;
				break;
			}
		}
		if(!added){
			map.put(site,tumor);
		}
	}

	private ConditionFact mergeFact(ConditionFact a, ConditionFact b) {
		if(!isSameTime(a,b))
			return null;
			
		ConditionFact specific = (ConditionFact) OntologyUtils.getInstance().getSpecificFact(a,b);
		if(specific != null){
			ConditionFact fact = (ConditionFact) FactFactory.createFact(specific);
			fact.addProvenanceFact(a);
			fact.addProvenanceFact(b);
			return fact;
		}
		return null;
	}

	private boolean isSameTime(ConditionFact a, ConditionFact b) {
		String a_time = a.getProperty(FHIRUtils.LANGUAGE_ASPECT_DOC_TIME_REL_URL);
		String b_time = b.getProperty(FHIRUtils.LANGUAGE_ASPECT_DOC_TIME_REL_URL);
		
		//return a_time != null && b_time != null ? a_time.equals(b_time): true;
		return true;
	}

	private BodySiteFact mergeFact(BodySiteFact a, BodySiteFact b) {
		BodySiteFact specific = (BodySiteFact) OntologyUtils.getInstance().getSpecificFact(a,b);
		if(specific != null){
			//TODO: right now check side, but merge the rest of modifiers (will see what happens)
			Fact side  = getCommonModifier(a.getBodySide(),b.getBodySide());
			if(side != null || (a.getBodySide() == null && b.getBodySide() == null)){
				BodySiteFact fact = (BodySiteFact) FactFactory.createFact(specific);
				for(Fact m: a.getModifiers())
					fact.addModifier(m);
				for(Fact m: b.getModifiers())
					fact.addModifier(m);
				fact.addProvenanceFact(a);
				fact.addProvenanceFact(b);
				
				return fact;
			}
		}
		return null;
	}

	private Fact getCommonModifier(Fact a, Fact b) {
		if(a != null && b != null)
			return a.getUri().equals(b.getUri())?a:null;
		else if(a != null)
			return a;
		else if(b != null)
			return b;
		return null;
	}

	/**
	 * create Tumor and Cancer summaries
	 * @param report
	 * @return
	 */
	private List<Summary> createSummaries(Report report) {
		List<Summary> list = new ArrayList<Summary>();
	
		Map<BodySiteFact, ConditionFact> tumors = getConditionsByLocation(report);
		if(!tumors.isEmpty()){
			CancerSummary cancer = createCancerSummary(report,tumors);
			for(BodySiteFact site: tumors.keySet()){
				ConditionFact condition = tumors.get(site);
				TumorSummary tumor = createTumorSummary(report,condition,site);
				cancer.addTumor(tumor);
			}
			// add provenance document
			cancer.getContainedFacts();
			
			list.add(cancer);
		}
		
		return list;
	}

	
	
	/**
	 * create cancer summary
	 * @param report
	 * @param diagnosis
	 * @return
	 */
	private CancerSummary createCancerSummary(Report report, Map<BodySiteFact,ConditionFact> tumors){
		CancerSummary cancer = new CancerSummary(report.getTitle());
		cancer.setReport(report);
		
		cancer.loadTemplate(ontology);
		CancerPhenotype phenotype = cancer.getPhenotype();
		phenotype.loadTemplate(ontology);
		
		//TODO: this will suck out everything, we really only need to add stuff that 
		// is related to given set of tumors in diagnosis
		cancer.loadElementsFromReport(report,ontology);
		phenotype.loadElementsFromReport(report,ontology);
		
		// set Dx, remove what got sucked out from report
		cancer.clearFactList(FHIRConstants.HAS_DIAGNOSIS);
		phenotype.clearFactList(FHIRConstants.HAS_HISTOLOGIC_TYPE);
		phenotype.clearFactList(FHIRConstants.HAS_TUMOR_EXTENT);
		phenotype.clearFactList(FHIRConstants.HAS_CANCER_TYPE);
		
		
		for(BodySiteFact bodySite: tumors.keySet()){
			ConditionFact diagnosis = tumors.get(bodySite);
			
			// add body location
			cancer.addFact(FHIRConstants.HAS_BODY_SITE,bodySite);
			cancer.addFact(FHIRConstants.HAS_DIAGNOSIS,diagnosis);
			
			// infer stuff from Dx
			Fact histType = getLexicalPartValue(diagnosis,FHIRConstants.HISTOLOGIC_TYPE_URI);
			if(histType != null){
				histType.addProvenanceFact(diagnosis);
				phenotype.addFact(FHIRConstants.HAS_HISTOLOGIC_TYPE,histType);
			}
			// insitu vs invasive 
			Fact tumorExtent = getLexicalPartValue(diagnosis,FHIRConstants.TUMOR_EXTENT_URI);
			if(tumorExtent != null){
				tumorExtent.addProvenanceFact(diagnosis);
				phenotype.addFact(FHIRConstants.HAS_TUMOR_EXTENT,tumorExtent);
			}
			
			// adnecarcionma vs sarcoma
			Fact cancerType = getLexicalPartValue(diagnosis,FHIRConstants.CANCER_TYPE_URI);
			if(cancerType != null){
				cancerType.addProvenanceFact(diagnosis);
				phenotype.addFact(FHIRConstants.HAS_CANCER_TYPE,cancerType);
			}
			
		}
		
		// upgrade TNM to Clinical or Pathologic
		//inferClinicalPathologicTNM(report, phenotype);
		
		return cancer;
	}
	
	
	private void inferClinicalPathologicTNM(Report report, CancerPhenotype phenotype){
		// upgrade TNM to Clinical or Pathologic
		boolean pathologic = report.getType() != null ? FHIRUtils.getDocumentType("SP").getText().equals(report.getType().getText()) : false;
		boolean clinical = !pathologic;
		
		for(Fact fact: phenotype.getFacts(FHIRConstants.HAS_T_CLASSIFICATION)){
			if(hasTNMModifier(fact,FHIRConstants.P_MODIFIER)){
				pathologic = true;
				clinical = !pathologic;
			}else if(hasTNMModifier(fact,FHIRConstants.C_MODIFIER)){
				clinical = true;
				pathologic = !clinical;
			}
			
			if(pathologic){
				Fact cfact = FactFactory.createFact(FHIRUtils.getCodeableConcept(FHIRUtils.getPathologicalTNM_URI(fact.getName())));
				if(cfact != null){
					cfact.addProvenanceFact(fact);
					phenotype.addFact(FHIRConstants.HAS_PATHOLOGIC_T_CLASSIFICATION,cfact);
				}
			}else if(clinical){
				Fact cfact = FactFactory.createFact(FHIRUtils.getCodeableConcept(FHIRUtils.getClinicalTNM_URI(fact.getName())));
				if(cfact != null){
					cfact.addProvenanceFact(fact);
					phenotype.addFact(FHIRConstants.HAS_CLINICAL_T_CLASSIFICATION,cfact);
				}
			}
		}
		
		for(Fact fact: phenotype.getFacts(FHIRConstants.HAS_N_CLASSIFICATION)){
			if(pathologic){
				Fact cfact = FactFactory.createFact(FHIRUtils.getCodeableConcept(FHIRUtils.getPathologicalTNM_URI(fact.getName())));
				if(cfact != null){
					cfact.addProvenanceFact(fact);
					phenotype.addFact(FHIRConstants.HAS_PATHOLOGIC_N_CLASSIFICATION,cfact);
				}
			}else if(clinical){
				Fact cfact = FactFactory.createFact(FHIRUtils.getCodeableConcept(FHIRUtils.getClinicalTNM_URI(fact.getName())));
				if(cfact != null){
					cfact.addProvenanceFact(fact);
					phenotype.addFact(FHIRConstants.HAS_CLINICAL_N_CLASSIFICATION,cfact);
				}
			}
		}
		
		for(Fact fact: phenotype.getFacts(FHIRConstants.HAS_M_CLASSIFICATION)){
			if(pathologic){
				Fact cfact = FactFactory.createFact(FHIRUtils.getCodeableConcept(FHIRUtils.getPathologicalTNM_URI(fact.getName())));
				if(cfact != null){
				cfact.addProvenanceFact(fact);
				phenotype.addFact(FHIRConstants.HAS_PATHOLOGIC_M_CLASSIFICATION,cfact);
				}
			}else if(clinical){
				Fact cfact = FactFactory.createFact(FHIRUtils.getCodeableConcept(FHIRUtils.getClinicalTNM_URI(fact.getName())));
				if(cfact != null){
					cfact.addProvenanceFact(fact);
					phenotype.addFact(FHIRConstants.HAS_CLINICAL_M_CLASSIFICATION,cfact);
				}
			}
		}
	}
	
	
	private boolean hasTNMModifier(Fact fact, String modifier){
		if(fact instanceof TNMFact){
			TNMFact tnm = (TNMFact) fact;
			if(tnm.getPrefix() != null && modifier.equals(tnm.getPrefix().getName())){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	private TumorSummary createTumorSummary(Report report, ConditionFact diagnosis, BodySiteFact bodySite){
		//System.out.println("creating tumor report: "+report.getTitle()+" tumor: "+diagnosis.getName()+" site: "+bodySite.getSummaryText());
		
		TumorSummary tumor = new TumorSummary(report.getTitle()+"_"+Summary.createLocationIdentifier(diagnosis,bodySite));
		tumor.setReport(report);
		
		tumor.loadTemplate(ontology);
		TumorPhenotype phenotype = tumor.getPhenotype();
		phenotype.loadTemplate(ontology);
				
		// add everyting for NOW
		//TODO: this will suck out everything, we really only need to add stuff that 
		// is related to given set of tumors in diagnosis
		tumor.loadElementsFromReport(report,ontology);
		phenotype.loadElementsFromReport(report,ontology);
		
		// add body location
		tumor.addFact(FHIRConstants.HAS_BODY_SITE,bodySite);
		
		// set Dx, remove what got sucked out from report
		tumor.clearFactList(FHIRConstants.HAS_DIAGNOSIS);
		phenotype.clearFactList(FHIRConstants.HAS_HISTOLOGIC_TYPE);
		phenotype.clearFactList(FHIRConstants.HAS_TUMOR_EXTENT);
		
		tumor.addFact(FHIRConstants.HAS_DIAGNOSIS,diagnosis);
		
		// infer stuff from Dx
		Fact histType = getLexicalPartValue(diagnosis,FHIRConstants.HISTOLOGIC_TYPE_URI);
		if(histType != null){
			histType.addProvenanceFact(diagnosis);
			phenotype.addFact(FHIRConstants.HAS_HISTOLOGIC_TYPE,histType);
		}
		// insitu vs invasive 
		Fact tumorExtent = getLexicalPartValue(diagnosis,FHIRConstants.TUMOR_EXTENT_URI);
		if(tumorExtent != null){
			tumorExtent.addProvenanceFact(diagnosis);
			phenotype.addFact(FHIRConstants.HAS_TUMOR_EXTENT,tumorExtent);
		}
		// adnecarcionma vs sarcoma
		Fact cancerType = getLexicalPartValue(diagnosis,FHIRConstants.CANCER_TYPE_URI);
		if(cancerType != null){
			cancerType.addProvenanceFact(diagnosis);
			phenotype.addFact(FHIRConstants.HAS_CANCER_CELL_LINE,cancerType);
		}
		
	
		return tumor;
	}
	
	
	/**
	 * get lexical part value
	 * @param diagnosis
	 * @param value
	 * @return
	 */
	private Fact getLexicalPartValue(Fact fact, URI value) {
		// get values from histologictype and search all synonyms up the tree 
		IClass dx = ontology.getClass(fact.getName());
		if(dx != null){
			IClass [] values = ontology.getClass(""+value).getSubClasses();
			Arrays.sort(values);
			for(IClass cls : values){
				if(isLexicalPartOf(cls,dx)){
					try {
						return FactFactory.createFact(value.toURL().getRef(),""+cls.getURI());
					} catch (MalformedURLException e) {
						throw new Error(e);
					}
				}
			}
		}
		return null;
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
	

}
