package org.healthnlp.deepphe.workbench;

import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;

public interface EncounterKnowledgeExtractorInterface {
	public void setProjectLocation(String projectLocation);
	public void setPatient(KbPatient patient);
	public void execute();
	
}
