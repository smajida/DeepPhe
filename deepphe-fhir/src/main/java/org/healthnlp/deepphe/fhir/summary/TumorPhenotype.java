package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;

import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;

public class TumorPhenotype extends Summary{
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
	public boolean isAppendable(Summary s) {
		return s instanceof TumorPhenotype;
	}
			
}