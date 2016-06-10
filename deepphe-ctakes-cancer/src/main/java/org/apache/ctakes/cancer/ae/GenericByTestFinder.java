package org.apache.ctakes.cancer.ae;


import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/10/2016
 */
public class GenericByTestFinder extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "GenericByTestFinder" );

   static private final Pattern TEST_FOR_PATTERN
         = Pattern.compile( "(((evaluat(ion\\s+of)?)|test)(ed\\s+for)?)|(may indicate)" );

   /**
    * Removes Metastasis to breast locations
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      final Collection<DiseaseDisorderMention> diseases = JCasUtil.select( jcas, DiseaseDisorderMention.class );
      for ( IdentifiedAnnotation disease : diseases ) {
         final int begin = Math.max( 0, disease.getBegin() - 25 );
         final String preceding = jcas.getDocumentText().substring( begin, disease.getBegin() );
         if ( TEST_FOR_PATTERN.matcher( preceding ).find() ) {
            disease.setGeneric( true );
         }
      }
      LOGGER.info( "Finished Processing" );
   }


}
