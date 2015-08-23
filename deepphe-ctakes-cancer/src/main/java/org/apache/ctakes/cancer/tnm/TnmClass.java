package org.apache.ctakes.cancer.tnm;


import org.apache.ctakes.cancer.util.SpannedEntity;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
final class TnmClass implements SpannedEntity {

   private final TnmClassPrefixType _classPrefixType;
   private final TnmClassType _classType;
   private final int _startOffset;
   private final int _endOffset;
   private final String _value;

   TnmClass( final TnmClassPrefixType classPrefixType,
             final TnmClassType classType,
             final int startOffset,
             final int endOffset,
             final String value ) {
      _classPrefixType = classPrefixType;
      _classType = classType;
      _startOffset = startOffset;
      _endOffset = endOffset;
      _value = value;
   }

   TnmClassPrefixType getPrefix() {
      return _classPrefixType;
   }

   TnmClassType getClassType() {
      return _classType;
   }

   @Override
   public int getStartOffset() {
      return _startOffset;
   }

   @Override
   public int getEndOffset() {
      return _endOffset;
   }

   String getValue() {
      return _value;
   }

   public String toString() {
      return _classType.name() + ", " + _classType.getTitle() + ": " + _value + " ; " + _classPrefixType.getTitle();
   }

}
