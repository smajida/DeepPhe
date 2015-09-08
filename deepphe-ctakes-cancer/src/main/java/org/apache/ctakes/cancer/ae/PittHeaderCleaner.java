package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.typesystem.type.textsem.*;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cleans EventMentions out of Pitt Header area
 */
final public class PittHeaderCleaner extends JCasAnnotator_ImplBase {

   static private final Pattern DIVIDER_PATTERN
         = Pattern.compile( "===================================================================" );

   static private final Collection<Class<? extends IdentifiedAnnotation>> UNWANTED_ANNOTATION_CLASSES
         = Arrays.asList( EventMention.class, EntityMention.class, Modifier.class, FractionAnnotation.class );

   /**
    * Cleans EventMentions out of Pitt Header area
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      final String docText = jcas.getDocumentText();
      if ( docText.length() <= 80 ) {
         return;
      }
      final Matcher matcher = DIVIDER_PATTERN.matcher( docText );
      if ( !matcher.find( 80 ) ) {
         return;
      }
      final int endIndex = matcher.end();
      final Collection<IdentifiedAnnotation> unwantedAnnotations = new HashSet<>();
      for ( Class<? extends IdentifiedAnnotation> unwantedClass : UNWANTED_ANNOTATION_CLASSES ) {
         unwantedAnnotations.addAll( JCasUtil.selectCovered( jcas, unwantedClass, 0, endIndex ) );
      }
      for ( IdentifiedAnnotation unwantedAnnotation : unwantedAnnotations ) {
         unwantedAnnotation.removeFromIndexes();
      }
   }


}
