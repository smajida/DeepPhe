package org.healthnlp.deepphe.workbench;

import org.healthnlp.deepphe.summarization.jess.kb.Diagnosis;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Er;
import org.healthnlp.deepphe.summarization.jess.kb.Her2;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Pr;
import org.healthnlp.deepphe.summarization.jess.kb.TnmMgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TnmNgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TnmTgrade;
import org.healthnlp.deepphe.summarization.jess.kb.TumorSize;


public class EncounterKnowledgeExtractorStub implements EncounterKnowledgeExtractorInterface {
	
	private Patient patient;
	
	@Override
	public void execute() {
		if (patient.getSequence() == 0) {
			initializePatientZeroEncounters();
		}
		else if (patient.getSequence() == 1) {
			initializePatientOneEncounters();
		}
	}
	
	private void initializePatientZeroEncounters() {
		for (Encounter encounter : patient.getEncounters()) {
			
			if (encounter.getSequence() == 0) {
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
				encounter.setSequence(0);
				
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setCode("C0858252");
				diagnosis.setPreferredTerm("Breast Adenocarcinoma");
				encounter.addSummary(diagnosis);
				
				TumorSize tumorSize = new TumorSize();
				tumorSize.setSummarizableId(encounter.getId());
				tumorSize.setBaseCode("C120286");
				tumorSize.setCode("C120286");
				tumorSize.setPreferredTerm("Tumor Greater Than or Equal to 2.1 Centimeters");
				tumorSize.setGreatestDimension(2.1d);
				tumorSize.setDimensionOne(2.1d);
				tumorSize.setDimensionTwo(-1.0d);
				tumorSize.setDimensionThree(-1.0d);
				tumorSize.setUnitOfMeasure("cm");
				encounter.addSummary(tumorSize);
				
				
			}
			else if (encounter.getSequence() == 1) {
				
				encounter.setKind("Progress Note");
				encounter.setPatientId(patient.getId());
				encounter.setSequence(1);				
				
				Er estrogenReceptor = new Er();
				estrogenReceptor.setCode("C0279756");
				estrogenReceptor.setPreferredTerm("Estrogen Receptor Negative");
				estrogenReceptor.setSummarizableId(encounter.getId());
				estrogenReceptor.setValue("negative");
				encounter.addSummary(estrogenReceptor);
				
				Pr progesteroneReceptor = new Pr();
				progesteroneReceptor.setCode("C0279766");
				progesteroneReceptor.setPreferredTerm("Progesterone Receptor Negative");
				progesteroneReceptor.setSummarizableId(encounter.getId());
				progesteroneReceptor.setValue("negative");
				encounter.addSummary(progesteroneReceptor);
				
				Her2 her2 = new Her2();
				her2.setCode("C2348909");
				her2.setPreferredTerm("HER2/Neu Positive");
				her2.setSummarizableId(encounter.getId());
				her2.setValue("positive");
				encounter.addSummary(her2);
				
				TnmTgrade tnmTgrade = new TnmTgrade();
				tnmTgrade.setBaseCode("C0475373");
				tnmTgrade.setCode("C0475373");
				tnmTgrade.setGroupIndex(1);
				tnmTgrade.setPreferredTerm("T2 Stage Finding");
				tnmTgrade.setProvidingDepartment("Clinical");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T2");
				encounter.addSummary(tnmTgrade);
				
				TnmNgrade tnmNgrade = new TnmNgrade();
				tnmNgrade.setBaseCode("C0441959");
				tnmNgrade.setCode("C0441959");
				tnmNgrade.setGroupIndex(1);
				tnmNgrade.setPreferredTerm("Node stage N0");
				tnmNgrade.setProvidingDepartment("Clinical");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N0");
				encounter.addSummary(tnmNgrade);
				
				TnmMgrade tnmMgrade = new TnmMgrade();
				tnmMgrade.setBaseCode("C0445034");
				tnmMgrade.setCode("C0445034");
				tnmMgrade.setGroupIndex(1);
				tnmMgrade.setPreferredTerm("M0 Stage Finding");
				tnmMgrade.setProvidingDepartment("Clinical");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M0");
				encounter.addSummary(tnmMgrade);
			
			}
			else if (encounter.getSequence() == 2) {
				
				encounter.setKind("Progress Note");
				encounter.setPatientId(patient.getId());
				encounter.setSequence(2);
				
				TumorSize tumorSize = new TumorSize();
				tumorSize.setSummarizableId(encounter.getId());
				tumorSize.setBaseCode("C120285");
				tumorSize.setCode("C120285");
				tumorSize.setPreferredTerm("Tumor Less Than or Equal to 2.0 Centimeters");
				tumorSize.setGreatestDimension(0.9d);
				tumorSize.setDimensionOne(0.9d);
				tumorSize.setDimensionTwo(-1.0d);
				tumorSize.setDimensionThree(-1.0d);
				tumorSize.setUnitOfMeasure("cm");
				encounter.addSummary(tumorSize);
				
			}
		}
	}
	
