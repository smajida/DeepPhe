package org.apache.ctakes.cancer.tnm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */ // http://en.wikipedia.org/wiki/TNM_staging_system
enum TnmClassOptionType {
   G( "Grade of cancer cells", "G[1-4](C[1-5])?" ),
   S( "Elevation of serum tumor markers", "S[0-3](C[1-5])?" ),
   R( "Completeness of the operation", "R[0-2](C[1-5])?" ),
   L( "Invasion into lymphatic vessels", "L[0-1](C[1-5])?" ),
   V( "Invasion into vein", "V[0-2](C[1-5])?" );

   final private String _title;
   final private Pattern _pattern;

   TnmClassOptionType( final String title, final String regex ) {
      _title = title;
      _pattern = Pattern.compile( regex );
   }

   String getTitle() {
      return _title;
   }

   Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
