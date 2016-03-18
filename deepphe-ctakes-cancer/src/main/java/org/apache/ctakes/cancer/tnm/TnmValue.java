package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.property.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
interface TnmValue extends Value {

   String PARENT_URI = "#TNM_Staging_System";
   String URI_SUFFIX = "_TNM_Finding";


   TnmValue UNKNOWN = new TnmValue() {
      public String getTitle() {
         return "Unknown";
      }

      public String getUri() {
         return "";
      }

      public String getCui() {
         return "";
      }

      public Matcher getMatcher( final CharSequence lookupWindow ) {
         return null;
      }
   };

   /**
    * @param uri full uri
    * @return StatusValue with the given uri or UNKNOWN if not found
    */
   static TnmValue getUriValue( final String uri ) {
      for ( TnmValue value : TnmValue.values() ) {
         if ( value.getUri().equals( uri ) ) {
            return value;
         }
      }
      return UNKNOWN;
   }

   /**
    * @return array with all T, N, M values in a single array.  Mimics enum .values()
    */
   static TnmValue[] values() {
      final Collection<TnmValue> values = new ArrayList<>();
      values.addAll( Arrays.asList( Tvalue.values() ) );
      values.addAll( Arrays.asList( Nvalue.values() ) );
      values.addAll( Arrays.asList( Mvalue.values() ) );
      return values.toArray( new TnmValue[ values.size() ] );
   }

}
