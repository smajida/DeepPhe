package org.healthnlp.deepphe.fhir.summary;

import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.DefaultFactList;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.Resource;

import java.io.File;
import java.net.URI;
import java.util.*;


public abstract class Summary extends List_  implements Element {
	protected Report report;
	protected Patient patient;
	private String annotationType = FHIRConstants.ANNOTATION_TYPE_DOCUMENT;
	protected Map<String,FactList> content;
	protected String resourceIdentifier;
	public Map<String, FactList> getContent() {
		if ( content == null ) {
			content = new LinkedHashMap<>();
		}
		return content;
	}

	/**
	 * get facts of a given category
	 * @param category
	 * @return
	 */
	public FactList getFacts(String category){
		return getContent().get(category);
	}
	
	/**
	 * get facts of a given category
	 * @param category
	 * @return
	 */
	protected FactList getFactsOrInsert(String category){
		FactList list = getContent().get(category);
		if(list == null){
			list = new DefaultFactList( category );
//			list.setCategory(category);
			getContent().put(category,list);
		}
		return list;
	}
	
	/**
	 * clear list for drools testing
	 * @param category
	 */
	public void clearFactList(String category){
		FactList list = getContent().get(category);
		if(list != null){
			list.clear();
		}
	}
	
	
	public void setResourceIdentifier(String resourceIdentifier) {
		String cname = getClass().getSimpleName()+"-";
		if(resourceIdentifier.startsWith(cname))
			resourceIdentifier = resourceIdentifier.substring(cname.length());
		this.resourceIdentifier = resourceIdentifier;
	}
	
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"-"+resourceIdentifier;
	}
	
	public String getDisplayText() {
		return getClass().getSimpleName()+" ("+resourceIdentifier+")";
	}

	
	/**
	 * get fact categories
	 * @return
	 */
	public Set<String> getFactCategories(){
		return getContent().keySet();
	}
	
	public void addFact(String category, Fact fact){
		FactList list =  getContent().get(category);
		if(list == null){
			list = new DefaultFactList( category );
//			list.setCategory(category);
			getContent().put(category,list);
		}
		list.add(fact);
	}
		
	public String getSummaryType(){
		return getClass().getSimpleName();
	}
	
	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append(getDisplayText()+":\n");
		for(String category: getFactCategories()){
			st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
			for(Fact c: getFacts(category)){
				st.append("\t\t"+c.getSummaryText()+"\n");
			}
		}
		return st.toString();
	}
	
	public Resource getResource(){
		return this;
	}
	public abstract URI getConceptURI();
	

	public void setReport(Report r) {
		report = r;
		if(r.getPatient() != null)
			setPatient(r.getPatient());
		// set report name to all text mentions
		String id = report.getResourceIdentifier();
		String tp = report.getType() == null?null:report.getType().getText();
		for(Fact f: getContainedFacts()){
			f.setDocumentIdentifier(id);
			f.setDocumentType(tp);
		}
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Report getReport() {
		return report;
	}

	public void save(File e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void copy(Resource r) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * is this summary appendable?
	 * @param s
	 * @return
	 */
	public abstract boolean isAppendable(Summary s);
	
	/**
	 * do a very simple append of data
	 * @param ph
	 */
	public void append(Summary ph) {
		// add body site
		for(String cat : ph.getFactCategories()){
			for(Fact c: ph.getFacts(cat)){
				if(getFacts(cat) == null || !FHIRUtils.contains(getFacts(cat),c)){
					addFact(cat,c);
				}
			}
		}
	}
	
	public String getAnnotationType() {
		return annotationType;
	}
	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}
	
	
	private void addIdentifiersToFact(Fact fact, String category){
		String reportId = report != null?report.getResourceIdentifier():null;
		String reportType = report != null?(report.getType() == null?null:report.getType().getText()):null;
		String patientId = patient != null?patient.getResourceIdentifier():null;
		fact.setCategory(category);
		fact.setDocumentIdentifier(reportId);
		fact.setDocumentType(reportType);
		fact.setPatientIdentifier(patientId);
		fact.addContainerIdentifier(getResourceIdentifier());
		fact.setSummaryType(getSummaryType());
		fact.setSummaryId(getResourceIdentifier());
		
	}
	
	
	/**
	 * return all facts that are contained within this fact
	 * @return
	 */
	public List<Fact> getContainedFacts(){
		ArrayList<Fact> list =  new ArrayList<Fact>();
			
		for(String category: getFactCategories()){
			for(Fact fact: getFacts(category)){
				// add IDs from this container and documents
				addIdentifiersToFact(fact, category);
				list.add(fact);
				for(Fact f: fact.getContainedFacts()){
					addIdentifiersToFact(f, category);
					
					list.add(f);
				}
			}
		}
		
		return list;
	}
	
}
