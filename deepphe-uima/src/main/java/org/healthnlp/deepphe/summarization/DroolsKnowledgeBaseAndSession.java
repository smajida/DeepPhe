
package org.healthnlp.deepphe.summarization;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;

public class DroolsKnowledgeBaseAndSession {
// Olga commented out the guts - doesn't work with a new drools engine
	/*
implements WorkingMemoryEventListener {

	private static DroolsKnowledgeBaseAndSession singleton = null;
	
	private static final String DROOLS_AUTO_LOADED_RULES_PATH = "src\\main\\resources\\drools\\autoload2";
	
	private final KnowledgeBase knowledgeBase = KnowledgeBaseFactory
			.newKnowledgeBase();

	private StatefulKnowledgeSession session;

	public static DroolsKnowledgeBaseAndSession getInstance() {
		if (singleton == null) {
			singleton = new DroolsKnowledgeBaseAndSession();
		}
		return singleton;
	}
	
	public DroolsKnowledgeBaseAndSession() {
		loadProductionDroolsFiles();
		session = knowledgeBase.newStatefulKnowledgeSession();
		session.addEventListener(this);
	}

	private void loadProductionDroolsFiles() {
		try {
			KnowledgeBuilder builder = KnowledgeBuilderFactory
					.newKnowledgeBuilder();
			File autoloadDirectory = new File(DROOLS_AUTO_LOADED_RULES_PATH);
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
			} else {
				knowledgeBase.addKnowledgePackages(builder
						.getKnowledgePackages());
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public void insert(KbIdentified identified) {
		session.insert(identified);
	}
	
	public Collection<KbIdentified> getIdentified() {
		final List<KbIdentified> identified = new ArrayList<>();
		for (Object obj :  session.getObjects(new IdentifiedObjectFilter())) {
			identified.add((KbIdentified)obj);
		}
		return identified;
	}
	
	public void execute() {
		session.fireAllRules();
	}
	
	public void clearDrools() {
		for (FactHandle factHandle : session.getFactHandles()) {
			session.retract(factHandle);
		}
	}
	
	public void resetDrools() {
		Collection<KnowledgePackage> knowledgePackages = knowledgeBase.getKnowledgePackages();
		for (KnowledgePackage pkg :  knowledgePackages) {
			knowledgeBase.removeKnowledgePackage(pkg.getName());
		}
		session = knowledgeBase.newStatefulKnowledgeSession();
	}

	public void executeDrools(String command) {
		KnowledgeBuilder builder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		Resource rulesResource = ResourceFactory
				.newByteArrayResource(command.getBytes());
		builder.add(rulesResource, ResourceType.DRL);
		final Collection<KbIdentified> identifieds = getIdentified();
		knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
		session = knowledgeBase.newStatefulKnowledgeSession();
		for (KbIdentified identified : identifieds) {
			session.insert(identified);
		}
		session.addEventListener(this);
	}

	public void displayFacts() {
		for (FactHandle factHandle : session.getFactHandles()) {
			KbIdentified identified = (KbIdentified) session.getObject(factHandle);
			System.out.println(identified.getClass().getSimpleName() + " <" + identified.getId() + ">");
		}
	}

	@Override
	public void objectInserted(ObjectInsertedEvent obj) {
		System.out.println("<DROOLS-ENGINE> inserted a " + obj.getObject().getClass().getSimpleName());
	}

	@Override
	public void objectRetracted(ObjectRetractedEvent obj) {
		System.out.println("<DROOLS-ENGINE> deleted a " + obj.getOldObject().getClass().getSimpleName());
	}

	@Override
	public void objectUpdated(ObjectUpdatedEvent obj) {
		System.out.println("<DROOLS-ENGINE> updated a " + obj.getObject().getClass().getSimpleName());
	}
	

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public StatefulKnowledgeSession getSession() {
		return session;
	}
*/

}

