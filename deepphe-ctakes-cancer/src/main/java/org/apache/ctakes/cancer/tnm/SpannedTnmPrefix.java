package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.property.SpannedTest;

import java.util.logging.Logger;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/24/2016
 */
public class SpannedTnmPrefix extends SpannedTest<TnmPrefix> {

   static private final Logger LOGGER = Logger.getLogger( "SpannedTnmPrefix" );

   SpannedTnmPrefix( final TnmPrefix test, final int startOffset, final int endOffset ) {
      super( test, startOffset, endOffset );
   }

}
