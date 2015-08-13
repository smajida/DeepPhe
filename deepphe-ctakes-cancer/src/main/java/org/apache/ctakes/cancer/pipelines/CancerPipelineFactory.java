package org.apache.ctakes.cancer.pipelines;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.ctakes.assertion.medfacts.cleartk.PolarityCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.UncertaintyCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.TnmAnnotator;
import org.apache.ctakes.cancer.pipelines.CancerPipelineRunner.CopyPropertiesToTemporalEventAnnotator;
import org.apache.ctakes.cancer.pipelines.CancerPipelineRunner.PittHeaderAnnotator;
import org.apache.ctakes.cancer.pipelines.CancerPipelineRunner.PittHeaderCleaner;
import org.apache.ctakes.chunker.ae.Chunker;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.CopyNPChunksToLookupWindowAnnotations;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.RemoveEnclosedLookupWindows;
import org.apache.ctakes.constituency.parser.ae.ConstituencyParser;
import org.apache.ctakes.contexttokenizer.ae.ContextDependentTokenizerAnnotator;
import org.apache.ctakes.core.ae.CDASegmentAnnotator;
import org.apache.ctakes.core.ae.SentenceDetector;
import org.apache.ctakes.core.ae.TokenizerAnnotatorPTB;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.ctakes.core.resource.FileResourceImpl;
import org.apache.ctakes.dependency.parser.ae.ClearNLPDependencyParserAE;
import org.apache.ctakes.dependency.parser.ae.ClearNLPSemanticRoleLabelerAE;
import org.apache.ctakes.dictionary.lookup2.ae.AbstractJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.DefaultJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.JCasTermAnnotator;
import org.apache.ctakes.postagger.POSTagger;
import org.apache.ctakes.relationextractor.ae.DegreeOfRelationExtractorAnnotator;
import org.apache.ctakes.relationextractor.ae.LocationOfRelationExtractorAnnotator;
import org.apache.ctakes.relationextractor.ae.ModifierExtractorAnnotator;
import org.apache.ctakes.temporal.ae.BackwardsTimeAnnotator;
import org.apache.ctakes.temporal.ae.DocTimeRelAnnotator;
import org.apache.ctakes.temporal.ae.EventAnnotator;
import org.apache.ctakes.temporal.ae.EventEventRelationAnnotator;
import org.apache.ctakes.temporal.ae.EventTimeRelationAnnotator;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.cleartk.ml.jar.GenericJarClassifierFactory;

public class CancerPipelineFactory {

	static private final String CTAKES_DIR_PREFIX = "/org/apache/ctakes/";

	public static AnalysisEngineDescription getPipelineDescription()
			throws ResourceInitializationException, InvalidXMLException,
			IOException {
		return getPipelineDescription(null);
	}

