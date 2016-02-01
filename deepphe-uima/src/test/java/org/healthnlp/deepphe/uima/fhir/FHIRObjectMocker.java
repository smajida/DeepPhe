package org.healthnlp.deepphe.uima.fhir;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.CancerSummary.CancerPhenotype;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary.TumorPhenotype;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

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
	final CancerPhenotype cancerPhenotype = context.mock(CancerPhenotype.class);
	final TumorPhenotype tumorPhenotype = context.mock(TumorPhenotype.class);
	
	
	public FHIRObjectMocker() {
		super();
		init();
	}



	private void init(){
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
				will(returnValue(Collections.singletonList(bodySiteCC)));

				allowing(cancerSummary).getOutcomes();
				will(returnValue(Collections.singletonList(outcomesCC)));

				allowing(cancerSummary).getTreatments();
				will(returnValue(Collections.singletonList(treatmentCC)));

				allowing(cancerSummary).getPhenotypes();
				will(returnValue(Collections.singletonList(cancerPhenotype)));
				
				//Cancer Phenotype
				
				allowing(cancerPhenotype).getDisplayText();
				will(returnValue("Phenotype Summary"));
				
				allowing(cancerPhenotype).getResourceIdentifier();
				will(returnValue("PhenotypeSummary01"));
				
				allowing(cancerPhenotype).getSummaryText();
				will(returnValue("This is the phenotype summary"));
				
				allowing(cancerPhenotype).getConceptURI();
				will(returnValue(FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI));
				
				allowing(cancerPhenotype).getCancerStage();
				will(returnValue(cancerStageCC));
				
				allowing(cancerPhenotype).getCancerType();
				will(returnValue(cancerTypeCC));
				
				allowing(cancerPhenotype).getDistantMetastasisClassification();
				will(returnValue(distantMetastasisClassificationCC));
				
				allowing(cancerPhenotype).getManifestations();
				will(returnValue(Collections.singletonList(manifestationsCC)));
				
				allowing(cancerPhenotype).getPrimaryTumorClassification();
				will(returnValue(primaryTumorClassificationCC));
				
				allowing(cancerPhenotype).getRegionalLymphNodeClassification();
				will(returnValue(regionalLympNodeClassificationCC));
				
				allowing(cancerPhenotype).getTumorExtent();
				will(returnValue(tumorExtentCC));
				
				allowing(cancerSummary).getTumors();
				will(returnValue(Collections.singletonList(tumorSummary)));
				
				//Tumor Summary
				
				allowing(tumorSummary).getDisplayText();
				will(returnValue("Tumor Summary"));
				
				allowing(tumorSummary).getResourceIdentifier();
				will(returnValue("TumorSummary01"));
				
				allowing(tumorSummary).getSummaryText();
				will(returnValue("This is the tumor summary"));
				
				allowing(tumorSummary).getConceptURI();
				will(returnValue(FHIRConstants.TUMOR_SUMMARY_URI));
								
				allowing(tumorSummary).getBodySite();
				will(returnValue(Collections.singletonList(bodySiteCC)));

				allowing(tumorSummary).getOutcomes();
				will(returnValue(Collections.singletonList(outcomesCC)));

				allowing(tumorSummary).getTreatments();
				will(returnValue(Collections.singletonList(treatmentCC)));
				
				allowing(tumorSummary).getTumorSequenceVarients();
				will(returnValue(Collections.singletonList(tumorSequenceVariantsCC)));
				
				allowing(tumorSummary).getTumorType();
				will(returnValue(tumorTypeCC));

				allowing(tumorSummary).getPhenotype();
				will(returnValue(tumorPhenotype));
				
				//Tumor Phenotype
				
				allowing(tumorPhenotype).getDisplayText();
				will(returnValue("Tumor Phenotype"));
				
				allowing(tumorPhenotype).getResourceIdentifier();
				will(returnValue("TumorPhenotype01"));
				
				allowing(tumorPhenotype).getSummaryText();
				will(returnValue("This is the tumor Phenotype"));
				
				allowing(tumorPhenotype).getConceptURI();
				will(returnValue(FHIRConstants.TUMOR_PHENOTYPE_SUMMARY_URI));
				
				allowing(tumorPhenotype).getHistologicTypes();
				will(returnValue(Collections.singletonList(manifestationsCC)));
				
				allowing(tumorPhenotype).getManifestations();
				will(returnValue(Collections.singletonList(histologicTypesCC)));				
				
				allowing(tumorPhenotype).getTumorExtent();
				will(returnValue(Collections.singletonList(tumorExtentCC)));
				
				//Patient Summary
				allowing(patientSummary).getDisplayText();
				will(returnValue("Patient Summary"));
				
				allowing(patientSummary).getResourceIdentifier();
				will(returnValue("PatientSummary01"));
				
				allowing(patientSummary).getSummaryText();
				will(returnValue("This is the patient summary"));
				
				allowing(patientSummary).getConceptURI();
				will(returnValue(FHIRConstants.PATIENT_SUMMARY_URI));
				
				allowing(patientSummary).getExposure();
				will(returnValue(Collections.singletonList(exposureCC)));
				
				allowing(patientSummary).getGermlineSequenceVariant();
				will(returnValue(Collections.singletonList(germlineSequenceVariantCC)));
				
				allowing(patientSummary).getOutcomes();
				will(returnValue(Collections.singletonList(outcomesCC)));
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
	
	
	
}
