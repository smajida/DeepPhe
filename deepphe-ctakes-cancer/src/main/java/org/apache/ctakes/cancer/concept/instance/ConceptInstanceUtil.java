package org.apache.ctakes.cancer.concept.instance;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.PhenotypeAnnotationUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 3/17/2016
 */
final public class ConceptInstanceUtil {

   static private final Logger LOGGER = Logger.getLogger( "ConceptInstanceUtil" );

   private ConceptInstanceUtil() {
   }

   /**
    * @param jCas ye olde ...
    * @param uri  uri for instance of interest
    * @return collection of instances with the given uri
    */
   static public Collection<ConceptInstance> getExactConceptInstances( final JCas jCas, final String uri ) {
      return OwlOntologyConceptUtil.getAnnotationsByUri( jCas, uri )
            .stream()
            .map( ConceptInstance::new )
            .collect( Collectors.toList() );
   }

   /**
    * @param jCas ye olde ...
    * @param uri  uri for instance of interest
    * @return collection of instances with the given uri
    */
   static public Collection<ConceptInstance> getBranchConceptInstances( final JCas jCas, final String uri ) {
      return OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( jCas, uri )
            .map( ConceptInstance::new )
            .collect( Collectors.toList() );
   }

   /**
    * @param annotations annotations from which to create concept instances
    * @return a collection of concept instances, one per annotation
    */
   static private Collection<ConceptInstance> createConceptInstances(
         final Collection<IdentifiedAnnotation> annotations ) {
      return annotations.stream()
            .map( ConceptInstance::new )
            .collect( Collectors.toList() );
   }


