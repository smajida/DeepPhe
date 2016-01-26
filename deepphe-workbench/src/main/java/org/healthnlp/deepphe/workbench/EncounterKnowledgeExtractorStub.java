package org.healthnlp.deepphe.workbench;

import org.apache.commons.lang.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.healthnlp.deepphe.summarization.drools.kb.*;
import org.healthnlp.deepphe.summarization.drools.kb.impl.*;

public class EncounterKnowledgeExtractorStub implements EncounterKnowledgeExtractorInterface {
	
	@Override
	public void executePatient(KbPatient patient) {
		if (patient.getSequence() == 0) {
			initializePatientZeroEncounters(patient);
		}
		else if (patient.getSequence() == 1) {
			initializePatientOneEncounters(patient);
		}
	}
	
	@Override
	public void setAnalysisEngine(AnalysisEngine ae) {
	}
	
	@Override
	public void executeEncounter(KbEncounter kbEncounter) {
	}
	
	private void initializePatientZeroEncounters(KbPatient patient) {
		
		for (KbEncounter encounter : patient.getEncounters()) {
			
			if (encounter.getSequence() == 0) {
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
				encounter.setSequence(0);
				
				Tumor diagnosis = new TumorImpl();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setPreferredTerm("Malignant Breast Neoplasm");
				encounter.addSummary((KbSummary) diagnosis);
				
				TumorSize tumorSize = new TumorSizeImpl();
				tumorSize.setSummarizableId(encounter.getId());
				tumorSize.setPreferredTerm("Tumor Greater Than or Equal to 2.1 Centimeters");
				tumorSize.setUnitOfMeasure("cm");
				encounter.addSummary((KbSummary)tumorSize);
				
			}
			else if (encounter.getSequence() == 1) {
				
				encounter.setKind("Progress Note");
				encounter.setPatientId(patient.getId());
				encounter.setSequence(1);				
				
				// Er
				HasInterpretationImpl receptorStatusSummary = new HasInterpretationImpl();
				receptorStatusSummary.setPreferredTerm("Estrogen Receptor Negative");
				receptorStatusSummary.setValue("negative");	
				String formattedId = StringUtils.leftPad(receptorStatusSummary.getId()+"", 7, "0");
				EstrogenReceptorStatus estrogenReceptor = new EstrogenReceptorStatusImpl();
				estrogenReceptor.setCode("<D" + formattedId + ">");
				estrogenReceptor.setPreferredTerm("Estrogen Receptor");
				Interpretation erInterpretation = new NegativeImpl();
				erInterpretation.setCode("<R" + formattedId + ">");
				erInterpretation.setPreferredTerm("Negative");				
				receptorStatusSummary.setDomainId(estrogenReceptor.getId());
				receptorStatusSummary.setRangeId(erInterpretation.getId());
				receptorStatusSummary.setSummarizableId(encounter.getId());
				
				encounter.addSummary((KbSummary)receptorStatusSummary);
				estrogenReceptor.setSummarizableId(encounter.getId());
				encounter.addSummary((KbSummary)estrogenReceptor);
				erInterpretation.setSummarizableId(encounter.getId());
				encounter.addSummary((KbSummary)erInterpretation);
				
				// Pr
				receptorStatusSummary = new HasInterpretationImpl();
				receptorStatusSummary.setBaseCode("C0279766");
				receptorStatusSummary.setCode("C0279766");
				receptorStatusSummary.setPreferredTerm("Progesterone Receptor Negative");
				receptorStatusSummary.setValue("negative");			
				formattedId = StringUtils.leftPad(receptorStatusSummary.getId()+"", 7, "0");
				ProgesteroneReceptorStatus progesteroneReceptor = new ProgesteroneReceptorStatusImpl();
				progesteroneReceptor.setCode("<D" + formattedId + ">");
				progesteroneReceptor.setPreferredTerm("Progesterone Receptor");
				Interpretation prInterpretation = new NegativeImpl();
				prInterpretation.setCode("<R" + formattedId + ">");
				prInterpretation.setPreferredTerm("Negative");				
				receptorStatusSummary.setDomainId(progesteroneReceptor.getId());
				receptorStatusSummary.setRangeId(prInterpretation.getId());			
				receptorStatusSummary.setSummarizableId(encounter.getId());
				
				encounter.addSummary((KbSummary)receptorStatusSummary);
				progesteroneReceptor.setSummarizableId(encounter.getId());
				encounter.addSummary((KbSummary)progesteroneReceptor);
				prInterpretation.setSummarizableId(encounter.getId());
				encounter.addSummary((KbSummary)prInterpretation);
				
				
				// Her2/Neu
				receptorStatusSummary = new HasInterpretationImpl();
				receptorStatusSummary.setPreferredTerm("HER2/Neu Receptor Positive");
				receptorStatusSummary.setValue("positive");			
				formattedId = StringUtils.leftPad(receptorStatusSummary.getId()+"", 7, "0");
				Her2neuReceptorStatus her2NeuReceptor = new Her2neuReceptorStatusImpl();
				her2NeuReceptor.setCode("<D" + formattedId + ">");
				her2NeuReceptor.setPreferredTerm("Her2/Neu Receptor");
				Interpretation her2NeuInterpretation = new PositiveImpl();
				her2NeuInterpretation.setCode("<R" + formattedId + ">");
				her2NeuInterpretation.setPreferredTerm("Positive");				
				receptorStatusSummary.setDomainId(her2NeuReceptor.getId());
				receptorStatusSummary.setRangeId(her2NeuInterpretation.getId());				
				receptorStatusSummary.setSummarizableId(encounter.getId());
				
				encounter.addSummary((KbSummary)receptorStatusSummary);
				her2NeuReceptor.setSummarizableId(encounter.getId());
				encounter.addSummary((KbSummary)her2NeuReceptor);
				her2NeuInterpretation.setSummarizableId(encounter.getId());
				encounter.addSummary((KbSummary)her2NeuInterpretation);
				
				// TNM
				PrimaryTumorClassification tnmTgrade = new T2Impl();
				tnmTgrade.setBaseCode("C0475373");
				tnmTgrade.setCode("C0475373");
				tnmTgrade.setPreferredTerm("T2 Stage Finding");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T2");
				encounter.addSummary((KbSummary)tnmTgrade);
				
				ClinicalRegionalLymphNodeClassification tnmNgrade = new N0Impl();
				tnmNgrade.setPreferredTerm("N0 Stage Finding");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N0");
				encounter.addSummary((KbSummary)tnmNgrade);
				
				DistantMetastasisClassification tnmMgrade = new M0Impl();
				tnmMgrade.setBaseCode("C0445034");
				tnmMgrade.setCode("C0445034");
				tnmMgrade.setPreferredTerm("M0 Stage Finding");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M0");
				encounter.addSummary((KbSummary)tnmMgrade);
			
			}
			else if (encounter.getSequence() == 2) {
				
				encounter.setKind("Progress Note");
				encounter.setPatientId(patient.getId());
				encounter.setSequence(2);
				
				TumorSize tumorSize = new TumorSizeImpl();
				tumorSize.setSummarizableId(encounter.getId());
				tumorSize.setPreferredTerm("Tumor Less Than or Equal to 2.0 Centimeters");
				tumorSize.setUnitOfMeasure("cm");
				encounter.addSummary((KbSummary)tumorSize);
			}
		}
	}
	
