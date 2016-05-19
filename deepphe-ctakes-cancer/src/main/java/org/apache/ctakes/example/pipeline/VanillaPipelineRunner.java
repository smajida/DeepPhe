package org.apache.ctakes.example.pipeline;

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
final public class VanillaPipelineRunner {
   private VanillaPipelineRunner() {
   }

   interface Options {
      @Option(
            shortName = "i",
            description = "specify the path to the directory containing the clinical notes to be processed" )
      String getInputDirectory();

      @Option(
            shortName = "o",
            description = "specify the path to the directory where the output files are to be saved" )
      String getOutputDirectory();

      @Option(
            shortName = "l",
            description = "specify the path to the dictionary lookup configuration file" )
      String getLookupConfigFile();
   }


   public static void runVanillaPipeline( final String inputDirectory,
                                          final String outputDirectory,
                                          final String lookupConfigFile ) throws UIMAException, IOException {
      final CollectionReader collectionReader = VanillaPipelineFactory.createFilesReader( inputDirectory );
      final AnalysisEngineDescription ctakesVanillaDescription
            = VanillaPipelineFactory.createPipelineDescription( lookupConfigFile );
      final AnalysisEngine ctakesVanillaEngine = AnalysisEngineFactory.createEngine( ctakesVanillaDescription );
      // Writes XMI files.  Optional
      final AnalysisEngine xmiWriter = VanillaPipelineFactory.createXMIWriter( outputDirectory );
      // Writes text files marked up in human-readable "pretty print".  Optional
      final AnalysisEngine prettyTextWriter = createPrettyTextWriter( outputDirectory );
      // Writes formatted text files listing annotations and their properties.  Optional
      final AnalysisEngine propertyTextWriter = createPropertyTextWriter( outputDirectory );
      SimplePipeline
            .runPipeline( collectionReader, ctakesVanillaEngine, prettyTextWriter, propertyTextWriter, xmiWriter );
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
      runVanillaPipeline( options.getInputDirectory(), options.getOutputDirectory(), options.getLookupConfigFile() );
   }


}
