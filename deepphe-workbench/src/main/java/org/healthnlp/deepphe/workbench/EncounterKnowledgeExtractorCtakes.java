package org.healthnlp.deepphe.workbench;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ctakes.cancer.ae.XMISerializer;
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
import org.xml.sax.SAXException;

public class EncounterKnowledgeExtractorCtakes implements
		EncounterKnowledgeExtractorInterface {

	private KbPatient patient;
	private AnalysisEngine ae = null;

	public EncounterKnowledgeExtractorCtakes() {
	}

	public void executePatient(KbPatient kbPatient) {
		for (KbEncounter kbEncounter : patient.getEncounters()) {
			executeEncounter(kbEncounter);
		}
		patient.clearSummaries(); // Patient summaries must be
		// recomputed
	}
	

	@Override
	public void executeEncounter(KbEncounter kbEncounter) {
		try {
			JCas encounterJCas = ae.newJCas();
			String documentText = kbEncounter.getContent();
			encounterJCas.setDocumentText(documentText);
			SimplePipeline.runPipeline(encounterJCas, ae);
			clearDerivedSummaries(kbEncounter);
			KbSummary cTakesXmiSummary = new KbSummary();
			cTakesXmiSummary.setCode("Dphe:cTakes");
			String xmi = serializeXmi(encounterJCas.getCas());
			cTakesXmiSummary.setPath(xmi);
			kbEncounter.addSummary(cTakesXmiSummary);
		} catch (ResourceInitializationException
				| AnalysisEngineProcessException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setAnalysisEngine(AnalysisEngine ae) {
		this.ae = ae;	
	}


	private void clearDerivedSummaries(KbEncounter encounter) {
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
