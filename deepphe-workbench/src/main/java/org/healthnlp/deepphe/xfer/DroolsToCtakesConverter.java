package org.healthnlp.deepphe.xfer;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ctakes.cancer.receptor.StatusInstanceFactory;
import org.apache.ctakes.cancer.receptor.StatusPropertyUtil;
import org.apache.ctakes.cancer.size.SizeInstanceFactory;
import org.apache.ctakes.cancer.tnm.TnmInstanceFactory;
import org.apache.ctakes.cancer.tnm.TnmPropertyUtil;
//import org.apache.ctakes.cancer.type.textsem.CancerSize;
//import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
//import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
//import org.apache.ctakes.cancer.type.textsem.TnmClassification;
//import org.apache.ctakes.cancer.type.textsem.TnmFeature;
//import org.apache.ctakes.cancer.type.textsem.TnmPrefix;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.healthnlp.deepphe.summarization.drools.kb.ClinicalRegionalLymphNodeClassification;
import org.healthnlp.deepphe.summarization.drools.kb.DistantMetastasisClassification;
import org.healthnlp.deepphe.summarization.drools.kb.HasInterpretation;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.summarization.drools.kb.PrimaryTumorClassification;
import org.healthnlp.deepphe.summarization.drools.kb.Tumor;
import org.healthnlp.deepphe.summarization.drools.kb.TumorSize;
import org.healthnlp.deepphe.summarization.drools.kb.impl.HasInterpretationImpl;

public class DroolsToCtakesConverter {

	private KbPatient patient;
	private JCas patientJCas;

	private PrimaryTumorClassification tnmTgrade;
	private ClinicalRegionalLymphNodeClassification tnmNgrade;
	private DistantMetastasisClassification tnmMgrade;

	private int tnmGradesSeen = 0;

	public void execute() {
		
		for (KbSummary summary : patient.getSummaries()) {
			
			if (Tumor.class.isAssignableFrom(summary.getClass())) {
				cacheDiagnosis(summary);
			} else if (PrimaryTumorClassification.class.isAssignableFrom(summary.getClass())) {
				tnmTgrade = (PrimaryTumorClassification) summary;
			}  else if (ClinicalRegionalLymphNodeClassification.class.isAssignableFrom(summary.getClass())) {
				tnmNgrade = (ClinicalRegionalLymphNodeClassification) summary;
			}  else if (DistantMetastasisClassification.class.isAssignableFrom(summary.getClass())) {
				tnmMgrade = (DistantMetastasisClassification) summary;
			}   else if (TumorSize.class.isAssignableFrom(summary.getClass())) {
				cacheTumorSize(summary);
			}   else if (HasInterpretation.class.isAssignableFrom(summary.getClass())) {
				HasInterpretationImpl hasInterpretation = (HasInterpretationImpl) summary;
				int domainId = hasInterpretation.getDomainId();
				int rangeId =  hasInterpretation.getRangeId();
				cacheReceptorStatus(summary);
			}
	
			if (tnmGradesSeen == 3) {
				cacheTnmGrade();
				tnmGradesSeen = 0;
			}
		}
	}

	private void cacheDiagnosis(KbSummary summary) {
		final Tumor diagnosis = (Tumor) summary;
		// TODO if we know the uri we can just use it directly
//		final IdentifiedAnnotation annotation = UriAnnotationFactory.createIdentifiedAnnotation( patientJCas, 0, 0, uri );
//      annotation.addToIndexes();

		final FSArray ocArr = new FSArray(patientJCas, 1);
		final OntologyConcept oc = new OntologyConcept(patientJCas);
		oc.setCode(diagnosis.getCode());
		oc.setCodingScheme("CTAKES");
		ocArr.set(0, oc);

		DiseaseDisorderMention neAnnot = new DiseaseDisorderMention(patientJCas);
		neAnnot.setBegin(0);
		neAnnot.setEnd(0);
		neAnnot.setDiscoveryTechnique(CONST.NE_DISCOVERY_TECH_DICT_LOOKUP);
		neAnnot.setOntologyConceptArr(ocArr);
		neAnnot.setTypeID(CONST.NE_TYPE_ID_DISORDER);
		neAnnot.addToIndexes();
	}

	private void cacheReceptorStatus(KbSummary summary) {
		// TODO - replace with Identified Annotations with proper value URIs
		final String typeUri = StatusPropertyUtil.getInstance().getTypeUri( summary.getBaseCode() );
		final String valueUri = StatusPropertyUtil.getInstance().getValueUri( summary.getValue() );
		StatusInstanceFactory.getInstance().createInstance( patientJCas,
				typeUri, 0, 0,
				valueUri, 0, 0,
				Collections.emptyList(), Collections.emptyList() );
//		final ReceptorStatus receptorStatusAnnotation = new ReceptorStatus(
//				patientJCas, 0, 0);
//		receptorStatusAnnotation.setCode(summary.getCode());
//		receptorStatusAnnotation.setDescription(summary.getPreferredTerm());
//		final boolean value = summary.getValue().equals("positive");
//		receptorStatusAnnotation.setValue(value);
//		receptorStatusAnnotation.setTypeID(NE_TYPE_ID_FINDING);
//		final UmlsConcept umlsConcept = new UmlsConcept(patientJCas);
//		umlsConcept.setCui(summary.getCode());
//		umlsConcept.setTui("T000");
//		umlsConcept.setPreferredText(summary.getPreferredTerm());
//		final FSArray ontologyConcepts = new FSArray(patientJCas, 1);
//		ontologyConcepts.set(0, umlsConcept);
//		receptorStatusAnnotation.setOntologyConceptArr(ontologyConcepts);
//		receptorStatusAnnotation.addToIndexes();
	}

