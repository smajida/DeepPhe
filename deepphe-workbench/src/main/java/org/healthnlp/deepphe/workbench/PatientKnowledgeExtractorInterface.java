package org.healthnlp.deepphe.workbench;

import java.util.List;

import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;

public interface PatientKnowledgeExtractorInterface {
	public void setPatients(List<KbPatient> patients);
	public void iteratePatients();
	public void nextPatient();
	public boolean hasMorePatients();
	public void execute();
	public void loadSinglePatient();
	public void setKnowledgeBase(DroolsKnowledgeBaseAndSession engine);	
	public void setDroolsTextOutputer(DroolsTextOutputer outputer);
}
