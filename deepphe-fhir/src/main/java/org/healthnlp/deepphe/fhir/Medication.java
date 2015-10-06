package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.ctakes.typesystem.type.textsem.MedicationMention;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Resource;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

public class Medication extends org.hl7.fhir.instance.model.Medication implements Element {
	public String getDisplay() {
		return getCode().getText();
	}

	public String getIdentifierSimple() {
		return  getClass().getSimpleName().toUpperCase()+"_"+getDisplay().replaceAll("\\W+","_");
	}

	public String getSummary() {
		StringBuffer st = new StringBuffer();
		st.append("Medication:\t"+getDisplay());
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
	}
	
	/**
	 * Initialize diagnosis from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public void initialize(MedicationMention dm){
		setCode(Utils.getCodeableConcept(dm));
	}
	
	public IClass getConceptClass(){
		return Utils.getConceptClass(getCode());
	}
	/**
	 * assign report instance and add appropriate information from there
	 */
	public void setReport(Report r) {
		
	}
	
	
	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getIdentifierSimple(),dir);
	}

	public void copy(Resource r) {
		org.hl7.fhir.instance.model.Medication p = (org.hl7.fhir.instance.model.Medication) r;
		code = p.getCode();
		isBrand = p.getIsBrandElement();
		manufacturer = p.getManufacturer();
		product = p.getProduct();
		package_ = p.getPackage();
		
	}
	public String toString(){
		return getDisplay();
	}
}