	private void cacheTumorSize(KbSummary summary) {
		
//		final CancerSize cancerSize = new CancerSize(patientJCas, 0, 0);
		final List<Double> dimensions = new ArrayList<Double>();
		
		if (TumorSize.class.isAssignableFrom(summary.getClass())) {
			dimensions.add(2.1d);
		} 
//		else if (TumorSize.class.isAssignableFrom(summary.getClass())) {
//			dimensions.add(2.0d);
//		} 
		if (!dimensions.isEmpty()) {
//			final FSArray measurementFeatures = new FSArray(patientJCas,
//					dimensions.size());
//			int measurementIndex = 0;
			for (Double dimension : dimensions) {
//				final SizeMeasurement measurementFeature = new SizeMeasurement(
//						patientJCas);
//				measurementFeature.setValue(dimension.floatValue());
//				measurementFeature.setUnit("cm");
//				measurementFeatures.set(measurementIndex, measurementFeature);
//				measurementIndex++;
				// TODO - replace with Identified Annotations with proper value URIs
				// TODO - what are the possible summary basecodes wrt size?
//			final String typeUri = SizePropertyUtil.getInstance().getTypeUri( summary.getBaseCode() );
//			final String valueUri = SizePropertyUtil.getInstance().getValueUri( summary.getValue() );
				SizeInstanceFactory.getInstance().createInstance( patientJCas, 0, 0, 0, 0, dimension+" cm" );
			}
//			cancerSize.setMeasurements(measurementFeatures);
		}
//		cancerSize.addToIndexes();
	}

	private void cacheTnmGrade() {
//		final TnmClassification tnmClassificationType = new TnmClassification(
//				patientJCas, 0, 0);
//		tnmClassificationType.setSize(createTnmStageFeature((KbSummary)tnmTgrade,
//				patientJCas));
//		tnmClassificationType.setNodeSpread(createTnmStageFeature((KbSummary)tnmNgrade,
//				patientJCas));
//		tnmClassificationType.setMetastasis(createTnmStageFeature((KbSummary)tnmMgrade,
//				patientJCas));
		createTnmStageFeature( (KbSummary)tnmTgrade, patientJCas );
		createTnmStageFeature( (KbSummary)tnmNgrade, patientJCas );
		createTnmStageFeature( (KbSummary)tnmMgrade, patientJCas );
//		tnmClassificationType.setTypeID(NE_TYPE_ID_FINDING);
//		final UmlsConcept umlsConcept = new UmlsConcept(patientJCas);
//		umlsConcept.setCui("C1300492");
//		umlsConcept.setTui("T034");
//		umlsConcept.setCodingScheme("SNOMED");
//		umlsConcept.setCode("385379008");
//		umlsConcept.setPreferredText("TNM tumor staging finding");
//		final FSArray ontologyConcepts = new FSArray(patientJCas, 1);
//		ontologyConcepts.set(0, umlsConcept);
//		tnmClassificationType.setOntologyConceptArr(ontologyConcepts);
//
//		UriAnnotationFactory.createIdentifiedAnnotation( patientJCas, 0, 0, TnmPropertyUtil.getParentUri() );
//		// TODO - replaced with UriAnnotationFactory call
////		IdentifiedAnnotation tnmClassificationType
////				 = UriAnnotationFactory.createIdentifiedAnnotation( patientJCas, 0, 0, TnmPropertyUtil.getParentUri() );
//		tnmClassificationType.addToIndexes();
	}

	//	private TnmFeature createTnmStageFeature(final KbSummary summary, final JCas jcas) {
	private SignSymptomMention createTnmStageFeature( final KbSummary summary, final JCas jcas ) {
		// TODO - replace with Identified Annotations with proper value URIs
		final String typeUri = TnmPropertyUtil.getInstance().getTypeUri( summary.getBaseCode() );
		final String valueUri = TnmPropertyUtil.getInstance().getValueUri( summary.getValue() );
		return TnmInstanceFactory.getInstance().createInstance( jcas,
				typeUri, 0, 0,
				valueUri, 0, 0,
				Collections.emptyList(), Collections.emptyList() );
//		final TnmFeature tnmStageFeature = new TnmFeature(jcas);
//		tnmStageFeature.setPrefix(createTnmStagePrefix(jcas));
//		tnmStageFeature.setCode(summary.getBaseCode());
//		tnmStageFeature.setDescription(summary.getPreferredTerm());
//		tnmStageFeature.setValue(summary.getValue());
//		return tnmStageFeature;
	}

//	private TnmPrefix createTnmStagePrefix(final JCas jcas) {
//		final TnmPrefix tnmStagePrefix = new TnmPrefix(jcas);
//		tnmStagePrefix.setCode("-");
//		tnmStagePrefix
//				.setDescription("Stage determination unspecified; assume clinical examination");
//		return tnmStagePrefix;
//	}

	public KbPatient getPatient() {
		return patient;
	}

	public void setPatient(KbPatient patient) {
		this.patient = patient;
	}

	public JCas getPatientJCas() {
		return patientJCas;
	}

	public void setPatientJCas(JCas patientJCas) {
		this.patientJCas = patientJCas;
	}

}
