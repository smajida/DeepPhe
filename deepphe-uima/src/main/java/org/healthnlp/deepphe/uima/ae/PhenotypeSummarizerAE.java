package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jess.JessException;
import jess.Rete;
import jess.Value;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;
import org.healthnlp.deepphe.summarization.jess.DagToKnowledgeDisassembler;
import org.healthnlp.deepphe.summarization.jess.KnowledgeToDagAssembler;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Goal;
import org.healthnlp.deepphe.summarization.jess.kb.Identified;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Summary;
import org.healthnlp.deepphe.summarization.jess.kb.SummaryFactory;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;

public class PhenotypeSummarizerAE extends JCasAnnotator_ImplBase {

	public static final String PARAM_CLIPS_DIRECTORY_PATH = "CLIPS_DIRECTORY_PATH";
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";

	private Patient patient;
	private Rete jessEngine;
	private final HashMap<Integer, Identified> knowledgeMap = new HashMap<Integer, Identified>();

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		String clipsDirectoryPath = 
		        (String) aContext.getConfigParameterValue(PARAM_CLIPS_DIRECTORY_PATH);
		loadProductionClipsFiles(clipsDirectoryPath);
		String ontologyPath = 
		        (String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH);
		
		File ontologyFile = new File(ontologyPath);
		IOntology ontology;
		try {
			ontology = OOntology.loadOntology(ontologyFile);
			new ResourceFactory(ontology);
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}

	private void loadProductionClipsFiles(String clipsDirectoryPath) {
		try {
			if (jessEngine == null) {
				jessEngine = new Rete();
				File productionClipsDirectory = new File(
						clipsDirectoryPath);
				if (productionClipsDirectory.isDirectory()) {
					List<File> fileList = Arrays
							.asList(productionClipsDirectory.listFiles());
					Collections.sort(fileList, new Comparator<File>() {
						@Override
						public int compare(File o1, File o2) {
							return o1.getName().compareTo(o2.getName());
						}
					});
					for (File f : fileList) {
						if (f.getAbsolutePath().endsWith(".clp"))
							;
						String text = FileUtils.readFileToString(f, "utf-8");
						jessEngine.eval(text);
					}
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}


	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		try {
			patient = (Patient) SerializationUtils.deserialize(Base64.getDecoder().decode(jcas.getDocumentText()));
			disassembleDagToKnowledge();
			appendGoalEstablishPlan();
			loadKnowledgeToJess();
			executeJess("(run)");
			assembleKnowledgeToDag();
			byte[] serializedPatientBytes = SerializationUtils.serialize(patient);
//			String serializedPatientString = new String(serializedPatientBytes, Charset.forName("UTF-8"));
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
	
	private void appendGoalEstablishPlan() {
		Goal goal = new Goal();
		goal.setName("establish-plan");
		goal.setIsActive(1);
		goal.setPriority(0);
		knowledgeMap.put(goal.getId(), goal);
	}

	private void loadKnowledgeToJess() throws JessException {
		for (Identified identified : knowledgeMap.values()) {
			System.out.println("Adding a "
					+ identified.getClass().getSimpleName()
					+ " to working memory.");
			jessEngine.add(identified);
		}
	}
	
	public void executeJess(String command) {
		try {
			Value result = jessEngine.eval(command);
			if (!jess.FactIDValue.class.isInstance(result)) {
				System.out.println(result.stringValue(jessEngine
						.getGlobalContext()));
			} else {
				jess.Fact f = result.factValue(jessEngine.getGlobalContext());
				System.out.println(f.toStringWithParens() + "\n");
			}
		} catch (JessException e1) {
			System.err.println(e1.getMessage());
		}

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
		} catch (Exception x) {
			factObj = null;
		}
		return factObj;
	}

}
