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
import org.healthnlp.deepphe.summarization.drools.kb.Breast;
import org.healthnlp.deepphe.summarization.drools.kb.KbGoal;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbRelation;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.summarization.drools.kb.M1;
import org.healthnlp.deepphe.summarization.drools.kb.Tumor;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


@Ignore
public class TesterBreastCancerMetastasis {
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
	public void testMet1() {

		int idCounter = 0;

		System.out.println("\nRunning test Met1\n");

		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setName("extract-metastasis");
		goal.setIsActive(1);
	
		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		M1 m1 = new M1Impl();
		m1.setId(idCounter++);
		m1.setSummarizableId(patient.getId());
		
		FactHandle handleGoal = session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleM1 = session.insert(m1);

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);

		final String[] rulesToTest = { "006_breastCancerMetastasis Met1" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 6);

		session.retract(handleGoal);
		session.retract(handlePatient);
		session.retract(handleM1);
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 3);
	}
	
	@Test
	public void testMet2() {
		
		int idCounter = 0;

		System.out.println("\nRunning testMet2\n");
		
		final List<FactHandle> handles = new ArrayList<FactHandle>();
		
		KbGoal goal = new KbGoal();
		goal.setId(idCounter++);
		goal.setIsActive(1);
		goal.setName("extract-metastasis");

		KbPatient patient = new KbPatient();
		patient.setId(idCounter++);
		
		Tumor tumorOne = new TumorImpl();
		tumorOne.setId(idCounter++);
		tumorOne.setSummarizableId(patient.getId());
	    KbSummary tumorTypeOne = new KbSummary();
	    StringBuilder sb = new StringBuilder();
		sb.append("<xs:element name=\"tumorType\">");
		sb.append("<xs:simpleType>");
		sb.append("<xs:restriction base=\"xs:string\">");
		sb.append("<xs:enumeration value=\"primary\"/>");
		sb.append("<xs:enumeration value=\"metastatic\"/>");
		sb.append("<xs:enumeration value=\"recurrence\"/>");
		sb.append("</xs:restriction>");
		sb.append("</xs:simpleType>");
		sb.append("</xs:element>");
	    tumorTypeOne.setId(idCounter++);
	    tumorTypeOne.setSummarizableId(patient.getId());
	    tumorTypeOne.setBaseCode(sb.toString());
	    tumorTypeOne.setCode("primary");
	    KbRelation hasTumorTypeOne = new KbRelation();
	    hasTumorTypeOne.setId(idCounter++);
	    hasTumorTypeOne.setCode("hasTumorType");
	    hasTumorTypeOne.setSummarizableId(patient.getId());
	    hasTumorTypeOne.setDomainId(tumorOne.getId());
	    hasTumorTypeOne.setRangeId(tumorTypeOne.getId());
	    Breast breastOne = new BreastImpl();
	    breastOne.setId(idCounter++);
	    breastOne.setSummarizableId(patient.getId());
	    HasBodySiteImpl hasBodyLocationOne = new HasBodySiteImpl();
	    hasBodyLocationOne.setId(idCounter++);
	    hasBodyLocationOne.setSummarizableId(patient.getId());
	    hasBodyLocationOne.setDomainId(tumorOne.getId());
	    hasBodyLocationOne.setRangeId(breastOne.getId());
	    
	    FactHandle handleGoal = session.insert(goal);
		FactHandle handlePatient = session.insert(patient);
		FactHandle handleTumorOne = session.insert(tumorOne);
		FactHandle handleTumorTypeOne = session.insert(tumorTypeOne);
		FactHandle handleHasTumorTypeOne = session.insert(hasTumorTypeOne);
		FactHandle handleBreastOne = session.insert(breastOne);
		FactHandle handleHasBodyLocationOne = session.insert(hasBodyLocationOne);
		
		handles.add(handleGoal);
		handles.add(handlePatient);
		handles.add(handleTumorOne);
		handles.add(handleTumorTypeOne);
		handles.add(handleHasTumorTypeOne);
		handles.add(handleBreastOne);
		handles.add(handleHasBodyLocationOne);
		
		Tumor tumorTwo = new TumorImpl();
		tumorTwo.setId(idCounter++);
		tumorTwo.setSummarizableId(patient.getId());
	    KbSummary tumorTypeTwo = new KbSummary();
	    tumorTypeTwo.setId(idCounter++);
	    tumorTypeTwo.setSummarizableId(patient.getId());
	    tumorTypeTwo.setBaseCode(sb.toString());
	    tumorTypeTwo.setCode("");
	    KbRelation hasTumorTypeTwo = new KbRelation();
	    hasTumorTypeTwo.setId(idCounter++);
	    hasTumorTypeTwo.setCode("hasTumorType");
	    hasTumorTypeTwo.setSummarizableId(patient.getId());
	    hasTumorTypeTwo.setDomainId(tumorTwo.getId());
	    hasTumorTypeTwo.setRangeId(tumorTypeTwo.getId());
	    BodySite bodySiteTwo = new BodySiteImpl();
	    bodySiteTwo.setId(idCounter++);
	    bodySiteTwo.setSummarizableId(patient.getId());
	    HasBodySiteImpl hasBodyLocationTwo = new HasBodySiteImpl();
	    hasBodyLocationTwo.setId(idCounter++);
	    hasBodyLocationTwo.setSummarizableId(patient.getId());
	    hasBodyLocationTwo.setDomainId(tumorTwo.getId());
	    hasBodyLocationTwo.setRangeId(bodySiteTwo.getId());
	    
		FactHandle handleTumorTwo = session.insert(tumorTwo);
		FactHandle handleTumorTypeTwo = session.insert(tumorTypeTwo);
		FactHandle handleHasTumorTypeTwo = session.insert(hasTumorTypeTwo);
		FactHandle handleBodySiteTwo = session.insert(bodySiteTwo);
		FactHandle handleHasBodyLocationTwo = session.insert(hasBodyLocationTwo);
		
		handles.add(handleTumorTwo);
		handles.add(handleTumorTypeTwo);
		handles.add(handleHasTumorTypeTwo);
		handles.add(handleBodySiteTwo);
		handles.add(handleHasBodyLocationTwo);

		long numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);
		
		assertEquals(tumorTypeTwo.getCode(),"");

		final String[] rulesToTest = { "006_breastCancerMetastasis Met2" };
		CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(
				rulesToTest);
		int numRulesFired = session.fireAllRules(customAgendaFilter);
		assertEquals(1, numRulesFired);

		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 12);
		
		assertEquals(tumorTypeTwo.getCode(),"metastatic");

		for (FactHandle handle : handles) {
			session.retract(handle);
		}
		
		numberOfFactsInSession = session.getFactCount();
		assertEquals(numberOfFactsInSession, 0);
		
		
	}
*/
}
