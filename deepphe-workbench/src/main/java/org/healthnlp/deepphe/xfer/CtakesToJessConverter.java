package org.healthnlp.deepphe.xfer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.type.textsem.*;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.summarization.jess.kb.Diagnosis;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Er;
import org.healthnlp.deepphe.summarization.jess.kb.Her2;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Pr;
import org.healthnlp.deepphe.summarization.jess.kb.Summarizable;
import org.healthnlp.deepphe.summarization.jess.kb.Summary;
import org.healthnlp.deepphe.summarization.jess.kb.TnmMgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TnmNgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TnmTgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TumorSize;
import org.healthnlp.deepphe.uima.ae.CasDetector;

public class CtakesToJessConverter {
	
	private JCas multiJCas;
	private JCas patientJCas;
	private Patient patient;
	
	public CtakesToJessConverter() {
		;
	}
	
	public void execute() throws CASException {
		patient.setId(parseIdFromViewKey(patientJCas.getViewName()));
		for (Iterator<JCas> iterator = multiJCas.getViewIterator(); iterator
				.hasNext();) {
			JCas summarizableCas = iterator.next();
			System.out.println(summarizableCas.getViewName());
			if (CasDetector.isPatientJCas(summarizableCas)) {
				getSummariesForSummarizable(patient, summarizableCas);
			}
			else if (CasDetector.isEncounterJCas(summarizableCas)) {
				Encounter encounter = new Encounter();
				encounter.setPatientId(patient.getId());
				encounter.setId(parseIdFromViewKey(summarizableCas.getViewName()));
				encounter.setSequence(encounter.getId());
				encounter.setKind("Pathology Report");
				getSummariesForSummarizable(encounter, summarizableCas);
				patient.addEncounter(encounter);			
			}
		}
	}
	
	public void getSummariesForSummarizable(Summarizable summarizable, JCas summarizableJCas) {

		List<IdentifiedAnnotation> identifiedAnnotations = new ArrayList<IdentifiedAnnotation>();
		identifiedAnnotations.addAll(getAnnotationsByType(summarizableJCas,
				DiseaseDisorderMention.type));
		identifiedAnnotations.addAll(getAnnotationsByType(summarizableJCas,
				TnmClassification.type));
		identifiedAnnotations.addAll(getAnnotationsByType(summarizableJCas,
				CancerSize.type));
		identifiedAnnotations.addAll(getAnnotationsByType(summarizableJCas,
				ReceptorStatus.type));

		int groupIndex = 0;

		for (IdentifiedAnnotation identifiedAnnotation : identifiedAnnotations) {

			UmlsConcept umlsConcept = getUmlsConceptForIdentifiedAnnotation(identifiedAnnotation);
			switch (identifiedAnnotation.getClass().getSimpleName()) {

			case "DiseaseDisorderMention":
				processDiseaseDisorderMention(summarizable, summarizableJCas, identifiedAnnotation, umlsConcept);
				break;
				
			case "TnmClassification":
				processTnmClassification(summarizable, summarizableJCas,
						identifiedAnnotation, umlsConcept, groupIndex);
				groupIndex++;
				break;

			case "CancerSize":
				processCancerSize(summarizable, summarizableJCas,
						identifiedAnnotation, umlsConcept);
				break;

			case "ReceptorStatus":
				processReceptorStatus(summarizable, umlsConcept);
				break;

			default:
				break;

			}
		}
	}

	private void processDiseaseDisorderMention(Summarizable summarizable,
			JCas encounterJCas, IdentifiedAnnotation identifiedAnnotation,
			UmlsConcept umlsConcept) {
		
		String cui = getCuiForUmlsConcept(umlsConcept);
		if (cui.equals("C1134719")) {
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setSummarizableId(summarizable.getId());
			diagnosis.setBaseCode(cui);
			diagnosis.setCode(cui);
			diagnosis.setPreferredTerm("Invasive Ductal Breast Carcinoma");
			diagnosis.setValue("NA");			
			summarizable.addSummary(diagnosis);
		}
		
		
	}

