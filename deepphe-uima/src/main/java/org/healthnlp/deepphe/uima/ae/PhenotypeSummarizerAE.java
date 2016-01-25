package org.healthnlp.deepphe.uima.ae;

import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;

import jess.JessException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.summarization.DroolsKnowledgeBaseAndSession;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.xfer.DagToKnowledgeDisassembler;
import org.healthnlp.deepphe.summarization.xfer.KnowledgeToDagAssembler;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;

public class PhenotypeSummarizerAE extends JCasAnnotator_ImplBase {

	public static final String PARAM_CLIPS_DIRECTORY_PATH = "CLIPS_DIRECTORY_PATH";
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";

	private DroolsKnowledgeBaseAndSession engine;
	private final HashMap<Integer, KbIdentified> knowledgeMap = new HashMap<Integer, KbIdentified>();
	private KbPatient patient;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		@SuppressWarnings("unused")
		String clipsDirectoryPath = 
		        (String) aContext.getConfigParameterValue(PARAM_CLIPS_DIRECTORY_PATH);
		@SuppressWarnings("unused")
		String ontologyPath = 
		        (String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH);
		if (engine == null) {
			engine = DroolsKnowledgeBaseAndSession.getInstance();
		}
	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		try {
			
			patient = new KbPatient();
			patient.setId(1);
			
			// TODO convert doc level objects to drools here.
			
			for(Report r: PhenotypeResourceFactory.loadReports(jcas)) {
			//	KbEncounter enc = SummaryFactory.getEncounter(r);
				KbEncounter enc = new KbEncounter();
				enc.setPatientId(patient.getId());
				patient.addEncounter(enc);
			}
			
			disassembleDagToKnowledge();
			appendGoalEstablishPlan();
			engine.clearDrools();
			loadKnowledgeToDrools();
			engine.execute();
			assembleKnowledgeToDag();
			
			byte[] serializedPatientBytes = SerializationUtils.serialize(patient);
			String base64EncodedPatient = Base64.getEncoder().encodeToString(serializedPatientBytes);
			JCas forI2b2JCas = jcas.createView("forI2b2");
			forI2b2JCas.setDocumentText(base64EncodedPatient);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void disassembleDagToKnowledge() {
		DagToKnowledgeDisassembler disassembler = new DagToKnowledgeDisassembler();
		knowledgeMap.clear();
		disassembler.setKnowledgeMap(knowledgeMap);
		disassembler.setPatient(patient);
		disassembler.execute();
	}
	
	private void assembleKnowledgeToDag() {
		KnowledgeToDagAssembler assembler = new KnowledgeToDagAssembler();
		assembler.setPatient(patient);
		Iterator<Object> factsIterator = engine.getSession()
				.getWorkingMemoryEntryPoints().iterator().next().getObjects()
				.iterator();
		while (factsIterator.hasNext()) {
			Object factObj = factsIterator.next();
			if (KbIdentified.class.isAssignableFrom(factObj.getClass())) {
				assembler.add((KbIdentified) factObj);
			}
		}
		assembler.execute();
	}
	
	private void appendGoalEstablishPlan() {
		KbGoal goal = new KbGoal();
		goal.setName("establish-plan");
		goal.setIsActive(1);
		goal.setPriority(0);
		knowledgeMap.put(goal.getId(), goal);
	}


	private void loadKnowledgeToDrools(){ 
		for (KbIdentified identified : knowledgeMap.values()) {
			engine.getSession().insert(identified);
		}
	}
	


}
