package org.healthnlp.deepphe.uima.ae;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.apache.ctakes.core.util.IdentifiedAnnotationUtil;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/27/2015
 */
public class IClassExtractor extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "IClassExtractor" );


   // minimum span required to accept a term
   @ConfigurationParameter( name = DocumentSummarizerAE.PARAM_ONTOLOGY_PATH, mandatory = true,
         description = "Path to the ontology file" )
   private String _ontologyPath;

   private IOntology _ontology;

   @Override
   public void initialize( final UimaContext uimaContext ) throws ResourceInitializationException {
	   super.initialize(uimaContext);
	   try {
		   _ontology = OwlConnectionFactory.getInstance().getOntology( _ontologyPath );
	   } catch ( IOntologyException | FileNotFoundException ontE ) {
		   LOGGER.error( ontE.getMessage() );
		   throw new ResourceInitializationException( ontE.getCause() );
	   }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting processing" );
      final Collection<IdentifiedAnnotation> annotations = JCasUtil.select( jcas, IdentifiedAnnotation.class );
      for ( IdentifiedAnnotation annotation : annotations ) {
         IdentifiedAnnotationUtil.getUmlsConcepts( annotation ).stream()
               .filter( concept -> OwlConcept.URI.equals( concept.getCodingScheme() ) )
               .map( OntologyConcept::getCode )
               .forEach( LOGGER::info );
//                  .forEach( _ontology::getClass );
      }

      LOGGER.info( "Finished processing" );
   }


}
