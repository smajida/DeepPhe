package org.apache.ctakes.cancer.ae;


import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/10/2016
 */
public class ListEntryNegator extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "ListEntryNegator" );

   static private final Pattern NEGATIVE_PATTERN
         = Pattern.compile( "(\\s?:\\s?)(NEGATIVE|(NO\\.?\\b)|NONE|(NOT (SEEN|PRESENT|INDICATED|FOUND|DISCOVERED)?))",
         Pattern.CASE_INSENSITIVE );

   /**
    * Removes Metastasis to breast locations
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      final Collection<DiseaseDisorderMention> diseases = JCasUtil.select( jcas, DiseaseDisorderMention.class );
      for ( IdentifiedAnnotation disease : diseases ) {
         final String docText = jcas.getDocumentText();
         final int diseaseEnd = disease.getEnd();
         final int end = Math.min( docText.length(), diseaseEnd + 25 );
         final String following = docText.substring( diseaseEnd, end );
         final Matcher matcher = NEGATIVE_PATTERN.matcher( following );
         if ( matcher.find() && matcher.start() < 2 ) {
            disease.setPolarity( -1 );
         }
      }
      LOGGER.info( "Finished Processing" );
   }

}
