package org.healthnlp.deepphe.xfer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.summarization.drools.kb.EstrogenReceptorStatus;
import org.healthnlp.deepphe.summarization.drools.kb.GenericDistantMetastasisTnmFinding;
import org.healthnlp.deepphe.summarization.drools.kb.GenericPrimaryTumorTnmFinding;
import org.healthnlp.deepphe.summarization.drools.kb.GenericRegionalLymphNodesTnmFinding;
import org.healthnlp.deepphe.summarization.drools.kb.Her2NeuStatus;
import org.healthnlp.deepphe.summarization.drools.kb.InvasiveBreastCarcinoma;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummarizable;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.summarization.drools.kb.OrdinalInterpretation;
import org.healthnlp.deepphe.summarization.drools.kb.ProgesteroneReceptorStatus;
import org.healthnlp.deepphe.summarization.drools.kb.RelationHasinterpretation;
import org.healthnlp.deepphe.summarization.drools.kb.TumorSize;
import org.healthnlp.deepphe.summarization.drools.kb.impl.EstrogenReceptorStatusImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.GenericDistantMetastasisTnmFindingImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.GenericPrimaryTumorTnmFindingImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.GenericRegionalLymphNodesTnmFindingImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.Her2NeuStatusImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.InvasiveBreastCarcinomaImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.NegativeImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.PositiveImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.ProgesteroneReceptorStatusImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.RelationHasinterpretationImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.TumorGreaterThanOrEqualTo21CentimetersImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.TumorLessThanOrEqualTo20CentimetersImpl;
import org.healthnlp.deepphe.uima.ae.CasDetector;

public class CtakesToDroolsConverter {
	
	private JCas multiJCas;
	private JCas patientJCas;
	private KbPatient patient;
	
