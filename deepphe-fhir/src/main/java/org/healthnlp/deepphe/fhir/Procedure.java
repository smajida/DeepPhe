package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.util.ArrayList;

import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.ProcedureMention;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

public class Procedure extends org.hl7.fhir.instance.model.Procedure  implements Element{
	
	/**
	 * initialize 
	 * @param m
	 */
	public void initialize(Mention m){
		setType(Utils.getCodeableConcept(m));
		Utils.createIdentifier(addIdentifier(),this,m);
		// find annatomic location
		Mention al = Utils.getNearestMention(m,m.getSentence().getDocument(),Utils.ANATOMICAL_SITE);
		if(al != null){
			CodeableConcept location = addBodySite();
			Utils.setCodeableConcept(location,al);
		}
	}
	
	/**
	 * Initialize diagnosis from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public void initialize(ProcedureMention dm){
		// set some properties
		setType(Utils.getCodeableConcept(dm));
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
	}
	

	public String getDisplaySimple() {
		return getType().getTextSimple();
	}

	public String getIdentifierSimple() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummary() {
		StringBuffer st = new StringBuffer();
		st.append("Procedure:\t"+getDisplaySimple());
		for(CodeableConcept l: getBodySite()){
			st.append(" | location: "+l.getTextSimple());
		}
		return st.toString();
	}
	public Resource getResource() {
		return this;
	}

	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getIdentifierSimple(),dir);
		
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
		DateAndTime d = r.getDateSimple();
		if( d != null){
			Period pp = new Period();
			pp.setStartSimple(d);
			pp.setEndSimple(d);
			setDate(pp);
		}
	}
	
	public IClass getConceptClass(){
		return Utils.getConceptClass(getType());
	}

	public void copy(Resource r) {
		org.hl7.fhir.instance.model.Procedure p = (org.hl7.fhir.instance.model.Procedure) r;
		identifier = new ArrayList();
		for (Identifier i : p.getIdentifier())
			identifier.add(i.copy());
		subject = p.getSubject();
		type = p.getType();
		bodySite = new ArrayList();
		for (CodeableConcept i : p.getBodySite())
			bodySite.add(i.copy());
		indication = new ArrayList();
		for (CodeableConcept i : p.getIndication())
			indication.add(i.copy());
		performer = new ArrayList();
		for (ProcedurePerformerComponent i : p.getPerformer())
			performer.add(i.copy());
		date = p.getDate();
		encounter = p.getEncounter();
		outcome = p.getOutcome();
		report = new ArrayList();
		for (ResourceReference i : p.getReport())
			report.add(i.copy());
		complication = new ArrayList();
		for (CodeableConcept i : p.getComplication())
			complication.add(i.copy());
		followUp = p.getFollowUp();
		relatedItem = new ArrayList();
		for (ProcedureRelatedItemComponent i : p.getRelatedItem())
			relatedItem.add(i.copy());
		notes = p.getNotes();
	}	
}
