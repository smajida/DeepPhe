package org.healthnlp.deepphe.fhir.fact;

import org.healthnlp.deepphe.util.FHIRConstants;

import java.util.ArrayList;
import java.util.List;

public class ConditionFact extends Fact {
	private FactList bodySite;
	private StageFact stage;
	public ConditionFact(){
		setType(FHIRConstants.CONDITION);
	}
	public FactList getBodySite() {
		if(bodySite == null){
			bodySite = new DefaultFactList();
			bodySite.addType(FHIRConstants.BODY_SITE);
		}
		return bodySite;
	}
	public void setBodySite(FactList bodySite) {
		this.bodySite = bodySite;
	}
	public StageFact getStage() {
		return stage;
	}
	public void setStage(StageFact stage) {
		this.stage = stage;
	}
	/*public List<Fact> getContainedFacts(){
		List<Fact> facts = new ArrayList<Fact>();
		if(stage != null){
			addContainedFact(facts,stage);
		}
		for(Fact fact: getBodySite()){
			addContainedFact(facts, fact);
		}
		return facts;
	}*/
	public String getSummaryText(){
		StringBuffer b = new StringBuffer(getLabel());
		if(!getBodySite().isEmpty())
			b.append(" | location: "+getBodySite());
		if(getStage() != null)
			b.append(" | stage: "+getStage().getSummaryText());
		return b.toString();
	}
}
