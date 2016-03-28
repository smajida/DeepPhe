package org.healthnlp.deepphe.fhir.fact;

import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.util.FHIRConstants;

public class StageFact extends Fact {
	private Fact summary;
	private FactList assessment;
	public StageFact(){
		setType(FHIRConstants.STAGE);
	}
	public Fact getSummary() {
		return summary;
	}
	public void setSummary(Fact summary) {
		this.summary = summary;
	}
	public FactList getAssessment() {
		if(assessment == null){
			assessment = new FactList();
			assessment.addType(FHIRConstants.FINDING);
		}
		return assessment;
	}
	public void setAssessment(FactList assessment) {
		this.assessment = assessment;
	}
	public List<Fact> getContainedFacts(){
		List<Fact> facts = new ArrayList<Fact>();
		if(summary != null){
			addContainedFact(facts,summary);
		}
		for(Fact fact: getAssessment()){
			addContainedFact(facts, fact);
		}
		return facts;
	}
}
