package org.healthnlp.deepphe.xfer;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

import java.util.ArrayList;
import java.util.List;

import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.cancer.type.textsem.TnmStage;
import org.apache.ctakes.cancer.type.textsem.TnmStagePrefix;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.healthnlp.deepphe.summarization.jess.kb.Diagnosis;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Summary;
import org.healthnlp.deepphe.summarization.jess.kb.TnmMgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TnmNgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TnmTgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TumorSize;

public class JessToCtakesConverter {

	private Patient patient;
	private JCas patientJCas;

	private TnmTgrade tnmTgrade;
	private TnmNgrade tnmNgrade;
	private TnmMgrade tnmMgrade;

	private int tnmGradesSeen = 0;

	public void execute() {

		for (Summary summary : patient.getSummaries()) {
			String className = summary.getClass().getSimpleName();
			switch (className) {
			case "Diagnosis":
				cacheDiagnosis(summary);
				break;
			case "TnmTgrade":
				tnmTgrade = (TnmTgrade) summary;
				tnmGradesSeen++;
				break;
			case "TnmNgrade":
				tnmNgrade = (TnmNgrade) summary;
				tnmGradesSeen++;
				break;
			case "TnmMgrade":
				tnmMgrade = (TnmMgrade) summary;
				tnmGradesSeen++;
				break;
			case "TumorSize":
				cacheTumorSize(summary);
				break;
			case "Her2":
			case "Er":
			case "Pr":
				cacheReceptorStatus(summary);
				break;
			default:
				break;
			}

			if (tnmGradesSeen == 3) {
				cacheTnmGrade();
				tnmGradesSeen = 0;
			}
		}
	}

	private void cacheDiagnosis(Summary summary) {
		final Diagnosis diagnosis = (Diagnosis) summary;
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

	private void cacheReceptorStatus(Summary summary) {
		final ReceptorStatus receptorStatusAnnotation = new ReceptorStatus(
				patientJCas, 0, 0);
		receptorStatusAnnotation.setCode(summary.getCode());
		receptorStatusAnnotation.setDescription(summary.getPreferredTerm());
		final boolean value = (summary.getValue().equals("positive")) ? true
				: false;
		receptorStatusAnnotation.setValue(value);
		receptorStatusAnnotation.setTypeID(NE_TYPE_ID_FINDING);
		final UmlsConcept umlsConcept = new UmlsConcept(patientJCas);
		umlsConcept.setCui(summary.getCode());
		umlsConcept.setTui("T000");
		umlsConcept.setPreferredText(summary.getPreferredTerm());
		final FSArray ontologyConcepts = new FSArray(patientJCas, 1);
		ontologyConcepts.set(0, umlsConcept);
		receptorStatusAnnotation.setOntologyConceptArr(ontologyConcepts);
		receptorStatusAnnotation.addToIndexes();
	}

	private void cacheTumorSize(Summary summary) {
		final TumorSize tumorSize = (TumorSize) summary;
		final CancerSize cancerSize = new CancerSize(patientJCas, 0, 0);
		final List<Double> dimensions = new ArrayList<Double>();
		if (tumorSize.getDimensionOne() > 0.0d) {
			dimensions.add(tumorSize.getDimensionOne());
		}
		if (tumorSize.getDimensionTwo() > 0.0d) {
			dimensions.add(tumorSize.getDimensionTwo());
		}
		if (tumorSize.getDimensionThree() > 0.0d) {
			dimensions.add(tumorSize.getDimensionThree());
		}
		if (!dimensions.isEmpty()) {
			final FSArray measurementFeatures = new FSArray(patientJCas,
					dimensions.size());
			int measurementIndex = 0;
			for (Double dimension : dimensions) {
				final SizeMeasurement measurementFeature = new SizeMeasurement(
						patientJCas);
				measurementFeature.setValue(dimension.floatValue());
				measurementFeature.setUnit(tumorSize.getUnitOfMeasure());
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
		tnmClassificationType.setSize(createTnmStageFeature(tnmTgrade,
				patientJCas));
		tnmClassificationType.setNodeSpread(createTnmStageFeature(tnmNgrade,
				patientJCas));
		tnmClassificationType.setMetastasis(createTnmStageFeature(tnmMgrade,
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

	private TnmStage createTnmStageFeature(final Summary summary,
			final JCas jcas) {
		final TnmStage tnmStageFeature = new TnmStage(jcas);
		tnmStageFeature.setPrefix(createTnmStagePrefix(jcas));
		tnmStageFeature.setCode(summary.getBaseCode());
		tnmStageFeature.setDescription(summary.getPreferredTerm());
		tnmStageFeature.setValue(summary.getValue());
		return tnmStageFeature;
	}

	private TnmStagePrefix createTnmStagePrefix(final JCas jcas) {
		final TnmStagePrefix tnmStagePrefix = new TnmStagePrefix(jcas);
		tnmStagePrefix.setCode("-");
		tnmStagePrefix
				.setDescription("Stage determination unspecified; assume clinical examination");
		return tnmStagePrefix;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public JCas getPatientJCas() {
		return patientJCas;
	}

	public void setPatientJCas(JCas patientJCas) {
		this.patientJCas = patientJCas;
	}

}
