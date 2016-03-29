package org.apache.ctakes.cancer.phenotype.tnm;

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
final class Tvalue implements TnmValue {
   static private final Tvalue[] VALUES;

   static {
      final Collection<Tvalue> values = new ArrayList<>();
      values.add( new Tvalue( "X", "TX", "X" ) );
      values.add( new Tvalue( "IS", "Tis", "IS" ) );
      values.add( new Tvalue( "A", "TA", "A" ) );
      values.add( new Tvalue( "0", "T0", "0" ) );
      values.add( new Tvalue( "4", "T4", "(IV|4)" ) );
      for ( int i = 1; i < 4; i++ ) {
         values.add( new Tvalue( "" + i, "T" + i, "[I]{" + i + "}|" + i ) );
      }
      for ( int i = 1; i < 4; i++ ) {
         for ( char c = 'a'; c <= 'z'; c++ ) {
            values.add( new Tvalue(
                  "" + i + c, "T" + i + c, "([I]{" + i + "}|" + i + ")" + c ) );
         }
      }
      for ( char c = 'a'; c <= 'z'; c++ ) {
         values.add( new Tvalue( "4" + c, "T4" + c, "(IV|4)" + c ) );
      }
      VALUES = values.toArray( new Tvalue[ values.size() ] );
   }

   static public Tvalue[] values() {
      return VALUES;
   }

   static private final String PARENT_URI = "#T_Stage";

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;


   Tvalue( final String title, final String uri, final String regex ) {
      _title = title;
      _uri = uri + URI_SUFFIX;
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
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
