package org.healthnlp.deepphe.summarization.drools.kb.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.M1;
import org.healthnlp.deepphe.summarization.drools.kb.Pn1;
import org.healthnlp.deepphe.summarization.drools.kb.Pn1mi;
import org.healthnlp.deepphe.summarization.drools.kb.Tis;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBrcaTnm {

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
	public void testMoneDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test M1-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-tnm");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		M1 m1 = new M1Impl();
		m1.setId(idCounter++);
		m1.setSummarizableId(patient.getId());
		factHandles.add(session.insert(m1));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "003_M1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 3);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
	}

	@Test
	public void testTisDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test M1-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-tnm");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		Tis tis = new TisImpl();
		tis.setId(idCounter++);
		tis.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tis));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "003_Tis-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 7);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 7);
	}

	@Test
	public void testPnOneMiDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test pN1m1-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-tnm");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		Pn1mi tis = new Pn1miImpl();
		tis.setId(idCounter++);
		tis.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tis));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "003_pN1m1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 9);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 9);
	}

	@Test
	public void testPnOneDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 003_pN1-1\n");

		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-tnm");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));

		Pn1 tis = new Pn1Impl();
		tis.setId(idCounter++);
		tis.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tis));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "003_pN1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 9);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 9);
	}

	

}
