package org.apache.ctakes.cancer.phenotype.size;

import org.apache.ctakes.cancer.owl.OwlConstants;
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
   static final String QUANTITY_URI = OwlConstants.CONTEXT_OWL + "#Quantity";

   private final String _unitText;

   Quantity( final SpannedType<QuantityUnit> dimensionType, final SpannedValue<QuantityValue> dimensionValue,
             final String unitText ) {
      super( dimensionType, dimensionValue );
      _unitText = unitText.toLowerCase();
   }

   public String getUnitText() {
      return _unitText;
   }

   @Override
   public String toString() {
      return getSpannedValue().getValue() + " " + getUnitText() + " quantity";
   }


}
