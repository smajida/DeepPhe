package org.apache.ctakes.cancer.owl;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/16/2016
 */
public interface OwlConstants {

   String ROOT_ELEMENT_NAME = "Annotation";
   String MODIFIER_ELEMENT_NAME = "Modifier";

   String CONTEXT_OWL = "http://blulab.chpc.utah.edu/ontologies/v2/ConText.owl";
   String SCHEMA_OWL = "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl";
   String CANCER_OWL = "http://ontologies.dbmi.pitt.edu/deepphe/nlpCancer.owl";
   String BREAST_CANCER_OWL = "http://ontologies.dbmi.pitt.edu/deepphe/nlpBreastCancer.owl";

   String UNKNOWN_URI = SCHEMA_OWL + "#" + ROOT_ELEMENT_NAME;
   String MEDICATION_URI = SCHEMA_OWL + "#MedicationStatement";
   String PROCEDURE_URI = SCHEMA_OWL + "#Procedure";
   String SIGN_SYMPTOM_URI = SCHEMA_OWL + "#SignSymptom";
   String DISEASE_DISORDER_URI = SCHEMA_OWL + "#DiseaseDisorder";


}
