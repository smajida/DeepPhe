package org.apache.ctakes.cancer.instance;


import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.typesystem.type.refsem.*;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.DegreeOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.IndicatesTextRelation;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.textsem.*;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
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
final public class InstanceUtil {

   static private final Logger LOGGER = Logger.getLogger( "InstanceUtil" );

   private InstanceUtil() {
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


   static public JCas getJcas( final Collection<IdentifiedAnnotation> annotations ) {
      return annotations.stream()
            .map( InstanceUtil::getJcas )
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


   /**
    * @param annotation -
    * @return a line of text with doctimerel, modality, aspect and permanence ; if available
    */
   static private String getAnnotationProperties( final IdentifiedAnnotation annotation ) {
      final StringBuilder sb = new StringBuilder();
      if ( annotation.getPolarity() < 0 ) {
         sb.append( " negated" );
      }
      if ( annotation.getUncertainty() == 1 ) {
         sb.append( " uncertain" );
      }
      if ( annotation.getGeneric() ) {
         sb.append( " generic" );
      }
      if ( annotation.getConditional() ) {
         sb.append( " conditional" );
      }
      if ( annotation.getHistoryOf() == 1 ) {
         sb.append( " in history" );
      }
      if ( annotation.getSubject() != null && !annotation.getSubject().isEmpty() ) {
         sb.append( " for " ).append( annotation.getSubject() );
      }
      return sb.toString();
   }

   /**
    * @param eventMention -
    * @return a line of text with doctimerel, modality, aspect and permanence ; if available
    */
   static private String getEventProperties( final EventMention eventMention ) {
      final Event event = eventMention.getEvent();
      if ( event == null ) {
         return "";
      }
      final EventProperties eventProperties = event.getProperties();
      if ( eventProperties == null ) {
         return "";
      }
      final StringBuilder sb = new StringBuilder();
      sb.append( " occurred " );
      sb.append( eventProperties.getDocTimeRel().toLowerCase() );
      sb.append( " document time" );
      // modality is: Actual, hypothetical, hedged, generic
      final String modality = eventProperties.getContextualModality();
      if ( modality != null && !modality.isEmpty() ) {
         sb.append( ", " );
         sb.append( modality.toLowerCase() );
      }
      // Aspect is: Intermittent (or not)
      final String aspect = eventProperties.getContextualAspect();
      if ( aspect != null && !aspect.isEmpty() ) {
         sb.append( ", " );
         sb.append( aspect.toLowerCase() );
      }
      // Permanence is: Finite or permanent
      final String permanence = eventProperties.getPermanence();
      if ( permanence != null && !permanence.isEmpty() ) {
         sb.append( ", " );
         sb.append( permanence.toLowerCase() );
      }
      return sb.toString();
   }

   /**
    * @param anatomicalSite -
    * @return a line of text with body laterality and side ; if available
    */
   static private String getAnatomicalProperties( final AnatomicalSiteMention anatomicalSite ) {
      StringBuilder sb = new StringBuilder();
      final BodyLateralityModifier laterality = anatomicalSite.getBodyLaterality();
      if ( laterality != null ) {
         final Attribute normalized = laterality.getNormalizedForm();
         if ( normalized != null && normalized instanceof BodyLaterality ) {
            sb.append( ", " );
            sb.append( ((BodyLaterality)normalized).getValue() );
         }
      }
      final BodySideModifier bodySide = anatomicalSite.getBodySide();
      if ( bodySide != null ) {
         final Attribute normalized = bodySide.getNormalizedForm();
         if ( normalized != null && normalized instanceof BodySide ) {
            sb.append( ", " );
            sb.append( ((BodySide)normalized).getValue() );
         }
      }
      return sb.toString();
   }

   /**
    * @param jcas       ye olde ...
    * @param annotation of interest
    * @return all relations with the given annotation as the first or second argument
    */
   static private Collection<String> getRelations( final JCas jcas, final IdentifiedAnnotation annotation ) {
      final Collection<BinaryTextRelation> relations = JCasUtil.select( jcas, BinaryTextRelation.class );
      if ( relations == null || relations.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<String> relationTexts = new ArrayList<>();
      for ( BinaryTextRelation relation : relations ) {
         final Annotation argument1 = relation.getArg1().getArgument();
         final Annotation argument2 = relation.getArg2().getArgument();
         if ( annotation.equals( argument1 ) || annotation.equals( argument2 ) ) {
            relationTexts.add( argument1.getCoveredText()
                               + " " + relation.getCategory().toLowerCase()
                               + " " + argument2.getCoveredText() );
         }
      }
      return relationTexts;
   }


}
