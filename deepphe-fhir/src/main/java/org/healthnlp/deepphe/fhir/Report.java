package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.healthnlp.deepphe.util.TextUtils;
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
	private List<Element> reportElements;
	
	/**
	 * create a default report object
	 */
	public Report(){
		setStatus(CompositionStatus.FINAL);
		setLanguage(Utils.DEFAULT_LANGUAGE);
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
		super.setText(Utils.getNarrative(text));
	}
	
	public Composition setTitle(String value) {
		setIdentifier(Utils.createIdentifier(getClass().getSimpleName().toUpperCase()+"_"+TextUtils.stripSuffix(value)));
		return super.setTitle(value);
	}


	public String getReportText(){
		return Utils.getText(getText());
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
		CodeableConcept tp = Utils.getDocumentType(type);
		if(tp != null)
			setType(tp);
	}

	/**
	 * get report elements
	 * @return
	 */
	public List<Element> getReportElements() {
		if(reportElements == null)
			reportElements = new ArrayList<Element>();
		return reportElements;
	}


	/**
	 * get procedures
	 * @return
	 */
	public List<Procedure> getProcedures() {
		return (List<Procedure>) Utils.getSubList(getReportElements(),Procedure.class);
	}

	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Disease> getDiagnoses(){
		return (List<Disease>) Utils.getSubList(getReportElements(),Disease.class);
	}
	
	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Observation> getObservations(){
		return (List<Observation>) Utils.getSubList(getReportElements(),Observation.class);
	}
	
	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Finding> getFindings(){
		return (List<Finding>) Utils.getSubList(getReportElements(),Finding.class);
	}

	/**
	 * get a set of defined diagnoses for this report
	 * @return
	 */
	public List<Medication> getMedications(){
		return (List<Medication>) Utils.getSubList(getReportElements(),Medication.class);
	}
	
	/**
	 * add diagnosis that this report is describing
	 * @param dx
	 */
	public void addReportElement(Element el){
		if(getReportElements().contains(el))
			return;
		
		// add to list of diagnosis
		getReportElements().add(el);
		el.setReport(this);
			
		// add reference
		CompositionEventComponent event = addEvent();
		event.addCode(el.getCode());
		event.addDetail(Utils.getResourceReference(el));
	}

	

	public String getDisplayText() {
		return getReportText();
	}

	public String getResourceIdentifier() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String toString(){
		return getDisplayText();
	}
	
	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append("Report:\n"+getDisplayText()+"\n---\n");
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
		Utils.saveFHIR(this,id,dir);
		
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
		return URI.create(Utils.CANCER_URL+"#"+Utils.COMPOSITION);
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
		return Utils.getCodeableConcept(getConceptURI());
	}
	
	/*private void writeObject(ObjectOutputStream stream) throws IOException {
		System.out.println("WTF: "+getClass().getName());
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}*/
	
}
