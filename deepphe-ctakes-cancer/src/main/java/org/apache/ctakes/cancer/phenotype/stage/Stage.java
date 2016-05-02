package org.apache.ctakes.cancer.phenotype.stage;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
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
final class Stage extends DefaultProperty<StageType, StageValue> {

   static private final Logger LOGGER = Logger.getLogger( "Stage" );

   static final String STAGE_URI = OwlOntologyConceptUtil.CANCER_OWL + "#CancerStage";

   Stage( final SpannedType<StageType> stageType, final SpannedValue<StageValue> stageValue ) {
      super( stageType, stageValue );
   }

   @Override
   public String toString() {
      final StageValue value = getSpannedValue().getValue();
      if ( value == StageValue.O_IS || value == StageValue.RCR || value == StageValue.UNKNOWN ) {
         return value.getTitle() + " Breast Carcinoma";
      }
      return getSpannedType().getType().getTitle() + " " + value.getTitle() + " Breast Carcinoma";
   }

}
