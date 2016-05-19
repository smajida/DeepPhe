package org.healthnlp.deepphe.fhir.fact;

import org.healthnlp.deepphe.util.FHIRConstants;

import java.util.ArrayList;
import java.util.List;

public class BodySiteFact extends Fact {
	private FactList modifiers;

	public BodySiteFact(){
		setType(FHIRConstants.BODY_SITE);
	}
	
	public FactList getModifiers() {
		if ( modifiers == null ) {
			modifiers = new DefaultFactList();
		}
		return modifiers;
	}
	public void setModifiers(FactList modifiers) {
		this.modifiers = modifiers;
	}
	public void addModifier(Fact fact) {
		getModifiers().add(fact);
	}
	
	public List<Fact> getContainedFacts(){
		List<Fact> facts = new ArrayList<Fact>();
		for(Fact fact: getModifiers()){
			addContainedFact(facts, fact);
		}
		return facts;
	}
}
