package org.healthnlp.deepphe.workbench;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ctakes.cancer.ae.XMISerializer;
import org.apache.ctakes.cancer.pipeline.CancerPipelineFactory;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.xfer.CtakesToDroolsConverter;
import org.xml.sax.SAXException;

public class EncounterKnowledgeExtractorCtakes implements
		EncounterKnowledgeExtractorInterface {

	private AnalysisEngine ae = null;

	public EncounterKnowledgeExtractorCtakes() {
	}

	public void executePatient(KbPatient kbPatient) {
		for (KbEncounter kbEncounter : kbPatient.getEncounters()) {
			executeEncounter(kbEncounter);
		}
		kbPatient.clearSummaries(); // Patient summaries must be
		// recomputed
	}

	//
	// Runs pipe over encounter note, xmi serialized the results,
	// stores the xmi serialized results in the encounter path attribute
	//
	@Override
	public void executeEncounter(KbEncounter kbEncounter) {
		try {
			buildCtakesAnalysisEngine();
			JCas encounterJCas = ae.newJCas();
			runCtakesPipeline(encounterJCas, kbEncounter);
			cacheSerializedCasXmi(encounterJCas, kbEncounter);
			clearDerivedSummaries(kbEncounter);
			pullCasToDag(encounterJCas, kbEncounter);
		} catch (IOException | SAXException | UIMAException e) {
			e.printStackTrace();
		}
	}
	
	private void pullCasToDag(JCas encounterJCas, KbEncounter kbEncounter) {
		CtakesToDroolsConverter ctakesToDroolsConverter = CtakesToDroolsConverter.getInstance();
		ctakesToDroolsConverter.getSummariesForSummarizable(kbEncounter, encounterJCas);
	}

	private void runCtakesPipeline(JCas encounterJCas, KbEncounter kbEncounter) throws AnalysisEngineProcessException {
		String documentText = kbEncounter.getContent();
		encounterJCas.setDocumentText(documentText);
		SimplePipeline.runPipeline(encounterJCas, ae);
	}

	private void cacheSerializedCasXmi(JCas encounterJCas, KbEncounter kbEncounter) throws IOException, SAXException {
		String xmi = serializeXmi(encounterJCas.getCas());
		kbEncounter.setXmi(xmi);
	}

	private void buildCtakesAnalysisEngine() {
		if (ae == null) {
			try {
				ae = CancerPipelineFactory
						.createPipelineEngine();
			} catch (ResourceInitializationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setAnalysisEngine(AnalysisEngine ae) {
		this.ae = ae;
	}

	private void clearDerivedSummaries(KbEncounter encounter) {
		// TODO change to enconter.getSummaries().removeAll();
		List<KbSummary> summariesToRemove = new ArrayList<KbSummary>();
		for (KbSummary removalCandidate : encounter.getSummaries()) {
			if (!removalCandidate.getCode().matches("Dphe:Anafora|Dphe:Ctakes")) {
				summariesToRemove.add(removalCandidate);
			}
		}
		encounter.getSummaries().removeAll(summariesToRemove);
	}
	
	public String serializeXmi(CAS aCas) throws IOException, SAXException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
		XMISerializer xmlSer = new XMISerializer(out, true);
		ser.serialize(aCas, xmlSer.getContentHandler());
		return out.toString("utf-8");
	}

}
