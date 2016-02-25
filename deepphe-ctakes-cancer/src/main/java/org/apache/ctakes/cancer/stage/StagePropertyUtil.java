package org.apache.ctakes.cancer.stage;

import org.apache.ctakes.cancer.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class StagePropertyUtil extends AbstractPropertyUtil<StageType, StageValue> {

   static private final Logger LOGGER = Logger.getLogger( "StagePropertyUtil" );


   public StagePropertyUtil() {
      super( "Stage" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean isCorrectProperty( final IdentifiedAnnotation annotation ) {
      return isCorrectProperty( annotation, StageType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected StageValue getUriValue( final String uri ) {
      return StageValue.getUriValue( uri );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected StageValue getUnknownValue() {
      return StageValue.UNKNOWN;
   }


}
