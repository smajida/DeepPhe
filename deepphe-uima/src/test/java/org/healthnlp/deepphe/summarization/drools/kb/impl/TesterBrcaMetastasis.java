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
import org.healthnlp.deepphe.summarization.drools.kb.BodySite;
import org.healthnlp.deepphe.summarization.drools.kb.HasBodyModifier;
import org.healthnlp.deepphe.summarization.drools.kb.HasBodySite;
import org.healthnlp.deepphe.summarization.drools.kb.HasTumorType;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.Left;
import org.healthnlp.deepphe.summarization.drools.kb.LocalRecurrence;
import org.healthnlp.deepphe.summarization.drools.kb.PrimaryTumor;
import org.healthnlp.deepphe.summarization.drools.kb.Right;
import org.healthnlp.deepphe.summarization.drools.kb.SurgicalScar;
import org.healthnlp.deepphe.summarization.drools.kb.Tumor;
import org.healthnlp.deepphe.summarization.drools.kb.TumorType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterBrcaMetastasis {
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
	public void testLocalRecurrenceOneDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test localRecurrence1-1\n");
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-metastasis");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));
	
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		LocalRecurrence local = new LocalRecurrenceImpl();
		local.setId(idCounter++);
		local.setSummarizableId(patient.getId());
		factHandles.add(session.insert(local));
		
		Tumor tumorOne = new TumorImpl();
		tumorOne.setId(idCounter++);
		tumorOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOne));
		
		TumorType tumorOneType = new TumorTypeImpl();
		tumorOneType.setId(idCounter++);
		tumorOneType.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOneType));
		
		HasTumorType hasTumorTypeOne = new HasTumorTypeImpl();
		hasTumorTypeOne.setId(idCounter++);
		hasTumorTypeOne.setSummarizableId(patient.getId());
		hasTumorTypeOne.setDomainId(tumorOne.getId());
		hasTumorTypeOne.setRangeId(tumorOneType.getId());
		factHandles.add(session.insert(hasTumorTypeOne));
	
		Tumor tumorTwo = new TumorImpl();
		tumorTwo.setId(idCounter++);
		tumorTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorTwo));

		BodySite siteOne = new BodySiteImpl();
		siteOne.setId(idCounter++);
		siteOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteOne));
		
		BodySite siteTwo = new BodySiteImpl();
		siteTwo.setId(idCounter++);
		siteTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteTwo));
		
		HasBodySite hasBodySiteOne = new HasBodySiteImpl();
		hasBodySiteOne.setId(idCounter++);
		hasBodySiteOne.setSummarizableId(patient.getId());
		hasBodySiteOne.setDomainId(tumorOne.getId());
		hasBodySiteOne.setRangeId(siteOne.getId());
		factHandles.add(session.insert(hasBodySiteOne));
		
		HasBodySite hasBodySiteTwo = new HasBodySiteImpl();
		hasBodySiteTwo.setId(idCounter++);
		hasBodySiteTwo.setSummarizableId(patient.getId());
		hasBodySiteTwo.setDomainId(tumorTwo.getId());
		hasBodySiteTwo.setRangeId(siteTwo.getId());
		factHandles.add(session.insert(hasBodySiteTwo));

		Right rightOne = new RightImpl();
		rightOne.setId(idCounter++);
		rightOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(rightOne));
		
		Right rightTwo = new RightImpl();
		rightTwo.setId(idCounter++);
		rightTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(rightTwo));
		
		HasBodyModifier hasBodyModifierOne = new HasBodyModifierImpl();
		hasBodyModifierOne.setId(idCounter++);
		hasBodyModifierOne.setSummarizableId(patient.getId());
		hasBodyModifierOne.setDomainId(siteOne.getId());
		hasBodyModifierOne.setRangeId(rightOne.getId());
		factHandles.add(session.insert(hasBodyModifierOne));
		
		HasBodyModifier hasBodyModifierTwo = new HasBodyModifierImpl();
		hasBodyModifierTwo.setId(idCounter++);
		hasBodyModifierTwo.setSummarizableId(patient.getId());
		hasBodyModifierTwo.setDomainId(siteTwo.getId());
		hasBodyModifierTwo.setRangeId(rightTwo.getId());
		factHandles.add(session.insert(hasBodyModifierTwo));
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "006_localRecurrence1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
	@Test
	public void testLocalRecurrenceOneDashTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test localRecurrence1-2\n");
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-metastasis");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));
	
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		LocalRecurrence local = new LocalRecurrenceImpl();
		local.setId(idCounter++);
		local.setSummarizableId(patient.getId());
		factHandles.add(session.insert(local));
		
		Tumor tumorOne = new TumorImpl();
		tumorOne.setId(idCounter++);
		tumorOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOne));
		
		TumorType tumorOneType = new TumorTypeImpl();
		tumorOneType.setId(idCounter++);
		tumorOneType.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOneType));
		
		HasTumorType hasTumorTypeOne = new HasTumorTypeImpl();
		hasTumorTypeOne.setId(idCounter++);
		hasTumorTypeOne.setSummarizableId(patient.getId());
		hasTumorTypeOne.setDomainId(tumorOne.getId());
		hasTumorTypeOne.setRangeId(tumorOneType.getId());
		factHandles.add(session.insert(hasTumorTypeOne));
	
		Tumor tumorTwo = new TumorImpl();
		tumorTwo.setId(idCounter++);
		tumorTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorTwo));

		BodySite siteOne = new BodySiteImpl();
		siteOne.setId(idCounter++);
		siteOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteOne));
		
		BodySite siteTwo = new BodySiteImpl();
		siteTwo.setId(idCounter++);
		siteTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteTwo));
		
		HasBodySite hasBodySiteOne = new HasBodySiteImpl();
		hasBodySiteOne.setId(idCounter++);
		hasBodySiteOne.setSummarizableId(patient.getId());
		hasBodySiteOne.setDomainId(tumorOne.getId());
		hasBodySiteOne.setRangeId(siteOne.getId());
		factHandles.add(session.insert(hasBodySiteOne));
		
		HasBodySite hasBodySiteTwo = new HasBodySiteImpl();
		hasBodySiteTwo.setId(idCounter++);
		hasBodySiteTwo.setSummarizableId(patient.getId());
		hasBodySiteTwo.setDomainId(tumorTwo.getId());
		hasBodySiteTwo.setRangeId(siteTwo.getId());
		factHandles.add(session.insert(hasBodySiteTwo));

		Left leftOne = new LeftImpl();
		leftOne.setId(idCounter++);
		leftOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(leftOne));
		
		Left leftTwo = new LeftImpl();
		leftTwo.setId(idCounter++);
		leftTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(leftTwo));
		
		HasBodyModifier hasBodyModifierOne = new HasBodyModifierImpl();
		hasBodyModifierOne.setId(idCounter++);
		hasBodyModifierOne.setSummarizableId(patient.getId());
		hasBodyModifierOne.setDomainId(siteOne.getId());
		hasBodyModifierOne.setRangeId(leftOne.getId());
		factHandles.add(session.insert(hasBodyModifierOne));
		
		HasBodyModifier hasBodyModifierTwo = new HasBodyModifierImpl();
		hasBodyModifierTwo.setId(idCounter++);
		hasBodyModifierTwo.setSummarizableId(patient.getId());
		hasBodyModifierTwo.setDomainId(siteTwo.getId());
		hasBodyModifierTwo.setRangeId(leftTwo.getId());
		factHandles.add(session.insert(hasBodyModifierTwo));
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "006_localRecurrence1-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
	@Test
	public void testLocalRecurrenceTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test localRecurrence2\n");
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-metastasis");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));
	
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		Tumor tumor = new TumorImpl();
		tumor.setId(idCounter++);
		tumor.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumor));
		
		SurgicalScar surgicalScar = new SurgicalScarImpl();
		surgicalScar.setId(idCounter++);
		surgicalScar.setSummarizableId(patient.getId());
		factHandles.add(session.insert(surgicalScar));
		
		BodySite bodySite = new BodySiteImpl();
		bodySite.setId(idCounter++);
		bodySite.setSummarizableId(patient.getId());
		factHandles.add(session.insert(bodySite));
		
		HasBodySite hasBodySiteForTumor = new HasBodySiteImpl();
		hasBodySiteForTumor.setId(idCounter++);
		hasBodySiteForTumor.setSummarizableId(patient.getId());
		hasBodySiteForTumor.setDomainId(tumor.getId());
		hasBodySiteForTumor.setRangeId(bodySite.getId());
		factHandles.add(session.insert(hasBodySiteForTumor));
		
		HasBodySite hasBodySiteForScar = new HasBodySiteImpl();
		hasBodySiteForScar.setId(idCounter++);
		hasBodySiteForScar.setSummarizableId(patient.getId());
		hasBodySiteForScar.setDomainId(surgicalScar.getId());
		hasBodySiteForScar.setRangeId(bodySite.getId());
		factHandles.add(session.insert(hasBodySiteForScar));

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "006_localRecurrence2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 2);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 2);
	}
	
	
	@Test
	public void testSecondPrimaryOneDashOne() {

		int idCounter = 0;

		System.out.println("\nRunning test 006_secondPrimary1-1\n");
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-metastasis");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));
		
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		PrimaryTumor primary = new PrimaryTumorImpl();
		primary.setId(idCounter++);
		primary.setSummarizableId(patient.getId());
		factHandles.add(session.insert(primary));
		
		Tumor tumorOne = new TumorImpl();
		tumorOne.setId(idCounter++);
		tumorOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOne));
		
		TumorType tumorOneType = new TumorTypeImpl();
		tumorOneType.setId(idCounter++);
		tumorOneType.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOneType));
		
		HasTumorType hasTumorTypeOne = new HasTumorTypeImpl();
		hasTumorTypeOne.setId(idCounter++);
		hasTumorTypeOne.setSummarizableId(patient.getId());
		hasTumorTypeOne.setDomainId(tumorOne.getId());
		hasTumorTypeOne.setRangeId(tumorOneType.getId());
		factHandles.add(session.insert(hasTumorTypeOne));
	
		Tumor tumorTwo = new TumorImpl();
		tumorTwo.setId(idCounter++);
		tumorTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorTwo));

		BodySite siteOne = new BodySiteImpl();
		siteOne.setId(idCounter++);
		siteOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteOne));
		
		BodySite siteTwo = new BodySiteImpl();
		siteTwo.setId(idCounter++);
		siteTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteTwo));

		Right right = new RightImpl();
		right.setId(idCounter++);
		right.setSummarizableId(patient.getId());
		factHandles.add(session.insert(right));
		
		Left left = new LeftImpl();
		left.setId(idCounter++);
		left.setSummarizableId(patient.getId());
		factHandles.add(session.insert(left));
		
		HasBodySite hasBodySiteOne = new HasBodySiteImpl();
		hasBodySiteOne.setId(idCounter++);
		hasBodySiteOne.setSummarizableId(patient.getId());
		hasBodySiteOne.setDomainId(tumorOne.getId());
		hasBodySiteOne.setRangeId(siteOne.getId());
		factHandles.add(session.insert(hasBodySiteOne));
		
		HasBodyModifier hasBodyModifierOne = new HasBodyModifierImpl();
		hasBodyModifierOne.setId(idCounter++);
		hasBodyModifierOne.setSummarizableId(patient.getId());
		hasBodyModifierOne.setDomainId(siteOne.getId());
		hasBodyModifierOne.setRangeId(right.getId());
		factHandles.add(session.insert(hasBodyModifierOne));
		
		HasBodySite hasBodySiteTwo = new HasBodySiteImpl();
		hasBodySiteTwo.setId(idCounter++);
		hasBodySiteTwo.setSummarizableId(patient.getId());
		hasBodySiteTwo.setDomainId(tumorTwo.getId());
		hasBodySiteTwo.setRangeId(siteTwo.getId());
		factHandles.add(session.insert(hasBodySiteTwo));

		HasBodyModifier hasBodyModifierTwo = new HasBodyModifierImpl();
		hasBodyModifierTwo.setId(idCounter++);
		hasBodyModifierTwo.setSummarizableId(patient.getId());
		hasBodyModifierTwo.setDomainId(siteTwo.getId());
		hasBodyModifierTwo.setRangeId(left.getId());
		factHandles.add(session.insert(hasBodyModifierTwo));
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "006_secondPrimary1-1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size() + 1);

		for (FactHandle factHandle : factHandles) {
			session.retract(factHandle);
		}
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 1);
	}
	
	@Test
	public void testSecondPrimaryOneDashTwo() {

		int idCounter = 0;

		System.out.println("\nRunning test 006_secondPrimary1-2\n");
		
		final List<FactHandle> factHandles = new ArrayList<FactHandle>();

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-metastasis");
		goal.setIsActive(1);
		factHandles.add(session.insert(goal));
		
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		factHandles.add(session.insert(patient));
		
		PrimaryTumor primary = new PrimaryTumorImpl();
		primary.setId(idCounter++);
		primary.setSummarizableId(patient.getId());
		factHandles.add(session.insert(primary));
		
		Tumor tumorOne = new TumorImpl();
		tumorOne.setId(idCounter++);
		tumorOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOne));
		
		TumorType tumorOneType = new TumorTypeImpl();
		tumorOneType.setId(idCounter++);
		tumorOneType.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorOneType));
		
		HasTumorType hasTumorTypeOne = new HasTumorTypeImpl();
		hasTumorTypeOne.setId(idCounter++);
		hasTumorTypeOne.setSummarizableId(patient.getId());
		hasTumorTypeOne.setDomainId(tumorOne.getId());
		hasTumorTypeOne.setRangeId(tumorOneType.getId());
		factHandles.add(session.insert(hasTumorTypeOne));
	
		Tumor tumorTwo = new TumorImpl();
		tumorTwo.setId(idCounter++);
		tumorTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(tumorTwo));

		BodySite siteOne = new BodySiteImpl();
		siteOne.setId(idCounter++);
		siteOne.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteOne));
		
		BodySite siteTwo = new BodySiteImpl();
		siteTwo.setId(idCounter++);
		siteTwo.setSummarizableId(patient.getId());
		factHandles.add(session.insert(siteTwo));

		Right right = new RightImpl();
		right.setId(idCounter++);
		right.setSummarizableId(patient.getId());
		factHandles.add(session.insert(right));
		
		Left left = new LeftImpl();
		left.setId(idCounter++);
		left.setSummarizableId(patient.getId());
		factHandles.add(session.insert(left));
		
		HasBodySite hasBodySiteOne = new HasBodySiteImpl();
		hasBodySiteOne.setId(idCounter++);
		hasBodySiteOne.setSummarizableId(patient.getId());
		hasBodySiteOne.setDomainId(tumorOne.getId());
		hasBodySiteOne.setRangeId(siteOne.getId());
		factHandles.add(session.insert(hasBodySiteOne));
		
		HasBodyModifier hasBodyModifierOne = new HasBodyModifierImpl();
		hasBodyModifierOne.setId(idCounter++);
		hasBodyModifierOne.setSummarizableId(patient.getId());
		hasBodyModifierOne.setDomainId(siteOne.getId());
		hasBodyModifierOne.setRangeId(left.getId());
		factHandles.add(session.insert(hasBodyModifierOne));
		
		HasBodySite hasBodySiteTwo = new HasBodySiteImpl();
		hasBodySiteTwo.setId(idCounter++);
		hasBodySiteTwo.setSummarizableId(patient.getId());
		hasBodySiteTwo.setDomainId(tumorTwo.getId());
		hasBodySiteTwo.setRangeId(siteTwo.getId());
		factHandles.add(session.insert(hasBodySiteTwo));

		HasBodyModifier hasBodyModifierTwo = new HasBodyModifierImpl();
		hasBodyModifierTwo.setId(idCounter++);
		hasBodyModifierTwo.setSummarizableId(patient.getId());
		hasBodyModifierTwo.setDomainId(siteTwo.getId());
		hasBodyModifierTwo.setRangeId(right.getId());
		factHandles.add(session.insert(hasBodyModifierTwo));
		
		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, factHandles.size());

		final String[] rulesToTest = { "006_secondPrimary1-2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

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
