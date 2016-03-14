package org.healthnlp.deepphe.uima.fhir;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.fhir.AnatomicalSite;
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactFactory;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary.TumorPhenotype;
import org.healthnlp.deepphe.uima.types.Attribute;
import org.healthnlp.deepphe.uima.types.BodySite;
import org.healthnlp.deepphe.uima.types.Cancer;
import org.healthnlp.deepphe.uima.types.CancerPhenotype;
import org.healthnlp.deepphe.uima.types.CancerStage;
import org.healthnlp.deepphe.uima.types.CancerType;
import org.healthnlp.deepphe.uima.types.Composition;
import org.healthnlp.deepphe.uima.types.DiseaseDisorder;
import org.healthnlp.deepphe.uima.types.DocumentOntology;
import org.healthnlp.deepphe.uima.types.Exposure;
import org.healthnlp.deepphe.uima.types.Gender;
import org.healthnlp.deepphe.uima.types.GermlineSequenceVariant;
import org.healthnlp.deepphe.uima.types.HistologicType;
import org.healthnlp.deepphe.uima.types.HumanName;
import org.healthnlp.deepphe.uima.types.MMetastasisstages;
import org.healthnlp.deepphe.uima.types.ManifestationOfDisease;
import org.healthnlp.deepphe.uima.types.Modifier;
import org.healthnlp.deepphe.uima.types.NNodestages;
import org.healthnlp.deepphe.uima.types.OrdinalInterpretation;
import org.healthnlp.deepphe.uima.types.Outcome;
import org.healthnlp.deepphe.uima.types.Patient;
import org.healthnlp.deepphe.uima.types.SummaryModel;
import org.healthnlp.deepphe.uima.types.TTumorstages;
import org.healthnlp.deepphe.uima.types.Treatment;
import org.healthnlp.deepphe.uima.types.Tumor;
import org.healthnlp.deepphe.uima.types.TumorExtent;
import org.healthnlp.deepphe.uima.types.TumorSequenceVariant;
import org.healthnlp.deepphe.uima.types.TumorType;
import org.healthnlp.deepphe.uima.types.TumorType_Type;
import org.healthnlp.deepphe.uima.types.Unit;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Resource;

public class PhenotypeResourceFactory {
	
	/**
	 * get annotation from CAS based on an idenfier
	 * @param JCas cas
	 * @param id
	 * @return
	 */
	private static Annotation getAnnotationByIdentifer(JCas jcas, String id){
		if(id == null)
			return null;
		
		for(Annotation a: getAnnotations(jcas,Annotation.type)){
			String a_id = getIdentifier(a);
			if(a_id != null && a_id.equals(id))
				return a;
		}
		return null;
	}
		
	/**
	 * get preferred name
	 * @param a
	 * @return
	 */
	private static String getPrefferedName(Annotation a){
		if(a instanceof org.healthnlp.deepphe.uima.types.Annotation){
			return ((org.healthnlp.deepphe.uima.types.Annotation) a).getHasPreferredName();
		}else if(a instanceof Attribute){
			return ((Attribute) a).getHasPreferredName();
		}else if(a instanceof Modifier){
			return ((Modifier) a).getHasPreferredName();
		}else if(a instanceof org.healthnlp.deepphe.uima.types.Summary){
			return ((org.healthnlp.deepphe.uima.types.Summary) a).getHasPreferredName();
		}
		return null;
	}
	
	/**
	 * get preferred name
	 * @param a
	 * @return
	 */
	private static String getIdentifier(Annotation a){
		if(a instanceof org.healthnlp.deepphe.uima.types.Annotation){
			return ((org.healthnlp.deepphe.uima.types.Annotation) a).getHasIdentifier();
		}else if(a instanceof Attribute){
			return ((Attribute) a).getHasIdentifier();
		}else if(a instanceof Modifier){
			return ((Modifier) a).getHasIdentifier();
		}else if(a instanceof org.healthnlp.deepphe.uima.types.Summary){
			return ((org.healthnlp.deepphe.uima.types.Summary) a).getHasIdentifier();
		}
		return null;
	}
	
	
	private static String getResourceURI(Annotation a){
		if(a instanceof org.healthnlp.deepphe.uima.types.Annotation){
			return ((org.healthnlp.deepphe.uima.types.Annotation) a).getHasURI();
		}else if(a instanceof Attribute){
			return ((Attribute) a).getHasURI();
		}else if(a instanceof Modifier){
			return ((Modifier) a).getHasURI();
		}else if(a instanceof org.healthnlp.deepphe.uima.types.Summary){
			return ((org.healthnlp.deepphe.uima.types.Summary) a).getHasURI();
		}
		return null;
	}
	
	
	private static Annotation getAnnotationByName(JCas jcas,int type, String name){
		for(Annotation a: getAnnotations(jcas, type)){
			String prefName = getPrefferedName(a);
			if(prefName != null && prefName.equals(name))
				return a;
		}
		return null;
	}
	
	private static Annotation getAnnotationByURI(JCas jcas,int type, String uri){
		for(Annotation a: getAnnotations(jcas, type)){
			String prefName = getResourceURI(a);
			if(prefName != null && prefName.equals(uri))
				return a;
		}
		return null;
	}
	
