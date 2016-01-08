package org.healthnlp.deepphe.uima.fhir;

import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;

import org.apache.ctakes.cancer.receptor.ReceptorStatusUtil;
import org.apache.ctakes.cancer.receptor.StatusType;
import org.apache.ctakes.cancer.receptor.StatusValue;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.fhir.Observation;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.apache.ctakes.cancer.receptor.StatusType.*;
import static org.apache.ctakes.cancer.receptor.StatusValue.*;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/7/2015
 */
public class ResourceFactoryTester {

   static private final Logger LOGGER = Logger.getLogger( "ResourceFactoryTester" );

   @BeforeClass
   static public void setupTestCas() throws UIMAException {
      try {
         _testCas = JCasFactory.createJCas();
      } catch ( UIMAException uimaE ) {
         LOGGER.error( "Could not create test CAS " + uimaE.getMessage() );
         throw uimaE;
      }
      int offset = 0;
      PR_PLUS_1 = createReceptorStatus( PR, POSITIVE, offset );
      offset += 40;
      PR_PLUS_2 = createReceptorStatus( PR, POSITIVE, offset );
      offset += 40;
      PR_MINUS_1 = createReceptorStatus( PR, NEGATIVE, offset );
      offset += 40;
      ER_PLUS_1 = createReceptorStatus( ER, POSITIVE, offset );
      offset += 40;
      ER_MINUS_1 = createReceptorStatus( ER, NEGATIVE, offset );
      offset += 40;
      ER_MINUS_2 = createReceptorStatus( ER, NEGATIVE, offset );
      offset += 40;
      HER2_PLUS_1 = createReceptorStatus( HER2, POSITIVE, offset );
      offset += 40;
      HER2_MINUS_1 = createReceptorStatus( HER2, NEGATIVE, offset );
      offset += 40;
      HER2_UNKNOWN_1 = createReceptorStatus( HER2, UNKNOWN, offset );
      final char[] noteText = new char[ offset + 51 ];
      Arrays.fill( noteText, 'a' );
      _testCas.setDocumentText( String.valueOf( noteText ) );
   }

   static private IdentifiedAnnotation createReceptorStatus( final StatusType type,
                                                             final StatusValue value,
                                                             final int offset ) {
      return ReceptorStatusUtil.createFullReceptorStatusMention( _testCas, offset, offset + 10, type,
            offset + 20, offset + 30, value );
   }

   @BeforeClass
   static public void loadOntology() {
      try {
         OwlConnectionFactory.getInstance().getOntology( "data/ontology/nlpBreastCancer.owl" );
        // ResourceFactory.getInstance().setOntology( OwlConnectionFactory.getInstance().getDefaultOntology() );
      } catch ( IOntologyException | FileNotFoundException multE ) {
         LOGGER.error( multE.getMessage() );
      }
   }

   static private JCas _testCas;
   static private IdentifiedAnnotation PR_PLUS_1;
   static private IdentifiedAnnotation PR_PLUS_2;
   static private IdentifiedAnnotation PR_MINUS_1;
   static private IdentifiedAnnotation ER_PLUS_1;
   static private IdentifiedAnnotation ER_MINUS_1;
   static private IdentifiedAnnotation ER_MINUS_2;
   static private IdentifiedAnnotation HER2_PLUS_1;
   static private IdentifiedAnnotation HER2_MINUS_1;
   static private IdentifiedAnnotation HER2_UNKNOWN_1;


   static private Stream<String> getUriStream( final Predicate<String> filterType ) {
      return DocumentResourceFactory.getObservations( _testCas )
            .stream()
            .map( Observation::getConceptURI )
            .map( URI::toASCIIString )
            .filter( filterType );
   }

 
   
   @Test
   public void testGetObservationsReceptorStatus() {
      final long pr_count = getUriStream( PR.getUri()::equals ).count();
      assertEquals( "Detected PR count is wrong", 3, pr_count );
      final long er_count = getUriStream( ER.getUri()::equals ).count();
      assertEquals( "Detected ER count is wrong", 3, er_count );
      final long her2_count = getUriStream( HER2.getUri()::equals ).count();
      assertEquals( "Detected HER2 count is wrong", 3, her2_count );
   }

}
