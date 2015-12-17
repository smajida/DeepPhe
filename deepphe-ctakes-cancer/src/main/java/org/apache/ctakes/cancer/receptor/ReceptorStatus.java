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

   private final SpannedStatusType _statusType;
   private final SpannedStatusValue _statusValue;

   ReceptorStatus( final SpannedStatusType statusType, final SpannedStatusValue statusValue ) {
      _statusType = statusType;
      _statusValue = statusValue;
   }

   SpannedStatusType getStatusType() {
      return _statusType;
   }

   SpannedStatusValue getStatusValue() {
      return _statusValue;
   }

   @Override
   public int getStartOffset() {
      return _statusType.getStartOffset();
   }

   @Override
   public int getEndOffset() {
      return _statusValue.getEndOffset();
   }

   @Override
   public String toString() {
      return _statusType.toString() + " is " + _statusValue.toString();
   }

}
