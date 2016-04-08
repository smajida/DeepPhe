package org.healthnlp.deepphe.uima.fhir;

import org.healthnlp.deepphe.fhir.*;
import org.healthnlp.deepphe.fhir.fact.DefaultFactList;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.summary.*;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

import java.util.Arrays;
import java.util.Collections;

public class FHIRObjectMocker {
	private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
	final Report report = context.mock(Report.class);
	final Patient patient = context.mock(Patient.class);
	final Disease disease = context.mock(Disease.class);
	final Procedure procedure = context.mock(Procedure.class);
	final Finding finding = context.mock(Finding.class);
	final Observation observation = context.mock(Observation.class);
	final Medication medication = context.mock(Medication.class);
	final CancerSummary cancerSummary = context.mock(CancerSummary.class);
	final TumorSummary tumorSummary = context.mock(TumorSummary.class);
	final PatientSummary patientSummary = context.mock(PatientSummary.class);
	final PatientPhenotype patientPhenotype = context.mock(PatientPhenotype.class);
	final CancerPhenotype cancerPhenotype = context.mock(CancerPhenotype.class);
	final TumorPhenotype tumorPhenotype = context.mock(TumorPhenotype.class);
	final MedicalRecord medicalRecord = context.mock(MedicalRecord.class);
	
	public FHIRObjectMocker() {
		super();
		init();
	}



