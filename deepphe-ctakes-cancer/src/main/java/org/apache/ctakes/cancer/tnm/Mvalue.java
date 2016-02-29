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
final class Mvalue implements TnmValue {
   static private final Mvalue[] VALUES;

   static {
      final Collection<Mvalue> values = new ArrayList<>();
      values.add( new Mvalue( "X", "MX_Stage_Finding", "X" ) );
      values.add( new Mvalue( "0", "M0_Stage_Finding", "0" ) );
      values.add( new Mvalue( "1", "M1_Stage_Finding", "(I|1)" ) );
      for ( char c = 'a'; c <= 'z'; c++ ) {
         values.add( new Mvalue( "1" + c, "M1" + c + "_Stage_Finding", "(I|1)" + c ) );
      }
      VALUES = values.toArray( new Mvalue[ values.size() ] );
   }

   static public Mvalue[] values() {
      return VALUES;
   }

   static private final String PARENT_URI = "#TNMValue";

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;


   Mvalue( final String title, final String uri, final String regex ) {
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