	public static AnalysisEngineDescription getPipelineDescription(
			final CancerPipelineOptions options)
			throws ResourceInitializationException, InvalidXMLException,
			IOException {
		final AggregateBuilder aggregateBuilder = new AggregateBuilder();
		aggregateBuilder.add(AnalysisEngineFactory
				.createEngineDescription(PittHeaderAnnotator.class));
		// grab segments using known headers from default + what UPMC has given
		// us.
		aggregateBuilder.add(AnalysisEngineFactory.createEngineDescription(
				CDASegmentAnnotator.class,
				CDASegmentAnnotator.PARAM_SECTIONS_FILE,
				"resources/ccda_sections.txt"));
		// aggregateBuilder.add(AnalysisEngineFactory.createEngineDescription(UpmcSimpleSegmenter.class));
		// core components, dictionary, dependency parser, polarity, uncertainty
		aggregateBuilder.add(SentenceDetector.createAnnotatorDescription());
		aggregateBuilder
				.add(TokenizerAnnotatorPTB.createAnnotatorDescription());
		// aggregateBuilder.add( LvgAnnotator.createAnnotatorDescription() );
		aggregateBuilder.add(ContextDependentTokenizerAnnotator
				.createAnnotatorDescription());
		aggregateBuilder.add(POSTagger.createAnnotatorDescription());
		aggregateBuilder.add(Chunker.createAnnotatorDescription());
		aggregateBuilder.add(ClinicalPipelineFactory
				.getStandardChunkAdjusterAnnotator());

		aggregateBuilder
				.add(AnalysisEngineFactory
						.createEngineDescription(CopyNPChunksToLookupWindowAnnotations.class));
		aggregateBuilder.add(AnalysisEngineFactory
				.createEngineDescription(RemoveEnclosedLookupWindows.class));

		try {
			aggregateBuilder
					.add(AnalysisEngineFactory
							.createEngineDescription(
									DefaultJCasTermAnnotator.class,
									AbstractJCasTermAnnotator.PARAM_WINDOW_ANNOT_PRP,
									"org.apache.ctakes.typesystem.type.textspan.Sentence",
									JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY,
									ExternalResourceFactory
											.createExternalResourceDescription(
													FileResourceImpl.class,
													FileLocator
															.locateFile("org/apache/ctakes/cancer/dictionary/lookup/fast/cancerHsql.xml"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ResourceInitializationException(e);
		}

		aggregateBuilder.add(ClearNLPDependencyParserAE
				.createAnnotatorDescription());
		aggregateBuilder.add(PolarityCleartkAnalysisEngine
				.createAnnotatorDescription());
		aggregateBuilder.add(UncertaintyCleartkAnalysisEngine
				.createAnnotatorDescription());
		aggregateBuilder.add(AnalysisEngineFactory
				.createEngineDescription(ClearNLPSemanticRoleLabelerAE.class));
		aggregateBuilder.add(AnalysisEngineFactory
				.createEngineDescription(ConstituencyParser.class));

		// Cancer deep phe tnm, hormone receptor status annotator. Do before
		// temporal (but after polarity?)
		aggregateBuilder.add(TnmAnnotator.createAnnotatorDescription());

		// temporal components:
		aggregateBuilder.add(EventAnnotator.createAnnotatorDescription());
		aggregateBuilder
				.add(AnalysisEngineFactory
						.createEngineDescription(CopyPropertiesToTemporalEventAnnotator.class));

/** TODO: Relation Extractor is thowing a NPE 
		aggregateBuilder
				.add(DocTimeRelAnnotator
						.createAnnotatorDescription(getStandardModelPath("temporal/ae/doctimerel")));
		aggregateBuilder
				.add(BackwardsTimeAnnotator
						.createAnnotatorDescription(getStandardModelPath("temporal/ae/timeannotator")));
		aggregateBuilder
				.add(EventTimeRelationAnnotator
						.createAnnotatorDescription(getStandardModelPath("temporal/ae/eventtime")));
		
		// options.getEventTimeRelationModelDirectory() + File.separator +
		// "model.jar" ));
		// if( options.getEventEventRelationModelDirectory() != null ) {
		aggregateBuilder
				.add(EventEventRelationAnnotator
						.createAnnotatorDescription(getStandardModelPath("temporal/ae/eventevent")));
		// }
**/
		// UMLS relations:
		aggregateBuilder
				.add(AnalysisEngineFactory
						.createEngineDescription(
								ModifierExtractorAnnotator.class,
								GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
								getStandardModelPath("relationextractor/models/modifier_extractor")));							

		// Kludge to clean out unwanted annotations from the pittsburgh header
		aggregateBuilder.add(AnalysisEngineFactory
				.createEngineDescription(PittHeaderCleaner.class));


		aggregateBuilder.add(AnalysisEngineFactory.createEngineDescription(
				DegreeOfRelationExtractorAnnotator.class,
				GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
				getStandardModelPath("relationextractor/models/degree_of")));
		aggregateBuilder.add(AnalysisEngineFactory.createEngineDescription(
				LocationOfRelationExtractorAnnotator.class,
				GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
				getStandardModelPath("relationextractor/models/location_of")));
		

		// coreference?
		// aggregateBuilder.add(
		// AnalysisEngineFactory.createEngineDescriptionFromPath("../ctakes/ctakes-coreference/desc/MipacqSvmCoreferenceResolverAggregate.xml"));

		return aggregateBuilder.createAggregateDescription();
	}

	static private String getStandardModelPath(final String moduleDirectory) {
		return CTAKES_DIR_PREFIX + moduleDirectory + "/model.jar";
	}
}
