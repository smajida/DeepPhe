package org.healthnlp.deepphe.workbench;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;

public interface EncounterKnowledgeExtractorInterface {
	public void executePatient(KbPatient kbPatient);
	public void executeEncounter(KbEncounter kbEncounter);
	public void setAnalysisEngine(AnalysisEngine ae);
}
