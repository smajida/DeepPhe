package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.receptor.ReceptorStatusUtil;
import org.apache.ctakes.cancer.receptor.StatusValue;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
//import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
//import org.apache.ctakes.cancer.type.textsem.ReceptorStatus_Type;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.UIMAException;
import org.apache.uima.jcas.cas.FSArray;
import org.hl7.fhir.instance.model.CodeableConcept;
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
	
	public String getDisplayText() {
		return getCode().getText();
	}

	public String getResourceIdentifier() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummaryText() {
		return "Observation:\t"+getDisplayText()+" | value: "+getObservationValue();
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
	 * @param dm
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
//		if(dm instanceof ReceptorStatus){
//			boolean value = ((ReceptorStatus)dm).getValue();
//			String i = value?Utils.INTERPRETATION_POSITIVE:Utils.INTERPRETATION_NEGATIVE;
//			String url = ""+ResourceFactory.getInstance().getOntology().getURI();
//			setInterpretation(Utils.getCodeableConcept(i,url+"#"+i,url));
//			// new StringType(value?"Positive":"Negative"));l;//
//		}
		if ( ReceptorStatusUtil.isReceptorStatus( dm ) ) {
			StatusValue value = null;
			try {
				value = ReceptorStatusUtil.getStatusValue( dm.getCAS().getJCas(), dm );
			} catch ( UIMAException uimaE ) {
				Logger.getLogger( getClass().getName() ).error( "Could not get Receptor Status Value for "
																				+ dm.getCoveredText() );
			}
			if ( value != null ) {
				final String title = value.getTitle();
				String url = ResourceFactory.getInstance().getOntology().getURI().toASCIIString();
				setInterpretation( Utils.getCodeableConcept( title, url + "#" + title, url ) );
				// could also use
				// setInterpretation( Utils.getCodeableConcept( title, value.getUri(), OwlOntologyConceptUtil.BREAST_CANCER_OWL ) );
			}
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
		String ident = getClass().getSimpleName().toUpperCase()+"_"+getDisplayText(); 
		addIdentifier(Utils.createIdentifier((ident+"_"+getObservationValue()).replaceAll("\\W+","_")));
	}
	
	public void setValue(String value, String unit){
		setValue(Double.parseDouble(value),unit);
		String ident = getClass().getSimpleName().toUpperCase()+"_"+getDisplayText(); 
		addIdentifier(Utils.createIdentifier((ident+"_"+getObservationValue()).replaceAll("\\W+","_")));
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
	public String getObservationValue(){
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
		if(getInterpretation() != null){
			return getInterpretation().getText();
		}
		
		return null;
	}
	
	
	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getResourceIdentifier(),dir);
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
		return getDisplayText();
	}
	public URI getConceptURI(){
		return Utils.getConceptURI(getCode());
	}
}
