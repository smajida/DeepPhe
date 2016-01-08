package org.healthnlp.deepphe.uima.fhir;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.MedicationMention;
import org.apache.ctakes.typesystem.type.textsem.ProcedureMention;
import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.fhir.Utils;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Procedure.ProcedureStatus;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
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
		Report r = getReport(Utils.getDocumentText(cas));
		
		// oh, well no document title available
		//r.setTitle(value);
		
		// find patient if available
		Patient patient = getPatient(cas);
		if(patient != null){
			r.setPatient(patient);
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
		Map<String,String> header = Utils.getHeaderValues(text);
		String dt = header.get(Utils.DOCUMENT_HEADER_PRINCIPAL_DATE);
		if(dt != null)
			r.setDate(Utils.getDate(dt));
		String tp = header.get(Utils.DOCUMENT_HEADER_REPORT_TYPE);
		if(tp != null)
			r.setType(tp);
		return r;
	}
	
	
	public static Patient getPatient(JCas cas) {
		Patient p =  getPatient(Utils.getDocumentText(cas));
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
		Map<String,String> header = Utils.getHeaderValues(text);
		String pn = header.get(Utils.DOCUMENT_HEADER_PATIENT_NAME);
		if(pn != null){
			Patient p = new Patient();
			p.setPatientName(pn);
			int n = text.indexOf(pn);
			p.addExtension(Utils.createMentionExtension(pn,n,n+pn.length()));
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
		for(IdentifiedAnnotation m: Utils.getAnnotationsByType(cas, ProcedureMention.type)){
			list.add(createProcedure((ProcedureMention) m));
		}
		return list;
	}
	
	/**
	 * 
	 */
	public static List<Disease> getDiagnoses(JCas cas) {
		List<Disease> list = new ArrayList<Disease>();
		for(IdentifiedAnnotation m: Utils.getAnnotationsByType(cas, DiseaseDisorderMention.type)){
			list.add(createDiagnosis((DiseaseDisorderMention) m));
		}
		return list;
	}
	
	public static List<Medication> getMedications(JCas cas) {
		List<Medication> list = new ArrayList<Medication>();
		for(IdentifiedAnnotation m: Utils.getAnnotationsByType(cas,MedicationMention.type)){
			list.add(createMedication((MedicationMention) m));
		}
		return list;
	}

	public static List<Finding> getFindings(JCas cas) {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	public static List<Observation> getObservations(JCas cas) {
		List<Observation> list = new ArrayList<Observation>();
		List<IdentifiedAnnotation> annotations = new ArrayList<IdentifiedAnnotation>();
		annotations.addAll(Utils.getAnnotationsByType(cas,CancerSize.type));
		annotations.addAll(Utils.getAnnotationsByType(cas,ReceptorStatus.type));
		for(IdentifiedAnnotation m: annotations ){
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
					report =  getReport((Composition) Utils.loadFHIR(f));
				}else if(isType(f, Patient.class)){
					patient = getPatient((org.hl7.fhir.instance.model.Patient)Utils.loadFHIR(f));
				}
			}
			// add patient to report
			if(report != null && patient != null){
				report.setPatient(patient);
			}
			
			// load other components into report
			for(File f: reportDir.listFiles()){
				if(isType(f, Finding.class)){
					report.addReportElement(getFinding((Condition) Utils.loadFHIR(f)));
				}else if(isType(f, Disease.class)){
					report.addReportElement(getDiagnosis((Condition)Utils.loadFHIR(f)));
				}else if(isType(f, Procedure.class)){
					report.addReportElement(getProcedure((org.hl7.fhir.instance.model.Procedure)Utils.loadFHIR(f)));
				}else if(isType(f, Observation.class)){
					report.addReportElement(getObservation((org.hl7.fhir.instance.model.Observation)Utils.loadFHIR(f)));
				}else if(isType(f, Medication.class)){
					report.addReportElement(getMedication((org.hl7.fhir.instance.model.Medication)Utils.loadFHIR(f)));
				}
			}
		}
		
		return report;
	}

	
	public static Disease createDiagnosis(DiseaseDisorderMention dm){
		return load(new Disease(),dm);
	}
	
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public static Disease load(Disease dx, DiseaseDisorderMention dm){
		// set some properties
		dx.setCode(Utils.getCodeableConcept(dm));
		//setCertainty(); --> dm.getConfidence()
		//setSeverity(value); -- > dm.getSeverity()???
		
		// create identifier
		Utils.createIdentifier(dx.addIdentifier(),dx,dm);
		
		
		// perhaps have annotation from Document time
		TimeMention tm = dm.getStartTime();
		if(tm != null){
			dx.setDateRecorded(Utils.getDate(tm));
		}
			
		// now lets take a look at the location of this disease
		AnatomicalSiteMention as = (AnatomicalSiteMention) Utils.getRelatedItem(dm,dm.getBodyLocation());
		if(as == null){
			as = Utils.getAnatimicLocation(dm);
		}
		if(as != null){
			// TODO: create body-site object as well
			dx.addBodySite(Utils.getCodeableConcept(as));
		}
	
		// now lets get the location relationships
		for(Annotation  a: Utils.getRelatedAnnotationsByType(dm,NeoplasmRelation.class)){
			if(a instanceof TnmClassification){
				dx.setStage(createStage((TnmClassification) a));
			}
		}
		
		// add mention text
		dx.addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		return dx;
	}
	
	public static Medication createMedication(MedicationMention dm){
		return load(new Medication(),dm);
	}
	
	/**
	 * load medication
	 * @param md
	 * @param dm
	 * @return
	 */
	public static Medication load(Medication md, MedicationMention dm){
		md.setCode(Utils.getCodeableConcept(dm));
		md.addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
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
		ob.setCode(Utils.getCodeableConcept(dm));
		ob.addIdentifier(Utils.createIdentifier(ob,dm));
		
		// extract value using free text if necessary
		String text = dm.getCoveredText();
		Pattern pp = Pattern.compile("(?i)(\\d*\\.\\d+)\\s*([cm]{1,2})");
		Matcher mm = pp.matcher(text);
		if(mm.find()){
			ob.setValue(mm.group(1),mm.group(2));
		}
		
		// set positive/negative
		if(dm instanceof ReceptorStatus){
			boolean value = ((ReceptorStatus)dm).getValue();
			String i = value?Utils.INTERPRETATION_POSITIVE:Utils.INTERPRETATION_NEGATIVE;
			String url = Utils.CANCER_URL;
			ob.setInterpretation(Utils.getCodeableConcept(i,url+"#"+i,"URI"));
			// new StringType(value?"Positive":"Negative"));l;// 
		}
		
		// if cancer size, then use their value
		if(dm instanceof CancerSize){
			FSArray arr = ((CancerSize)dm).getMeasurements();
			if(arr != null){
				for(int i=0;i<arr.size();i++){
					SizeMeasurement num = (SizeMeasurement) arr.get(i);
					ob.setValue(num.getValue(),num.getUnit());
					
					String ident = ob.getClass().getSimpleName().toUpperCase()+"_"+ob.getDisplayText(); 
					ob.addIdentifier(Utils.createIdentifier((ident+"_"+ob.getObservationValue()).replaceAll("\\W+","_")));
					break;
				}
			}
		}
		

		// add mention text
		ob.addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		return ob;
	}
	
	
	public static Procedure createProcedure(ProcedureMention dm){
		return load(new Procedure(),dm);
	}
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public static Procedure load(Procedure pr,ProcedureMention dm){
		// set some properties
		pr.setCode(Utils.getCodeableConcept(dm));
		pr.setStatus(ProcedureStatus.COMPLETED);
		Utils.createIdentifier(pr.addIdentifier(),pr,dm);
				
		// now lets take a look at the location of this disease
		AnatomicalSiteMention as = (AnatomicalSiteMention) Utils.getRelatedItem(dm,dm.getBodyLocation());
		if(as == null)
			as = Utils.getAnatimicLocation(dm);
		if(as != null){
			CodeableConcept location = pr.addBodySite();
			Utils.setCodeableConcept(location,as);
		}
		// now lets add observations
		//addEvidence();
		//addRelatedItem();

		// add mention text
		pr.addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		return pr;
	}
	
	
	
	public static Stage createStage(TnmClassification st) {
		return load(new Stage(),st);
	}
	
	
	/**
	 * load stage object
	 */
	
	public static Stage load(Stage stage, TnmClassification st) {
	
		CodeableConcept c = Utils.getCodeableConcept(st);
		c.setText(st.getCoveredText());
		stage.setSummary(c);
		// extract individual Stage levels if values are conflated
		if(st.getSize() != null)
			stage.setStringExtension(Utils.CANCER_URL+"#"+Utils.T_STAGE,st.getSize().getCode()+st.getSize().getValue());
		if(st.getNodeSpread() != null)
			stage.setStringExtension(Utils.CANCER_URL+"#"+Utils.N_STAGE,st.getNodeSpread().getCode()+st.getNodeSpread().getValue());
		if(st.getMetastasis() != null)
			stage.setStringExtension(Utils.CANCER_URL+"#"+Utils.M_STAGE,st.getMetastasis().getCode()+st.getMetastasis().getValue());
		

		// add mention text
		stage.addExtension(Utils.createMentionExtension(st.getCoveredText(),st.getBegin(),st.getEnd()));
		
		return stage;
	}
	

	
}
