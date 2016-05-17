package org.apache.ctakes.cancer.pipeline;


import org.apache.ctakes.assertion.medfacts.cleartk.UncertaintyCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.*;
import org.apache.ctakes.chunker.ae.Chunker;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.CopyNPChunksToLookupWindowAnnotations;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory.RemoveEnclosedLookupWindows;
import org.apache.ctakes.contexttokenizer.ae.ContextDependentTokenizerAnnotator;
import org.apache.ctakes.core.ae.SentenceDetectorAnnotator;
import org.apache.ctakes.core.ae.TokenizerAnnotatorPTB;
import org.apache.ctakes.core.cc.FileTreeXmiWriter;
import org.apache.ctakes.core.cr.FileTreeReader;
import org.apache.ctakes.core.cr.FilesInDirectoryCollectionReader;
import org.apache.ctakes.coreference.ae.MarkableSalienceAnnotator;
import org.apache.ctakes.coreference.ae.MentionClusterCoreferenceAnnotator;
import org.apache.ctakes.dependency.parser.ae.ClearNLPDependencyParserAE;
import org.apache.ctakes.dictionary.lookup2.ae.DefaultJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.JCasTermAnnotator;
import org.apache.ctakes.necontexts.ContextAnnotator;
import org.apache.ctakes.postagger.POSTagger;
import org.apache.ctakes.relationextractor.ae.LocationOfRelationExtractorAnnotator;
import org.apache.ctakes.relationextractor.ae.ModifierExtractorAnnotator;
import org.apache.ctakes.temporal.ae.DocTimeRelAnnotator;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.cleartk.ml.jar.GenericJarClassifierFactory;

import javax.annotation.concurrent.Immutable;


@Immutable
final public class CancerPipelineFactory {


   static private final String CTAKES_DIR_PREFIX = "/org/apache/ctakes/";

   // Default dictionary lookup exclusion pos tags except for IN
   static private final String LOOKUP_EXCLUSION_TAGS
         = "VB,VBD,VBG,VBN,VBP,VBZ,CC,CD,DT,EX,LS,MD,PDT,POS,PP,PP$,PRP,PRP$,RP,TO,WDT,WP,WPS,WRB";
   static private final String LOOKUP_CONFIG_DESC
         = "org/apache/ctakes/cancer/dictionary/lookup/fast/cancerHsql.xml";


   private CancerPipelineFactory() {
   }

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
      // Temporal is horribly slow on these notes.  Do not use it until necessary
      addTemporalEngines( aggregateBuilder );
      // Kludge to clean out unwanted annotations from the pittsburgh header
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( PittHeaderCleaner.class ) );
      addUmlsRelationEngines( aggregateBuilder );
      // coreference
//      addCorefEngines( aggregateBuilder );

      return aggregateBuilder;
   }


   public static CollectionReader createFilesReader( final String inputDirectory )
         throws ResourceInitializationException {
      return CollectionReaderFactory.createReader( FileTreeReader.class,
            FilesInDirectoryCollectionReader.PARAM_INPUTDIR,
            inputDirectory );
   }

   public static AnalysisEngine createXMIWriter( final String outputDirectory )
         throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngine( FileTreeXmiWriter.class,
            FileTreeXmiWriter.PARAM_OUTPUTDIR,
            outputDirectory );
   }


   static private void addPittHeaderEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( PittHeaderAnnotator.class ) );
   }

   static private void addCoreEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      addLoggedEngine( aggregateBuilder,
            SentenceDetectorAnnotator.getDescription( "/org/apache/ctakes/core/sentdetect/model.jar" ) );
      aggregateBuilder.add( TokenizerAnnotatorPTB.createAnnotatorDescription() );
      aggregateBuilder.add( ContextDependentTokenizerAnnotator.createAnnotatorDescription() );
      aggregateBuilder.add( POSTagger.createAnnotatorDescription() );
      aggregateBuilder.add( Chunker.createAnnotatorDescription() );
      aggregateBuilder.add( ClinicalPipelineFactory.getStandardChunkAdjusterAnnotator() );
   }

   static private void addDictionaryEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add(
            AnalysisEngineFactory.createEngineDescription( CopyNPChunksToLookupWindowAnnotations.class ) );
      aggregateBuilder.add(
            AnalysisEngineFactory.createEngineDescription( RemoveEnclosedLookupWindows.class ) );
      aggregateBuilder.add(
            AnalysisEngineFactory.createEngineDescription( DefaultJCasTermAnnotator.class,
                  JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY, LOOKUP_CONFIG_DESC,
                  JCasTermAnnotator.PARAM_EXC_TAGS_KEY, LOOKUP_EXCLUSION_TAGS,
                  JCasTermAnnotator.PARAM_MIN_SPAN_KEY, 3 ) );
   }

   static private void addAttributeEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      addLoggedEngine( aggregateBuilder, ClearNLPDependencyParserAE.createAnnotatorDescription() );
