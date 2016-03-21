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
      values.add( new Mvalue( "X", "MX", "X" ) );
      values.add( new Mvalue( "0", "M0", "0" ) );
      values.add( new Mvalue( "1", "M1", "(I|1)" ) );
      for ( char c = 'a'; c <= 'z'; c++ ) {
         values.add( new Mvalue( "1" + c, "M1" + c, "(I|1)" + c ) );
      }
      VALUES = values.toArray( new Mvalue[ values.size() ] );
   }

   static public Mvalue[] values() {
      return VALUES;
   }

   static private final String PARENT_URI = "#M_Stage";

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;


   Mvalue( final String title, final String uri, final String regex ) {
      _title = title;
      _uri = uri + URI_SUFFIX + "_v7";
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
