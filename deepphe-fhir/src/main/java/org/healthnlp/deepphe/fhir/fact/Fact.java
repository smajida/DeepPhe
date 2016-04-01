package org.healthnlp.deepphe.fhir.fact;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.CodeableConcept;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Fact representing a piece of information in Ontology
 * @author tseytlin
 *
 */

@NodeEntity
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
	private Set<String> ancestors;
	private List<Fact> provenanceFacts;
	private List<TextMention> provenanceText;

	private transient String documentIdentifier, patientIdentifier, documentType;
	private transient Set<String> containerIdentifier;

	
	
	public String getName() {
		return name;
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
		getProvenanceFacts().add(fact);
	}
	public void addProvenanceFacts(List<Fact> facts) {
		getProvenanceFacts().addAll(facts);
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
		if(documentType != null)
			return documentType;
		// else search in province text
		for(TextMention t: getProvenanceText()){
			if(t.getDocumentType() != null)
				return t.getDocumentType();
		}
		return null;
	}
	
	public void setDocumentType(String docType){
		documentType = docType;
		for(TextMention t: getProvenanceText()){
			t.setDocumentType(docType);
		}
	}
	public void setDocumentIdentifier(String id){
		documentIdentifier = id;
		for(TextMention t: getProvenanceText()){
			t.setDocumentIdentifier(id);
		}
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
	
	public String getInfo(){
		StringBuffer b = new StringBuffer();
		b.append("name: "+getName()+"|");
		b.append("uri: "+getUri()+"|");
		b.append("category: "+getCategory()+"|");
		b.append("type: "+getType()+"|");
		b.append("id: "+getIdentifier()+"|");
		b.append("patient id: "+getPatientIdentifier()+"|");
		b.append("document id: "+getDocumentIdentifier()+"|");
		b.append("document tyoe: "+getDocumentType()+"|");
		b.append("container ids: "+getContainerIdentifier()+"|");
		b.append("ancestors: "+getAncestors()+"\n");
		return b.toString();
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
	
}