	private static org.healthnlp.deepphe.uima.types.Quantity getQuantity(JCas jcas,double value, String unit){
		for(Annotation a: getAnnotations(jcas, org.healthnlp.deepphe.uima.types.Quantity.type)){
			org.healthnlp.deepphe.uima.types.Quantity q = (org.healthnlp.deepphe.uima.types.Quantity) a;
			if(value == q.getHasQuantityValue()){
				// if unit doesn't match
				if(unit != null && q.getHasUnit() != null && q.getHasUnit().size() == 1 && !unit.equals(q.getHasUnit(0).getHasPreferredName())){
					return null;
				}
				return q;
			}
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
		comp.setHasTitle(r.getTitle());
		
		// add doc type
		if(r.getType() != null){
			DocumentOntology dont = (DocumentOntology) getAnnotationByName(jcas,DocumentOntology.type, r.getType().getText());
			if(dont == null)		
				dont = new DocumentOntology(jcas);
			addCodeableConcept(dont, r.getType());
			comp.setHasDocType(getValue(jcas,dont));
		}	
		
		// create patient
		Patient patient = savePatient(r.getPatient(),jcas);
		comp.setHasPatient(getValue(jcas,patient));
		
		comp.setHasURI(""+r.getConceptURI());
		comp.setHasIdentifier(r.getResourceIdentifier());
		comp.setHasPreferredName(r.getTitle());
		
		
		// init individual components
		List<FeatureStructure> events = new ArrayList<FeatureStructure>();
		for(Element e: r.getReportElements()){
			// add to FSArray
			org.healthnlp.deepphe.uima.types.Annotation el = saveElement(e,jcas);
			if(el != null){
				// reset offsets
				el.setBegin(el.getBegin()+r.getOffset());
				el.setEnd(el.getEnd()+r.getOffset());
				
				/*// add to event
				CompositionEvent ev = new CompositionEvent(jcas);
				ev.setBegin(el.getBegin()+r.getOffset());
				ev.setEnd(el.getEnd()+r.getOffset());
				ev.setHasDetail(getValue(jcas,el));
				ev.addToIndexes();*/
				
				events.add(el);
			}
		}
		if(!events.isEmpty())
			comp.setHasEvent(getValues(jcas, events));
		
		// save summaries for this report
		/*List<FeatureStructure> summaries = new ArrayList<FeatureStructure>();
		for(Summary ss: r.getCompositionSummaries()){
			org.healthnlp.deepphe.uima.types.Summary el = saveSummary(ss,jcas);
			if(el != null){
				summaries.add(el);
			}
		}
		if(!summaries.isEmpty())
			comp.setHasCompositionSummary(getValues(jcas, summaries));	*/
		for(CancerSummary cs : r.getCancerSummaries()){
			comp.setHasCompositionSummaryCancer(getValue(jcas,saveCancerSummary(cs,jcas)));
		}
		for(TumorSummary ts : r.getTumorSummaries()){
			comp.setHasCompositionSummaryTumor(getValue(jcas,saveTumorSummary(ts,jcas)));
		}
		
		
		comp.addToIndexes();
		
		return comp;
	}

	/*public static org.healthnlp.deepphe.uima.types.Summary saveSummary(Summary summaryFHIR, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Summary summaryAnnotation = null;
		if(summaryFHIR instanceof PatientSummary){
			summaryAnnotation = savePatientSummary((PatientSummary) summaryFHIR,jcas);
		}else if(summaryFHIR instanceof CancerSummary){
			summaryAnnotation = saveCancerSummary((CancerSummary) summaryFHIR,jcas);
		}else if(summaryFHIR instanceof TumorSummary){
			summaryAnnotation = saveTumorSummary((TumorSummary) summaryFHIR,jcas);
		}
		
		return summaryAnnotation;
	}*/


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
			for(Fact cc : summaryFHIR.getBodySite()){
				BodySite ex = (BodySite) getAnnotationByIdentifer(jcas,cc.getIdentifier());
				if(ex == null){
					ex = new BodySite(jcas);
					addCodeableConcept(ex, cc.getCodeableConcept());
				}
				summaryAnnotation.setHasBodySite(i++,ex);
			}
		}
		
		// add outcomes
		if(!summaryFHIR.getOutcomes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasOutcome(new FSArray(jcas,summaryFHIR.getOutcomes().size()));
			for(Fact cc : summaryFHIR.getOutcomes()){
				Outcome ex = new Outcome(jcas);
				addCodeableConcept(ex, cc.getCodeableConcept());
				summaryAnnotation.setHasOutcome(i++,ex);
			}
		}
		// add treatment
		if(!summaryFHIR.getTreatments().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasTreatment(new FSArray(jcas,summaryFHIR.getTreatments().size()));
			for(Fact cc : summaryFHIR.getTreatments()){
				Treatment ex = new Treatment(jcas);
				addCodeableConcept(ex, cc.getCodeableConcept());
				summaryAnnotation.setHasTreatment(i++,ex);
			}
		}
		
		// add sequnece varients
		if(!summaryFHIR.getTumorSequenceVarients().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasSequenceVariant(new FSArray(jcas,summaryFHIR.getTumorSequenceVarients().size()));
			for(Fact cc : summaryFHIR.getTumorSequenceVarients()){
				TumorSequenceVariant ex = new TumorSequenceVariant(jcas);
				addCodeableConcept(ex, cc.getCodeableConcept());
				summaryAnnotation.setHasSequenceVariant(i++,ex);
			}
		}
		
		// get tumor type
		if(summaryFHIR.getTumorType() != null){
			TumorType tt = new TumorType(jcas);
			addCodeableConcept(tt,summaryFHIR.getTumorType().getCodeableConcept());
			summaryAnnotation.setHasTumorType(getValue(jcas,tt));
		}
		
