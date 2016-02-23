package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
class Tvalue implements TnmValue {
   static private final Tvalue[] VALUES;

   static {
      final Collection<Tvalue> values = new ArrayList<>();
      values.add( new Tvalue( "X", "TX_Stage_Finding", "", "X" ) );
      values.add( new Tvalue( "IS", "TIS_Stage_Finding", "", "IS" ) );
      values.add( new Tvalue( "A", "TA_Stage_Finding", "", "A" ) );
      values.add( new Tvalue( "0", "T0_Stage_Finding", "", "0" ) );
      values.add( new Tvalue( "4", "T4_Stage_Finding", "", "(IV|4)" ) );
      for ( int i = 1; i < 4; i++ ) {
         values.add( new Tvalue( "" + i, "T" + i + "_Stage_Finding", "", "[I]{" + i + "}|" + i ) );
      }
      for ( int i = 1; i < 4; i++ ) {
         for ( char c = 'a'; c <= 'z'; c++ ) {
            values.add( new Tvalue(
                  "" + i + c, "T" + i + c + "_Stage_Finding", "", "([I]{" + i + "}|" + i + ")" + c ) );
         }
      }
      for ( char c = 'a'; c <= 'z'; c++ ) {
         values.add( new Tvalue( "4" + c, "T4" + c + "_Stage_Finding", "", "(IV|4)" + c ) );
      }
      VALUES = values.toArray( new Tvalue[ values.size() ] );
   }

   static public Tvalue[] values() {
      return VALUES;
   }

   static private final String PARENT_URI = "#TNMValue";

   final private String _title;
   final private String _uri;
   final private String _cui;
   final private Pattern _pattern;


   Tvalue( final String title, final String uri, final String cui, final String regex ) {
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

}
