package org.healthnlp.deepphe.summarization.drools.kb.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

/*import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;*/
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.Tumor;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TesterBreastCancerPrimaryTumor {
/*
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
	public void testPrimaryTumorRule() {

		int idCounter = 0;

		System.out.println("\nRunning testPrimaryTumorRule\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-primary-tumor");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology");

		KbEncounter encTwo = new KbEncounter();
		encTwo.setId(idCounter++);
		encTwo.setPatientId(patient.getId());
		encTwo.setSequence(1);
		encTwo.setIsActive(1);
		encTwo.setKind("Pathology");

		Tumor diseaseOne = new TumorImpl();
		diseaseOne.setId(idCounter++);
		diseaseOne.setSummarizableId(encOne.getId());

		Tumor diseaseTwo = new TumorImpl();
		diseaseTwo.setId(idCounter++);
		diseaseTwo.setSummarizableId(encTwo.getId());

		session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleEncTwo = session.insert(encTwo);
		FactHandle handleDiseaseOne = session.insert(diseaseOne);
		FactHandle handleDiseaseTwo = session.insert(diseaseTwo);

		final String[] rulesToTest = { 
				"001_breastCancerPrimaryTumor From First Encounter",
				"001_breastCancerPrimaryTumor Transition Goal State"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(2, numRulesFired);

		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleEncTwo);
		session.retract(handleDiseaseOne);
		session.retract(handleDiseaseTwo);

	}
	*/
}
