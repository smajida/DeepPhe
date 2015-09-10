package org.apache.ctakes.cancer.ae;

import java.io.*;

import org.apache.ctakes.cancer.pipeline.CancerPipelineFactory;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;


public class CancerPipelineTest {

   // LOG4J logger based on class name
   static private final Logger LOGGER = Logger.getLogger( "CancerPipelineTest" );

   static private final String MODULE_TEST_RESOURCES = "deepphe-ctakes-cancer/src/test/resources";
   static private final String DATA_DIRECTORY_PATH = "org/apache/ctakes/cancer/note";


   @Test
   public void test() throws ResourceInitializationException, AnalysisEngineProcessException {
      File dataDirectory = new File( DATA_DIRECTORY_PATH );
      if  ( !dataDirectory.isDirectory() ) {
         dataDirectory = new File( System.getProperty( "user.dir" ), DATA_DIRECTORY_PATH );
      }
      if  ( !dataDirectory.isDirectory() ) {
         dataDirectory = new File( System.getProperty( "user.dir" ),
               MODULE_TEST_RESOURCES + File.separator + DATA_DIRECTORY_PATH );
      }
      final File[] files = dataDirectory.listFiles();
      if ( files == null || files.length == 0 ) {
         LOGGER.error( "No Files in directory: " + dataDirectory.getPath() );
         System.exit( 1 );
      }
      // Get the default pipeline
      final AnalysisEngine analysisEngine = CancerPipelineFactory.createPipelineEngine();

      final StringBuilder sb = new StringBuilder();
      final JCas jcas = analysisEngine.newJCas();
      for ( File file : files ) {
         LOGGER.info( "Processing: " + file.getName() );
         try ( BufferedReader br = new BufferedReader( new FileReader( file ) ) ) {
            String line = br.readLine();
            while ( line != null ) {
               sb.append( line ).append( '\n' );
               line = br.readLine();
            }
         } catch ( IOException ioE ) {
            throw new AnalysisEngineProcessException( ioE );
         }
         jcas.setDocumentText( sb.toString() );
         sb.setLength( 0 );
         analysisEngine.process( jcas );
         jcas.reset();
      }
   }

}
