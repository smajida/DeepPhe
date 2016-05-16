package org.apache.ctakes.cancer.location;

import org.apache.ctakes.cancer.util.SpannedEntity;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/16/2016
 */
class SpannedModifier implements SpannedEntity {
   private final LocationModifier __modifier;
   private final int __startOffset;
   private final int __endOffset;

   SpannedModifier( final LocationModifier modifier, final int startOffset, final int endOffset ) {
      __modifier = modifier;
      __startOffset = startOffset;
      __endOffset = endOffset;
   }

   final public LocationModifier getModifier() {
      return __modifier;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   final public int getStartOffset() {
      return __startOffset;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   final public int getEndOffset() {
      return __endOffset;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   final public String toString() {
      return __modifier.getTitle() + " at " + getStartOffset() + "-" + getEndOffset();
   }
}