	private void initializePatientOneEncounters() {
		for (Encounter encounter : patient.getEncounters()) {
			
			if (encounter.getSequence() == 0) {
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
				
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setCode("C0858252");
				diagnosis.setPreferredTerm("Breast Adenocarcinoma");
				encounter.addSummary(diagnosis);
				
				TnmTgrade tnmTgrade = new TnmTgrade();
				tnmTgrade.setBaseCode("C1707025");
				tnmTgrade.setCode("C1707025");
				tnmTgrade.setGroupIndex(1);
				tnmTgrade.setPreferredTerm("Breast Cancer pT0 TNM Finding");
				tnmTgrade.setProvidingDepartment("Pathology");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T0");
				encounter.addSummary(tnmTgrade);
				
				TnmNgrade tnmNgrade = new TnmNgrade();
				tnmNgrade.setBaseCode("C2981868");
				tnmNgrade.setCode("C2981868");
				tnmNgrade.setGroupIndex(1);
				tnmNgrade.setPreferredTerm("Breast Cancer pN0 TNM Finding v7");
				tnmNgrade.setProvidingDepartment("Pathology");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N0");
				encounter.addSummary(tnmNgrade);
				
				TnmMgrade tnmMgrade = new TnmMgrade();
				tnmMgrade.setBaseCode("C1707005");
				tnmMgrade.setCode("C1707005");
				tnmMgrade.setGroupIndex(1);
				tnmMgrade.setPreferredTerm("Breast Cancer pM1 TNM Finding");
				tnmMgrade.setProvidingDepartment("Clinical");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M1");
				encounter.addSummary(tnmMgrade);
				
			}
			else if (encounter.getSequence() == 1) {
				
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
			
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setCode("C0858252");
				diagnosis.setPreferredTerm("Breast Adenocarcinoma");
				encounter.addSummary(diagnosis);
				
				TnmTgrade tnmTgrade = new TnmTgrade();
				tnmTgrade.setBaseCode("C1707026");
				tnmTgrade.setCode("C1707026");
				tnmTgrade.setGroupIndex(1);
				tnmTgrade.setPreferredTerm("Breast Cancer pT1 TNM Finding");
				tnmTgrade.setProvidingDepartment("Pathology");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T1");
				encounter.addSummary(tnmTgrade);
				
				TnmNgrade tnmNgrade = new TnmNgrade();
				tnmNgrade.setBaseCode("C2981871");
				tnmNgrade.setCode("C2981871");
				tnmNgrade.setGroupIndex(1);
				tnmNgrade.setPreferredTerm("Breast Cancer pN1 TNM Finding v7");
				tnmNgrade.setProvidingDepartment("Pathology");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N1");
				encounter.addSummary(tnmNgrade);
				
				TnmMgrade tnmMgrade = new TnmMgrade();
				tnmMgrade.setBaseCode("C1707005");
				tnmMgrade.setCode("C1707005");
				tnmMgrade.setGroupIndex(1);
				tnmMgrade.setPreferredTerm("Breast Cancer pM1 TNM Finding");
				tnmMgrade.setProvidingDepartment("Clinical");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M1");
				encounter.addSummary(tnmMgrade);
			
			}
			else if (encounter.getSequence() == 2) {
				
				encounter.setKind("Pathology Report");
				encounter.setPatientId(patient.getId());
			
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setSummarizableId(encounter.getId());
				diagnosis.setCode("C0858252");
				diagnosis.setPreferredTerm("Breast Adenocarcinoma");
				encounter.addSummary(diagnosis);
				
				TnmTgrade tnmTgrade = new TnmTgrade();
				tnmTgrade.setBaseCode("C1707031");
				tnmTgrade.setCode("C1707031");
				tnmTgrade.setGroupIndex(1);
				tnmTgrade.setPreferredTerm("Breast Cancer pT2 TNM Finding");
				tnmTgrade.setProvidingDepartment("Pathology");
				tnmTgrade.setSummarizableId(encounter.getId());
				tnmTgrade.setUnitOfMeasure("NA");
				tnmTgrade.setValue("T2");
				encounter.addSummary(tnmTgrade);
				
				TnmNgrade tnmNgrade = new TnmNgrade();
				tnmNgrade.setBaseCode("C2981876");
				tnmNgrade.setCode("C2981876");
				tnmNgrade.setGroupIndex(1);
				tnmNgrade.setPreferredTerm("Breast Cancer pN2 TNM Finding v7");
				tnmNgrade.setProvidingDepartment("Clinical");
				tnmNgrade.setSummarizableId(encounter.getId());
				tnmNgrade.setUnitOfMeasure("NA");
				tnmNgrade.setValue("N0");
				encounter.addSummary(tnmNgrade);
				
				TnmMgrade tnmMgrade = new TnmMgrade();
				tnmMgrade.setBaseCode("C1707005");
				tnmMgrade.setCode("C1707005");
				tnmMgrade.setGroupIndex(1);
				tnmMgrade.setPreferredTerm("Breast Cancer pM1 TNM Finding");
				tnmMgrade.setProvidingDepartment("Clinical");
				tnmMgrade.setSummarizableId(encounter.getId());
				tnmMgrade.setUnitOfMeasure("NA");
				tnmMgrade.setValue("M1");
				encounter.addSummary(tnmMgrade);
				
			}
		}
		
	}

	@Override
	public void setPatient(Patient patient) {
		this.patient = patient;
		
	}

	@Override
	public void setProjectLocation(String projectLocation) {
		// not used
		
	}
}
