package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Condition.ConditionEvidenceComponent;
import org.hl7.fhir.instance.model.Condition.ConditionLocationComponent;
import org.hl7.fhir.instance.model.Condition.ConditionRelatedItemComponent;
import org.hl7.fhir.instance.model.Condition.ConditionStatus;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

public class Finding extends Condition  implements Element{

	public Finding(){
		setCategory(Utils.CONDITION_CATEGORY_FINDING);
		setLanguageSimple(Utils.DEFAULT_LANGUAGE); // we only care about English
		setStatusSimple(ConditionStatus.confirmed); // here we only deal with 'confirmed' dx
	}
	public String getDisplaySimple() {
		return getCode().getTextSimple();
	}

	public String getIdentifierSimple() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummary() {
		StringBuffer st = new StringBuffer();
		st.append("Finding:\t"+getDisplaySimple());
		return st.toString();
	}
	public Resource getResource() {
		return this;
	}
	/**
	 * initialize 
	 * @param m
	 */
	public void initialize(Mention m){
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
			setSubject(Utils.getResourceReference(p));
			setSubjectTarget(p);
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
		subject = c.getSubject();
		encounter = c.getEncounter();
		asserter = c.getAsserter();
		dateAsserted = c.getDateAsserted();
		code = c.getCode();
		category = c.getCategory();
		status = c.getStatus();
		certainty = c.getCertainty();
		severity = c.getSeverity();
		onset = c.getOnset();
		abatement = c.getAbatement();
		if(c.getStage() != null){
			stage = ResourceFactory.getStage(c.getStage());
		}
		evidence = new ArrayList();
		for (ConditionEvidenceComponent i : c.getEvidence())
			evidence.add(i.copy());
		location = new ArrayList();
		for (ConditionLocationComponent i : c.getLocation())
			location.add(i.copy());
		relatedItem = new ArrayList();
		for (ConditionRelatedItemComponent i : c.getRelatedItem())
			relatedItem.add(i.copy());
		notes = c.getNotes();
		
	}
}