	private void initializePatientOneEncounters(KbPatient patient) {
		for (KbEncounter encounter : patient.getEncounters()) {
			
			if (encounter.getSequence() == 0) {
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
				
				Tumor diagnosis = new TumorImpl();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setPreferredTerm("Malignant Breast Neoplasm");
				encounter.addSummary((KbSummary) diagnosis);
				
				PrimaryTumorClassification tnmTgrade = new T0Impl();
				tnmTgrade.setPreferredTerm("Breast Cancer pT0 TNM Finding");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T0");
				encounter.addSummary((KbSummary)tnmTgrade);
				
				ClinicalRegionalLymphNodeClassification tnmNgrade = new N0Impl();
				tnmNgrade.setPreferredTerm("Node stage N0");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N0");
				encounter.addSummary((KbSummary)tnmNgrade);
				
				DistantMetastasisClassification tnmMgrade = new M1Impl();
				tnmMgrade.setPreferredTerm("Breast Cancer pM1 TNM Finding");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M1");
				encounter.addSummary((KbSummary)tnmMgrade);
				
			}
			else if (encounter.getSequence() == 1) {
				
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
			
				Tumor diagnosis = new TumorImpl();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setPreferredTerm("Breast Adenocarcinoma");
				encounter.addSummary((KbSummary) diagnosis);
				
				PrimaryTumorClassification tnmTgrade = new T1Impl();
				tnmTgrade.setPreferredTerm("Breast Cancer pT1 TNM Finding");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T1");
				encounter.addSummary((KbSummary)tnmTgrade);
				
				ClinicalRegionalLymphNodeClassification tnmNgrade = new N1Impl();
				tnmNgrade.setPreferredTerm("Breast Cancer pN1 TNM Finding v7");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N1");
				encounter.addSummary((KbSummary)tnmNgrade);
				
				DistantMetastasisClassification tnmMgrade = new M1Impl();
				tnmMgrade.setPreferredTerm("Breast Cancer pM1 TNM Finding");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M1");
				encounter.addSummary((KbSummary)tnmMgrade);
			
			}
			else if (encounter.getSequence() == 2) {
				
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
			
				Tumor diagnosis = new TumorImpl();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setCode("C1707026");
				diagnosis.setPreferredTerm("Breast Adenocarcinoma");
				encounter.addSummary((KbSummary) diagnosis);
				
				PrimaryTumorClassification tnmTgrade = new T2Impl();
				tnmTgrade.setBaseCode("C1707031");
				tnmTgrade.setCode("C1707031");
				tnmTgrade.setPreferredTerm("Breast Cancer pT2 TNM Finding");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T2");
				encounter.addSummary((KbSummary)tnmTgrade);
				
				ClinicalRegionalLymphNodeClassification tnmNgrade = new N0Impl();
				tnmNgrade.setBaseCode("C2981876");
				tnmNgrade.setCode("C2981876");
				tnmNgrade.setPreferredTerm("Breast Cancer pN2 TNM Finding v7");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N0");
				encounter.addSummary((KbSummary)tnmNgrade);
				
				DistantMetastasisClassification tnmMgrade = new M1Impl();
				tnmMgrade.setBaseCode("C1707005");
				tnmMgrade.setCode("C1707005");
				tnmMgrade.setPreferredTerm("Breast Cancer pM1 TNM Finding");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M1");
				encounter.addSummary((KbSummary)tnmMgrade);
				
			}
		}
		
	}

	



}
