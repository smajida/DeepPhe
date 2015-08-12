package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.uima.ae.DocumentSummarizerAE;
import org.healthnlp.deepphe.uima.cr.PatientCollectionReader;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

@Immutable
final public class PhenotypeSummarizerPipeline {
	   
   private PhenotypeSummarizerPipeline() {
   }

   static interface Options{
      @Option(
            shortName = "i",
            description = "specify the path to the directory containing the clinical notes to be processed" )
      public String getInputDirectory();

      @Option(
              shortName = "m",
              description = "specify the path to the model ontology file to be used." )
        public String getOntologyPath();
   }

   
   public static void runPhenotypeSummarizerPipeline( final String inputDirectory,final String ontologyPath) throws UIMAException, IOException {
      final CollectionReader collectionReader = createCollectionReader(inputDirectory);
      final AnalysisEngine phenotypeSummarizerAE = createPhenotypeSummarizerAE();
      final AnalysisEngine i2B2OutputAE = createI2B2OutputAE();
      SimplePipeline.runPipeline( collectionReader,phenotypeSummarizerAE, i2B2OutputAE);

   }


   private static AnalysisEngine createI2B2OutputAE() {
	   return null;
}


private static CollectionReader createCollectionReader(String inputDirectory) throws ResourceInitializationException {
	   return CollectionReaderFactory.createReader( PatientCollectionReader.class,
			   PatientCollectionReader.PARAM_INPUTDIR,
	            inputDirectory );
}


public static void main( final String... args ) throws UIMAException, IOException {
      final Options options = CliFactory.parseArguments( Options.class, args );
      runPhenotypeSummarizerPipeline( options.getInputDirectory(), options.getOntologyPath());
   }


   static public AnalysisEngine createPhenotypeSummarizerAE() throws ResourceInitializationException {
	   return AnalysisEngineFactory.createEngine( DocumentSummarizerAE.class);
   }
   

}
