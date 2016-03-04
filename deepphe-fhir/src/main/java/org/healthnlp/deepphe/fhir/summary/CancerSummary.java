package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactFactory;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BackboneElement;


public class CancerSummary extends Summary {
	private CancerPhenotype phenotype;
	private FactList bodySite, treatment, outcome;
	private List<TumorSummary> tumors;
	private Report report;
	
	public CancerSummary(){
		phenotype = new CancerPhenotype();
	}

	public List<Fact> getAllFacts(){
		List<Fact> list = new ArrayList<Fact>();
		list.addAll(bodySite);
		list.addAll(treatment);
		list.addAll(outcome);
		list.addAll(getPhenotype().getAllFacts());
		for(TumorSummary ts: getTumors()){
			list.addAll(ts.getAllFacts());
		}
		return list;
	}
	
	public static class CancerPhenotype extends BackboneElement{
		private Fact cancerStage,cancerType,tumorExtent,primaryTumorClassification, distantMetastasisClassification,regionalLymphNodeClassification;
		private FactList manifestation;
			
		public BackboneElement copy() {
			// TODO Auto-generated method stub
			return null;
		}
		public List<Fact> getAllFacts() {
			List<Fact> list = new ArrayList<Fact>();
			list.add(cancerStage);
			list.add(cancerType);
			list.add(tumorExtent);
			list.add(primaryTumorClassification);
			list.add(distantMetastasisClassification);
			list.add(regionalLymphNodeClassification);
			list.addAll(manifestation);
			return list;
		}
		public Fact getCancerStage() {
			return cancerStage;
		}
		public void setCancerStage(Fact cancerStage) {
			this.cancerStage = cancerStage;
		}
		public Fact getCancerType() {
			return cancerType;
		}
		public void setCancerType(Fact cancerType) {
			this.cancerType = cancerType;
		}
		public Fact getTumorExtent() {
			return tumorExtent;
		}
		public void setTumorExtent(Fact tumorExtent) {
			this.tumorExtent = tumorExtent;
		}
		public Fact getPrimaryTumorClassification() {
			return primaryTumorClassification;
		}
		public void setPrimaryTumorClassification(Fact primaryTumorClassification) {
			this.primaryTumorClassification = primaryTumorClassification;
		}
		public Fact getDistantMetastasisClassification() {
			return distantMetastasisClassification;
		}
		public void setDistantMetastasisClassification(Fact distantMetastasisClassification) {
			this.distantMetastasisClassification = distantMetastasisClassification;
		}
		public Fact getRegionalLymphNodeClassification() {
			return regionalLymphNodeClassification;
		}
		public void setRegionalLymphNodeClassification(Fact regionalLymphNodeClassification) {
			this.regionalLymphNodeClassification = regionalLymphNodeClassification;
		}
		public FactList getManifestations() {
			if(manifestation == null)
				manifestation = new FactList();
			return manifestation;
		}
		public void addManifestation(Fact manifestation) {
			getManifestations().add(manifestation);
		}
		public String getDisplayText() {
			return getClass().getSimpleName();
		}
		public String getResourceIdentifier() {
			return getClass().getSimpleName()+"_"+Math.abs(hashCode());
		}
		public String getSummaryText() {
			String s = null; 
			StringBuffer st = new StringBuffer();
			st.append(getDisplayText()+":\n");
			s = getCancerStage() != null?getCancerStage().getSummaryText():"";
			st.append("\tStage:\t"+s+"\n");
			s = getCancerType() != null?getCancerType().getSummaryText():"";
			st.append("\tType:\t"+s+"\n");
			s = getTumorExtent() != null?getTumorExtent().getSummaryText():"";
			st.append("\tExtent:\t"+s+"\n");		
			s = getPrimaryTumorClassification() != null?getPrimaryTumorClassification().getSummaryText():"";
			st.append("\tT Class:\t"+s+"\n");
			s = getDistantMetastasisClassification() != null?getDistantMetastasisClassification().getSummaryText():"";
			st.append("\tM Class:\t"+s+"\n");
			s = getRegionalLymphNodeClassification() != null?getRegionalLymphNodeClassification().getSummaryText():"";
			st.append("\tN Class:\t"+s+"\n");
			if(!getManifestations().isEmpty()){
				st.append("\tManifistations:\n");
				for(Fact c: getManifestations()){
					st.append("\t\t"+c.getSummaryText()+"\n");
				}
			}
			return st.toString();
		}
		public URI getConceptURI() {
			return FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI;
		}
		
