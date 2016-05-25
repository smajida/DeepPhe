package org.healthnlp.deepphe.util;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * list of misc FHIR and ontology related constants
 * @author tseytlin
 */
public class FHIRConstants {
	
	public static final String NLP_CANCER_URL = "http://ontologies.dbmi.pitt.edu/deepphe/nlpCancer.owl";
	public static final String SCHEMA_URL = "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl";
	public static final String CONTEXT_URL = "http://blulab.chpc.utah.edu/ontologies/v2/ConText.owl";
	public static final String MODEL_CANCER_URL = NLP_CANCER_URL;
	//public static final String MODEL_CANCER_URL = "http://ontologies.dbmi.pitt.edu/deepphe/modelCancer.owl";
	
	public static final String ANNOTATION_TYPE_MENTION = "mention";
	public static final String ANNOTATION_TYPE_DOCUMENT = "document";
	public static final String ANNOTATION_TYPE_RECORD = "record";
	
	public static final String INTERPRETATION_POSITIVE = "Positive";
	public static final String INTERPRETATION_NEGATIVE = "Negative";
	
	public static final String ELEMENT = "Element";
	public static final String EVENT ="Event";
	public static final String COMPOSITION = "Composition";
	public static final String PATIENT = "Patient";
	public static final String TUMOR = "Tumor";
	public static final String CANCER = "Cancer";
	public static final String TUMOR_PHENOTYPE = "TumorPhenotype";
	public static final String CANCER_PHENOTYPE = "CancerPhenotype";
	public static final String PATIENT_PHENOTYPE = "PatientPhenotype";
	
	public static final String CONDITION = "Condition";
	public static final String DIAGNOSIS = "DiseaseDisorder";
	public static final String PROCEDURE = "Procedure";
	public static final String OBSERVATION = "Observation";
	public static final String FINDING = "Finding";
	public static final String MEDICATION = "MedicationStatement";
	public static final String BODY_SITE = "BodySite";
	public static final String TUMOR_SIZE = "Tumor_Size";
	public static final String QUANTITY = "Quantity";
	public static final String STAGE = "Generic_TNM_Finding";
	public static final String TNM_STAGE = "TNM_Staging_System";
	public static final String AGE = "Age";
	public static final String GENDER = "Gender";
	public static final String PHENOTYPIC_FACTOR = "PhenotypicFactor";
	public static final String GENOMIC_FACTOR = "GenomicFactor";
	public static final String TREATMENT_FACTOR = "TreatmentFactor";
	public static final String RELATED_FACTOR = "RelatedFactor";
	public static final String TREATMENT = "Treatment";
	public static final String MANIFISTATION = "ManifestationOfDisease";
	public static final String MEDICAL_RECORD = "MedicalRecord";
	public static final String BODY_MODIFIER = "BodyModifier";
	public static final String BODY_SIDE = "BodySide";
	public static final String ORDINAL_INTERPRETATION = "OrdinalInterpretation";
	public static final String QUADRANT = "Quadrant";
	public static final String CLOCKFACE_POSITION = "Clockface_position";
	public static final String UNIT = "Unit";
	
	
	public static final String HAS_BODY_SITE = "hasBodySite";
	public static final String HAS_BODY_SIDE = "hasBodySide";
	public static final String HAS_QUADRANT = "hasQuadrant";
	public static final String HAS_CLOCKFACE = "hasClockfacePosition";
	public static final String HAS_BODY_MODIFIER = "hasBodySiteModifier";
	public static final String HAS_TREATMENT = "hasTreatment";
	public static final String HAS_OUTCOME = "hasOutcome";
	public static final String HAS_CANCER_STAGE = "hasCancerStage";
	public static final String HAS_CANCER_TYPE = "hasCancerType";
	public static final String HAS_TUMOR_EXTENT = "hasTumorExtent";
	public static final String HAS_T_CLASSIFICATION = "hasGenericTClassification";
	public static final String HAS_N_CLASSIFICATION = "hasGenericNClassification";
	public static final String HAS_M_CLASSIFICATION = "hasGenericMClassification";
	public static final String HAS_TUMOR_TYPE = "hasTumorType";
	public static final String HAS_SEQUENCE_VARIENT = "hasSequenceVarient";
	public static final String HAS_HISTOLOGIC_TYPE = "hasHistologicType";
	public static final String HAS_MANIFESTATION = "hasManifestation";
	public static final String HAS_NAME = "hasName";
	public static final String HAS_GENDER = "hasGender";
	public static final String HAS_BIRTH_DATE = "hasBirthDate";
	public static final String HAS_DEATH_DATE = "hasDeathDate";
	public static final String HAS_INTERPRETATION = "hasInterpretation";
	public static final String HAS_NUM_VALUE = "hasNumValue";
	public static final String HAS_METHOD = "hasMethod";
	public static final String HAS_TNM_PREFIX = "hasTNMPrefix";
	public static final String HAS_TNM_SUFFIX = "hasTNMSuffix";
	
