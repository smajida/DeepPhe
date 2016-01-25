package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
public enum StatusValue {
   POSITIVE( "Positive", Boolean.TRUE, "Positive", "\\+(pos)?|pos(itive)?" ),
   NEGATIVE( "Negative", Boolean.FALSE, "Negative", "-(neg)?|neg(ative)?" ),
   UNKNOWN( "Unknown", null, "Unknown", "unknown|indeterminate|equivocal|(not assessed)|\\bN/?A\\b" );
   final private String _title;
   final private Boolean _booleanValue;
   final private String _uri;
   final private Pattern _pattern;

   StatusValue( final String title, final Boolean booleanValue, final String uri, final String regex ) {
      _title = title;
      _booleanValue = booleanValue;
      _uri = uri;
      _pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
   }

   public String getTitle() {
      return _title;
   }

   public String getUri() {
      return OwlOntologyConceptUtil.BREAST_CANCER_OWL + _uri;
   }

   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

   public Boolean getBooleanValue() {
      return _booleanValue;
   }


   /**
    * @param uri full uri
    * @return StatusValue with the given uri or UNKNOWN if not found
    */
   static public StatusValue getUriStatusValue( final String uri ) {
      for ( StatusValue statusValue : StatusValue.values() ) {
         if ( statusValue.getUri().equals( uri ) ) {
            return statusValue;
         }
      }
      return StatusValue.UNKNOWN;
   }

}
