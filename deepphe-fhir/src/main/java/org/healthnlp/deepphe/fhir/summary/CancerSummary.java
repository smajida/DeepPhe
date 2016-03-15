package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactFactory;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BackboneElement;


public class CancerSummary extends Summary {
	private CancerPhenotype phenotype;
	private List<TumorSummary> tumors;
	private Report report;
	
	public CancerSummary(){
		phenotype = new CancerPhenotype();
	}
		
	public List<Fact> getAllFacts(){
		List<Fact> list = super.getAllFacts();
		list.addAll(getPhenotype().getAllFacts());
		for(TumorSummary ts: getTumors()){
			list.addAll(ts.getAllFacts());
		}
		return list;
	}
	
	public static class CancerPhenotype extends Summary{
		public FactList getCancerStage() {
			return getFactsOrInsert(FHIRConstants.HAS_CANCER_STAGE);
		}
		public FactList getCancerType() {
			return getFactsOrInsert(FHIRConstants.HAS_CANCER_TYPE);
		}
		public FactList getTumorExtent() {
			return getFactsOrInsert(FHIRConstants.HAS_TUMOR_EXTENT);
		}
		public FactList getPrimaryTumorClassification() {
			return getFactsOrInsert(FHIRConstants.HAS_T_CLASSIFICATION);
		}
		public FactList getDistantMetastasisClassification() {
			return getFactsOrInsert(FHIRConstants.HAS_M_CLASSIFICATION);
		}
		public FactList getRegionalLymphNodeClassification() {
			return getFactsOrInsert(FHIRConstants.HAS_N_CLASSIFICATION);
		}
		public String getDisplayText() {
			return getClass().getSimpleName();
		}
		public String getResourceIdentifier() {
			return getClass().getSimpleName()+"_"+Math.abs(hashCode());
		}
		public String getSummaryText() {
			StringBuffer st = new StringBuffer();
			st.append(getDisplayText()+":\n");
			for(String category: getFactCategories()){
				st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
				for(Fact c: getFacts(category)){
					st.append("\t\t"+c.getSummaryText()+"\n");
				}
			}
			return st.toString();
		}
		public URI getConceptURI() {
			return FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI;
		}
		public boolean isAppendable(Summary s) {
			return s instanceof CancerPhenotype;
		}
	}
	
	
	public Report getReport() {
		return report;
	}
	
	public void setReport(Report report) {
		this.report = report;
		// set report name to all text mentions
		String id = report.getResourceIdentifier();
		String tp = report.getType() == null?null:report.getType().getText();
		for(Fact f: getAllFacts()){
			f.setDocumentIdentifier(id);
			f.setDocumentType(tp);
		}
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
	public String getDisplayText() {
		return getClass().getSimpleName();
	}
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}
	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append(getDisplayText()+":\n");
		
		for(String category: getFactCategories()){
			st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
			for(Fact c: getFacts(category)){
				st.append("\t\t"+c.getSummaryText()+"\n");
			}
		}
		
		// look at phenotypes
		//for(CancerPhenotype ph: getPhenotypes()){
		st.append(getPhenotype().getSummaryText()+"\n");
		//}
		// look at tumors
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
}
