package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class TnmPropertyUtil extends AbstractPropertyUtil<TnmType, TnmValue> {

   static private final Logger LOGGER = Logger.getLogger( "TnmPropertyUtil" );


   public TnmPropertyUtil() {
      super( "TNM" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean isCorrectProperty( final IdentifiedAnnotation annotation ) {
      return isCorrectProperty( annotation, TnmType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected TnmValue getUriValue( final String uri ) {
      return TnmValue.getUriValue( uri );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected TnmValue getUnknownValue() {
      return TnmValue.UNKNOWN;
   }


}
