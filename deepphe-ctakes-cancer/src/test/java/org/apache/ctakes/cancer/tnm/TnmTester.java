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

   static private final String[] NO_TNM_SENTENCES = {
         "Patient has Taken aspirin for her headache.", "The patient drives a lamborghini LXM1 and boy is it fast." };

   static private final String[] PUNCTUATION_SENTENCES = {
         "Patient's Cancer diagnosed as pT1 N1.",
         "Test confirmed Primary T0, Metastasis M1.",
         "Father had T1, son is worse (T3)"
   };

   static private final String[] ROMAN_SENTENCES = {
         "Patient's Cancer diagnosed as pTI NI.",
         "Test confirmed Primary T0, Metastasis MI.",
         "Father had TIII, son is worse (TIV)"
   };

   @Test
   public void testTnmClasses() {
      final List<TnmClass> tnmClasses = TnmFinder.getTnmClasses( FULL_SENTENCE );
      assertEquals( "Expect 3 TNM Classes from " + FULL_SENTENCE, 3, tnmClasses.size() );
      testTnmClass( tnmClasses.get( 0 ), TnmClassPrefixType.UNSPECIFIED, TnmClassType.T, "2" );
      testTnmClass( tnmClasses.get( 1 ), TnmClassPrefixType.UNSPECIFIED, TnmClassType.N, "0" );
      testTnmClass( tnmClasses.get( 2 ), TnmClassPrefixType.UNSPECIFIED, TnmClassType.M, "0" );
   }

   @Test
   public void testNoTnm() {
      for ( String sentence : NO_TNM_SENTENCES ) {
         final List<TnmClass> tnmClasses = TnmFinder.getTnmClasses( sentence );
         assertEquals( "Expect no TNM in " + sentence, 0, tnmClasses.size() );
      }
   }

   @Test
   public void testPunctuation() {
      for ( String sentence : PUNCTUATION_SENTENCES ) {
         final List<TnmClass> tnmClasses = TnmFinder.getTnmClasses( sentence );
         assertEquals( "Expect 2 TNM Classes from " + sentence, 2, tnmClasses.size() );
      }
   }

   @Test
   public void testRoman() {
      for ( String sentence : ROMAN_SENTENCES ) {
         final List<TnmClass> tnmClasses = TnmFinder.getTnmClasses( sentence );
         assertEquals( "Expect 2 TNM Classes from " + sentence, 2, tnmClasses.size() );
      }
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
