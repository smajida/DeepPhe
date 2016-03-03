package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.util.FHIRConstants;


public class PatientSummary extends Summary {
	private Fact name, gender,ethnicity,birthDate,deathDate;
	private PatientPhenotype phenotype;
	private List<Fact> exposure, outcome, germlineSequenceVariant;
	public static class PatientPhenotype {
	}
	
	public Fact getName() {
		return name;
	}


	public void setName(Fact name) {
		this.name = name;
	}


	public Fact getGender() {
		return gender;
	}


	public void setGender(Fact gender) {
		this.gender = gender;
	}


	public Fact getEthnicity() {
		return ethnicity;
	}


	public void setEthnicity(Fact ethnicity) {
		this.ethnicity = ethnicity;
	}


	public Fact getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Fact birthDate) {
		this.birthDate = birthDate;
	}


	public Fact getDeathDate() {
		return deathDate;
	}


	public void setDeathDate(Fact deathDate) {
		this.deathDate = deathDate;
	}

	public PatientPhenotype getPhenotype(){
		if(phenotype == null)
			phenotype = new PatientPhenotype();
		return phenotype;
	}
	
	
	public void setPhenotype(PatientPhenotype phenotype) {
		this.phenotype = phenotype;
	}


	public List<Fact> getExposure() {
		if(exposure == null)
			exposure = new ArrayList<Fact>();
		return exposure;
	}

	public List<Fact> getOutcomes() {
		if(outcome == null)
			outcome = new ArrayList<Fact>();
		return outcome;
	}

	public List<Fact> getGermlineSequenceVariant() {
		if(germlineSequenceVariant == null)
			germlineSequenceVariant = new ArrayList<Fact>();
		return germlineSequenceVariant;
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

	public boolean isAppendable(Summary s) {
		//TODO: nothing to do here for now
		return s instanceof PatientSummary;
	}

	public void append(Summary s) {
		// TODO: nothing to do here for now
		
	}
}
