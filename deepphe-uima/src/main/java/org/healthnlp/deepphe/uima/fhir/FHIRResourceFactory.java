package org.healthnlp.deepphe.uima.fhir;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.fhir.AnatomicalSite;
import org.healthnlp.deepphe.fhir.Diagnosis;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.fhir.Utils;
import org.healthnlp.deepphe.uima.types.BodySite;
import org.healthnlp.deepphe.uima.types.Composition;
import org.healthnlp.deepphe.uima.types.CompositionEvent;
import org.healthnlp.deepphe.uima.types.DiseaseDisorder;
import org.healthnlp.deepphe.uima.types.HumanName;
import org.healthnlp.deepphe.uima.types.NumericModifier;
import org.healthnlp.deepphe.uima.types.Patient;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.hl7.fhir.instance.model.BackboneElement;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.instance.model.Quantity;

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
	
		// create patient
		Patient patient = savePatient(r.getPatient(),jcas);
		comp.setHasSubject(getValue(jcas,patient));
		
		comp.setHasURI(""+r.getConceptURI());
		comp.setHasIdentifier(r.getResourceIdentifier());
		comp.setHasPreferredName(r.getTitle());
		
		
		// init individual components
		int i = 0;
		comp.setHasEvent(new FSArray(jcas,r.getReportElements().size()));
		for(Element e: r.getReportElements()){
			// add to FSArray
			org.healthnlp.deepphe.uima.types.Mention el = saveElement(e,jcas);
			if(el != null){
				// reset offsets
				el.setBegin(el.getBegin()+r.getOffset());
				el.setEnd(el.getEnd()+r.getOffset());
							
				CompositionEvent ev = new CompositionEvent(jcas);
				ev.setHasEvent(getValue(jcas,el));
				comp.setHasEvent(i++,ev);
			}
		}
		comp.addToIndexes();
		return comp;
	}

	public static org.healthnlp.deepphe.uima.types.Mention saveElement(Element e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Mention el = null;
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
		}else if(e instanceof org.healthnlp.deepphe.fhir.Patient){
			el = savePatient((org.healthnlp.deepphe.fhir.Patient)e,jcas);
		}
		
		// do common things
		if(el != null){
			el.setHasURI(""+e.getConceptURI());
			el.setHasIdentifier(e.getResourceIdentifier());
			el.setHasPreferredName(e.getDisplayText());
			
			List<String> mentions = Utils.getMentionExtensions((DomainResource)e);
			el.setHasSpan(new StringArray(jcas,mentions.size()));
			int i=0;
			for(String m: mentions ){
				int [] ms = Utils.getMentionSpan(m);
				el.setBegin(ms[0]);
				el.setEnd(ms[1]);
				el.setHasSpan(i++,m);
				break;
			}
			el.addToIndexes();
		}
		
		return el;
	}
	
	/**
	 * get set of annotations from cas of a given type
	 * @param cas
	 * @param type
	 * @return
	 */
	public static List<Annotation> getAnnotations(JCas cas,int type){
		List<Annotation> a = new ArrayList<Annotation>();
		Iterator<Annotation> it = cas.getAnnotationIndex(type).iterator();
		while(it.hasNext())
			a.add(it.next());
		return a;
	}
	
	
	private static org.healthnlp.deepphe.uima.types.Patient getPatient(JCas jcas){
		// find existing patient
		for(Annotation a: getAnnotations(jcas,Patient.type)){
			return (Patient) a;
		}
		return null;
	}
	
	/**
	 * 
	 * @param jcas
	 * @param val
	 * @return
	 */
	private static FSArray getValue(JCas jcas,FeatureStructure val){
		return (val == null)?null:getValue(jcas,Collections.singleton(val));
		
	}
	
	/**
	 * 
	 * @param jcas
	 * @param val
	 * @return
	 */
	private static FSArray getValue(JCas jcas,Collection<FeatureStructure> vals){
		if(vals == null)
			return null;
		FSArray fs = new FSArray(jcas,vals.size());
		int i=0;
		for(FeatureStructure val: vals){
			fs.set(i++,val);
		}
		fs.addToIndexes();
		return fs;
		
	}
	
	
	private static org.healthnlp.deepphe.uima.types.Patient savePatient(org.healthnlp.deepphe.fhir.Patient e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Patient p = getPatient(jcas);
		// init the patient
		if(p == null){
			p = new org.healthnlp.deepphe.uima.types.Patient(jcas);
		}

		p.setHasURI(""+e.getConceptURI());
		p.setHasIdentifier(e.getResourceIdentifier());
		p.setHasPreferredName(e.getDisplayText());
		
		List<String> mentions = Utils.getMentionExtensions((DomainResource)e);
		p.setHasSpan(new StringArray(jcas,mentions.size()));
		int i=0;
		for(String m: mentions ){
			int [] ms = Utils.getMentionSpan(m);
			p.setBegin(ms[0]);
			p.setEnd(ms[1]);
			p.setHasSpan(i++,m);
		}
		
		
		// set/reset the name
		HumanName hn = new HumanName(jcas);
		hn.setHasFirstName(e.getGivenName());
		hn.setHasLastName(e.getFamilyName());
		hn.addToIndexes();
		
		p.setHasName(getValue(jcas, hn));
		
		// TODO: set gender, birthdate, death date etc ....
		p.addToIndexes();
		return p;
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
		
		// add interpretation
		if(e.getInterpretation() != null)
			ob.setHasInterpretation(Utils.getConceptCode(e.getInterpretation()));
		
		if(e.getValue() != null){
			
			NumericModifier nm = new NumericModifier(jcas);
			if(e.getValue() instanceof Quantity){
				Quantity q = (Quantity) e.getValue();
				nm.setHasValue(q.getValue().doubleValue());
				nm.setHasUnits(q.getUnit());
			}
			nm.addToIndexes();
			ob.setHasNumValue(getValue(jcas,nm));
		}
		//ob.setHasNumValue(e.getObservationValue());
		//TODO: add interpretation
		return ob;
	}
	
	private static DiseaseDisorder saveDiagnosis(Diagnosis e, JCas jcas) {
		DiseaseDisorder dd = new DiseaseDisorder(jcas);
		
		try{
			if(e.getAbatement() != null)
				dd.setHasAbatementBoolean(((BooleanType)e.getAbatement()).getValue());
			//if(e.getOnsetAge() != null)
			//	dd.setHasAgeOnset(""+e.getOnsetAge().getValue());
			//if(e.getSeverity() != null)
			//	dd.setHas(""+e.getSeverity().getText());
			if(e.getCategory() != null)
				dd.setHasConditionCategory(e.getCategory().getText());
			
			// add patient 
			dd.setHasSubject(getValue(jcas,getPatient(jcas)));
			// add body location
			//TODO:
			
		//dd.setHasStage(v);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return dd;
	}

	/**
	 * load typesystem element into corresponding FHIR object
	 * @param el
	 * @return
	 */
	public static Element loadElement(org.healthnlp.deepphe.uima.types.Mention e){
		Element el = null;
		if(e instanceof DiseaseDisorder){
			el = loadDiagnosis((DiseaseDisorder)e);
		}else if(e instanceof org.healthnlp.deepphe.uima.types.Observation){
			el = loadObservation((org.healthnlp.deepphe.uima.types.Observation)e);
		}else if(e instanceof org.healthnlp.deepphe.uima.types.Finding){
			el = loadFinding((org.healthnlp.deepphe.uima.types.Finding) e);
		}else if(e instanceof org.healthnlp.deepphe.uima.types.Procedure){
			el = loadProcedure((org.healthnlp.deepphe.uima.types.Procedure)e);
		}else if(e instanceof org.healthnlp.deepphe.uima.types.Medication){
			el = loadMedication((org.healthnlp.deepphe.uima.types.Medication)e);
		}else if(e instanceof org.healthnlp.deepphe.uima.types.BodySite){
			el = loadBodySite((org.healthnlp.deepphe.uima.types.BodySite)e);
		}
		return el;
	}
	
	
	
	private static AnatomicalSite loadBodySite(BodySite e) {
		AnatomicalSite ob = new AnatomicalSite();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		Utils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}

	private static Medication loadMedication(org.healthnlp.deepphe.uima.types.Medication e) {
		Medication ob = new Medication();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		return ob;
	}

	private static Procedure loadProcedure(org.healthnlp.deepphe.uima.types.Procedure e) {
		Procedure ob = new Procedure();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		Utils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}

	private static Finding loadFinding(org.healthnlp.deepphe.uima.types.Finding e) {
		Finding ob = new Finding();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		Utils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}

	private static Observation loadObservation(org.healthnlp.deepphe.uima.types.Observation e) {
		Observation ob = new Observation();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		
		if(e.getHasNumValue() != null){
			for(int i=0;i<e.getHasNumValue().size();i++){
				NumericModifier nm = e.getHasNumValue(i);
				ob.setValue(nm.getHasValue(),nm.getHasUnits());
			}
		}
		
		if(e.getHasInterpretation() != null){
			ob.setInterpretation(Utils.getCodeableConcept(URI.create(e.getHasInterpretation())));
		}
		
		
		Utils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}
	
	private static void addMention(DomainResource r, org.healthnlp.deepphe.uima.types.Mention e){
		for(int i=0;i<e.getHasSpan().size();i++)
			r.addExtension(Utils.createMentionExtension(e.getHasSpan(i)));
	}

	
	private static Diagnosis loadDiagnosis(DiseaseDisorder e) {
		Diagnosis dx = new Diagnosis();
		dx.setCode(getCodeableConcept(e));
		
		// now lets take a look at the location of this diagnosis
		if(e.getHasBodyLocation() != null){
			for(int i=0;i<e.getHasBodyLocation().size();i++){
				BodySite bs = e.getHasBodyLocation(i);
				dx.addBodySite(getCodeableConcept(bs));
			}
		}
		// now lets get the location relationships
		/*if(e.getHasStage() != null){
			Stage st = new Stage();
			st.
			
		}*/
		
		// add mention text
		addMention(dx,e);

		
		Utils.createIdentifier(dx.addIdentifier(),e.getHasIdentifier());
		return dx;
	}

	
	private static CodeableConcept getCodeableConcept(org.healthnlp.deepphe.uima.types.Mention e){
		return Utils.getCodeableConcept(e.getCoveredText(),e.getHasURI(),Utils.getOntologyURL(e.getHasURI()));
	}
	
	private static org.healthnlp.deepphe.fhir.Patient loadPatient(Patient pt) {
		if(pt == null)
			return null;
		
		// add patient
		org.healthnlp.deepphe.fhir.Patient pp = new org.healthnlp.deepphe.fhir.Patient();
		for(int i=0;i<pt.getHasName().size();i++){
			HumanName hn = pt.getHasName(i);
			org.hl7.fhir.instance.model.HumanName nm = pp.addName();
			nm.addGiven(hn.getHasFirstName());
			nm.addFamily(hn.getHasLastName());
		}
		
		try {
			// add gender
			if(pt.getHasGender() != null)
				pp.setGender(AdministrativeGender.fromCode(pt.getHasGender()));
			
			// add birth date
			if(pt.getHasBirthDate() != null)
				pp.setBirthDate(Utils.getDate(pt.getHasBirthDate()));
			
			// add death date
			if(pt.getHasDeathDate() != null)
				pp.setDeceased(new BooleanType(true));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// add identifier
		Utils.createIdentifier(pp.addIdentifier(),pt.getHasIdentifier());
		
		return pp;
	}
	
	/**
	 * load report from composition annotation
	 * @param r
	 * @return
	 */
	public static Report loadReport(Composition comp){
		Report rr = new Report();
		
		// setup report
		rr.setText(comp.getCoveredText());
		rr.setTitle(comp.getHasTitle());
		if(comp.getHasDateOfComposition() != null)
			rr.setDate(Utils.getDate(comp.getHasDateOfComposition()));
		rr.setType(comp.getHasDocType());
		
		// handle patient (there can be only one :)
		org.healthnlp.deepphe.fhir.Patient p = loadPatient(comp.getHasSubject(0));
		rr.setPatient(p);
		
		// get related items
		for(int i=0;i<comp.getHasEvent().size();i++){
			CompositionEvent ce = comp.getHasEvent(i);
			for(int j=0;j<ce.getHasEvent().size();j++){
				Element el = loadElement(ce.getHasEvent(j));
				rr.addReportElement(el);
			}
		}
		
		
		return rr;
	}
	
	
	

	/**
	 * load a set of reports from JCas
	 * @param jcas
	 * @return
	 */
	public static List<Report>  loadReports(JCas cas){
		List<Report> reports = new ArrayList<Report>();
		for(Annotation a: getAnnotations(cas,Composition.type)){
			Report r = loadReport((Composition) a);
			reports.add(r);
		}
		return reports;
	}
}
