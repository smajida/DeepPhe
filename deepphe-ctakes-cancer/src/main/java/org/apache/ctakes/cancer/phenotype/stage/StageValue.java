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
   IS( "In Situ", "Is", "IS\\b" ),
   O_IS( "0 In Situ", "Stage_0is", "0is\\b" ),
   O_A( "0A", "Stage_0a", "0A\\b" ),
   I( "1", "Stage_1", "(I|1)\\b" ),
   I_A( "1A", "Stage_1A", "(I|1)A\\b" ),
   I_A1( "1A1", "Stage_1A1", "(I|1)A1\\b" ),
   I_A2( "1A2", "Stage_1A2", "(I|1)A2\\b" ),
   I_M( "1M", "Stage_1m", "(I|1)m\\b" ),
   I_B( "1B", "Stage_1B", "(I|1)B\\b" ),
   I_B1( "1B1", "Stage_1B1", "(I|1)B1\\b" ),
   I_B2( "1B2", "Stage_1B2", "(I|1)B2\\b" ),
   II( "2", "Stage_2", "(II|2)\\b" ),
   II_A( "2A", "Stage_2A", "(II|2)A\\b" ),
   II_B( "2B", "Stage_2B", "(II|2)B\\b" ),
   II_C( "2C", "Stage_2C", "(II|2)C\\b" ),
   III( "3", "Stage_3", "(III|3)\\b" ),
   III_A( "3A", "Stage_3A", "(III|3)A\\b" ),
   III_B( "3B", "Stage_3B", "(III|3)B\\b" ),
   III_C( "3C", "Stage_3C", "(III|3)C\\b" ),
   III_C1( "3C1", "Stage_IIIC1", "(III|3)C1\\b" ),
   III_C2( "3C2", "Stage_IIIC2", "(III|3)C2\\b" ),
   IV( "4", "Stage_4", "(IV|4)\\b" ),
   IV_A( "4A", "Stage_4A", "(IV|4)A\\b" ),
   IV_B( "4B", "Stage_4B", "(IV|4)B\\b" ),
   IV_C( "4C", "Stage_4C", "(IV|4)C\\b" ),
   IV_S( "4S", "Stage_IVS", "(IV|4)s\\b" ),
   RCR( "Recurrent", "Recurrence", "\\bRecurrent" ), // No longer exists
   UNKNOWN( "Unspecified", "Stage_Unspecified", "(Unknown|Unspecified)\\b" );  // No longer exists

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