	private void init(){
		final DefaultFactList bodySiteFL = new DefaultFactList();
		final DefaultFactList outcomesFL = new DefaultFactList();
		final DefaultFactList treatmentFL = new DefaultFactList();
		final DefaultFactList cancerStageFL = new DefaultFactList();
		final DefaultFactList cancerTypeFL = new DefaultFactList();
		final DefaultFactList distantMetastasisClassificationFL = new DefaultFactList();
		final DefaultFactList manifestationsFL = new DefaultFactList();
		final DefaultFactList primaryTumorClassificationFL = new DefaultFactList();
		final DefaultFactList regionalLympNodeClassificationFL = new DefaultFactList();
		final DefaultFactList tumorExtentFL = new DefaultFactList();
		final DefaultFactList tumorSequenceVariantsFL = new DefaultFactList();
		final DefaultFactList tumorTypeFL = new DefaultFactList();
		final DefaultFactList histologicTypesFL = new DefaultFactList();
		final DefaultFactList germlineSequenceVariantFL = new DefaultFactList();
		final DefaultFactList exposureFL = new DefaultFactList();
		final DefaultFactList stageFL = new DefaultFactList();
		final DefaultFactList genderFL = new DefaultFactList();
		final DefaultFactList birthDateFL = new DefaultFactList();
		final DefaultFactList deathDateFL = new DefaultFactList();
		final DefaultFactList nameFL = new DefaultFactList();

		final CodeableConcept bodySiteCC = context.mock(CodeableConcept.class,"bodySiteCC");
		final CodeableConcept outcomesCC = context.mock(CodeableConcept.class,"outcomesCC");
		final CodeableConcept treatmentCC = context.mock(CodeableConcept.class,"treatmentCC");
		final CodeableConcept cancerStageCC = context.mock(CodeableConcept.class,"cancerStageCC");
		final CodeableConcept cancerTypeCC = context.mock(CodeableConcept.class,"cancerTypeCC");
		final CodeableConcept distantMetastasisClassificationCC = context.mock(CodeableConcept.class,"distantMetastasisClassificationCC");
		final CodeableConcept manifestationsCC = context.mock(CodeableConcept.class,"manifestationsCC");
		final CodeableConcept primaryTumorClassificationCC = context.mock(CodeableConcept.class,"primaryTumorClassificationCC");
		final CodeableConcept regionalLympNodeClassificationCC = context.mock(CodeableConcept.class,"regionalLympNodeClassificationCC");
		final CodeableConcept tumorExtentCC = context.mock(CodeableConcept.class,"tumorExtentCC");
		final CodeableConcept tumorSequenceVariantsCC = context.mock(CodeableConcept.class,"tumorSequenceVariantsCC");
		final CodeableConcept tumorTypeCC = context.mock(CodeableConcept.class,"tumorTypeCC");
		final CodeableConcept histologicTypesCC = context.mock(CodeableConcept.class,"histologicTypesCC");
		final CodeableConcept germlineSequenceVariantCC = context.mock(CodeableConcept.class,"germlineSequenceVariantCC");
		final CodeableConcept exposureCC = context.mock(CodeableConcept.class,"exposureCC");
		final CodeableConcept stageCC = context.mock(CodeableConcept.class,"stagecc");
		
		final Stage stage = context.mock(Stage.class);
		
		
		context.checking(new Expectations() {
			{
				//Patient
				allowing(patient).getPatientName();
				will(returnValue("Patient01"));
				
				//Report
				allowing(report).getTitle();
				will(returnValue("Document01"));
				
				allowing(report).getReportText();
				will(returnValue("Patient is a 54 year old female with history of T2N0M0 left breast cancer ER-neg,\n"
						+ "PR-neg, HER2+, now undergoing neoadjuvant chemo with taxotere, carboplatin, Herceptin, and pertuzumab."));


				allowing(report).getPatient();
				will(returnValue(patient));

				allowing(report).getCompositionSummaries();
				will(returnValue(Arrays.asList(new Summary[]{cancerSummary,tumorSummary,patientSummary})));
				
				allowing(report).getDiagnoses();
				will(returnValue(Collections.singletonList(disease)));
				
				allowing(report).getFindings();
				will(returnValue(Collections.singletonList(finding)));
				
				allowing(report).getProcedures();
				will(returnValue(Collections.singletonList(procedure)));
				
				allowing(report).getObservations();
				will(returnValue(Collections.singletonList(observation)));
				
				allowing(report).getMedications();
				will(returnValue(Collections.singletonList(medication)));

				allowing(report).getCompositionSummaries();
				will(returnValue(Arrays.asList(new Summary[]{cancerSummary,patientSummary,tumorSummary})));

				allowing(report).getCancerSummaries();
				will(returnValue(Collections.singletonList(cancerSummary)));

				allowing(report).getTumorSummaries();
				will(returnValue(patientSummary));

				allowing(report).getPatientSummary();
				will(returnValue(Collections.singletonList(medication)));


				//Stage
				allowing(stage).getSummary();
				will(returnValue(stageCC));
				
				allowing(bodySiteCC).getText();
				will(returnValue("left breast"));
				allowing(outcomesCC).getText();
				will(returnValue("disease free survival"));
				allowing(treatmentCC).getText();
				will(returnValue("neoadjuvant chemo therapy"));
				allowing(stageCC).getText();
				will(returnValue("T2N0M0 Stage I"));				
				allowing(cancerStageCC).getText();
				will(returnValue("Stage I"));
				allowing(cancerTypeCC).getText();
				will(returnValue("adenocarcinoma"));
				allowing(distantMetastasisClassificationCC).getText();
				will(returnValue("M0"));
				allowing(manifestationsCC).getText();
				will(returnValue("tumor size 3mm"));
				allowing(primaryTumorClassificationCC).getText();
				will(returnValue("T2"));
				allowing(regionalLympNodeClassificationCC).getText();
				will(returnValue("N0"));
				allowing(tumorExtentCC).getText();
				will(returnValue("insitu"));
				allowing(tumorSequenceVariantsCC).getText();
				will(returnValue("tumorSequenceVariant"));
				allowing(tumorTypeCC).getText();
				will(returnValue("primary"));
				allowing(histologicTypesCC).getText();
				will(returnValue("ductal"));
				allowing( germlineSequenceVariantCC).getText();
				will(returnValue("BRCA-1"));
				allowing(exposureCC).getText();
				will(returnValue("asbestos"));
				
				//Disease
				allowing(disease).getDisplayText();
				will(returnValue("Malignant Breast Neoplasm"));
				
				allowing(disease).getBodySite();
				will(returnValue(Collections.singletonList(bodySiteCC)));
				
				allowing(disease).getStage();
				will(returnValue(stage));
				
				//Procedure
				allowing(procedure).getDisplayText();
				will(returnValue("Biopsy"));
				
				allowing(procedure).getBodySite();
				will(returnValue(Collections.singletonList(bodySiteCC)));
				
				//Finding
				allowing(finding).getDisplayText();
				will(returnValue("Finding Text"));
				
				//Observation
				allowing(observation).getDisplayText();
				will(returnValue("Estrogen Receptor Status"));
				
				allowing(observation).getObservationValue();
				will(returnValue("Negative"));
				
				allowing(observation).getBodySite();
				will(returnValue(bodySiteCC));
				
				//Medication
				allowing(medication).getDisplayText();
				will(returnValue("Taxotere"));
				
				//CancerSummary

				allowing(cancerSummary).getDisplayText();
				will(returnValue("Cancer Summary"));
				
				allowing(cancerSummary).getResourceIdentifier();
				will(returnValue("CancerSummary01"));
				
				allowing(cancerSummary).getSummaryText();
				will(returnValue("This is the cancer summary"));
				
				allowing(cancerSummary).getConceptURI();
				will(returnValue(FHIRConstants.CANCER_SUMMARY_URI));
				
				allowing(cancerSummary).getBodySite();
				will(returnValue(bodySiteFL));

				allowing(cancerSummary).getOutcomes();
				will(returnValue(outcomesFL));

				allowing(cancerSummary).getTreatments();
				will(returnValue(treatmentFL));

				allowing(cancerSummary).getPhenotypes();
				will(returnValue(Collections.singletonList(cancerPhenotype)));

				allowing(cancerSummary).getTumors();
				will(returnValue(Collections.singletonList(tumorSummary)));

				allowing(cancerSummary).getReport();
				will(returnValue(report));

				allowing(cancerSummary).getPatient();
				will(returnValue(patient));

				allowing(cancerSummary).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));

				//Cancer Phenotype
				allowing(cancerPhenotype).getPatient();
				will(returnValue(patient));

				allowing(cancerPhenotype).getReport();
				will(returnValue(report));
				
				allowing(cancerPhenotype).getDisplayText();
				will(returnValue("Phenotype Summary"));
				
				allowing(cancerPhenotype).getResourceIdentifier();
				will(returnValue("PhenotypeSummary01"));
				
				allowing(cancerPhenotype).getSummaryText();
				will(returnValue("This is the phenotype summary"));
				
				allowing(cancerPhenotype).getConceptURI();
				will(returnValue(FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI));
				
				allowing(cancerPhenotype).getCancerStage();
				will(returnValue(cancerStageFL));
				
				allowing(cancerPhenotype).getCancerType();
				will(returnValue(cancerTypeFL));
				
				allowing(cancerPhenotype).getDistantMetastasisClassification();
				will(returnValue(distantMetastasisClassificationFL));
				
				allowing(cancerPhenotype).getPrimaryTumorClassification();
				will(returnValue(primaryTumorClassificationFL));
				
				allowing(cancerPhenotype).getRegionalLymphNodeClassification();
				will(returnValue(regionalLympNodeClassificationFL));
				
				allowing(cancerPhenotype).getTumorExtent();
				will(returnValue(tumorExtentFL));

				allowing(cancerPhenotype).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));

