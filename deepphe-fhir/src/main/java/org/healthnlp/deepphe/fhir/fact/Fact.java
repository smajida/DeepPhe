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
	
	public CodeableConcept getCodeableConcept(){
		return FactFactory.createCodeableConcept(this);
	}
	
}
