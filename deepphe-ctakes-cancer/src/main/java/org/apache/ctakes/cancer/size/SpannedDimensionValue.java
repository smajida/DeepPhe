package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.property.SpannedValue;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
@Immutable
final class SpannedDimensionValue extends SpannedValue<DimensionValue> {

   static private final Logger LOGGER = Logger.getLogger( "SpannedDimensionValue" );


   SpannedDimensionValue( final DimensionValue sizeValue, final int startOffset, final int endOffset ) {
      super( sizeValue, startOffset, endOffset );
   }

}
