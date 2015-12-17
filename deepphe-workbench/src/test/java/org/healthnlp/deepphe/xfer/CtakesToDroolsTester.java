package org.healthnlp.deepphe.xfer;


import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import org.apache.ctakes.cancer.receptor.ReceptorStatusUtil;
import org.apache.ctakes.cancer.receptor.StatusType;
import org.apache.ctakes.cancer.receptor.StatusValue;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.apache.ctakes.cancer.receptor.StatusType.ER;
import static org.apache.ctakes.cancer.receptor.StatusType.HER2;
import static org.apache.ctakes.cancer.receptor.StatusType.PR;
import static org.apache.ctakes.cancer.receptor.StatusValue.NEGATIVE;
import static org.apache.ctakes.cancer.receptor.StatusValue.POSITIVE;
import static org.apache.ctakes.cancer.receptor.StatusValue.UNKNOWN;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/7/2015
 */
public class CtakesToDroolsTester {

   static private final Logger LOGGER = Logger.getLogger( "CtakesToDroolsTester" );

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

   static private final CtakesToDroolsConverter CONVERTER = new CtakesToDroolsConverter();

   @Test
   public void testGetSummariesForSummarizableReceptorStatus() {
      // Not quite sure how to best test this
   }


   @Test
   public void testProcessReceptorStatus() {
      // Should implement this when the switch statement uses
      // -- case ER.getCui( NEGATIVE ) :
      // and the like, rather than
      // -- case "C0279756": // Negative Estrogen Receptor
   }


}
