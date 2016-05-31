package org.healthnlp.deepphe.fhir.fact;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hl7.fhir.instance.model.CodeableConcept;
import org.neo4j.ogm.annotation.GraphId;


/**
 * Fact representing a piece of information in Ontology
 * @author tseytlin
 *
 */

public class Fact {
	
	@GraphId
	Long objectId;
	
	public Long getObjectId() {
		 return objectId;
	}
	
	public void setObjectId(Long id){
		this.objectId = id;
	}
	
	private String name,uri,identifier,label,category,type = getClass().getSimpleName();
	private String summaryType, summaryId;
	private Set<String> ancestors;
	private Set<String> rulesApplied;
	private List<Fact> provenanceFacts;
	private List<TextMention> provenanceText;
	private Date recordedDate;
	private int temporalOrder;
	
	private String documentIdentifier, patientIdentifier, documentType;
	private transient Set<String> containerIdentifier;
	private Map<String,String> properties;
	
	
	public Map<String, String> getProperties() {
		if(properties == null)
			properties = new LinkedHashMap<String, String>();
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	public void addPropety(String key, String val){
		getProperties().put(key,val);
	}
	public String getProperty(String key){
		return getProperties().get(key);
	}
	public void addPropeties(Map<String,String> props){
		getProperties().putAll(props);
	}
	public String getName() {
		return name;
	}
	
	public String getFullName() {
		return getName();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getIdentifier() {
		if(identifier == null)
			identifier = FactFactory.createIdentifier(this);
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<Fact> getProvenanceFacts() {
		if(provenanceFacts == null)
			provenanceFacts = new ArrayList<Fact>();
		return provenanceFacts;
	}
	public void addProvenanceFact(Fact fact) {
		if(!getProvenanceFacts().contains(fact)){
			getProvenanceFacts().add(fact);
			getProvenanceFacts().addAll(fact.getProvenanceFacts());
		}
	}
	public void addProvenanceFacts(List<Fact> facts) {
		for(Fact f: facts){
			addProvenanceFact(f);
		}
		//facts.clear();
		//facts = null;
	}
	
	
	public String getDocumentName() {
		if(documentIdentifier != null){
			Matcher m = Pattern.compile("REPORT_(.*)_\\d+").matcher(documentIdentifier);
			if(m.matches())
				return m.group(1);
		}
		return documentIdentifier;
	}

	

	public List<TextMention> getProvenanceText() {
		if(provenanceText == null)
			provenanceText = new ArrayList<TextMention>();
		return provenanceText;
	}
	public List<String> getProvenanceMentions() {
		List<String> list = new ArrayList<String>();
		for(TextMention t: getProvenanceText()){
			list.add(t.toString());
		}
		return list;
	}
	public void addProvenanceText(TextMention mention) {
		if(!getProvenanceText().contains(mention))
			getProvenanceText().add(mention);
	}
	public String getLabel() {
		if(label == null)
			label = getName();
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String toString(){
		return getName();
	}
	public String getSummaryText(){
		return getName();
	}
	public Set<String> getAncestors() {
		if(ancestors == null)
			ancestors = new LinkedHashSet<String>();
		return ancestors;
	}
	public void addAncestor(String a) {
		getAncestors().add(a);
	}
	
	public CodeableConcept getCodeableConcept(){
		return FactFactory.createCodeableConcept(this);
	}
	public String getDocumentType(){
		return documentType;
	}
	
	public void setDocumentType(String docType){
		documentType = docType;
	}
	public void setDocumentIdentifier(String id){
		documentIdentifier = id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDocumentIdentifier() {
		return documentIdentifier;
	}
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	public Set<String> getContainerIdentifier() {
		if(containerIdentifier == null)
			containerIdentifier = new LinkedHashSet<String>();
		return containerIdentifier;
	}
	public void addContainerIdentifier(String containerIdentifier) {
		getContainerIdentifier().add(containerIdentifier);
	}
	
	public Set<String> getRulesApplied() {
		if(rulesApplied == null)
			rulesApplied = new LinkedHashSet<String>();
		return rulesApplied;
	}
	public void addRulesApplied(String ruleName) {
		getRulesApplied().add(ruleName);
	}
	
	public String getInfo(){
		StringBuffer b = new StringBuffer();
		b.append("name: "+getName()+"|");
		b.append("uri: "+getUri()+"|");
		b.append("category: "+getCategory()+"|");
		b.append("type: "+getType()+"|");
		b.append("id: "+getIdentifier()+"|");
		b.append("patient id: "+getPatientIdentifier()+"|");
		b.append("document id: "+getDocumentIdentifier()+"|");
		b.append("document type: "+getDocumentType()+"|");
		b.append("summary type: "+getSummaryType()+"|");
		b.append("summary id: "+getSummaryId()+"|");
		b.append("container ids: "+getContainerIdentifier()+"|");
		//b.append("rulesApplied: "+getRulesApplied()+"|");
		b.append("ancestors: "+getAncestors()+"\n");
	   
		return b.toString();
	}
	
	
	public Date getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}

	public int getTemporalOrder() {
		return temporalOrder;
	}

	public void setTemporalOrder(int temporalOrder) {
		this.temporalOrder = temporalOrder;
	}

	/**
	 * return all facts that are contained within this fact
	 * @return
	 */
	public List<Fact> getContainedFacts(){
		return new ArrayList<Fact>();
	}
	
	/**
	 * convinience method to add all contained facts
	 * @param facts
	 * @param fact
	 * @return
	 */
	protected List<Fact> addContainedFact(List<Fact> facts, Fact fact){
		fact.addContainerIdentifier(getIdentifier());
		facts.add(fact);
		facts.addAll(fact.getContainedFacts());
		return facts;
	}
	
	/**
	 * get a value for this fact for a given property
	 * @param property
	 * @return
	 */
	public Fact getValue(String property){
		return this;
	}
	public String getSummaryType() {
		return summaryType;
	}
	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}
	public String getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	
	public boolean equivalent(Fact fact){
		if(fact == null)
			return false;
		return getUri().equals(fact.getUri());
	}
}
