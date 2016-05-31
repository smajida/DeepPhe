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
		//TODO: what to do about multiple diagnosis and tumors
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
	 * create a fact
	 * @param cc
	 * @return
	 */
	private Fact createFact(CodeableConcept cc){
		Element e = FHIRRegistry.getInstance().getResource(FHIRUtils.getResourceIdentifer(cc));
		if(e != null)
			return FactFactory.createFact(e);
		
		Fact fact = null;
		URI uri = FHIRUtils.getConceptURI(cc);
		if(uri != null){
			IClass cls = ontology.getClass(""+uri);
			if(cls != null){
				fact = new Fact();
				if(cls.hasSuperClass(ontology.getClass(FHIRConstants.OBSERVATION)))
					fact = new ObservationFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.CONDITION)))
					fact = new ConditionFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.BODY_SITE)))
					fact = new BodySiteFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.PROCEDURE)))
					fact = new ProcedureFact();
				fact = FactFactory.createFact(cc,fact);
				addAncestors(fact);
				
			}else{
				System.err.println("WTF no class; "+cc.getText()+" "+uri);
			}
		}
		return fact;
	}
	
	
	private void addAncestors(Fact fact){
		OntologyUtils.getInstance().addAncestors(fact);
		for(Fact f:	fact.getContainedFacts()){
			OntologyUtils.getInstance().addAncestors(f);
		}
	}
	
	
	/**
	 * create cancer summary
	 * @param report
	 * @param diagnosis
	 * @return
	 */
	private CancerSummary createCancerSummary(Report report, Disease diagnosis){
		CancerSummary cancer = new CancerSummary(report.getTitle());
		
		cancer.loadTemplate(ontology);
		CancerPhenotype phenotype = cancer.getPhenotype();
		phenotype.loadTemplate(ontology);
		
		//cancer.addPhenotype(phenotype);
		
		// add body location
		for(CodeableConcept cc: diagnosis.getBodySite()){
			cancer.addFact(FHIRConstants.HAS_BODY_SITE,createFact(cc));
		}
		
		// add TNM stage infromation
		Stage stage = diagnosis.getStage();
		if(stage != null){
			if(!FHIRUtils.equals(stage.getSummary(),FHIRConstants.GENERIC_TNM))
				phenotype.addFact(FHIRConstants.HAS_CANCER_STAGE,createFact(stage.getSummary()));
			phenotype.addFact(FHIRConstants.HAS_T_CLASSIFICATION,createFact(stage.getPrimaryTumorStageCode()));
			phenotype.addFact(FHIRConstants.HAS_N_CLASSIFICATION,createFact(stage.getRegionalLymphNodeStageCode()));
			phenotype.addFact(FHIRConstants.HAS_M_CLASSIFICATION,createFact(stage.getDistantMetastasisStageCode()));
		}
		
		
		// create diagnosis fact
		Fact dx = FactFactory.createFact(diagnosis);
		addAncestors(dx);
		
		// adnecarcionma vs sarcoma
		CodeableConcept ct = getCancerType(diagnosis);
		if(ct != null){
			Fact f = createFact(ct);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_CANCER_TYPE,f);
		}
		// insitu vs invasive 
		CodeableConcept te = getTumorExtent(diagnosis);
		if(te != null){
			Fact f = createFact(te);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_TUMOR_EXTENT,f);  
		}
		
		//phenotype.addHistologicType(null); // ductal, lobular infered from DX
		CodeableConcept ht = getHistologicType(diagnosis);
		if(ht != null){
			Fact f = createFact(ht);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_HISTOLOGIC_TYPE,f);
		}
		
		cancer.loadElementsFromReport(report,ontology);
		//TODO: temporary disable to account for clinical/pathological/generic oops in the ontology
		//loadElementsFromReport(phenotype, report);
		
		return cancer;
	}
	
	
	private TumorSummary createTumorSummary(Report report, Condition diagnosis, CancerSummary cancer){
		TumorSummary tumor = new TumorSummary(report.getTitle()+createTumorId(diagnosis));
		tumor.loadTemplate(ontology);
		TumorPhenotype phenotype = tumor.getPhenotype();
		phenotype.loadTemplate(ontology);
		
		// create diagnosis fact
		Fact dx = FactFactory.createFact((Element)diagnosis);
		addAncestors(dx);
		// add body location
		for(CodeableConcept cc: diagnosis.getBodySite()){
			tumor.addFact(FHIRConstants.HAS_BODY_SITE,createFact(cc));
		}
			
		//phenotype.addHistologicType(null); // ductal, lobular infered from DX
		CodeableConcept ht = getHistologicType(diagnosis);
		if(ht != null){
			Fact f = createFact(ht);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_HISTOLOGIC_TYPE,f);
		}
		// insitu vs invasive 
		//phenotype.addTumorExtent(null);    // insitu vs invasive
		CodeableConcept te = getTumorExtent(diagnosis);
		if(te != null){
			Fact f = createFact(te);
			f.addProvenanceFact(dx);
			phenotype.addFact(FHIRConstants.HAS_TUMOR_EXTENT,f);  
		}
		
		
		// add treatment (// chemo)
		tumor.loadElementsFromReport(report,ontology);
		phenotype.loadElementsFromReport(report,ontology);
	
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
