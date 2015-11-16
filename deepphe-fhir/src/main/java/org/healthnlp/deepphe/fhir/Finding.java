package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Condition.ConditionEvidenceComponent;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.Extension;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

public class Finding extends Condition  implements Element{

	public Finding(){
		setCategory(Utils.CONDITION_CATEGORY_FINDING);
		setLanguage(Utils.DEFAULT_LANGUAGE); // we only care about English
		//setClinicalStatus();
		setVerificationStatus(ConditionVerificationStatus.CONFIRMED);
	}
	public String getDisplay() {
		return getCode().getText();
	}

	public String getIdentifierSimple() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummary() {
		StringBuffer st = new StringBuffer();
		st.append("Finding:\t"+getDisplay());
		return st.toString();
	}
	public Resource getResource() {
		return this;
	}
	/**
	 * initialize 
	 * @param m
	 */
	public void load(Mention m){
		setCode(Utils.getCodeableConcept(m));
		// create identifier
		Utils.createIdentifier(addIdentifier(),this,m);
				
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
		/*DateAndTime d = r.getDateSimple();
		if( d != null){
			setIssuedSimple(d);
		}*/
	}
	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getIdentifierSimple(),dir);
	}
	public IClass getConceptClass(){
		return Utils.getConceptClass(getCode());
	}
	public void copy(Resource r) {
		Condition c = (Condition)r;
		identifier = new ArrayList();
		for (Identifier i : c.getIdentifier())
			identifier.add(i.copy());
		// subject target?
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
			stage = ResourceFactory.getStage(c.getStage());
		}
		evidence = new ArrayList();
		for (ConditionEvidenceComponent i : c.getEvidence())
			evidence.add(i.copy());
		bodySite = new ArrayList();
		for (CodeableConcept i : c.getBodySite())
			bodySite.add(i.copy());
		notes = c.getNotesElement();
		extension = new ArrayList<Extension>();
		for(Extension e: c.getExtension())
			extension.add(e);
		
	}
	public String toString(){
		return getDisplay();
	}
}
