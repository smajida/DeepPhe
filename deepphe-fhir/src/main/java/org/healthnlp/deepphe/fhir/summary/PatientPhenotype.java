package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import org.healthnlp.deepphe.util.FHIRConstants;

public class PatientPhenotype extends Summary {
	public String getDisplayText() {
		return  getClass().getSimpleName();
	}
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}
	public URI getConceptURI() {
		return FHIRConstants.PATIENT_PHENOTYPE_SUMMARY_URI;
	}
	public boolean isAppendable(Summary s) {
		return s instanceof PatientPhenotype;
	}
}