package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.util.SpannedEntity;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
@Immutable
final class ReceptorStatus implements SpannedEntity {

   private final ReceptorStatusType _statusType;
   private final int _startOffset;
   private final int _endOffset;
   private final ReceptorStatusValue _statusValue;

   ReceptorStatus( final ReceptorStatusType statusType, final int startOffset, final int endOffset,
                   final ReceptorStatusValue statusValue ) {
      _statusType = statusType;
      _startOffset = startOffset;
      _endOffset = endOffset;
      _statusValue = statusValue;
   }

   ReceptorStatusType getStatusType() {
      return _statusType;
   }

   @Override
   public int getStartOffset() {
      return _startOffset;
   }

   @Override
   public int getEndOffset() {
      return _endOffset;
   }

   ReceptorStatusValue getStatusValue() {
      return _statusValue;
   }

   public String toString() {
      return _statusType.getTitle() + ": " + _statusValue.getTitle() + " at " + getStartOffset() + "-" + getEndOffset();
   }

}
