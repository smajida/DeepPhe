package org.healthnlp.deepphe.fhir.fact;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.CodeableConcept;

/**
 * Fact representing a piece of information in Ontology
 * @author tseytlin
 *
 */
public class Fact {
	private String name,uri,identifier,label;
	private List<String> ancestors;
	private List<Fact> provenanceFacts;
	private List<TextMention> provenanceText;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getURI() {
		return uri;
	}
	public void setURI(String uri) {
		this.uri = uri;
	}
	public String getIdentifier() {
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
	public List<TextMention> getProvenanceText() {
		if(provenanceText == null)
			provenanceText = new ArrayList<TextMention>();
		return provenanceText;
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
		return getLabel();
	}
	public List<String> getAncestors() {
		if(ancestors == null)
			ancestors = new ArrayList<String>();
		return ancestors;
	}
	public void setAncestors(List<String> ancestors) {
		this.ancestors = ancestors;
	}
	
	public CodeableConcept getCodeableConcept(){
		return FactFactory.createCodeableConcept(this);
	}
	public String getDocumentType(){
		for(TextMention t: getProvenanceText()){
			if(t.getDocumentType() != null)
				return t.getDocumentType();
		}
		return null;
	}
	public void setDocumentType(String docType){
		for(TextMention t: getProvenanceText()){
			t.setDocumentType(docType);
		}
	}
	public void setDocumentIdentifier(String id){
		for(TextMention t: getProvenanceText()){
			t.setDocumentIdentifier(id);
		}
	}
}