	private void processReceptorStatus(Summarizable summarizable,
			UmlsConcept umlsConcept) {
		
		String cui = getCuiForUmlsConcept(umlsConcept);
		
		switch (cui) {
		case "C0279756": // Negative Estrogen Receptor
			Summary receptorStatusSummary = new Er();
			receptorStatusSummary.setSummarizableId(summarizable.getId());
			receptorStatusSummary.setBaseCode(cui);
			receptorStatusSummary.setCode(cui);
			receptorStatusSummary.setPreferredTerm(getPreferredTermForUmlsConcept(umlsConcept));
			receptorStatusSummary.setValue("negative");			
			summarizable.addSummary(receptorStatusSummary);
			break;
		case "C0279766": // Negative Progesterone Receptor
			receptorStatusSummary = new Pr();
			receptorStatusSummary.setSummarizableId(summarizable.getId());
			receptorStatusSummary.setBaseCode(cui);
			receptorStatusSummary.setCode(cui);
			receptorStatusSummary.setPreferredTerm(getPreferredTermForUmlsConcept(umlsConcept));
			receptorStatusSummary.setValue("negative");
			summarizable.addSummary(receptorStatusSummary);
			break;
		case "C1960398": // Positive Human epidermal growth factor receptor 2
			receptorStatusSummary = new Her2();
			receptorStatusSummary.setSummarizableId(summarizable.getId());
			receptorStatusSummary.setBaseCode(cui);
			receptorStatusSummary.setCode(cui);
			receptorStatusSummary.setPreferredTerm(getPreferredTermForUmlsConcept(umlsConcept));
			receptorStatusSummary.setValue("positive");
			summarizable.addSummary(receptorStatusSummary);
			break;
		default:
			break;
		}

	}

	private void processCancerSize(Summarizable summarizable, JCas encounterJCas,
			IdentifiedAnnotation identifiedAnnotation, UmlsConcept umlsConcept) {

		CancerSize cancerSize = (CancerSize) identifiedAnnotation;

		TumorSize tumorSize = new TumorSize();
		tumorSize.setSummarizableId(summarizable.getId());

		tumorSize.setDimensionOne(-1.0d);
		tumorSize.setDimensionTwo(-1.0d);
		tumorSize.setDimensionThree(-1.0d);

		FSArray measurementsArray = cancerSize.getMeasurements();
		double maxSize = -1.0d;
		for (int idx = 0; idx < measurementsArray.size(); idx++) {
			SizeMeasurement sizeMeasurement = cancerSize.getMeasurements(idx);
			float valueAsFloat = sizeMeasurement.getValue();
			double valueAsDouble = (double) valueAsFloat;
			System.out.println("Got size measurement of " + valueAsDouble);
			switch (idx) {
			case 0:
				tumorSize.setDimensionOne(valueAsDouble);
				tumorSize.setUnitOfMeasure(sizeMeasurement.getUnit());
				break;
			case 1:
				tumorSize.setDimensionOne(valueAsDouble);
				break;
			default:
				tumorSize.setDimensionThree(valueAsDouble);
				break;
			}
			if (valueAsDouble  > maxSize) {
				maxSize = valueAsDouble;
			}			
		}
		tumorSize.setGreatestDimension(maxSize);
		
		if (tumorSize.getGreatestDimension() >= 2.0d) {
			tumorSize.setBaseCode("C120286");
			tumorSize.setCode("C120286");
			tumorSize
					.setPreferredTerm("Tumor Greater Than or Equal to 2.1 Centimeters");
		} else {
			tumorSize.setBaseCode("C120285");
			tumorSize.setCode("C120285");
			tumorSize
					.setPreferredTerm("Tumor Less Than or Equal to 2.0 Centimeters");
		}

		summarizable.addSummary(tumorSize);

	}

