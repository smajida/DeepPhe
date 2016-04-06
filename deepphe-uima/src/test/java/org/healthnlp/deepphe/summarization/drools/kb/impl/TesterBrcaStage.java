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
import org.healthnlp.deepphe.summarization.drools.kb.CancerPhenotype;
import org.healthnlp.deepphe.summarization.drools.kb.Hasmclassification;
import org.healthnlp.deepphe.summarization.drools.kb.Hasnclassification;
import org.healthnlp.deepphe.summarization.drools.kb.Hastclassification;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.M0;
import org.healthnlp.deepphe.summarization.drools.kb.M1;
import org.healthnlp.deepphe.summarization.drools.kb.N0;
import org.healthnlp.deepphe.summarization.drools.kb.N1;
import org.healthnlp.deepphe.summarization.drools.kb.N2;
import org.healthnlp.deepphe.summarization.drools.kb.N3;
import org.healthnlp.deepphe.summarization.drools.kb.Pn0;
import org.healthnlp.deepphe.summarization.drools.kb.Pn1;
import org.healthnlp.deepphe.summarization.drools.kb.Pn1mi;
import org.healthnlp.deepphe.summarization.drools.kb.Pn2;
import org.healthnlp.deepphe.summarization.drools.kb.Pn3;
import org.healthnlp.deepphe.summarization.drools.kb.T0;
import org.healthnlp.deepphe.summarization.drools.kb.T1;
import org.healthnlp.deepphe.summarization.drools.kb.T2;
import org.healthnlp.deepphe.summarization.drools.kb.T3;
import org.healthnlp.deepphe.summarization.drools.kb.T4;
import org.healthnlp.deepphe.summarization.drools.kb.Tis;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TesterBrcaStage {
/*
	private StatefulKnowledgeSession session = null;

	@Before
	public void setUp() {
		try {

			KnowledgeBuilder builder = KnowledgeBuilderFactory
					.newKnowledgeBuilder();
			URL autoloadUrl = getClass().getResource("drools/autoload2");
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
	public void testAllRules() {

		int idCounter = 0;

		System.out.println("\nRunning test M1-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage0-1.1", "004_Stage0-1.2",
				"004_Stage1A-1.1", "004_Stage1A-1.2", "004_Stage1B-1",
				"004_Stage1B-2", "004_Stage2A-1.1", "004_Stage2A-1.2",
				"004_Stage2A-2.1", "004_Stage2A-2.2", "004_Stage2A-3.1",
				"004_Stage2A-3.2", "004_Stage2B-1.1", "004_Stage2B-1.2",
				"004_Stage2B-2.1", "004_Stage2B-2.2", "004_Stage2B-3.1",
				"004_Stage2B-3.2", "004_Stage2B-4.1", "004_Stage2B-4.2",
				"004_Stage2B-5.1", "004_Stage2B-5.2", "004_Stage3B-1.1",
				"004_Stage3B-1.2", "004_Stage3B-2.1", "004_Stage3B-2.2",
				"004_Stage3B-3.1", "004_Stage3B-3.2", "004_Stage3C-1.1",
				"004_Stage3C-1.2", "004_Stage4-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(0, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 0);
	}
	
//	rule "004_Stage0-1.1"
	@Test
	public void testStageZeroDashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage0-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		Tis t = new TisImpl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));


		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage0-1.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}


//	rule "004_Stage0-1.2"
	@Test
	public void testStageZeroDashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage0-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		
		Tis t = new TisImpl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn0 n = new Pn0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));



		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage0-1.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	

//	rule "004_Stage1A-1.1"
	@Test
	public void testStageOneAdashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test Stage1A-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage1A-1.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage1A-1.2"
	@Test
	public void testStageOneAdashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage1A-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn0 n = new Pn0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage1A-1.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage1B-1"
	@Test
	public void testStageOneBdashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage1B-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		T0 t = new T0Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn1mi n = new Pn1miImpl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage1B-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage1B-2"
	@Test
	public void testStageOneBdashTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage1B-2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn1mi n = new Pn1miImpl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { 
				"004_Stage1B-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}

//	rule "004_Stage2A-1.1"
	@Test
	public void testStageTwoAdashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2A-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		T0 t = new T0Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N1 n = new N1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));


		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage2A-1.1"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2A-1.2"
	
	@Test
	public void testStageTwoAdashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2A-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T0 t = new T0Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn1 n = new Pn1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {"004_Stage2A-1.2"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2A-2.1"
	@Test
	public void testStageTwoAdashTwoPointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2A-2.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
	
		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N1 n = new N1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { 
				"004_Stage2A-2.1"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2A-2.2"
	@Test
	public void testStageTwoAdashTwoPointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2A-2.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn1 n = new Pn1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));


		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {"004_Stage2A-2.2"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2A-3.1"
	@Test
	public void testStageTwoAdashThreePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2A-3.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage2A-3.1"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2A-3.2"
	@Test
	public void testStageTwoAdashThreePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2A-3.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn0 n = new Pn0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { 
				"004_Stage2A-3.2"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2B-1.1"
	
	@Test
	public void testStageTwoBdashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2B-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N1 n = new N1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {"004_Stage2B-1.1"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2B-1.2"
	@Test
	public void testStageTwoBdashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2B-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn1 n = new Pn1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage2B-1.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2B-2.1"
	@Test
	public void testStageTwoBdashTwoPointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2B-2.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T3 t = new T3Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { 
				"004_Stage2B-2.1"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage2B-2.2"
	@Test
	public void testStageTwoBdashTwoPointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage2B-2.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T3 t = new T3Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn0 n = new Pn0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {"004_Stage2B-2.2"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3A-1.1"
	@Test
	public void testStageThreeAdashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T0 t = new T0Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N2 n = new N2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-1.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3A-1.2"
	@Test
	public void testStageThreeAdashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T0 t = new T0Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn2 n = new Pn2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-1.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3A-2.1"
	@Test
	public void testStageThreeAdashTwoPointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-2.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N2 n = new N2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-2.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}

//	rule "004_Stage3A-2.2 "

	@Test
	public void testStageThreeAdashTwoPointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-2.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T1 t = new T1Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn2 n = new Pn2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		System.out.println("Number facts is " + numberOfFactsInSession);
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {"004_Stage3A-2.2"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		System.out.println("Number rules fired is " + numRulesFired);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3A-3.1"
	@Test
	public void testStageThreeAdashThreePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-3.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N2 n = new N2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-3.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3A-3.2"
	@Test
	public void testStageThreeAdashThreePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-3.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn2 n = new Pn2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-3.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3A-4.1"
	@Test
	public void testStageThreeAdashFourPointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-4.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T3 t = new T3Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N1 n = new N1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-4.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3A-4.2"
	public void testStageThreeAdashFourPointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-4.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T3 t = new T3Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn0 n = new Pn0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-4.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3A-5.1"
	public void testStageThreeAdashFivePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-5.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-5.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3A-5.2"
	public void testStageThreeAdashFivePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3A-5.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3A-5.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
//	rule "004_Stage3B-1.1"
	@Test
	public void testStageThreeBdashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3B-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3B-1.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3B-1.2"
	@Test
	public void testStageThreeBdashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3B-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn0 n = new Pn0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { 
				"004_Stage3B-1.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	

//	rule "004_Stage3B-2.1"
	@Test
	public void testStageThreeBdashTwoPointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3B-2.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N1 n = new N1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3B-2.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3B-2.2"
	@Test
	public void testStageThreeBdashTwoPointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3B-2.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn1 n = new Pn1Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "004_Stage3B-2.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
	
//	rule "004_Stage3B-3.1"
	@Test
	public void testStageThreeBdashThreePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3B-3.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N2 n = new N2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {
				"004_Stage3B-3.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3B-3.2"
	@Test
	public void testStageThreeBdashThreePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3B-3.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T4 t = new T4Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn2 n = new Pn2Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3B-3.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
	
//	rule "004_Stage3C-1.1"
	@Test
	public void testStageThreeCdashOnePointOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3C-1.1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N3 n = new N3Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage3C-1.1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage3C-1.2"
	@Test
	public void testStageThreeCdashOnePointTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage3C-1.2\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		Pn3 n = new Pn3Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M0 m = new M0Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { 
				"004_Stage3C-1.2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
//	rule "004_Stage4-1"
	@Test
	public void testStageFourDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 004_Stage4-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-stage");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
	    T2 t = new T2Impl();
		t.setSummarizableId(patient.getId());
		factHandles.add(session.insert(t));
		
		N0 n = new N0Impl();
		n.setSummarizableId(patient.getId());
		factHandles.add(session.insert(n));
		
		M1 m = new M1Impl();
		m.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m));
		
		CancerPhenotype cancerPhenotype = new CancerPhenotypeImpl();
		cancerPhenotype.setSummarizableId(patient.getId());
		factHandles.add(session.insert(cancerPhenotype));
		
		Hastclassification hasTclassification = new HastclassificationImpl();
		hasTclassification.setSummarizableId(patient.getId());
		hasTclassification.setDomainId(cancerPhenotype.getId());
		hasTclassification.setRangeId(t.getId());
		factHandles.add(session.insert(hasTclassification));
		
		Hasnclassification hasNclassification = new HasnclassificationImpl();
		hasNclassification.setSummarizableId(patient.getId());
		hasNclassification.setDomainId(cancerPhenotype.getId());
		hasNclassification.setRangeId(n.getId());
		factHandles.add(session.insert(hasNclassification));
		
		Hasmclassification hasMclassification = new HasmclassificationImpl();
		hasMclassification.setSummarizableId(patient.getId());
		hasMclassification.setDomainId(cancerPhenotype.getId());
		hasMclassification.setRangeId(m.getId());
		factHandles.add(session.insert(hasMclassification));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = {  "004_Stage4-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(rulesToTest.length, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
*/
}
