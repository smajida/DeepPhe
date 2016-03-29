package org.apache.ctakes.cancer.phenotype.tnm;

import org.apache.ctakes.cancer.phenotype.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

/**
 * Singleton class with Utilities to interact with neoplasm TNM property annotations, mostly by uri.
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
final public class TnmPropertyUtil extends AbstractPropertyUtil<TnmType, TnmValue> {

   static private final Logger LOGGER = Logger.getLogger( "TnmPropertyUtil" );

   static private class SingletonHolder {
      static private TnmPropertyUtil INSTANCE = new TnmPropertyUtil();
   }

   static public TnmPropertyUtil getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private TnmPropertyUtil() {
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

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTypeUri( final String typeText ) {
      return getTypeUri( typeText, TnmType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getValueUri( final String valueText ) {
      return getValueUri( valueText, TnmValue.values() );
   }

   /**
    * @return the uri acting as parent to all individual tnm property uris
    */
   static public String getParentUri() {
      return Tnm.TNM_URI;
   }

}
