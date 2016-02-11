package org.apache.ctakes.cancer.ae;

import java.io.*;

import org.apache.ctakes.cancer.pipeline.CancerPipelineFactory;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;


public class CancerPipelineTest {

   // LOG4J logger based on class name
   static private final Logger LOGGER = Logger.getLogger( "CancerPipelineTest" );

   static private final String DATA_DIRECTORY_PATH = "data/sample/docs";


//   @Test
//   public void test() throws ResourceInitializationException, AnalysisEngineProcessException {
//      File dataDirectory;
//      try {
//         dataDirectory = FileLocator.locateFile( DATA_DIRECTORY_PATH );
//      } catch ( IOException ioE ) {
//         throw new ResourceInitializationException( ioE );
//      }
//      final File[] files = dataDirectory.listFiles();
//      if ( files == null || files.length == 0 ) {
//         LOGGER.error( "No Files in directory: " + dataDirectory.getPath() );
//         System.exit( 1 );
//      }
//      // Get the default pipeline
//      final AnalysisEngine analysisEngine = CancerPipelineFactory.createPipelineEngine();
//
//      final StringBuilder sb = new StringBuilder();
//      final JCas jcas = analysisEngine.newJCas();
//      for ( File file : files ) {
//         LOGGER.info( "Processing: " + file.getName() );
//         try ( BufferedReader br = new BufferedReader( new FileReader( file ) ) ) {
//            String line = br.readLine();
//            while ( line != null ) {
//               sb.append( line ).append( '\n' );
//               line = br.readLine();
//            }
//         } catch ( IOException ioE ) {
//            throw new AnalysisEngineProcessException( ioE );
//         }
//         jcas.setDocumentText( sb.toString() );
//         sb.setLength( 0 );
//         analysisEngine.process( jcas );
//         jcas.reset();
//      }
//   }

}
