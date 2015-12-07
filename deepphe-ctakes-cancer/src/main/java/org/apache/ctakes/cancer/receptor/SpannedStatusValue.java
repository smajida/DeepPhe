package org.apache.ctakes.cancer.receptor;


import org.apache.ctakes.cancer.util.SpannedEntity;
import org.apache.log4j.Logger;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/6/2015
 */
public class SpannedStatusValue implements SpannedEntity {

   static private final Logger LOGGER = Logger.getLogger( "SpannedStatusValue" );

   private final StatusValue _statusValue;
   private final int _startOffset;
   private final int _endOffset;

   SpannedStatusValue( final StatusValue statusValue, final int typeStartOffset, final int typeEndOffset ) {
      _statusValue = statusValue;
      _startOffset = typeStartOffset;
      _endOffset = typeEndOffset;
   }

   StatusValue getStatusValue() {
      return _statusValue;
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
      return _statusValue.getTitle() + " at " + getStartOffset() + "-" + getEndOffset();
   }


}
