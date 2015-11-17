package org.apache.ctakes.cancer.size;


import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class Measurement {

   private final float _value;
   private final String _unit;

   Measurement( final float value, final String unit ) {
      _value = value;
      _unit = unit;
   }

   float getValue() {
      return _value;
   }

   String getUnit() {
      return _unit;
   }

   public String toString() {
      return _value + " " + _unit;
   }
}
