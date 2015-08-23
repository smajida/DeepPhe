package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.util.SpannedEntity;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class TnmTumorClassification implements SpannedEntity {

   private final Map<TnmClassType, TnmClass> _tnmClassMap;
   private final Collection<TnmClassOption> _tnmClassOptions;
   private final int _startOffset;
   private final int _endOffset;

   TnmTumorClassification( final Map<TnmClassType, TnmClass> tnmClassMap,
                           final Collection<TnmClassOption> tnmClassOptions ) {
      _tnmClassMap = Collections.unmodifiableMap( tnmClassMap );
      _tnmClassOptions = Collections.unmodifiableList( new ArrayList<>( tnmClassOptions ) );
      int startOffset = Integer.MAX_VALUE;
      int endOffset = Integer.MIN_VALUE;
      for ( TnmClass tnmClass : tnmClassMap.values() ) {
         startOffset = Math.min( tnmClass.getStartOffset(), startOffset );
         endOffset = Math.max( tnmClass.getEndOffset(), endOffset );
      }
      for ( TnmClassOption option : tnmClassOptions ) {
         endOffset = Math.max( option.getEndOffset(), endOffset );
      }
      _startOffset = startOffset;
      _endOffset = endOffset;
   }

   Map<TnmClassType, TnmClass> getTnmClassMap() {
      return _tnmClassMap;
   }

   Collection<TnmClassOption> getTnmClassOptions() {
      return _tnmClassOptions;
   }

   @Override
   public int getStartOffset() {
      return _startOffset;
   }

   @Override
   public int getEndOffset() {
      return _endOffset;
   }

   public String toString() {
      final StringBuilder sb = new StringBuilder();
      for ( TnmClassType classType : TnmClassType.values() ) {
         final TnmClass tnmClass = _tnmClassMap.get( classType );
         if ( tnmClass != null ) {
            sb.append( tnmClass.toString() ).append( ", " );
         }
      }
      for ( TnmClassOption option : _tnmClassOptions ) {
         sb.append( option.toString() ).append( ", " );
      }
      sb.setLength( sb.length() - 1 );
      return sb.toString();
   }

}
