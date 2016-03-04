package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.property.SpannedValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
public class SizeTester {

   static private final Logger LOGGER = Logger.getLogger( "SizeTester" );

   static private final double TOLERANCE = 0.001;

   static private final String MULTIPLE_MENTION_SENTENCE_1 = "INVASIVE DUCTAL CARCINOMA, 2.1 CM LEFT BREAST;";
   static private final String MULTIPLE_MENTION_SENTENCE_2 = " DUCTAL CARCINOMA IN SITU, 900mm. RIGHT BREAST 12:00.";
   static private final String MULTIPLE_MENTION_SENTENCE_3 = "  Total Tumor size 1.4x1.9 cm";
   static private final String MULTIPLE_MENTION_SENTENCE_4 = "INVASIVE DUCTAL CARCINOMA, 2.8 * 1.4 * 1.9 mm";

   static private final String MULTIPLE_MENTION_SENTENCE_5
         = "INVASIVE DUCTAL CARCINOMA, 2.8*1.4*1.9 mm, DUCTAL CARCINOMA IN SITU (2cm).";

   static private final String SENTENCE_4 = "There are three contrast enhancing lesions in the right parietal lobe,"
                                             + " the largest measuring 0.9 cm, suggestive of metastatic tumor.";

   @Test
   public void testCapUnitMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( MULTIPLE_MENTION_SENTENCE_1 );
      assertEquals( "Expect one Cancer Size in " + MULTIPLE_MENTION_SENTENCE_1, 1, cancerSizes.size() );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_1, cancerSizes.get( 0 ).getSpannedValue(), "2.1 cm" );
   }

   @Test
   public void testNoTimeMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( MULTIPLE_MENTION_SENTENCE_2 );
      assertEquals( "Expect one Cancer Size in " + MULTIPLE_MENTION_SENTENCE_2, 1, cancerSizes.size() );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_2, cancerSizes.get( 0 ).getSpannedValue(), "900 mm" );
   }


   @Test
   public void testSingleMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( SENTENCE_4 );
      assertEquals( "Expect one Cancer Size in " + SENTENCE_4, 1, cancerSizes.size() );
      testMeasurement( SENTENCE_4, cancerSizes.get( 0 ).getSpannedValue(), "0.9 cm" );
   }

   @Test
   public void testDoubleMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( MULTIPLE_MENTION_SENTENCE_3 );
      assertEquals( "Expect two Cancer Sizes in " + MULTIPLE_MENTION_SENTENCE_3, 2, cancerSizes.size() );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_3, cancerSizes.get( 0 ).getSpannedValue(), "1.4 cm" );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_3, cancerSizes.get( 1 ).getSpannedValue(), "1.9 cm" );
   }

   @Test
   public void testTripleMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( MULTIPLE_MENTION_SENTENCE_4 );
      assertEquals( "Expect three Cancer Sizes in " + MULTIPLE_MENTION_SENTENCE_4, 3, cancerSizes.size() );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_4, cancerSizes.get( 0 ).getSpannedValue(), "2.8 mm" );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_4, cancerSizes.get( 1 ).getSpannedValue(), "1.4 mm" );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_4, cancerSizes.get( 2 ).getSpannedValue(), "1.9 mm" );
   }

   @Test
   public void testTripleAndMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( MULTIPLE_MENTION_SENTENCE_5 );
      assertEquals( "Expect four Cancer Sizes in " + MULTIPLE_MENTION_SENTENCE_5, 4, cancerSizes.size() );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_5, cancerSizes.get( 0 ).getSpannedValue(), "2.8 mm" );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_5, cancerSizes.get( 1 ).getSpannedValue(), "1.4 mm" );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_5, cancerSizes.get( 2 ).getSpannedValue(), "1.9 mm" );
      testMeasurement( MULTIPLE_MENTION_SENTENCE_5, cancerSizes.get( 3 ).getSpannedValue(), "2 cm" );
   }

   static private void testMeasurement( final String sentence,
                                        final SpannedValue spannedValue,
                                        final String expectedValue ) {
      assertEquals(
            "Expected " + expectedValue + " in " + sentence, expectedValue, spannedValue.getValue().getTitle() );
   }

}
