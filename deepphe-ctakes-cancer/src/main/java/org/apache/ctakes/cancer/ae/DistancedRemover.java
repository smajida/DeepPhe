package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.core.util.RelationUtil;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/10/2016
 */
public class DistancedRemover extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "DistancedRemover" );

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
      final Collection<String> neoplasmUris = NeoplasmRelationFactory.getNeoplasmUris();
      for ( String neoplasmUri : neoplasmUris ) {
         final Collection<IdentifiedAnnotation> neoplasms = OwlOntologyConceptUtil
               .getAnnotationsByUri( jcas, neoplasmUri );
         for ( IdentifiedAnnotation neoplasm : neoplasms ) {
            final Collection<LocationOfTextRelation> relations = JCasUtil.select( jcas, LocationOfTextRelation.class );
            if ( relations == null || relations.isEmpty() ) {
               continue;
            }
            for ( LocationOfTextRelation relation : relations ) {
               final Collection<IdentifiedAnnotation> locations = RelationUtil
                     .getSecondArguments( Collections.singletonList( relation ), neoplasm );
               for ( IdentifiedAnnotation location : locations ) {
                  if ( location.getBegin() > neoplasm.getEnd() ) {
                     final String between = jcas.getDocumentText().substring( neoplasm.getEnd(), location.getBegin() );
                     if ( between.contains( " from " ) ) {
                        relation.removeFromIndexes( jcas );
                        continue;
                     }
                  }
               }
            }
         }
      }
      LOGGER.info( "Finished Processing" );
   }

}
