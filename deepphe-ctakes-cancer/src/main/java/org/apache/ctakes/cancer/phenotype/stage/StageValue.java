package org.apache.ctakes.cancer.phenotype.stage;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.property.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
enum StageValue implements Value {
   IS_0( "In Situ", "In_Situ_Lesion", "(IS|0)\\b" ),
   I( "I", "Stage_I", "(I|1)\\b" ),
   I_A( "IA", "Stage_IA", "(IA|1A)\\b" ),
   I_B( "IB", "Stage_IB", "(IB|1B)\\b" ),
   II( "II", "Stage_II", "(II|2)\\b" ),
   II_A( "IIA", "Stage_IIA", "(IIA|2A)\\b" ),
   II_B( "IIB", "Stage_IIB", "(IIB|2B)\\b" ),
   III( "III", "Stage_III", "(III|3)\\b" ),
   III_A( "IIIA", "Stage_IIIA", "(IIIA|3A)\\b" ),
   III_B( "IIIB", "Stage_IIIB", "(IIIB|3B)\\b" ),
   III_C( "IIIC", "Stage_IIIC", "(IIIC|3C)\\b" ),
   IV( "IV", "Stage_IV", "(IV|4)\\b" ),
   RCR( "Recurrent", "Recurrence", "\\bRecurrent" ),
   UNKNOWN( "Unspecified", "Stage_Unspecified", "(Unknown|Unspecified)\\b" );

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;


   StageValue( final String title, final String uri, final String regex ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTitle() {
      return _title;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getUri() {
      return OwlOntologyConceptUtil.CANCER_OWL + "#" + _uri;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

   /**
    * @param uri full uri
    * @return StatusValue with the given uri or UNKNOWN if not found
    */
   static public StageValue getUriValue( final String uri ) {
      for ( StageValue stageValue : StageValue.values() ) {
         if ( stageValue.getUri().equals( uri ) ) {
            return stageValue;
         }
      }
      return StageValue.UNKNOWN;
   }

}
