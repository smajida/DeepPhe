package org.healthnlp.deepphe.uima.fhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.core.util.DocumentIDAnnotationUtil;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.MedicationMention;
import org.apache.ctakes.typesystem.type.textsem.ProcedureMention;
import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.fhir.AnatomicalSite;
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.util.TextUtils;
import org.hl7.fhir.instance.model.BodySite;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Procedure.ProcedureStatus;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;

public class DocumentResourceFactory {
	private static DocumentResourceFactory instance;
	private IOntology ontology;
	private OntologyUtils ontologyUtils;
	
	public DocumentResourceFactory(IOntology ont){
		ontology = ont;
		instance = this;
	}
	
	public static DocumentResourceFactory getInstance(){
		return instance;
	}
	
	
	public IOntology getOntology() {
		return ontology;
	}

	public void setOntology(IOntology ontology) {
		this.ontology = ontology;
	}

	public OntologyUtils getOntologyUtils(){
		if(ontologyUtils == null && ontology != null){
			ontologyUtils = new OntologyUtils(ontology);
		}
		return ontologyUtils;
	}
	
	/**
	 * create a Report object from DocumentAnnotation
	 * @param doc
	 * @return
	 */
	public static Report getReport(JCas cas) {
		Report r = getReport(cTAKESUtils.getDocumentText(cas));
		
		// oh, well no document title available
		String title;
		try {
			title = DocumentIDAnnotationUtil.getDocumentIdForFile(cas);
		} catch (Exception e) {
			e.printStackTrace();
			title = "Untitled";
		}
		if(title != null){
			r.setTitle(TextUtils.stripSuffix(title));
		}
		
		// find patient if available
		Patient patient = getPatient(cas);
		if(patient != null){
			r.setPatient(patient);
		}
		
		// now find all anatomical site found in a report
		for(AnatomicalSite ob: getAnatomicalSite(cas)){
			r.addReportElement(ob);
		}	
		
		// now find all observations found in a report
		for(Observation ob: getObservations(cas)){
			r.addReportElement(ob);
		}
		
		// now find all observations found in a report
		for(Finding ob: getFindings(cas)){
			r.addReportElement(ob);
		}	
		
		// find all procedures mentioned in each report
		for(Procedure p: getProcedures(cas)){
			r.addReportElement(p);
		}
		
		// now find all observations found in a report
		for(Medication ob: getMedications(cas)){
			r.addReportElement(ob);
		}		
		
		// now find all primary disease that are found in a report
		for(Disease dx: getDiagnoses(cas)){
			r.addReportElement(dx);
		}			
		
		
		return r;
	}
	
	/**
	 * create a Report object from DocumentAnnotation
	 * @param doc
	 * @return
	 */
	public static Report getReport(String text) {
		if(text == null)
			return null;
		
		Report r = new Report();
		r.setText(text);
		// some hard-coded report type values
		Map<String,String> header = FHIRUtils.getHeaderValues(text);
		String dt = header.get(FHIRUtils.DOCUMENT_HEADER_PRINCIPAL_DATE);
		if(dt != null)
			r.setDate(FHIRUtils.getDate(dt));
		String tp = header.get(FHIRUtils.DOCUMENT_HEADER_REPORT_TYPE);
		if(tp != null)
			r.setType(tp);
		return r;
	}
	
	
	public static Patient getPatient(JCas cas) {
		Patient p =  getPatient(cTAKESUtils.getDocumentText(cas));
		// TODO: age and gender
		
		return p;
	}
	
	/**
	 * get patient from the document
	 * @param doc
	 * @return
	 */
	public static Patient getPatient(org.hl7.fhir.instance.model.Patient p) {
		Patient patient = new Patient();
		patient.copy(p);
		return patient;
	}
	
	/**
	 * get patient from the document
	 * @param doc
	 * @return
	 */
	public static Patient getPatient(String text) {
		if(text == null)
			return null;
		Map<String,String> header = FHIRUtils.getHeaderValues(text);
		String pn = header.get(FHIRUtils.DOCUMENT_HEADER_PATIENT_NAME);
		if(pn != null){
			Patient p = new Patient();
			p.setPatientName(pn);
			int n = text.indexOf(pn);
			p.addExtension(FHIRUtils.createMentionExtension(pn,n,n+pn.length()));
			
			// register
			FHIRRegistry.getInstance().addResource(p);
			
			return p;
		}
		return null;
	}


	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public IClass getConceptClass(Concept c){
		String code = c.getCode();
		if(code.contains(":"))
			code = code.substring(code.indexOf(':')+1);
		return ontology.getClass(code);
	}
	
	

