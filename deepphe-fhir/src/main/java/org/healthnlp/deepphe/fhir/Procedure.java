package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.ProcedureMention;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

public class Procedure extends org.hl7.fhir.instance.model.Procedure  implements Element{
	
	/**
	 * initialize 
	 * @param m
	 */
	public void load(Mention m){
		setCode(Utils.getCodeableConcept(m));
		setStatus(ProcedureStatus.COMPLETED);
		Utils.createIdentifier(addIdentifier(),this,m);
		// find annatomic location
		Mention al = Utils.getNearestMention(m,m.getSentence().getDocument(),Utils.ANATOMICAL_SITE);
		if(al != null){
			CodeableConcept location = addBodySite();
			Utils.setCodeableConcept(location,al);
		}

		// add mention text
		addExtension(Utils.createMentionExtension(m.getText(),m.getStartPosition(),m.getEndPosition()));
	}
	
	/**
	 * Initialize diagnosis from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public void load(ProcedureMention dm){
		// set some properties
		setCode(Utils.getCodeableConcept(dm));
		setStatus(ProcedureStatus.COMPLETED);
		Utils.createIdentifier(addIdentifier(),this,dm);
				
		// now lets take a look at the location of this diagnosis
		AnatomicalSiteMention as = (AnatomicalSiteMention) Utils.getRelatedItem(dm,dm.getBodyLocation());
		if(as == null)
			as = Utils.getAnatimicLocation(dm);
		if(as != null){
			CodeableConcept location = addBodySite();
			Utils.setCodeableConcept(location,as);
		}
		// now lets add observations
		//addEvidence();
		//addRelatedItem();

		// add mention text
		addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
	}
	

	public String getDisplayText() {
		return getCode().getText();
	}

	public String getResourceIdentifier() {
		return Utils.getIdentifier(getIdentifier());
	}
	
	public String toString(){
		return getDisplayText();
	}

	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append("Procedure:\t"+getDisplayText());
		for(CodeableConcept l: getBodySite()){
			st.append(" | location: "+l.getText());
		}
		return st.toString();
	}
	public Resource getResource() {
		return this;
	}

	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getResourceIdentifier(),dir);
		
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
		Date d = r.getDate();
		if( d != null){
			setPerformed(new DateType(d));
		}
	}
	
	public IClass getConceptClass(){
		return Utils.getConceptClass(getCode());
	}
	
	public String getConceptURI(){
		return Utils.getConceptURI(getCode());
	}

	public void copy(Resource r) {
		org.hl7.fhir.instance.model.Procedure p = (org.hl7.fhir.instance.model.Procedure) r;
		identifier = new ArrayList();
		for (Identifier i : p.getIdentifier())
			identifier.add(i.copy());
		subject = p.getSubject();
		code = p.getCode();
		bodySite = new ArrayList();
		for (CodeableConcept i : p.getBodySite())
			bodySite.add(i.copy());
		performer = new ArrayList();
		for (ProcedurePerformerComponent i : p.getPerformer())
			performer.add(i.copy());
		performed = p.getPerformed();
		encounter = p.getEncounter();
		outcome = p.getOutcome();
		report = new ArrayList();
		for (Reference i : p.getReport())
			report.add(i.copy());
		complication = new ArrayList();
		for (CodeableConcept i : p.getComplication())
			complication.add(i.copy());
		followUp = p.getFollowUp();
		notes = p.getNotes();
		extension = new ArrayList<Extension>();
		for(Extension e: p.getExtension())
			extension.add(e);
	}	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		System.out.println("WTF: "+getClass().getName());
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}
}
