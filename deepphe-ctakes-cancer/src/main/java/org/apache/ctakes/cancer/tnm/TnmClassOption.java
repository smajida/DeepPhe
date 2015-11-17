package org.apache.ctakes.cancer.tnm;


import org.apache.ctakes.cancer.util.SpannedEntity;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class TnmClassOption implements SpannedEntity {

   static private final String CERTAINTY_TITLE = "Certainty of last mentioned parameter";

   private final TnmClassOptionType _optionType;
   private final int _startOffset;
   private final int _endOffset;
   private final int _value;
   private final int _certainty;

   TnmClassOption( final TnmClassOptionType optionType,
                   final int startOffset, final int endOffset,
                   final int value, final int certainty ) {
      _optionType = optionType;
      _startOffset = startOffset;
      _endOffset = endOffset;
      _value = value;
      _certainty = certainty;
   }

   TnmClassOptionType getOptionType() {
      return _optionType;
   }

   @Override
   public int getStartOffset() {
      return _startOffset;
   }

   @Override
   public int getEndOffset() {
      return _endOffset;
   }

   int getValue() {
      return _value;
   }

   int getCertainty() {
      return _certainty;
   }

   public String toString() {
      return _optionType.getTitle() + ": " + _value
             + (_certainty > 0 ? " , " + CERTAINTY_TITLE + ": " + _certainty : "");
   }
}