	public static List<Procedure> getProcedures(JCas cas) {
		List<Procedure> list = new ArrayList<Procedure>();
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.PROCEDURE_URI)){
			list.add(createProcedure(m));
		}
		return list;
	}
	
	/**
	 * 
	 */
	public static List<Disease> getDiagnoses(JCas cas) {
		List<Disease> list = new ArrayList<Disease>();
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.DIAGNOSIS_URI)){
			list.add(createDiagnosis(m));
		}
		return list;
	}
	
	public static List<Medication> getMedications(JCas cas) {
		List<Medication> list = new ArrayList<Medication>();
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas,FHIRConstants.MEDICATION_URI)){
			list.add(createMedication(m));
		}
		return list;
	}

	public static List<Finding> getFindings(JCas cas) {
		List<Finding> list = new ArrayList<Finding>();
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.FINDING_URI) ){
			list.add(createFinding(m));
		}
		//TODO: add TNM stuff for now
		for(Annotation  a: cTAKESUtils.getAnnotationsByType(cas, TnmClassification.type)){
			TnmClassification tnm = (TnmClassification) a;
			if(tnm.getSize() != null){
				list.add(createFinding(tnm.getSize()));
			}
			if(tnm.getMetastasis() != null){
				list.add(createFinding(tnm.getMetastasis()));
			}
			if(tnm.getNodeSpread() != null){
				list.add(createFinding(tnm.getNodeSpread()));
			}
		}
		
		return list;
	}
	
	public static List<AnatomicalSite> getAnatomicalSite(JCas cas){
		List<AnatomicalSite> list = new ArrayList<AnatomicalSite>();
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas,FHIRConstants.BODY_SITE_URI)){
			list.add(createAnatomicalSite(m));
		}
		return list;
	}
	
	public static List<Observation> getObservations(JCas cas) {
		List<Observation> list = new ArrayList<Observation>();
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.OBSERVATION_URI) ){
			list.add(createObservation(m));
		}
		//TODO: this is a hack that will be removed after CancerSize becomes an obervation in cTAKES API
		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas,CancerSize.type)){
			list.add(createObservation(m));
		}
		
		return list;
	}
	
	private static boolean isType(File f, Class cls){
		return f.getName().endsWith(".xml") && f.getName().startsWith(cls.getSimpleName().toUpperCase());
	}
	
	
	public static Report getReport(Composition c){
		if(c == null)
			return null;
		Report report = new Report();
		report.copy(c);
		return report;
	}
	
	public static Finding getFinding(Condition c){
		if(c == null)
			return null;
		Finding d = new Finding();
		d.copy(c);
		return d;
	}
	public static Disease getDiagnosis(Condition c){
		if(c == null)
			return null;
		Disease d = new Disease();
		d.copy(c);
		return d;
	}
	
	public static Procedure getProcedure(org.hl7.fhir.instance.model.Procedure p){
		if(p == null)
			return null;
		Procedure pp = new Procedure();
		pp.copy(p);
		return pp;
	}
	
	public static Observation getObservation(org.hl7.fhir.instance.model.Observation p){
		if(p == null)
			return null;
		Observation pp = new Observation();
		pp.copy(p);
		return pp;
	}
	
	public static Medication getMedication(org.hl7.fhir.instance.model.Medication p){
		if(p == null)
			return null;
		Medication pp = new Medication();
		pp.copy(p);
		return pp;
	}
	
	

	public static AnatomicalSite getAnatomicalSite(BodySite p) {
		if(p == null)
			return null;
		AnatomicalSite pp = new AnatomicalSite();
		pp.copy(p);
		return pp;
	}

	
	
	public static Stage getStage(ConditionStageComponent c){
		if(c == null)
			return null;
		Stage s = new Stage();
		s.copy(c);
		return s;
	}
	
	/**
	 * if possible re-create Report FHIR object from REPORT file directory that has FHIR XML files
	 * @param dir
	 * @return
	 */
	public static Report loadReport(File reportDir) throws Exception{
		Report report = null;
		Patient patient = null;
		
		if(reportDir.exists()){
			// find report & patient first
			for(File f: reportDir.listFiles()){
				if(isType(f, Report.class)){
					report =  getReport((Composition) FHIRUtils.loadFHIR(f));
				}else if(isType(f, Patient.class)){
					patient = getPatient((org.hl7.fhir.instance.model.Patient)FHIRUtils.loadFHIR(f));
				}
			}
			// add patient to report
			if(report != null && patient != null){
				report.setPatient(patient);
			}
			
			// load other components into report
			for(File f: reportDir.listFiles()){
				if(isType(f, Finding.class)){
					report.addReportElement(getFinding((Condition) FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Disease.class)){
					report.addReportElement(getDiagnosis((Condition)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Procedure.class)){
					report.addReportElement(getProcedure((org.hl7.fhir.instance.model.Procedure)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Observation.class)){
					report.addReportElement(getObservation((org.hl7.fhir.instance.model.Observation)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Medication.class)){
					report.addReportElement(getMedication((org.hl7.fhir.instance.model.Medication)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, AnatomicalSite.class)){
					report.addReportElement(getAnatomicalSite((org.hl7.fhir.instance.model.BodySite)FHIRUtils.loadFHIR(f)));
				}
			}
		}
		
		return report;
	}

	
	public static Disease createDiagnosis(IdentifiedAnnotation dm){
		return load(new Disease(),dm);
	}
	
	/**
	 * 
	 * @param anatomicalSite
	 * @param m
	 * @return
	 */
	public static AnatomicalSite load(AnatomicalSite anatomicalSite, IdentifiedAnnotation mention) {
		anatomicalSite.setCode(cTAKESUtils.getCodeableConcept(mention));
		// add mention text
		anatomicalSite.addExtension(FHIRUtils.createMentionExtension(mention.getCoveredText(),mention.getBegin(),mention.getEnd()));
		
		// create identifier
		FHIRUtils.createIdentifier(anatomicalSite.addIdentifier(),anatomicalSite);
	
		
		// register
		FHIRRegistry.getInstance().addResource(anatomicalSite,mention);
		
		return anatomicalSite;
	}
	
	/**
	 * 
	 * @param Finding
	 * @param m
	 * @return
	 */
	public static Finding load(Finding finding, IdentifiedAnnotation mention) {
		finding.setCode(cTAKESUtils.getCodeableConcept(mention));
		
		// add mention text
		finding.addExtension(FHIRUtils.createMentionExtension(mention.getCoveredText(),mention.getBegin(),mention.getEnd()));
		
		// create identifier
		FHIRUtils.createIdentifier(finding.addIdentifier(),finding);
	
		
		// register
		FHIRRegistry.getInstance().addResource(finding,mention);
		
		return finding;
	}
	
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public static Disease load(Disease dx, IdentifiedAnnotation dm){
		// set some properties
		dx.setCode(cTAKESUtils.getCodeableConcept(dm));
		//setCertainty(); --> dm.getConfidence()
		//setSeverity(value); -- > dm.getSeverity()???
		
		
		// perhaps have annotation from Document time
		/*TimeMention tm = dm.getStartTime();
		if(tm != null){
			dx.setDateRecorded(cTAKESUtils.getDate(tm));
		}*/
			
		// now lets take a look at the location of this disease
		AnatomicalSiteMention as =  cTAKESUtils.getAnatimicLocation(dm);
		if(as != null){
			CodeableConcept cc = cTAKESUtils.getCodeableConcept(as);
			// see if there is a registered resource available
			AnatomicalSite site = (AnatomicalSite) FHIRRegistry.getInstance().getResource(as);
			if(site != null)
				FHIRUtils.addResourceReference(cc,site);
			dx.addBodySite(cc);
		}
	
		// now lets get the location relationships
		TnmClassification tnm = cTAKESUtils.getTnmClassification(dm);
		if(tnm != null){
			dx.setStage(createStage(tnm));
		}
		
		// add mention text
		dx.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		// create identifier
		FHIRUtils.createIdentifier(dx.addIdentifier(),dx);
		
		// register
		FHIRRegistry.getInstance().addResource(dx,dm);
		
		return dx;
	}
	
	public static Medication createMedication(IdentifiedAnnotation dm){
		return load(new Medication(),dm);
	}
	
	/**
	 * load medication
	 * @param md
	 * @param dm
	 * @return
	 */
	public static Medication load(Medication md, IdentifiedAnnotation dm){
		md.setCode(cTAKESUtils.getCodeableConcept(dm));
		md.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		// register
		FHIRRegistry.getInstance().addResource(md,dm);
		return md;
	}
	
	
	public static Observation createObservation(IdentifiedAnnotation dm){
		return load(new Observation(),dm);
	}
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public static Observation load(Observation ob,IdentifiedAnnotation dm){
		// set some properties
		ob.setCode(cTAKESUtils.getCodeableConcept(dm));
		
		// see if there is an ordinal interpretation
		IdentifiedAnnotation interpretation =  cTAKESUtils.getDegreeOf(dm);
		if(interpretation != null){
			ob.setInterpretation(cTAKESUtils.getCodeableConcept(interpretation));
		}
		
		// if cancer size, then use their value
		SizeMeasurement num = cTAKESUtils.getSizeMeasurement(dm);
		if(num != null){
			ob.setValue(num.getValue(),num.getUnit());
		}
		

		// add mention text
		ob.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		// add id
		FHIRUtils.createIdentifier(ob.addIdentifier(),ob);
		
		// register
		FHIRRegistry.getInstance().addResource(ob,dm);
		
		return ob;
	}
	
	
	public static Procedure createProcedure(IdentifiedAnnotation dm){
		return load(new Procedure(),dm);
	}
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public static Procedure load(Procedure pr,IdentifiedAnnotation dm){
		// set some properties
		pr.setCode(cTAKESUtils.getCodeableConcept(dm));
		pr.setStatus(ProcedureStatus.COMPLETED);
				
		// now lets take a look at the location of this disease
		AnatomicalSiteMention as = cTAKESUtils.getAnatimicLocation(dm);
		if(as != null){
			CodeableConcept location = pr.addBodySite();
			cTAKESUtils.setCodeableConcept(location,as);
			AnatomicalSite site = (AnatomicalSite) FHIRRegistry.getInstance().getResource(as);
			if(site != null)
				FHIRUtils.addResourceReference(location,site);
		}

		
		// now lets add observations
		//addEvidence();
		//addRelatedItem();

		// add mention text
		pr.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		FHIRUtils.createIdentifier(pr.addIdentifier(),pr);

		
		// register
		FHIRRegistry.getInstance().addResource(pr,dm);
		return pr;
	}
	
	
	
	public static Stage createStage(TnmClassification st) {
		return load(new Stage(),st);
	}
	

	public static AnatomicalSite createAnatomicalSite(IdentifiedAnnotation m) {
		return load(new AnatomicalSite(),m);
	}

	public static Finding createFinding(IdentifiedAnnotation m) {
		return load(new Finding(),m);
	}
	
	


	/**
	 * load stage object
	 */
	
	public static Stage load(Stage stage, TnmClassification st) {
	
		CodeableConcept c = cTAKESUtils.getCodeableConcept(st);
		c.setText(st.getCoveredText());
		stage.setSummary(c);
		
		// extract individual Stage levels if values are conflated
		if(st.getSize() != null){
			Finding f = (Finding) FHIRRegistry.getInstance().getResource(st.getSize());
			if(f == null)
				f = createFinding(st.getSize());
			stage.addAssessment(FHIRUtils.getResourceReference(f));
			stage.getAssessmentTarget().add(f);
			stage.setStringExtension(Stage.TNM_PRIMARY_TUMOR,cTAKESUtils.getConceptURI(st.getSize()));
		}
		if(st.getNodeSpread() != null){
			Finding f = (Finding) FHIRRegistry.getInstance().getResource(st.getNodeSpread());
			if(f == null)
				f = createFinding(st.getNodeSpread());
			stage.addAssessment(FHIRUtils.getResourceReference(f));
			stage.getAssessmentTarget().add(f);
			stage.setStringExtension(Stage.TNM_REGIONAL_LYMPH_NODES,cTAKESUtils.getConceptURI(st.getNodeSpread()));
		}
		if(st.getMetastasis() != null){
			Finding f = (Finding) FHIRRegistry.getInstance().getResource(st.getMetastasis());
			if(f == null)
				f = createFinding(st.getMetastasis());
			stage.addAssessment(FHIRUtils.getResourceReference(f));
			stage.getAssessmentTarget().add(f);
			stage.setStringExtension(Stage.TNM_DISTANT_METASTASIS,cTAKESUtils.getConceptURI(st.getMetastasis()));
		}
		

		// add mention text
		stage.addExtension(FHIRUtils.createMentionExtension(st.getCoveredText(),st.getBegin(),st.getEnd()));
		
		// register
		//FHIRRegistry.getInstance().addResource(stage,st);
		
		return stage;
	}
	

	
}
