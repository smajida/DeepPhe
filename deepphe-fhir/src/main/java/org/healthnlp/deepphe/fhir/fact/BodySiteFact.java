package org.healthnlp.deepphe.fhir.fact;

import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.util.FHIRConstants;

public class BodySiteFact extends Fact {
	private FactList modifiers;
	private Fact side;
	public BodySiteFact(){
		setType(FHIRConstants.BODY_SITE);
	}
	
	public FactList getModifiers() {
		if(modifiers == null)
			modifiers = new FactList();
		return modifiers;
	}
	public void setModifiers(FactList modifiers) {
		this.modifiers = modifiers;
	}
	public Fact getSide() {
		return side;
	}
	public void setSide(Fact side) {
		this.side = side;
	}
	
	public List<Fact> getContainedFacts(){
		List<Fact> facts = new ArrayList<Fact>();
		if(side != null){
			addContainedFact(facts,side);
		}
		for(Fact fact: getModifiers()){
			addContainedFact(facts, fact);
		}
		return facts;
	}
}
