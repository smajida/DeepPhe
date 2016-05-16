package org.apache.ctakes.cancer.concept.instance;

import org.apache.ctakes.cancer.location.LocationModifier;
import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.owl.OwlUriUtil;
import org.apache.ctakes.cancer.phenotype.PhenotypeAnnotationUtil;
import org.apache.ctakes.typesystem.type.refsem.BodySide;
import org.apache.ctakes.typesystem.type.refsem.Event;
import org.apache.ctakes.typesystem.type.refsem.EventProperties;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 3/17/2016
 */
@Immutable
final public class ConceptInstance {

   static private final Logger LOGGER = Logger.getLogger( "ConceptInstance" );

   private final IdentifiedAnnotation _annotation;

   public ConceptInstance( final IdentifiedAnnotation annotation ) {
      _annotation = annotation;
   }

   public IdentifiedAnnotation getIdentifiedAnnotation() {
      return _annotation;
   }
   
   /**
    * @return the url of the instance
    */
   public String getUri() {
      return OwlUriUtil.getSpecificUri( _annotation );
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

   static private final String TEST_QUADRANT_URI = LocationModifier.Quadrant.LOWER_OUTER.getUri();

   public String getQuadrantUri() {
      final IdentifiedAnnotation firstAnatomical = getAnatomicalSites().findFirst().get();
      if ( firstAnatomical == null ) {
         return TEST_QUADRANT_URI;
      }
      return PhenotypeAnnotationUtil.getQuadrants( firstAnatomical ).stream()
            .map( OwlOntologyConceptUtil::getUris )
            .flatMap( Collection::stream )
            .findAny()
            .orElse( TEST_QUADRANT_URI );
   }

   static private final String TEST_CLOCKWISE = OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#4_o_clock_position";

   public String getClockwiseUri() {
      final IdentifiedAnnotation firstAnatomical = getAnatomicalSites().findFirst().get();
      if ( firstAnatomical == null ) {
         return TEST_CLOCKWISE;
      }
      return PhenotypeAnnotationUtil.getQuadrants( firstAnatomical ).stream()
            .map( OwlOntologyConceptUtil::getUris )
            .flatMap( Collection::stream )
            .findAny()
            .orElse( TEST_CLOCKWISE );

   }

   static private final String TEST_BODY_SIDE = OwlOntologyConceptUtil.CANCER_OWL + "#Left";

   public String getBodySideUri() {
      return getAnatomicalSites()
            .map( AnatomicalSiteMention::getBodySide )
            .map( Modifier::getNormalizedForm )
            .filter( BodySide.class::isInstance )
            .map( n -> (BodySide)n )
            .map( BodySide::getValue )
            .findFirst().orElse( TEST_BODY_SIDE );
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
      return PhenotypeAnnotationUtil.getLocations( _annotation ).stream()
            .filter( AnatomicalSiteMention.class::isInstance )
            .map( a -> (AnatomicalSiteMention)a );
   }



}