//      aggregateBuilder.add( PolarityCleartkAnalysisEngine.createAnnotatorDescription() );
      addLoggedEngine( aggregateBuilder, ContextAnnotator.createAnnotatorDescription() );
      addLoggedEngine( aggregateBuilder, UncertaintyCleartkAnalysisEngine.createAnnotatorDescription() );
//      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( ClearNLPSemanticRoleLabelerAE.class ) );
//      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( ConstituencyParser.class ) );
   }

   static private void addTemporalEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
//      addLoggedEngine( aggregateBuilder, EventAnnotator.createAnnotatorDescription() );
      addLoggedEngine( aggregateBuilder,
            DocTimeRelAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/doctimerel" ) ) );
//      addLoggedEngine( aggregateBuilder,
//            BackwardsTimeAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/timeannotator" ) ) );
//      aggregateBuilder.add(
//            EventTimeRelationAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/eventtime" ) ) );
//      aggregateBuilder.add(
//            EventEventRelationAnnotator.createAnnotatorDescription( getModelPath( "temporal/ae/eventevent" ) ) );
   }

   static private void addUmlsRelationEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      addLoggedEngine( aggregateBuilder,
            AnalysisEngineFactory.createEngineDescription(
                  ModifierExtractorAnnotator.class,
                  GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
                  getModelPath( "relationextractor/models/modifier_extractor" ) ) );
//      aggregateBuilder.add(
//            AnalysisEngineFactory.createEngineDescription(
//                  DegreeOfRelationExtractorAnnotator.class,
//                  GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
//                  getModelPath( "relationextractor/models/degree_of" ) ) );
      addLoggedEngine( aggregateBuilder,
            AnalysisEngineFactory.createEngineDescription(
                  LocationOfRelationExtractorAnnotator.class,
                  GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH,
                  CTAKES_DIR_PREFIX + "relation/extractor/location_of.jar" ) );
   }

   private static void addCorefEngines( final AggregateBuilder aggregateBuilder )
         throws ResourceInitializationException {
      aggregateBuilder.add( AnalysisEngineFactory.createEngineDescription( IdentifiedAnnotationMarkableAnnotator.class ) );
      aggregateBuilder.add( MarkableSalienceAnnotator
            .createAnnotatorDescription( "/org/apache/ctakes/temporal/ae/salience/model.jar" ) );
      aggregateBuilder.add( MentionClusterCoreferenceAnnotator
            .createAnnotatorDescription( "/org/apache/ctakes/coreference/mention-cluster/model.jar" ) );
   }

   static private void addLoggedEngine( final AggregateBuilder aggregateBuilder,
                                        final AnalysisEngineDescription description )
         throws ResourceInitializationException {
      aggregateBuilder.add( StartEndProgressLogger.getDescription( description.getAnnotatorImplementationName(), true ) );
      aggregateBuilder.add( description );
      aggregateBuilder.add( StartEndProgressLogger.getDescription( description.getAnnotatorImplementationName(), false ) );
   }


   static private String getModelPath( final String moduleDirectory ) {
      return CTAKES_DIR_PREFIX + moduleDirectory + "/model.jar";
   }


}