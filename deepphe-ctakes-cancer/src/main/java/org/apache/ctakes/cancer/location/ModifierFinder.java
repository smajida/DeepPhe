package org.apache.ctakes.cancer.location;


import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;

import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.apache.ctakes.cancer.location.LocationModifier.*;

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
      // To get rid of unwanted clockwise duplications
      removeClockwises( jcas, windowStartOffset, lookupWindow.getEnd() );
      findModifiers( lookupWindow.getCoveredText(), Clockwise.values() )
            .forEach( s -> ModifierFactory.createLocationModifier( jcas, windowStartOffset, s ) );
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
      final List<SpannedModifier> bilaterals = bodySides.stream()
            .filter( s -> s.getModifier() == BodySide.BILATERAL )
            .collect( Collectors.toList() );
      if ( bilaterals.isEmpty() ) {
         return bodySides;
      }
      bodySides.removeAll( bilaterals );
      final Collection<SpannedModifier> removalSides = new HashSet<>();
      for ( SpannedModifier bilateral : bilaterals ) {
         bodySides.stream()
               .filter( b -> b.getStartOffset() >= bilateral.getStartOffset() )
               .filter( b -> b.getEndOffset() <= bilateral.getEndOffset() )
               .forEach( removalSides::add );
      }
      bodySides.removeAll( removalSides );
      bodySides.addAll( bilaterals );
      return bodySides;
   }

   /**
    * remove clockwise annotations within the given span
    *
    * @param jCas       -
    * @param startIndex -
    * @param endIndex   -
    */
   static private void removeClockwises( final JCas jCas, final int startIndex, final int endIndex ) {
      OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( jCas,
            OwlConstants.BREAST_CANCER_OWL + "#Clockface_position" )
            .filter( a -> a.getBegin() >= startIndex )
            .filter( a -> a.getEnd() <= endIndex )
            .forEach( TOP::removeFromIndexes );
   }


}
