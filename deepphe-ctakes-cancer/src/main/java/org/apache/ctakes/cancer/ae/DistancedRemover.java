package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.core.util.RelationUtil;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import java.util.Collection;
import java.util.Collections;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/10/2016
 */
public class DistancedRemover extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "DistancedRemover" );

   /**
    * Removes Annotation to locations if they are actually expressions of distance
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
            final Collection<LocationOfTextRelation> relations = JCasUtil.select( jcas, LocationOfTextRelation.class );
            if ( relations == null || relations.isEmpty() ) {
               continue;
            }
            for ( LocationOfTextRelation relation : relations ) {
               final Collection<IdentifiedAnnotation> locations = RelationUtil
                     .getSecondArguments( Collections.singletonList( relation ), neoplasm );
               for ( IdentifiedAnnotation location : locations ) {
                  if ( location.getBegin() > neoplasm.getEnd() ) {
                     final String between = jcas.getDocumentText().substring( neoplasm.getEnd(), location.getBegin() );
                     if ( between.contains( " from " ) ) {
                        relation.removeFromIndexes( jcas );
                     }
                  }
               }
            }
         }
      }
      LOGGER.info( "Finished Processing" );
   }

}
