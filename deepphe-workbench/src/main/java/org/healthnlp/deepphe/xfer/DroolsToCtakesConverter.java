package org.healthnlp.deepphe.xfer;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

import java.util.ArrayList;
import java.util.List;

import org.apache.ctakes.cancer.receptor.ReceptorStatusUtil;
import org.apache.ctakes.cancer.receptor.StatusType;
import org.apache.ctakes.cancer.receptor.StatusValue;
import org.apache.ctakes.cancer.type.textsem.*;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.healthnlp.deepphe.summarization.drools.kb.DiseaseDisorder;
import org.healthnlp.deepphe.summarization.drools.kb.GenericDistantMetastasisTnmFinding;
import org.healthnlp.deepphe.summarization.drools.kb.GenericPrimaryTumorTnmFinding;
import org.healthnlp.deepphe.summarization.drools.kb.GenericRegionalLymphNodesTnmFinding;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.summarization.drools.kb.RelationHasinterpretation;
import org.healthnlp.deepphe.summarization.drools.kb.TumorGreaterThanOrEqualTo21Centimeters;
import org.healthnlp.deepphe.summarization.drools.kb.TumorLessThanOrEqualTo20Centimeters;
import org.healthnlp.deepphe.summarization.drools.kb.TumorSize;

public class DroolsToCtakesConverter {

	private KbPatient patient;
	private JCas patientJCas;

	private GenericPrimaryTumorTnmFinding tnmTgrade;
	private GenericRegionalLymphNodesTnmFinding tnmNgrade;
	private GenericDistantMetastasisTnmFinding tnmMgrade;

	private int tnmGradesSeen = 0;

	public void execute() {
		
		for (KbSummary summary : patient.getSummaries()) {
			
			if (DiseaseDisorder.class.isAssignableFrom(summary.getClass())) {
				cacheDiagnosis(summary);
			} else if (GenericPrimaryTumorTnmFinding.class.isAssignableFrom(summary.getClass())) {
				tnmTgrade = (GenericPrimaryTumorTnmFinding) summary;
			}  else if (GenericRegionalLymphNodesTnmFinding.class.isAssignableFrom(summary.getClass())) {
				tnmNgrade = (GenericRegionalLymphNodesTnmFinding) summary;
			}  else if (GenericDistantMetastasisTnmFinding .class.isAssignableFrom(summary.getClass())) {
				tnmMgrade = (GenericDistantMetastasisTnmFinding) summary;
			}   else if (TumorSize.class.isAssignableFrom(summary.getClass())) {
				cacheTumorSize(summary);
			}   else if (RelationHasinterpretation.class.isAssignableFrom(summary.getClass())) {
				int domainId = ((RelationHasinterpretation) summary).getDomainId();
				int rangeId =  ((RelationHasinterpretation) summary).getRangeId();
				cacheReceptorStatus(summary);
			}
	
			if (tnmGradesSeen == 3) {
				cacheTnmGrade();
				tnmGradesSeen = 0;
			}
		}
	}

	private void cacheDiagnosis(KbSummary summary) {
		final DiseaseDisorder diagnosis = (DiseaseDisorder) summary;
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

	//	private void cacheReceptorStatus(KbSummary summary) {
//		final ReceptorStatus receptorStatusAnnotation = new ReceptorStatus(
//				patientJCas, 0, 0);
//		receptorStatusAnnotation.setCode(summary.getCode());
//		receptorStatusAnnotation.setDescription(summary.getPreferredTerm());
//		final boolean value = (summary.getValue().equals("positive")) ? true
//				: false;
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
//	}
	private void cacheReceptorStatus( final KbSummary summary ) {
		final StatusValue statusValue = summary.getValue().equals( "positive" )
												  ? StatusValue.POSITIVE : StatusValue.NEGATIVE;
		// TODO Test -- This depends upon the summary.getCode returning the constants declared in StatusType
		StatusType statusType = StatusType.PR;
		for ( StatusType type : StatusType.values() ) {
			if ( type.getCui( statusValue ).equals( summary.getCode() ) ) {
				statusType = type;
				break;
			}
		}
		ReceptorStatusUtil.createFullReceptorStatusMention( patientJCas, 0, 0, statusType, 0, 0, statusValue );
	}


	private void cacheTumorSize(KbSummary summary) {
		
		final CancerSize cancerSize = new CancerSize(patientJCas, 0, 0);
		final List<Double> dimensions = new ArrayList<Double>();
		
		if (TumorGreaterThanOrEqualTo21Centimeters.class.isAssignableFrom(summary.getClass())) {
			dimensions.add(2.1d);
		} 
		else if (TumorLessThanOrEqualTo20Centimeters.class.isAssignableFrom(summary.getClass())) {
			dimensions.add(2.0d);
		} 
		if (!dimensions.isEmpty()) {
			final FSArray measurementFeatures = new FSArray(patientJCas,
					dimensions.size());
			int measurementIndex = 0;
			for (Double dimension : dimensions) {
				final SizeMeasurement measurementFeature = new SizeMeasurement(
						patientJCas);
				measurementFeature.setValue(dimension.floatValue());
				measurementFeature.setUnit("cm");
				measurementFeatures.set(measurementIndex, measurementFeature);
				measurementIndex++;
			}
			cancerSize.setMeasurements(measurementFeatures);
		}
		cancerSize.addToIndexes();
	}

	private void cacheTnmGrade() {
		final TnmClassification tnmClassificationType = new TnmClassification(
				patientJCas, 0, 0);
		tnmClassificationType.setSize(createTnmStageFeature((KbSummary)tnmTgrade,
				patientJCas));
		tnmClassificationType.setNodeSpread(createTnmStageFeature((KbSummary)tnmNgrade,
				patientJCas));
		tnmClassificationType.setMetastasis(createTnmStageFeature((KbSummary)tnmMgrade,
				patientJCas));
		tnmClassificationType.setTypeID(NE_TYPE_ID_FINDING);
		final UmlsConcept umlsConcept = new UmlsConcept(patientJCas);
		umlsConcept.setCui("C1300492");
		umlsConcept.setTui("T034");
		umlsConcept.setCodingScheme("SNOMED");
		umlsConcept.setCode("385379008");
		umlsConcept.setPreferredText("TNM tumor staging finding");
		final FSArray ontologyConcepts = new FSArray(patientJCas, 1);
		ontologyConcepts.set(0, umlsConcept);
		tnmClassificationType.setOntologyConceptArr(ontologyConcepts);
		tnmClassificationType.addToIndexes();
	}

	private TnmFeature createTnmStageFeature(final KbSummary summary,
			final JCas jcas) {
		final TnmFeature tnmStageFeature = new TnmFeature(jcas);
		tnmStageFeature.setPrefix(createTnmStagePrefix(jcas));
		tnmStageFeature.setCode(summary.getBaseCode());
		tnmStageFeature.setDescription(summary.getPreferredTerm());
		tnmStageFeature.setValue(summary.getValue());
		return tnmStageFeature;
	}

	private TnmPrefix createTnmStagePrefix(final JCas jcas) {
		final TnmPrefix tnmStagePrefix = new TnmPrefix(jcas);
		tnmStagePrefix.setCode("-");
		tnmStagePrefix
				.setDescription("Stage determination unspecified; assume clinical examination");
		return tnmStagePrefix;
	}

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