	public static final String T_STAGE = "T_Stage";
	public static final String M_STAGE = "M_Stage";
	public static final String N_STAGE = "N_Stage";
	
	// predefined URIs
	public static final URI PATIENT_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+PATIENT);
	public static final URI CANCER_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+CANCER);
	public static final URI TUMOR_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+TUMOR);
	public static final URI CANCER_PHENOTYPE_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+CANCER_PHENOTYPE);
	public static final URI TUMOR_PHENOTYPE_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+TUMOR_PHENOTYPE);

	public static final URI PATIENT_PHENOTYPE_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+PATIENT_PHENOTYPE);;
	public static final URI MEDICAL_RECORD_URI = URI.create(SCHEMA_URL+"#MedicalRecord");
	
	// mention level URIs
	public static final URI OBSERVATION_URI = URI.create(SCHEMA_URL+"#"+OBSERVATION);
	public static final URI FINDING_URI = URI.create(SCHEMA_URL+"#"+FINDING);
	public static final URI DIAGNOSIS_URI = URI.create(SCHEMA_URL+"#"+DIAGNOSIS);
	public static final URI PROCEDURE_URI = URI.create(SCHEMA_URL+"#"+PROCEDURE);
	public static final URI MEDICATION_URI = URI.create(SCHEMA_URL+"#"+MEDICATION);
	public static final URI BODY_SITE_URI = URI.create( CONTEXT_URL + "#" + BODY_SITE );
	public static final URI PATIENT_URI = URI.create(SCHEMA_URL+"#"+PATIENT);
	public static final URI TREATMENT_URI = URI.create(MODEL_CANCER_URL+"#"+TREATMENT);
	public static final URI MANIFISTATION_URI = URI.create(MODEL_CANCER_URL+"#"+MANIFISTATION);
	
	public static final URI HISTOLOGIC_TYPE_URI = URI.create(MODEL_CANCER_URL+"#HistologicType");
	public static final URI TUMOR_EXTENT_URI = URI.create(MODEL_CANCER_URL+"#TumorExtent");
	public static final URI CANCER_TYPE_URI = URI.create(MODEL_CANCER_URL+"#CancerType");
	
	
	public static final URI PRIMARY_TUMOR_URI = URI.create(MODEL_CANCER_URL+"#PrimaryTumor");
	public static final URI RECURRENT_TUMOR_URI = URI.create(MODEL_CANCER_URL+"#Recurrent_Tumor");
	//public static final URI TUMOR_SIZE_URI = URI.create(MODEL_CANCER_URL+"/"+TUMOR_PHENOTYPE);
	public static final URI MALE_URI = URI.create(MODEL_CANCER_URL+"#Male_Gender");
	public static final URI FEMALE_URI = URI.create(MODEL_CANCER_URL+"#Female_Gender");
/*	public static final String PATHOLOGY_REPORT_URI = URI.create(MODEL_CANCER_URL+"#Female_Gender");;
	public static final String RADIOLOGY_REPORT_URI = null;
	public static final String DISCHARGE_SUMMARY_URI = null;
	public static final String PROGRESS_NOTE_URI = null;*/
	public static final URI QUANTITY_URI = URI.create("http://blulab.chpc.utah.edu/ontologies/v2/ConText.owl#Quantity");
	public static final URI NUMERIC_MODIFIER_URI = URI.create("http://blulab.chpc.utah.edu/ontologies/v2/ConText.owl#NumericModifier");
	public static final URI GENERIC_TNM = URI.create(MODEL_CANCER_URL+"#TNM_Staging_System");

	public static final List<String> BODY_SIDE_LIST = Arrays.asList("Right","Left","Bilateral");
	public static final List<String> TNM_MODIFIER_LIST = Arrays.asList("p","c","y","r","sn");

	







	
}
