package org.healthnlp.deepphe.fhir.summary;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BackboneElement;
import org.hl7.fhir.instance.model.CodeableConcept;


public class TumorSummary extends Summary {
	private CodeableConcept tumorType;
	private TumorPhenotype phenotype;
	private List<CodeableConcept> treatment, tumorSequenceVarient, outcome, bodySite;
	
	
	public TumorSummary(){
		phenotype = new TumorPhenotype();
	}
	
	
	public static class TumorPhenotype extends BackboneElement{
		private List<CodeableConcept> manifestation, histologicType, tumorExtent;

		public List<CodeableConcept> getManifestations() {
			if(manifestation == null)
				manifestation = new ArrayList<CodeableConcept>();
			return manifestation;
		}

		public void addManifestation(CodeableConcept m) {
			getManifestations().add(m);
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
			st.append(getDisplayText()+":\n");
			st.append("\tHistological Type:\t");
			for(CodeableConcept c: getHistologicTypes()){
				st.append(c.getText()+"  ");
			}
			st.append("\n");
			st.append("\tTumor Extent:\t");
			for(CodeableConcept c: getTumorExtent()){
				st.append(c.getText()+"  ");
			}
			st.append("\n");
			if(!getManifestations().isEmpty()){
				st.append("\tManifistations:\n");
				for(CodeableConcept c: getManifestations()){
					st.append("\t\t"+c.getText()+"\n");
				}
			}
			
			return st.toString();
		}
		public BackboneElement copy() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * do a very simple append of data
		 * @param ph
		 */
		public void append(TumorPhenotype ph) {
			for(CodeableConcept c: ph.getHistologicTypes()){
				if(!FHIRUtils.contains(getHistologicTypes(), c)){
					addHistologicType(c);
				}
			}
			for(CodeableConcept c: ph.getTumorExtent()){
				if(!FHIRUtils.contains(getTumorExtent(), c)){
					addTumorExtent(c);
				}
			}
			for(CodeableConcept c: ph.getManifestations()){
				if(!FHIRUtils.contains(getManifestations(), c)){
					addManifestation(c);
				}
			}
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
	public List<CodeableConcept> getBodySites() {
		if(bodySite == null)
			bodySite = new ArrayList<CodeableConcept>();
		return bodySite;
	}
	public void addBodySite(CodeableConcept b) {
		getBodySites().add(b);
	}
	public String getDisplayText() {
		return  getClass().getSimpleName();
	}
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}
	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append(getDisplayText()+":\n");
		
		String s = getTumorType() != null?getTumorType().getText():"";
		st.append("\tType:\t"+s+"\n");
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
		st.append("\tSequence Varients:\n");
		for(CodeableConcept c: getTumorSequenceVarients()){
			st.append("\t\t"+c.getText()+"\n");
		}
		// look at outcomes
		st.append("\tOutcomes:\n");
		for(CodeableConcept c: getOutcomes()){
			st.append("\t\t"+c.getText()+"\n");
		}
		
		// add phenotype
		if(getPhenotype() != null){
			st.append(getPhenotype().getSummaryText()+"\n");
		}
		
	
		return st.toString();
	}
	public URI getConceptURI() {
		return FHIRConstants.TUMOR_SUMMARY_URI;
	}
	
	
	public boolean isAppendable(Summary s) {
		if(s instanceof TumorSummary){
			// if no body site defined, assume they are the same
			if(getBodySites().isEmpty())
				return true;
			// else see if the body sites intersect
			TumorSummary ts = (TumorSummary) s;
			for(CodeableConcept c: ts.getBodySites()){
				if(FHIRUtils.contains(getBodySites(),c))
					return true;
			}
			
		}
		return false;
	}

	public void append(Summary s) {
		TumorSummary summary = (TumorSummary) s;
		
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
		
		// add sequence
		for(CodeableConcept c: summary.getTumorSequenceVarients()){
			if(!FHIRUtils.contains(getTumorSequenceVarients(),c))
				addTumorSequenceVarient(c);
		}
		
		// add outcome
		for(CodeableConcept c: summary.getOutcomes()){
			if(!FHIRUtils.contains(getOutcomes(),c))
				addOutcome(c);
		}
		
		// add phenotypes (worry about 1 for now)
		getPhenotype().append(summary.getPhenotype());
			
	}
}
