package org.apache.ctakes.cancer.location;


import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.apache.ctakes.cancer.location.LocationModifier.BodySide;
import static org.apache.ctakes.cancer.location.LocationModifier.Quadrant;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/16/2016
 */
public class ModifierFinder {

   static private final Logger LOGGER = Logger.getLogger( "ModifierFinder" );


   static public void addLocationModifiers( final JCas jcas, final AnnotationFS lookupWindow ) {
      final int windowStartOffset = lookupWindow.getBegin();
      findModifiers( lookupWindow.getCoveredText(), Quadrant.values() )
            .forEach( s -> ModifierFactory.createLocationModifier( jcas, windowStartOffset, s ) );
      findBodySides( lookupWindow.getCoveredText() )
            .forEach( s -> ModifierFactory.createLocationModifier( jcas, windowStartOffset, s ) );
      final int windowEndOffset = lookupWindow.getEnd();
      final Collection<IdentifiedAnnotation> clockwises = getClockwises( jcas, windowStartOffset, windowEndOffset );
      for ( IdentifiedAnnotation clockwise : clockwises ) {
         ModifierFactory.createLocationModifier( jcas, clockwise );
         // Just to get rid of duplications
         clockwise.removeFromIndexes( jcas );
      }
   }


   static List<SpannedModifier> findModifiers( final String lookupWindow, final LocationModifier... modifiers ) {
      if ( lookupWindow.length() < 6 ) {
         return Collections.emptyList();
      }
      final List<SpannedModifier> spannedModifiers = new ArrayList<>();
      for ( LocationModifier modifier : modifiers ) {
         final Matcher matcher = modifier.getMatcher( lookupWindow );
         while ( matcher.find() ) {
            spannedModifiers.add( new SpannedModifier( modifier, matcher.start(), matcher.end() ) );

         }
      }
      Collections.sort( spannedModifiers, SpanOffsetComparator.getInstance() );
      return spannedModifiers;
   }

   static List<SpannedModifier> findBodySides( final String lookupWindow ) {
      final List<SpannedModifier> bodySides = findModifiers( lookupWindow, BodySide.values() );
      final Collection<SpannedModifier> removalSides = new HashSet<>();
      for ( SpannedModifier bodySide : bodySides ) {
         if ( bodySide.getModifier() == BodySide.BILATERAL ) {
            for ( SpannedModifier otherSide : bodySides ) {
               if ( !otherSide.equals( bodySide )
                    && otherSide.getStartOffset() >= bodySide.getStartOffset()
                    && otherSide.getEndOffset() <= bodySide.getEndOffset() ) {
                  // something like "left and right" should be subsumed by -bilateral-
                  removalSides.add( otherSide );
               }
            }
         }
      }
      bodySides.removeAll( removalSides );
      return bodySides;
   }

   static List<IdentifiedAnnotation> getClockwises( final JCas jCas, final int startIndex, final int endIndex ) {
      return OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( jCas,
            OwlConstants.BREAST_CANCER_OWL + "#Clockface_position" )
            .filter( a -> a.getBegin() >= startIndex )
            .filter( a -> a.getEnd() <= endIndex )
            .collect( Collectors.toList() );
   }


}
