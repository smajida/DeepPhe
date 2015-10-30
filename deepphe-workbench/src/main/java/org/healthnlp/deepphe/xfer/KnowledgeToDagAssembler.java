package org.healthnlp.deepphe.xfer;

import java.util.HashMap;
import java.util.List;

import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummarizable;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;

public class KnowledgeToDagAssembler {
	
	private KbPatient patient;
	private HashMap<Integer, KbIdentified> knowledgeMap = new HashMap<>();
	
	public void execute() {
		clearPatientAssembly();
		clearSummarizableAssembly();
		assemblePatientEncounters();
		assembleSummaries();
	}
	
	private void clearPatientAssembly() {
		for (KbIdentified identified : knowledgeMap.values()) {
			if (KbPatient.class.isInstance(identified)) {
				patient = (KbPatient) identified;
				patient.clearEncounters();
			}
		}
	}
	
	private void clearSummarizableAssembly() {
		for (KbIdentified identified : knowledgeMap.values()) {
			if (KbSummarizable.class.isInstance(identified)) {
				KbSummarizable summarizable = (KbSummarizable) identified;
				summarizable.clearSummaries();
			}
		}
	}

	private void assemblePatientEncounters() {
		for (KbIdentified identified : knowledgeMap.values()) {
			if (KbEncounter.class.isInstance(identified)) {
				KbEncounter encounter = (KbEncounter) identified;
				KbPatient patient = (KbPatient) knowledgeMap.get(encounter.getPatientId());
				patient.addEncounter(encounter);
			}
		}
	}

	private void assembleSummaries() {
		for (KbIdentified identified : knowledgeMap.values()) {
			if (KbSummary.class.isInstance(identified)) {
				KbSummary summary = (KbSummary) identified;
				KbSummarizable summarizable = (KbSummarizable) knowledgeMap.get(summary.getSummarizableId());
				if (summarizable == null) {
					System.err.println("Unmapped Summary found ==> \n" + summary.toString() );
				}
				summarizable.addSummary(summary);
			}
		}
	}
	
	public void add(KbIdentified identified) {
		knowledgeMap.put(identified.getId(), identified);
	}
	
	public void addAll(List<KbIdentified> identifiedsForMapping) {
		for (KbIdentified identified : identifiedsForMapping) {
			knowledgeMap.put(identified.getId(), identified);
		}
	}

	public KbPatient getPatient() {
		return patient;
	}

	public void setPatient(KbPatient patient) {
		this.patient = patient;
	}

}
