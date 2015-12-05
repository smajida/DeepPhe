package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.ontology.OwlOntologyConceptUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
enum DefinedReceptorStatusValue implements ReceptorStatusValue {
   POSITIVE( "Positive", Boolean.TRUE, "Positive", "\\+|pos" ),
   NEGATIVE( "Negative", Boolean.FALSE, "Negative", "-|neg" ),
   UNKNOWN( "Unknown", null, "Unknown", "unknown|indeterminate|equivocal|(not assessed)|\\bN/?A\\b" );

   final private String _title;
   final private Boolean _booleanValue;
   final private String _uri;
   final private Pattern _pattern;

   DefinedReceptorStatusValue( final String title, final Boolean booleanValue, final String uri, final String regex ) {
      _title = title;
      _booleanValue = booleanValue;
      _uri = uri;
      _pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
   }

   @Override
   public String getTitle() {
      return _title;
   }

   @Override
   public String getUri() {
      return OwlOntologyConceptUtil.BREAST_CANCER_OWL + _uri;
   }

   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

   @Override
   public Boolean getBooleanValue() {
      return _booleanValue;
   }

}
