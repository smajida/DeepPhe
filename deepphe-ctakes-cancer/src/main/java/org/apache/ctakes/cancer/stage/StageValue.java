package org.apache.ctakes.cancer.stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
public enum StageValue {
   IS_0( "Breast Carcinoma In Situ", "C0154084", "(IS|0)\\b" ),
   I( "Stage I Breast Carcinoma", "C0278485", "(I|1)\\b" ),
   I_A( "Stage IA Breast Carcinoma", "CL413891", "(IA|1A)\\b" ),
   I_B( "Stage IB Breast Carcinoma", "CL413892", "(IB|1B)\\b" ),
   II( "Stage II Breast Carcinoma", "C0278486", "(II|2)\\b" ),
   II_A( "Stage IIA Breast Carcinoma", "C1336156", "(IIA|2A)\\b" ),
   II_B( "Stage IIB Breast Carcinoma", "C1336178", "(IIB|2B)\\b" ),
   III( "Stage III Breast Carcinoma", "C0278487", "(III|3)\\b" ),
   III_A( "Stage IIIA Breast Carcinoma", "C0278489", "(IIIA|3A)\\b" ),
   III_B( "Stage IIIB Breast Carcinoma", "C0278513", "(IIIB|3B)\\b" ),
   III_C( "Stage IIIC Breast Carcinoma", "CL473795", "(IIIC|3C)\\b" ),
   IV( "Stage IV Breast Carcinoma", "C0278488", "(IV|4)\\b" ),
   Rcr( "Recurrent Breast Carcinoma", "C0278493", "\\bRecurrent" );

   static private final String STAGE_PREFIX = "\\bStage\\s*";
   static private final String TUI = "T191";

   final private String _title;
   final private String _cui;
   final private Pattern _pattern;


   private StageValue( final String title, final String cui, final String regex ) {
      _title = title;
      _cui = cui;
//      _pattern = Pattern.compile( STAGE_PREFIX + "(" + regex + ")" );
      _pattern = Pattern.compile( STAGE_PREFIX + regex, Pattern.CASE_INSENSITIVE );
   }

   String getTitle() {
      return _title;
   }

   String getCui() {
      return _cui;
   }

   String getTui() {
      return TUI;
   }

   Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
