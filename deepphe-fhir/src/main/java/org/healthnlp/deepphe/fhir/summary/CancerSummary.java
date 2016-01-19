package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.fhir.summary.TumorSummary.TumorPhenotype;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.hl7.fhir.instance.model.BackboneElement;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Resource;

public class CancerSummary extends Summary {
	public static class CancerPhenotype extends BackboneElement{
		private CodeableConcept cancerStage,cancerType,tumorExtent,primaryTumorClassification, distantMetastasisClassification,regionalLymphNodeClassification;
		private List<CodeableConcept> manifistation;
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
		public List<CodeableConcept> getManifistations() {
			if(manifistation == null)
				manifistation = new ArrayList<CodeableConcept>();
			return manifistation;
		}
		public void addManifistation(CodeableConcept manifistation) {
			getManifistations().add(manifistation);
		}
		public String getDisplayText() {
			return getClass().getSimpleName();
		}
		public String getResourceIdentifier() {
			return getClass().getSimpleName()+"_"+Math.abs(hashCode());
		}
		public String getSummaryText() {
			StringBuffer st = new StringBuffer();
			st.append(getDisplayText());
			return st.toString();
		}
		public URI getConceptURI() {
			return FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI;
		}
	}
	private List<CancerPhenotype> phenotype;
	private List<CodeableConcept> bodySite, treatment, outcome;
	private List<TumorSummary> tumors;
	
	public List<CodeableConcept> getBodySite() {
		if(bodySite == null)
			bodySite = new ArrayList<CodeableConcept>();
		return bodySite;
	}
	public void addBodySite(CodeableConcept bodySite) {
		getBodySite().add(bodySite);
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
		st.append(getDisplayText()+":\t");
		for(CancerPhenotype ph: getPhenotypes()){
			st.append("["+ph.getSummaryText()+"] ");
		}
		for(TumorSummary ts: getTumors()){
			st.append("("+ts.getSummaryText().replaceAll("\t"," ")+") ");
		}
		return st.toString();
	}
	public URI getConceptURI() {
		return FHIRConstants.CANCER_SUMMARY_URI;
	}
}
