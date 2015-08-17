package org.healthnlp.deepphe.workbench;

import java.util.List;

import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import jess.Rete;

public interface PatientKnowledgeExtractorInterface {
	public void setPatients(List<Patient> patients);
	public void iteratePatients();
	public void nextPatient();
	public boolean hasMorePatients();
	public void loadSinglePatient();
	public void setJessEngine(Rete jessRete);	
	public void execute();
	public void clearJess();
	public void executeJess(String command);
	public void displayFacts();
	public void displayDeftemplates();
	public void setJessTextOutputer(JessTextOutputer outputer);
	public void loadProductionClipsFiles();
}
