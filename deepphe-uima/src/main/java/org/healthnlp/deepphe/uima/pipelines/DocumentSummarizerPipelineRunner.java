package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.apache.ctakes.cancer.pipelines.CancerPipelineRunner;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.uima.ae.DocumentSummarizerAE;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

@Immutable
final public class DocumentSummarizerPipelineRunner {
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
      final AnalysisEngineDescription analysisEngineDescription = CancerPipelineRunner.getPipelineDescription();
      //final AnalysisEngine xmiWriter = CancerPipelineRunner.createXMIWriter( outputDirectory );
      runCancerPipeline( collectionReader, analysisEngineDescription, createDocSummarizerCasConsumer(outputDirectory) );
   }

   public static void runCancerPipeline( final CollectionReader collectionReader,
                                         final AnalysisEngineDescription analysisEngineDescription,
                                         final AnalysisEngine outputWriter ) throws UIMAException, IOException {
      SimplePipeline.runPipeline( collectionReader,
            AnalysisEngineFactory.createEngine( analysisEngineDescription ),
            outputWriter );
   }

   public static void main( final String... args ) throws UIMAException, IOException {
      final Options options = CliFactory.parseArguments( Options.class, args );
      runCancerPipeline( options.getInputDirectory(), options.getOutputDirectory() );
   }


   public static AnalysisEngine createDocSummarizerCasConsumer( final String outputDirectory )
	         throws ResourceInitializationException {
	      return AnalysisEngineFactory.createEngine( DocumentSummarizerAE.class,
	    		  DocumentSummarizerAE.PARAM_OUTPUTDIR,
	            outputDirectory );
	   }


}
