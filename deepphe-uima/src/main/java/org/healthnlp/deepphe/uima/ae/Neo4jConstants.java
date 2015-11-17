package org.healthnlp.deepphe.uima.ae;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

public class Neo4jConstants {
	public enum Nodes implements Label {
		Patient,Document,Observation,Phenotype,Finding,Procedure,Medication,Diagnosis;
	}
	
	public enum Relationships implements RelationshipType {
		hasSubject;
	}
}
