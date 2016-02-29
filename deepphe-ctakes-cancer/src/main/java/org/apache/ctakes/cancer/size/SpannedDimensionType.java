package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.property.SpannedType;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
@Immutable
final class SpannedDimensionType extends SpannedType<DimensionType> {

   static private final Logger LOGGER = Logger.getLogger( "SpannedDimensionType" );

   SpannedDimensionType( final DimensionType type, final int startOffset, final int endOffset ) {
      super( type, startOffset, endOffset );
   }

}