package org.apache.ctakes.cancer.stage;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.property.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
public enum StageValue implements Value {
   IS_0( "In Situ", "In_Situ_Lesion", "C0154084", "(IS|0)\\b" ),
   I( "I", "Stage_I", "C0278485", "(I|1)\\b" ),
   I_A( "IA", "Stage_IA", "CL413891", "(IA|1A)\\b" ),
   I_B( "IB", "Stage_IB", "CL413892", "(IB|1B)\\b" ),
   II( "II", "Stage_II", "C0278486", "(II|2)\\b" ),
   II_A( "IIA", "Stage_IIA", "C1336156", "(IIA|2A)\\b" ),
   II_B( "IIB", "Stage_IIB", "C1336178", "(IIB|2B)\\b" ),
   III( "III", "Stage_III", "C0278487", "(III|3)\\b" ),
   III_A( "IIIA", "Stage_IIIA", "C0278489", "(IIIA|3A)\\b" ),
   III_B( "IIIB", "Stage_IIIB", "C0278513", "(IIIB|3B)\\b" ),
   III_C( "IIIC", "Stage_IIIC", "CL473795", "(IIIC|3C)\\b" ),
   IV( "IV", "Stage_IV", "C0278488", "(IV|4)\\b" ),
   RCR( "Recurrent", "Recurrence", "C0278493", "\\bRecurrent" ),
   UNKNOWN( "Unspecified", "Stage_Unspecified", "", "(Unknown|Unspecified)\\b" );

   final private String _title;
   final private String _uri;
   final private String _cui;
   final private Pattern _pattern;


   StageValue( final String title, final String uri, final String cui, final String regex ) {
      _title = title;
      _uri = uri;
      _cui = cui;
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
      return OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + _uri;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getCui() {
      return _cui;
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
