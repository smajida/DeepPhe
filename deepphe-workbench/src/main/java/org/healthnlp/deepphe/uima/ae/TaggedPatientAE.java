package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jess.JessException;
import jess.Rete;
import jess.Value;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.xfer.CtakesToDroolsConverter;
import org.healthnlp.deepphe.xfer.DagToKnowledgeDisassembler;
import org.healthnlp.deepphe.xfer.DroolsToCtakesConverter;
import org.healthnlp.deepphe.xfer.KnowledgeToDagAssembler;

public class TaggedPatientAE extends TaggedSummarizableAE {

	private JCas multiJCas;
	private JCas patientJCas;
	private KbPatient patient;
	private Rete jessEngine;
	private final HashMap<Integer, KbIdentified> knowledgeMap = new HashMap<Integer, KbIdentified>();

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		loadProductionClipsFiles();
	}
	
	private void loadProductionClipsFiles() {
		try {
			if (jessEngine == null) {
				jessEngine = new Rete();
				File productionClipsDirectory = new File("C:/ws/ws-deepphe-6/DeepPhe/deepphe-uima/src/main/jess/autoload");
				if (productionClipsDirectory.isDirectory()) {
					List<File> fileList = Arrays.asList(productionClipsDirectory.listFiles());
					Collections.sort(fileList, new Comparator<File>() {
						@Override
						public int compare(File o1, File o2) {
							return o1.getName().compareTo(o2.getName());
						}});
					for (File f : fileList) {
						if (f.getAbsolutePath().endsWith(".clp"));
						String text = FileUtils.readFileToString(f, "utf-8");
						jessEngine.eval(text);
					}
				}
			}			
		} catch(Exception x) {
			x.printStackTrace();
		}			
	}

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		super.process(multiJCas);
		this.multiJCas = multiJCas;
		patientJCas = fetchActiveJCas(multiJCas);
		if (CasDetector.isPatientJCas(patientJCas)) {
			try {
				executeJess("(retract-identifiables)");
				uploadPatientDAG();
				summarizePatientKnowlegeWithJess();
				cachePatientDAG();
			} catch (CASException e) {
				throw new AnalysisEngineProcessException(e);
			} catch (JessException e) {
				throw new AnalysisEngineProcessException(e);
			}
		}
	}
	
	private void cachePatientDAG() 	throws CASException {
		DroolsToCtakesConverter jessToCtakesConverter = new DroolsToCtakesConverter();
		jessToCtakesConverter.setPatient(patient);
		jessToCtakesConverter.setPatientJCas(patientJCas);	
		jessToCtakesConverter.execute();
	}

	private void summarizePatientKnowlegeWithJess() throws JessException {
		disassembleDagToKnowledge();
		appendGoalEstablishPlan();
		loadKnowledgeToJess();
		executeJess("(run)");
		assembleKnowledgeToDag();
	}

	private void uploadPatientDAG()
			throws CASException {
		CtakesToDroolsConverter ctakesToJessConverter = new CtakesToDroolsConverter();
		ctakesToJessConverter.setMultiJCas(multiJCas);
		ctakesToJessConverter.setPatientJCas(patientJCas);
		ctakesToJessConverter.setPatient(new KbPatient());
		ctakesToJessConverter.execute();
		patient = ctakesToJessConverter.getPatient();
	}

	private void loadKnowledgeToJess() throws JessException {
		for (KbIdentified identified :  knowledgeMap.values()) {
			System.out.println("Adding a " + identified.getClass().getSimpleName() + " to working memory.");
			jessEngine.add(identified);
		}
	}
	
	private void appendGoalEstablishPlan() {
		KbGoal goal = new KbGoal();
		goal.setName("establish-plan");
		goal.setIsActive(1);
		goal.setPriority(0);
		knowledgeMap.put(goal.getId(),goal);
	}
	
	private void disassembleDagToKnowledge() {
		DagToKnowledgeDisassembler disassembler = new DagToKnowledgeDisassembler();
		knowledgeMap.clear();
		disassembler.setKnowledgeMap(knowledgeMap);
		disassembler.setPatient(patient);
		disassembler.execute();
	}
	
	@SuppressWarnings("unchecked")
	private void assembleKnowledgeToDag() {
		KnowledgeToDagAssembler assembler = new KnowledgeToDagAssembler();
		assembler.setPatient(patient);	
		Iterator<jess.Fact> factsIterator = jessEngine.listFacts();
		while (factsIterator.hasNext()) {
			jess.Fact fact = (jess.Fact) factsIterator.next();
			Object factObj = extractObjectSlot(fact);
			if (factObj != null) {
				if (KbIdentified.class.isAssignableFrom(factObj.getClass())) {
					assembler.add((KbIdentified) factObj);
					System.out.println(factObj.toString());
				}				
			}
		}		
		assembler.execute();	
	}
	
	private Object extractObjectSlot(jess.Fact fact) {
		Object factObj = null;
		try {
			Value v = fact.getSlotValue("OBJECT");
			factObj = v.javaObjectValue(jessEngine.getGlobalContext());
		}
		catch (Exception x) {
			factObj = null;
		}
		return factObj;
	}

	public void executeJess(String command) {
		try {
			Value result = jessEngine.eval(command);
			if (!jess.FactIDValue.class.isInstance(result)) {
				System.out
						.println(result.stringValue(jessEngine.getGlobalContext()));
			} else {
				jess.Fact f = result.factValue(jessEngine.getGlobalContext());
				System.out.println(f.toStringWithParens() + "\n");
			}
		} catch (JessException e1) {
			System.err.println(e1.getMessage());
		}

	}

}