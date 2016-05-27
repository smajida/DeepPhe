package org.healthnlp.deepphe.uima.ae;

import edu.pitt.dbmi.nlp.noble.ontology.*;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.*;
import org.healthnlp.deepphe.fhir.fact.*;
import org.healthnlp.deepphe.fhir.summary.*;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.hl7.fhir.instance.model.CodeableConcept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.ILogicExpression;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.IRestriction;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

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
		/*for(BodySiteFact f: map.keySet()){
			System.err.println("\t"+f.getSummaryText()+"\t\t -> \t"+map.get(f).getSummaryText());
		}*/
		
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
		// TODO: implement DocTimeRel comparison
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
			list.add(cancer);
		}
		
		
		/*
		CancerSummary cancer = null;
		TumorSummary tumor = null;
		
		// doc1 - cancer + tumor
		// doc2 - cancer (for now)
		// doc3 - cancer (metastatic neoplasm) - tumor (for 
		
		// explicit mentions mentions of cancer, carcinoma, any disease under 'malignant breast neoplasm' class "metastatic neoplasm"
		// If in pathology report, then generate tumor instance as well.
		//TODO: what to do about multiple diagnosis and tumors
		Disease cancerDx = null;
		for(Disease c: report.getDiagnoses()){
			if(hasTrigger(cancerTriggers,c)){
				cancerDx = c;
				break;
			}
		}

		// if cancer was found, create a summary
		if(cancerDx != null){
			cancer = createCancerSummary(report,cancerDx);
			list.add(cancer);
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
					if(cancer != null) {
						cancer.addTumor(tumor);
					}else{
						list.add(tumor);
					}
					break;
				}
			}
		}
		*/		
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
		
		cancer.loadTemplate(ontology);
		CancerPhenotype phenotype = cancer.getPhenotype();
		phenotype.loadTemplate(ontology);
		
		//TODO: this will suck out everything, we really only need to add stuff that 
		// is related to given set of tumors in diagnosis
		cancer.loadElementsFromReport(report,ontology);
		phenotype.loadElementsFromReport(report,ontology);
		
		// set Dx, remove what got sucked out from report
		cancer.clearFactList(FHIRConstants.HAS_DIAGNOSIS);
		
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
		
		
		/*// add body location
		for(CodeableConcept cc: diagnosis.getBodySite()){
			cancer.addFact(FHIRConstants.HAS_BODY_SITE,FactFactory.createFact(cc));
		}
		
		
		// create diagnosis fact
		Fact dx = FactFactory.createFact(diagnosis);
	
		// adnecarcionma vs sarcoma
		CodeableConcept ct = getCancerType(diagnosis);
		if(ct != null){
			Fact f = FactFactory.createFact(ct);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_CANCER_TYPE,f);
		}
		// insitu vs invasive 
		CodeableConcept te = getTumorExtent(diagnosis);
		if(te != null){
			Fact f = FactFactory.createFact(te);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_TUMOR_EXTENT,f);  
		}
		
		//phenotype.addHistologicType(null); // ductal, lobular infered from DX
		CodeableConcept ht = getHistologicType(diagnosis);
		if(ht != null){
			Fact f = FactFactory.createFact(ht);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_HISTOLOGIC_TYPE,f);
		}*/
		
		
		return cancer;
	}
	
	
	private TumorSummary createTumorSummary(Report report, ConditionFact diagnosis, BodySiteFact bodySite){
		TumorSummary tumor = new TumorSummary(report.getTitle()+Summary.createLocationIdentifier(bodySite));
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
		
		/*	// create diagnosis fact
		Fact dx = FactFactory.createFact((Element)diagnosis);
		// add body location
		for(CodeableConcept cc: diagnosis.getBodySite()){
			tumor.addFact(FHIRConstants.HAS_BODY_SITE,FactFactory.createFact(cc));
		}
			
		//phenotype.addHistologicType(null); // ductal, lobular infered from DX
		CodeableConcept ht = getHistologicType(diagnosis);
		if(ht != null){
			Fact f = FactFactory.createFact(ht);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_HISTOLOGIC_TYPE,f);
		}
		// insitu vs invasive 
		//phenotype.addTumorExtent(null);    // insitu vs invasive
		CodeableConcept te = getTumorExtent(diagnosis);
		if(te != null){
			Fact f = FactFactory.createFact(te);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_TUMOR_EXTENT,f);  
		}*/
		
	
	
		return tumor;
	}

	
	private String createTumorId(Condition diagnosis) {
		StringBuffer b = new StringBuffer();
		for(CodeableConcept cc: diagnosis.getBodySite()){
			b.append("-"+FHIRUtils.getConceptName(cc));
		}
		return b.toString();
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
			for(IClass cls : ontology.getClass(""+value).getSubClasses()){
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
	
	

	private boolean hasCommonBodySite(List<CodeableConcept> aa, List<CodeableConcept> bb){
		for(CodeableConcept ca: aa){
			URI ua = FHIRUtils.getConceptURI(ca);
			for(CodeableConcept cb: bb){
				URI ub = FHIRUtils.getConceptURI(cb);
				// if equal, or first argument is more specific than the second
				if(ua.equals(ub) || ontology.getClass(ua.toString()).hasSuperClass(ontology.getClass(ub.toString()))){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasCommonBodySite(FactList  aa, FactList bb){
		try {
		for(Fact ca: aa){
			URI ua = new URI(ca.getUri());
			
			for(Fact cb: bb){
				URI ub = new URI(cb.getUri());
				// if equal, or first argument is more specific than the second
				if(ua.equals(ub) || ontology.getClass(ua.toString()).hasSuperClass(ontology.getClass(ub.toString()))){
					return true;
				}
			}
		}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
