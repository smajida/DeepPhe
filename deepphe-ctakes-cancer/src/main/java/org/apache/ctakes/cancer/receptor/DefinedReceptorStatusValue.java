package org.apache.ctakes.cancer.receptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
enum DefinedReceptorStatusValue implements ReceptorStatusValue {
   POSITIVE( "Positive", Boolean.TRUE, "\\+|pos" ),
   NEGATIVE( "Negative", Boolean.FALSE, "-|neg" ),
   UNKNOWN( "Unknown", null, "unknown|indeterminate|equivocal|(not assessed)|\\bN/?A\\b" );

   final private String _title;
   final private Boolean _booleanValue;
   final private Pattern _pattern;

   DefinedReceptorStatusValue( final String title, final Boolean booleanValue, final String regex ) {
      _title = title;
      _booleanValue = booleanValue;
      _pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
   }

   @Override
   public String getTitle() {
      return _title;
   }

   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

   @Override
   public Boolean getBooleanValue() {
      return _booleanValue;
   }

}
