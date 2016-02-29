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

   static private final String SENTENCE_4 = "There are three contrast enhancing lesions in the right parietal lobe,"
                                             + " the largest measuring 0.9 cm, suggestive of metastatic tumor.";

   //   @Test
//   public void testMultipleMention() {
//      final String MULTIPLE_MENTION_SENTENCE = MULTIPLE_MENTION_SENTENCE_1
//                                               + MULTIPLE_MENTION_SENTENCE_2
//                                               + MULTIPLE_MENTION_SENTENCE_3;
//      final List<Size> cancerSizes = SizeFinder.getSizes( MULTIPLE_MENTION_SENTENCE );
//      assertEquals( "Expect three Cancer Sizes in " + MULTIPLE_MENTION_SENTENCE, 3, cancerSizes.size() );
//      final List<Measurement> measurements = new ArrayList<>( cancerSizes.get( 0 ).getMeasurements() );
//      assertEquals( "Expect one first measurement in " + MULTIPLE_MENTION_SENTENCE_1, 1, measurements.size() );
//      testMeasurement( MULTIPLE_MENTION_SENTENCE_1, measurements.get( 0 ), 2.1, DimensionUnit.CENTIMETER );
//      measurements.clear();
//      measurements.addAll( cancerSizes.get( 1 ).getMeasurements() );
//      assertEquals( "Expect one first measurement in " + MULTIPLE_MENTION_SENTENCE_2, 1, measurements.size() );
//      testMeasurement( MULTIPLE_MENTION_SENTENCE_2, measurements.get( 0 ), 900, DimensionUnit.MILLIMETER );
//      measurements.clear();
//      measurements.addAll( cancerSizes.get( 2 ).getMeasurements() );
//      assertEquals( "Expect two first measurement in " + MULTIPLE_MENTION_SENTENCE_3, 2, measurements.size() );
//      testMeasurement( MULTIPLE_MENTION_SENTENCE_3, measurements.get( 0 ), 1.4, DimensionUnit.CENTIMETER );
//      testMeasurement( MULTIPLE_MENTION_SENTENCE_3, measurements.get( 1 ), 1.9, DimensionUnit.CENTIMETER );
//   }
//
   @Test
   public void testSingleMention() {
      final List<Dimension> cancerSizes = SizeFinder.getDimensions( SENTENCE_4 );
      assertEquals( "Expect one Cancer Size in " + SENTENCE_4, 1, cancerSizes.size() );
      testMeasurement( SENTENCE_4, cancerSizes.get( 0 ).getSpannedValue(), "0.9 cm" );
   }

   static private void testMeasurement( final String sentence,
                                        final SpannedValue spannedValue,
                                        final String expectedValue ) {
      assertEquals(
            "Expected " + expectedValue + " in " + sentence, expectedValue, spannedValue.getValue().getTitle() );
   }

}
