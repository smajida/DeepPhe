package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;





//import org.apache.ctakes.cancer.type.relation.TnmStageTextRelation;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation_Type;
import org.apache.ctakes.typesystem.type.relation.Relation;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Condition.ConditionEvidenceComponent;
import org.hl7.fhir.instance.model.DateType;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

/**
 * This class represents a diagnosis mention in a report
 * @author tseytlin
 */
public class Diagnosis extends Condition implements Element {
	
	public Diagnosis(){
		setCategory(Utils.CONDITION_CATEGORY_DIAGNOSIS);
		setLanguage(Utils.DEFAULT_LANGUAGE); // we only care about English
		
		//setClinicalStatus("active"); // here we only deal with 'confirmed' dx
		setVerificationStatus(ConditionVerificationStatus.CONFIRMED); //?????
	}
	
	/**
	 * Initialize diagnosis from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public void load(DiseaseDisorderMention dm){
		// set some properties
		setCode(Utils.getCodeableConcept(dm));
		//setCertainty(); --> dm.getConfidence()
		//setSeverity(value); -- > dm.getSeverity()???
		
		// create identifier
		Utils.createIdentifier(addIdentifier(),this,dm);
		
		
		// perhaps have annotation from Document time
		TimeMention tm = dm.getStartTime();
		if(tm != null){
			setDateRecorded(Utils.getDate(tm));
		}
			
		// now lets take a look at the location of this diagnosis
		AnatomicalSiteMention as = (AnatomicalSiteMention) Utils.getRelatedItem(dm,dm.getBodyLocation());
		if(as == null){
			as = Utils.getAnatimicLocation(dm);
		}
		if(as != null){
			// TODO: create body-site object as well
			addBodySite(Utils.getCodeableConcept(as));
		}
	
		// now lets get the location relationships
		for(Annotation  a: Utils.getRelatedAnnotationsByType(dm,NeoplasmRelation.class)){
			if(a instanceof TnmClassification){
				Stage stage = new Stage();
				stage.load((TnmClassification) a);
				setStage(stage);
			}
		}
		
		// add mention text
		addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
	}
	
	
	/**
	 * initialize from concept mention
	 * @param m
	 */
	public void load(Mention m){
		setCode(Utils.getCodeableConcept(m));
		
		// create identifier
		Utils.createIdentifier(addIdentifier(),this,m);
		
		// find annatomic location
		Mention al = Utils.getNearestMention(m,m.getSentence().getDocument(),Utils.ANATOMICAL_SITE);
		if(al != null){
			addBodySite(Utils.getCodeableConcept(al));
		}
		
		// find relevant stage
		Mention st = Utils.getNearestMention(m,m.getSentence().getDocument(),Utils.STAGE);
		if(st != null){
			Stage stage = new Stage();
			stage.load(st);
			setStage(stage);
		}
		
		// add mention text
		addExtension(Utils.createMentionExtension(m.getText(),m.getStartPosition(),m.getEndPosition()));
	}
	
	/**
	 * initialize from class in the ontology
	 * @param cls
	 */
	public void initialize(IClass cls){
		setCode(Utils.getCodeableConcept(cls));
		Utils.createIdentifier(addIdentifier(),this,cls);
	}
	
	
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
			stage = ResourceFactory.getStage(c.getStage());
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

	public String getDisplay() {
		return getCode().getText();
	}

	public String getIdentifierSimple() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummary() {
		StringBuffer st = new StringBuffer();
		st.append("Diagnosis:\t"+getDisplay());
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
		Utils.saveFHIR(this,getIdentifierSimple(),dir);
		
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
	public IClass getConceptClass(){
		return Utils.getConceptClass(getCode());
	}
	public String toString(){
		return getDisplay();
	}
}
