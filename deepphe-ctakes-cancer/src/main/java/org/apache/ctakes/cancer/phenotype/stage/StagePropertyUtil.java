package org.apache.ctakes.cancer.phenotype.stage;

import org.apache.ctakes.cancer.phenotype.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

/**
 * Singleton class with Utilities to interact with neoplasm Receptor Status property annotations, mostly by uri.
 *
 * should be used to:
 * <ul>
 * test that an annotation is of the desired property {@link #isCorrectProperty(IdentifiedAnnotation)}
 * get the property type uri from text {@link #getTypeUri(String)}
 * get the property value uri from text {@link #getValueUri(String)}
 *</ul>
 *
 * In addition there are static methods to:
 * <ul>
 * get the parent uri of the tnm property types {@link #getParentUri()}
 * </ul>
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class StagePropertyUtil extends AbstractPropertyUtil<StageType, StageValue> {

   static private final Logger LOGGER = Logger.getLogger( "StagePropertyUtil" );

   static private class SingletonHolder {
      static private StagePropertyUtil INSTANCE = new StagePropertyUtil();
   }

   static public StagePropertyUtil getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private StagePropertyUtil() {
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


   /**
    * {@inheritDoc}
    */
   @Override
   public String getTypeUri( final String typeText ) {
      return getTypeUri( typeText, StageType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getValueUri( final String valueText ) {
      return getValueUri( valueText, StageValue.values() );
   }

   /**
    * @return the uri acting as parent to all individual stage property uris
    */
   static public String getParentUri() {
      return Stage.STAGE_URI;
   }


}
