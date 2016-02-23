package org.apache.ctakes.cancer.property;

import java.util.regex.Matcher;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
public interface Value {

   String getTitle();

   String getUri();

   String getCui();

   Matcher getMatcher( final CharSequence lookupWindow );

}
