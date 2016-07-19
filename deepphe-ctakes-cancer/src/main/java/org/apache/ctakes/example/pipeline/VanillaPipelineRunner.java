package org.apache.ctakes.example.pipeline;

import com.lexicalscope.jewel.cli.ArgumentValidationException;
import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.InvalidOptionSpecificationException;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Immutable
final public class VanillaPipelineRunner {

   private VanillaPipelineRunner() {
   }

   static private final String INPUT_DIR = "inputDir";
   static private final String OUTPUT_DIR = "outputDir";
   static private final String LOOKUP_CONFIG = "lookupConfig";

   interface Options {
      @Option(
            shortName = "i",
            longName = INPUT_DIR,
            description = "specify the path to the directory containing the clinical notes to be processed",
            defaultToNull = true
      )
      String getInputDirectory();

      @Option(
            shortName = "o",
            longName = OUTPUT_DIR,
            description = "specify the path to the directory where the output files are to be saved",
            defaultToNull = true )
      String getOutputDirectory();

      @Option(
            shortName = "l",
            longName = LOOKUP_CONFIG,
            description = "specify the path to the dictionary lookup configuration file",
            defaultToNull = true )
      String getLookupConfigFile();

      @Option(
            shortName = "p",
            longName = "propertyFile",
            description = "specify the path to the input parameter property file",
            defaultToNull = true )
      String getPropertiesFile();
   }

   public static void runVanillaPipeline( final String propertiesFile ) throws UIMAException, IOException {
      final Properties properties = new Properties();
      try ( InputStream inputStream = VanillaPipelineRunner.class.getResourceAsStream( propertiesFile ) ) {
         if ( inputStream == null ) {
            throw new FileNotFoundException( "Could not load Property File " + propertiesFile );
         }
         properties.load( inputStream );
      }
      runVanillaPipeline( properties.getProperty( INPUT_DIR ),
            properties.getProperty( OUTPUT_DIR ),
            properties.getProperty( LOOKUP_CONFIG ) );
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
      Options options = null;
      try {
         options = CliFactory.parseArguments( Options.class, args );
      } catch ( ArgumentValidationException | InvalidOptionSpecificationException multE ) {
         throw new IOException( multE.getMessage() + "\n" + String.join( " ", args ) );
      }
      if ( options.getPropertiesFile() != null ) {
         runVanillaPipeline( options.getPropertiesFile() );
      } else {
         runVanillaPipeline( options.getInputDirectory(), options.getOutputDirectory(), options.getLookupConfigFile() );
      }
   }


}
