package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;

import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;

public class TumorPhenotype extends Summary{
	
	private String summaryType = getClass().getSimpleName();
	private String uuid = String.valueOf(Math.abs(hashCode()));
	
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
		return summaryType+"_"+uuid;
	}
	public URI getConceptURI() {
		return FHIRConstants.TUMOR_PHENOTYPE_SUMMARY_URI;
	}
	public String getDisplayText() {
		return summaryType;
	}		
	public boolean isAppendable(Summary s) {
		return s instanceof TumorPhenotype;
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
			
}