   /**
    * @param phenotype tnm, stage, receptor status, size
    * @return collection of neoplasm concept instances for the given phenotype
    */
   static public Collection<ConceptInstance> getPhenotypeNeoplasms( final ConceptInstance phenotype ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getPhenotypeNeoplasms( phenotype.getIdentifiedAnnotation() ) );
   }

   /**
    * @param jcas      ye olde ...
    * @param phenotype tnm, stage, receptor status, size
    * @return collection of neoplasm concept instances for the given phenotype
    */
   static public Collection<ConceptInstance> getPhenotypeNeoplasms( final JCas jcas,
                                                                    final ConceptInstance phenotype ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getPhenotypeNeoplasms( jcas, phenotype.getIdentifiedAnnotation() ) );
   }

   /**
    * @param neoplasm neoplasm
    * @return collection of phenotype concept instances for the given neoplasm; tnm, stage, receptor status, size
    */
   static public Collection<ConceptInstance> getNeoplasmAllPhenotypes( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmAllPhenotypes( neoplasm.getIdentifiedAnnotation() ) );
   }

   /**
    * @param jcas     ye olde ...
    * @param neoplasm neoplasm
    * @return collection of phenotype concept instances for the given neoplasm; tnm, stage, receptor status, size
    */
   static public Collection<ConceptInstance> getNeoplasmAllPhenotypes( final JCas jcas,
                                                                       final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmAllPhenotypes( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   /**
    * @param jcas     ye olde ...
    * @param neoplasm a concept instance that would be the argument of a NeoplasmRelation
    * @param uri      uri for a property of interest
    * @return concept instances for properties of the given neoplasm with the given uri
    */
   static public Collection<ConceptInstance> getNeoplasmExactPhenotypes( final JCas jcas,
                                                                         final ConceptInstance neoplasm,
                                                                         final String uri ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmExactPhenotypes( jcas, neoplasm.getIdentifiedAnnotation(), uri ) );
   }

   /**
    * @param jcas      ye olde ...
    * @param neoplasm  a concept instance that would be the argument of a NeoplasmRelation
    * @param parentUri uri for a property of interest
    * @return concept instances for properties of the given neoplasm with the given uri
    */
   static public Collection<ConceptInstance> getNeoplasmBranchPhenotypes( final JCas jcas,
                                                                          final ConceptInstance neoplasm,
                                                                          final String parentUri ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil
                  .getNeoplasmBranchPhenotypes( jcas, neoplasm.getIdentifiedAnnotation(), parentUri ) );
   }


   static public Collection<ConceptInstance> getNeoplasmT( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmT( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmT( final JCas jcas,
                                                           final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmT( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmN( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmN( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmN( final JCas jcas,
                                                           final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmN( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmM( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmM( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmM( final JCas jcas,
                                                           final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmM( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmTNM( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmTNM( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmTNM( final JCas jcas,
                                                             final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmTNM( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmStage( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmStage( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmStage( final JCas jcas,
                                                               final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmStage( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmReceptorStatus( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmReceptorStatus( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmReceptorStatus( final JCas jcas,
                                                                        final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmReceptorStatus( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmSizes( final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmSizes( neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getNeoplasmSizes( final JCas jcas,
                                                               final ConceptInstance neoplasm ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getNeoplasmSizes( jcas, neoplasm.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getDiagnosticTests( final ConceptInstance testable ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getDiagnosticTests( testable.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getDiagnosticTests( final JCas jcas, final ConceptInstance testable ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getDiagnosticTests( jcas, testable.getIdentifiedAnnotation() ) );
   }


   static public Collection<ConceptInstance> getLocations( final ConceptInstance locatable ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getLocations( locatable.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getLocations( final JCas jcas,
                                                           final ConceptInstance locatable ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getLocations( jcas, locatable.getIdentifiedAnnotation() ) );
   }


   static public Collection<ConceptInstance> getPhenotypeValues( final ConceptInstance phenotype ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getPhenotypeValues( phenotype.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getPhenotypeValues( final JCas jcas,
                                                                 final ConceptInstance phenotype ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getPhenotypeValues( jcas, phenotype.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getPropertyValues( final ConceptInstance property ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getPropertyValues( property.getIdentifiedAnnotation() ) );
   }

   static public Collection<ConceptInstance> getPropertyValues( final JCas jcas,
                                                                final ConceptInstance property ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil.getPropertyValues( jcas, property.getIdentifiedAnnotation() ) );
   }


   /**
    * @param jcas      -
    * @param neoplasm  a concept instance that would be the argument of a NeoplasmRelation
    * @param parentUri parent uri for a property of interest
    * @return concept instances for properties of the given neoplasm with the given parent uri
    */
   static public Collection<ConceptInstance> getNeoplasmPropertiesBranch( final JCas jcas,
                                                                          final ConceptInstance neoplasm,
                                                                          final String parentUri ) {
      return createConceptInstances(
            PhenotypeAnnotationUtil
                  .getNeoplasmPropertiesBranch( jcas, neoplasm.getIdentifiedAnnotation(), parentUri ) );
   }


   /**
    * @return a Collection of the most specific uris for all the given annotations
    */
   private String getSpecificUri( final Collection<IdentifiedAnnotation> annotations ) {
      return getSpecificUris( annotations ).stream().findFirst().orElse( "UnknownUri" );
   }

   /**
    * @return a Collection of the most specific uris for all the given annotations
    */
   private Collection<String> getSpecificUris( final Collection<IdentifiedAnnotation> annotations ) {
      if ( annotations == null || annotations.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<String> specificUris = new HashSet<>();
      for ( IdentifiedAnnotation value : annotations ) {
         specificUris.addAll( OwlOntologyConceptUtil.getUris( value ) );
      }
      final Collection<String> backup = new ArrayList<>( specificUris );
      for ( IdentifiedAnnotation value : annotations ) {
         final Collection<String> uris = OwlOntologyConceptUtil.getUris( value );
         for ( String uri : uris ) {
            final Collection<String> uriBranch = OwlOntologyConceptUtil.getUriBranchStream( uri )
                  .collect( Collectors.toList() );
            specificUris.retainAll( uriBranch );
         }
      }
      if ( specificUris.isEmpty() ) {
         LOGGER.warn( "No Valid Uris" );
         return backup;
      }
      return specificUris;
   }

}
