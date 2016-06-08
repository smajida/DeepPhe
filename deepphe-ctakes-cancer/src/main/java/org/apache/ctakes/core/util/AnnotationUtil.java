package org.apache.ctakes.core.util;

import org.apache.ctakes.dictionary.lookup2.textspan.DefaultTextSpan;
import org.apache.ctakes.dictionary.lookup2.textspan.TextSpan;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.cas.FSArray;

import javax.annotation.concurrent.Immutable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

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

   static private final int DEFAULT_CHAR_TOLERANCE = 35;
   static private final int DEFAULT_TOKEN_TOLERANCE = 7;
   static private final double DEFAULT_FAVORATISM = 2d;
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
      return getPrecedingOrAnnotation( testStartOffset, testEndOffset, DEFAULT_CHAR_TOLERANCE, annotations );
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param testEndOffset   end offset of the test text span
    * @param tolerance       maximum character distance between annotations
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getPrecedingOrAnnotation( final int testStartOffset,
                                                                              final int testEndOffset,
                                                                              final int tolerance,
                                                                              final Iterable<T> annotations ) {
      final GappedAnnotation<T> preceding = getPrecedingAnnotation( testStartOffset, annotations );
      final GappedAnnotation<T> following = getFollowingAnnotation( testEndOffset, annotations );
      if ( preceding.__gap > following.__gap / DEFAULT_FAVORATISM && following.__gap < tolerance ) {
         return following.__annotation;
      }
      if ( preceding.__gap < tolerance ) {
         return preceding.__annotation;
      }
      return null;
   }

   /**
    * @param annotation -
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getPrecedingOrAnnotation( final IdentifiedAnnotation annotation,
                                                                              final Iterable<T> annotations ) {
      return getPrecedingOrAnnotation( annotation, DEFAULT_TOKEN_TOLERANCE, annotations );
   }

   /**
    * @param annotation  -
    * @param tolerance   maximum character distance between annotations
    * @param annotations annotations to test for proximity to test text span
    * @param <T>         extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getPrecedingOrAnnotation( final IdentifiedAnnotation annotation,
                                                                              final int tolerance,
                                                                              final Iterable<T> annotations ) {
      final GappedAnnotation<T> preceding = getPrecedingAnnotation( annotation, annotations );
      final GappedAnnotation<T> following = getFollowingAnnotation( annotation, annotations );
      if ( preceding.__gap > following.__gap / DEFAULT_FAVORATISM && following.__gap < tolerance ) {
         return following.__annotation;
      }
      if ( preceding.__gap < tolerance ) {
         return preceding.__annotation;
      }
      return null;
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param testEndOffset   end offset of the test text span
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getFollowingOrAnnotation( final int testStartOffset,
                                                                              final int testEndOffset,
                                                                              final Iterable<T> annotations ) {
      return getFollowingOrAnnotation( testStartOffset, testEndOffset, DEFAULT_CHAR_TOLERANCE, annotations );
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param testEndOffset   end offset of the test text span
    * @param tolerance       maximum character distance between annotations
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getFollowingOrAnnotation( final int testStartOffset,
                                                                              final int testEndOffset,
                                                                              final int tolerance,
                                                                              final Iterable<T> annotations ) {
      final GappedAnnotation<T> following = getFollowingAnnotation( testEndOffset, annotations );
      final GappedAnnotation<T> preceding = getPrecedingAnnotation( testStartOffset, annotations );
      if ( following.__gap > following.__gap / DEFAULT_FAVORATISM && preceding.__gap < tolerance ) {
         return preceding.__annotation;
      }
      if ( following.__gap < tolerance ) {
         return following.__annotation;
      }
      return null;
   }

   /**
    * @param annotation  -
    * @param annotations annotations to test for proximity to test text span
    * @param <T>         extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getFollowingOrAnnotation( final IdentifiedAnnotation annotation,
                                                                              final Iterable<T> annotations ) {
      return getFollowingOrAnnotation( annotation, DEFAULT_TOKEN_TOLERANCE, annotations );
   }

   /**
    * @param annotation  -
    * @param tolerance   maximum character distance between annotations
    * @param annotations annotations to test for proximity to test text span
    * @param <T>         extension of EventMention
    * @return the closest preceding T to the test text span or closest following if none preceding
    */
   static public <T extends IdentifiedAnnotation> T getFollowingOrAnnotation( final IdentifiedAnnotation annotation,
                                                                              final int tolerance,
                                                                              final Iterable<T> annotations ) {
      final GappedAnnotation<T> following = getFollowingAnnotation( annotation, annotations );
      final GappedAnnotation<T> preceding = getPrecedingAnnotation( annotation, annotations );
      if ( following.__gap > following.__gap / DEFAULT_FAVORATISM && preceding.__gap < tolerance ) {
         return preceding.__annotation;
      }
      if ( following.__gap < tolerance ) {
         return following.__annotation;
      }
      return null;
   }

   /**
    * @param testStartOffset start offset of the test text span
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span
    */
   static private <T extends IdentifiedAnnotation> GappedAnnotation<T> getPrecedingAnnotation(
         final int testStartOffset,
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
      return new GappedAnnotation<>( closestPreceding, smallestGap );
   }

   /**
    * @param annotation       annotation
    * @param annotations     annotations to test for proximity to test text span
    * @param <T>             extension of EventMention
    * @return the closest preceding T to the test text span
    */
   static private <T extends IdentifiedAnnotation> GappedAnnotation<T> getPrecedingAnnotation(
         final IdentifiedAnnotation annotation,
         final Iterable<T> annotations ) {
      T closestPreceding = null;
      int smallestGap = Integer.MAX_VALUE;
      for ( T testAnnotation : annotations ) {
         final int gap = getTokenGap( annotation, testAnnotation );
         if ( gap > 0 && gap < smallestGap ) {
            closestPreceding = testAnnotation;
            smallestGap = gap;
         }
      }
      return new GappedAnnotation<>( closestPreceding, smallestGap );
   }

   /**
    * @param testEndOffset end offset of the test text span
    * @param annotations   annotations to test for proximity to test text span
    * @param <T>           extension of EventMention
    * @return the closest T to the test text span
    */
   static private <T extends IdentifiedAnnotation> GappedAnnotation<T> getFollowingAnnotation( final int testEndOffset,
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
      return new GappedAnnotation<>( closestFollowing, smallestGap );
   }

   /**
    * @param annotation    annotation
    * @param annotations   annotations to test for proximity to test text span
    * @param <T>           extension of EventMention
    * @return the closest T to the test text span
    */
   static private <T extends IdentifiedAnnotation> GappedAnnotation<T> getFollowingAnnotation(
         final IdentifiedAnnotation annotation,
         final Iterable<T> annotations ) {
      T closestFollowing = null;
      int smallestGap = Integer.MAX_VALUE;
      for ( T testAnnotation : annotations ) {
         final int gap = getTokenGap( annotation, testAnnotation );
         if ( gap > 0 && gap < smallestGap ) {
            closestFollowing = testAnnotation;
            smallestGap = gap;
         }
      }
      return new GappedAnnotation<>( closestFollowing, smallestGap );
   }


   static public int getTokenGap( final IdentifiedAnnotation annotation1, final IdentifiedAnnotation annotation2 ) {
      final Collection<TextSpan> annotations
            = org.apache.uima.fit.util.JCasUtil.selectBetween( IdentifiedAnnotation.class, annotation1, annotation2 )
            .stream()
            .map( a -> new DefaultTextSpan( a.getBegin(), a.getEnd() ) )
            .collect( Collectors.toSet() );
      return annotations.size();
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


   static private class GappedAnnotation<T extends IdentifiedAnnotation> {
      private final T __annotation;
      private final int __gap;

      private GappedAnnotation( final T annotation, final int gap ) {
         __annotation = annotation;
         __gap = gap;
      }
   }

   static private final GappedAnnotation NULL_GAPPED_ANNOTATION = new GappedAnnotation( null, Integer.MAX_VALUE );

}
