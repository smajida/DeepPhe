package org.apache.ctakes.dictionary.lookup2.ontology;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import org.apache.log4j.Logger;

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

   static final private Logger LOGGER = Logger.getLogger( "OwlConnectionFactory" );
   static final private Logger DOT_LOGGER = Logger.getLogger( "ProgressAppender" );
   static final private Logger EOL_LOGGER = Logger.getLogger( "ProgressDone" );


   private final Map<String, IOntology> ONTOLOGIES = Collections.synchronizedMap( new HashMap<>() );

   private OwlConnectionFactory() {
   }

   public Collection<String> listOntologyPaths() {
      return Collections.unmodifiableSet( ONTOLOGIES.keySet() );
   }

   public IOntology getOntology( final String owlPath ) throws IOntologyException {
      IOntology ontology = ONTOLOGIES.get( owlPath );
      if ( ontology != null ) {
         return ontology;
      }
      LOGGER.info( "Loading Ontology at " + owlPath + ":" );
      final Timer timer = new Timer();
      timer.scheduleAtFixedRate( new DotPlotter(), 333, 333 );
      try {
         ontology = OOntology.loadOntology( owlPath );
      } catch ( IOntologyException ontE ) {
         timer.cancel();
         EOL_LOGGER.error( "" );
         LOGGER.error( "  Could not load Ontology at " + owlPath );
         throw ontE;
      }
      timer.cancel();
      EOL_LOGGER.info( "" );
      LOGGER.info( " Ontology loaded" );
      ONTOLOGIES.put( owlPath, ontology );
      return ontology;
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
