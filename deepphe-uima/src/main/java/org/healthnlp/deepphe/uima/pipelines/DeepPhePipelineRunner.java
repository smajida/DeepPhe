package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.apache.uima.UIMAException;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

@Immutable
final public class DeepPhePipelineRunner {
		   
   private DeepPhePipelineRunner() {
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
      
	  @Option(shortName = "c", description = "specify the path to the jess clips files to be used.")
		public String getClipsDirectoryPath();
   }

   public static void main( final String... args ) throws UIMAException, IOException {
	      final Options options = CliFactory.parseArguments( Options.class, args );
	      DocumentSummarizerPipeline.runDocumentSummarizerPipeline( options.getInputDirectory(), options.getOutputDirectory(), options.getOntologyPath());
	      PhenotypeSummarizerPipeline.runPhenotypeSummarizerPipeline(options.getOutputDirectory(), options.getOntologyPath(), options.getClipsDirectoryPath());
	      
	   }
}
