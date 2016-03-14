package org.healthnlp.deepphe.fhir.summary;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.summary.CancerSummary.CancerPhenotype;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.Resource;


public abstract class Summary extends List_  implements Element {
	private Report report;
	private String annotationType = FHIRConstants.ANNOTATION_TYPE_DOCUMENT;
	protected Map<String,FactList> content;
	public Map<String, FactList> getContent() {
		if(content == null)
			content = new LinkedHashMap<String, FactList>();
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
	 * get fact categories
	 * @return
	 */
	public Set<String> getFactCategories(){
		return getContent().keySet();
	}
	
	public void addFact(String category, Fact fact){
		FactList list =  getContent().get(category);
		if(list == null){
			list = new FactList();
			getContent().put(category,list);
		}
		list.add(fact);
	}
	public List<Fact> getAllFacts(){
		List<Fact> list = new ArrayList<Fact>();
		for(FactList l: getContent().values())
			list.addAll(l);
		return list;
	}

	
	public abstract String getDisplayText();
	public abstract String getResourceIdentifier();
	public abstract String getSummaryText();
	public Resource getResource(){
		return this;
	}
	public abstract URI getConceptURI();
	

	public void setReport(Report r) {
		report = r;
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
				if(!FHIRUtils.contains(getFacts(cat),c)){
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
	
}
