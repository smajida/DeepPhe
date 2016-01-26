package org.healthnlp.deepphe.workbench;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jess.JessException;

import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.xfer.DagToKnowledgeDisassembler;
import org.healthnlp.deepphe.xfer.KnowledgeToDagAssembler;

public class PatientKnowledgeExtractorDrools implements
		PatientKnowledgeExtractorInterface {

	private DroolsKnowledgeBaseAndSession engine;
	private DroolsTextOutputer droolsTextOutputer;
	private List<KbPatient> patients;
	private Iterator<KbPatient> patientIterator;

	private final HashMap<Integer, KbIdentified> knowledgeMap = new HashMap<Integer, KbIdentified>();
	private KbPatient currentPatient;

	public PatientKnowledgeExtractorDrools() {
	}

	public void execute() {
		try {
			for (KbPatient patient : patients) {
				currentPatient = patient;
				executePatient();
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	private void executePatient() throws JessException {
		disassembleDagToKnowledge();
		appendGoalEstablishPlan();
		engine.clearDrools();
		loadKnowledgeToDrools();
		engine.execute();
		assembleKnowledgeToDag();
	}

	public void loadSinglePatient() {
		disassembleDagToKnowledge();
		appendGoalEstablishPlan();
		loadKnowledgeToDrools();
	}

	private void assembleKnowledgeToDag() {
		KnowledgeToDagAssembler assembler = new KnowledgeToDagAssembler();
		assembler.setPatient(currentPatient);
		Iterator<Object> factsIterator = engine.getSession()
				.getWorkingMemoryEntryPoints().iterator().next().getObjects()
				.iterator();
		while (factsIterator.hasNext()) {
			Object factObj = factsIterator.next();
			if (KbIdentified.class.isAssignableFrom(factObj.getClass())) {
				assembler.add((KbIdentified) factObj);
				droolsTextOutputer.appendText(factObj.toString());
			}
		}
		assembler.execute();
	}

	private void loadKnowledgeToDrools() {
		for (KbIdentified identified : knowledgeMap.values()) {
			engine.getSession().insert(identified);
		}
	}

	private void disassembleDagToKnowledge() {
		DagToKnowledgeDisassembler disassembler = new DagToKnowledgeDisassembler();
		knowledgeMap.clear();
		disassembler.setKnowledgeMap(knowledgeMap);
		disassembler.setPatient(currentPatient);
		disassembler.execute();
	}

	private void appendGoalEstablishPlan() {
		KbGoal goal = new KbGoal();
		goal.setName("establish-plan");
		goal.setIsActive(1);
		goal.setPriority(0);
		knowledgeMap.put(goal.getId(), goal);
	}

	@Override
	public void setDroolsTextOutputer(DroolsTextOutputer jessTextOutputer) {
		this.droolsTextOutputer = jessTextOutputer;
	}

	public DroolsKnowledgeBaseAndSession getKnowledgeBase() {
		return engine;
	}

	@Override
	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;
	}

	@Override
	public void iteratePatients() {
		patientIterator = patients.iterator();

	}

	@Override
	public void nextPatient() {
		currentPatient = patientIterator.next();

	}

	@Override
	public boolean hasMorePatients() {
		return patientIterator.hasNext();
	}

	@Override
	public void setKnowledgeBase(DroolsKnowledgeBaseAndSession engine) {
		this.engine = engine;
	}

}