		/**
		 * do a very simple append of data
		 * @param ph
		 */
		public void append(CancerPhenotype ph) {
			if(ph.getCancerStage() != null && getCancerStage() == null)
				setCancerStage(ph.getCancerStage());
			if(ph.getCancerType() != null && getCancerType() == null)
				setCancerType(ph.getCancerType());
			if(ph.getTumorExtent() != null && getTumorExtent() == null)
				setTumorExtent(ph.getTumorExtent());
			if(ph.getPrimaryTumorClassification() != null && getPrimaryTumorClassification() == null)
				setPrimaryTumorClassification(ph.getPrimaryTumorClassification());
			if(ph.getDistantMetastasisClassification() != null && getDistantMetastasisClassification() == null)
				setDistantMetastasisClassification(ph.getDistantMetastasisClassification());
			if(ph.getRegionalLymphNodeClassification() != null && getRegionalLymphNodeClassification() == null)
				setRegionalLymphNodeClassification(ph.getRegionalLymphNodeClassification());
			for(Fact c: ph.getManifestations()){
				if(!FHIRUtils.contains(getManifestations(), c)){
					addManifestation(c);
				}
			}
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
	
	
	public FactList getBodySites() {
		if(bodySite == null)
			bodySite = new FactList();
		return bodySite;
	}
	public void addBodySite(Fact bodySite) {
		getBodySites().add(bodySite);
	}
	public List<CancerPhenotype> getPhenotypes() {
		//if(phenotype == null)
		//	phenotype = new ArrayList<CancerSummary.CancerPhenotype>();
		return Arrays.asList(getPhenotype());
	}
	/*
	public void addPhenotype(CancerPhenotype phenotype) {
		getPhenotypes().add(phenotype);
	}*/
	
	public FactList getTreatments() {
		if(treatment == null)
			treatment = new FactList();
		return treatment;
	}
	public CancerPhenotype getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(CancerPhenotype phenotype) {
		this.phenotype = phenotype;
	}
	public void addTreatment(Fact treatment) {
		getTreatments().add(treatment);
	}
	public FactList getOutcomes() {
		if(outcome == null)
			outcome = new FactList();
		return outcome;
	}
	public void addOutcome(Fact outcome) {
		getOutcomes().add(outcome);
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
		st.append("\tBody Sites:\t");
		for(Fact c: getBodySites()){
			st.append(c.getSummaryText()+"  ");
		}
		st.append("\n");
		// look at treatment
		st.append("\tTreatments:\n");
		for(Fact c: getTreatments()){
			st.append("\t\t"+c.getSummaryText()+"\n");
		}
		// look at outcomes
		st.append("\tOutcomes:\n");
		for(Fact c: getOutcomes()){
			st.append("\t\t"+c.getSummaryText()+"\n");
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
		CancerSummary summary = (CancerSummary) s;
		// add body site
		for(Fact c: summary.getBodySites()){
			if(!FHIRUtils.contains(getBodySites(),c))
				addBodySite(c);
		}
		// add treatment
		for(Fact c: summary.getTreatments()){
			if(!FHIRUtils.contains(getTreatments(),c))
				addTreatment(c);
		}
		// add outcome
		for(Fact c: summary.getOutcomes()){
			if(!FHIRUtils.contains(getOutcomes(),c))
				addOutcome(c);
		}
		
		// add phenotypes (worry about 1 for now)
		//TODO: need to make some simple assumptions for now
		CancerPhenotype phenotype = getPhenotype();
		/*for(CancerPhenotype ph: getPhenotypes()){
			phenotype = ph; 
		}
		if(phenotype == null){
			phenotype = new CancerPhenotype();
			addPhenotype(phenotype);
		}*/
		// now simply merge in the data
		//for(CancerPhenotype ph: summary.getPhenotypes()){
		phenotype.append(summary.getPhenotype());
		//}
		
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
