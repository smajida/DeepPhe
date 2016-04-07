package org.healthnlp.deepphe.fhir.summary;

import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CancerSummary extends Summary {
	private CancerPhenotype phenotype;
	private List<TumorSummary> tumors;
	
	private String summaryType = getClass().getSimpleName();
	private String uuid = String.valueOf(Math.abs(hashCode()));
	
	public CancerSummary(){
		phenotype = new CancerPhenotype();
		
	}

	public void setReport(Report r){
		super.setReport(r);
		getPhenotype().setReport(r);
		for(TumorSummary ts: getTumors()){
			ts.setReport(r);
		}
	}
		
	/**
	 * return all facts that are contained within this fact
	 * @return
	 */
	public List<Fact> getContainedFacts(){
		List<Fact> list = super.getContainedFacts();
		List<Fact> phFacts = getPhenotype().getContainedFacts();
		for(Fact f:phFacts){
			f.addContainerIdentifier(getResourceIdentifier());
			list.add(f);
		}
		//list.addAll(getPhenotype().getContainedFacts());	
		for(TumorSummary ts: getTumors()){
			List<Fact> tsFacts = ts.getContainedFacts();
			for(Fact f:tsFacts){
				f.addContainerIdentifier(getResourceIdentifier());
				list.add(f);
			}
			//list.addAll(ts.getContainedFacts());
		}
		return list;
	}
	

	public FactList getBodySite() {
		return getFactsOrInsert(FHIRConstants.HAS_BODY_SITE);
	}
	
	public List<CancerPhenotype> getPhenotypes() {
		return Arrays.asList(getPhenotype());
	}
	
	public FactList getTreatments() {
		return getFactsOrInsert(FHIRConstants.HAS_TREATMENT);
	}
	public CancerPhenotype getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(CancerPhenotype phenotype) {
		this.phenotype = phenotype;
	}
	public FactList getOutcomes() {
		return getFacts(FHIRConstants.HAS_OUTCOME);
	}
	public List<TumorSummary> getTumors() {
		if(tumors == null)
			tumors = new ArrayList<TumorSummary>();
		return tumors;
	}
	public void addTumor(TumorSummary tumor) {
		tumor.setAnnotationType(getAnnotationType());
		getTumors().add(tumor);
	}
	
	public TumorSummary getTumorSummaryByUuid(String uuid){
		TumorSummary toret = null;
		for(TumorSummary ts: getTumors()){
			if(ts.getUuid().equals(uuid))
				toret = ts;
		}
		if(toret == null){
			toret = new TumorSummary();
			toret.setUuid(uuid);
			addTumor(toret);
		}
		
		return toret;
	}
	
	public String getDisplayText() {
		return summaryType;
	}
	public String getResourceIdentifier() {
		return summaryType+"_"+uuid;
	}
	
	public String getSummaryText() {
		StringBuffer st = new StringBuffer(super.getSummaryText());
		st.append(getPhenotype().getSummaryText()+"\n");
		for(TumorSummary ts: getTumors()){
			st.append(ts.getSummaryText()+"\n");
		}
		return st.toString();
	}
	public URI getConceptURI() {
		return FHIRConstants.CANCER_SUMMARY_URI;
	}
	
	public boolean isAppendable(Summary s) {
		// maybe if it happens at the same bodySite?
		// will just append it for now
		return s instanceof CancerSummary;
	}

	public void append(Summary s) {
		super.append(s);
		CancerSummary summary = (CancerSummary) s;

		CancerPhenotype phenotype = getPhenotype();
		phenotype.append(summary.getPhenotype());
		
		// add tumors if none exist
		for(TumorSummary t: summary.getTumors()){
			append(t);
		}
	}

	/**
	 * append tumor summary if possible
	 * @param s
	 */
	public void append(TumorSummary ts) {
		// add tumors if none exist
		if(getTumors().isEmpty()){
			addTumor(new TumorSummary());
		}
		// go over existing tumors and append if possible
		boolean appended = false;
		for(TumorSummary tumor: getTumors()){
			if(tumor.isAppendable(ts)){
				tumor.append(ts);
				appended = true;
			}
		}
		// if this tumor was not appened to existing tumors
		// add a new one
		if(!appended){
			TumorSummary tumor = new TumorSummary();
			tumor.append(ts);
			addTumor(tumor);
		}
			
	}

	public String getSummaryType() {
		return summaryType;
	}

	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
