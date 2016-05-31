package org.apache.ctakes.core.util;

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.cas.FSArray;

import javax.annotation.concurrent.Immutable;
import java.util.Arrays;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/7/2015
 */
@Immutable
final public class AnnotationUtil {

   static private final Logger LOGGER = Logger.getLogger( "AnnotationUtil" );

   private AnnotationUtil() {
   }


   static private final FeatureStructure[] NO_FEATURES = new FeatureStructure[ 0 ];

   static private FeatureStructure[] getFeatureStructures( final IdentifiedAnnotation annotation ) {
      final FSArray fsArray = annotation.getOntologyConceptArr();
      if ( fsArray == null ) {
         return NO_FEATURES;
      }
      return fsArray.toArray();
   }

   /**
    * @param annotation annotation to check for desired tui(s)
    * @param wantedTuis tui(s) to check wrt the given annotation
    * @return true if the annotation has one of the wanted tuis
    */
   static public boolean hasWantedTui( final IdentifiedAnnotation annotation, final String... wantedTuis ) {
      final FeatureStructure[] featureStructures = getFeatureStructures( annotation );
      for ( FeatureStructure featureStructure : featureStructures ) {
         if ( featureStructure instanceof UmlsConcept ) {
            final String tui = ((UmlsConcept)featureStructure).getTui();
            if ( tui == null ) {
               continue;
            }
            for ( String wantedTui : wantedTuis ) {
               if ( tui.equals( wantedTui ) ) {
                  return true;
               }
            }
         }
      }
      return false;
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param testEndOffset   end offset of the test text span
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getPrecedingOrAnnotation( final int testStartOffset,
                                                                              final int testEndOffset,
                                                                              final Iterable<T> annotations ) {
      final T closestPreceding = getPrecedingAnnotation( testStartOffset, annotations );
      if ( closestPreceding != null ) {
         return closestPreceding;
      }
      return getFollowingAnnotation( testEndOffset, annotations );
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span
    */
   static public <T extends IdentifiedAnnotation> T getPrecedingAnnotation( final int testStartOffset,
                                                                            final Iterable<T> annotations ) {
      T closestPreceding = null;
      int smallestGap = Integer.MAX_VALUE;
      for ( T annotation : annotations ) {
         final int gap = testStartOffset - annotation.getEnd();
         if ( gap > 0 && gap < smallestGap ) {
            closestPreceding = annotation;
            smallestGap = gap;
         }
      }
      return closestPreceding;
   }

   /**
    * @param testEndOffset end offset of the test text span
    * @param annotations   annotations to test for proximity to test text span
    * @param <T>           extension of EventMention
    * @return the closest T to the test text span
    */
   static public <T extends IdentifiedAnnotation> T getFollowingAnnotation( final int testEndOffset,
                                                                            final Iterable<T> annotations ) {
      T closestFollowing = null;
      int smallestGap = Integer.MAX_VALUE;
      for ( T annotation : annotations ) {
         final int gap = annotation.getBegin() - testEndOffset;
         if ( gap > 0 && gap < smallestGap ) {
            closestFollowing = annotation;
            smallestGap = gap;
         }
      }
      return closestFollowing;
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param testEndOffset   end offset of the test text span
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest T to the test text span
    */
   static public <T extends IdentifiedAnnotation> T getClosestAnnotation( final int testStartOffset,
                                                                          final int testEndOffset,
                                                                          final Iterable<T> annotations ) {
      T closestAnnotation = null;
      int smallestGap = Integer.MAX_VALUE;
      for ( T annotation : annotations ) {
         final int gap = Math.max( annotation.getBegin() - testEndOffset,
               testStartOffset - annotation.getEnd() );
         if ( gap < smallestGap ) {
            closestAnnotation = annotation;
            smallestGap = gap;
         }
      }
      return closestAnnotation;
   }


   /**
    * TODO kludgy at this point.
    *
    * @param testStartOffset start offset of the test text span
    * @param testEndOffset   end offset of the test text span
    * @param annotation1     -
    * @param annotation2     -
    * @return true if the diseaseDisorder is closer to the test text span than the signSymptom
    */
   static public IdentifiedAnnotation getCloserAnnotation( final int testStartOffset, final int testEndOffset,
                                                           final IdentifiedAnnotation annotation1,
                                                           final IdentifiedAnnotation annotation2 ) {
      if ( annotation1 == null ) {
         return annotation2;
      } else if ( annotation2 == null ) {
         return annotation1;
      }
      return getClosestAnnotation( testStartOffset, testEndOffset, Arrays.asList( annotation1, annotation2 ) );
   }


}
