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
import org.healthnlp.deepphe.summarization.drools.kb.EstrogenReceptorStatus;
import org.healthnlp.deepphe.summarization.drools.kb.Her2NeuStatus;
import org.healthnlp.deepphe.summarization.drools.kb.Indeterminate;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.Negative;
import org.healthnlp.deepphe.summarization.drools.kb.Positive;
import org.healthnlp.deepphe.summarization.drools.kb.ProgesteroneReceptorStatus;
import org.healthnlp.deepphe.summarization.drools.kb.RelationHasinterpretation;
import org.healthnlp.deepphe.summarization.drools.kb.impl.EstrogenReceptorStatusImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.Her2NeuStatusImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.IndeterminateImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.NegativeImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.PositiveImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.ProgesteroneReceptorStatusImpl;
import org.healthnlp.deepphe.summarization.drools.kb.impl.RelationHasinterpretationImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBreastCancerReceptorStatus {

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
	public void testEstrogenReceptorStatus() {

		int idCounter = 0;

		System.out.println("\nRunning testEstrogenReceptorStatus\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-receptor-status");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology Report");

		EstrogenReceptorStatus erStatus = new EstrogenReceptorStatusImpl();
		erStatus.setId(idCounter++);
		erStatus.setSummarizableId(encOne.getId());
		
		Positive positive = new PositiveImpl();
		positive.setId(idCounter++);
		positive.setSummarizableId(encOne.getId());
		
		RelationHasinterpretation hasInterpretation = new RelationHasinterpretationImpl();
		hasInterpretation.setId(idCounter++);
		hasInterpretation.setSummarizableId(encOne.getId());
		
		erStatus.setSummarizableId(encOne.getId());
		hasInterpretation.setDomainId(erStatus.getId());
		hasInterpretation.setRangeId(positive.getId());
		
		session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleErStatus = session.insert(erStatus);
		FactHandle handlePositive = session.insert(positive);
		FactHandle handleHasInterpretation = session.insert(hasInterpretation);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);

		final String[] rulesToTest = { 
				"005_breastCancerReceptorStatus Er"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 9);

		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleHasInterpretation);
		session.retract(handleErStatus);
		session.retract(handlePositive);
	}
	
	@Test
	public void testProgesteronReceptorStatus() {

		int idCounter = 0;

		System.out.println("\nRunning testProgesteronReceptorStatus\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-receptor-status");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Progress Note");

		ProgesteroneReceptorStatus prStatus = new ProgesteroneReceptorStatusImpl();
		prStatus.setId(idCounter++);
		prStatus.setSummarizableId(encOne.getId());
		
		Negative negative = new NegativeImpl();
		negative.setId(idCounter++);
		negative.setSummarizableId(encOne.getId());
		
		RelationHasinterpretation hasInterpretation = new RelationHasinterpretationImpl();
		hasInterpretation.setId(idCounter++);
		hasInterpretation.setSummarizableId(encOne.getId());
		
		prStatus.setSummarizableId(encOne.getId());
		hasInterpretation.setDomainId(prStatus.getId());
		hasInterpretation.setRangeId(negative.getId());
		
		FactHandle handleGoal = session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleErStatus = session.insert(prStatus);
		FactHandle handleNegative = session.insert(negative);
		FactHandle handleHasInterpretation = session.insert(hasInterpretation);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);

		final String[] rulesToTest = { 
				"005_breastCancerReceptorStatus Pr"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 9);

		session.retract(handleGoal);
		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleHasInterpretation);
		session.retract(handleErStatus);
		session.retract(handleNegative);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
		
	}
	
	
	@Test
	public void testHer2NeuReceptorStatus() {

		int idCounter = 0;

		System.out.println("\nRunning testHer2NeuReceptorStatus\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-receptor-status");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);

		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology Report");

		Her2NeuStatus her2NeuStatus = new Her2NeuStatusImpl();
		her2NeuStatus.setId(idCounter++);
		her2NeuStatus.setSummarizableId(encOne.getId());
		
		Indeterminate indeterminate = new IndeterminateImpl();
		indeterminate.setId(idCounter++);
		indeterminate.setSummarizableId(encOne.getId());
		
		RelationHasinterpretation hasInterpretation = new RelationHasinterpretationImpl();
		hasInterpretation.setId(idCounter++);
		hasInterpretation.setSummarizableId(encOne.getId());
		
		her2NeuStatus.setSummarizableId(encOne.getId());
		hasInterpretation.setDomainId(her2NeuStatus.getId());
		hasInterpretation.setRangeId(indeterminate.getId());
		
		FactHandle handleGoal = session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleEncOne = session.insert(encOne);
		FactHandle handleErStatus = session.insert(her2NeuStatus);
		FactHandle handleIndeterminate = session.insert(indeterminate);
		FactHandle handleHasInterpretation = session.insert(hasInterpretation);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);

		final String[] rulesToTest = { 
				"005_breastCancerReceptorStatus Her2"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 9);

		session.retract(handleGoal);
		session.retract(handlePatient);
		session.retract(handleEncOne);
		session.retract(handleHasInterpretation);
		session.retract(handleErStatus);
		session.retract(handleIndeterminate);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
		
	}
	
	@Test
	public void testTriplePositiveReceptorStatus() {

		int idCounter = 0;

		System.out.println("\nRunning testTriplePositiveReceptorStatus\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-receptor-status");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		EstrogenReceptorStatus erStatus = new EstrogenReceptorStatusImpl();
		erStatus.setId(idCounter++);
		erStatus.setSummarizableId(patient.getId());

		ProgesteroneReceptorStatus prStatus = new ProgesteroneReceptorStatusImpl();
		prStatus.setId(idCounter++);
		prStatus.setSummarizableId(patient.getId());

		Her2NeuStatus her2NeuStatus = new Her2NeuStatusImpl();
		her2NeuStatus.setId(idCounter++);
		her2NeuStatus.setSummarizableId(patient.getId());
		
		Positive erPositive = new PositiveImpl();
		erPositive.setId(idCounter++);
		erPositive.setSummarizableId(patient.getId());
		
		Positive prPositive = new PositiveImpl();
		prPositive.setId(idCounter++);
		prPositive.setSummarizableId(patient.getId());
		
		Positive her2NeuPositive = new PositiveImpl();
		her2NeuPositive.setId(idCounter++);
		her2NeuPositive.setSummarizableId(patient.getId());
		
		RelationHasinterpretation erHasInterpretation = new RelationHasinterpretationImpl();
		erHasInterpretation.setId(idCounter++);
		erHasInterpretation.setSummarizableId(patient.getId());
		erHasInterpretation.setDomainId(erStatus.getId());
		erHasInterpretation.setRangeId(erPositive.getId());
		
		RelationHasinterpretation prHasInterpretation = new RelationHasinterpretationImpl();
		prHasInterpretation.setId(idCounter++);
		prHasInterpretation.setSummarizableId(patient.getId());
		prHasInterpretation.setDomainId(prStatus.getId());
		prHasInterpretation.setRangeId(prPositive.getId());
		
		RelationHasinterpretation her2NeuHasInterpretation = new RelationHasinterpretationImpl();
		her2NeuHasInterpretation.setId(idCounter++);
		her2NeuHasInterpretation.setSummarizableId(patient.getId());
		her2NeuHasInterpretation.setDomainId(her2NeuStatus.getId());
		her2NeuHasInterpretation.setRangeId(her2NeuPositive.getId());
		
		FactHandle handleGoal = session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		
		FactHandle handleErStatus = session.insert(erStatus);
		FactHandle handlePrStatus = session.insert(prStatus);
		
		FactHandle handleHer2NeuStatus = session.insert(her2NeuStatus);
		FactHandle handleErPositive = session.insert(erPositive);
		
		FactHandle handlePrPositive = session.insert(prPositive);
		FactHandle handleHer2NeuPositive = session.insert(her2NeuPositive);	
		
		FactHandle handleErHasInterpretation = session.insert(erHasInterpretation);
		FactHandle handlePrHasInterpretation = session.insert(prHasInterpretation);
		FactHandle handleHer2NeuHasInterpretation = session.insert(her2NeuHasInterpretation);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 11);

		final String[] rulesToTest = { 
				"005_breastCancerReceptorStatus Triple Positive"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);

		session.retract(handleGoal);
		session.retract(handlePatient);
		
		session.retract(handleErStatus);
		session.retract(handlePrStatus);
		
		session.retract(handleHer2NeuStatus);
		session.retract(handleErPositive);
		
		session.retract(handlePrPositive);
		session.retract(handleHer2NeuPositive);
		
		session.retract(handleErHasInterpretation);
		session.retract(handlePrHasInterpretation);
		
		session.retract(handleHer2NeuHasInterpretation);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
		
	}
	
	@Test
	public void testTripleNegativeReceptorStatus() {

		int idCounter = 0;

		System.out.println("\nRunning testTripleNegativeReceptorStatus\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-receptor-status");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		EstrogenReceptorStatus erStatus = new EstrogenReceptorStatusImpl();
		erStatus.setId(idCounter++);
		erStatus.setSummarizableId(patient.getId());

		ProgesteroneReceptorStatus prStatus = new ProgesteroneReceptorStatusImpl();
		prStatus.setId(idCounter++);
		prStatus.setSummarizableId(patient.getId());

		Her2NeuStatus her2NeuStatus = new Her2NeuStatusImpl();
		her2NeuStatus.setId(idCounter++);
		her2NeuStatus.setSummarizableId(patient.getId());
		
		Negative erNegative = new NegativeImpl();
		erNegative.setId(idCounter++);
		erNegative.setSummarizableId(patient.getId());
		
		Negative prNegative = new NegativeImpl();
		prNegative.setId(idCounter++);
		prNegative.setSummarizableId(patient.getId());
		
		Negative her2NeuNegative = new NegativeImpl();
		her2NeuNegative.setId(idCounter++);
		her2NeuNegative.setSummarizableId(patient.getId());
		
		RelationHasinterpretation erHasInterpretation = new RelationHasinterpretationImpl();
		erHasInterpretation.setId(idCounter++);
		erHasInterpretation.setSummarizableId(patient.getId());
		erHasInterpretation.setDomainId(erStatus.getId());
		erHasInterpretation.setRangeId(erNegative.getId());
		
		RelationHasinterpretation prHasInterpretation = new RelationHasinterpretationImpl();
		prHasInterpretation.setId(idCounter++);
		prHasInterpretation.setSummarizableId(patient.getId());
		prHasInterpretation.setDomainId(prStatus.getId());
		prHasInterpretation.setRangeId(prNegative.getId());
		
		RelationHasinterpretation her2NeuHasInterpretation = new RelationHasinterpretationImpl();
		her2NeuHasInterpretation.setId(idCounter++);
		her2NeuHasInterpretation.setSummarizableId(patient.getId());
		her2NeuHasInterpretation.setDomainId(her2NeuStatus.getId());
		her2NeuHasInterpretation.setRangeId(her2NeuNegative.getId());
		
		FactHandle handleGoal = session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		
		FactHandle handleErStatus = session.insert(erStatus);
		FactHandle handlePrStatus = session.insert(prStatus);
		
		FactHandle handleHer2NeuStatus = session.insert(her2NeuStatus);
		FactHandle handleErNegative = session.insert(erNegative);
		
		FactHandle handlePrNegative = session.insert(prNegative);
		FactHandle handleHer2NeuNegative = session.insert(her2NeuNegative);	
		
		FactHandle handleErHasInterpretation = session.insert(erHasInterpretation);
		FactHandle handlePrHasInterpretation = session.insert(prHasInterpretation);
		FactHandle handleHer2NeuHasInterpretation = session.insert(her2NeuHasInterpretation);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 11);

		final String[] rulesToTest = { 
				"005_breastCancerReceptorStatus Triple Negative"};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);

		session.retract(handleGoal);
		session.retract(handlePatient);
		
		session.retract(handleErStatus);
		session.retract(handlePrStatus);
		
		session.retract(handleHer2NeuStatus);
		session.retract(handleErNegative);
		
		session.retract(handlePrNegative);
		session.retract(handleHer2NeuNegative);
		
		session.retract(handleErHasInterpretation);
		session.retract(handlePrHasInterpretation);
		
		session.retract(handleHer2NeuHasInterpretation);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
		
	}
	
	@Test
	public void testTripleNegativeCascade() {

		int idCounter = 0;

		System.out.println("\nRunning testTripleNegativeCascade\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-receptor-status");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		KbEncounter encOne = new KbEncounter();
		encOne.setId(idCounter++);
		encOne.setPatientId(patient.getId());
		encOne.setSequence(0);
		encOne.setIsActive(1);
		encOne.setKind("Pathology Report");

		//
		// Er-
		//
		EstrogenReceptorStatus erStatus = new EstrogenReceptorStatusImpl();
		erStatus.setId(idCounter++);
		erStatus.setSummarizableId(encOne.getId());
		
		Negative erNegative = new NegativeImpl();
		erNegative.setId(idCounter++);
		erNegative.setSummarizableId(encOne.getId());
		
		RelationHasinterpretation erHasInterpretation = new RelationHasinterpretationImpl();
		erHasInterpretation.setId(idCounter++);
		erHasInterpretation.setSummarizableId(encOne.getId());
		erHasInterpretation.setDomainId(erStatus.getId());
		erHasInterpretation.setRangeId(erNegative.getId());
		
		//
		// Pr-
		//
		ProgesteroneReceptorStatus prStatus = new ProgesteroneReceptorStatusImpl();
		prStatus.setId(idCounter++);
		prStatus.setSummarizableId(encOne.getId());
		
		Negative prNegative = new NegativeImpl();
		prNegative.setId(idCounter++);
		prNegative.setSummarizableId(encOne.getId());
		
		RelationHasinterpretation prHasInterpretation = new RelationHasinterpretationImpl();
		prHasInterpretation.setId(idCounter++);
		prHasInterpretation.setSummarizableId(encOne.getId());
		prHasInterpretation.setDomainId(prStatus.getId());
		prHasInterpretation.setRangeId(prNegative.getId());
		
		// Her2Neu-
		Her2NeuStatus her2NeuStatus = new Her2NeuStatusImpl();
		her2NeuStatus.setId(idCounter++);
		her2NeuStatus.setSummarizableId(encOne.getId());
		
		Negative her2NeuNegative = new NegativeImpl();
		her2NeuNegative.setId(idCounter++);
		her2NeuNegative.setSummarizableId(encOne.getId());
		
		RelationHasinterpretation her2NeuHasInterpretation = new RelationHasinterpretationImpl();
		her2NeuHasInterpretation.setId(idCounter++);
		her2NeuHasInterpretation.setSummarizableId(encOne.getId());
		her2NeuHasInterpretation.setDomainId(her2NeuStatus.getId());
		her2NeuHasInterpretation.setRangeId(her2NeuNegative.getId());
		
	
		session.insert(goal);
		session.insert(patient);
		session.insert(encOne);
		session.insert(erStatus);
		session.insert(erNegative);
		session.insert(erHasInterpretation);
		session.insert(prStatus);
		session.insert(prNegative);
		session.insert(prHasInterpretation);
		session.insert(her2NeuStatus);
		session.insert(her2NeuNegative);
		session.insert(her2NeuHasInterpretation);
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);

		final String[] rulesToTest = { 
				"005_breastCancerReceptorStatus Er",
				"005_breastCancerReceptorStatus Pr",
				"005_breastCancerReceptorStatus Her2",
				"005_breastCancerReceptorStatus Triple Negative",
				"005_breastCancerReceptorStatus Triple Positive",
				"005_breastCancerReceptorStatus Transition Goal State"
				
		};
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(5, numRulesFired);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 21);
		
	}
}
