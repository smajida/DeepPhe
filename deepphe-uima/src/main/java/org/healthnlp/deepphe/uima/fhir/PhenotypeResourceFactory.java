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
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.uima.types.Attribute;
import org.healthnlp.deepphe.uima.types.BodySite;
import org.healthnlp.deepphe.uima.types.BodySiteSummary;
import org.healthnlp.deepphe.uima.types.CancerPhenotype;
import org.healthnlp.deepphe.uima.types.CancerStage;
import org.healthnlp.deepphe.uima.types.CancerType;
import org.healthnlp.deepphe.uima.types.CauseOfDeath;
import org.healthnlp.deepphe.uima.types.Composition;
import org.healthnlp.deepphe.uima.types.CompositionEvent;
import org.healthnlp.deepphe.uima.types.DiseaseDisorder;
import org.healthnlp.deepphe.uima.types.DistantMetastasisClassification;
import org.healthnlp.deepphe.uima.types.Exposure;
import org.healthnlp.deepphe.uima.types.GermlineSequenceVariant;
import org.healthnlp.deepphe.uima.types.HistologicType;
import org.healthnlp.deepphe.uima.types.ManifestionOfDisease;
import org.healthnlp.deepphe.uima.types.MedicationStatement;
import org.healthnlp.deepphe.uima.types.Modifier;
import org.healthnlp.deepphe.uima.types.NumericModifier;
import org.healthnlp.deepphe.uima.types.Outcome;
import org.healthnlp.deepphe.uima.types.Patient;
import org.healthnlp.deepphe.uima.types.PrimaryTumorClassification;
import org.healthnlp.deepphe.uima.types.RegionalLymphNodeClassification;
import org.healthnlp.deepphe.uima.types.Treatment;
import org.healthnlp.deepphe.uima.types.Tumor;
import org.healthnlp.deepphe.uima.types.TumorExtent;
import org.healthnlp.deepphe.uima.types.TumorSequenceVariant;
import org.healthnlp.deepphe.uima.types.Tumor_Type;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary.TumorPhenotype;
import org.hl7.fhir.instance.model.BackboneElement;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.instance.model.Quantity;

public class PhenotypeResourceFactory {
	
	/**
	 * get annotation from CAS based on an idenfier
	 * @param JCas cas
	 * @param id
	 * @return
	 */
	private static Annotation getAnnotationByIdentifer(JCas jcas, String id){
		for(Annotation a: getAnnotations(jcas,Annotation.type)){
			String a_id = null;
			if(a instanceof org.healthnlp.deepphe.uima.types.Annotation){
				a_id = ((org.healthnlp.deepphe.uima.types.Annotation) a).getHasIdentifier();
			}else if(a instanceof Attribute){
				a_id = ((Attribute) a).getHasIdentifier();
			}else if(a instanceof Modifier){
				a_id = ((Modifier) a).getHasIdentifier();
			}
			
			if(a_id != null && a_id.equals(id))
				return a;
		}
		return null;
	}
		
	
	/**
	 * save FHIR report in JCas
	 * @param r
	 * @param jcas
	 */
	public static Composition saveReport(Report r, JCas jcas) {
		// first lets create Report annotation
		Annotation a = getAnnotationByIdentifer(jcas,r.getResourceIdentifier());
		Composition comp = a == null?new Composition(jcas):(Composition)a;
		comp.setBegin(r.getOffset());
		comp.setEnd(r.getOffset()+r.getReportText().length());
		comp.setHasDateOfComposition(""+r.getDate());
		comp.setHasDocType(r.getType().getText());
		comp.setHasTitle(r.getTitle());
	
		// create patient
		Patient patient = savePatient(r.getPatient(),jcas);
		comp.setHasPatient(getValue(jcas,patient));
		
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
		
		// save summaries for this report
		i = 0;
		comp.setHasCompositionSummary(new FSArray(jcas,r.getCompositionSummary().size()));		
		for(Summary ss: r.getCompositionSummary()){
			org.healthnlp.deepphe.uima.types.Summary el = saveSummary(ss,jcas);
			if(el != null){
				comp.setHasCompositionSummary(i++,el);
			}
		}
		comp.addToIndexes();
		
		return comp;
	}

