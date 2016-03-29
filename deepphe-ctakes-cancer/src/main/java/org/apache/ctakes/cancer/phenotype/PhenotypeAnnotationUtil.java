package org.apache.ctakes.cancer.phenotype;


import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.receptor.StatusPropertyUtil;
import org.apache.ctakes.cancer.phenotype.size.SizePropertyUtil;
import org.apache.ctakes.cancer.phenotype.stage.StagePropertyUtil;
import org.apache.ctakes.cancer.phenotype.tnm.TnmPropertyUtil;
import org.apache.ctakes.cancer.phenotype.tnm.TnmType;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.DegreeOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.IndicatesTextRelation;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class with methods that can be used to get information about Neoplasm Properties.
 * It can be used to:
 * <ul>
 * get all neoplasm properties as a collection of annotations {@link #getNeoplasmAllPhenotypes(JCas, IdentifiedAnnotation)}
 * get neoplasm properties with a specified uri {@link #getNeoplasmExactPhenotypes(JCas, IdentifiedAnnotation, String)}
 * get neoplasm properties with a specified parent uri {@link #getNeoplasmPropertiesBranch(JCas, IdentifiedAnnotation, String)}
 * get values associated with a specified property annotation {@link #getPropertyValues(JCas, IdentifiedAnnotation)}
 * get diagnostic tests associated with a specified property annotation {@link #getDiagnosticTests(IdentifiedAnnotation)}
 * get location annotations for a given neoplasm {@link #getLocations(IdentifiedAnnotation)}
 * </ul>
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/25/2016
 */
@Immutable
final public class PhenotypeAnnotationUtil {

   static private final Logger LOGGER = Logger.getLogger( "PhenotypeAnnotationUtil" );

   private PhenotypeAnnotationUtil() {
   }


   /**
    * @param phenotype tnm, stage, receptor status, size
    * @return collection of neoplasm annotations for the given phenotype
    */
   static public Collection<IdentifiedAnnotation> getPhenotypeNeoplasms( final IdentifiedAnnotation phenotype ) {
      return getPhenotypeNeoplasms( getJcas( phenotype ), phenotype );
   }

   /**
    * @param jcas      ye olde ...
    * @param phenotype tnm, stage, receptor status, size
    * @return collection of neoplasm annotations for the given phenotype
    */
   static public Collection<IdentifiedAnnotation> getPhenotypeNeoplasms( final JCas jcas,
                                                                         final IdentifiedAnnotation phenotype ) {
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getSecondArguments( relations, phenotype );
   }

   /**
    * @param neoplasm neoplasm
    * @return collection of phenotype annotations for the given neoplasm; tnm, stage, receptor status, size
    */
   static public Collection<IdentifiedAnnotation> getNeoplasmAllPhenotypes( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmAllPhenotypes( getJcas( neoplasm ), neoplasm );
   }

   /**
    * @param jcas     ye olde ...
    * @param neoplasm neoplasm
    * @return collection of phenotype annotations for the given neoplasm; tnm, stage, receptor status, size
    */
   static public Collection<IdentifiedAnnotation> getNeoplasmAllPhenotypes( final JCas jcas,
                                                                            final IdentifiedAnnotation neoplasm ) {
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, neoplasm );
   }

   /**
    * @param jcas     ye olde ...
    * @param neoplasm an annotation that would be the argument of a NeoplasmRelation
    * @param uri      uri for a property of interest
    * @return annotations for properties of the given neoplasm with the given uri
    */
   static public Collection<IdentifiedAnnotation> getNeoplasmExactPhenotypes( final JCas jcas,
                                                                              final IdentifiedAnnotation neoplasm,
                                                                              final String uri ) {
      final Collection<IdentifiedAnnotation> allPhenotypes = getNeoplasmAllPhenotypes( jcas, neoplasm );
      if ( allPhenotypes.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<IdentifiedAnnotation> uriAnnotations = OwlOntologyConceptUtil.getAnnotationsByUri( jcas, uri );
      if ( uriAnnotations == null || uriAnnotations.isEmpty() ) {
         return Collections.emptyList();
      }
      allPhenotypes.retainAll( uriAnnotations );
      return uriAnnotations;
   }

   /**
    * @param jcas      ye olde ...
    * @param neoplasm  an annotation that would be the argument of a NeoplasmRelation
    * @param parentUri uri for a property of interest
    * @return annotations for properties of the given neoplasm with the given uri
    */
   static public Collection<IdentifiedAnnotation> getNeoplasmBranchPhenotypes( final JCas jcas,
                                                                               final IdentifiedAnnotation neoplasm,
                                                                               final String parentUri ) {
      final Collection<IdentifiedAnnotation> allPhenotypes = getNeoplasmAllPhenotypes( jcas, neoplasm );
      if ( allPhenotypes.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<IdentifiedAnnotation> uriAnnotations
            = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, parentUri );
      if ( uriAnnotations == null || uriAnnotations.isEmpty() ) {
         return Collections.emptyList();
      }
      allPhenotypes.retainAll( uriAnnotations );
      return uriAnnotations;
   }


   static public Collection<IdentifiedAnnotation> getNeoplasmT( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmT( getJcas( neoplasm ), neoplasm );

   }

   static public Collection<IdentifiedAnnotation> getNeoplasmT( final JCas jcas,
                                                                final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmExactPhenotypes( jcas, neoplasm, TnmType.T.getUri() );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmN( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmN( getJcas( neoplasm ), neoplasm );

   }

   static public Collection<IdentifiedAnnotation> getNeoplasmN( final JCas jcas,
                                                                final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmExactPhenotypes( jcas, neoplasm, TnmType.N.getUri() );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmM( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmM( getJcas( neoplasm ), neoplasm );

   }

   static public Collection<IdentifiedAnnotation> getNeoplasmM( final JCas jcas,
                                                                final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmExactPhenotypes( jcas, neoplasm, TnmType.M.getUri() );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmTNM( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmTNM( getJcas( neoplasm ), neoplasm );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmTNM( final JCas jcas,
                                                                  final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmBranchPhenotypes( jcas, neoplasm, TnmPropertyUtil.getParentUri() );
   }


   static public Collection<IdentifiedAnnotation> getNeoplasmStage( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmStage( getJcas( neoplasm ), neoplasm );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmStage( final JCas jcas,
                                                                    final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmBranchPhenotypes( jcas, neoplasm, StagePropertyUtil.getParentUri() );
   }


   static public Collection<IdentifiedAnnotation> getNeoplasmReceptorStatus( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmReceptorStatus( getJcas( neoplasm ), neoplasm );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmReceptorStatus( final JCas jcas,
                                                                             final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmBranchPhenotypes( jcas, neoplasm, StatusPropertyUtil.getParentUri() );
   }


   static public Collection<IdentifiedAnnotation> getNeoplasmSizes( final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmSizes( getJcas( neoplasm ), neoplasm );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmSizes( final JCas jcas,
                                                                    final IdentifiedAnnotation neoplasm ) {
      return getNeoplasmBranchPhenotypes( jcas, neoplasm, SizePropertyUtil.getParentUri() );
   }


   /**
    * @param jcas      -
    * @param neoplasm  an annotation that would be the argument of a NeoplasmRelation
    * @param parentUri parent uri for a property of interest
    * @return annotations for properties of the given neoplasm with the given parent uri
    */
   static public Collection<IdentifiedAnnotation> getNeoplasmPropertiesBranch( final JCas jcas,
                                                                               final IdentifiedAnnotation neoplasm,
                                                                               final String parentUri ) {
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<IdentifiedAnnotation> uriAnnotations = OwlOntologyConceptUtil
            .getAnnotationsByUriBranch( jcas, parentUri );
      if ( uriAnnotations == null || uriAnnotations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, neoplasm );
   }

   static public Collection<IdentifiedAnnotation> getDiagnosticTests( final IdentifiedAnnotation testable ) {
      return getDiagnosticTests( getJcas( testable ), testable );
   }

   static public Collection<IdentifiedAnnotation> getDiagnosticTests( final JCas jcas,
                                                                      final IdentifiedAnnotation testable ) {
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      final Collection<IndicatesTextRelation> relations = JCasUtil.select( jcas, IndicatesTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, testable );
   }

   static public Collection<IdentifiedAnnotation> getLocations( final IdentifiedAnnotation locatable ) {
      return getLocations( getJcas( locatable ), locatable );
   }

   static public Collection<IdentifiedAnnotation> getLocations( final JCas jcas,
                                                                final IdentifiedAnnotation locatable ) {
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      final Collection<LocationOfTextRelation> relations = JCasUtil.select( jcas, LocationOfTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, locatable );
   }


//   static public Collection<String> getNeoplasmPropertyTypes( final JCas jcas, final IdentifiedAnnotation neoplasm ) {
//      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
//      if ( relations == null || relations.isEmpty() ) {
//         return Collections.emptyList();
//      }
//      return relations.stream()
//            .filter( r -> r.getArg2().getArgument().equals( neoplasm ) )
//            .map( Relation::getCategory )
//            .collect( Collectors.toList() );
//   }


   static public Collection<IdentifiedAnnotation> getPhenotypeValues( final IdentifiedAnnotation phenotype ) {
      return getPropertyValues( phenotype );
   }

   static public Collection<IdentifiedAnnotation> getPhenotypeValues( final JCas jcas,
                                                                      final IdentifiedAnnotation phenotype ) {
      return getPropertyValues( jcas, phenotype );
   }

   static public Collection<IdentifiedAnnotation> getPropertyValues( final IdentifiedAnnotation property ) {
      return getPropertyValues( getJcas( property ), property );
   }

   static public Collection<IdentifiedAnnotation> getPropertyValues( final JCas jcas,
                                                                     final IdentifiedAnnotation property ) {
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      final Collection<DegreeOfTextRelation> relations = JCasUtil.select( jcas, DegreeOfTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getSecondArguments( relations, property );
   }


   static public JCas getJcas( final Collection<IdentifiedAnnotation> annotations ) {
      return annotations.stream()
            .map( PhenotypeAnnotationUtil::getJcas )
            .filter( Objects::nonNull )
            .findFirst().orElse( null );
   }

   /**
    * @param annotation any type of annotation
    * @return jcas containing the annotation or null if there is none
    */
   static public JCas getJcas( final TOP annotation ) {
      try {
         return annotation.getCAS().getJCas();
      } catch ( CASException casE ) {
         LOGGER.error( casE.getMessage() );
      }
      return null;
   }

   static private Collection<IdentifiedAnnotation> getFirstArguments(
         final Collection<? extends BinaryTextRelation> relations,
         final IdentifiedAnnotation identifiedAnnotation ) {
      return relations.stream()
            .filter( r -> r.getArg2().getArgument().equals( identifiedAnnotation ) )
            .map( r -> r.getArg1().getArgument() )
            .filter( IdentifiedAnnotation.class::isInstance )
            .map( a -> (IdentifiedAnnotation)a )
            .collect( Collectors.toList() );
   }

   static private Collection<IdentifiedAnnotation> getSecondArguments(
         final Collection<? extends BinaryTextRelation> relations,
         final IdentifiedAnnotation identifiedAnnotation ) {
      return relations.stream()
            .filter( r -> r.getArg1().getArgument().equals( identifiedAnnotation ) )
            .map( r -> r.getArg2().getArgument() )
            .filter( IdentifiedAnnotation.class::isInstance )
            .map( a -> (IdentifiedAnnotation)a )
            .collect( Collectors.toList() );
   }


}
