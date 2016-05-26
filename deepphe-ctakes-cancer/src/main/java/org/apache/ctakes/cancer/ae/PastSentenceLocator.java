package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.location.LocationModifier;
import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.PhenotypeAnnotationUtil;
import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.core.util.AnnotationUtil;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The ml location_of module only finds same-sentence relations.
 * Many neoplasms have locations outside the same sentence.
 * Since we can limit the sites to "breast" we shouldn't have too much performance impact.
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/25/2016
 */
public class PastSentenceLocator extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "PastSentenceLocator" );


   /**
    * Adds breast locations for neoplasms and location modifiers that do not already have them
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      // breasts
      final Collection<IdentifiedAnnotation> breasts
            = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, OwlConstants.BREAST_CANCER_OWL + "#Breast" );
      if ( breasts.isEmpty() ) {
         LOGGER.info( "Finished Processing" );
         return;
      }
      // neoplasms
      getIsolatedNeoplasms( jcas ).forEach( n -> createLocationRelation( jcas, n, breasts ) );
      // modifiers
      getIsolatedModifiers( jcas, LocationModifier.Quadrant.values() )
            .forEach( q -> createLocationRelation( jcas, q, breasts ) );
      getIsolatedModifiers( jcas, LocationModifier.BodySide.values() )
            .forEach( s -> createLocationRelation( jcas, s, breasts ) );
      getIsolatedModifiers( jcas, LocationModifier.Clockwise.values() )
            .forEach( c -> createLocationRelation( jcas, c, breasts ) );
      LOGGER.info( "Finished Processing" );
   }

   static private Stream<IdentifiedAnnotation> getIsolatedNeoplasms( final JCas jCas ) {
      // Metastases are under neoplasm, but for now we do not want them as we are interested in primary neoplasms
      // Metastases
      final Collection<String> metastasisUris
            = OwlOntologyConceptUtil.getUriBranchStream( OwlConstants.BREAST_CANCER_OWL + "#Metastatic_Neoplasm" )
            .collect( Collectors.toSet() );
      return NeoplasmRelationFactory.getNeoplasmUris().stream()
            .filter( u -> !metastasisUris.contains( u ) )
            .flatMap( u -> OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( jCas, u ) )
            .filter( n -> PhenotypeAnnotationUtil.getLocations( jCas, n ).isEmpty() );
   }

   static private Stream<IdentifiedAnnotation> getIsolatedModifiers( final JCas jCas,
                                                                     final LocationModifier[] modifiers ) {
      return Arrays.stream( modifiers )
            .map( LocationModifier::getUri )
            .map( u -> OwlOntologyConceptUtil.getAnnotationsByUri( jCas, u ) )
            .flatMap( Collection::stream )
            .filter( m -> PhenotypeAnnotationUtil.getLocations( jCas, m ).isEmpty() );
   }

   /**
    * @param jCas      ye olde ...
    * @param locatable neoplasm or location modifier
    * @param sites     breasts
    */
   static public void createLocationRelation( final JCas jCas,
                                              final IdentifiedAnnotation locatable,
                                              final Collection<IdentifiedAnnotation> sites ) {
      final IdentifiedAnnotation closestSite
            = AnnotationUtil.getPrecedingOrAnnotation( locatable.getBegin(), locatable.getEnd(), sites );
      if ( closestSite != null ) {
         createLocationRelation( jCas, locatable, closestSite );
      }
   }

   /**
    * @param jCas      ye olde ...
    * @param locatable neoplasm or location modifier
    * @param site      breast
    */
   static public void createLocationRelation( final JCas jCas,
                                              final IdentifiedAnnotation locatable,
                                              final IdentifiedAnnotation site ) {
      if ( locatable == null ) {
         LOGGER.info( "No locatable relation " + ((site != null) ? site.getCoveredText() : "") );
         return;
      }
      if ( site == null ) {
         LOGGER.info( "No breast to relate to " + locatable.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument locatableArgument = new RelationArgument( jCas );
      locatableArgument.setArgument( locatable );
      locatableArgument.setRole( "Argument" );
      locatableArgument.addToIndexes();
      final RelationArgument siteArgument = new RelationArgument( jCas );
      siteArgument.setArgument( site );
      siteArgument.setRole( "Related_to" );
      siteArgument.addToIndexes();
      final LocationOfTextRelation locationRelation = new LocationOfTextRelation( jCas );
      locationRelation.setArg1( locatableArgument );
      locationRelation.setArg2( siteArgument );
      locationRelation.setCategory( "location_of" );
      locationRelation.addToIndexes();
   }


}
