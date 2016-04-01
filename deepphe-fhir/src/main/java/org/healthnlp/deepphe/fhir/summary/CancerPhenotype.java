package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;

import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;

public class CancerPhenotype extends Summary{
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
	public URI getConceptURI() {
		return FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI;
	}
	public boolean isAppendable(Summary s) {
		return s instanceof CancerPhenotype;
	}
}