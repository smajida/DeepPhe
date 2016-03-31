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
final class Quantity extends DefaultProperty<QuantityUnit, QuantityValue> {

   //   static final String QUANTITY_URI = OwlOntologyConceptUtil.CONTEXT_OWL + "#DimensionalMeasurement";
   static final String QUANTITY_URI = OwlOntologyConceptUtil.CONTEXT_OWL + "#Quantity";

   Quantity( final SpannedType<QuantityUnit> dimensionType, final SpannedValue<QuantityValue> dimensionValue ) {
      super( dimensionType, dimensionValue );
   }

   @Override
   public String toString() {
      return getSpannedValue().getValue() + " " + getSpannedType().getType().getTitle() + " quantity";
   }


}
