package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.property.SpannedType;
import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/7/2015
 */
final public class SizeFinder {

   private SizeFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "SizeFinder" );

   static private final String VALUE_REGEX = "\\d+(\\.\\d+)?";
   static private final Pattern VALUE_PATTERN = Pattern.compile( VALUE_REGEX );

   static private final String FRONT_REGEX = "(\\b|\\(|\\[|\\{)";
   static private final String BACK_REGEX = "(\\b|,|\\.|\\?|!|\\)|\\]|\\})";
   static private final String DIM_REGEX = " ?(x|\\*) ?";
   static private final String UNIT_REGEX = " ?(mm|cm)";
   static private final String ONE_D_REGEX = FRONT_REGEX + VALUE_REGEX
                                             + UNIT_REGEX + BACK_REGEX;
   static private final String TWO_D_REGEX = FRONT_REGEX + VALUE_REGEX
                                             + DIM_REGEX + VALUE_REGEX
                                             + UNIT_REGEX + BACK_REGEX;
   static private final String THREE_D_REGEX = FRONT_REGEX + VALUE_REGEX
                                               + DIM_REGEX + VALUE_REGEX
                                               + DIM_REGEX + VALUE_REGEX
                                               + UNIT_REGEX + BACK_REGEX;

   static private final String FULL_REGEX = FRONT_REGEX + VALUE_REGEX
                                            + "(" + DIM_REGEX + VALUE_REGEX + "){0,2}"
                                            + UNIT_REGEX + BACK_REGEX;

   static private final Pattern FULL_PATTERN = Pattern.compile( FULL_REGEX, Pattern.CASE_INSENSITIVE );

   static public void addSizes( final JCas jcas, final AnnotationFS lookupWindow,
                                final Iterable<IdentifiedAnnotation> masses ) {
      final Collection<Dimension> dimensions = getDimensions( lookupWindow.getCoveredText() );
      if ( dimensions.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( Dimension dimension : dimensions ) {
         SizeInstanceFactory.getInstance().createInstance( jcas, windowStartOffset, dimension, masses );
      }
   }

   static List<Dimension> getDimensions( final String lookupWindow ) {
      if ( lookupWindow.length() < 3 ) {
         return Collections.emptyList();
      }
      final List<Dimension> dimensions = new ArrayList<>();
      final Matcher fullMatcher = FULL_PATTERN.matcher( lookupWindow );
      while ( fullMatcher.find() ) {
         final String matchWindow = lookupWindow.substring( fullMatcher.start(), fullMatcher.end() );
         for ( DimensionType type : DimensionType.values() ) {
            final Matcher typeMatcher = type.getMatcher( matchWindow );
            if ( typeMatcher.find() ) {
               final int typeStart = fullMatcher.start() + typeMatcher.start();
               final int typeEnd = fullMatcher.start() + typeMatcher.end();
               final String unit = matchWindow.substring( typeMatcher.start(), typeMatcher.end() );
               final SpannedDimensionType spannedType = new SpannedDimensionType( type, typeStart, typeEnd );
               final Matcher valueMatcher = VALUE_PATTERN.matcher( matchWindow );
               while ( valueMatcher.find() ) {
                  final int valueStart = fullMatcher.start() + valueMatcher.start();
                  final int valueEnd = fullMatcher.start() + valueMatcher.end();
                  final DimensionValue value
                        = new DimensionValue( lookupWindow.substring( valueStart, valueEnd ) + " " + unit );
                  final Dimension dimension
                        = new Dimension( spannedType, new SpannedDimensionValue( value, valueStart, valueEnd ) );
                  dimensions.add( dimension );
               }
            }
         }
      }
      Collections.sort( dimensions, SpanOffsetComparator.getInstance() );
      return dimensions;
   }

//   static private Collection<Measurement> getMeasurements( final String text, final DimensionUnit dimensionUnit,
//                                                           final int startOffset, final int endOffset ) {
//      return getMeasurements( text.substring( startOffset, endOffset ), dimensionUnit );
//   }
//
//   static private Collection<Measurement> getMeasurements( final CharSequence fullMeasurementText,
//                                                           final DimensionUnit dimensionUnit ) {
//      final Collection<Measurement> measurements = new ArrayList<>();
//      final Matcher matcher = VALUE_PATTERN.matcher( fullMeasurementText );
//      while ( matcher.find() ) {
//         final CharSequence matchText = fullMeasurementText.subSequence( matcher.start(), matcher.end() );
//         if ( matchText != null && matchText.length() > 0 ) {
//            float value = 0f;
//            try {
//               value = Float.parseFloat( "" + matchText );
//            } catch ( NumberFormatException nfE ) {
//               LOGGER.error( nfE.getMessage() );
//            }
//            measurements.add( new Measurement( value, dimensionUnit.getTitle() ) );
//         }
//      }
//      return measurements;
//   }


//   static private CancerSize createCancerSize( final JCas jcas, final int windowStartOffset, final Dimension dimension ) {
//      final int startOffset = windowStartOffset + dimension.getStartOffset();
//      final int endOffset = windowStartOffset + dimension.getEndOffset();
//      // TODO - change to measurementannotation
////      MeasurementAnnotation cancerSize = new MeasurementAnnotation( jcas );
//      final CancerSize cancerSize = new CancerSize( jcas, startOffset, endOffset );
////      if ( !size.getMeasurements().isEmpty() ) {
////         final FSArray measurementFeatures = new FSArray( jcas, size.getMeasurements().size() );
////         int measurementIndex = 0;
////         for ( Measurement measurement : size.getMeasurements() ) {
////            final SizeMeasurement measurementFeature = new SizeMeasurement( jcas );
////            measurementFeature.setValue( measurement.getValue() );
////            measurementFeature.setUnit( measurement.getUnit() );
////            measurementFeatures.set( measurementIndex, measurementFeature );
////            measurementIndex++;
////         }
////         cancerSize.setMeasurements( measurementFeatures );
////      }
//      cancerSize.addToIndexes();
//      return cancerSize;
//   }

//   /**
//    * Create a UIMA relation type based on arguments and the relation label. This
//    * allows subclasses to create/define their own types: e.g. coreference can
//    * create CoreferenceRelation instead of BinaryTextRelation
//    *
//    * @param jCas       - JCas object, needed to create new UIMA types
//    * @param cancerSize - First argument to relation
//    * @param neoplasm   - Second argument to relation
//    */
//   static private void addCancerSizeRelationToCas( final JCas jCas,
//                                                   final CancerSize cancerSize,
//                                                   final IdentifiedAnnotation neoplasm ) {
//      NeoplasmRelationFactory.createNeoplasmRelation( jCas, cancerSize, neoplasm, "Size_of" );
//   }


}
