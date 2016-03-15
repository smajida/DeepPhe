package org.healthnlp.deepphe.fhir.summary;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BackboneElement;


public class TumorSummary extends Summary {
	private TumorPhenotype phenotype;
	public TumorSummary(){
		phenotype = new TumorPhenotype();
	}
	
	public List<Fact> getAllFacts() {
		List<Fact> list = super.getAllFacts();
		list.addAll(getPhenotype().getAllFacts());
		return list;
	}
	
	public static class TumorPhenotype extends Summary{
		public FactList getManifestations() {
			return getFactsOrInsert(FHIRConstants.HAS_MANIFESTATION);
		}
		
		public FactList getHistologicTypes() {
			return getFactsOrInsert(FHIRConstants.HAS_HISTOLOGIC_TYPE);
		}

	
		public FactList getTumorExtent() {
			return getFactsOrInsert(FHIRConstants.HAS_TUMOR_EXTENT);
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
			for(String category: getContent().keySet()){
				st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
				for(Fact c: getFacts(category)){
					st.append("\t\t"+c.getSummaryText()+"\n");
				}
			}
			return st.toString();
		}
		public boolean isAppendable(Summary s) {
			return s instanceof TumorPhenotype;
		}
				
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
	public FactList getSequenceVarients() {
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
		StringBuffer st = new StringBuffer();
		st.append(getDisplayText()+":\n");
		
		for(String category: getContent().keySet()){
			st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
			for(Fact c: getFacts(category)){
				st.append("\t\t"+c.getSummaryText()+"\n");
			}
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
