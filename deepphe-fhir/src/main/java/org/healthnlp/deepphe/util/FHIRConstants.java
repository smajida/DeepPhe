package org.healthnlp.deepphe.util;

import java.net.URI;

/**
 * list of misc FHIR and ontology related constants
 * @author tseytlin
 */
public class FHIRConstants {
	
	public static final String NLP_CANCER_URL = "http://ontologies.dbmi.pitt.edu/deepphe/nlpCancer.owl";
	public static final String SCHEMA_URL = "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl";
	public static final String MODEL_CANCER_URL = NLP_CANCER_URL;
	//public static final String MODEL_CANCER_URL = "http://ontologies.dbmi.pitt.edu/deepphe/modelCancer.owl";
	
	public static final String ANNOTATION_TYPE_MENTION = "mention";
	public static final String ANNOTATION_TYPE_DOCUMENT = "document";
	public static final String ANNOTATION_TYPE_RECORD = "record";
	
	public static final String INTERPRETATION_POSITIVE = "Positive";
	public static final String INTERPRETATION_NEGATIVE = "Negative";
	
	public static final String ELEMENT = "Element";
	public static final String COMPOSITION = "Composition";
	public static final String PATIENT = "Patient";
	public static final String TUMOR = "Tumor";
	public static final String CANCER = "Cancer";
	public static final String TUMOR_PHENOTYPE = "TumorPhenotype";
	public static final String CANCER_PHENOTYPE = "CancerPhenotype";
	
	public static final String DIAGNOSIS = "DiseaseDisorder";
	public static final String PROCEDURE = "Procedure";
	public static final String OBSERVATION = "Observation";
	public static final String FINDING = "Finding";
	public static final String MEDICATION = "MedicationStatement";
	public static final String BODY_SITE = "BodySite";
	public static final String TUMOR_SIZE = "Tumor_Size";
	public static final String STAGE = "Generic_TNM_Finding";
	public static final String AGE = "Age";
	public static final String GENDER = "Gender";
	public static final String PHENOTYPIC_FACTOR = "PhenotypicFactor";
	public static final String GENOMIC_FACTOR = "GenomicFactor";
	public static final String TREATMENT_FACTOR = "TreatmentFactor";
	public static final String RELATED_FACTOR = "RelatedFactor";
	public static final String TREATMENT = "Treatment";
	public static final String MANIFISTATION = "ManifestationOfDisease";
	
	
	public static final String T_STAGE = "Generic_Primary_Tumor_TNM_Finding";
	public static final String M_STAGE = "Generic_Distant_Metastasis_TNM_Finding";
	public static final String N_STAGE = "Generic_Regional_Lymph_Nodes_TNM_Finding";
	
	// predefined URIs
	public static final URI PATIENT_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+PATIENT);
	public static final URI CANCER_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+CANCER);
	public static final URI TUMOR_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+TUMOR);
	public static final URI CANCER_PHENOTYPE_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+CANCER_PHENOTYPE);
	public static final URI TUMOR_PHENOTYPE_SUMMARY_URI = URI.create(MODEL_CANCER_URL+"#"+TUMOR_PHENOTYPE);
	public static final URI MEDICAL_RECORD_URI = URI.create(SCHEMA_URL+"#MedicalRecord");
	
	// mention level URIs
	public static final URI OBSERVATION_URI = URI.create(SCHEMA_URL+"#"+OBSERVATION);
	public static final URI FINDING_URI = URI.create(SCHEMA_URL+"#"+FINDING);
	public static final URI DIAGNOSIS_URI = URI.create(SCHEMA_URL+"#"+DIAGNOSIS);
	public static final URI PROCEDURE_URI = URI.create(SCHEMA_URL+"#"+PROCEDURE);
	public static final URI MEDICATION_URI = URI.create(SCHEMA_URL+"#"+MEDICATION);
	public static final URI BODY_SITE_URI = URI.create(SCHEMA_URL+"#"+BODY_SITE);
	public static final URI PATIENT_URI = URI.create(SCHEMA_URL+"#"+PATIENT);
	public static final URI TREATMENT_URI = URI.create(MODEL_CANCER_URL+"#"+TREATMENT);
	public static final URI MANIFISTATION_URI = URI.create(MODEL_CANCER_URL+"#"+MANIFISTATION);
	
	public static final URI HISTOLOGIC_TYPE_URI = URI.create(MODEL_CANCER_URL+"#HistologicType");
	public static final URI TUMOR_EXTENT_URI = URI.create(MODEL_CANCER_URL+"#TumorExtent");
	public static final URI CANCER_TYPE_URI = URI.create(MODEL_CANCER_URL+"#CancerType");
	
	
	public static final URI PRIMARY_TUMOR_URI = URI.create(MODEL_CANCER_URL+"#PrimaryTumor");
	public static final URI RECURRENT_TUMOR_URI = URI.create(MODEL_CANCER_URL+"#Recurrent_Tumor");
	//public static final URI TUMOR_SIZE_URI = URI.create(MODEL_CANCER_URL+"/"+TUMOR_PHENOTYPE);
	
}
