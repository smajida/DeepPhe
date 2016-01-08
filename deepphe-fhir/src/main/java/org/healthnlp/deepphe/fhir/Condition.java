package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import org.hl7.fhir.instance.model.CodeableConcept;

import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Condition.ConditionEvidenceComponent;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Condition.ConditionVerificationStatus;

public class Condition extends org.hl7.fhir.instance.model.Condition {
	
	public void copy(Resource r){
		Condition c = (Condition)r;
		this.identifier = new ArrayList();
		for (Identifier i : c.getIdentifier())
			this.identifier.add(i.copy());
		patient = c.getPatient();
		encounter = c.getEncounter();
		asserter = c.getAsserter();
		dateRecorded = new DateType(c.getDateRecorded());
		code = c.getCode();
		category = c.getCategory();
		clinicalStatus = c.getClinicalStatusElement();
		verificationStatus = c.getVerificationStatusElement();
		severity = c.getSeverity();
		onset = c.getOnset();
		abatement = c.getAbatement();
		if(c.getStage() != null){
			stage =  new Stage();
			((Stage)stage).copy(c.getStage());
		}
		evidence = new ArrayList();
		for (ConditionEvidenceComponent i : c.getEvidence())
			evidence.add(i.copy());
		bodySite = new ArrayList();
		for (CodeableConcept i : c.getBodySite())
			bodySite.add(i.copy());
		/*relatedItem = new ArrayList();
		for (ConditionRelatedItemComponent i : c.getRelatedItem())
			relatedItem.add(i.copy());*/
		notes = c.getNotesElement();
		
		extension = new ArrayList<Extension>();
		for(Extension e: c.getExtension())
			extension.add(e);

	}
	
	
	public Stage getStage(){
		ConditionStageComponent st =  super.getStage();
		if(st == null || st.getSummary() == null || st.getSummary().getCoding().isEmpty())
			return null;
		if(st instanceof Stage)
			return (Stage) st;
		// convert to new stage
		Stage s = new Stage();
		s.copy(st);
		setStage(s);
		return s;
	}

	public String getDisplayText() {
		return getCode().getText();
	}

	public String getResourceIdentifier() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append("Disease:\t"+getDisplayText());
		for(CodeableConcept l: getBodySite()){
			st.append(" | location: "+l.getText());
		}
		Stage s = getStage();
		if(s != null){
			st.append(" | stage: "+s.getSummary().getText());
			//+" T:"+s.getPrimaryTumorStage()+" N: "+s.getRegionalLymphNodeStage()+" M: "+s.getDistantMetastasisStage());
		}
		return st.toString();
	}
	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getResourceIdentifier(),dir);
	}

	public Resource getResource() {
		return this;
	}

	/**
	 * assign report instance and add appropriate information from there
	 */
	public void setReport(Report r) {
		Patient p = r.getPatient();
		if(p != null){
			setPatient(Utils.getResourceReference(p));
			setPatientTarget(p);
		}
		// set date
		Date d = r.getDate();
		if( d != null){
			setDateRecorded(d);
		}
	}
	
	public URI getConceptURI(){
		return Utils.getConceptURI(getCode());
	}
	
	public String toString(){
		return getDisplayText();
	}
}
