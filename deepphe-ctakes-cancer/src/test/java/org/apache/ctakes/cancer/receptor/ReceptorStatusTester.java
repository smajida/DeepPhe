package org.apache.ctakes.cancer.receptor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
final public class ReceptorStatusTester {

   static private final Logger LOGGER = Logger.getLogger( "ReceptorStatusTester" );

   static private final String MULTIPLE_MENTION_SENTENCE
         = "Patient is a 54 year old female with history of T2N0M0 left breast cancer ER-, PR-, HER2+,"
           + " now undergoing neoadjuvant chemo with taxotere, carboplatin, Herceptin, and pertuzumab.";

   static private final String[] NO_RECEPTOR_SENTENCES = {
         "Patient stated her position.", "Patient sex is neuter.", "Patient is positive her pulse is rapid.",
         "Patient was rushed to the ER", "The patient rapped PR to the ER for HER2 the neu!",
         "xy complement, progesterone absorption negative" };

   //  THIS SENTENCE IS EXPECTED TO CREATE A FALSE POSITIVE
   static private final String[] FAIL_RECEPTOR_SENTENCES = { "The ER is positive that the patient is stable." };

   static private final String[] ER_POS_SENTENCES = {
         "Patient tested ER+ last week.", "Patient tested ER + last week.",
         "Patient tested ER+pos last week.", "Patient tested ER+positive last week.",
         "Patient tested ER pos last week.", "Patient tested ER positive last week.",
         "Patient tested ER status pos last week.", "Patient tested ER status positive last week.",
         "Patient ER status pos.", "Patient ER status is positive.",
         "ER      POS", "ER Status           POSITIVE",
         "Estrogen      POS", "Estrogen           POSITIVE" };

   static private final String[] ER_NEG_SENTENCES = {
         "Patient tested ER- last week.", "Patient tested ER - last week.",
         "Patient tested ER-neg last week.", "Patient tested ER-negative last week.",
         "Patient tested ER neg last week.", "Patient tested ER negative last week.",
         "Patient tested ER status neg last week.", "Patient tested ER status negative last week.",
         "Patient ER status neg.", "Patient ER status is negative.",
         "ER      NEG", "ER Status           NEGATIVE",
         "Estrogen      NEG", "Estrogen           NEGATIVE" };

   static private final String[] ER_NA_SENTENCES = {
         "Patient ER status unknown.", "Patient ER status is unknown.",
         "Patient ER status indeterminate.", "Patient ER status is indeterminate.",
         "Patient ER status equivocal.", "Patient ER status is equivocal.",
         "Patient ER status not assessed.", "Patient ER status is not assessed.",
         "Patient ER status NA.", "Patient ER status is N/A.",
         "ER      unknown", "ER Status           unknown",
         "ER      indeterminate", "ER Status           indeterminate",
         "ER      equivocal", "ER Status           equivocal",
         "ER      not assessed", "ER Status           not assessed",
         "ER      NA", "ER Status           NA",
         "ER      N/A", "ER Status           N/A",
         "Estrogen      unknown", "Estrogen      indeterminate",
         "Estrogen      equivocal", "Estrogen      not assessed",
         "Estrogen      NA", "Estrogen      N/A" };


   static private final String[] PR_POS_SENTENCES = {
         "Patient tested PR+ last week.", "Patient tested PR + last week.",
         "Patient tested PR+pos last week.", "Patient tested PR+positive last week.",
         "Patient tested PR pos last week.", "Patient tested PR positive last week.",
         "Patient tested PR status pos last week.", "Patient tested PR status positive last week.",
         "Patient PR status pos.", "Patient PR status is positive.",
         "PR      POS", "PR Status           POSITIVE",
         "Progesterone      POS", "Progesterone           POSITIVE" };

   static private final String[] PR_NEG_SENTENCES = {
         "Patient tested PR- last week.", "Patient tested PR - last week.",
         "Patient tested PR-neg last week.", "Patient tested PR-negative last week.",
         "Patient tested PR neg last week.", "Patient tested PR negative last week.",
         "Patient tested PR status neg last week.", "Patient tested PR status negative last week.",
         "Patient PR status neg.", "Patient PR status is negative.",
         "PR      NEG", "PR Status           NEGATIVE",
         "Progesterone      NEG", "Progesterone           NEGATIVE" };

