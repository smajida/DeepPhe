package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.apache.ctakes.cancer.pipeline.CancerPipelineFactory;
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
final public class DocumentSummarizerPipeline {
		   
   private DocumentSummarizerPipeline() {
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

      @Option(
              shortName = "m",
              description = "specify the path to the model ontology file to be used." )
        public String getOntologyPath();
   }


   public static void runDocumentSummarizerPipeline( final String inputDirectory,
                                                     final String outputDirectory,
                                                     final String ontologyPath ) throws UIMAException, IOException {
      final CollectionReader collectionReader = CancerPipelineFactory.createFilesInDirectoryReader( inputDirectory );
      final AnalysisEngineDescription ctakesCancerDescription = CancerPipelineFactory.createPipelineDescription();
      final AnalysisEngine ctakesCancerEngine = AnalysisEngineFactory.createEngine( ctakesCancerDescription );
      final AnalysisEngine docSummarizer = createDocSummarizerAE( outputDirectory, ontologyPath );
      SimplePipeline.runPipeline( collectionReader, ctakesCancerEngine, docSummarizer );
   }


   public static AnalysisEngine createDocSummarizerAE( final String outputDirectory, final String ontologyPath)
	         throws ResourceInitializationException {
	      return AnalysisEngineFactory.createEngine( DocumentSummarizerAE.class,
	    		  DocumentSummarizerAE.PARAM_OUTPUTDIR,
	            outputDirectory,
	            DocumentSummarizerAE.PARAM_ONTOLOGY_PATH,
	            ontologyPath);
	   }


   public static void main( final String... args ) throws UIMAException, IOException {
	      final Options options = CliFactory.parseArguments( Options.class, args );
	      runDocumentSummarizerPipeline( options.getInputDirectory(), options.getOutputDirectory(), options.getOntologyPath());
//      CancerPipelineRunner.runCancerPipeline( options.getInputDirectory(), options.getOutputDirectory() );
	   }


}
