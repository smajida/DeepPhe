package org.healthnlp.deepphe.fhir.summary;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.fhir.summary.CancerSummary.CancerPhenotype;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.hl7.fhir.instance.model.BackboneElement;
import org.hl7.fhir.instance.model.CodeableConcept;


public class TumorSummary extends Summary {
	private CodeableConcept tumorType;
	private TumorPhenotype phenotype;
	private List<CodeableConcept> treatment, tumorSequenceVarient, outcome, bodySite;
	
	
	public static class TumorPhenotype extends BackboneElement{
		private List<CodeableConcept> manifistation, histologicType, tumorExtent;

		public List<CodeableConcept> getManifistations() {
			if(manifistation == null)
				manifistation = new ArrayList<CodeableConcept>();
			return manifistation;
		}

		public void addManifistation(CodeableConcept m) {
			getManifistations().add(m);
		}

		public List<CodeableConcept> getHistologicTypes() {
			if(histologicType == null)
				histologicType = new ArrayList<CodeableConcept>();
			return histologicType;
		}

		public void addHistologicType(CodeableConcept ht) {
			getHistologicTypes().add(ht);
		}

		public List<CodeableConcept> getTumorExtent() {
			if(tumorExtent == null){
				tumorExtent = new ArrayList<CodeableConcept>();
			}
			return tumorExtent;
		}

		public void addTumorExtent(CodeableConcept te) {
			getTumorExtent().add(te);
		}

		public String getResourceIdentifier() {
			return getClass().getSimpleName()+"_"+Math.abs(hashCode());
		}
		public URI getConceptURI() {
			return FHIRConstants.TUMOR_PHENOTYPE_SUMMARY_URI;
		}
		public String getDisplayText() {
			return getClass().getSimpleName();
		}		
		public String getSummaryText() {
			StringBuffer st = new StringBuffer();
			st.append(getDisplayText());
			return st.toString();
		}
		public BackboneElement copy() {
			// TODO Auto-generated method stub
			return null;
		}

		
	}
	
	public CodeableConcept getTumorType() {
		return tumorType;
	}
	public void setTumorType(CodeableConcept tumorType) {
		this.tumorType = tumorType;
	}
	public TumorPhenotype getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(TumorPhenotype phenotype) {
		this.phenotype = phenotype;
	}
	public List<CodeableConcept> getTreatments() {
		if(treatment == null)
			treatment = new ArrayList<CodeableConcept>();
		return treatment;
	}
	public void addTreatment(CodeableConcept t) {
		getTreatments().add(t);
	}
	public List<CodeableConcept> getTumorSequenceVarients() {
		if(tumorSequenceVarient == null)
			tumorSequenceVarient = new ArrayList<CodeableConcept>();
		return tumorSequenceVarient;
	}
	public void addTumorSequenceVarient(CodeableConcept ts) {
		getTumorSequenceVarients().add(ts);
	}
	public List<CodeableConcept> getOutcomes() {
		if(outcome == null)
			outcome = new ArrayList<CodeableConcept>();
		return outcome;
	}
	public void addOutcome(CodeableConcept o) {
		getOutcomes().add(o);
	}
	public List<CodeableConcept> getBodySite() {
		if(bodySite == null)
			bodySite = new ArrayList<CodeableConcept>();
		return bodySite;
	}
	public void addBodySite(CodeableConcept b) {
		getBodySite().add(b);
	}
	public String getDisplayText() {
		return  getClass().getSimpleName();
	}
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}
	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append(getDisplayText()+":\t");
		if(getPhenotype() != null){
			st.append("["+getPhenotype().getSummaryText()+"]");
		}
		return st.toString();
	}
	public URI getConceptURI() {
		return FHIRConstants.TUMOR_SUMMARY_URI;
	}
}
