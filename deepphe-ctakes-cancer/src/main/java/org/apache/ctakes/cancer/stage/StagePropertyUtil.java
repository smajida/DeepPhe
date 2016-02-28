package org.apache.ctakes.cancer.stage;

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


   static public String getCoallescedUri() {
      return Stage.STAGE_URI;
   }


   static public IdentifiedAnnotation getCoallescedProperty( final JCas jcas, final IdentifiedAnnotation neoplasm ) {
      final Collection<IdentifiedAnnotation> stageAnnotations
            = InstanceUtil.getNeoplasmPropertiesBranch( jcas, neoplasm, getCoallescedUri() );
      if ( stageAnnotations.size() > 1 ) {
         LOGGER.warn( "More than 1 Stage annotation associated with " + neoplasm.getCoveredText() );
      }
      return getCoallescedProperty( jcas, stageAnnotations );
   }


   static public IdentifiedAnnotation getCoallescedProperty( final JCas jcas,
                                                             final Collection<IdentifiedAnnotation> stageAnnotations ) {
      final Collection<IdentifiedAnnotation> values = stageAnnotations.stream()
            .map( InstanceUtil::getPropertyValues )
            .flatMap( Collection::stream )
            .collect( Collectors.toList() );
      final Collection<IdentifiedAnnotation> fullStages = new ArrayList<>( stageAnnotations );
      fullStages.addAll( values );
      final int begin = fullStages.stream()
            .map( Annotation::getBegin )
            .min( Integer::min ).get();
      final int end = fullStages.stream()
            .map( InstanceUtil::getNeoplasmProperties ).flatMap( Collection::stream )
            .map( Annotation::getEnd ).max( Integer::max ).get();
      return UriAnnotationFactory.createIdentifiedAnnotation( jcas, begin, end, StagePropertyUtil.getCoallescedUri() );
   }



}
