package org.apache.ctakes.cancer.phenotype.receptor;


import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.PhenotypeAnnotationUtil;
import org.apache.ctakes.cancer.phenotype.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

import java.util.Collection;

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
 * @since 12/6/2015
 */
final public class StatusPropertyUtil extends AbstractPropertyUtil<StatusType, StatusValue> {

   static private final Logger LOGGER = Logger.getLogger( "StatusPropertyUtil" );

   static private class SingletonHolder {
      static private StatusPropertyUtil INSTANCE = new StatusPropertyUtil();
   }

   static public StatusPropertyUtil getInstance() {
      return SingletonHolder.INSTANCE;
   }


   private StatusPropertyUtil() {
      super( "Receptor Status" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean isCorrectProperty( final IdentifiedAnnotation annotation ) {
      return isCorrectProperty( annotation, StatusType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected StatusValue getUriValue( final String uri ) {
      return StatusValue.getUriValue( uri );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTypeUri( final String typeText ) {
      return getTypeUri( typeText, StatusType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getValueUri( final String valueText ) {
      return getValueUri( valueText, StatusValue.values() );
   }


   /**
    * {@inheritDoc}
    */
   @Override
   protected StatusValue getUnknownValue() {
      return StatusValue.UNKNOWN;
   }

   /**
    * @return the uri acting as parent to all individual receptor status property uris
    */
   static public String getParentUri() {
      return Status.RECEPTOR_STATUS_URI;
   }

   static public boolean isTripleNegative( final Collection<IdentifiedAnnotation> receptors ) {
      if ( receptors.size() < 3 ) {
         return false;
      }
      boolean erNeg = false;
      boolean prNeg = false;
      boolean her2Neg = false;
      final String negativeUri = StatusValue.NEGATIVE.getUri();
      for ( IdentifiedAnnotation receptor : receptors ) {
         final boolean haveNegative = PhenotypeAnnotationUtil.getPhenotypeValues( receptor ).stream()
               .map( OwlOntologyConceptUtil::getUris )
               .flatMap( Collection::stream )
               .filter( negativeUri::equals )
               .findFirst()
               .isPresent();
         if ( !haveNegative ) {
            continue;
         }
         final Collection<String> receptorUris = OwlOntologyConceptUtil.getUris( receptor );
         erNeg = erNeg || receptorUris.contains( StatusType.ER.getUri() );
         prNeg = prNeg || receptorUris.contains( StatusType.PR.getUri() );
         her2Neg = her2Neg || receptorUris.contains( StatusType.HER2.getUri() );
      }
      return erNeg && prNeg && her2Neg;
   }


}
