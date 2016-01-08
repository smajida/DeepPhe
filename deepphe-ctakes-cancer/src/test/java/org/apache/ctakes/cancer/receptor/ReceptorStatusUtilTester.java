package org.apache.ctakes.cancer.receptor;

import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import static org.apache.ctakes.cancer.receptor.StatusType.*;
import static org.apache.ctakes.cancer.receptor.StatusValue.*;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/7/2015
 */
public class ReceptorStatusUtilTester {

   static private final Logger LOGGER = Logger.getLogger( "ReceptorStatusUtilTester" );

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

      offset += 40;
      NOT_RECEPTOR_SS = new SignSymptomMention( _testCas, offset, offset + 10 );
      NOT_RECEPTOR_SS.addToIndexes();

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
         OwlConnectionFactory.getInstance().getOntology( "data/ontology/breastCancer.owl" );
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
   static private IdentifiedAnnotation NOT_RECEPTOR_SS;


   @Test
   public void testIsReceptorStatus() {
      assertTrue( "PR Positive not Receptor Status", ReceptorStatusUtil.isReceptorStatus( PR_PLUS_1 ) );
      assertTrue( "PR Positive not Receptor Status", ReceptorStatusUtil.isReceptorStatus( PR_PLUS_2 ) );
      assertTrue( "PR Negative not Receptor Status", ReceptorStatusUtil.isReceptorStatus( PR_MINUS_1 ) );
      assertTrue( "ER Positive not Receptor Status", ReceptorStatusUtil.isReceptorStatus( ER_PLUS_1 ) );
      assertTrue( "ER Negative not Receptor Status", ReceptorStatusUtil.isReceptorStatus( ER_MINUS_1 ) );
      assertTrue( "ER Negative not Receptor Status", ReceptorStatusUtil.isReceptorStatus( ER_MINUS_2 ) );
      assertTrue( "HER2 Positive not Receptor Status", ReceptorStatusUtil.isReceptorStatus( HER2_PLUS_1 ) );
      assertTrue( "HER2 Negative not Receptor Status", ReceptorStatusUtil.isReceptorStatus( HER2_MINUS_1 ) );
      assertTrue( "HER2 Unknown not Receptor Status", ReceptorStatusUtil.isReceptorStatus( HER2_UNKNOWN_1 ) );
      assertFalse( "Generic Sign Symptom is Receptor Status", ReceptorStatusUtil.isReceptorStatus( NOT_RECEPTOR_SS ) );
   }

   @Test
   public void testGetStatusValue() {
      assertEquals( "PR Positive not Positive", ReceptorStatusUtil.getStatusValue( _testCas, PR_PLUS_1 ), POSITIVE );
      assertEquals( "PR Positive not Positive", ReceptorStatusUtil.getStatusValue( _testCas, PR_PLUS_2 ), POSITIVE );
      assertEquals( "PR Negative not Negative", ReceptorStatusUtil.getStatusValue( _testCas, PR_MINUS_1 ), NEGATIVE );
      assertEquals( "ER Positive not Positive", ReceptorStatusUtil.getStatusValue( _testCas, ER_PLUS_1 ), POSITIVE );
      assertEquals( "ER Negative not Negative", ReceptorStatusUtil.getStatusValue( _testCas, ER_MINUS_1 ), NEGATIVE );
      assertEquals( "ER Negative not Negative", ReceptorStatusUtil.getStatusValue( _testCas, ER_MINUS_2 ), NEGATIVE );
      assertEquals( "HER2 Positive not Positive", ReceptorStatusUtil
            .getStatusValue( _testCas, HER2_PLUS_1 ), POSITIVE );
      assertEquals( "HER2 Negative not Negative", ReceptorStatusUtil
            .getStatusValue( _testCas, HER2_MINUS_1 ), NEGATIVE );
      assertEquals( "HER2 Unknown not Unknown", ReceptorStatusUtil
            .getStatusValue( _testCas, HER2_UNKNOWN_1 ), UNKNOWN );
      assertNull( "Generic Sign Symptom value not Null", ReceptorStatusUtil
            .getStatusValue( _testCas, NOT_RECEPTOR_SS ) );
   }

   // createFullReceptorStatusMention is mostly tested by the setup and other tests


}
