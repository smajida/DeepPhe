package org.healthnlp.deepphe.uima.pipelines;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.apache.ctakes.assertion.medfacts.cleartk.PolarityCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.CancerPipelineTest.AddEvent;
import org.apache.ctakes.cancer.ae.TnmAnnotator;
import org.apache.ctakes.cancer.pipelines.CancerPipelineRunner;
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
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.uima.ae.DocumentSummarizerAE;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

@Immutable
final public class DocumentSummarizerPipelineRunner {
	
   static private final String TIME_ANNOTATOR_MODEL = "/org/apache/ctakes/temporal/ae/timeannotator/model.jar";
   static private final String EVENT_ANNOTATOR_MODEL = "/org/apache/ctakes/temporal/ae/eventannotator/model.jar";
   static private final String DOCTIMEREL_ANNOTATOR_MODEL = "/org/apache/ctakes/temporal/ae/doctimerel/model.jar";

	   
   private DocumentSummarizerPipelineRunner() {
   }

   static interface Options{
      @Option(
            shortName = "i",
            description = "specify the path to the directory containing the clinical notes to be processed" )
      public String getInputDirectory();

      @Option(
            shortName = "o",
            description = "specify the path to the directory where the output xmi files are to be saved" )
      public String getOutputDirectory();
   }

   
   public static void runCancerPipeline( final String inputDirectory,
                                         final String outputDirectory ) throws UIMAException, IOException {
      final CollectionReader collectionReader = CancerPipelineRunner.createFilesInDirectoryReader( inputDirectory );
      final AnalysisEngine docSummarizer = createDocSummarizerCasConsumer(outputDirectory);
      runCancerPipeline( collectionReader, docSummarizer );
   }

   public static void runCancerPipeline( final CollectionReader collectionReader,
                                         final AnalysisEngine outputWriter ) throws UIMAException, IOException {
      SimplePipeline.runPipeline( collectionReader,
            buildAnalysisEngine(),
            outputWriter );
   }

   public static void main( final String... args ) throws UIMAException, IOException {
      final Options options = CliFactory.parseArguments( Options.class, args );
      runCancerPipeline( options.getInputDirectory(), options.getOutputDirectory() );
   }


   static public AnalysisEngine buildAnalysisEngine() throws ResourceInitializationException {
	      final AggregateBuilder builder = new AggregateBuilder();
	      builder.add( ClinicalPipelineFactory.getTokenProcessingPipeline() );
	      builder.add( AnalysisEngineFactory.createEngineDescription( CopyNPChunksToLookupWindowAnnotations.class ) );
	      builder.add( AnalysisEngineFactory.createEngineDescription( RemoveEnclosedLookupWindows.class ) );

//	      builder.add( UmlsDictionaryLookupAnnotator.createAnnotatorDescription() );
	      try {
	         builder.add(AnalysisEngineFactory.createEngineDescription(DefaultJCasTermAnnotator.class,
	               AbstractJCasTermAnnotator.PARAM_WINDOW_ANNOT_PRP,
	               "org.apache.ctakes.typesystem.type.textspan.Sentence",
	               JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY,
	               ExternalResourceFactory.createExternalResourceDescription(
	                     FileResourceImpl.class,
	                     FileLocator.locateFile( "org/apache/ctakes/dictionary/lookup/fast/cTakesHsql.xml" ) )
	         ));
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	         throw new ResourceInitializationException(e);
	      }

	      builder.add( ClearNLPDependencyParserAE.createAnnotatorDescription() );

	      // Add BackwardsTimeAnnotator
	      builder.add( BackwardsTimeAnnotator.createAnnotatorDescription( TIME_ANNOTATOR_MODEL ) );
	      // Add EventAnnotator
	      builder.add( EventAnnotator.createAnnotatorDescription( EVENT_ANNOTATOR_MODEL ) );
	      //link event to eventMention
	      builder.add( AnalysisEngineFactory.createEngineDescription( AddEvent.class ) );
	      // Add Document Time Relative Annotator
	      builder.add( DocTimeRelAnnotator.createAnnotatorDescription( DOCTIMEREL_ANNOTATOR_MODEL ) );

	      builder.add( PolarityCleartkAnalysisEngine.createAnnotatorDescription() );
	      builder.add( AnalysisEngineFactory.createEngineDescription( ExtractionPrepAnnotator.class,
	            "AnnotationVersion", 2,
	            "AnnotationVersionPropKey", "ANNOTATION_VERSION" ) );
	      // Add the Cancer Stage and Receptor Status Annotator
	      builder.add( AnalysisEngineFactory.createEngineDescription( TnmAnnotator.class ) );

	      //ADD XMI CAS CONSUMER HERE?

	      return builder.createAggregate();
   }
   
   public static AnalysisEngine createDocSummarizerCasConsumer( final String outputDirectory )
	         throws ResourceInitializationException {
	      return AnalysisEngineFactory.createEngine( DocumentSummarizerAE.class,
	    		  DocumentSummarizerAE.PARAM_OUTPUTDIR,
	            outputDirectory );
	   }


}
