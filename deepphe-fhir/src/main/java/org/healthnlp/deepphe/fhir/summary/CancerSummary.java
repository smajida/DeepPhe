package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BackboneElement;
import org.hl7.fhir.instance.model.CodeableConcept;

public class CancerSummary extends Summary {
	public static class CancerPhenotype extends BackboneElement{
		private CodeableConcept cancerStage,cancerType,tumorExtent,primaryTumorClassification, distantMetastasisClassification,regionalLymphNodeClassification;
		private List<CodeableConcept> manifestation;
		public BackboneElement copy() {
			// TODO Auto-generated method stub
			return null;
		}
		public CodeableConcept getCancerStage() {
			return cancerStage;
		}
		public void setCancerStage(CodeableConcept cancerStage) {
			this.cancerStage = cancerStage;
		}
		public CodeableConcept getCancerType() {
			return cancerType;
		}
		public void setCancerType(CodeableConcept cancerType) {
			this.cancerType = cancerType;
		}
		public CodeableConcept getTumorExtent() {
			return tumorExtent;
		}
		public void setTumorExtent(CodeableConcept tumorExtent) {
			this.tumorExtent = tumorExtent;
		}
		public CodeableConcept getPrimaryTumorClassification() {
			return primaryTumorClassification;
		}
		public void setPrimaryTumorClassification(CodeableConcept primaryTumorClassification) {
			this.primaryTumorClassification = primaryTumorClassification;
		}
		public CodeableConcept getDistantMetastasisClassification() {
			return distantMetastasisClassification;
		}
		public void setDistantMetastasisClassification(CodeableConcept distantMetastasisClassification) {
			this.distantMetastasisClassification = distantMetastasisClassification;
		}
		public CodeableConcept getRegionalLymphNodeClassification() {
			return regionalLymphNodeClassification;
		}
		public void setRegionalLymphNodeClassification(CodeableConcept regionalLymphNodeClassification) {
			this.regionalLymphNodeClassification = regionalLymphNodeClassification;
		}
		public List<CodeableConcept> getManifestations() {
			if(manifestation == null)
				manifestation = new ArrayList<CodeableConcept>();
			return manifestation;
		}
		public void addManifestation(CodeableConcept manifestation) {
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
			s = getCancerStage() != null?getCancerStage().getText():"";
			st.append("\tStage:\t"+s+"\n");
			s = getCancerType() != null?getCancerType().getText():"";
			st.append("\tType:\t"+s+"\n");
			s = getTumorExtent() != null?getTumorExtent().getText():"";
			st.append("\tExtent:\t"+s+"\n");		
			s = getPrimaryTumorClassification() != null?getPrimaryTumorClassification().getText():"";
			st.append("\tT Class:\t"+s+"\n");
			s = getDistantMetastasisClassification() != null?getDistantMetastasisClassification().getText():"";
			st.append("\tM Class:\t"+s+"\n");
			s = getRegionalLymphNodeClassification() != null?getRegionalLymphNodeClassification().getText():"";
			st.append("\tN Class:\t"+s+"\n");
			if(!getManifestations().isEmpty()){
				st.append("\tManifistations:\n");
				for(CodeableConcept c: getManifestations()){
					st.append("\t\t"+c.getText()+"\n");
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
			for(CodeableConcept c: ph.getManifestations()){
				if(!FHIRUtils.contains(getManifestations(), c)){
					addManifestation(c);
				}
			}
		}
	}
	private List<CancerPhenotype> phenotype;
	private List<CodeableConcept> bodySite, treatment, outcome;
	private List<TumorSummary> tumors;
	
	public List<CodeableConcept> getBodySites() {
		if(bodySite == null)
			bodySite = new ArrayList<CodeableConcept>();
		return bodySite;
	}
	public void addBodySite(CodeableConcept bodySite) {
		getBodySites().add(bodySite);
	}
	public List<CancerPhenotype> getPhenotypes() {
		if(phenotype == null)
			phenotype = new ArrayList<CancerSummary.CancerPhenotype>();
		return phenotype;
	}
	public void addPhenotype(CancerPhenotype phenotype) {
		getPhenotypes().add(phenotype);
	}
	public List<CodeableConcept> getTreatments() {
		if(treatment == null)
			treatment = new ArrayList<CodeableConcept>();
		return treatment;
	}
	public void addTreatment(CodeableConcept treatment) {
		getTreatments().add(treatment);
	}
	public List<CodeableConcept> getOutcomes() {
		if(outcome == null)
			outcome = new ArrayList<CodeableConcept>();
		return outcome;
	}
	public void addOutcome(CodeableConcept outcome) {
		getOutcomes().add(outcome);
	}
	public List<TumorSummary> getTumors() {
		if(tumors == null)
			tumors = new ArrayList<TumorSummary>();
		return tumors;
	}
	public void addTumor(TumorSummary tumor) {
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
		for(CodeableConcept c: getBodySites()){
			st.append(c.getText()+"  ");
		}
		st.append("\n");
		// look at treatment
		st.append("\tTreatments:\n");
		for(CodeableConcept c: getTreatments()){
			st.append("\t\t"+c.getText()+"\n");
		}
		// look at outcomes
		st.append("\tOutcomes:\n");
		for(CodeableConcept c: getOutcomes()){
			st.append("\t\t"+c.getText()+"\n");
		}
		// look at phenotypes
		for(CancerPhenotype ph: getPhenotypes()){
			st.append(ph.getSummaryText()+"\n");
		}
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
		for(CodeableConcept c: summary.getBodySites()){
			if(!FHIRUtils.contains(getBodySites(),c))
				addBodySite(c);
		}
		// add treatment
		for(CodeableConcept c: summary.getTreatments()){
			if(!FHIRUtils.contains(getTreatments(),c))
				addTreatment(c);
		}
		// add outcome
		for(CodeableConcept c: summary.getOutcomes()){
			if(!FHIRUtils.contains(getOutcomes(),c))
				addOutcome(c);
		}
		
		// add phenotypes (worry about 1 for now)
		//TODO: need to make some simple assumptions for now
		CancerPhenotype phenotype = null;
		for(CancerPhenotype ph: getPhenotypes()){
			phenotype = ph; 
		}
		if(phenotype == null){
			phenotype = new CancerPhenotype();
			addPhenotype(phenotype);
		}
		// now simply merge in the data
		for(CancerPhenotype ph: summary.getPhenotypes()){
			phenotype.append(ph);
		}
		
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
