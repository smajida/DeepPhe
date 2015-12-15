package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.util.SpannedEntity;
import org.apache.log4j.Logger;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/6/2015
 */
final class SpannedStatusType implements SpannedEntity {

   static private final Logger LOGGER = Logger.getLogger( "SpannedStatusType" );

   private final StatusType _statusType;
   private final int _startOffset;
   private final int _endOffset;


   SpannedStatusType( final StatusType statusType, final int typeStartOffset, final int typeEndOffset ) {
      _statusType = statusType;
      _startOffset = typeStartOffset;
      _endOffset = typeEndOffset;
   }

   StatusType getStatusType() {
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

   @Override
   public String toString() {
      return _statusType.getTitle() + " at " + getStartOffset() + "-" + getEndOffset();
   }

}