		summaryAnnotation.setHasURI(""+summaryFHIR.getConceptURI());
		summaryAnnotation.setHasIdentifier(summaryFHIR.getResourceIdentifier());
		summaryAnnotation.setHasAnnotationType(summaryFHIR.getAnnotationType());
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
			for(Fact cc : phenotype.getHistologicTypes()){
				HistologicType ex = (HistologicType) getAnnotationByURI(jcas,HistologicType.type,cc.getURI());
				if(ex == null){		
					ex =new HistologicType(jcas);
					addCodeableConcept(ex, cc.getCodeableConcept());
				}
				summaryAnnotation.setHasHistologicType(i++,ex);
			}
		}
		
		// tumor extent
		if(!phenotype.getTumorExtent().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasTumorExtent(new FSArray(jcas,phenotype.getTumorExtent().size()));
			for(Fact cc : phenotype.getTumorExtent()){
				TumorExtent ex = (TumorExtent) getAnnotationByURI(jcas,TumorExtent.type,cc.getURI());
				if(ex == null){		
					ex = new TumorExtent(jcas);
					addCodeableConcept(ex, cc.getCodeableConcept());
				}
				summaryAnnotation.setHasTumorExtent(i++,ex);
			}
		}
		
		// add manifistation
		if(!phenotype.getManifestations().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasManifestation(new FSArray(jcas,phenotype.getManifestations().size()));
			for(Fact cc : phenotype.getManifestations()){
				ManifestationOfDisease ex = (ManifestationOfDisease) getAnnotationByURI(jcas,ManifestationOfDisease.type,cc.getURI());
				if(ex == null){
					ex = new ManifestationOfDisease(jcas);
					addCodeableConcept(ex, cc.getCodeableConcept());
				}
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
		summaryAnnotation.setHasPhenotype(getValue(jcas,saveCancerPhenotype(summaryFHIR.getPhenotype(),jcas)));
		
		/*if(!summaryFHIR.getPhenotypes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasPhenotype(new FSArray(jcas,summaryFHIR.getPhenotypes().size()));
			for(CancerSummary.CancerPhenotype phenotype : summaryFHIR.getPhenotypes()){
				CancerPhenotype phenotypeAnnotation = saveCancerPhenotype(phenotype,jcas);
				summaryAnnotation.setHasPhenotype(i++,phenotypeAnnotation);
			}
		}*/
		
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
			for(Fact cc : summaryFHIR.getBodySite()){
				BodySite ex = (BodySite) getAnnotationByIdentifer(jcas,cc.getIdentifier());
				if(ex == null){
					ex = new BodySite(jcas);
					addCodeableConcept(ex, cc.getCodeableConcept());
				}
				summaryAnnotation.setHasBodySite(i++,ex);
			}
		}
		
		// add outcomes
		if(!summaryFHIR.getOutcomes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasOutcome(new FSArray(jcas,summaryFHIR.getOutcomes().size()));
			for(Fact cc : summaryFHIR.getOutcomes()){
				Outcome ex = new Outcome(jcas);
				addCodeableConcept(ex, cc.getCodeableConcept());
				summaryAnnotation.setHasOutcome(i++,ex);
			}
		}
		
		// add treatment
		if(!summaryFHIR.getTreatments().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasTreatment(new FSArray(jcas,summaryFHIR.getTreatments().size()));
			for(Fact cc : summaryFHIR.getTreatments()){
				Treatment ex = (Treatment) getAnnotationByURI(jcas,Treatment.type,cc.getURI());
				if(ex == null){
					ex = new Treatment(jcas);
					addCodeableConcept(ex, cc.getCodeableConcept());
				}
				summaryAnnotation.setHasTreatment(i++,ex);
			}
		}
		
		summaryAnnotation.setHasURI(""+summaryFHIR.getConceptURI());
		summaryAnnotation.setHasIdentifier(summaryFHIR.getResourceIdentifier());
		summaryAnnotation.setHasAnnotationType(summaryFHIR.getAnnotationType());
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
			CancerStage cstage = (CancerStage) getAnnotationByURI(jcas,CancerStage.type,phenotype.getCancerStage().getURI());
			if(cstage == null){	
				cstage = 	new CancerStage(jcas);
				addCodeableConcept(cstage,phenotype.getCancerStage().getCodeableConcept());
			}
			summaryAnnotation.setHasCancerStage(getValue(jcas,cstage));
		}
		// add type
		if(phenotype.getCancerType() != null){
			CancerType ctype = (CancerType) getAnnotationByURI(jcas,CancerType.type,phenotype.getCancerType().getURI());
			if(ctype == null){
				ctype = new CancerType(jcas);
				addCodeableConcept(ctype,phenotype.getCancerType().getCodeableConcept());
			}
			summaryAnnotation.setHasCancerType(getValue(jcas,ctype));
		}
		
		// add tumor extends
		/*
		if(phenotype.getTumorExtent() != null){
			TumorExtent te = new TumorExtent(jcas);
			addCodeableConcept(te,phenotype.getTumorExtent());
			summaryAnnotation.setHasTumorExtent(getValue(jcas,te));
		}
		*/
		
		// add primary tumor stage
		if(phenotype.getPrimaryTumorClassification() != null){
			TTumorstages pstage = (TTumorstages) getAnnotationByURI(jcas,TTumorstages.type,phenotype.getPrimaryTumorClassification().getURI());
			if(pstage == null){
				pstage = new TTumorstages(jcas);
				addCodeableConcept(pstage,phenotype.getPrimaryTumorClassification().getCodeableConcept());
			}
			summaryAnnotation.setHasTClassification(getValue(jcas,pstage));
		}
		
		// add regional lymph node
		if(phenotype.getRegionalLymphNodeClassification() != null){
			NNodestages nstage = (NNodestages) getAnnotationByURI(jcas,NNodestages.type,phenotype.getRegionalLymphNodeClassification().getURI());
			if(nstage == null){
				nstage = new NNodestages(jcas);
				addCodeableConcept(nstage,phenotype.getRegionalLymphNodeClassification().getCodeableConcept());
			}
			summaryAnnotation.setHasNClassification(getValue(jcas,nstage));
		}
		
		// add primary tumor stage
		if(phenotype.getDistantMetastasisClassification() != null){
			MMetastasisstages mstage = (MMetastasisstages) getAnnotationByURI(jcas,MMetastasisstages.type,phenotype.getDistantMetastasisClassification().getURI());
			if(mstage == null){
				mstage = new MMetastasisstages(jcas);
				addCodeableConcept(mstage,phenotype.getDistantMetastasisClassification().getCodeableConcept());
			}
			summaryAnnotation.setHasMClassification(getValue(jcas,mstage));
		}
		
		// add manifistation
		if(!phenotype.getManifestations().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasManifestation(new FSArray(jcas,phenotype.getManifestations().size()));
			for(Fact cc : phenotype.getManifestations()){
				ManifestationOfDisease ex = new ManifestationOfDisease(jcas);
				addCodeableConcept(ex, cc.getCodeableConcept());
				summaryAnnotation.setHasManifestation(i++,ex);
			}
		}
		
		// regurlar fluf
		summaryAnnotation.setHasURI(""+phenotype.getConceptURI());
		summaryAnnotation.setHasIdentifier(phenotype.getResourceIdentifier());
		summaryAnnotation.addToIndexes();
		return summaryAnnotation;
	}

	/*
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
		if(!summaryFHIR.getGermlineSequenceVariant().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasSequenceVariant(new FSArray(jcas,summaryFHIR.getGermlineSequenceVariant().size()));
			for(CodeableConcept cc : summaryFHIR.getGermlineSequenceVariant()){
				GermlineSequenceVariant ex = new GermlineSequenceVariant(jcas);
				addCodeableConcept(ex, cc);
				summaryAnnotation.setHasSequenceVariant(i++,ex);
			}
		}
		
		// add outcomes
		if(!summaryFHIR.getOutcomes().isEmpty()){
			int i = 0;
			summaryAnnotation.setHasOutcome(new FSArray(jcas,summaryFHIR.getOutcomes().size()));
			for(CodeableConcept cc : summaryFHIR.getOutcomes()){
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
	*/

	private static void addCodeableConcept( org.healthnlp.deepphe.uima.types.Annotation a, CodeableConcept cc) {
		a.setHasURI(""+FHIRUtils.getConceptURI(cc));
		a.setHasPreferredName(cc.getText());
		a.addToIndexes();
	}
	
	private static void addCodeableConcept( org.healthnlp.deepphe.uima.types.Attribute a, CodeableConcept cc) {
		a.setHasURI(""+FHIRUtils.getConceptURI(cc));
		a.setHasPreferredName(cc.getText());
		a.addToIndexes();
	}
	
	private static void addCodeableConcept( org.healthnlp.deepphe.uima.types.SummaryModel a, CodeableConcept cc) {
		a.setHasURI(""+FHIRUtils.getConceptURI(cc));
		a.setHasPreferredName(cc.getText());
		a.addToIndexes();
	}
	
	private static void addCodeableConcept( org.healthnlp.deepphe.uima.types.Modifier a, CodeableConcept cc) {
		a.setHasURI(""+FHIRUtils.getConceptURI(cc));
		a.setHasPreferredName(cc.getText());
		a.addToIndexes();
	}


	public static org.healthnlp.deepphe.uima.types.Annotation saveElement(Element e, JCas jcas) {
		org.healthnlp.deepphe.uima.types.Annotation el = null;
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
		}else if(e instanceof org.healthnlp.deepphe.fhir.AnatomicalSite){
			el = saveAnatomicalSite((AnatomicalSite)e,jcas);
		}
		
		// do common things
		if(el != null){
			el.setHasURI(""+e.getConceptURI());
			el.setHasIdentifier(e.getResourceIdentifier());
			el.setHasPreferredName(e.getDisplayText());
			el.setHasAnnotationType(e.getAnnotationType());
			
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
		return (val == null)?null:getValues(jcas,Collections.singleton(val));
		
	}
	
	/**
	 * 
	 * @param jcas
	 * @param val
	 * @return
	 */
	private static FSArray getValues(JCas jcas,Collection<FeatureStructure> vals){
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
		if(e == null)
			return null;
		
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
		HumanName hn = (HumanName) getAnnotationByName(jcas,HumanName.type,e.getDisplayText()); 
		if(hn == null)		
			hn = new HumanName(jcas);
		hn.setHasPreferredName(e.getDisplayText());
		hn.setHasFullName(e.getDisplayText());
		hn.addToIndexes(); 
		p.setHasName(getValue(jcas, hn));
		
		if(e.getBirthDate() != null)
			p.setHasBirthDate(e.getBirthDate().toString());
		if(e.getGender() != null){
			Gender g = new Gender(jcas);
			g.setHasPreferredName(e.getGender().getDisplay());
			g.addToIndexes();
			p.setHasGender(getValue(jcas,g));
		}
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

	private static BodySite saveAnatomicalSite(AnatomicalSite e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		BodySite t = (a == null)? new  BodySite(jcas):(BodySite)a;
		return t;
	}
	
	private static org.healthnlp.deepphe.uima.types.Procedure saveProcedure(Procedure e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Procedure t = (a == null)?new  org.healthnlp.deepphe.uima.types.Procedure(jcas):(org.healthnlp.deepphe.uima.types.Procedure)a;
		
		// add body location
		List<FeatureStructure> sites = getBodySites(jcas,e.getBodySite());
		if(!sites.isEmpty())
			t.setHasBodySite(getValues(jcas,sites));
		
		return t;
	}

	
	
	private static org.healthnlp.deepphe.uima.types.Finding saveFinding(CodeableConcept cc, JCas jcas) {
		String id = FHIRUtils.getResourceIdentifer(cc);
		
		// fetch a previously registered Finding
		Annotation a = getAnnotationByIdentifer(jcas,id);
		if(a != null){
			return (org.healthnlp.deepphe.uima.types.Finding) a;
		}
		// define new finding and save it
		Finding finding = new Finding();
		finding.setCode(cc);
		if(id != null)
			FHIRUtils.createIdentifier(finding.addIdentifier(),id);
		
		return (org.healthnlp.deepphe.uima.types.Finding) saveElement(finding, jcas);
	}

	
	
	private static org.healthnlp.deepphe.uima.types.Finding saveFinding(Finding e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Finding t = (a == null)? new  org.healthnlp.deepphe.uima.types.Finding(jcas):(org.healthnlp.deepphe.uima.types.Finding) a;
		
		// add body location
		List<FeatureStructure> sites = getBodySites(jcas,e.getBodySite());
		if(!sites.isEmpty())
			t.setHasBodySite(getValues(jcas,sites));
		
		return t;
	}

	private static org.healthnlp.deepphe.uima.types.Observation saveObservation(Observation e, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,e.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.Observation ob = (a == null)?new  org.healthnlp.deepphe.uima.types.Observation(jcas):(org.healthnlp.deepphe.uima.types.Observation) a;
		
		// add interpretation
		if(e.getInterpretation() != null){
			OrdinalInterpretation oi = (OrdinalInterpretation) getAnnotationByURI(jcas,OrdinalInterpretation.type,""+FHIRUtils.getConceptURI(e.getInterpretation())); 
			if(oi == null)		
				oi = new OrdinalInterpretation(jcas);
			addCodeableConcept(oi, e.getInterpretation());
			ob.setHasInterpretation(getValue(jcas,oi));
		}
		if(e.getValue() != null){
			
			//NumericModifier nm = new NumericModifier(jcas);
		
			if(e.getValue() instanceof Quantity){
				Quantity q = (Quantity) e.getValue();
				
				org.healthnlp.deepphe.uima.types.Quantity nm = getQuantity(jcas, q.getValue().doubleValue(),q.getUnit()); 
				if(nm == null)
					nm = new org.healthnlp.deepphe.uima.types.Quantity(jcas);
	
				nm.setHasQuantityValue((float)(q.getValue().doubleValue()));
				Unit u = (Unit) getAnnotationByName(jcas, Unit.type,q.getUnit());
				if(u == null)
					u = new Unit(jcas);
				u.setHasPreferredName(q.getUnit());
				u.addToIndexes();
				nm.setHasUnit(getValue(jcas, u));
				
				nm.addToIndexes();
				ob.setHasNumValue(getValue(jcas,nm));
			}
			
		}
		//ob.setHasNumValue(e.getObservationValue());
		
		// add body location
		List<FeatureStructure> sites = getBodySites(jcas,Collections.singleton(e.getBodySite()));
		if(!sites.isEmpty())
			ob.setHasBodySite(getValues(jcas,sites));
		
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
			List<FeatureStructure> sites = getBodySites(jcas, e.getBodySite());
			if(!sites.isEmpty())
				dd.setHasBodySite(getValues(jcas,sites));
			
			
			// save stage
			Stage stage = e.getStage();
			if(stage != null){
				org.healthnlp.deepphe.uima.types.Stage st =  new  org.healthnlp.deepphe.uima.types.Stage(jcas);
				/*URI uri = FHIRUtils.getConceptURI(stage.getSummary());
				if(uri != null){
					CancerStage cs = new CancerStage(jcas);
					addCodeableConcept(cs,stage.getSummary());
					st.setHasStageSummary(getValue(jcas,cs));
				}*/
				if(stage.getSummary() != null){
					st.setHasStageSummary(getValue(jcas,saveFinding(stage.getSummary(), jcas)));
				}
				
				List<FeatureStructure> tnm = new ArrayList<FeatureStructure>();
				for(Resource r : stage.getAssessmentTarget()){
					tnm.add(saveElement((Finding) r, jcas));
				}
				if(!tnm.isEmpty())
					st.setHasStageAssessment(getValues(jcas, tnm));
				st.addToIndexes();
				
				// add stage
				dd.setHasStage(getValue(jcas, st));
			}
					
		//dd.setHasStage(v);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return dd;
	}

	
	/**
	 * get a list of BodySites from 
	 * @param jcas
	 * @param bodySites
	 * @return
	 */
	private static List<FeatureStructure> getBodySites(JCas jcas, Collection<CodeableConcept> bodySites){
		List<FeatureStructure> sites = new ArrayList<FeatureStructure>();
		for(CodeableConcept bs: bodySites){
			String id = FHIRUtils.getResourceIdentifer(bs);
			BodySite bodySite = (BodySite) getAnnotationByIdentifer(jcas,id);
			if(bodySite == null && id != null){
				bodySite =  new  BodySite(jcas);
				bodySite.setHasURI(""+FHIRUtils.getConceptURI(bs));
				bodySite.setHasIdentifier(id);
				bodySite.setHasPreferredName(bs.getText());
				bodySite.addToIndexes();
			}
			if(bodySite != null)
				sites.add(bodySite);
			
		}
		return sites;
	}
	
	
	
	
	/**
	 * load typesystem element into corresponding FHIR object
	 * @param el
	 * @return
	 */
	public static Element loadElement(org.healthnlp.deepphe.uima.types.Annotation e){
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
	
	private static Finding loadFinding(org.healthnlp.deepphe.uima.types.TNMValue e) {
		Finding ob = new Finding();
		ob.setCode(getCodeableConcept(e));
		ob.addExtension(FHIRUtils.createMentionExtension(e.getCoveredText(),e.getBegin(),e.getEnd()));
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
				String unit = "";
				if(nm.getHasUnit() != null && nm.getHasUnit().size() > 0)
					unit = nm.getHasUnit(0).getHasPreferredName();
				ob.setValue(nm.getHasQuantityValue(),unit);
			}
		}
		
		if(e.getHasInterpretation() != null && e.getHasInterpretation().size() > 0){
			ob.setInterpretation(getCodeableConcept(e.getHasInterpretation(0)));
		}
		
		
		FHIRUtils.createIdentifier(ob.addIdentifier(),e.getHasIdentifier());
		return ob;
	}
	
	private static void addMention(DomainResource r, org.healthnlp.deepphe.uima.types.Annotation e){
		if(e != null && e.getHasSpan() != null){
			for(int i=0;i<e.getHasSpan().size();i++)
				r.addExtension(FHIRUtils.createMentionExtension(e.getHasSpan(i)));
		}
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
		if(e.getHasStage() != null){
			// get stage
			org.healthnlp.deepphe.uima.types.Stage st = e.getHasStage(0);
			
			// init 
			Stage stage = new Stage();
			if(st.getHasStageSummary() != null && st.getHasStageSummary().size() > 0){
				stage.setSummary(getCodeableConcept(st.getHasStageSummary(0)));
			}
			for(int i=0;i<getSize(st.getHasStageAssessment());i++){
				stage.addAssessment(loadFinding(st.getHasStageAssessment(i)));
			}
			dx.setStage(stage);
		}
		
		// add mention text
		addMention(dx,e);

		
		FHIRUtils.createIdentifier(dx.addIdentifier(),e.getHasIdentifier());
		return dx;
	}

	
	private static CodeableConcept getCodeableConcept(org.healthnlp.deepphe.uima.types.Annotation e){
		CodeableConcept cc =  FHIRUtils.getCodeableConcept(e.getHasPreferredName(),e.getHasURI(),FHIRUtils.SCHEMA_OWL);
		Coding c = cc.addCoding();
		c.setSystem(FHIRUtils.SCHEMA_REFERENCE);
		c.setCode(e.getHasIdentifier());
		c.setDisplay(e.getHasPreferredName());
		return cc;
	}
	
	private static CodeableConcept getCodeableConcept(org.healthnlp.deepphe.uima.types.SummaryModel e){
		CodeableConcept cc =  FHIRUtils.getCodeableConcept(e.getHasPreferredName(),e.getHasURI(),FHIRUtils.SCHEMA_OWL);
		Coding c = cc.addCoding();
		c.setSystem(FHIRUtils.SCHEMA_REFERENCE);
		c.setCode(e.getHasIdentifier());
		c.setDisplay(e.getHasPreferredName());
		return cc;
	}
	
	private static CodeableConcept getCodeableConcept(org.healthnlp.deepphe.uima.types.Modifier e){
		CodeableConcept cc =  FHIRUtils.getCodeableConcept(e.getHasPreferredName(),e.getHasURI(),FHIRUtils.SCHEMA_OWL);
		Coding c = cc.addCoding();
		c.setSystem(FHIRUtils.SCHEMA_REFERENCE);
		c.setCode(e.getHasIdentifier());
		c.setDisplay(e.getHasPreferredName());
		return cc;
	}
	
	private static CodeableConcept getCodeableConcept(org.healthnlp.deepphe.uima.types.Attribute e){
		CodeableConcept cc =  FHIRUtils.getCodeableConcept(e.getHasPreferredName(),e.getHasURI(),FHIRUtils.SCHEMA_OWL);
		Coding c = cc.addCoding();
		c.setSystem(FHIRUtils.SCHEMA_REFERENCE);
		c.setCode(e.getHasIdentifier());
		c.setDisplay(e.getHasPreferredName());
		return cc;
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
		for(int i=0;i<getSize(pt.getHasName());i++){
			HumanName hn = pt.getHasName(i);
			pp.setPatientName(hn.getHasFullName());
		}
		//pp.setPatientName(pt.getHasName());
		
		try {
			// add gender
			for(int i=0;i<getSize(pt.getHasGender()); i++){
				pp.setGender(AdministrativeGender.fromCode(pt.getHasGender(i).getHasPreferredName()));
			}
			
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
		for(int i=0;i<getSize(comp.getHasDocType());i++){
			rr.setType(getCodeableConcept(comp.getHasDocType(i)));
		}
		rr.setOffset(comp.getBegin());
		
		// handle patient (there can be only one :)
		if(comp.getHasPatient() != null){
			org.healthnlp.deepphe.fhir.Patient p = loadPatient(comp.getHasPatient(0));
			rr.setPatient(p);
		}
		
		// get related items
		for(int i=0;i<getSize(comp.getHasEvent());i++){
			Element el = loadElement(comp.getHasEvent(i));
			if(el != null)
				rr.addReportElement(el);
				
		}
		
		// load summaries
		/*for(int i=0;i<getSize(comp.getHasCompositionSummary());i++){
			Summary summary = loadSummary( comp.getHasCompositionSummary(i));
			rr.addCompositionSummary(summary);
		}*/
		for(int i=0;i<getSize(comp.getHasCompositionSummaryCancer());i++){
			rr.addCompositionSummary(loadCancerSummary(comp.getHasCompositionSummaryCancer(i)));
		}
		for(int i=0;i<getSize(comp.getHasCompositionSummaryTumor());i++){
			rr.addCompositionSummary(loadTumorSummary(comp.getHasCompositionSummaryTumor(i)));
		}
		
		
		return rr;
	}
	
	/*public static Summary loadSummary(org.healthnlp.deepphe.uima.types.Summary summaryAnnotation) {
		Summary summary = null;
		if(summaryAnnotation instanceof org.healthnlp.deepphe.uima.types.PatientSummary){
			summary = loadPatientSummary((org.healthnlp.deepphe.uima.types.PatientSummary) summaryAnnotation);
		}else if(summaryAnnotation instanceof Cancer){
			summary = loadCancerSummary((Cancer) summaryAnnotation);
		}else if(summaryAnnotation instanceof Tumor){
			summary = loadTumorSummary((Tumor) summaryAnnotation);
		}
		
		return summary;
	}*/
	
	
/*	private static PatientSummary loadPatientSummary(org.healthnlp.deepphe.uima.types.PatientSummary summaryAnnotation) {
		PatientSummary patientSummary = new PatientSummary();
		//TODO: don't care about patient summary for now, will do later
		FHIRUtils.createIdentifier(patientSummary.addIdentifier(),summaryAnnotation.getHasIdentifier());
		return patientSummary;
	}*/

	private static Fact getFact(Annotation a){
		CodeableConcept cc = null;
		
		if(a instanceof org.healthnlp.deepphe.uima.types.Annotation){
			cc = getCodeableConcept((org.healthnlp.deepphe.uima.types.Annotation)a);
		}else if(a instanceof Modifier){
			cc = getCodeableConcept((Modifier)a);
		}else if(a instanceof Attribute){
			cc = getCodeableConcept((Attribute)a);
		}else if(a instanceof SummaryModel){
			cc = getCodeableConcept((SummaryModel)a);
		}
		
		if(cc != null){
			return FactFactory.createFact(cc);
		}
		
		return null;
	}
	
	private static int getSize(FSArray arr){
		return arr == null?0:arr.size();
	}

	private static CancerSummary loadCancerSummary(Cancer summaryAnnotation) {
		CancerSummary cancerSummary = new CancerSummary();
		
		// add values
		for(int i=0;i<getSize(summaryAnnotation.getHasBodySite());i++){
			cancerSummary.addBodySite(getFact(summaryAnnotation.getHasBodySite(i)));	
		}
		for(int i=0;i<getSize(summaryAnnotation.getHasOutcome());i++){
			cancerSummary.addOutcome(getFact(summaryAnnotation.getHasOutcome(i)));	
		}
		for(int i=0;i<getSize(summaryAnnotation.getHasTreatment());i++){
			cancerSummary.addTreatment(getFact(summaryAnnotation.getHasTreatment(i)));	
		}
		
		// add phenotypes
		for(int i=0;i<getSize(summaryAnnotation.getHasPhenotype());i++){
			CancerPhenotype pheneAnnotation = summaryAnnotation.getHasPhenotype(i);
			CancerSummary.CancerPhenotype phenotype = new CancerSummary.CancerPhenotype();
			
			for(int j=0;j<getSize(pheneAnnotation.getHasCancerStage());j++){
				phenotype.setCancerStage(getFact(pheneAnnotation.getHasCancerStage(j)));
			}
			for(int j=0;j<getSize(pheneAnnotation.getHasCancerType());j++){
				phenotype.setCancerType(getFact(pheneAnnotation.getHasCancerType(j)));
			}
			/*for(int j=0;j<getSize(pheneAnnotation.getHasTumorExtent());j++){
				phenotype.setTumorExtent(getCodeableConcept(pheneAnnotation.getHasTumorExtent(j)));
			}*/
			for(int j=0;j<getSize(pheneAnnotation.getHasTClassification());j++){
				phenotype.setPrimaryTumorClassification(getFact(pheneAnnotation.getHasTClassification(j)));
			}
			for(int j=0;j<getSize(pheneAnnotation.getHasMClassification());j++){
				phenotype.setDistantMetastasisClassification(getFact(pheneAnnotation.getHasMClassification(j)));
			}
			for(int j=0;j<getSize(pheneAnnotation.getHasNClassification());j++){
				phenotype.setRegionalLymphNodeClassification(getFact(pheneAnnotation.getHasNClassification(j)));
			}
			for(int j=0;j<getSize(pheneAnnotation.getHasManifestation());j++){
				phenotype.addManifestation(getFact(pheneAnnotation.getHasManifestation(j)));
			}
			cancerSummary.setPhenotype(phenotype);
		}
		
		// add tumors
		for(int i=0;i<getSize(summaryAnnotation.getRealizes());i++){
			cancerSummary.addTumor(loadTumorSummary(summaryAnnotation.getRealizes(i)));
		}
		
		FHIRUtils.createIdentifier(cancerSummary.addIdentifier(),summaryAnnotation.getHasIdentifier());
		return cancerSummary;
	}


	private static TumorSummary loadTumorSummary(Tumor summaryAnnotation) {
		TumorSummary tumorSummary = new TumorSummary();
		// add values
		for(int i=0;i<getSize(summaryAnnotation.getHasBodySite());i++){
			tumorSummary.addBodySite(getFact(summaryAnnotation.getHasBodySite(i)));	
		}
		for(int i=0;i<getSize(summaryAnnotation.getHasOutcome());i++){
			tumorSummary.addOutcome(getFact(summaryAnnotation.getHasOutcome(i)));	
		}
		for(int i=0;i<getSize(summaryAnnotation.getHasTreatment());i++){
			tumorSummary.addTreatment(getFact(summaryAnnotation.getHasTreatment(i)));	
		}
		for(int i=0;i<getSize(summaryAnnotation.getHasSequenceVariant());i++){
			tumorSummary.addTreatment(getFact(summaryAnnotation.getHasSequenceVariant(i)));	
		}
		for(int i=0;i<getSize(summaryAnnotation.getHasTumorType());i++){
			tumorSummary.setTumorType(getFact(summaryAnnotation.getHasTumorType(i)));
		}
		// add phenotypes
		for(int i=0;i<getSize(summaryAnnotation.getHasPhenotype());i++){
			org.healthnlp.deepphe.uima.types.TumorPhenotype pheneAnnotation = summaryAnnotation.getHasPhenotype(i);
			TumorSummary.TumorPhenotype phenotype = new TumorSummary.TumorPhenotype();
			for(int j=0;j<getSize(pheneAnnotation.getHasHistologicType());j++){
				phenotype.addHistologicType(getFact(pheneAnnotation.getHasHistologicType(j)));
			}
			for(int j=0;j<getSize(pheneAnnotation.getHasTumorExtent());j++){
				phenotype.addTumorExtent(getFact(pheneAnnotation.getHasTumorExtent(j)));
			}
			for(int j=0;j<getSize(pheneAnnotation.getHasManifestation());j++){
				phenotype.addManifestation(getFact(pheneAnnotation.getHasManifestation(j)));
			}
			tumorSummary.setPhenotype(phenotype);
			
		}
		
		FHIRUtils.createIdentifier(tumorSummary.addIdentifier(),summaryAnnotation.getHasIdentifier());
		return tumorSummary;
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


	public static CancerSummary loadMedicalRecordCancerSummary(JCas jcas) {
		//TODO: load actual medical record annotation
		for(Annotation a: getAnnotations(jcas,Cancer.type)){
			Cancer annotationSummary = (Cancer) a;
			if(FHIRConstants.ANNOTATION_TYPE_RECORD.equals(annotationSummary.getHasAnnotationType())){
				return loadCancerSummary(annotationSummary);
			}
		}
		return null;
	}

	public static PatientSummary loadMedicalRecordPatientSummary(JCas jcas){
		return null;
	}

	/**
	 * create medical record
	 * @param record
	 * @param jcas
	 */
	public static org.healthnlp.deepphe.uima.types.MedicalRecord saveMedicalRecord(MedicalRecord record, JCas jcas) {
		Annotation a = getAnnotationByIdentifer(jcas,record.getResourceIdentifier());
		org.healthnlp.deepphe.uima.types.MedicalRecord summaryAnnotation = (a == null)?new org.healthnlp.deepphe.uima.types.MedicalRecord(jcas):(org.healthnlp.deepphe.uima.types.MedicalRecord)a;
		
		summaryAnnotation.setHasURI(""+record.getConceptURI());
		summaryAnnotation.setHasIdentifier(record.getResourceIdentifier());
		
		Patient patientAnnotation = savePatientSummary(record.getPatientSummary(), jcas);
		Cancer cancerAnnoation = saveCancerSummary(record.getCancerSummary(), jcas);
		
		summaryAnnotation.setHasMedicalRecordSummaryCancer(getValue(jcas,cancerAnnoation));
		summaryAnnotation.setHasMedicalRecordSummaryPatient(getValue(jcas,patientAnnotation));
		
		List<FeatureStructure> list = new ArrayList<FeatureStructure>();
		for(Report r:  record.getReports()){
			Composition c = (Composition) getAnnotationByIdentifer(jcas,r.getResourceIdentifier());
			if(c != null)
				list.add(c);
		}
		summaryAnnotation.setHasComposition(getValues(jcas,list));
		summaryAnnotation.addToIndexes();
		
		return summaryAnnotation;
	}
	

	private static Patient savePatientSummary(PatientSummary patientSummary, JCas jcas) {
		// TODO Auto-generated method stub
		return null;
	}

	private static PatientSummary loadPatientSummary(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * create medical record
	 * @param record
	 * @param jcas
	 */
	public static MedicalRecord loadMedicalRecord(JCas jcas) {
		org.healthnlp.deepphe.uima.types.MedicalRecord annotationRecord = null;
		for(Annotation a: getAnnotations(jcas,org.healthnlp.deepphe.uima.types.MedicalRecord.type)){
			annotationRecord = (org.healthnlp.deepphe.uima.types.MedicalRecord) a;
		}	
		
		MedicalRecord record = new MedicalRecord();
		
		// add reports
		record.setReports(loadReports(jcas));
		for(int i=0;i< getSize(annotationRecord.getHasMedicalRecordSummaryCancer()); i++){
			record.setCancerSummary(loadCancerSummary(annotationRecord.getHasMedicalRecordSummaryCancer(i)));
		}
		for(int i=0;i< getSize(annotationRecord.getHasPatient()); i++){
			record.setPatientSummary(loadPatientSummary(annotationRecord.getHasPatient(i)));
		}
		record.setPatient(loadPatient(jcas));
		
		return record;
		
	}
		
}
