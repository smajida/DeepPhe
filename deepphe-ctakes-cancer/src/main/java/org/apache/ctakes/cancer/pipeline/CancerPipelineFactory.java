package org.apache.ctakes.cancer.pipeline;


import javax.annotation.concurrent.Immutable;

import org.apache.ctakes.assertion.medfacts.cleartk.PolarityCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.UncertaintyCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.CancerPropertiesAnnotator;
import org.apache.ctakes.cancer.ae.PittHeaderAnnotator;
import org.apache.ctakes.cancer.ae.PittHeaderCleaner;
import org.apache.ctakes.cancer.ae.PropertyToEventCopier;
import org.apache.ctakes.chunker.ae.Chunker;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.CopyNPChunksToLookupWindowAnnotations;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.RemoveEnclosedLookupWindows;
import org.apache.ctakes.constituency.parser.ae.ConstituencyParser;
import org.apache.ctakes.contexttokenizer.ae.ContextDependentTokenizerAnnotator;
import org.apache.ctakes.core.ae.SentenceDetector;
import org.apache.ctakes.core.ae.TokenizerAnnotatorPTB;
import org.apache.ctakes.core.cc.XmiWriterCasConsumerCtakes;
import org.apache.ctakes.core.cr.FilesInDirectoryCollectionReader;
import org.apache.ctakes.dependency.parser.ae.ClearNLPDependencyParserAE;
import org.apache.ctakes.dependency.parser.ae.ClearNLPSemanticRoleLabelerAE;
import org.apache.ctakes.dictionary.lookup2.ae.DefaultJCasTermAnnotator;
import org.apache.ctakes.lvg.ae.LvgAnnotator;
import org.apache.ctakes.postagger.POSTagger;
import org.apache.ctakes.relationextractor.ae.DegreeOfRelationExtractorAnnotator;
import org.apache.ctakes.relationextractor.ae.LocationOfRelationExtractorAnnotator;
import org.apache.ctakes.relationextractor.ae.ModifierExtractorAnnotator;
import org.apache.ctakes.temporal.ae.BackwardsTimeAnnotator;
import org.apache.ctakes.temporal.ae.DocTimeRelAnnotator;
import org.apache.ctakes.temporal.ae.EventAnnotator;
import org.apache.ctakes.temporal.ae.EventEventRelationAnnotator;
import org.apache.ctakes.temporal.ae.EventTimeRelationAnnotator;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.cleartk.ml.jar.GenericJarClassifierFactory;

@Immutable
final public class CancerPipelineFactory {

//   Is the ExtractionPrepAnnotator necessary?
//   builder.add( AnalysisEngineFactory.createEngineDescription( ExtractionPrepAnnotator.class,
//         "AnnotationVersion", 2,
//         "AnnotationVersionPropKey", "ANNOTATION_VERSION" ) );



   static private final String CTAKES_DIR_PREFIX = "/org/apache/ctakes/";

   private CancerPipelineFactory() {}

   public static AnalysisEngine createPipelineEngine() throws ResourceInitializationException {
      return createPipelineEngine( null );
   }

   public static AnalysisEngine createPipelineEngine( final CancerPipelineOptions options )
         throws ResourceInitializationException {
      final AggregateBuilder aggregateBuilder = createAggregateBuilder();
      return aggregateBuilder.createAggregate();
   }


   public static AnalysisEngineDescription createPipelineDescription() throws ResourceInitializationException {
      return createPipelineDescription( null );
   }

   public static AnalysisEngineDescription createPipelineDescription( final CancerPipelineOptions options )
         throws ResourceInitializationException {
      final AggregateBuilder aggregateBuilder = createAggregateBuilder();
      return aggregateBuilder.createAggregateDescription();
   }

   private static AggregateBuilder createAggregateBuilder() throws ResourceInitializationException {
      final AggregateBuilder aggregateBuilder = new AggregateBuilder();

      addPittHeaderEngines( aggregateBuilder );
      addCoreEngines( aggregateBuilder );
      addDictionaryEngines( aggregateBuilder );
      addAttributeEngines( aggregateBuilder );
      // Cancer deep phe tnm, stage, hormone receptor status annotator.  Do before temporal (but after polarity?)
      aggregateBuilder.add( CancerPropertiesAnnotator.createAnnotatorDescription() );
      addTemporalEngines( aggregateBuilder );
      // Kludge to clean out unwanted annotations from the pittsburgh header
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( PittHeaderCleaner.class ) );
      addUmlsRelationEngines( aggregateBuilder );
      // coreference?
      //	    aggregateBuilder.add(
      //	        AnalysisEngineFactory.createEngineDescriptionFromPath("../ctakes/ctakes-coreference/desc/MipacqSvmCoreferenceResolverAggregate.xml"));

