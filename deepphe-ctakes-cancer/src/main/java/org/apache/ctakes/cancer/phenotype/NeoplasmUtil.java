package org.apache.ctakes.cancer.phenotype;


import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
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
import java.util.stream.Collectors;

/**
 * Utility class with methods that can be used to get information about Neoplasm Properties.
 * It can be used to:
 * <ul>
 * get all neoplasm properties as a collection of annotations {@link #getNeoplasmProperties(JCas, IdentifiedAnnotation)}
 * get neoplasm properties with a specified uri {@link #getNeoplasmProperties(JCas, IdentifiedAnnotation, String)}
 * get neoplasm properties with a specified parent uri {@link #getNeoplasmPropertiesBranch(JCas, IdentifiedAnnotation, String)}
 * get values associated with a specified property annotation {@link #getPropertyValues(JCas, IdentifiedAnnotation)}
 * get diagnostic tests associated with a specified property annotation {@link #getDiagnosticTests(IdentifiedAnnotation)}
 * get location annotations for a given neoplasm {@link #getLocations(IdentifiedAnnotation)}
 * </ul>
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/25/2016
 */
@Immutable
final public class NeoplasmUtil {

   static private final Logger LOGGER = Logger.getLogger( "NeoplasmUtil" );

   private NeoplasmUtil() {
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmProperties( final IdentifiedAnnotation neoplasm ) {
      final JCas jcas = getJcas( neoplasm );
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      return getNeoplasmProperties( jcas, neoplasm );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasmProperties( final JCas jcas,
                                                                         final IdentifiedAnnotation neoplasm ) {
      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, neoplasm );
   }

   /**
    * @param jcas     -
    * @param neoplasm an annotation that would be the argument of a NeoplasmRelation
    * @param uri      uri for a property of interest
    * @return annotations for properties of the given neoplasm with the given uri
    */
   static public Collection<IdentifiedAnnotation> getNeoplasmProperties( final JCas jcas,
                                                                         final IdentifiedAnnotation neoplasm,
                                                                         final String uri ) {
      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<IdentifiedAnnotation> uriAnnotations = OwlOntologyConceptUtil.getAnnotationsByUri( jcas, uri );
      if ( uriAnnotations == null || uriAnnotations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, neoplasm );
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


   static public Collection<IdentifiedAnnotation> getPropertyValues( final IdentifiedAnnotation property ) {
      final JCas jcas = getJcas( property );
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      return getPropertyValues( jcas, property );
   }

   static public Collection<IdentifiedAnnotation> getPropertyValues( final JCas jcas,
                                                                     final IdentifiedAnnotation property ) {
      final Collection<DegreeOfTextRelation> relations = JCasUtil.select( jcas, DegreeOfTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getSecondArguments( relations, property );
   }

   static public Collection<IdentifiedAnnotation> getDiagnosticTests( final IdentifiedAnnotation testable ) {
      final JCas jcas = getJcas( testable );
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      return getDiagnosticTests( jcas, testable );
   }

   static public Collection<IdentifiedAnnotation> getDiagnosticTests( final JCas jcas,
                                                                      final IdentifiedAnnotation testable ) {
      final Collection<IndicatesTextRelation> relations = JCasUtil.select( jcas, IndicatesTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, testable );
   }

   static public Collection<IdentifiedAnnotation> getLocations( final IdentifiedAnnotation locatable ) {
      final JCas jcas = getJcas( locatable );
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      return getLocations( jcas, locatable );
   }

   static public Collection<IdentifiedAnnotation> getLocations( final JCas jcas,
                                                                final IdentifiedAnnotation locatable ) {
      final Collection<LocationOfTextRelation> relations = JCasUtil.select( jcas, LocationOfTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getFirstArguments( relations, locatable );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasms( final IdentifiedAnnotation locatable ) {
      final JCas jcas = getJcas( locatable );
      if ( jcas == null ) {
         return Collections.emptyList();
      }
      return getNeoplasms( jcas, locatable );
   }

   static public Collection<IdentifiedAnnotation> getNeoplasms( final JCas jcas,
                                                                final IdentifiedAnnotation locatable ) {
      final Collection<NeoplasmRelation> relations = JCasUtil.select( jcas, NeoplasmRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      return getSecondArguments( relations, locatable );
   }





   static public JCas getJcas( final Collection<IdentifiedAnnotation> annotations ) {
      return annotations.stream()
            .map( NeoplasmUtil::getJcas )
            .filter( jcas -> jcas != null )
            .findFirst().get();
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
