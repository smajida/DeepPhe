package org.apache.ctakes.cancer.stage;

import org.apache.ctakes.cancer.property.SpannedValue;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
@Immutable
final class SpannedStageValue extends SpannedValue<StageValue> {

   static private final Logger LOGGER = Logger.getLogger( "SpannedStageValue" );


   SpannedStageValue( final StageValue stageValue, final int startOffset, final int endOffset ) {
      super( stageValue, startOffset, endOffset );
   }

}