	private void processTnmClassification(Summarizable summarizable,
			JCas encounterJCas, IdentifiedAnnotation identifiedAnnotation,
			UmlsConcept umlsConcept, int groupIndex) {
		TnmClassification tnmClassification = (TnmClassification) identifiedAnnotation;

		TnmFeature tnmT = tnmClassification.getSize();
      TnmFeature tnmN = tnmClassification.getNodeSpread();
      TnmFeature tnmM = tnmClassification.getMetastasis();

		TnmTgrade tnmTgrade = new TnmTgrade();
		tnmTgrade.setSummarizableId(summarizable.getId());
		tnmTgrade.setCode(getCuiForUmlsConcept(umlsConcept));
		tnmTgrade.setBaseCode("T");
		tnmTgrade.setGroupIndex(groupIndex);
		tnmTgrade.setIsActive(1);
		tnmTgrade.setPreferredTerm(tnmT.getDescription());
		tnmTgrade.setValue(tnmT.getValue());
		tnmTgrade.setProvidingDepartment("Pathology");
		tnmTgrade.setUnitOfMeasure("NA");
		summarizable.getSummaries().add(tnmTgrade);

		TnmNgrade tnmNgrade = new TnmNgrade();
		tnmNgrade.setSummarizableId(summarizable.getId());
		tnmNgrade.setBaseCode("N");
		tnmNgrade.setCode(getCuiForUmlsConcept(umlsConcept));
		tnmNgrade.setGroupIndex(groupIndex);
		tnmNgrade.setPreferredTerm(tnmN.getDescription());
		tnmNgrade.setProvidingDepartment("Pathology");
		tnmNgrade.setUnitOfMeasure("NA");
		tnmNgrade.setValue(tnmN.getValue());
		summarizable.addSummary(tnmNgrade);

		TnmMgrade tnmMgrade = new TnmMgrade();
		tnmMgrade.setSummarizableId(summarizable.getId());
		tnmMgrade.setBaseCode("M");
		tnmMgrade.setCode(getCuiForUmlsConcept(umlsConcept));
		tnmMgrade.setGroupIndex(groupIndex);
		tnmMgrade.setPreferredTerm(tnmM.getDescription());
		tnmMgrade.setProvidingDepartment("Pathology");
		tnmMgrade.setUnitOfMeasure("NA");
		tnmMgrade.setValue(tnmM.getValue());
		summarizable.addSummary(tnmMgrade);

	}

	public UmlsConcept getUmlsConceptForIdentifiedAnnotation(
			final IdentifiedAnnotation annotation) {
		UmlsConcept result = null;
		final FeatureStructure[] featureStructures = getFeatureStructures(annotation);
		for (FeatureStructure featureStructure : featureStructures) {
			if (featureStructure instanceof UmlsConcept) {
				result = ((UmlsConcept) featureStructure);
			}
		}
		return result;
	}

	private String getCuiForUmlsConcept(UmlsConcept umlsConcept) {
		return (umlsConcept == null) ? "UNKNOWN" : umlsConcept.getCui();
	}

	private String getPreferredTermForUmlsConcept(UmlsConcept umlsConcept) {
		return (umlsConcept == null) ? "UNKNOWN" : umlsConcept
				.getPreferredText();
	}

	private FeatureStructure[] getFeatureStructures(
			final IdentifiedAnnotation annotation) {
		final FSArray fsArray = annotation.getOntologyConceptArr();
		if (fsArray == null) {
			return new FeatureStructure[0];
		}
		return fsArray.toArray();
	}

	private List<IdentifiedAnnotation> getAnnotationsByType(JCas cas, int type) {
		List<IdentifiedAnnotation> list = new ArrayList<IdentifiedAnnotation>();
		Iterator<Annotation> it = cas.getAnnotationIndex(type).iterator();
		while (it.hasNext()) {
			IdentifiedAnnotation ia = (IdentifiedAnnotation) it.next();
			list.add(ia);
		}
		return list;
	}

	private int parseIdFromViewKey(String viewKey) {
		int result = -1;
		Pattern pattern = Pattern.compile("\\d\\d\\d\\d$");
		Matcher matcher = pattern.matcher(viewKey);
		if (matcher.find()) {
			result = Integer.parseInt(matcher.group());
		}
		return result;
	}

	public JCas getMultiJCas() {
		return multiJCas;
	}

	public void setMultiJCas(JCas multiJCas) {
		this.multiJCas = multiJCas;
	}

	public JCas getPatientJCas() {
		return patientJCas;
	}

	public void setPatientJCas(JCas patientJCas) {
		this.patientJCas = patientJCas;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


}
