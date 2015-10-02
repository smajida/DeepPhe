package org.apache.ctakes.dictionary.lookup2.concept;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.dictionary.lookup2.util.CuiCodeUtil;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;

import java.util.*;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
public class OwlConceptFactory implements ConceptFactory {

   static private final Logger LOGGER = Logger.getLogger( "OntologyConceptFactory" );

   static private final String OWL_FILE_PATH = "owlPath";

   final private ConceptFactory _delegateFactory;

   public OwlConceptFactory( final String name, final UimaContext uimaContext, final Properties properties ) {
      this( name, properties.getProperty( OWL_FILE_PATH ) );
   }

   public OwlConceptFactory( final String name, final String owlFilePath ) {
      final Map<Long, Concept> conceptMap = parseOwlFile( owlFilePath );
      _delegateFactory = new MemConceptFactory( name, conceptMap );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getName() {
      return _delegateFactory.getName();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Concept createConcept( final Long cuiCode ) {
      return _delegateFactory.createConcept( cuiCode );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Map<Long, Concept> createConcepts( final Collection<Long> cuiCodes ) {
      return _delegateFactory.createConcepts( cuiCodes );
   }


   static private Map<Long, Concept> createOwlConcepts( final IClass iClass ) {
      final String cui = OwlParserUtil.getCui( iClass );
      if ( cui == null ) {
         return Collections.emptyMap();
      }
      final Long cuiCode = CuiCodeUtil.getInstance().getCuiCode( cui );
      final Concept ontologyConcept = new OwlConcept( iClass );
      return Collections.singletonMap( cuiCode, ontologyConcept );
   }


   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing a owl file.
    *
    * @param owlFilePath path to file containing ontology owl
    * @return collection of all valid terms read from the bsv file
    */
   static private Map<Long, Concept> parseOwlFile( final String owlFilePath ) {
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getOntology( owlFilePath );
         final Map<Long, Concept> conceptMap = new HashMap<>();
         final IClass root = ontology.getClass( "Element" );
         for ( IClass childClass : root.getSubClasses() ) {
            conceptMap.putAll( createOwlConcepts( childClass ) );
         }
         return conceptMap;
      } catch ( IOntologyException ontE ) {
         LOGGER.error( ontE.getMessage() );
      }
      return Collections.emptyMap();
   }


}
