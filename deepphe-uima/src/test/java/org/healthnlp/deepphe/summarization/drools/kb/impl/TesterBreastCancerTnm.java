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
import org.healthnlp.deepphe.summarization.drools.kb.ClinicalRegionalLymphNodeClassification;
import org.healthnlp.deepphe.summarization.drools.kb.DistantMetastasisClassification;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.PrimaryTumorClassification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBreastCancerTnm {

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
	public void testTnm() {

		int idCounter = 0;

		System.out.println("\nRunning testTnm\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-tnm");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology Report");
		
		// TNM T
		PrimaryTumorClassification tum = new T2Impl();
		tum.setId(idCounter++);
		tum.setSummarizableId(encOne.getId());
		
		// TNM N
		ClinicalRegionalLymphNodeClassification nod = new N2Impl();
		nod.setId(idCounter++);
		nod.setSummarizableId(encOne.getId());
		
		// TNM M
		DistantMetastasisClassification met = new M1Impl();
		met.setId(idCounter++);
		met.setSummarizableId(encOne.getId());

		session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleTum = session.insert(tum);
		FactHandle handleNod = session.insert(nod);
		FactHandle handleMet = session.insert(met);

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);

		final String[] rulesToTest = { 
				"003_breastCancerTnm T Grade From Last Encounter",
				"003_breastCancerTnm N Grade From Last Encounter",
				"003_breastCancerTnm M Grade From Last Encounter",
				"003_breastCancerTnm Transition Goal State"
		};
		
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(4, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 8);

		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleTum);
		session.retract(handleNod);
		session.retract(handleMet);
	}	
	
}
