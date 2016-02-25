package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.property.SpannedValue;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
@Immutable
final class SpannedTnmValue extends SpannedValue<TnmValue> {

   static private final Logger LOGGER = Logger.getLogger( "SpannedStageValue" );


   SpannedTnmValue( final TnmValue tnmValue, final int startOffset, final int endOffset ) {
      super( tnmValue, startOffset, endOffset );
   }

}
