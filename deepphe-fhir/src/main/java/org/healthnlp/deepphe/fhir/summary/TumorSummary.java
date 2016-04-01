package org.healthnlp.deepphe.fhir.summary;

import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;

import java.net.URI;
import java.util.List;


public class TumorSummary extends Summary {
	private TumorPhenotype phenotype;
	public TumorSummary(){
		phenotype = new TumorPhenotype();
	}

	public void setReport(Report r){
		super.setReport(r);
		getPhenotype().setReport(r);
	}
	
	/**
	 * return all facts that are contained within this fact
	 * @return
	 */
	public List<Fact> getContainedFacts(){
		List<Fact> list = super.getContainedFacts();
		list.addAll(getPhenotype().getContainedFacts());	
		return list;
	}
	
	public FactList getTumorType() {
		return getFacts(FHIRConstants.HAS_TUMOR_TYPE);
	}
	public TumorPhenotype getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(TumorPhenotype phenotype) {
		this.phenotype = phenotype;
	}
	public FactList getTreatment() {
		return getFactsOrInsert(FHIRConstants.HAS_TREATMENT);
	}
	public FactList getSequenceVariants() {
		return getFactsOrInsert(FHIRConstants.HAS_SEQUENCE_VARIENT);
	}
	public FactList getOutcome() {
		return getFactsOrInsert(FHIRConstants.HAS_OUTCOME);
	}
	public FactList getBodySite() {
		return getFactsOrInsert(FHIRConstants.HAS_BODY_SITE);
	}
	public String getDisplayText() {
		return  getClass().getSimpleName();
	}
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}
	public String getSummaryText() {
		StringBuffer st = new StringBuffer(super.getSummaryText());
		// add phenotype
		if(getPhenotype() != null){
			st.append(getPhenotype().getSummaryText());
			st.append("\n");
		}
		return st.toString();
	}
	public URI getConceptURI() {
		return FHIRConstants.TUMOR_SUMMARY_URI;
	}
	
	
	public boolean isAppendable(Summary s) {
		if(s instanceof TumorSummary){
			// if no body site defined, assume they are the same
			if(getBodySite().isEmpty())
				return true;
			// else see if the body sites intersect
			TumorSummary ts = (TumorSummary) s;
			for(Fact c: ts.getBodySite()){
				if(FHIRUtils.contains(getBodySite(),c))
					return true;
			}
			
		}
		return false;
	}

	public void append(Summary s) {
		TumorSummary summary = (TumorSummary) s;
		
		// add body site
		for(String cat : summary.getFactCategories()){
			for(Fact c: summary.getFacts(cat)){
				if(!FHIRUtils.contains(getFacts(cat),c)){
					addFact(cat,c);
				}
			}
		}
	
		// add phenotypes (worry about 1 for now)
		getPhenotype().append(summary.getPhenotype());
			
	}
	
}