   static private final String[] PR_NA_SENTENCES = {
         "Patient PR status unknown.", "Patient PR status is unknown.",
         "Patient PR status indeterminate.", "Patient PR status is indeterminate.",
         "Patient PR status equivocal.", "Patient PR status is equivocal.",
         "Patient PR status not assessed.", "Patient PR status is not assessed.",
         "Patient PR status NA.", "Patient PR status is N/A.",
         "PR      unknown", "PR Status           unknown",
         "PR      indeterminate", "PR Status           indeterminate",
         "PR      equivocal", "PR Status           equivocal",
         "PR      not assessed", "PR Status           not assessed",
         "PR      NA", "PR Status           NA",
         "PR      N/A", "PR Status           N/A",
         "Progesterone      unknown", "Progesterone      indeterminate",
         "Progesterone      equivocal", "Progesterone      not assessed",
         "Progesterone      NA", "Progesterone      N/A" };


   static private final String[] HER2_POS_SENTENCES = {
         "Patient tested HER2+ last week.", "Patient tested HER2 + last week.",
         "Patient tested HER2+pos last week.", "Patient tested HER2+positive last week.",
         "Patient tested HER2 pos last week.", "Patient tested HER2 positive last week.",
         "Patient tested HER2 status pos last week.", "Patient tested HER2 status positive last week.",
         "Patient HER2 status pos.", "Patient HER2 status is positive.",
         "HER2      POS", "HER2 Status           POSITIVE",
         "Patient tested HER2/neu+ last week.", "Patient tested HER2/neu + last week.",
         "Patient tested HER2/neu+pos last week.", "Patient tested HER2/neu+positive last week.",
         "Patient tested HER2/neu pos last week.", "Patient tested HER2/neu positive last week.",
         "Patient tested HER2/neu status pos last week.", "Patient tested HER2/neu status positive last week.",
         "Patient HER2/neu status pos.", "Patient HER2/neu status is positive.",
         "HER2/neu      POS", "HER2/neu Status           POSITIVE" };

   static private final String[] HER2_NEG_SENTENCES = {
         "Patient tested HER2- last week.", "Patient tested HER2 - last week.",
         "Patient tested HER2-neg last week.", "Patient tested HER2-negative last week.",
         "Patient tested HER2 neg last week.", "Patient tested HER2 negative last week.",
         "Patient tested HER2 status neg last week.", "Patient tested HER2 status negative last week.",
         "Patient HER2 status neg.", "Patient HER2 status is negative.",
         "HER2      NEG", "HER2 Status           NEGATIVE",
         "Patient tested HER2/neu- last week.", "Patient tested HER2/neu - last week.",
         "Patient tested HER2/neu-neg last week.", "Patient tested HER2/neu-negative last week.",
         "Patient tested HER2/neu neg last week.", "Patient tested HER2/neu negative last week.",
         "Patient tested HER2/neu status neg last week.", "Patient tested HER2/neu status negative last week.",
         "Patient HER2/neu status neg.", "Patient HER2/neu status is negative.",
         "HER2/neu      NEG", "HER2/neu Status           NEGATIVE" };

   static private final String[] HER2_NA_SENTENCES = {
         "Patient HER2 status unknown.", "Patient HER2 status is unknown.",
         "Patient HER2 status indeterminate.", "Patient HER2 status is indeterminate.",
         "Patient HER2 status equivocal.", "Patient HER2 status is equivocal.",
         "Patient HER2 status not assessed.", "Patient HER2 status is not assessed.",
         "Patient HER2 status NA.", "Patient HER2 status is N/A.",
         "HER2      unknown", "HER2 Status           unknown",
         "HER2      indeterminate", "HER2 Status           indeterminate",
         "HER2      equivocal", "HER2 Status           equivocal",
         "HER2      not assessed", "HER2 Status           not assessed",
         "HER2      NA", "HER2 Status           NA",
         "HER2      N/A", "HER2 Status           N/A",
         "Patient HER2/neu status unknown.", "Patient HER2/neu status is unknown.",
         "Patient HER2/neu status indeterminate.", "Patient HER2/neu status is indeterminate.",
         "Patient HER2/neu status equivocal.", "Patient HER2/neu status is equivocal.",
         "Patient HER2/neu status not assessed.", "Patient HER2/neu status is not assessed.",
         "Patient HER2/neu status NA.", "Patient HER2/neu status is N/A.",
         "HER2/neu      unknown", "HER2/neu Status           unknown",
         "HER2/neu      indeterminate", "HER2/neu Status           indeterminate",
         "HER2/neu      equivocal", "HER2/neu Status           equivocal",
         "HER2/neu      not assessed", "HER2/neu Status           not assessed",
         "HER2/neu      NA", "HER2/neu Status           NA",
         "HER2/neu      N/A", "HER2/neu Status           N/A" };


