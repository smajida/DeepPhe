package org.healthnlp.deepphe.fhir.fact;

import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.util.FHIRConstants;

public class ProcedureFact extends Fact {
	private FactList bodySite;
	private Fact method;
	public ProcedureFact(){
		setType(FHIRConstants.PROCEDURE);
	}
	public FactList getBodySite() {
		if(bodySite == null){
			bodySite = new FactList();
			bodySite.addType(FHIRConstants.BODY_SITE);
		}
		return bodySite;
	}
	public void setBodySite(FactList bodySite) {
		this.bodySite = bodySite;
	}
	public Fact getMethod() {
		return method;
	}
	public void setMethod(Fact method) {
		this.method = method;
	}
	public List<Fact> getContainedFacts(){
		List<Fact> facts = new ArrayList<Fact>();
		if(method != null){
			addContainedFact(facts,method);
		}
		for(Fact fact: getBodySite()){
			addContainedFact(facts, fact);
		}
		return facts;
	}
	public String getSummaryText(){
		StringBuffer b = new StringBuffer(getLabel());
		if(!getBodySite().isEmpty())
			b.append(" location: "+getBodySite());
		if(getMethod() != null)
			b.append(" method: "+getMethod().getSummaryText());
		return b.toString();
	}
}