				//Tumor Summary
				allowing(tumorSummary).getPatient();
				will(returnValue(patient));

				allowing(tumorSummary).getReport();
				will(returnValue(report));

				allowing(tumorSummary).getDisplayText();
				will(returnValue("Tumor Summary"));
				
				allowing(tumorSummary).getResourceIdentifier();
				will(returnValue("TumorSummary01"));
				
				allowing(tumorSummary).getSummaryText();
				will(returnValue("This is the tumor summary"));
				
				allowing(tumorSummary).getConceptURI();
				will(returnValue(FHIRConstants.TUMOR_SUMMARY_URI));
								
				allowing(tumorSummary).getBodySite();
				will(returnValue(bodySiteFL));

				allowing(tumorSummary).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));

				allowing(tumorSummary).getOutcome();
				will(returnValue(outcomesFL));

				allowing(tumorSummary).getTreatment();
				will(returnValue(treatmentFL));
				
				allowing(tumorSummary).getSequenceVariants();
				will(returnValue(tumorSequenceVariantsFL));
				
				allowing(tumorSummary).getTumorType();
				will(returnValue(tumorTypeFL));

				allowing(tumorSummary).getPhenotype();
				will(returnValue(tumorPhenotype));
				
				//Tumor Phenotype
				allowing(tumorPhenotype).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));

				allowing(tumorPhenotype).getReport();
				will(returnValue(report));

				allowing(tumorPhenotype).getPatient();
				will(returnValue(patient));

				allowing(tumorPhenotype).getDisplayText();
				will(returnValue("Tumor Phenotype"));
				
				allowing(tumorPhenotype).getResourceIdentifier();
				will(returnValue("TumorPhenotype01"));
				
				allowing(tumorPhenotype).getSummaryText();
				will(returnValue("This is the tumor Phenotype"));
				
				allowing(tumorPhenotype).getConceptURI();
				will(returnValue(FHIRConstants.TUMOR_PHENOTYPE_SUMMARY_URI));
				
				allowing(tumorPhenotype).getHistologicTypes();
				will(returnValue(histologicTypesFL));
				
				allowing(tumorPhenotype).getManifestations();
				will(returnValue(manifestationsFL));
				
				allowing(tumorPhenotype).getTumorExtent();
				will(returnValue(tumorExtentFL));

				//Patient Phenotype
				allowing(patientPhenotype).getReport();
				will(returnValue(report));

				allowing(patientPhenotype).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));


				allowing(patientPhenotype).getPatient();
				will(returnValue(patient));

				allowing(patientPhenotype).getDisplayText();
				will(returnValue("Patient Phenotype"));

				allowing(patientPhenotype).getResourceIdentifier();
				will(returnValue("PatientPhenotype01"));

				allowing(patientPhenotype).getSummaryText();
				will(returnValue("This is the Patient Phenotype"));

				allowing(patientPhenotype).getConceptURI();
				will(returnValue(FHIRConstants.PATIENT_PHENOTYPE_SUMMARY_URI));
				//Patient Summary
				allowing(patientSummary).getPatient();
				will(returnValue(patient));

				allowing(patientSummary).getReport();
				will(returnValue(report));

				allowing(patientSummary).getName();
				will(returnValue(nameFL));

				allowing(patientSummary).getDisplayText();
				will(returnValue("Patient Summary"));
				
				allowing(patientSummary).getResourceIdentifier();
				will(returnValue("PatientSummary01"));

				allowing(patientSummary).getSummaryText();
				will(returnValue("This is the patient summary"));

				allowing(patientSummary).getPhenotype();
				will(returnValue(patientPhenotype));
				
				allowing(patientSummary).getConceptURI();
				will(returnValue(FHIRConstants.PATIENT_SUMMARY_URI));
				
				allowing(patientSummary).getOutcomes();
				will(returnValue(outcomesFL));

				allowing(patientSummary).getSequenceVariant();
				will(returnValue(tumorSequenceVariantsFL));

				allowing(patientSummary).getGender();
				will(returnValue(genderFL));

				allowing(patientSummary).getBirthDate();
				will(returnValue(birthDateFL));

				allowing(patientSummary).getDeathDate();
				will(returnValue(deathDateFL));

				allowing(patientSummary).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));

				//Medical Record


				allowing(medicalRecord).getDisplayText();
				will(returnValue("Generic Medical Record"));

				allowing(medicalRecord).getSummaryText();
				will(returnValue("Medical Record Summary"));

				allowing(medicalRecord).getPatientIdentifier();
				will(returnValue("patientIdentifier"));

				allowing(medicalRecord).getConceptURI();
				will(returnValue(FHIRConstants.MEDICAL_RECORD_URI));

				allowing(medicalRecord).getCode();
				will(returnValue(FHIRUtils.getCodeableConcept(FHIRConstants.MEDICAL_RECORD_URI)));

				allowing(medicalRecord).getAnnotationType();
				will(returnValue(FHIRConstants.ANNOTATION_TYPE_RECORD));

				allowing(medicalRecord).getPatient();
				will(returnValue(patient));

				allowing(medicalRecord).getPatientSummary();
				will(returnValue(patientSummary));
				
				allowing(medicalRecord).getCancerSummary();
				will(returnValue(cancerSummary));

				allowing(medicalRecord).getReports();
				will(returnValue(Collections.singletonList(report)));
			}
		});
	}



	public Report getReport() {
		return report;
	}



	public Patient getPatient() {
		return patient;
	}




	public Disease getDiagnosis() {
		return disease;
	}



	public Procedure getProcedure() {
		return procedure;
	}



	public Finding getFinding() {
		return finding;
	}



	public Observation getObservation() {
		return observation;
	}



	public Medication getMedication() {
		return medication;
	}



	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}


	public CancerSummary getCancerSummary() {
		return cancerSummary;
	}
}
