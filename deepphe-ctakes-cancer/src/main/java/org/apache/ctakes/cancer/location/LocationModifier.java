package org.apache.ctakes.cancer.location;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;

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
      UPPER_INNER( "Upper_Inner_Quadrant_of_the_Breast", "upper ?-? ?inner( quadrant)?" ),
      LOWER_INNER( "Lower_Inner_Quadrant_of_the_Breast", "lower ?-? ?inner( quadrant)?" ),
      UPPER_OUTER( "Upper_Outer_Quadrant_of_the_Breast", "upper ?-? ?outer( quadrant)?" ),
      LOWER_OUTER( "Lower_Outer_Quadrant_of_the_Breast", "lower ?-? ?outer( quadrant)?" ),
      CENTRAL_PORTION( "Central_Portion_of_the_Breast", "central portion" );
      private final String __uri;
      private final Pattern __pattern;

      Quadrant( final String uri, final String regex ) {
         __uri = uri;
         __pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
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
         __pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
      }

      public String getUri() {
         return OwlConstants.CANCER_OWL + "#" + __uri;
      }

      public Matcher getMatcher( final CharSequence lookupWindow ) {
         return __pattern.matcher( lookupWindow );
      }
   }


}
