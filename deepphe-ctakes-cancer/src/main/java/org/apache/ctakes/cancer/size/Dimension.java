package org.apache.ctakes.cancer.size;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.property.DefaultProperty;
import org.apache.ctakes.cancer.property.SpannedType;
import org.apache.ctakes.cancer.property.SpannedValue;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class Dimension extends DefaultProperty<DimensionType, DimensionValue> {

   static final String DIMENSION_URI = OwlOntologyConceptUtil.SCHEMA_OWL + "#DimensionalMeasurement";

   Dimension( final SpannedType<DimensionType> dimensionType, final SpannedValue<DimensionValue> dimensionValue ) {
      super( dimensionType, dimensionValue );
   }

   @Override
   public String toString() {
      return getSpannedValue().getValue() + " " + getSpannedType().getType().getTitle() + " measurement";
   }


}
