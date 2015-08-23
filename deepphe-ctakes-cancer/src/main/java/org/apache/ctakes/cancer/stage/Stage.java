package org.apache.ctakes.cancer.stage;

import org.apache.ctakes.cancer.util.SpannedEntity;

import javax.annotation.concurrent.Immutable;
import java.util.logging.Logger;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class Stage implements SpannedEntity {

   static private final Logger LOGGER = Logger.getLogger( "Stage" );


   private final int _startOffset;
   private final int _endOffset;
   private final StageValue _value;

   Stage( final int startOffset, final int endOffset, final StageValue value ) {
      _startOffset = startOffset;
      _endOffset = endOffset;
      _value = value;
   }

   @Override
   public int getStartOffset() {
      return _startOffset;
   }

   @Override
   public int getEndOffset() {
      return _endOffset;
   }

   StageValue getValue() {
      return _value;
   }

}
