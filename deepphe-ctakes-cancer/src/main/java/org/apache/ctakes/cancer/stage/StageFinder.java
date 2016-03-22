package org.apache.ctakes.cancer.stage;


import org.apache.ctakes.cancer.property.SpannedType;
import org.apache.ctakes.cancer.property.SpannedValue;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/5/2015
 */
final public class StageFinder {

   private StageFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "StageFinder" );


   static private final String TYPE_REGEX = "Stage";
   static private final String SHORT_VALUE = "([I]{1,3}|IV|IS|[0-4])[a-c]?";
   static private final String LONG_VALUE = "N/?A\\b|recurrent|unknown|unspecified|indeterminate|(not assessed)";
   static private final String NON_STAGE = "(in situ)|unknown|indeterminate|unspecified|recurrent Breast Carcinoma";

   // Order is very important
   static private final String FULL_REGEX = "\\b" + TYPE_REGEX + "\\s*"
                                            + "((" + SHORT_VALUE + "\\b)"
                                            + "|(" + LONG_VALUE + "))";
   static private final Pattern FULL_PATTERN = Pattern.compile( FULL_REGEX, Pattern.CASE_INSENSITIVE );


   static public void addStages( final JCas jcas, final AnnotationFS lookupWindow,
                                 final Iterable<IdentifiedAnnotation> neoplasms ) {
      final Collection<Stage> stages = getStages( lookupWindow.getCoveredText() );
      if ( stages.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( Stage stage : stages ) {
         StageResourceFactory.getInstance().createResource( jcas, windowStartOffset, stage, neoplasms );
      }
   }


   static List<Stage> getStages( final String lookupWindow ) {
      if ( lookupWindow.length() < 6 ) {
         return Collections.emptyList();
      }
      final List<Stage> stages = new ArrayList<>();
      final Matcher fullMatcher = FULL_PATTERN.matcher( lookupWindow );
      while ( fullMatcher.find() ) {
         final String matchWindow = lookupWindow.substring( fullMatcher.start(), fullMatcher.end() );
         SpannedType<StageType> spannedType = null;
         for ( StageType type : StageType.values() ) {
            final Matcher typeMatcher = type.getMatcher( matchWindow );
            while ( typeMatcher.find() ) {
               final int typeStart = fullMatcher.start() + typeMatcher.start();
               final int typeEnd = fullMatcher.start() + typeMatcher.end();
               spannedType = new SpannedStageType( type, typeStart, typeEnd );
               final SpannedValue<StageValue> spannedValue
                     = getSpannedValue( lookupWindow, spannedType.getEndOffset(), fullMatcher.end() );
               if ( spannedValue != null ) {
                  stages.add( new Stage( spannedType, spannedValue ) );
               }
            }
         }
      }
      // TODO add NON_STAGE in situ, unknown, etc.
      Collections.sort( stages, SpanOffsetComparator.getInstance() );
      return stages;
   }


   static private SpannedStageValue getSpannedValue( final String matchWindow,
                                                     final int startOffset,
                                                     final int endOffset ) {
      final String valueLookupWindow = matchWindow.substring( startOffset, endOffset );
      if ( valueLookupWindow.isEmpty() ) {
         return null;
      }
      SpannedStageValue bestValue = null;
      for ( StageValue value : StageValue.values() ) {
         final Matcher valueMatcher = value.getMatcher( valueLookupWindow );
         if ( valueMatcher.find() ) {
            if ( bestValue == null
                 || (valueMatcher.end() - valueMatcher.start() > bestValue.getEndOffset() - bestValue.getStartOffset())
                 || (startOffset + valueMatcher.start() < bestValue.getStartOffset()) ) {
               bestValue = new SpannedStageValue( value,
                     startOffset + valueMatcher.start(),
                     startOffset + valueMatcher.end() );
            }
         }
      }
      return bestValue;
   }


}
