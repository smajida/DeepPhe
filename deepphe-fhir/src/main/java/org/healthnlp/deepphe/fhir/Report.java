package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.TextUtils;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;


/**
 * represents a medical document that contains a set of 
 * Disease along with Evidence, Signs and Symptoms
 * @author tseytlin
 *
 */
public class Report extends Composition implements Element, Comparable<Report>{
	private int offset;
	//private CompositionEventComponent event;
	private Map<String,Element> reportElements;
	private Map<String,Summary> compositionSummary;
	
	/**
	 * create a default report object
	 */
	public Report(){
		setStatus(CompositionStatus.FINAL);
		setLanguage(FHIRUtils.DEFAULT_LANGUAGE);
		//event = addEvent();
	}

	
	
	public int getOffset() {
		return offset;
	}



	public void setOffset(int offset) {
		this.offset = offset;
	}



	/**
	 * set document text
	 * @param text
	 */
	public void setText(String text){
		super.setText(FHIRUtils.getNarrative(text));
	}
	
	public Composition setTitle(String value) {
		setIdentifier(FHIRUtils.createIdentifier(getClass().getSimpleName().toUpperCase()+"_"+TextUtils.stripSuffix(value)+"_"+Math.abs(getReportText().hashCode())));
		return super.setTitle(value);
	}


	public String getReportText(){
		return FHIRUtils.getText(getText());
	}
	
	/**
	 * set patient that this document is describing
	 */
	public void setPatient(Patient p){
		setSubject(p.getReference());
		setSubjectTarget(p);
		
		// set DateTime
		Date dt = getDate();
		if(dt != null)
			p.setCurrentDate(dt);
		
		getReportElementMap().put(p.getResourceIdentifier(),p);
		//addReportElement(p);
	}
	
	public Patient getPatient(){
		Resource r = getSubjectTarget();
		if(r == null)
			return null;
		if(r instanceof Patient)
			return (Patient) r;
		if(r instanceof org.hl7.fhir.instance.model.Patient){
			Patient p = new Patient();
			p.copy((org.hl7.fhir.instance.model.Patient) r);
			return p;
		}
		return null;
	}
	
	/**
	 * set report type
	 * @param type
	 */
	public void setType(String type){
		CodeableConcept tp = FHIRUtils.getDocumentType(type);
		if(tp != null)
			setType(tp);
	}

	/**
	 * get report elements
	 * @return
	 */
	public Collection<Element> getReportElements() {
		return getReportElementMap().values();
	}

	public Map<String,Element> getReportElementMap(){
		if(reportElements == null)
			reportElements = new LinkedHashMap<String,Element>();
		return reportElements;
	}
	
	public Element getReportElement(String id){
		return getReportElementMap().get(id);
	}

	/**
	 * get procedures
	 * @return
	 */
	public List<Procedure> getProcedures() {
		return (List<Procedure>) FHIRUtils.getSubList(getReportElements(),Procedure.class);
	}

	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Disease> getDiagnoses(){
		return (List<Disease>) FHIRUtils.getSubList(getReportElements(),Disease.class);
	}
	
	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Observation> getObservations(){
		return (List<Observation>) FHIRUtils.getSubList(getReportElements(),Observation.class);
	}
	
	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Finding> getFindings(){
		return (List<Finding>) FHIRUtils.getSubList(getReportElements(),Finding.class);
	}

	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Medication> getMedications(){
		return (List<Medication>) FHIRUtils.getSubList(getReportElements(),Medication.class);
	}
	
	/**
	 * add diagnosis that this report is describing
	 * @param el element to be added
	 */
	public void addReportElement(Element el){
		//if(getReportElements().contains(el))
		//	return;
		
		// add to list of diagnosis
		getReportElementMap().put(el.getResourceIdentifier(),el);
		el.setReport(this);
			
		// add reference
		CompositionEventComponent event = addEvent();
		event.addCode(el.getCode());
		event.addDetail(FHIRUtils.getResourceReference(el));
	}

	

	public String getDisplayText() {
		return getReportText();
	}

	public String getResourceIdentifier() {
		return FHIRUtils.getIdentifier(getIdentifier());
	}