   @Test
   public void testMultipleMention() {
      final List<ReceptorStatus> receptorStatuses
            = ReceptorStatusFinder.getReceptorStatuses( MULTIPLE_MENTION_SENTENCE );
      assertEquals( "Expect three Hormone Receptors in " + MULTIPLE_MENTION_SENTENCE, 3, receptorStatuses.size() );
      assertTrue( "First receptor is Estrogen in " + MULTIPLE_MENTION_SENTENCE,
            receptorStatuses.get( 0 ).getStatusType() == ReceptorStatusType.ER );
      assertTrue( "Second receptor is Progesterone in " + MULTIPLE_MENTION_SENTENCE,
            receptorStatuses.get( 1 ).getStatusType() == ReceptorStatusType.PR );
      assertTrue( "Third receptor is HER2/neu in " + MULTIPLE_MENTION_SENTENCE,
            receptorStatuses.get( 2 ).getStatusType() == ReceptorStatusType.HER2 );
      assertTrue( "First receptor is Negative in " + MULTIPLE_MENTION_SENTENCE,
            receptorStatuses.get( 0 ).getStatusValue() == DefinedReceptorStatusValue.NEGATIVE );
      assertTrue( "Second receptor is Negative in " + MULTIPLE_MENTION_SENTENCE,
            receptorStatuses.get( 1 ).getStatusValue() == DefinedReceptorStatusValue.NEGATIVE );
      assertTrue( "Third receptor is Positive in " + MULTIPLE_MENTION_SENTENCE,
            receptorStatuses.get( 2 ).getStatusValue() == DefinedReceptorStatusValue.POSITIVE );
   }

   @Test
   public void testNoReceptor() {
      for ( String sentence : NO_RECEPTOR_SENTENCES ) {
         assertEquals( "Expect no Hormone Receptors in " + sentence, 0,
               ReceptorStatusFinder.getReceptorStatuses( sentence ).size() );
      }
   }

   @Test
   public void testFailReceptor() {
      for ( String sentence : FAIL_RECEPTOR_SENTENCES ) {
         assertEquals( "Expect an unwanted Hormone Receptor in " + sentence, 1,
               ReceptorStatusFinder.getReceptorStatuses( sentence ).size() );
      }
   }

   static private void testReceptorStatus( final String[] sentences,
                                           final ReceptorStatusType expectedType,
                                           final ReceptorStatusValue expectedValue ) {
      final List<ReceptorStatus> receptorStatuses = new ArrayList<>( 1 );
      for ( String sentence : sentences ) {
         receptorStatuses.addAll( ReceptorStatusFinder.getReceptorStatuses( sentence ) );
         assertEquals( "Expect one Hormone Receptor in " + sentence, 1, receptorStatuses.size() );
         assertTrue( "Receptor is " + expectedType.getTitle() + " in " + sentence,
               receptorStatuses.get( 0 ).getStatusType() == expectedType );
         assertTrue( "Receptor is " + expectedValue.getTitle() + " in " + sentence,
               receptorStatuses.get( 0 ).getStatusValue() == expectedValue );
         receptorStatuses.clear();
      }
   }


   @Test
   public void testErPos() {
      testReceptorStatus( ER_POS_SENTENCES, ReceptorStatusType.ER, DefinedReceptorStatusValue.POSITIVE );
   }

   @Test
   public void testErNeg() {
      testReceptorStatus( ER_NEG_SENTENCES, ReceptorStatusType.ER, DefinedReceptorStatusValue.NEGATIVE );
   }

   @Test
   public void testErNA() {
      testReceptorStatus( ER_NA_SENTENCES, ReceptorStatusType.ER, DefinedReceptorStatusValue.UNKNOWN );
   }

   @Test
   public void testPrPos() {
      testReceptorStatus( PR_POS_SENTENCES, ReceptorStatusType.PR, DefinedReceptorStatusValue.POSITIVE );
   }

   @Test
   public void testPrNeg() {
      testReceptorStatus( PR_NEG_SENTENCES, ReceptorStatusType.PR, DefinedReceptorStatusValue.NEGATIVE );
   }

   @Test
   public void testPrNA() {
      testReceptorStatus( PR_NA_SENTENCES, ReceptorStatusType.PR, DefinedReceptorStatusValue.UNKNOWN );
   }


   @Test
   public void testHer2Pos() {
      testReceptorStatus( HER2_POS_SENTENCES, ReceptorStatusType.HER2, DefinedReceptorStatusValue.POSITIVE );
   }

   @Test
   public void testHer2Neg() {
      testReceptorStatus( HER2_NEG_SENTENCES, ReceptorStatusType.HER2, DefinedReceptorStatusValue.NEGATIVE );
   }

   @Test
   public void testHer2NA() {
      testReceptorStatus( HER2_NA_SENTENCES, ReceptorStatusType.HER2, DefinedReceptorStatusValue.UNKNOWN );
   }

}
