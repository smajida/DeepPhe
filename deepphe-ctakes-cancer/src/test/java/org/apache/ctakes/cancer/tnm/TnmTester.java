package org.apache.ctakes.cancer.tnm;

import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/23/2015
 */
public class TnmTester {

   static private final Logger LOGGER = Logger.getLogger( "TnmTester" );

   static private final String FULL_SENTENCE
         = "Patient is a 54 year old female with history of T2N0M0 left breast cancer ER-neg, PR-neg, HER2+,"
           + " now undergoing neoadjuvant chemo with taxotere, carboplatin, Herceptin, and pertuzumab.";


   @Test
   public void testTnmClasses() {
      final List<TnmClass> tnmClasses = TnmFinder.getTnmClasses( FULL_SENTENCE );
      assertEquals( "Expect 3 TNM Classes from " + FULL_SENTENCE, 3, tnmClasses.size() );
      testTnmClass( tnmClasses.get( 0 ), TnmClassPrefixType.UNSPECIFIED, TnmClassType.T, "2" );
      testTnmClass( tnmClasses.get( 1 ), TnmClassPrefixType.UNSPECIFIED, TnmClassType.N, "0" );
      testTnmClass( tnmClasses.get( 2 ), TnmClassPrefixType.UNSPECIFIED, TnmClassType.M, "0" );
   }


   static private void testTnmClass( final TnmClass tnmClass,
                                     final TnmClassPrefixType expectedPrefix,
                                     final TnmClassType expectedClassType,
                                     final String expectedValue ) {
      assertEquals( "Expecting prefix " + expectedPrefix.getCharacterCode() + " : " + expectedPrefix.getTitle(),
            expectedPrefix, tnmClass.getPrefix() );
      assertEquals( "Expected class type " + expectedClassType.name() + " : " + expectedClassType.getTitle(),
            expectedClassType, tnmClass.getClassType() );
      assertEquals( "Expected value " + expectedValue, expectedValue, tnmClass.getValue() );
   }

}
