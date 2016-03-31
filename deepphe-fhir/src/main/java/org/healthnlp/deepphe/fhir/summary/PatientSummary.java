package org.healthnlp.deepphe.fhir.summary;

import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.neo4j.ogm.annotation.Transient;

import java.net.URI;
import java.util.List;



public class PatientSummary extends Summary {

//	@Relationship(type="hasPhenotype", direction = Relationship.OUTGOING)
	@Transient
	private PatientPhenotype phenotype;

	public void setReport(Report r){
		super.setReport(r);
		getPhenotype().setReport(r);
	}

	/**
	 * return all facts that are contained within this fact
	 * @return
	 */
	@Transient
	public List<Fact> getContainedFacts(){
		List<Fact> list = super.getContainedFacts();
		list.addAll(getPhenotype().getContainedFacts());	
		return list;
	}

	@Transient
	public FactList getName() {
		return getFactsOrInsert(FHIRConstants.HAS_NAME);
	}
	@Transient
	public FactList getGender() {
		return getFactsOrInsert(FHIRConstants.HAS_GENDER);
	}
	@Transient
	public FactList getBirthDate() {
		return getFactsOrInsert(FHIRConstants.HAS_BIRTH_DATE);
	}
	@Transient
	public FactList getDeathDate() {
		return getFactsOrInsert(FHIRConstants.HAS_DEATH_DATE);
	}

	@Transient
	public PatientPhenotype getPhenotype(){
		if(phenotype == null)
			phenotype = new PatientPhenotype();
		return phenotype;
	}

	@Transient
	public void setPhenotype(PatientPhenotype phenotype) {
		this.phenotype = phenotype;
	}

	@Transient
	public FactList getOutcomes() {
		return getFacts(FHIRConstants.HAS_OUTCOME);
	}
	@Transient
	public FactList getSequenceVariant() {
		return getFacts(FHIRConstants.HAS_SEQUENCE_VARIENT);
	}

	public String getDisplayText() {
		return getClass().getSimpleName();
	}
	@Transient
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}

	public String getSummaryText() {
		StringBuffer st = new StringBuffer(super.getSummaryText());
		st.append(getPhenotype().getSummaryText()+"\n");
		return st.toString();
	}

	@Transient
	public URI getConceptURI() {
		return FHIRConstants.PATIENT_SUMMARY_URI;
	}

	public boolean isAppendable(Summary s) {
		return s instanceof PatientSummary;
	}

	public void append(Summary s) {
		super.append(s);
		PatientSummary summary = (PatientSummary) s;
		getPhenotype().append(summary.getPhenotype());
		
	}
}
