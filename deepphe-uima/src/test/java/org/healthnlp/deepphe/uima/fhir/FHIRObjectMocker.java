package org.healthnlp.deepphe.uima.fhir;

import java.util.Collections;

import org.healthnlp.deepphe.fhir.Diagnosis;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
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
	final Diagnosis diagnosis = context.mock(Diagnosis.class);
	final Procedure procedure = context.mock(Procedure.class);
	final Finding finding = context.mock(Finding.class);
	final Observation observation = context.mock(Observation.class);
	final Medication medication = context.mock(Medication.class);
	
	
	
	public FHIRObjectMocker() {
		super();
		init();
	}



	private void init(){
		final CodeableConcept bodySiteCC = context.mock(CodeableConcept.class,"bodysitecc");
		
		final Stage stage = context.mock(Stage.class);
		final CodeableConcept stageCC = context.mock(CodeableConcept.class,"stagecc");
		
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
				
				allowing(report).getDiagnoses();
				will(returnValue(Collections.singletonList(diagnosis)));
				
				allowing(report).getFindings();
				will(returnValue(Collections.singletonList(finding)));
				
				allowing(report).getProcedures();
				will(returnValue(Collections.singletonList(procedure)));
				
				allowing(report).getObservations();
				will(returnValue(Collections.singletonList(observation)));
				
				allowing(report).getMedications();
				will(returnValue(Collections.singletonList(medication)));
				
				//Body Site CC
				allowing(bodySiteCC).getText();
				will(returnValue("left breast"));
				//Stage
				allowing(stage).getSummary();
				will(returnValue(stageCC));
				//Stage CC
				allowing(stageCC).getText();
				will(returnValue("T2N0M0"));
				
				//Diagnosis
				allowing(diagnosis).getDisplayText();
				will(returnValue("Malignant Breast Neoplasm"));
				
				allowing(diagnosis).getBodySite();
				will(returnValue(Collections.singletonList(bodySiteCC)));
				
				allowing(diagnosis).getStage();
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
				
			}
		});
	}



	public Report getReport() {
		return report;
	}



	public Patient getPatient() {
		return patient;
	}



	public Diagnosis getDiagnosis() {
		return diagnosis;
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