      return aggregateBuilder;
   }

   public static CollectionReader createFilesInDirectoryReader( final String inputDirectory )
         throws ResourceInitializationException {
      return CollectionReaderFactory.createReader( FilesInDirectoryCollectionReader.class,
            FilesInDirectoryCollectionReader.PARAM_INPUTDIR,
            inputDirectory );
   }

   public static AnalysisEngine createXMIWriter( final String outputDirectory )
         throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngine( XmiWriterCasConsumerCtakes.class,
            XmiWriterCasConsumerCtakes.PARAM_OUTPUTDIR,
            outputDirectory );
   }


   static private void addPittHeaderEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( PittHeaderAnnotator.class ) );
      // grab segments using known headers from default + what UPMC has given us.
//      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( CDASegmentAnnotator.class,
//            CDASegmentAnnotator.PARAM_SECTIONS_FILE, "ccda_sections.txt" ) );
//	  aggregateBuilder.add(AnalysisEngineFactory.createEngineDescription(UpmcSimpleSegmenter.class));
   }

   static private void addCoreEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add( SentenceDetector.createAnnotatorDescription() );
      aggregateBuilder.add( TokenizerAnnotatorPTB.createAnnotatorDescription() );
      aggregateBuilder.add( LvgAnnotator.createAnnotatorDescription() );
      aggregateBuilder.add( ContextDependentTokenizerAnnotator.createAnnotatorDescription() );
      aggregateBuilder.add( POSTagger.createAnnotatorDescription() );
      aggregateBuilder.add( Chunker.createAnnotatorDescription() );
      aggregateBuilder.add( ClinicalPipelineFactory.getStandardChunkAdjusterAnnotator() );
   }

   static private void addDictionaryEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder
            .add( AnalysisEngineFactory.createEngineDescription( CopyNPChunksToLookupWindowAnnotations.class ) );
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( RemoveEnclosedLookupWindows.class ) );
      aggregateBuilder.add( DefaultJCasTermAnnotator.createAnnotatorDescription(
            "org/apache/ctakes/cancer/dictionary/lookup/fast/cancerHsql.xml" ) );
   }

   static private void addAttributeEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add( ClearNLPDependencyParserAE.createAnnotatorDescription() );
      aggregateBuilder.add( PolarityCleartkAnalysisEngine.createAnnotatorDescription() );
      aggregateBuilder.add( UncertaintyCleartkAnalysisEngine.createAnnotatorDescription() );
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( ClearNLPSemanticRoleLabelerAE.class ) );
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( ConstituencyParser.class ) );
   }

   static private void addTemporalEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add( EventAnnotator.createAnnotatorDescription() );
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( PropertyToEventCopier.class ) );
      aggregateBuilder.add(
            DocTimeRelAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/doctimerel" ) ) );
      aggregateBuilder.add(
            BackwardsTimeAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/timeannotator" ) ) );
      aggregateBuilder.add(
            EventTimeRelationAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/eventtime" ) ) );
      aggregateBuilder.add(
            EventEventRelationAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/eventevent" ) ) );
   }

   static private void addUmlsRelationEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add(
            AnalysisEngineFactory.createEngineDescription(
                  ModifierExtractorAnnotator.class,
                  GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
                  getModelPath( "relationextractor/models/modifier_extractor" ) ) );
      aggregateBuilder.add(
            AnalysisEngineFactory.createEngineDescription(
                  DegreeOfRelationExtractorAnnotator.class,
                  GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
                  getModelPath( "relationextractor/models/degree_of" ) ) );
      aggregateBuilder.add(
            AnalysisEngineFactory.createEngineDescription(
                  LocationOfRelationExtractorAnnotator.class,
                  GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
                  getModelPath( "relationextractor/models/location_of" ) ) );
   }


   static private String getModelPath( final String moduleDirectory ) {
      return CTAKES_DIR_PREFIX + moduleDirectory + "/model.jar";
   }


}