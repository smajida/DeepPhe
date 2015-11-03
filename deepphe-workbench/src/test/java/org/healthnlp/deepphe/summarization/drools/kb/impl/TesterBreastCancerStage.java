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
import org.healthnlp.deepphe.summarization.drools.kb.IntraductalBreastNeoplasm;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.LobularBreastCarcinomaInSitu;
import org.healthnlp.deepphe.summarization.drools.kb.M0StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.M1StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.N0StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.N1StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.N1miStageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.N2StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.N3StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.PagetDiseaseOfTheBreast;
import org.healthnlp.deepphe.summarization.drools.kb.T0StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.T1StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.T2StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.T3StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.T4StageFinding;
import org.healthnlp.deepphe.summarization.drools.kb.TisStageFinding;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBreastCancerStage {

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
	public void testStage0_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
     
		TisStageFinding pTis = new TisStageFindingImpl();
		pTis.setId(idCounter++);
		pTis.setSummarizableId(patient.getId());
		
		N0StageFinding pN0 = new N0StageFindingImpl();
		pN0.setId(idCounter++);
		pN0.setSummarizableId(patient.getId());
		
		M0StageFinding pM0 = new M0StageFindingImpl();
		pM0.setId(idCounter++);
		pM0.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(pTis);
		session.insert(pN0);
		session.insert(pM0);

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage0_2_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
     
		IntraductalBreastNeoplasm dcis = new IntraductalBreastNeoplasmImpl();
		dcis.setId(idCounter++);
		dcis.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(dcis);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);
	}
	
	@Test
	public void testStage0_2_2() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_2_2\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
     
		IntraductalBreastNeoplasm dcis = new IntraductalBreastNeoplasmImpl();
		dcis.setId(idCounter++);
		dcis.setSummarizableId(patient.getId());
		
		PagetDiseaseOfTheBreast pagets = new PagetDiseaseOfTheBreastImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(dcis);
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-2-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
	}
	
	@Test
	public void testStage0_3_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
     
		LobularBreastCarcinomaInSitu dcis = new LobularBreastCarcinomaInSituImpl();
		dcis.setId(idCounter++);
		dcis.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(dcis);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-3-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);
	}
	
	@Test
	public void testStage0_3_2() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_2_2\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
     
		LobularBreastCarcinomaInSitu dcis = new LobularBreastCarcinomaInSituImpl();
		dcis.setId(idCounter++);
		dcis.setSummarizableId(patient.getId());
		
		PagetDiseaseOfTheBreast pagets = new PagetDiseaseOfTheBreastImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(dcis);
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-3-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
	}
	
	@Test
	public void testStage0_4_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_4_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		IntraductalBreastNeoplasm dcis = new IntraductalBreastNeoplasmImpl();
		dcis.setId(idCounter++);
		dcis.setSummarizableId(patient.getId());
     
		LobularBreastCarcinomaInSitu lcis = new LobularBreastCarcinomaInSituImpl();
		lcis.setId(idCounter++);
		lcis.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(dcis);
		session.insert(lcis);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-4-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
	}
	
	@Test
	public void testStage0_4_2() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_4_2\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		IntraductalBreastNeoplasm dcis = new IntraductalBreastNeoplasmImpl();
		dcis.setId(idCounter++);
		dcis.setSummarizableId(patient.getId());
     
		LobularBreastCarcinomaInSitu lcis = new LobularBreastCarcinomaInSituImpl();
		lcis.setId(idCounter++);
		lcis.setSummarizableId(patient.getId());
		
		PagetDiseaseOfTheBreast pagets = new PagetDiseaseOfTheBreastImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(dcis);
		session.insert(lcis);
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-4-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}

	@Test
	public void testStage0_5_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage0_5_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
	
		PagetDiseaseOfTheBreast pagets = new PagetDiseaseOfTheBreastImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		
		
		
		session.insert(goal);
		session.insert(patient);
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-5-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 4);
	}
	
	@Test
	public void testStage1a_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage1a_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1StageFinding tStage = new T1StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N0StageFinding nStage = new N0StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage1a-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	
	@Test
	public void testStage1b_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage1b_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0StageFinding tStage = new T0StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1miStageFinding nStage = new N1miStageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage1b-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage1b_2_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage1b_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1StageFinding tStage = new T1StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1miStageFinding nStage = new N1miStageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage1b-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage2a_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage2a_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0StageFinding tStage = new T0StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1StageFinding nStage = new N1StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage2a-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}

	@Test
	public void testStage2a_2_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage2a_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1StageFinding tStage = new T1StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1StageFinding nStage = new N1StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage2a-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage2a_3_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage2a_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T2StageFinding tStage = new T2StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N0StageFinding nStage = new N0StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage2a-3-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage2b_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage2b_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T2StageFinding tStage = new T2StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1StageFinding nStage = new N1StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage2b-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage2b_2_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage2b_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T3StageFinding tStage = new T3StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N0StageFinding nStage = new N0StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage2b-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}

	@Test
	public void testStage3a_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3a_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0StageFinding tStage = new T0StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N2StageFinding nStage = new N2StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3a-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3a_2_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3a_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1StageFinding tStage = new T1StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N2StageFinding nStage = new N2StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3a-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3a_3_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3a_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T2StageFinding tStage = new T2StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N2StageFinding nStage = new N2StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3a-3-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3a_4_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3a_4_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T3StageFinding tStage = new T3StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1StageFinding nStage = new N1StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3a-4-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3a_5_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3a_5_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T3StageFinding tStage = new T3StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N2StageFinding nStage = new N2StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3a-5-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3b_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3b_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T4StageFinding tStage = new T4StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N0StageFinding nStage = new N0StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3b-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3b_2_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3b_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T4StageFinding tStage = new T4StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N1StageFinding nStage = new N1StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3b-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3b_3_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3b_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T4StageFinding tStage = new T4StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N2StageFinding nStage = new N2StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3b-3-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage3c_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage3c_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0StageFinding tStage = new T0StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N3StageFinding nStage = new N3StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0StageFinding mStage = new M0StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage3c-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
	@Test
	public void testStage4_1_1() {

		int idCounter = 0;

		System.out.println("\nRunning testStage4_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0StageFinding tStage = new T0StageFindingImpl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		N0StageFinding nStage = new N0StageFindingImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M1StageFinding mStage = new M1StageFindingImpl();
		mStage.setId(idCounter++);
		mStage.setSummarizableId(patient.getId());
		
		session.insert(goal);
		session.insert(patient);
		session.insert(tStage);
		session.insert(nStage);
		session.insert(mStage);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 5);
		final String[] rulesToTest = { "004_breastCancerStage Stage4-1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);
	}
	
}
