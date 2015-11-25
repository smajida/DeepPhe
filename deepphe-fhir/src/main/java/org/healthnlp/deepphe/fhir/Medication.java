package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;

import org.apache.ctakes.typesystem.type.textsem.MedicationMention;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Resource;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

public class Medication extends org.hl7.fhir.instance.model.Medication implements Element {
	public String getDisplayText() {
		return getCode().getText();
	}

	public String getResourceIdentifier() {
		return  getClass().getSimpleName().toUpperCase()+"_"+getDisplayText().replaceAll("\\W+","_");
	}

	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append("Medication:\t"+getDisplayText());
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

		// add mention text
		addExtension(Utils.createMentionExtension(m.getText(),m.getStartPosition(),m.getEndPosition()));
	}
	
	/**
	 * Initialize diagnosis from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public void load(MedicationMention dm){
		setCode(Utils.getCodeableConcept(dm));

		// add mention text
		addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
	}
	
	public IClass getConceptClass(){
		return Utils.getConceptClass(getCode());
	}
	public URI getConceptURI(){
		return Utils.getConceptURI(getCode());
	}
	
	/**
	 * assign report instance and add appropriate information from there
	 */
	public void setReport(Report r) {
		
	}
	
	
	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getResourceIdentifier(),dir);
	}

	public void copy(Resource r) {
		org.hl7.fhir.instance.model.Medication p = (org.hl7.fhir.instance.model.Medication) r;
		code = p.getCode();
		isBrand = p.getIsBrandElement();
		manufacturer = p.getManufacturer();
		product = p.getProduct();
		package_ = p.getPackage();
		extension = new ArrayList<Extension>();
		for(Extension e: p.getExtension())
			extension.add(e);
		
	}
	public String toString(){
		return getDisplayText();
	}
}
