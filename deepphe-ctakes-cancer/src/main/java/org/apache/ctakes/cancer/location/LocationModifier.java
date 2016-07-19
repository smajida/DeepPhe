package org.apache.ctakes.cancer.location;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/16/2016
 */
public interface LocationModifier {

   default String getTitle() {
      final IClass iClass = OwlOntologyConceptUtil.getIClass( getUri() );
      if ( iClass == null ) {
         return null;
      }
      return OwlParserUtil.getPreferredText( iClass );
   }

   String getUri();

   Matcher getMatcher( final CharSequence lookupWindow );


   enum Quadrant implements LocationModifier {
      UPPER_INNER( "Upper_Inner_Quadrant_of_the_Breast", "((upper(/lower)? ?-? ?inner)|(inner ?-? ?upper))( quadrant)?" ),
      LOWER_INNER( "Lower_Inner_Quadrant_of_the_Breast", "((lower ?-? ?inner)|(inner ?-? ?lower))( quadrant)?" ),
      UPPER_OUTER( "Upper_Outer_Quadrant_of_the_Breast", "((upper(/lower)? ?-? ?outer)|(outer ?-? ?upper))( quadrant)?" ),
      LOWER_OUTER( "Lower_Outer_Quadrant_of_the_Breast", "((lower ?-? ?outer)|(outer ?-? ?lower))( quadrant)?" ),
      CENTRAL_PORTION( "Central_Portion_of_the_Breast", "central portion" );
      private final String __uri;
      private final Pattern __pattern;

      Quadrant( final String uri, final String regex ) {
         __uri = uri;
         __pattern = Pattern.compile( "\\b(" + regex + ")\\.?,?\\b", Pattern.CASE_INSENSITIVE );
      }


      public String getUri() {
         return OwlConstants.BREAST_CANCER_OWL + "#" + __uri;
      }

      public Matcher getMatcher( final CharSequence lookupWindow ) {
         return __pattern.matcher( lookupWindow );
      }
   }


   enum BodySide implements LocationModifier {
      LEFT( "Left", "((to the )?left( side)?)|lt|levo" ),
      RIGHT( "Right", "((to the )?right( side)?)|rt|dextro" ),
      BILATERAL( "Bilateral", "bilateral|(both sides)|(right and left)|(left and right)" );

      private final String __uri;
      private final Pattern __pattern;

      BodySide( final String uri, final String regex ) {
         __uri = uri;
         __pattern = Pattern.compile( "\\b(" + regex + ")\\.?,?\\b", Pattern.CASE_INSENSITIVE );
      }

      public String getUri() {
         return OwlConstants.CANCER_OWL + "#" + __uri;
      }

      public Matcher getMatcher( final CharSequence lookupWindow ) {
         return __pattern.matcher( lookupWindow );
      }
   }

   class Clockwise implements LocationModifier {
      static private final Clockwise[] VALUES;

      static {
         final Collection<Clockwise> values = new ArrayList<>();
         for ( int i = 1; i < 13; i++ ) {
            values.add( new Clockwise( i + "_o_clock_position", i + " (o'?)? ?clock( position)?" ) );
            values.add( new Clockwise( i + "_30_o_clock_position", i + "(.|:)30 (o'?)? ?clock( position)?" ) );
         }
         VALUES = values.toArray( new Clockwise[ values.size() ] );
      }

      static public Clockwise[] values() {
         return VALUES;
      }

      private final String __uri;
      private final Pattern __pattern;

      Clockwise( final String uri, final String regex ) {
         __uri = uri;
         __pattern = Pattern.compile( "\\b(" + regex + ")\\.?,?\\b", Pattern.CASE_INSENSITIVE );
      }

      public String getUri() {
         return OwlConstants.BREAST_CANCER_OWL + "#" + __uri;
      }

      public Matcher getMatcher( final CharSequence lookupWindow ) {
         return __pattern.matcher( lookupWindow );
      }
   }

}
