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
import org.healthnlp.deepphe.summarization.drools.kb.Breast;
import org.healthnlp.deepphe.summarization.drools.kb.HasBodySite;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.Tumor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBreastCancerPlanner {

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
	public void testPlannerScaffold() {

		int idCounter = 0;

		System.out.println("\nRunning testPrimaryTumorRule\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("establish-plan");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology");

		Tumor diseaseOne = new TumorImpl();
		diseaseOne.setId(idCounter++);
		diseaseOne.setSummarizableId(encOne.getId());
		
		Breast breast = new BreastImpl();
		breast.setId(idCounter++);
		breast.setSummarizableId(patient.getId());
		
		HasBodySite hasBodySite = new HasBodySiteImpl();
		hasBodySite.setId(idCounter++);
		hasBodySite.setSummarizableId(patient.getId());
		hasBodySite.setDomainId(diseaseOne.getId());
		hasBodySite.setRangeId(breast.getId());
		
		session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleDiseaseOne = session.insert(diseaseOne);		
		FactHandle handleBreast = session.insert(breast);
		FactHandle handleHasBodySite = session.insert(hasBodySite);

		final String[] rulesToTest = { 
				"000_breastCancerPlanner Activate when BreastNeoplasm",
				"000_breastCancerPlanner Module Needs Written",
				"000_breastCancerPlanner Activate Next Goal"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(10, numRulesFired);
		
		assertEquals(session.getFactCount(), 5);

		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleDiseaseOne);
		session.retract(handleBreast);
		session.retract(handleHasBodySite);
		
		assertEquals(session.getFactCount(), 0);

	}
	
	@Test
	public void testPlannerDenucleated() {

		int idCounter = 0;

		System.out.println("\nRunning testPlannerDenucleated\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("establish-plan");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology");

		Tumor diseaseOne = new TumorImpl();
		diseaseOne.setId(idCounter++);
		diseaseOne.setSummarizableId(encOne.getId());
		
		Breast breast = new BreastImpl();
		breast.setId(idCounter++);
		breast.setSummarizableId(patient.getId());
		
		HasBodySite hasBodySite = new HasBodySiteImpl();
		hasBodySite.setId(idCounter++);
		hasBodySite.setSummarizableId(patient.getId());
		hasBodySite.setDomainId(diseaseOne.getId());
		hasBodySite.setRangeId(breast.getId());

		session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleDiseaseOne = session.insert(diseaseOne);
		FactHandle handleBreast = session.insert(breast);
		FactHandle handleHasBodySite = session.insert(hasBodySite);

		final String[] rulesToTest = { 
				"000_breastCancerPlanner Activate when BreastNeoplasm",
				"000_breastCancerPlanner Module Needs Written",
				"000_breastCancerPlanner Activate Next Goal",
				"001_breastCancerPrimaryTumor Transition Goal State",
				"002_breastCancerTumorSize Transition Goal State",
				"003_breastCancerTnm Transition Goal State",
				"004_breastCancerStage Transition Goal State",
				"005_breastCancerReceptor Transition Goal State"
				};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(10, numRulesFired);
		
		assertEquals(session.getFactCount(), 5);

		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleDiseaseOne);
		session.retract(handleBreast);
		session.retract(handleHasBodySite);
		
		assertEquals(session.getFactCount(), 0);
	}

}
