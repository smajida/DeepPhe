package org.apache.ctakes.example.pipeline;


import org.apache.ctakes.assertion.medfacts.cleartk.UncertaintyCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.StartEndProgressLogger;
import org.apache.ctakes.chunker.ae.Chunker;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory;
import org.apache.ctakes.contexttokenizer.ae.ContextDependentTokenizerAnnotator;
import org.apache.ctakes.core.ae.SentenceDetectorAnnotator;
import org.apache.ctakes.core.ae.SimpleSegmentAnnotator;
import org.apache.ctakes.core.ae.TokenizerAnnotatorPTB;
import org.apache.ctakes.core.cc.FileTreeXmiWriter;
import org.apache.ctakes.core.cr.FileTreeReader;
import org.apache.ctakes.core.cr.FilesInDirectoryCollectionReader;
import org.apache.ctakes.dependency.parser.ae.ClearNLPDependencyParserAE;
import org.apache.ctakes.dictionary.lookup2.ae.DefaultJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.JCasTermAnnotator;
import org.apache.ctakes.necontexts.ContextAnnotator;
import org.apache.ctakes.postagger.POSTagger;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.resource.ResourceInitializationException;

import javax.annotation.concurrent.Immutable;


@Immutable
final public class VanillaPipelineFactory {

   private VanillaPipelineFactory() {
   }


   public static AnalysisEngineDescription createPipelineDescription( final String lookupConfigFile )
         throws ResourceInitializationException {
      final AggregateBuilder aggregateBuilder = createAggregateBuilder( lookupConfigFile );
      return aggregateBuilder.createAggregateDescription();
   }

   private static AggregateBuilder createAggregateBuilder( final String lookupConfigFile )
         throws ResourceInitializationException {
      final AggregateBuilder aggregateBuilder = new AggregateBuilder();

      addCoreEngines( aggregateBuilder );
      addDictionaryEngines( aggregateBuilder, lookupConfigFile );
      addAttributeEngines( aggregateBuilder );
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

   static private void addCoreEngines( final AggregateBuilder builder )
         throws ResourceInitializationException {
      addLoggedEngine( builder, SimpleSegmentAnnotator.createAnnotatorDescription() );
      addLoggedEngine( builder,
            SentenceDetectorAnnotator.getDescription( "/org/apache/ctakes/core/sentdetect/model.jar" ) );
      builder.add( TokenizerAnnotatorPTB.createAnnotatorDescription() );
      builder.add( ContextDependentTokenizerAnnotator.createAnnotatorDescription() );
      builder.add( POSTagger.createAnnotatorDescription() );
      builder.add( Chunker.createAnnotatorDescription() );
      builder.add( ClinicalPipelineFactory.getStandardChunkAdjusterAnnotator() );
   }

   static private void addDictionaryEngines( final AggregateBuilder builder, final String lookupConfigFile )
         throws ResourceInitializationException {
      builder.add(
            AnalysisEngineFactory.createEngineDescription( DefaultJCasTermAnnotator.class,
                  JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY, lookupConfigFile ) );
   }

   static private void addAttributeEngines( final AggregateBuilder builder )
         throws ResourceInitializationException {
      addLoggedEngine( builder, ClearNLPDependencyParserAE.createAnnotatorDescription() );
      addLoggedEngine( builder, ContextAnnotator.createAnnotatorDescription() );
      addLoggedEngine( builder, UncertaintyCleartkAnalysisEngine.createAnnotatorDescription() );
   }

   static private void addLoggedEngine( final AggregateBuilder builder,
                                        final AnalysisEngineDescription description )
         throws ResourceInitializationException {
      builder.add( StartEndProgressLogger.getDescription( description.getAnnotatorImplementationName(), true ) );
      builder.add( description );
      builder.add( StartEndProgressLogger.getDescription( description.getAnnotatorImplementationName(), false ) );
   }


}