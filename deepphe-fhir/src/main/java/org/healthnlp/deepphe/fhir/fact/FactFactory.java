package org.healthnlp.deepphe.fhir.fact;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import org.healthnlp.deepphe.fhir.AnatomicalSite;
import org.healthnlp.deepphe.fhir.Condition;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BodySite;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Quantity;


/**
 * create different facts
 * @author tseytlin
 *
 */
public class FactFactory {
	
	/**
	 * return codeable concept representation of a given Fact
	 * @param fact
	 * @return
	 */
	public static CodeableConcept createCodeableConcept(Fact fact){
		CodeableConcept c = FHIRUtils.getCodeableConcept(fact.getLabel(),fact.getUri(),FHIRUtils.SCHEMA_OWL);
		if(fact.getIdentifier() != null){
			Coding coding = c.addCoding();
			coding.setCode(fact.getIdentifier());
			coding.setDisplay(fact.getLabel());
			coding.setSystem(FHIRUtils.SCHEMA_REFERENCE);
		}
		return c;
	}
	
	/**
	 * create a generic fact based on a codeable concept
	 */
	public static Fact createFact(CodeableConcept cc){
		return createFact(cc,new Fact());
	}
	
	/**
	 * create a generic fact based on a codeable concept
	 */
	public static Fact createFact(CodeableConcept cc, Fact fact){
		URI uri = FHIRUtils.getConceptURI(cc);
		String id = FHIRUtils.getResourceIdentifer(cc);
		fact.setUri(""+uri);
		fact.setLabel(cc.getText());
		
		if(uri != null){
			try {
				fact.setName(uri.toURL().getRef());
			} catch (MalformedURLException e) {
				throw new Error(e);
			}
		}else{
			fact.setName(fact.getLabel());
		}
		if(id != null)
			fact.setIdentifier(id);
		
		return fact;
		
	}
	
	
	public static TextMention createTextMention(String mention){
		int [] se = FHIRUtils.getMentionSpan(mention);
		String text = FHIRUtils.getMentionText(mention);
		TextMention tm = new TextMention();
		tm.setText(text);
		tm.setStart(se[0]);
		tm.setEnd(se[1]);
		return tm;
	}

	public static Fact createFact(Element resource) {
		if(resource instanceof Observation)
			return createFact((Observation)resource);
		if(resource instanceof AnatomicalSite)
			return createFact((AnatomicalSite)resource);
		if(resource instanceof Condition)
			return createFact((Condition)resource);
		if(resource instanceof Procedure)
			return createFact((Procedure)resource);
		if(resource instanceof Quantity)
			return createFact((Quantity)resource);
		
		return createFact(resource,new Fact());
	}

	private static Fact createFact(Element resource,Fact fact) {
		fact = createFact(resource.getCode(),fact);
		fact.setIdentifier(resource.getResourceIdentifier());
		//fact.setLabel(label);
		for(String m: FHIRUtils.getMentionExtensions((DomainResource)resource.getResource())){
			TextMention mention = createTextMention(m);
			fact.addProvenanceText(mention);
		}
		
		return fact;
	}
	
	
	/**
	 * create Fact from quantity
	 * @param q
	 * @return
	 */
	public static ValueFact createFact(Quantity q){
		URI uri = FHIRConstants.QUANTITY_URI;
		String name;
		try {
			name = uri.toURL().getRef();
		} catch (MalformedURLException e) {
			throw new Error(e);
		}
		
		double value = q.getValue().doubleValue();
		String units = q.getUnit();
		
		ValueFact fact = new ValueFact();
		fact.setName(name);
		fact.setLabel(name);
		fact.setUri(""+uri);
		fact.setType(name);
		fact.setIdentifier((name.toUpperCase()+"_"+value+" "+units).trim());
		
		fact.setValue(value);
		fact.setUnit(units);
		
		return fact;
	}
	
	/**
	 * create observation fact
	 * @param ob
	 * @return
	 */
	public static ObservationFact createFact(Observation ob) {
		ObservationFact fact = (ObservationFact) createFact(ob,new ObservationFact());
		if(FHIRUtils.hasConceptURI(ob.getInterpretation())){
			fact.setInterpretation(createFact(ob.getInterpretation()));
		}
		if(ob.getValue() != null && ob.getValue() instanceof Quantity){
			try {
				fact.setValue(createFact(ob.getValueQuantity()));
			} catch (Exception e) {
				throw new Error(e);
			}
		}
		if(FHIRUtils.hasConceptURI(ob.getMethod())){
			fact.setMethod(createFact(ob.getMethod()));
		}
		return fact;
	}

	/**
	 * create observation fact
	 * @param ob
	 * @return
	 */
	public static ConditionFact createFact(Condition condition) {
		ConditionFact fact = (ConditionFact) createFact(condition,new ConditionFact());
		for(CodeableConcept cc: condition.getBodySite()){
			fact.getBodySite().add(createFact(cc));
		}
		return fact;
	}

	
	/**
	 * create observation fact
	 * @param ob
	 * @return
	 */
	public static ProcedureFact createFact(Procedure condition) {
		ProcedureFact fact = (ProcedureFact) createFact(condition,new ProcedureFact());
		for(CodeableConcept cc: condition.getBodySite()){
			fact.getBodySite().add(createFact(cc));
		}
		//TODO: handle method
		return fact;
	}
	/**
	 * create observation fact
	 * @param ob
	 * @return
	 */
	public static BodySiteFact createFact(AnatomicalSite location) {
		BodySiteFact fact = (BodySiteFact) createFact(location,new BodySiteFact());
		return fact;
	}
	
	
	/**
	 * create empty fact of a given type
	 * @param type
	 * @return
	 */
	public static Fact createFact(String type){
		Fact fact = null;
		if(FHIRConstants.BODY_SITE.equals(type)){
			fact = new BodySiteFact();
		}else if(FHIRConstants.OBSERVATION.equals(type)){
			fact = new ObservationFact();
		}else if(FHIRConstants.CONDITION.equals(type)){
			fact = new ConditionFact();
		}else if(FHIRConstants.PROCEDURE.equals(type)){
			fact = new ProcedureFact();
		}else if(FHIRConstants.QUANTITY.equals(type)){
			fact = new ValueFact();
		}else{
			fact = new Fact();
		}
		return fact;
	}
	
	/**
	 * create empty fact of a given type
	 * @param type
	 * @return
	 */
	public static Fact createFact(String type, String uri){
		return createFact(FHIRUtils.getCodeableConcept(URI.create(uri)),createFact(type));
	}
	
	public static String createIdentifier(Fact fact){
		return fact.getType()+"_"+fact.getName().replaceAll("\\W+","_")+"_"+Math.abs(fact.getProvenanceMentions().hashCode());
	}
	
}