	public String toString(){
		return getDisplayText();
	}
	
	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		
		if(getPatient() != null)
			st.append(getPatient().getSummaryText()+"\n");
		for(Disease dx: getDiagnoses()){
			st.append(dx.getSummaryText()+"\n");
		}
		for(Procedure p: getProcedures()){
			st.append(p.getSummaryText()+"\n");
		}
		for(Finding dx: getFindings()){
			st.append(dx.getSummaryText()+"\n");
		}
		for(Observation p: getObservations()){
			st.append(p.getSummaryText()+"\n");
		}
		for(Medication p: getMedications()){
			st.append(p.getSummaryText()+"\n");
		}
		if(!getCompositionSummaries().isEmpty()){
			st.append("---\nSummaries:\n");
			for(Summary s: getCompositionSummaries()){
				st.append(s.getSummaryText()+"\n");
			}
			st.append("---");
		}
		st.append("\n"+getDisplayText()+"\n---\n");
		
		return st.toString();
	}


	public Resource getResource() {
		return this;
	}

	/**
	 * persist this object to a directory
	 * @param dir
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public void save(File dir) throws Exception{
		String id = getResourceIdentifier();
		
		dir = new File(dir,TextUtils.stripSuffix(getTitle()));
		FHIRUtils.saveFHIR(this,id,dir);
		
		// go over components
		Patient pt = getPatient();
		if(pt != null)
			pt.save(dir);
		for(Element e: getReportElements()){
			e.save(dir);
		}
	}


	public void setReport(Report r) {
	}

	public URI getConceptURI(){
		return URI.create(FHIRUtils.CANCER_URL+"#"+FHIRUtils.COMPOSITION);
	}


	public void copy(Resource r) {
		Composition c = (Composition) r;
		identifier = c.getIdentifier();
		date = c.getDateElement();
		type = c.getType();
		class_ = c.getClass_();
		title = c.getTitleElement();
		status = c.getStatusElement();
		confidentiality = c.getConfidentialityElement();
		subject = c.getSubject();
		author = new ArrayList();
		for (Reference i : c.getAuthor())
			author.add(i.copy());
		attester = new ArrayList();
		for (CompositionAttesterComponent i : c.getAttester())
			attester.add(i.copy());
		custodian = c.getCustodian();
		encounter = c.getEncounter();
		section = new ArrayList();
		for (SectionComponent i : c.getSection())
			section.add(i.copy());
		setText(c.getText());
		event = new ArrayList<Composition.CompositionEventComponent>();
		for(Composition.CompositionEventComponent e: c.getEvent()){
			event.add(e.copy());
		}
		
	}



	public int compareTo(Report r) {
		if(getDate() != null && r.getDate() != null)
			return getDate().compareTo(r.getDate());
		if(getTitle() != null && r.getTitle() != null)
			return getTitle().compareTo(r.getTitle());
		return 0;
	}



	public CodeableConcept getCode() {
		return FHIRUtils.getCodeableConcept(getConceptURI());
	}
	
	public Collection<Summary> getCompositionSummaries(){
		return getCompositionSummaryMap().values();
	}
	
	public Map<String,Summary> getCompositionSummaryMap(){
		if(compositionSummary == null)
			compositionSummary = new LinkedHashMap<String,Summary>();
		return compositionSummary;
	}
	
	
	public void addCompositionSummary(Summary s){
		s.setReport(this);
		getCompositionSummaryMap().put(s.getResourceIdentifier(),s);
	}
	public void addCompositionSummaries(List<? extends Summary> s){
		for(Summary ss: s){
			addCompositionSummary(ss);
		}
	}
	
	
	public List<CancerSummary> getCancerSummaries(){
		List<CancerSummary> list = new ArrayList<CancerSummary>();
		for(Summary s: getCompositionSummaries()){
			if(s instanceof CancerSummary)
				list.add((CancerSummary) s);
		}
		return list;
	}
	
	public List<TumorSummary> getTumorSummaries(){
		List<TumorSummary> list = new ArrayList<TumorSummary>();
		for(Summary s: getCompositionSummaries()){
			if(s instanceof TumorSummary)
				list.add((TumorSummary) s);
		}
		return list;
	}
	
	public PatientSummary getPatientSummary(){
		for(Summary s: getCompositionSummaries()){
			if(s instanceof PatientSummary)
				return (PatientSummary) s;
		}
		return null;
	}



	public String getAnnotationType() {
		return FHIRConstants.ANNOTATION_TYPE_DOCUMENT;
	}
	
	/*private void writeObject(ObjectOutputStream stream) throws IOException {
		System.out.println("WTF: "+getClass().getName());
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}*/
	
}