	public static org.healthnlp.deepphe.uima.types.Summary saveSummary(Summary summaryFHIR, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Summary summaryAnnotation = null;
		if(summaryFHIR instanceof PatientSummary){
			summaryAnnotation = savePatientSummary((PatientSummary) summaryFHIR,jcas);
		}else if(summaryFHIR instanceof CancerSummary){
			summaryAnnotation = saveCancerSummary((CancerSummary) summaryFHIR,jcas);
		}else if(summaryFHIR instanceof TumorSummary){
			summaryAnnotation = saveTumorSummary((TumorSummary) summaryFHIR,jcas);
		}
		
		return summaryAnnotation;
	}


	/**
	 * create tumor summary
	 * @param summaryFHIR
	 * @param jcas
	 * @return
	 */
	private static org.healthnlp.deepphe.uima.types.Tumor saveTumorSummary(TumorSummary summaryFHIR, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,summaryFHIR.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Tumor summaryAnnotation = (a == null)?new org.healthnlp.deepphe.uima.types.Tumor(jcas):(org.healthnlp.deepphe.uima.types.Tumor)a;
		
		// add phenotype
		org.healthnlp.deepphe.uima.types.TumorPhenotype phenotypeAnnotation = saveTumorPhenotype(summaryFHIR.getPhenotype(),jcas);
		summaryAnnotation.setHasPhenotype(getValue(jcas,phenotypeAnnotation));
		
		// add body site
		if(!summaryFHIR.getBodySite().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasBodySite(new FSArray(jcas,summaryFHIR.getBodySite().size()));
			for(CodeableConcept cc : summaryFHIR.getBodySite()){
				BodySiteSummary ex = new BodySiteSummary(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasBodySite(i++,ex);
			}
		}
		
		// add outcomes
		if(!summaryFHIR.getOutcomes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasOutcome(new FSArray(jcas,summaryFHIR.getOutcomes().size()));
			for(CodeableConcept cc : summaryFHIR.getOutcomes()){
				Outcome ex = new Outcome(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasOutcome(i++,ex);
			}
		}
		// add treatment
		if(!summaryFHIR.getTreatments().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasTreatment(new FSArray(jcas,summaryFHIR.getTreatments().size()));
			for(CodeableConcept cc : summaryFHIR.getTreatments()){
				Treatment ex = new Treatment(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasTreatment(i++,ex);
			}
		}
		
		// add sequnece varients
		if(!summaryFHIR.getTumorSequenceVarients().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasSequenceVariant(new FSArray(jcas,summaryFHIR.getTumorSequenceVarients().size()));
			for(CodeableConcept cc : summaryFHIR.getTumorSequenceVarients()){
				TumorSequenceVariant ex = new TumorSequenceVariant(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasSequenceVariant(i++,ex);
			}
		}
		
		// get tumor type
		if(summaryFHIR.getTumorType() != null)
			summaryAnnotation.setHasTumorType(FHIRUtils.getConceptCode(summaryFHIR.getTumorType()));
		
		
		summaryAnnotation.setHasURI(""+summaryFHIR.getConceptURI());
		summaryAnnotation.setHasIdentifier(summaryFHIR.getResourceIdentifier());
		summaryAnnotation.addToIndexes();
		
		return summaryAnnotation;
	}

	/**
	 * create phenotype 
	 * @param phenotype
	 * @param jcas
	 * @return
	 */

	private static org.healthnlp.deepphe.uima.types.TumorPhenotype saveTumorPhenotype(TumorPhenotype phenotype, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,phenotype.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.TumorPhenotype summaryAnnotation = (a == null)? new org.healthnlp.deepphe.uima.types.TumorPhenotype(jcas):(org.healthnlp.deepphe.uima.types.TumorPhenotype)a;
		
		// add histologic types
		if(!phenotype.getHistologicTypes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasHistologicType(new FSArray(jcas,phenotype.getHistologicTypes().size()));
			for(CodeableConcept cc : phenotype.getHistologicTypes()){
				HistologicType ex = new HistologicType(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasHistologicType(i++,ex);
			}
		}
		
		// tumor extent
		if(!phenotype.getTumorExtent().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasTumorExtent(new FSArray(jcas,phenotype.getTumorExtent().size()));
			for(CodeableConcept cc : phenotype.getTumorExtent()){
				TumorExtent ex = new TumorExtent(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasTumorExtent(i++,ex);
			}
		}
		
		// add manifistation
		if(!phenotype.getManifistations().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasManifestation(new FSArray(jcas,phenotype.getManifistations().size()));
			for(CodeableConcept cc : phenotype.getManifistations()){
				ManifestionOfDisease ex = new ManifestionOfDisease(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasManifestation(i++,ex);
			}
		}
	
		summaryAnnotation.setHasURI(""+phenotype.getConceptURI());
		summaryAnnotation.setHasIdentifier(phenotype.getResourceIdentifier());
		summaryAnnotation.addToIndexes();
		
		return summaryAnnotation;
	}


	/**
	 * save cancer summary to typesystem
	 * @param summaryFHIR
	 * @param jcas
	 * @return
	 */
	private static org.healthnlp.deepphe.uima.types.Cancer saveCancerSummary(CancerSummary summaryFHIR, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,summaryFHIR.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Cancer summaryAnnotation = (a == null)?new org.healthnlp.deepphe.uima.types.Cancer(jcas):(org.healthnlp.deepphe.uima.types.Cancer)a;
		
		// add phenotype
		if(!summaryFHIR.getPhenotypes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasPhenotype(new FSArray(jcas,summaryFHIR.getPhenotypes().size()));
			for(CancerSummary.CancerPhenotype phenotype : summaryFHIR.getPhenotypes()){
				CancerPhenotype phenotypeAnnotation = saveCancerPhenotype(phenotype,jcas);
				summaryAnnotation.setHasPhenotype(i++,phenotypeAnnotation);
			}
		}
		// add tumor
		if(!summaryFHIR.getTumors().isEmpty()){
			int i = 0;
			summaryAnnotation.setRealizes(new FSArray(jcas,summaryFHIR.getTumors().size()));
			for(TumorSummary tumorSummary: summaryFHIR.getTumors()){
				Tumor tumorAnnotation = saveTumorSummary(tumorSummary, jcas);
				summaryAnnotation.setRealizes(i++,tumorAnnotation);
			}
		}
		
		// add body site
		if(!summaryFHIR.getBodySite().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasBodySite(new FSArray(jcas,summaryFHIR.getBodySite().size()));
			for(CodeableConcept cc : summaryFHIR.getBodySite()){
				BodySiteSummary ex = new BodySiteSummary(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasBodySite(i++,ex);
			}
		}
		
		// add outcomes
		if(!summaryFHIR.getOutcomes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasOutcome(new FSArray(jcas,summaryFHIR.getOutcomes().size()));
			for(CodeableConcept cc : summaryFHIR.getOutcomes()){
				Outcome ex = new Outcome(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasOutcome(i++,ex);
			}
		}
		
		// add treatment
		if(!summaryFHIR.getTreatments().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasTreatment(new FSArray(jcas,summaryFHIR.getTreatments().size()));
			for(CodeableConcept cc : summaryFHIR.getTreatments()){
				Treatment ex = new Treatment(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasTreatment(i++,ex);
			}
		}
		
		summaryAnnotation.setHasURI(""+summaryFHIR.getConceptURI());
		summaryAnnotation.setHasIdentifier(summaryFHIR.getResourceIdentifier());
		summaryAnnotation.addToIndexes();
		
		return summaryAnnotation;
	}

	/**
	 * save cancer phenotype to typesystem
	 * @param phenotype
	 * @param jcas
	 * @return
	 */

	private static CancerPhenotype saveCancerPhenotype(org.healthnlp.deepphe.fhir.summary.CancerSummary.CancerPhenotype phenotype, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,phenotype.getResourceIdentifier());
		CancerPhenotype summaryAnnotation = (a == null)?new CancerPhenotype(jcas):(CancerPhenotype)a;
		
		// add stage
		if(phenotype.getCancerStage() != null){
			CancerStage cstage = new CancerStage(jcas);
			addCodeableConcept(cstage,phenotype.getCancerStage());
			summaryAnnotation.setHasCancerStage(getValue(jcas,cstage));
		}
		// add type
		if(phenotype.getCancerType() != null){
			CancerType ctype = new CancerType(jcas);
			addCodeableConcept(ctype,phenotype.getCancerType());
			summaryAnnotation.setHasCancerType(getValue(jcas,ctype));
		}
		
		// add tumor extends
		if(phenotype.getTumorExtent() != null){
			TumorExtent te = new TumorExtent(jcas);
			addCodeableConcept(te,phenotype.getTumorExtent());
			summaryAnnotation.setHasCancerType(getValue(jcas,te));
		}
		
		// add primary tumor stage
		if(phenotype.getPrimaryTumorClassification() != null){
			PrimaryTumorClassification pstage = new PrimaryTumorClassification(jcas);
			addCodeableConcept(pstage,phenotype.getPrimaryTumorClassification());
			summaryAnnotation.setHasCancerType(getValue(jcas,pstage));
		}
		
		// add regional lymph node
		if(phenotype.getRegionalLymphNodeClassification() != null){
			RegionalLymphNodeClassification nstage = new RegionalLymphNodeClassification(jcas);
			addCodeableConcept(nstage,phenotype.getRegionalLymphNodeClassification());
			summaryAnnotation.setHasCancerType(getValue(jcas,nstage));
		}
		
		// add primary tumor stage
		if(phenotype.getDistantMetastasisClassification() != null){
			DistantMetastasisClassification mstage = new DistantMetastasisClassification(jcas);
			addCodeableConcept(mstage,phenotype.getDistantMetastasisClassification());
			summaryAnnotation.setHasCancerType(getValue(jcas,mstage));
		}
		
		// add manifistation
		if(!phenotype.getManifistations().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasManifestation(new FSArray(jcas,phenotype.getManifistations().size()));
			for(CodeableConcept cc : phenotype.getManifistations()){
				ManifestionOfDisease ex = new ManifestionOfDisease(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasManifestation(i++,ex);
			}
		}
		
		// regurlar fluf
		summaryAnnotation.setHasURI(""+phenotype.getConceptURI());
		summaryAnnotation.setHasIdentifier(phenotype.getResourceIdentifier());
		summaryAnnotation.addToIndexes();
		return summaryAnnotation;
	}


	private static org.healthnlp.deepphe.uima.types.PatientSummary savePatientSummary(PatientSummary summaryFHIR, JCas jcas) {
		org.healthnlp.deepphe.uima.types.PatientSummary summaryAnnotation = new org.healthnlp.deepphe.uima.types.PatientSummary(jcas);
		
		// add exposures
		if(!summaryFHIR.getExposure().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasExposure(new FSArray(jcas,summaryFHIR.getExposure().size()));
			for(CodeableConcept cc : summaryFHIR.getExposure()){
				Exposure ex = new Exposure(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasExposure(i++,ex);
			}
		}
		
		// add sequnece varients
		if(!summaryFHIR.getGermlineSequenceVarient().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasSequenceVariant(new FSArray(jcas,summaryFHIR.getGermlineSequenceVarient().size()));
			for(CodeableConcept cc : summaryFHIR.getGermlineSequenceVarient()){
				GermlineSequenceVariant ex = new GermlineSequenceVariant(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasSequenceVariant(i++,ex);
			}
		}
		
		// add outcomes
		if(!summaryFHIR.getOutcome().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasOutcome(new FSArray(jcas,summaryFHIR.getOutcome().size()));
			for(CodeableConcept cc : summaryFHIR.getOutcome()){
				CauseOfDeath ex = new CauseOfDeath(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasOutcome(i++,ex);
			}
		}
		
		summaryAnnotation.setHasURI(""+summaryFHIR.getConceptURI());
		summaryAnnotation.setHasIdentifier(summaryFHIR.getResourceIdentifier());
		summaryAnnotation.addToIndexes();
		
		return summaryAnnotation;
	}


	private static void addCodeableConcept( org.healthnlp.deepphe.uima.types.Annotation a, CodeableConcept cc) {
		a.setHasURI(""+FHIRUtils.getConceptURI(cc));
		a.setHasPreferredName(cc.getText());
		a.addToIndexes();
	}
	
	private static void addCodeableConcept( org.healthnlp.deepphe.uima.types.Attribute a, CodeableConcept cc) {
		a.setHasURI(""+FHIRUtils.getConceptURI(cc));
		a.setHasPreferredName(cc.getText());
	}


	public static org.healthnlp.deepphe.uima.types.Mention saveElement(Element e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Mention el = null;
		if(e instanceof Disease){
			el = saveDiagnosis((Disease)e,jcas);
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
			
			List<String> mentions = FHIRUtils.getMentionExtensions((DomainResource)e);
			el.setHasSpan(new StringArray(jcas,mentions.size()));
			int i=0;
			for(String m: mentions ){
				int [] ms = FHIRUtils.getMentionSpan(m);
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
		org.healthnlp.deepphe.uima.types.Patient p  = (org.healthnlp.deepphe.uima.types.Patient) getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		//org.healthnlp.deepphe.uima.types.Patient p = getPatient(jcas);
		// init the patient
		if(p == null){
			p = new org.healthnlp.deepphe.uima.types.Patient(jcas);
		}

		p.setHasURI(""+e.getConceptURI());
		p.setHasIdentifier(e.getResourceIdentifier());
		p.setHasPreferredName(e.getDisplayText());
		
		// add existing mentions
		List<String> mentions = new ArrayList<String>();
	/*	if(p.getHasSpan() != null){
			for(int i=0;i<p.getHasSpan().size();i++){
				mentions.add(p.getHasSpan(i));
			}
		}*/
		mentions.addAll(FHIRUtils.getMentionExtensions((DomainResource)e));
		p.setHasSpan(new StringArray(jcas,mentions.size()));
		int i=0;
		for(String m: mentions ){
			int [] ms = FHIRUtils.getMentionSpan(m);
			p.setBegin(ms[0]);
			p.setEnd(ms[1]);
			p.setHasSpan(i++,m);
		}
		
		
		// set/reset the name
		/*HumanName hn = new HumanName(jcas);
		hn.setHasFirstName(e.getGivenName());
		hn.setHasLastName(e.getFamilyName());
		hn.addToIndexes(); */
		
		p.setHasName(e.getDisplayText());
		if(e.getBirthDate() != null)
			p.setHasBirthDate(e.getBirthDate().toString());
		if(e.getGender() != null)
			p.setHasGender(e.getGender().getDisplay());
		if(e.getDeceased() != null && e.getDeceased() instanceof DateTimeType){
			try {
				p.setHasDeathDate(e.getDeceasedDateTimeType().getValue().toString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		p.addToIndexes();
		return p;
	}

	private static org.healthnlp.deepphe.uima.types.MedicationStatement saveMedication(Medication e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.MedicationStatement t = (a == null)? new  org.healthnlp.deepphe.uima.types.MedicationStatement(jcas):(org.healthnlp.deepphe.uima.types.MedicationStatement)a;
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Procedure saveProcedure(Procedure e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Procedure t = (a == null)?new  org.healthnlp.deepphe.uima.types.Procedure(jcas):(org.healthnlp.deepphe.uima.types.Procedure)a;
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Finding saveFinding(Finding e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Finding t = (a == null)? new  org.healthnlp.deepphe.uima.types.Finding(jcas):(org.healthnlp.deepphe.uima.types.Finding) a;
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Observation saveObservation(Observation e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Observation ob = (a == null)?new  org.healthnlp.deepphe.uima.types.Observation(jcas):(org.healthnlp.deepphe.uima.types.Observation) a;
		
		// add interpretation
		if(e.getInterpretation() != null)
			ob.setHasInterpretation(FHIRUtils.getConceptCode(e.getInterpretation()));
		
		if(e.getValue() != null){
			
			//NumericModifier nm = new NumericModifier(jcas);
			org.healthnlp.deepphe.uima.types.Quantity nm = new org.healthnlp.deepphe.uima.types.Quantity(jcas);
			if(e.getValue() instanceof Quantity){
				Quantity q = (Quantity) e.getValue();
				nm.setHasQuantityValue((float)(q.getValue().doubleValue()));
				nm.setHasUnit(q.getUnit());
			}
			nm.addToIndexes();
			ob.setHasNumValue(getValue(jcas,nm));
		}
		//ob.setHasNumValue(e.getObservationValue());
		//TODO: add interpretation
		return ob;
	}
	
	private static DiseaseDisorder saveDiagnosis(Disease e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		DiseaseDisorder dd = (a == null)? new DiseaseDisorder(jcas): (DiseaseDisorder) a;
		
		try{
			if(e.getAbatement() != null)
				dd.setHasAbatementBoolean(((BooleanType)e.getAbatement()).getValue());
			//if(e.getOnsetAge() != null)
			//	dd.setHasAgeOnset(""+e.getOnsetAge().getValue());
			//if(e.getSeverity() != null)
			//	dd.setHas(""+e.getSeverity().getText());
			if(e.getCategory() != null){
				dd.setHasConditionCategory(e.getCategory().getText());
			}
			
			// add patient 
			dd.setHasPatient(getValue(jcas,getPatient(jcas)));
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
		}else if(e instanceof org.healthnlp.deepphe.uima.types.MedicationStatement){
			el = loadMedication((org.healthnlp.deepphe.uima.types.MedicationStatement)e);
		}else if(e instanceof org.healthnlp.deepphe.uima.types.BodySite){
			el = loadBodySite((org.healthnlp.deepphe.uima.types.BodySite)e);
		}
		return el;
	}
	
	
	
	private static AnatomicalSite loadBodySite(BodySite e) {
		AnatomicalSite ob = new AnatomicalSite();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		FHIRUtils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}

	private static Medication loadMedication(org.healthnlp.deepphe.uima.types.MedicationStatement e) {
		Medication ob = new Medication();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		return ob;
	}

	private static Procedure loadProcedure(org.healthnlp.deepphe.uima.types.Procedure e) {
		Procedure ob = new Procedure();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		FHIRUtils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}

	private static Finding loadFinding(org.healthnlp.deepphe.uima.types.Finding e) {
		Finding ob = new Finding();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		FHIRUtils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}

	private static Observation loadObservation(org.healthnlp.deepphe.uima.types.Observation e) {
		Observation ob = new Observation();
		ob.setCode(getCodeableConcept(e));
		addMention(ob,e);
		
		if(e.getHasNumValue() != null){
			for(int i=0;i<e.getHasNumValue().size();i++){
				//TODO: numeric modifer 
				//NumericModifier nm = e.getHasNumValue(i);
				//ob.setValue(nm.getHasValue(),nm.getHasUnits());
				org.healthnlp.deepphe.uima.types.Quantity nm = e.getHasNumValue(i);
				ob.setValue(nm.getHasQuantityValue(),nm.getHasUnit());
			}
		}
		
		if(e.getHasInterpretation() != null){
			ob.setInterpretation(FHIRUtils.getCodeableConcept(URI.create(e.getHasInterpretation())));
		}
		
		
		FHIRUtils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}
	
	private static void addMention(DomainResource r, org.healthnlp.deepphe.uima.types.Mention e){
		for(int i=0;i<e.getHasSpan().size();i++)
			r.addExtension(FHIRUtils.createMentionExtension(e.getHasSpan(i)));
	}

	
	private static Disease loadDiagnosis(DiseaseDisorder e) {
		Disease dx = new Disease();
		dx.setCode(getCodeableConcept(e));
		
		// now lets take a look at the location of this disease
		if(e.getHasBodySite() != null){
			for(int i=0;i<e.getHasBodySite().size();i++){
				BodySite bs = e.getHasBodySite(i);
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

		
		FHIRUtils.createIdentifier(dx.addIdentifier(),e.getHasIdentifier());
		return dx;
	}

	
	private static CodeableConcept getCodeableConcept(org.healthnlp.deepphe.uima.types.Mention e){
		return FHIRUtils.getCodeableConcept(e.getCoveredText(),e.getHasURI(),FHIRUtils.getOntologyURL(e.getHasURI()));
	}
	
	private static org.healthnlp.deepphe.fhir.Patient loadPatient(Patient pt) {
		if(pt == null)
			return null;
		
		// add patient
		org.healthnlp.deepphe.fhir.Patient pp = new org.healthnlp.deepphe.fhir.Patient();
		/*for(int i=0;i<pt.getHasName().size();i++){
			HumanName hn = pt.getHasName(i);
			org.hl7.fhir.instance.model.HumanName nm = pp.addName();
			nm.addGiven(hn.getHasFirstName());
			nm.addFamily(hn.getHasLastName());
		}*/
		pp.setPatientName(pt.getHasName());
		
		try {
			// add gender
			if(pt.getHasGender() != null)
				pp.setGender(AdministrativeGender.fromCode(pt.getHasGender()));
			
			// add birth date
			if(pt.getHasBirthDate() != null)
				pp.setBirthDate(FHIRUtils.getDate(pt.getHasBirthDate()));
			
			// add death date
			if(pt.getHasDeathDate() != null)
				pp.setDeceased(new BooleanType(true));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// add identifier
		FHIRUtils.createIdentifier(pp.addIdentifier(),pt.getHasIdentifier());
		
		// add mentions
		addMention(pp,pt);
		
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
			rr.setDate(FHIRUtils.getDate(comp.getHasDateOfComposition()));
		rr.setType(comp.getHasDocType());
		rr.setOffset(comp.getBegin());
		
		// handle patient (there can be only one :)
		org.healthnlp.deepphe.fhir.Patient p = loadPatient(comp.getHasPatient(0));
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
	 * load a single patient mention froma a cas (there can only be one)
	 * @param cas
	 * @return
	 */
	public static org.healthnlp.deepphe.fhir.Patient loadPatient(JCas cas){
		Patient p = null;
		for(Annotation a: getAnnotations(cas,Patient.type)){
			p = (Patient) a;
			break;
		}
		return p != null?loadPatient(p):null;
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
