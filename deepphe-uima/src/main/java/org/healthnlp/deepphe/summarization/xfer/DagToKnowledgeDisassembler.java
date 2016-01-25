package org.healthnlp.deepphe.summarization.xfer;

import java.util.HashMap;

import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;

public class DagToKnowledgeDisassembler {

	private HashMap<Integer, KbIdentified> knowledgeMap;
	private KbPatient patient;

	public void execute() {
		
		knowledgeMap.clear();
		knowledgeMap.put(patient.getId(), patient);
		for (KbSummary summary : patient.getSummaries()) {
			knowledgeMap.put(summary.getId(), summary);
		}
		for (KbEncounter encounter : patient.getEncounters()) {
			knowledgeMap.put(encounter.getId(), encounter);
			for (KbSummary summary : encounter.getSummaries()) {
				knowledgeMap.put(summary.getId(), summary);
			}
		}
	}
	
//	RelationHassemanticAttribute rel = new RelationHassemanticAttributeImpl();
//	if (RelationHassemanticAttribute.class.isAssignableFrom(summary.getClass())) {
//		int domainId = ((RelationHassemanticAttribute) summary).getDomainId();
//		int rangeId = ((RelationHassemanticAttribute) summary).getRangeId();
//		KbIdentified domainObj = knowledgeMap.get(domainId);
//		KbIdentified rangeObj = knowledgeMap.get(rangeId);
//		
//	}
	
	public KbPatient getPatient() {
		return patient;
	}

	public void setPatient(KbPatient patient) {
		this.patient = patient;
	}


	public HashMap<Integer, KbIdentified> getKnowledgeMap() {
		return knowledgeMap;
	}

	public void setKnowledgeMap(HashMap<Integer, KbIdentified> knowledgeMap) {
		this.knowledgeMap = knowledgeMap;
	}

}
