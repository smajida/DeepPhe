package org.apache.ctakes.cancer.phenotype.tnm;

import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.property.DefaultProperty;
import org.apache.ctakes.cancer.phenotype.property.SpannedType;
import org.apache.ctakes.cancer.phenotype.property.SpannedValue;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
@Immutable
final class Tnm extends DefaultProperty<TnmType, TnmValue> {

   static private final Logger LOGGER = Logger.getLogger( "Tnm" );

   //   static final String TNM_URI = OwlOntologyConceptUtil.CANCER_OWL + "#TNMClassification";
   static final String TNM_URI = OwlConstants.CANCER_OWL + "#TNM_Staging_System";

   Tnm( final SpannedType<TnmType> tnmType, final SpannedValue<TnmValue> tnmValue ) {
      super( tnmType, tnmValue );
   }

   @Override
   public String toString() {
      return getSpannedType().getType().getTitle() + " " + getSpannedValue().getValue() + " Carcinoma";
   }

}
