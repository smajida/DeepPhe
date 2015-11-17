package org.healthnlp.deepphe.summarization.drools.kb.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.TumorGreaterThanOrEqualTo21Centimeters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBreastCancerTumorSize {

	private StatefulKnowledgeSession session = null;

	@Before
	public void setUp() {
		try {

			KnowledgeBuilder builder = KnowledgeBuilderFactory
					.newKnowledgeBuilder();
			URL autoloadUrl = getClass().getResource("drools/autoload");
			File autoloadDirectory = new File(autoloadUrl.getPath());
			if (autoloadDirectory.exists() && autoloadDirectory.isDirectory()) {
				File[] autoloadFiles = autoloadDirectory.listFiles();
				for (File autoloadFile : autoloadFiles) {
					Resource rulesResource = ResourceFactory
							.newFileResource(autoloadFile);
					builder.add(rulesResource, ResourceType.DRL);
				}
			}
			if (builder.hasErrors()) {
				throw new RuntimeException(builder.getErrors().toString());
			}
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory
					.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
			session = knowledgeBase.newStatefulKnowledgeSession();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		if (session != null) {
			session.dispose();
		}
	}

	@Test
	public void testTumorSize() {

		int idCounter = 0;

		System.out.println("\nRunning testTumorSize\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-tumor-size");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology Report");

		TumorGreaterThanOrEqualTo21Centimeters gte21Centimeters = new TumorGreaterThanOrEqualTo21CentimetersImpl();
		gte21Centimeters.setId(idCounter++);
		gte21Centimeters.setSummarizableId(encOne.getId());

		session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleGte21Centimeters = session.insert(gte21Centimeters);

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);

		final String[] rulesToTest = { "002_breastCancerTumorSize Tumor Size From First Reliable Encounter" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);

		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleGte21Centimeters);
	}

}
