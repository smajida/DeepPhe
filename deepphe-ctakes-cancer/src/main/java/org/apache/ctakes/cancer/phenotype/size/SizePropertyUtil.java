package org.apache.ctakes.cancer.phenotype.size;

import org.apache.ctakes.cancer.owl.UriAnnotationFactory;
import org.apache.ctakes.cancer.phenotype.NeoplasmUtil;
import org.apache.ctakes.cancer.phenotype.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Singleton class with Utilities to interact with neoplasm TNM property annotations, mostly by uri.
 * <p>
 * should be used to:
 * <ul>
 * test that an annotation is of the desired property {@link #isCorrectProperty(IdentifiedAnnotation)}
 * get the property type uri from text {@link #getTypeUri(String)}
 * get the property value uri from text {@link #getValueUri(String)}
 * </ul>
 * <p>
 * In addition there are static methods to:
 * <ul>
 * get the parent uri of the tnm property types {@link #getParentUri()}
 * create an annotation consisting of individual TNM types and values {@link #createCoallescedProperty(JCas, Collection)}
 * {@link #createCoallescedProperty(JCas, IdentifiedAnnotation)}
 * </ul>
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class SizePropertyUtil extends AbstractPropertyUtil<DimensionType, DimensionValue> {

   static private final Logger LOGGER = Logger.getLogger( "SizePropertyUtil" );

   static private class SingletonHolder {
      static private SizePropertyUtil INSTANCE = new SizePropertyUtil();
   }

   static public SizePropertyUtil getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private SizePropertyUtil() {
      super( "Size" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean isCorrectProperty( final IdentifiedAnnotation annotation ) {
      return isCorrectProperty( annotation, DimensionType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected DimensionValue getUriValue( final String uri ) {
      return DimensionValue.UNKNOWN;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected DimensionValue getUnknownValue() {
      return DimensionValue.UNKNOWN;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTypeUri( final String typeText ) {
      return getTypeUri( typeText, DimensionType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getValueUri( final String valueText ) {
      return DimensionValue.QUANTITY_URI;
   }

   /**
    * @return the uri acting as parent to all individual tnm property uris
    */
   static public String getParentUri() {
      return Dimension.DIMENSION_URI;
   }

   static public IdentifiedAnnotation createCoallescedProperty( final JCas jcas, final IdentifiedAnnotation neoplasm ) {
      final Collection<IdentifiedAnnotation> dimensionAnnotations
            = NeoplasmUtil.getNeoplasmPropertiesBranch( jcas, neoplasm, getParentUri() );
      if ( dimensionAnnotations.size() > 3 ) {
         LOGGER.warn( "More than 3 dimension annotations associated with " + neoplasm.getCoveredText() );
      }
      return createCoallescedProperty( jcas, dimensionAnnotations );
   }


   static public IdentifiedAnnotation createCoallescedProperty( final JCas jcas,
                                                                final Collection<IdentifiedAnnotation> sizeAnnotations ) {
      final Collection<IdentifiedAnnotation> values = sizeAnnotations.stream()
            .map( NeoplasmUtil::getPropertyValues )
            .flatMap( Collection::stream )
            .collect( Collectors.toList() );
      final Collection<IdentifiedAnnotation> fullSize = new ArrayList<>( sizeAnnotations );
      fullSize.addAll( values );
      final int begin = fullSize.stream()
            .mapToInt( Annotation::getBegin )
            .min().getAsInt();
      final int end = fullSize.stream()
            .mapToInt( Annotation::getEnd )
            .max().getAsInt();
      return UriAnnotationFactory.createIdentifiedAnnotation( jcas, begin, end, SizePropertyUtil.getParentUri() );
   }

}
