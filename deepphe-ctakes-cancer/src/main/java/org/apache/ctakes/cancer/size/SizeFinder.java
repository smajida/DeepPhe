package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/7/2015
 */
final public class SizeFinder {

   private SizeFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "SizeFinder" );

   // look for sizes applicable to DD 191 (cancer) and SS 33 (finding), 34 (lab/test result), 184 (SS)

   static private final String VALUE_REGEX = "\\d+(\\.\\d+)?";
   static private final Pattern VALUE_PATTERN = Pattern.compile( VALUE_REGEX );

   static List<Size> getSizes( final String lookupWindow ) {
      final List<Size> sizes = new ArrayList<>();
      for ( DimensionUnit dimensionUnit : DimensionUnit.values() ) {
         final Matcher matcher = dimensionUnit.getMatcher( lookupWindow );
         while ( matcher.find() ) {
            sizes.add( new Size( matcher.start(), matcher.end(),
                  getMeasurements( lookupWindow, dimensionUnit, matcher.start(), matcher.end() ) ) );
         }
      }
      Collections.sort( sizes, SpanOffsetComparator.getInstance() );
      return sizes;
   }

   static private Collection<Measurement> getMeasurements( final String text, final DimensionUnit dimensionUnit,
                                                           final int startOffset, final int endOffset ) {
      return getMeasurements( text.substring( startOffset, endOffset ), dimensionUnit );
   }

   static private Collection<Measurement> getMeasurements( final CharSequence fullMeasurementText,
                                                           final DimensionUnit dimensionUnit ) {
      final Collection<Measurement> measurements = new ArrayList<>();
      final Matcher matcher = VALUE_PATTERN.matcher( fullMeasurementText );
      while ( matcher.find() ) {
         final CharSequence matchText = fullMeasurementText.subSequence( matcher.start(), matcher.end() );
         if ( matchText != null && matchText.length() > 0 ) {
            float value = 0f;
            try {
               value = Float.parseFloat( "" + matchText );
            } catch ( NumberFormatException nfE ) {
               LOGGER.error( nfE.getMessage() );
            }
            measurements.add( new Measurement( value, dimensionUnit.getTitle() ) );
         }
      }
      return measurements;
   }


   static public void addSizes( final JCas jcas, final AnnotationFS lookupWindow,
                                final Iterable<DiseaseDisorderMention> lookupDisorders,
                                final Iterable<SignSymptomMention> lookupSymptoms ) {
      final Collection<Size> sizes = getSizes( lookupWindow.getCoveredText() );
      if ( sizes.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( Size size : sizes ) {
         final CancerSize cancerSize = createCancerSize( jcas, windowStartOffset, size );
         final int startOffset = windowStartOffset + size.getStartOffset();
         final int endOffset = windowStartOffset + size.getEndOffset();
         final DiseaseDisorderMention closestDiseaseMention
               = FinderUtil.getClosestEventMention( startOffset, endOffset, lookupDisorders );
         final SignSymptomMention closestFindingMention
               = FinderUtil.getClosestEventMention( startOffset, endOffset, lookupSymptoms );
         final EventMention closerEventMention
               = FinderUtil
               .getCloserEventMention( startOffset, endOffset, closestDiseaseMention, closestFindingMention );
         addCancerSizeRelationToCas( jcas, cancerSize, closerEventMention );
      }
   }

   static private CancerSize createCancerSize( final JCas jcas, final int windowStartOffset, final Size size ) {
      final int startOffset = windowStartOffset + size.getStartOffset();
      final int endOffset = windowStartOffset + size.getEndOffset();
      final CancerSize cancerSize = new CancerSize( jcas, startOffset, endOffset );
      if ( !size.getMeasurements().isEmpty() ) {
         final FSArray measurementFeatures = new FSArray( jcas, size.getMeasurements().size() );
         int measurementIndex = 0;
         for ( Measurement measurement : size.getMeasurements() ) {
            final SizeMeasurement measurementFeature = new SizeMeasurement( jcas );
            measurementFeature.setValue( measurement.getValue() );
            measurementFeature.setUnit( measurement.getUnit() );
            measurementFeatures.set( measurementIndex, measurementFeature );
            measurementIndex++;
         }
         cancerSize.setMeasurements( measurementFeatures );
      }
      cancerSize.addToIndexes();
      return cancerSize;
   }

   /**
    * Create a UIMA relation type based on arguments and the relation label. This
    * allows subclasses to create/define their own types: e.g. coreference can
    * create CoreferenceRelation instead of BinaryTextRelation
    *
    * @param jCas         - JCas object, needed to create new UIMA types
    * @param cancerSize   - First argument to relation
    * @param eventMention - Second argument to relation
    */
   static private void addCancerSizeRelationToCas( final JCas jCas,
                                                   final CancerSize cancerSize,
                                                   final EventMention eventMention ) {
      if ( eventMention == null ) {
         LOGGER.info( "No Neoplasm discovered to relate to " + cancerSize.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument cancerSizeArgument = new RelationArgument( jCas );
      cancerSizeArgument.setArgument( cancerSize );
      cancerSizeArgument.setRole( "Argument" );
      cancerSizeArgument.addToIndexes();
      final RelationArgument eventMentionArgument = new RelationArgument( jCas );
      eventMentionArgument.setArgument( eventMention );
      eventMentionArgument.setRole( "Related_to" );
      eventMentionArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( cancerSizeArgument );
      neoplasmRelation.setArg2( eventMentionArgument );
      neoplasmRelation.setCategory( "Size_of" );
      neoplasmRelation.addToIndexes();
   }


}
