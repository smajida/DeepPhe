package org.healthnlp.deepphe.fhir.summary;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BackboneElement;


public class TumorSummary extends Summary {
	private Fact tumorType;
	private TumorPhenotype phenotype;
	private FactList treatment, tumorSequenceVarient, outcome, bodySite;
	public TumorSummary(){
		phenotype = new TumorPhenotype();
	}
	public static class TumorPhenotype extends BackboneElement{
		private FactList manifestation, histologicType, tumorExtent;
		
		public FactList getManifestations() {
			if(manifestation == null)
				manifestation = new FactList();
			return manifestation;
		}

		public void addManifestation(Fact m) {
			getManifestations().add(m);
		}

		public FactList getHistologicTypes() {
			if(histologicType == null)
				histologicType = new FactList();
			return histologicType;
		}

		public void addHistologicType(Fact ht) {
			getHistologicTypes().add(ht);
		}

		public FactList getTumorExtent() {
			if(tumorExtent == null){
				tumorExtent = new FactList();
			}
			return tumorExtent;
		}

		public void addTumorExtent(Fact te) {
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
			for(Fact c: getHistologicTypes()){
				st.append(c.getSummaryText()+"  ");
			}
			st.append("\n");
			st.append("\tTumor Extent:\t");
			for(Fact c: getTumorExtent()){
				st.append(c.getSummaryText()+"  ");
			}
			st.append("\n");
			if(!getManifestations().isEmpty()){
				st.append("\tManifistations:\n");
				for(Fact c: getManifestations()){
					st.append("\t\t"+c.getSummaryText()+"\n");
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
			for(Fact c: ph.getHistologicTypes()){
				if(!FHIRUtils.contains(getHistologicTypes(), c)){
					addHistologicType(c);
				}
			}
			for(Fact c: ph.getTumorExtent()){
				if(!FHIRUtils.contains(getTumorExtent(), c)){
					addTumorExtent(c);
				}
			}
			for(Fact c: ph.getManifestations()){
				if(!FHIRUtils.contains(getManifestations(), c)){
					addManifestation(c);
				}
			}
		}
				
	}
	
	public Fact getTumorType() {
		return tumorType;
	}
	public void setTumorType(Fact tumorType) {
		this.tumorType = tumorType;
	}
	public TumorPhenotype getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(TumorPhenotype phenotype) {
		this.phenotype = phenotype;
	}
	public FactList getTreatments() {
		if(treatment == null)
			treatment = new FactList();
		return treatment;
	}
	public void addTreatment(Fact t) {
		getTreatments().add(t);
	}
	public FactList getTumorSequenceVarients() {
		if(tumorSequenceVarient == null)
			tumorSequenceVarient = new FactList();
		return tumorSequenceVarient;
	}
	public void addTumorSequenceVarient(Fact ts) {
		getTumorSequenceVarients().add(ts);
	}
	public FactList getOutcomes() {
		if(outcome == null)
			outcome = new FactList();
		return outcome;
	}
	public void addOutcome(Fact o) {
		getOutcomes().add(o);
	}
	public FactList getBodySites() {
		if(bodySite == null)
			bodySite = new FactList();
		return bodySite;
	}
	public void addBodySite(Fact b) {
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
		
		String s = getTumorType() != null?getTumorType().getSummaryText():"";
		st.append("\tType:\t"+s+"\n");
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
		st.append("\tSequence Varients:\n");
		for(Fact c: getTumorSequenceVarients()){
			st.append("\t\t"+c.getSummaryText()+"\n");
		}
		// look at outcomes
		st.append("\tOutcomes:\n");
		for(Fact c: getOutcomes()){
			st.append("\t\t"+c.getSummaryText()+"\n");
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
			for(Fact c: ts.getBodySites()){
				if(FHIRUtils.contains(getBodySites(),c))
					return true;
			}
			
		}
		return false;
	}

	public void append(Summary s) {
		TumorSummary summary = (TumorSummary) s;
		
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
		// add tumor type
		if(getTumorType() == null && summary.getTumorType() != null)
			setTumorType(summary.getTumorType());
		
		// add sequence
		for(Fact c: summary.getTumorSequenceVarients()){
			if(!FHIRUtils.contains(getTumorSequenceVarients(),c))
				addTumorSequenceVarient(c);
		}
		
		// add outcome
		for(Fact c: summary.getOutcomes()){
			if(!FHIRUtils.contains(getOutcomes(),c))
				addOutcome(c);
		}
		
		// add phenotypes (worry about 1 for now)
		getPhenotype().append(summary.getPhenotype());
			
	}
}
