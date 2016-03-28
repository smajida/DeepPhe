package org.apache.ctakes.cancer.phenotype.size;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.property.DefaultProperty;
import org.apache.ctakes.cancer.phenotype.property.SpannedType;
import org.apache.ctakes.cancer.phenotype.property.SpannedValue;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class Dimension extends DefaultProperty<DimensionType, DimensionValue> {

   //   static final String DIMENSION_URI = OwlOntologyConceptUtil.SCHEMA_OWL + "#DimensionalMeasurement";
   static final String DIMENSION_URI = OwlOntologyConceptUtil.CONTEXT_OWL + "#DimensionalMeasurement";

   Dimension( final SpannedType<DimensionType> dimensionType, final SpannedValue<DimensionValue> dimensionValue ) {
      super( dimensionType, dimensionValue );
   }

   @Override
   public String toString() {
      return getSpannedValue().getValue() + " " + getSpannedType().getType().getTitle() + " measurement";
   }


}
