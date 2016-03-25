package org.apache.ctakes.cancer.phenotype.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.property.DefaultProperty;
import org.apache.ctakes.cancer.phenotype.property.SpannedType;
import org.apache.ctakes.cancer.phenotype.property.SpannedValue;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
@Immutable
final class Status extends DefaultProperty<StatusType, StatusValue> {


   static final String RECEPTOR_STATUS_URI = OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Receptor_Status";

   Status( final SpannedType<StatusType> statusType, final SpannedValue<StatusValue> statusValue ) {
      super( statusType, statusValue );
   }

   @Override
   public String toString() {
      return getSpannedType().getType().getTitle() + " status is " + getSpannedValue().getValue().getTitle();
   }

}
