package org.apache.ctakes.dictionary.lookup2.ontology;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
public enum OwlConnectionFactory {
   INSTANCE;

   public static OwlConnectionFactory getInstance() {
      return INSTANCE;
   }

   static public final String ROOT_ELEMENT_NAME = "Annotation";

   static private final Logger LOGGER = Logger.getLogger( "OwlConnectionFactory" );
   static private final Logger DOT_LOGGER = Logger.getLogger( "ProgressAppender" );
   static private final Logger EOL_LOGGER = Logger.getLogger( "ProgressDone" );


   private final Map<String, IOntology> ONTOLOGIES = Collections.synchronizedMap( new HashMap<>() );
   private String _defaultOntologyPath;

   synchronized public Collection<String> listOntologyPaths() {
      return Collections.unmodifiableSet( ONTOLOGIES.keySet() );
   }

   synchronized public IOntology getOntology( final String owlPath ) throws IOntologyException, FileNotFoundException {
      // FileLocator can throw declared exception fnfE - no need for try catch (descriptive FileLocator fnfE message)
      final String fullOwlPath = FileLocator.getFullPath( owlPath );
      IOntology ontology = ONTOLOGIES.get( fullOwlPath );
      if ( ontology != null ) {
         return ontology;
      }
      LOGGER.info( "Loading Ontology at " + fullOwlPath + ":" );
      final Timer timer = new Timer();
      timer.scheduleAtFixedRate( new DotPlotter(), 333, 333 );
      try {
         ontology = OOntology.loadOntology( fullOwlPath );
      } catch ( IOntologyException ontE ) {
         timer.cancel();
         EOL_LOGGER.error( "" );
         LOGGER.error( "Could not load Ontology at " + fullOwlPath );
         throw ontE;
      }
      timer.cancel();
      EOL_LOGGER.info( "" );
      LOGGER.info( "Ontology loaded" );
      ONTOLOGIES.put( fullOwlPath, ontology );
      if ( ONTOLOGIES.size() == 1 ) {
         _defaultOntologyPath = fullOwlPath;
      }
      return ontology;
   }

   synchronized public IOntology getDefaultOntology() throws IOntologyException, FileNotFoundException {
      if ( _defaultOntologyPath == null ) {
         throw new IOntologyException( "No Default Ontology" );
      }
      return getOntology( _defaultOntologyPath );
   }


   static private class DotPlotter extends TimerTask {
      private int _count = 0;

      @Override
      public void run() {
         DOT_LOGGER.info( "." );
         _count++;
         if ( _count % 50 == 0 ) {
            EOL_LOGGER.info( " " + _count );
         }
      }
   }

}
