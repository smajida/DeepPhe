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
class Nvalue implements TnmValue {
   static private final Nvalue[] VALUES;

   static {
      final Collection<Nvalue> values = new ArrayList<>();
      values.add( new Nvalue( "X", "NX_Stage_Finding", "X" ) );
      values.add( new Nvalue( "0", "N0_Stage_Finding", "0" ) );
      for ( int i = 1; i < 4; i++ ) {
         values.add( new Nvalue( "" + i, "N" + i + "_Stage_Finding", "[I]{" + i + "}|" + i ) );
      }
      for ( int i = 1; i < 4; i++ ) {
         for ( char c = 'a'; c <= 'z'; c++ ) {
            values.add( new Nvalue(
                  "" + i + c, "N" + i + c + "_Stage_Finding", "([I]{" + i + "}|" + i + ")" + c ) );
         }
      }
      VALUES = values.toArray( new Nvalue[ values.size() ] );
   }

   static public Nvalue[] values() {
      return VALUES;
   }

   static private final String PARENT_URI = "#TNMValue";

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;


   Nvalue( final String title, final String uri, final String regex ) {
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
