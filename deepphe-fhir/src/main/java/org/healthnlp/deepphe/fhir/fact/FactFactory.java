package org.healthnlp.deepphe.fhir.fact;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DomainResource;

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
		CodeableConcept c = FHIRUtils.getCodeableConcept(fact.getLabel(),fact.getURI(),FHIRUtils.SCHEMA_OWL);
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
	private static Fact createFact(CodeableConcept cc, Fact fact){
		URI uri = FHIRUtils.getConceptURI(cc);
		String id = FHIRUtils.getResourceIdentifer(cc);
		fact.setURI(""+uri);
		if(uri != null){
			try {
				fact.setName(uri.toURL().getRef());
				fact.setLabel(fact.getName().replaceAll("_"," "));
			} catch (MalformedURLException e) {
				throw new Error(e);
			}
		}
		//TODO: URI SHOULD NOT BE NULL???????
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
	
	public static Fact createFact(Observation ob) {
		ObservationFact fact = (ObservationFact) createFact(ob,new ObservationFact());
		if(ob.getInterpretation() != null)
			fact.setInterpretation(createFact(ob.getInterpretation()));
		//TODO: implement further
		return fact;
	}

	
	/**
	 * create empty fact of a given type
	 * @param type
	 * @return
	 */
	public static Fact createFact(String type){
		Fact fact = new Fact();
		//TODO: implement
		return fact;
	}
	
}
