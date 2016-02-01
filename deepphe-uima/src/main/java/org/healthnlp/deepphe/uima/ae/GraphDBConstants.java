package org.healthnlp.deepphe.uima.ae;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

public class GraphDBConstants {
	public enum Nodes implements Label {
		CancerPhenotype,TumorPhenotype,CancerSummary,PatientSummary,TumorSummary,Patient,Document,Observation,Phenotype,Finding,Procedure,Medication,Diagnosis;
	}
	
	public enum Relationships implements RelationshipType {
		hasCancerPhenotype,hasTumorPhenotype,hasCancerSummary,hasPatientSummary,hasTumorSummary,hasSubject,hasDiagnosis,hasProcedure,hasFinding,hasObservation,hasMedication;
	}
}
