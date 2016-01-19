package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.util.FHIRConstants;
import org.hl7.fhir.instance.model.CodeableConcept;


public class PatientSummary extends Summary {
	private List<CodeableConcept> exposure, outcome, germlineSequenceVarient;

	public List<CodeableConcept> getExposure() {
		if(exposure == null)
			exposure = new ArrayList<CodeableConcept>();
		return exposure;
	}

	public List<CodeableConcept> getOutcome() {
		if(outcome == null)
			outcome = new ArrayList<CodeableConcept>();
		return outcome;
	}

	public List<CodeableConcept> getGermlineSequenceVarient() {
		if(germlineSequenceVarient == null)
			germlineSequenceVarient = new ArrayList<CodeableConcept>();
		return germlineSequenceVarient;
	}

	public String getDisplayText() {
		return getClass().getSimpleName();
	}

	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}

	public String getSummaryText() {
		// TODO Auto-generated method stub
		return getDisplayText();
	}

	public URI getConceptURI() {
		return FHIRConstants.PATIENT_SUMMARY_URI;
	}
	
}
