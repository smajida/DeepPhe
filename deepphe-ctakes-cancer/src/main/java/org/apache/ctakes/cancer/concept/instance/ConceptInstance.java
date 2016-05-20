package org.apache.ctakes.cancer.concept.instance;

import org.apache.ctakes.cancer.owl.OwlUriUtil;
import org.apache.ctakes.typesystem.type.refsem.Event;
import org.apache.ctakes.typesystem.type.refsem.EventProperties;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;

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
         final String dtr = properties.getDocTimeRel();
         return dtr == null ? "" : dtr;
      }
      return "";
   }

   /**
    * @return Actual, hypothetical, hedged, generic
    */
   public String getModality() {
      final EventProperties properties = getEventProperties();
      if ( properties != null ) {
         final String cm = properties.getContextualModality();
         return cm == null ? "" : cm;
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


   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder();
      sb.append( "ConceptInstance: " )
            .append( getIdentifiedAnnotation().getCoveredText() )
            .append( " " ).append( getUri() )
            .append( "\tis a " ).append( getIdentifiedAnnotation().getClass().getName() )
            .append( isNegated() ? "\tnegated" : "" )
            .append( isUncertain() ? "\tuncertain" : "" )
            .append( isConditional() ? "\tconditional" : "" )
            .append( isHypothetical() ? "\thypothetical" : "" )
            .append( isPermanent() ? "\tpermanent" : "" )
            .append( isIntermittent() ? "\tintermittent" : "" )
            .append( inPatientHistory() ? "\tpatient history" : "" )
            .append( getSubject().isEmpty() ? "" : "\t" + getSubject() )
            .append( getDocTimeRel().isEmpty() ? "" : "\t" + getDocTimeRel() )
            .append( getModality().isEmpty() ? "" : "\t" + getModality() );
      return sb.toString();
   }

}