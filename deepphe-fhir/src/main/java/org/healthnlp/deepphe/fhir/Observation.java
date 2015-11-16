package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus_Type;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.jcas.cas.FSArray;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Type;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;

/**
 * Observation object
 * @author tseytlin
 */
public class Observation extends org.hl7.fhir.instance.model.Observation implements Element{
	public Observation(){
		setStatus(ObservationStatus.FINAL);
		//setLanguage(Utils.DEFAULT_LANGUAGE); // we only care about English
	}
	
	public String getDisplay() {
		return getCode().getText();
	}

	public String getIdentifierSimple() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummary() {
		return "Observation:\t"+getDisplay()+" | value: "+getValueSimple();
	}
	
	public Resource getResource() {
		return this;
	}

	public void setReport(Report r) {
		// set patient
		Patient p = r.getPatient();
		if(p != null){
			setSubject(Utils.getResourceReference(p));
			setSubjectTarget(p);
		}
		// set date
		Date d = r.getDate();
		if( d != null){
			setIssued(d);
		}
	}

	/**
	 * initialize 
	 * @param m
	 */
	public void load(Mention m){
		// set name for this observation
		setCode(Utils.getCodeableConcept(m));
		
		// create identifier
		addIdentifier(Utils.createIdentifier(this,m));
				
		
		//TODO: this should be defined in the ontology
		// extract value if available
		// for NOW, lets just hard-coded it
		String text = m.getText();
		Pattern pp = Pattern.compile("(?i)(\\d*\\.\\d+)\\s*([cm]{1,2})");
		Matcher mm = pp.matcher(text);
		if(mm.find()){
			setValue(mm.group(1),mm.group(2));
		}
		// set positive/negative
		for(String st: Arrays.asList("Positive", "Negative","Unknown")){
			if(m.getConcept().getName().endsWith(st)){
				setValue(new StringType(st));
				break;
			}
		}
		

		// add mention text
		addExtension(Utils.createMentionExtension(m.getText(),m.getStartPosition(),m.getEndPosition()));
	}
	
	/**
	 * Initialize diagnosis from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx
	 */
	public void load(IdentifiedAnnotation dm){
		// set some properties
		setCode(Utils.getCodeableConcept(dm));
		addIdentifier(Utils.createIdentifier(this,dm));
		
		// extract value using free text if necessary
		String text = dm.getCoveredText();
		Pattern pp = Pattern.compile("(?i)(\\d*\\.\\d+)\\s*([cm]{1,2})");
		Matcher mm = pp.matcher(text);
		if(mm.find()){
			setValue(mm.group(1),mm.group(2));
		}
		
		// set positive/negative
		if(dm instanceof ReceptorStatus){
			/*for(String st: Arrays.asList("Positive", "Negative","Unknown")){
				if(Utils.getConceptName(dm).contains(st)){
					setValue(new StringType(st));
					break;
				}
			}
			*/
			boolean value = ((ReceptorStatus)dm).getValue();
			setValue(new StringType(value?"Positive":"Negative"));
			
		}
		
		// if cancer size, then use their value
		if(dm instanceof CancerSize){
			FSArray arr = ((CancerSize)dm).getMeasurements();
			if(arr != null){
				for(int i=0;i<arr.size();i++){
					SizeMeasurement sm = (SizeMeasurement) arr.get(i);
					setValue(sm);
					break;
				}
			}
		}
		

		// add mention text
		addExtension(Utils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
	}

	public void setValue(SizeMeasurement num){
		setValue(num.getValue(),num.getUnit());
		String ident = getClass().getSimpleName().toUpperCase()+"_"+getDisplay(); 
		addIdentifier(Utils.createIdentifier((ident+"_"+getValueSimple()).replaceAll("\\W+","_")));
	}
	
	public void setValue(String value, String unit){
		setValue(Double.parseDouble(value),unit);
		String ident = getClass().getSimpleName().toUpperCase()+"_"+getDisplay(); 
		addIdentifier(Utils.createIdentifier((ident+"_"+getValueSimple()).replaceAll("\\W+","_")));
	}
	
	public void setValue(double value, String unit){
		Quantity q = new Quantity();
		q.setValue(new BigDecimal(value,MathContext.DECIMAL32));
		q.setUnit(unit);
		setValue(q);
	}
	public IClass getConceptClass(){
		return Utils.getConceptClass(getCode());
	}
	public String getValueSimple(){
		Type t = getValue();
		if(t != null){
			if( t instanceof StringType)
				return ((StringType)t).getValue();
			else if( t instanceof DecimalType)
				return ""+((DecimalType)t).getValue();
			else if( t instanceof Quantity)
				return ((Quantity)t).getValue().doubleValue()+" "+((Quantity)t).getUnit();
			else
				return t.toString();
		}
		return null;
	}
	
	
	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getIdentifierSimple(),dir);
	}

	public void copy(Resource r) {
		//TODO:
		org.hl7.fhir.instance.model.Observation o = (org.hl7.fhir.instance.model.Observation)r;
		code = o.getCode();
		value = o.getValue();
		interpretation = o.getInterpretation();
		comments = o.getCommentsElement();
		//applies = o.getApplies();
		issued = o.getIssuedElement();
		status = o.getStatusElement();
		//reliability = o.getReliability();
		bodySite = o.getBodySite();
		method = o.getMethod();
		identifier = o.getIdentifier();
		subject = o.getSubject();
		specimen = o.getSpecimen();
		performer = o.getPerformer();
		//for (ResourceReference i : o.getPerformer())
		//	performer.add(i.copy());
		referenceRange = new ArrayList();
		for (ObservationReferenceRangeComponent i :o.getReferenceRange())
			referenceRange.add(i.copy());
		related = new ArrayList();
		for (ObservationRelatedComponent i : o.getRelated())
			related.add(i.copy());
		extension = new ArrayList<Extension>();
		for(Extension e: o.getExtension())
			extension.add(e);
		
	}
	public String toString(){
		return getDisplay();
	}
}