	public CtakesToDroolsConverter() {
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
				KbEncounter encounter = new KbEncounter();
				encounter.setPatientId(patient.getId());
				encounter.setId(parseIdFromViewKey(summarizableCas.getViewName()));
				encounter.setSequence(encounter.getId());
				encounter.setKind("Pathology Report");
				getSummariesForSummarizable(encounter, summarizableCas);
				patient.addEncounter(encounter);			
			}
		}
	}
	
	public void getSummariesForSummarizable(KbSummarizable summarizable, JCas summarizableJCas) {

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

	private void processDiseaseDisorderMention(KbSummarizable summarizable,
			JCas encounterJCas, IdentifiedAnnotation identifiedAnnotation,
			UmlsConcept umlsConcept) {
		String cui = getCuiForUmlsConcept(umlsConcept);
		if (cui.equals("C1134719")) {
			InvasiveBreastCarcinoma diagnosis = new InvasiveBreastCarcinomaImpl();
			diagnosis.setSummarizableId(summarizable.getId());
			diagnosis.setBaseCode(cui);
			diagnosis.setCode(cui);
			diagnosis.setPreferredTerm("Invasive Ductal Breast Carcinoma");
			diagnosis.setValue("NA");			
			summarizable.addSummary((KbSummary) diagnosis);
		}
	}

	private void processReceptorStatus(KbSummarizable summarizable,
			UmlsConcept umlsConcept) {
		
		String cui = getCuiForUmlsConcept(umlsConcept);
		
		switch (cui) {
		case "C0279756": // Negative Estrogen Receptor	
			RelationHasinterpretation receptorStatusSummary = new RelationHasinterpretationImpl();
			EstrogenReceptorStatus erStatus = new EstrogenReceptorStatusImpl();
			OrdinalInterpretation erInterpretation = new NegativeImpl();
			receptorStatusSummary.setDomainId(erStatus.getId());
			receptorStatusSummary.setRangeId(erInterpretation.getId());
			receptorStatusSummary.setSummarizableId(summarizable.getId());
			receptorStatusSummary.setBaseCode(cui);
			receptorStatusSummary.setCode(cui);
			receptorStatusSummary.setPreferredTerm(getPreferredTermForUmlsConcept(umlsConcept));
			receptorStatusSummary.setValue("negative");			
			summarizable.addSummary((KbSummary)receptorStatusSummary);
			break;
		case "C0279766": // Negative Progesterone Receptor
			receptorStatusSummary = new RelationHasinterpretationImpl();
			ProgesteroneReceptorStatus prStatus = new ProgesteroneReceptorStatusImpl();
			OrdinalInterpretation prInterpretation = new NegativeImpl();
			receptorStatusSummary.setDomainId(prStatus.getId());
			receptorStatusSummary.setRangeId(prInterpretation.getId());
			receptorStatusSummary.setSummarizableId(summarizable.getId());
			receptorStatusSummary.setBaseCode(cui);
			receptorStatusSummary.setCode(cui);
			receptorStatusSummary.setPreferredTerm(getPreferredTermForUmlsConcept(umlsConcept));
			receptorStatusSummary.setValue("negative");			
			summarizable.addSummary((KbSummary)receptorStatusSummary);
			break;
		case "C1960398": // Positive Human epidermal growth factor receptor 2
			receptorStatusSummary = new RelationHasinterpretationImpl();
			Her2NeuStatus her2NeuStatus = new Her2NeuStatusImpl();
			OrdinalInterpretation her2NeuInterpretation = new PositiveImpl();
			receptorStatusSummary.setDomainId(her2NeuStatus.getId());
			receptorStatusSummary.setRangeId(her2NeuInterpretation.getId());
			receptorStatusSummary.setSummarizableId(summarizable.getId());
			receptorStatusSummary.setBaseCode(cui);
			receptorStatusSummary.setCode(cui);
			receptorStatusSummary.setPreferredTerm(getPreferredTermForUmlsConcept(umlsConcept));
			receptorStatusSummary.setValue("positive");			
			summarizable.addSummary((KbSummary)receptorStatusSummary);
			break;
		default:
			break;
		}

	}

	private void processCancerSize(KbSummarizable summarizable, JCas encounterJCas,
			IdentifiedAnnotation identifiedAnnotation, UmlsConcept umlsConcept) {
	
		CancerSize cancerSize = (CancerSize) identifiedAnnotation;
		FSArray measurementsArray = cancerSize.getMeasurements();
		double maxSize = -1.0d;
		for (int idx = 0; idx < measurementsArray.size(); idx++) {
			SizeMeasurement sizeMeasurement = cancerSize.getMeasurements(idx);
			float valueAsFloat = sizeMeasurement.getValue();
			double valueAsDouble = (double) valueAsFloat;
			if (valueAsDouble  > maxSize) {
				maxSize = valueAsDouble;
			}			
		}
		
		if (maxSize > 0.0d) {
			System.out.println("Got size measurement of " + maxSize);		
			TumorSize tumorSize;
			if (maxSize >= 2.0d) {
				tumorSize = new TumorGreaterThanOrEqualTo21CentimetersImpl();
				tumorSize.setBaseCode("C120286");
				tumorSize.setCode("C120286");
				tumorSize
						.setPreferredTerm("Tumor Greater Than or Equal to 2.1 Centimeters");
			} else {
				tumorSize = new TumorLessThanOrEqualTo20CentimetersImpl();
				tumorSize.setBaseCode("C120285");
				tumorSize.setCode("C120285");
				tumorSize
						.setPreferredTerm("Tumor Less Than or Equal to 2.0 Centimeters");
			}
			summarizable.addSummary((KbSummary)tumorSize);
		}
		
	}

	private void processTnmClassification(KbSummarizable summarizable,
			JCas encounterJCas, IdentifiedAnnotation identifiedAnnotation,
			UmlsConcept umlsConcept, int groupIndex) {

		GenericPrimaryTumorTnmFinding tnmTgrade = new GenericPrimaryTumorTnmFindingImpl();
		tnmTgrade.setSummarizableId(summarizable.getId());
		tnmTgrade.setCode(getCuiForUmlsConcept(umlsConcept));
		tnmTgrade.setBaseCode("T");
		tnmTgrade.setIsActive(1);
		tnmTgrade.setUnitOfMeasure("NA");
		summarizable.getSummaries().add((KbSummary)tnmTgrade);

		GenericRegionalLymphNodesTnmFinding tnmNgrade = new GenericRegionalLymphNodesTnmFindingImpl();
		tnmNgrade.setSummarizableId(summarizable.getId());
		tnmNgrade.setBaseCode("N");
		tnmNgrade.setCode(getCuiForUmlsConcept(umlsConcept));
		tnmNgrade.setUnitOfMeasure("NA");
		summarizable.addSummary((KbSummary)tnmNgrade);

		GenericDistantMetastasisTnmFinding tnmMgrade = new GenericDistantMetastasisTnmFindingImpl();
		tnmMgrade.setSummarizableId(summarizable.getId());
		tnmMgrade.setBaseCode("M");
		tnmMgrade.setCode(getCuiForUmlsConcept(umlsConcept));
		tnmMgrade.setUnitOfMeasure("NA");
		summarizable.addSummary((KbSummary)tnmMgrade);

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

	public KbPatient getPatient() {
		return patient;
	}

	public void setPatient(KbPatient patient) {
		this.patient = patient;
	}


}
