package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.property.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
enum StatusValue implements Value {
   POSITIVE( "Positive", "Positive", "\\+?pos(itive)?|\\+(pos)?" ),
   NEGATIVE( "Negative", "Negative", "-?neg(ative)?|-(neg)?" ),
   UNKNOWN( "Unknown", "Unknown", "unknown|indeterminate|equivocal|(not assessed)|\\bN/?A\\b" );
   //   http://ontologies.dbmi.pitt.edu/deepphe/nlpBreastCancer.owl#Equivocal
   final private String _title;
   final private String _uri;
   final private Pattern _pattern;


   static private final String VALUE_TYPE_URI = "http://ontologies.dbmi.pitt.edu/deepphe/nlpCancer.owl#TNMValue";

   StatusValue( final String title, final String uri, final String regex ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
   }

   public String getTitle() {
      return _title;
   }

   public String getUri() {
      return OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + _uri;
   }

   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

   /**
    * @param uri full uri
    * @return StatusValue with the given uri or UNKNOWN if not found
    */
   static public StatusValue getUriValue( final String uri ) {
      for ( StatusValue statusValue : StatusValue.values() ) {
         if ( statusValue.getUri().equals( uri ) ) {
            return statusValue;
         }
      }
      return StatusValue.UNKNOWN;
   }

}
