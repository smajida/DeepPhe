package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.property.DefaultProperty;
import org.apache.ctakes.cancer.property.SpannedType;
import org.apache.ctakes.cancer.property.SpannedValue;
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

   static final String TNM_URI = OwlOntologyConceptUtil.CANCER_OWL + "#TNMClassification";

   Tnm( final SpannedType<TnmType> tnmType, final SpannedValue<TnmValue> tnmValue ) {
      super( tnmType, tnmValue );
   }

   @Override
   public String toString() {
      return getSpannedType().getType().getTitle() + " " + getSpannedValue().getValue() + " Carcinoma";
   }

}
