package org.healthnlp.deepphe.neo4j.summary;

import org.healthnlp.deepphe.util.FHIRConstants;

import java.net.URI;

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
}