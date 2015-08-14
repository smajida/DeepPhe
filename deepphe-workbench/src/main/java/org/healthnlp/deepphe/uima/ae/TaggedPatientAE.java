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
import org.healthnlp.deepphe.summarization.jess.DagToKnowledgeDisassembler;
import org.healthnlp.deepphe.summarization.jess.KnowledgeToDagAssembler;
import org.healthnlp.deepphe.summarization.jess.kb.Goal;
import org.healthnlp.deepphe.summarization.jess.kb.Identified;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.xfer.CtakesToJessConverter;
import org.healthnlp.deepphe.xfer.JessToCtakesConverter;

public class TaggedPatientAE extends TaggedSummarizableAE {

	private JCas multiJCas;
	private JCas patientJCas;
	private Patient patient;
	private Rete jessEngine;
	private final HashMap<Integer, Identified> knowledgeMap = new HashMap<Integer, Identified>();

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
		JessToCtakesConverter jessToCtakesConverter = new JessToCtakesConverter();
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
		CtakesToJessConverter ctakesToJessConverter = new CtakesToJessConverter();
		ctakesToJessConverter.setMultiJCas(multiJCas);
		ctakesToJessConverter.setPatientJCas(patientJCas);
		ctakesToJessConverter.setPatient(new Patient());
		ctakesToJessConverter.execute();
		patient = ctakesToJessConverter.getPatient();
	}

	private void loadKnowledgeToJess() throws JessException {
		for (Identified identified :  knowledgeMap.values()) {
			System.out.println("Adding a " + identified.getClass().getSimpleName() + " to working memory.");
			jessEngine.add(identified);
		}
	}
	
	private void appendGoalEstablishPlan() {
		Goal goal = new Goal();
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
				if (Identified.class.isAssignableFrom(factObj.getClass())) {
					assembler.add((Identified) factObj);
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