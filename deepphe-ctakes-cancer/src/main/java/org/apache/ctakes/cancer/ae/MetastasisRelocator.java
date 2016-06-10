package org.apache.ctakes.cancer.ae;


import org.apache.ctakes.cancer.location.LocationModifier;
import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Removes Metastasis to breast locations and location modifier to non-breast locations
 * In the future this will need to be modified so that other cancer types can have such exclusions,
 * but running on patients with more than one primary type will be hazardous
 * and running full-EMR will probably never be possible without a
 * "cancer primary type recognizer" that can handle the full document and be run before this one.
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/19/2016
 */
public class MetastasisRelocator extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "MetastasisRelocator" );

   static private final Collection<String> BREAST_URIS
         = OwlOntologyConceptUtil.getUriBranchStream( OwlConstants.BREAST_CANCER_OWL + "#Breast" )
         .collect( Collectors.toSet() );

   static private final Predicate<BinaryTextRelation> isBreastLocation = r -> {
      final Annotation site = r.getArg2().getArgument();
      if ( !IdentifiedAnnotation.class.isInstance( site ) ) {
         return true;
      }
      final Collection<String> uris = OwlOntologyConceptUtil.getUris( (IdentifiedAnnotation)site );
      uris.removeAll( BREAST_URIS );
      return uris.isEmpty();
   };

   static private final BiPredicate<BinaryTextRelation, Collection<String>> isWantedLocatable = ( r, c ) -> {
      final Annotation locatable = r.getArg1().getArgument();
      if ( !IdentifiedAnnotation.class.isInstance( locatable ) ) {
         return false;
      }
      final Collection<String> uris = OwlOntologyConceptUtil.getUris( (IdentifiedAnnotation)locatable );
      uris.removeAll( c );
      return uris.isEmpty();
   };


   /**
    * Removes Metastasis to breast locations
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      // metastases
      final Collection<String> metastasisUris
            = OwlOntologyConceptUtil.getUriBranchStream( OwlConstants.BREAST_CANCER_OWL + "#Metastatic_Neoplasm" )
            .collect( Collectors.toList() );
      getBreastRelations( jcas, metastasisUris ).forEach( TOP::removeFromIndexes );
      // remove quadrant locations that are not breasts
      getNonBreastRelations( jcas, getModifierUris( LocationModifier.Quadrant.values() ) )
            .forEach( TOP::removeFromIndexes );
      // remove clockwise locations that are not breasts
      getNonBreastRelations( jcas, getModifierUris( LocationModifier.Clockwise.values() ) )
            .forEach( TOP::removeFromIndexes );
      LOGGER.info( "Finished Processing" );
   }

   static private Collection<LocationOfTextRelation> getBreastRelations( final JCas jCas,
                                                                         final Collection<String> locatableUris ) {
      return JCasUtil.select( jCas, LocationOfTextRelation.class ).stream()
            .filter( r -> isWantedLocatable.test( r, locatableUris ) )
            .filter( isBreastLocation )
            .collect( Collectors.toList() );
   }

   static private Collection<LocationOfTextRelation> getNonBreastRelations( final JCas jCas,
                                                                            final Collection<String> locatableUris ) {
      return JCasUtil.select( jCas, LocationOfTextRelation.class ).stream()
            .filter( r -> isWantedLocatable.test( r, locatableUris ) )
            .filter( isBreastLocation.negate() )
            .collect( Collectors.toList() );
   }

   static private Collection<String> getModifierUris( final LocationModifier[] modifiers ) {
      return Arrays.stream( modifiers )
            .map( LocationModifier::getUri )
            .collect( Collectors.toList() );
   }

}
