package org.healthnlp.deepphe.workbench;

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.uima.pipelines.CtakesCancerEncounterPipeBuilder;
import org.healthnlp.deepphe.xfer.CtakesToDroolsConverter;

public class EncounterKnowledgeExtractorCtakes implements
		EncounterKnowledgeExtractorInterface {

	private KbPatient patient;
	private AnalysisEngine ae = null;
	
	@Override
	public void setPatient(KbPatient patient) {
		this.patient = patient;
	}

	@Override
	public void execute() {
		try {
			if (patient != null && patient.getEncounters().size() > 0) {
				buildCtakesAnalysisEngine();
				for (KbEncounter encounter : patient.getEncounters()) {
					JCas encounterJCas = ae.newJCas();
					String documentText = encounter.getContent();
					encounterJCas.setDocumentText(documentText);
					SimplePipeline.runPipeline(encounterJCas, ae);
					CtakesToDroolsConverter ctakesToJessConverter = new CtakesToDroolsConverter();
					encounter.clearSummaries();			
					ctakesToJessConverter.getSummariesForSummarizable(encounter, encounterJCas);
				}
				patient.clearSummaries();  // Patient summaries must be recomputed
			}
		} catch (ResourceInitializationException | InvalidXMLException
				| IOException | AnalysisEngineProcessException e) {
			e.printStackTrace();
		}

	}

	private void buildCtakesAnalysisEngine()
			throws ResourceInitializationException, InvalidXMLException,
			IOException {
		if (ae == null) {
			try {
				ae = AnalysisEngineFactory
						.createEngine(CtakesCancerEncounterPipeBuilder
								.getPipelineDescription());
			} catch (InvalidXMLException | IOException fnf) {
				throw new ResourceInitializationException(fnf);
			}
		}
	}

	@Override
	public void setProjectLocation(String projectLocation) {
		// currently not used for cTAKES

	}

}
