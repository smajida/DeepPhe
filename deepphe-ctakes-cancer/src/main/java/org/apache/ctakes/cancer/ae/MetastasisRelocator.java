package org.apache.ctakes.cancer.ae;


import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Removes Metastasis to breast locations
 * In the future this will need to be modified so that other cancer types can have such exclusions,
 * but running on patients with more than one primary type will be hazardous
 * and running full-EMR will probably never be possible without a
 * "cancer primary type recognizer" that can handle the full document and be run before this one.
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/19/2016
 */
public class MetastasisRelocator extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "MetastasisRelocator" );

   /**
    * Removes Metastasis to breast locations
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      // breasts
      final Collection<IdentifiedAnnotation> breasts = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas,
            OwlConstants.BREAST_CANCER_OWL + "#Breast" );
      if ( breasts.isEmpty() ) {
         LOGGER.info( "Finished Processing" );
         return;
      }
      // Metastases
      final Collection<IdentifiedAnnotation> metastases
            = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, OwlConstants.BREAST_CANCER_OWL
                                                                      + "#Metastatic_Neoplasm" );
      if ( metastases.isEmpty() ) {
         LOGGER.info( "Finished Processing" );
         return;
      }
      // location relations
      final Collection<LocationOfTextRelation> locations = JCasUtil.select( jcas, LocationOfTextRelation.class );
      if ( locations == null || locations.isEmpty() ) {
         LOGGER.info( "Finished Processing" );
         return;
      }
      final Collection<LocationOfTextRelation> unwantedRelations = new ArrayList<>();
      for ( LocationOfTextRelation relation : locations ) {
         // Arg1 is locatable (metastasis?), arg2 is location (breast?)
         final Annotation locatable = relation.getArg1().getArgument();
         if ( !(locatable instanceof IdentifiedAnnotation) || !metastases.contains( locatable ) ) {
            continue;
         }
         final Annotation location = relation.getArg2().getArgument();
         if ( !(location instanceof IdentifiedAnnotation) || !breasts.contains( location ) ) {
            continue;
         }
         unwantedRelations.add( relation );
      }
      unwantedRelations.forEach( TOP::removeFromIndexes );
      LOGGER.info( "Finished Processing" );
   }


}
