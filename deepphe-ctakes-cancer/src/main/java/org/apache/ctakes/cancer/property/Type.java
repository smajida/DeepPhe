package org.apache.ctakes.cancer.property;


import java.util.regex.Matcher;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
public interface Type {

   String getTitle();

   String getUri();

   <T extends Value> String getCui( final T value );

   String getTui();

   Matcher getMatcher( final CharSequence lookupWindow );

}
