package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;

import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;

public class CancerPhenotype extends Summary{
	private String summaryType = getClass().getSimpleName();
	private String uuid = String.valueOf(Math.abs(hashCode()));
	
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
		return summaryType;
	}
	public String getResourceIdentifier() {
		return summaryType+"_"+uuid;
	}
	public URI getConceptURI() {
		return FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI;
	}
	
	public String getSummaryType() {
		return summaryType;
	}

	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public boolean isAppendable(Summary s) {
		return s instanceof CancerPhenotype;
	}
}