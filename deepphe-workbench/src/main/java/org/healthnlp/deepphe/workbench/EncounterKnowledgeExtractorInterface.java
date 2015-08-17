package org.healthnlp.deepphe.workbench;

import org.healthnlp.deepphe.summarization.jess.kb.Patient;

public interface EncounterKnowledgeExtractorInterface {
	public void setProjectLocation(String projectLocation);
	public void setPatient(Patient patient);
	public void execute();
	
}
