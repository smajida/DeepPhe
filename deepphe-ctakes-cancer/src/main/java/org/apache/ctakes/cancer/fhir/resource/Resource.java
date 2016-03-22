package org.apache.ctakes.cancer.fhir.resource;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.refsem.BodyLaterality;
import org.apache.ctakes.typesystem.type.refsem.BodySide;
import org.apache.ctakes.typesystem.type.refsem.Event;
import org.apache.ctakes.typesystem.type.refsem.EventProperties;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 3/17/2016
 */
@Immutable
final public class Resource {

   static private final Logger LOGGER = Logger.getLogger( "Resource" );

   private final String _uri;
   private final IdentifiedAnnotation _annotation;

   public Resource( final String uri, final IdentifiedAnnotation annotation ) {
      _uri = uri;
      _annotation = annotation;
   }

   /**
    * @return the url of the instance
    */
   public String getUri() {
      return _uri;
   }


   /**
    * @return a Collection of the most specific neoplasm uris
    */
   public Collection<String> getNeoplasmUris() {
      final Collection<IdentifiedAnnotation> tests = InstanceUtil.getNeoplasms( _annotation );
      return getSpecificUris( tests );
   }

   /**
    * @return a Collection of the most specific value uris
    */
   public Collection<String> getValueUris() {
      final Collection<IdentifiedAnnotation> values = InstanceUtil.getPropertyValues( _annotation );
      return getSpecificUris( values );
   }

   /**
    * @return a Collection of the most specific diagnostic test uris
    */
   public Collection<String> getDiagnosticTestUris() {
      final Collection<IdentifiedAnnotation> tests = InstanceUtil.getDiagnosticTests( _annotation );
      return getSpecificUris( tests );
   }

   /**
    * @return a Collection of the most specific location uris
    */
   public Collection<String> getLocationUris() {
      final Collection<IdentifiedAnnotation> tests = InstanceUtil.getLocations( _annotation );
      return getSpecificUris( tests );
   }

   /**
    * @return true if the instance is negated: "not stage 2"
    */
   public boolean isNegated() {
      return _annotation.getPolarity() < 0;
   }

   /**
    * @return true if the instance is uncertain "might be stage 2"
    */
   public boolean isUncertain() {
      return _annotation.getUncertainty() == 1;
   }

   /**
    * @return true if the instance is hypothetical "testing may indicate stage 2"
    */
   public boolean isHypothetical() {
      return _annotation.getGeneric();
   }

   /**
    * @return true if the instance is conditional
    */
   public boolean isConditional() {
      return _annotation.getConditional();
   }

   /**
    * @return true if the instance is in patient history
    */
   public boolean inPatientHistory() {
      return _annotation.getHistoryOf() == 1;
   }

   /**
    * @return string representation of subject
    */
   public String getSubject() {
      final String subject = _annotation.getSubject();
      return subject == null ? "" : subject;
   }

   public String getDocTimeRel() {
      final EventProperties properties = getEventProperties();
      if ( properties != null ) {
         return properties.getDocTimeRel();
      }
      return "";
   }

   /**
    * @return Actual, hypothetical, hedged, generic
    */
   public String getModality() {
      final EventProperties properties = getEventProperties();
      if ( properties != null ) {
         return properties.getContextualModality();
      }
      return "";
   }

   /**
    * @return true if is an intermittent event
    */
   public boolean isIntermittent() {
      final EventProperties properties = getEventProperties();
      if ( properties != null ) {
         final String intermittent = properties.getContextualAspect();
         return intermittent != null && !intermittent.isEmpty();
      }
      return false;
   }

   public boolean isPermanent() {
      final EventProperties properties = getEventProperties();
      if ( properties != null ) {
         final String permanence = properties.getPermanence();
         return permanence != null && !permanence.equalsIgnoreCase( "Finite" );
      }
      return false;
   }

   public String getLocationLaterality() {
      final String laterality = getAnatomicalSites()
            .map( AnatomicalSiteMention::getBodyLaterality )
            .map( Modifier::getNormalizedForm )
            .filter( BodyLaterality.class::isInstance )
            .map( n -> (BodyLaterality)n )
            .map( BodyLaterality::getValue )
            .findFirst().get();
      return laterality == null ? "" : laterality;
   }

   public String getLocationBodySide() {
      final String side = getAnatomicalSites()
            .map( AnatomicalSiteMention::getBodySide )
            .map( Modifier::getNormalizedForm )
            .filter( BodySide.class::isInstance )
            .map( n -> (BodySide)n )
            .map( BodySide::getValue )
            .findFirst().get();
      return side == null ? "" : side;
   }


   private EventProperties getEventProperties() {
      if ( !EventMention.class.isInstance( _annotation ) ) {
         return null;
      }
      final Event event = ((EventMention)_annotation).getEvent();
      if ( event != null ) {
         return event.getProperties();
      }
      return null;
   }

   private Stream<AnatomicalSiteMention> getAnatomicalSites() {
      return InstanceUtil.getLocations( _annotation ).stream()
            .filter( AnatomicalSiteMention.class::isInstance )
            .map( a -> (AnatomicalSiteMention)a );
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
         LOGGER.warning( "Value URIs are not in the same branch for " + getUri() );
         return backup;
      }
      return specificUris;
   }


}