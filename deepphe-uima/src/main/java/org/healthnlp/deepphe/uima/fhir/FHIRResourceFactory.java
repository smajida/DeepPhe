package org.healthnlp.deepphe.uima.fhir;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.healthnlp.deepphe.fhir.Diagnosis;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Utils;
import org.healthnlp.deepphe.uima.types.Composition;
import org.healthnlp.deepphe.uima.types.CompositionEvent;
import org.healthnlp.deepphe.uima.types.DiseaseDisorder;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.DomainResource;

public class FHIRResourceFactory {
	/**
	 * save FHIR report in JCas
	 * @param r
	 * @param jcas
	 */
	public static Composition saveReport(Report r, JCas jcas) {
		// first lets create Report annotation
		Composition comp = new Composition(jcas);
		comp.setBegin(r.getOffset());
		comp.setEnd(r.getOffset()+r.getReportText().length());
		comp.setHasDateOfComposition(""+r.getDate());
		comp.setHasDocType(r.getType().getText());
		comp.setHasTitle(r.getTitle());
	
		// init individual components
		int i = 0;
		comp.setHasEvent(new FSArray(jcas,r.getReportElements().size()));
		for(Element e: r.getReportElements()){
			// add to FSArray
			org.healthnlp.deepphe.uima.types.Element el = saveElement(e,jcas);
			if(el != null){
				// reset offsets
				el.setEnd(el.getEnd()+r.getOffset());
				el.setBegin(el.getBegin()+r.getOffset());
				
				CompositionEvent ev = new CompositionEvent(jcas);
				ev.setHasEvent(new FSArray(jcas,1));
				ev.setHasEvent(0,el);
				ev.addToIndexes();
				comp.setHasEvent(i++,ev);
			}
		}
		comp.addToIndexes();
		return comp;
	}

	private static org.healthnlp.deepphe.uima.types.Element saveElement(Element e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Element el = null;
		if(e instanceof Diagnosis){
			el = saveDiagnosis((Diagnosis)e,jcas);
		}else if(e instanceof Observation){
			el = saveObservation((Observation)e,jcas);
		}else if(e instanceof Finding){
			el = saveFinding((Finding) e,jcas);
		}else if(e instanceof Procedure){
			el = saveProcedure((Procedure)e,jcas);
		}else if(e instanceof Medication){
			el = saveMedication((Medication)e,jcas);
		}
		
		// do common things
		if(el != null){
			el.setURI(e.getConceptURI());
			for(String m: Utils.getMentionExtensions((DomainResource)e)){
				int [] ms = Utils.getMentionSpan(m);
				el.setBegin(ms[0]);
				el.setEnd(ms[1]);
			}
			el.addToIndexes();
		}
		
		return el;
	}
	

	private static org.healthnlp.deepphe.uima.types.Medication saveMedication(Medication e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Medication t = new  org.healthnlp.deepphe.uima.types.Medication(jcas);
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Procedure saveProcedure(Procedure e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Procedure t = new  org.healthnlp.deepphe.uima.types.Procedure(jcas);
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Finding saveFinding(Finding e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Finding t = new  org.healthnlp.deepphe.uima.types.Finding(jcas);
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Observation saveObservation(Observation e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Observation ob = new  org.healthnlp.deepphe.uima.types.Observation(jcas);
		ob.setHasNumValue(e.getObservationValue());
		return ob;
	}
	
	private static DiseaseDisorder saveDiagnosis(Diagnosis e, JCas jcas) {
		DiseaseDisorder dd = new DiseaseDisorder(jcas);
		
		try{
			if(e.getAbatement() != null)
				dd.setHasAbatementBoolean(((BooleanType)e.getAbatement()).getValue());
			//if(e.getOnsetAge() != null)
			//	dd.setHasAgeOnset(""+e.getOnsetAge().getValue());
			if(e.getSeverity() != null)
				dd.setHasCertainty(""+e.getSeverity().getText());
			if(e.getCategory() != null)
				dd.setHasConditionCategory(e.getCategory().getText());
			
		//dd.setHasStage(v);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return dd;
	}
	
	
}
