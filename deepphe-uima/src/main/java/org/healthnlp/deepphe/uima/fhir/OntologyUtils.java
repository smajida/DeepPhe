package org.healthnlp.deepphe.uima.fhir;

import java.net.URI;
import java.util.LinkedList;
import java.util.Queue;

import org.healthnlp.deepphe.fhir.fact.BodySiteFact;
import org.healthnlp.deepphe.fhir.fact.ConditionFact;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactFactory;
import org.healthnlp.deepphe.fhir.fact.ObservationFact;
import org.healthnlp.deepphe.fhir.fact.ProcedureFact;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;

public class OntologyUtils {
	private IOntology ontology;
	
	public OntologyUtils(IOntology ont){
		ontology = ont;
	}
	
	public void addAncestors(Fact fact){
		IClass cls = ontology.getClass(fact.getUri());
		if(cls == null){
			cls = ontology.getClass(fact.getName());
			fact.setUri(cls.getURI().toString());
		}
		if(cls != null){
			Queue<IClass> parents = new LinkedList<IClass>(); 
			parents.add(cls);
			while(!parents.isEmpty()){
				IClass c = parents.remove();
				for(IClass parent: c.getDirectSuperClasses()){
					parents.add(parent);
					// stop, if we have a parent that is defined in upper level ontology
					if(parent.getURI().toString().startsWith(FHIRConstants.SCHEMA_URL) || parent.getURI().toString().startsWith(FHIRConstants.CONTEXT_URL)){
						return;		
					}
					fact.addAncestor(parent.getName());	
				}
			}
		}
	}

	/**
	 * create a fact from codeable concept
	 * @param cc
	 * @return
	 */
	public Fact createFact(CodeableConcept cc){
		Fact fact = null;
		URI uri = FHIRUtils.getConceptURI(cc);
		if(uri != null){
			IClass cls = ontology.getClass(""+uri);
			if(cls != null){
				fact = new Fact();
				if(cls.hasSuperClass(ontology.getClass(FHIRConstants.OBSERVATION)))
					fact = new ObservationFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.CONDITION)))
					fact = new ConditionFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.BODY_SITE)))
					fact = new BodySiteFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.PROCEDURE)))
					fact = new ProcedureFact();
				fact = FactFactory.createFact(cc,fact);
				addAncestors(fact);
				
			}else{
				//TODO:
				System.err.println("ERROR: WTF no class; "+cc.getText()+" "+uri);
			}
		}
		return fact;
	}
	
}
