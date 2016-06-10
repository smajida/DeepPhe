package org.apache.ctakes.cancer.ae;


import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/10/2016
 */
public class GenericByTestFinder extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "GenericByTestFinder" );

   static private final Collection<String> TEST_FOR_TEXTS
         = Arrays.asList( "evaluation for", "evaluation of", "tested for", "test for", "test of", "may indicate" );

   /**
    * Removes Metastasis to breast locations
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      final Collection<String> neoplasmUris = NeoplasmRelationFactory.getNeoplasmUris();
      for ( String neoplasmUri : neoplasmUris ) {
         final Collection<IdentifiedAnnotation> neoplasms = OwlOntologyConceptUtil
               .getAnnotationsByUri( jcas, neoplasmUri );
         for ( IdentifiedAnnotation neoplasm : neoplasms ) {
            final int begin = Math.max( 0, neoplasm.getBegin() - 15 );
            final String preceding = jcas.getDocumentText().substring( begin, neoplasm.getBegin() );
            if ( TEST_FOR_TEXTS.stream().anyMatch( preceding::contains ) ) {
               neoplasm.setGeneric( true );
            }
         }
      }
      LOGGER.info( "Finished Processing" );
   }


}
