package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.instance.InstanceUtil;
import org.apache.ctakes.cancer.owl.UriAnnotationFactory;
import org.apache.ctakes.cancer.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

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
 * create an annotation consisting of individual TNM types and values {@link #createCoallescedProperty(JCas, Collection)}
 *       {@link #createCoallescedProperty(JCas, IdentifiedAnnotation)}
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

   static public IdentifiedAnnotation createCoallescedProperty( final JCas jcas, final IdentifiedAnnotation neoplasm ) {
      final Collection<IdentifiedAnnotation> tnmAnnotations
            = InstanceUtil.getNeoplasmPropertiesBranch( jcas, neoplasm, getParentUri() );
      if ( tnmAnnotations.size() > 3 ) {
         LOGGER.warn( "More than 3 TNM annotations associated with " + neoplasm.getCoveredText() );
      }
      return createCoallescedProperty( jcas, tnmAnnotations );
   }


   static public IdentifiedAnnotation createCoallescedProperty( final JCas jcas,
                                                                final Collection<IdentifiedAnnotation> tnmAnnotations ) {
      final Collection<IdentifiedAnnotation> values = tnmAnnotations.stream()
            .map( InstanceUtil::getPropertyValues )
            .flatMap( Collection::stream )
            .collect( Collectors.toList() );
      final Collection<IdentifiedAnnotation> prefixes = tnmAnnotations.stream()
            .map( InstanceUtil::getDiagnosticTests )
            .flatMap( Collection::stream )
            .collect( Collectors.toList() );
      final Collection<IdentifiedAnnotation> fullTnms = new ArrayList<>( tnmAnnotations );
      fullTnms.addAll( values );
      fullTnms.addAll( prefixes );
      final int begin = fullTnms.stream()
            .map( Annotation::getBegin )
            .min( Integer::min ).get();
      final int end = fullTnms.stream()
            .map( InstanceUtil::getNeoplasmProperties ).flatMap( Collection::stream )
            .map( Annotation::getEnd ).max( Integer::max ).get();
      return UriAnnotationFactory.createIdentifiedAnnotation( jcas, begin, end, TnmPropertyUtil.getParentUri() );
   }

}
