package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.util.SpannedEntity;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class Size implements SpannedEntity {

   private final int _startOffset;
   private final int _endOffset;
   private final List<Measurement> _measurements;

   Size( final int startOffset, final int endOffset, final Collection<Measurement> measurements ) {
      _startOffset = startOffset;
      _endOffset = endOffset;
      _measurements = Collections.unmodifiableList( new ArrayList<>( measurements ) );
   }

   @Override
   public int getStartOffset() {
      return _startOffset;
   }

   @Override
   public int getEndOffset() {
      return _endOffset;
   }

   List<Measurement> getMeasurements() {
      return _measurements;
   }

   public String toString() {
      if ( _measurements.isEmpty() ) {
         return "No size";
      }
      final StringBuilder sb = new StringBuilder();
      for ( Measurement measurement : _measurements ) {
         sb.append( measurement.toString() ).append( " x " );
      }
      sb.setLength( sb.length() - 3 );
      return "Size: " + sb.toString();
   }
}
