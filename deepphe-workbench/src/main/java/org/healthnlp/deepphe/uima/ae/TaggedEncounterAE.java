package org.healthnlp.deepphe.uima.ae;

import java.io.FileNotFoundException;

import org.apache.ctakes.assertion.medfacts.cleartk.PolarityCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.CancerPipelineTest.AddEvent;
import org.apache.ctakes.cancer.ae.TnmAnnotator;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.CopyNPChunksToLookupWindowAnnotations;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.RemoveEnclosedLookupWindows;
import org.apache.ctakes.clinicalpipeline.ae.ExtractionPrepAnnotator;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.ctakes.core.resource.FileResourceImpl;
import org.apache.ctakes.dependency.parser.ae.ClearNLPDependencyParserAE;
import org.apache.ctakes.dictionary.lookup2.ae.AbstractJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.DefaultJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.JCasTermAnnotator;
import org.apache.ctakes.temporal.ae.BackwardsTimeAnnotator;
import org.apache.ctakes.temporal.ae.DocTimeRelAnnotator;
import org.apache.ctakes.temporal.ae.EventAnnotator;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;

public class TaggedEncounterAE extends TaggedSummarizableAE {

	static private final String TIME_ANNOTATOR_MODEL = "/org/apache/ctakes/temporal/ae/timeannotator/model.jar";
	static private final String EVENT_ANNOTATOR_MODEL = "/org/apache/ctakes/temporal/ae/eventannotator/model.jar";
	static private final String DOCTIMEREL_ANNOTATOR_MODEL = "/org/apache/ctakes/temporal/ae/doctimerel/model.jar";

	private AnalysisEngine cTakesCancerAnalysisEngine = null;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			buildCtakesAggregateAnalysisEngine();
		} catch (FileNotFoundException fnf) {
			throw new ResourceInitializationException(fnf);
		}
	}

	private void buildCtakesAggregateAnalysisEngine() throws ResourceInitializationException, FileNotFoundException {
		final AggregateBuilder builder = new AggregateBuilder();
		builder.add(ClinicalPipelineFactory.getTokenProcessingPipeline());
		builder.add(AnalysisEngineFactory
				.createEngineDescription(CopyNPChunksToLookupWindowAnnotations.class));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(RemoveEnclosedLookupWindows.class));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(
						DefaultJCasTermAnnotator.class,
						AbstractJCasTermAnnotator.PARAM_WINDOW_ANNOT_PRP,
						"org.apache.ctakes.typesystem.type.textspan.Sentence",
						JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY,
						ExternalResourceFactory.createExternalResourceDescription(
								FileResourceImpl.class,
								FileLocator
										.locateFile("org/apache/ctakes/cancer/dictionary/lookup/fast/cancerHsql.xml"))));
		builder.add(ClearNLPDependencyParserAE.createAnnotatorDescription());
		builder.add(BackwardsTimeAnnotator
				.createAnnotatorDescription(TIME_ANNOTATOR_MODEL));
		builder.add(EventAnnotator
				.createAnnotatorDescription(EVENT_ANNOTATOR_MODEL));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(AddEvent.class));
		builder.add(DocTimeRelAnnotator
				.createAnnotatorDescription(DOCTIMEREL_ANNOTATOR_MODEL));
		builder.add(PolarityCleartkAnalysisEngine.createAnnotatorDescription());
		builder.add(AnalysisEngineFactory.createEngineDescription(
				ExtractionPrepAnnotator.class, "AnnotationVersion", 2,
				"AnnotationVersionPropKey", "ANNOTATION_VERSION"));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(TnmAnnotator.class));
		cTakesCancerAnalysisEngine = builder.createAggregate();
	}

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		super.process(multiJCas);
		JCas jCasComponent = fetchActiveJCas(multiJCas);
		if (jCasComponent != null
				&& jCasComponent.getViewName().startsWith("Encounter")) {
			try {
				processEncounter(jCasComponent);
			} catch (ResourceInitializationException e) {
				throw new AnalysisEngineProcessException(e);
			}
		}
	}
	
	private void processEncounter(JCas jCasComponent) throws ResourceInitializationException, AnalysisEngineProcessException {
		JCas encounterJCas = cTakesCancerAnalysisEngine.newJCas();
		encounterJCas.setDocumentText(jCasComponent.getDocumentText());
		cTakesCancerAnalysisEngine.process(encounterJCas);
		boolean aCopySofa = true;
		CasCopier.copyCas(encounterJCas.getCas(), jCasComponent.getCas(), aCopySofa);
	}
	
}
