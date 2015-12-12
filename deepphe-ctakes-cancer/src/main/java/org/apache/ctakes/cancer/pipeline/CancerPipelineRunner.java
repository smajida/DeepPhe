package org.apache.ctakes.cancer.pipeline;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;
import org.apache.ctakes.core.cc.pretty.plaintext.PrettyTextWriterFit;
import org.apache.ctakes.core.cc.property.plaintext.PropertyTextWriterFit;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;

import javax.annotation.concurrent.Immutable;
import java.io.IOException;

@Immutable
final public class CancerPipelineRunner {
   private CancerPipelineRunner() {
   }

   static interface Options extends CancerPipelineOptions {
      @Option(
            shortName = "i",
            description = "specify the path to the directory containing the clinical notes to be processed")
      public String getInputDirectory();

      @Option(
            shortName = "o",
            description = "specify the path to the directory where the output xmi files are to be saved")
      public String getOutputDirectory();
   }


   public static void runCancerPipeline( final String inputDirectory,
                                         final String outputDirectory ) throws UIMAException, IOException {
      final CollectionReader collectionReader = CancerPipelineFactory.createFilesInDirectoryReader( inputDirectory );
      final AnalysisEngineDescription ctakesCancerDescription = CancerPipelineFactory.createPipelineDescription();
      final AnalysisEngine ctakesCancerEngine = AnalysisEngineFactory.createEngine( ctakesCancerDescription );
      final AnalysisEngine xmiWriter = CancerPipelineFactory.createXMIWriter( outputDirectory );
      final AnalysisEngine prettyTextWriter = createPrettyTextWriter( outputDirectory );
      final AnalysisEngine propertyTextWriter = createPropertyTextWriter( outputDirectory );
      SimplePipeline
            .runPipeline( collectionReader, ctakesCancerEngine, prettyTextWriter, propertyTextWriter, xmiWriter );
   }


   private static AnalysisEngine createPrettyTextWriter( final String outputDirectory )
         throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngine( PrettyTextWriterFit.createAnnotatorDescription( outputDirectory ) );
   }

   private static AnalysisEngine createPropertyTextWriter( final String outputDirectory )
         throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngine( PropertyTextWriterFit.createAnnotatorDescription( outputDirectory ) );
   }


   public static void main( final String... args ) throws UIMAException, IOException {
      final Options options = CliFactory.parseArguments( Options.class, args );
      runCancerPipeline( options.getInputDirectory(), options.getOutputDirectory() );
   }


}
