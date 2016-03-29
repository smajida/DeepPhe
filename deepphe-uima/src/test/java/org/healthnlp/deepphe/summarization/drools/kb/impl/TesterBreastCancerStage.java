package org.healthnlp.deepphe.summarization.drools.kb.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;*/
import org.healthnlp.deepphe.summarization.drools.kb.Breast;
import org.healthnlp.deepphe.summarization.drools.kb.CancerStage;
import org.healthnlp.deepphe.summarization.drools.kb.ClinicalRegionalLymphNodeClassification;
import org.healthnlp.deepphe.summarization.drools.kb.Ductal;
import org.healthnlp.deepphe.summarization.drools.kb.HasBodySite;
import org.healthnlp.deepphe.summarization.drools.kb.HasHistologicType;
import org.healthnlp.deepphe.summarization.drools.kb.HasOutcome;
import org.healthnlp.deepphe.summarization.drools.kb.HasPhenotype;
import org.healthnlp.deepphe.summarization.drools.kb.HasSequenceVariant;
import org.healthnlp.deepphe.summarization.drools.kb.HasTreatment;
import org.healthnlp.deepphe.summarization.drools.kb.HasTumorExtent;
import org.healthnlp.deepphe.summarization.drools.kb.HasTumorType;
import org.healthnlp.deepphe.summarization.drools.kb.InSitu;
import org.healthnlp.deepphe.summarization.drools.kb.Invasive;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.Lobular;
import org.healthnlp.deepphe.summarization.drools.kb.M0;
import org.healthnlp.deepphe.summarization.drools.kb.M1;
import org.healthnlp.deepphe.summarization.drools.kb.PagetsDisease;
import org.healthnlp.deepphe.summarization.drools.kb.Pn0;
import org.healthnlp.deepphe.summarization.drools.kb.Pn1;
import org.healthnlp.deepphe.summarization.drools.kb.Pn1mi;
import org.healthnlp.deepphe.summarization.drools.kb.Pn2;
import org.healthnlp.deepphe.summarization.drools.kb.Pn3;
import org.healthnlp.deepphe.summarization.drools.kb.PrimaryTumor;
import org.healthnlp.deepphe.summarization.drools.kb.PrimaryTumorClassification;
import org.healthnlp.deepphe.summarization.drools.kb.Stageia;
import org.healthnlp.deepphe.summarization.drools.kb.T0;
import org.healthnlp.deepphe.summarization.drools.kb.T1;
import org.healthnlp.deepphe.summarization.drools.kb.T2;
import org.healthnlp.deepphe.summarization.drools.kb.T3;
import org.healthnlp.deepphe.summarization.drools.kb.T4;
import org.healthnlp.deepphe.summarization.drools.kb.Tis;
import org.healthnlp.deepphe.summarization.drools.kb.Tumor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBreastCancerStage {
/*
	private StatefulKnowledgeSession session = null;
	private int idCounter = 0;

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

		idCounter = 0;

		System.out.println("\nRunning testStage\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
     
		Tis pTis = new TisImpl();
		pTis.setId(idCounter++);
		pTis.setSummarizableId(patient.getId());
		
		Pn0 pN0 = new Pn0Impl();
		pN0.setId(idCounter++);
		pN0.setSummarizableId(patient.getId());
		
		M0 pM0 = new M0Impl();
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

		idCounter = 0;
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		System.out.println("\nRunning testStage0_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		session.insert(goal);

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		session.insert(patient);
		
		//
		// DCIS
		//
		Tumor tumor = populateSessionWithInSituPrimaryBreastTumor(patient, factHandles);
		populateSessionWithDcis(patient, tumor, factHandles);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 11);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-2-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);
	}
	
	@Test
	public void testStage0_2_2() {

		System.out.println("\nRunning testStage0_2_2\n");
		
		idCounter = 0;
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();


		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		//
		// DCIS
		//
		Tumor tumor = populateSessionWithInSituPrimaryBreastTumor(patient, factHandles);
		populateSessionWithDcis(patient, tumor, factHandles);
		
		//
		// Paget's Disease
		//
		
		PagetsDisease pagets = new PagetsDiseaseImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-2-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 13);
	}
	
	@Test
	public void testStage0_3_1() {

		idCounter = 0;
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		System.out.println("\nRunning testStage0_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		//
		// LCIS
		//
		Tumor tumor = populateSessionWithInSituPrimaryBreastTumor(patient, factHandles);
		populateSessionWithLcis(patient, tumor, factHandles);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 11);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-3-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);
	}
	
	@Test
	public void testStage0_3_2() {

		idCounter = 0;
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		System.out.println("\nRunning testStage0_2_2\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		session.insert(goal);

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		session.insert(patient);
		
		//
		// LCIS
		//
		Tumor tumor = populateSessionWithInSituPrimaryBreastTumor(patient, factHandles);
		populateSessionWithLcis(patient, tumor, factHandles);
     
		PagetsDisease pagets = new PagetsDiseaseImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-3-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 13);
	}
	
	@Test
	public void testStage0_4_1() {

		idCounter = 0;
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		System.out.println("\nRunning testStage0_4_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		session.insert(goal);

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		session.insert(patient);
		
		//
		// DCIS and LCIS
		//
		Tumor tumor = populateSessionWithInSituPrimaryBreastTumor(patient, factHandles);
		populateSessionWithDcis(patient, tumor, factHandles);
		populateSessionWithLcis(patient, tumor, factHandles);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 13);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-4-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 14);
	}
	
	@Test
	public void testStage0_4_2() {

		idCounter = 0;
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		System.out.println("\nRunning testStage0_4_2\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		session.insert(goal);

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		session.insert(patient);
		
		//
		// DCIS and LCIS
		//
		Tumor tumor = populateSessionWithInSituPrimaryBreastTumor(patient, factHandles);
		populateSessionWithDcis(patient, tumor, factHandles);
		populateSessionWithLcis(patient, tumor, factHandles);
		
		PagetsDisease pagets = new PagetsDiseaseImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
		session.insert(pagets);
	
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 14);
		final String[] rulesToTest = { "004_breastCancerStage Stage0-4-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 15);
	}

	@Test
	public void testStage0_5_1() {

		idCounter = 0;

		System.out.println("\nRunning testStage0_5_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");
		session.insert(goal);

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		session.insert(patient);
		
		PagetsDisease pagets = new PagetsDiseaseImpl();
		pagets.setId(idCounter++);
		pagets.setSummarizableId(patient.getId());
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

		idCounter = 0;

		System.out.println("\nRunning testStage1a_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1 tStage = new T1Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn0 nStage = new Pn0Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage1b_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0 tStage = new T0Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1mi nStage = new Pn1miImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage1b_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1 tStage = new T1Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1mi nStage = new Pn1miImpl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage2a_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0 tStage = new T0Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1 nStage = new Pn1Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage2a_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1 tStage = new T1Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1 nStage = new Pn1Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage2a_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T2 tStage = new T2Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn0 nStage = new Pn0Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage2b_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T2 tStage = new T2Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1 nStage = new Pn1Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage2b_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T3 tStage = new T3Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn0 nStage = new Pn0Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3a_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0 tStage = new T0Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn2 nStage = new Pn2Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3a_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T1 tStage = new T1Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn2 nStage = new Pn2Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3a_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T2 tStage = new T2Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn2 nStage = new Pn2Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3a_4_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T3 tStage = new T3Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1 nStage = new Pn1Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3a_5_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T3 tStage = new T3Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn2 nStage = new Pn2Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3b_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T4 tStage = new T4Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn0 nStage = new Pn0Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3b_2_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T4 tStage = new T4Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn1 nStage = new Pn1Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3b_3_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T4 tStage = new T4Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn2 nStage = new Pn2Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage3c_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		T0 tStage = new T0Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		Pn3 nStage = new Pn3Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M0 mStage = new M0Impl();
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

		idCounter = 0;

		System.out.println("\nRunning testStage4_1_1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-stage");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		PrimaryTumorClassification tStage = new T0Impl();
		tStage.setId(idCounter++);
		tStage.setSummarizableId(patient.getId());
     
		ClinicalRegionalLymphNodeClassification nStage = new N0Impl();
		nStage.setId(idCounter++);
		nStage.setSummarizableId(patient.getId());
		
		M1 mStage = new M1Impl();
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
	
	private Tumor populateSessionWithInSituPrimaryBreastTumor(KbPatient kbPatient, List<FactHandle> factHandles) {
		//
		// Tumor
		//
		Tumor tumor = new TumorImpl();
		tumor.setId(idCounter++);
		tumor.setSummarizableId(kbPatient.getId());		
		factHandles.add(session.insert(tumor));
		
		//
		// LCIS
		//
		
		//
		// tumor hasBodySite breast
		// 
		HasBodySite hasBodySite = new HasBodySiteImpl();
		hasBodySite.setId(idCounter++);
		hasBodySite.setSummarizableId(kbPatient.getId());		
		Breast breast = new BreastImpl();
		breast.setId(idCounter++);
		breast.setSummarizableId(kbPatient.getId());
		hasBodySite.setDomainId(tumor.getId());
		hasBodySite.setRangeId(breast.getId());
		
		factHandles.add(session.insert(tumor));
		factHandles.add(session.insert(breast));
		factHandles.add(session.insert(hasBodySite));
	
		//
		// tumor hasTumorType primaryTumor
		// 
		HasTumorType hasTumorType = new HasTumorTypeImpl();
		hasTumorType.setId(idCounter++);
		hasTumorType.setSummarizableId(kbPatient.getId());	
		PrimaryTumor primaryTumor = new PrimaryTumorImpl();
		primaryTumor.setId(idCounter++);
		primaryTumor.setSummarizableId(kbPatient.getId());
		hasTumorType.setDomainId(tumor.getId());
		hasTumorType.setRangeId(primaryTumor.getId());
		hasTumorType.setDomainId(tumor.getId());
		hasTumorType.setRangeId(primaryTumor.getId());
	
		
		factHandles.add(session.insert(hasTumorType));
		factHandles.add(session.insert(primaryTumor));
		
		//
		// tumor hasAttribute inSitu
		// 
		HasTumorExtent hasTumorExtent = new HasTumorExtentImpl();
		hasTumorExtent.setId(idCounter++);
		hasTumorExtent.setSummarizableId(kbPatient.getId());	
		InSitu inSitu = new InSituImpl();
		inSitu.setId(idCounter++);
		inSitu.setSummarizableId(kbPatient.getId());
		hasTumorExtent.setDomainId(tumor.getId());
		hasTumorExtent.setRangeId(inSitu.getId());
		factHandles.add(session.insert(hasTumorExtent));
		factHandles.add(session.insert(inSitu));
		
		System.out.println("there are " + factHandles.size() + " facts");
		
		return tumor;
	}
	
	private void populateSessionWithDcis(KbPatient kbPatient, Tumor tumor, List<FactHandle> factHandles) {
		//
		// DCIS
		//
		
		//
		// tumor hasHasHistologicType ductal
		// 
		HasHistologicType hasHistologicType = new HasHistologicTypeImpl();
		hasHistologicType.setId(idCounter++);
		hasHistologicType.setSummarizableId(kbPatient.getId());	
		Ductal ductal = new DuctalImpl();
		ductal.setId(idCounter++);
		ductal.setSummarizableId(kbPatient.getId());
		hasHistologicType.setDomainId(tumor.getId());
		hasHistologicType.setRangeId(ductal.getId());
		factHandles.add(session.insert(hasHistologicType));
		factHandles.add(session.insert(ductal));
		
	}
	
	private void populateSessionWithLcis(KbPatient patient, Tumor tumor, List<FactHandle> factHandles) {
		//
		// LCIS
		//
		
		//
		// tumor hasHasHistologicType lobular
		// 
		HasHistologicType hasHistologicType = new HasHistologicTypeImpl();
		hasHistologicType.setId(idCounter++);
		hasHistologicType.setSummarizableId(patient.getId());	
		Lobular lobular = new LobularImpl();
		lobular.setId(idCounter++);
		lobular.setSummarizableId(patient.getId());
		hasHistologicType.setDomainId(tumor.getId());
		hasHistologicType.setRangeId(lobular.getId());
		factHandles.add(session.insert(hasHistologicType));
		factHandles.add(session.insert(lobular));
		
	}
	
	private void buildAllTumorRelations() {
		HasHistologicType hasHistologicType = new HasHistologicTypeImpl();
		HasOutcome hasOutcome = new HasOutcomeImpl();
		HasPhenotype hasPhenotype = new HasPhenotypeImpl();
		HasSequenceVariant hasSequenceVarient = new HasSequenceVariantImpl();
		HasTreatment hasTreatment = new HasTreatmentImpl();
		HasTumorType hasTumorType = new HasTumorTypeImpl();
		HasTumorExtent hasTumorExtent = new HasTumorExtentImpl();
		
		Stageia stage = new StageiaImpl();
		Pn1mi pN1mi = new Pn1miImpl();
		Pn0 pN0 = new Pn0Impl();
		
		CancerStage stageIb = new StageibImpl();
		CancerStage stageIia = new StageiiaImpl();
		
		Invasive invasive = new InvasiveImpl();
     
	}
*/	
}
