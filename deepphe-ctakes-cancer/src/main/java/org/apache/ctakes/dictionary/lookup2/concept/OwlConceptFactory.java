package org.apache.ctakes.dictionary.lookup2.concept;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.ctakes.core.util.DotLogger;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.dictionary.lookup2.util.CuiCodeUtil;
import org.apache.ctakes.dictionary.lookup2.util.LookupUtil;
import org.apache.ctakes.dictionary.lookup2.util.TuiCodeUtil;
import org.apache.ctakes.dictionary.lookup2.util.collection.CollectionMap;
import org.apache.ctakes.dictionary.lookup2.util.collection.HashSetMap;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;

import java.io.*;
import java.util.*;

import static org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory.MODIFIER_ELEMENT_NAME;
import static org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory.ROOT_ELEMENT_NAME;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
public class OwlConceptFactory implements ConceptFactory {

   static private final Logger LOGGER = Logger.getLogger( "OwlConceptFactory" );

   static private final String OWL_FILE_PATH = "owlPath";

   final private ConceptFactory _delegateFactory;

   public OwlConceptFactory( final String name, final UimaContext uimaContext, final Properties properties ) {
      this( name, properties.getProperty( OWL_FILE_PATH ) );
   }

   public OwlConceptFactory( final String name, final String owlFilePath ) {
      // Get the bsv terms first just in case there are crazy overlaps
      final Map<Long, Concept> conceptMap = parseBsvFiles( owlFilePath );
      conceptMap.putAll( parseOwlFile( owlFilePath ) );
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
         LOGGER.info( "Creating Concepts from Ontology Owl:" );
         try ( DotLogger dotter = new DotLogger() ) {
            final Map<Long, Concept> conceptMap = new HashMap<>();
            final IClass root = ontology.getClass( ROOT_ELEMENT_NAME );
            for ( IClass childClass : root.getSubClasses() ) {
               conceptMap.putAll( createOwlConcepts( childClass ) );
            }
            final IClass root2 = ontology.getClass( MODIFIER_ELEMENT_NAME );
            for ( IClass childClass : root2.getSubClasses() ) {
               conceptMap.putAll( createOwlConcepts( childClass ) );
            }
            return conceptMap;
         } catch ( IOException ioE ) {
            LOGGER.error( "Could not create concepts from Ontology Owl" );
         }
      } catch ( IOntologyException | FileNotFoundException ontE ) {
         LOGGER.error( ontE.getMessage() );
      }
      return Collections.emptyMap();
   }


   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing all of the bsv files in the same directory as the owl file.
    *
    * @param owlFilePath path to file containing ontology owl
    * @return collection of all valid terms read from bsv files
    */
   static private Map<Long, Concept> parseBsvFiles( final String owlFilePath ) {
      File owlDir;
      try {
         final File owlParent = FileLocator.locateFile( owlFilePath );
         owlDir = owlParent.getParentFile();
      } catch ( IOException ioE ) {
         return Collections.emptyMap();
      }
      final FilenameFilter bsvFilter = ( dir, name ) -> name.toLowerCase().endsWith( ".bsv" );
      final File[] bsvFiles = owlDir.listFiles( bsvFilter );
      if ( bsvFiles == null || bsvFiles.length == 0 ) {
         return Collections.emptyMap();
      }
      final Map<Long, Concept> bsvConcepts = new HashMap<>();
      LOGGER.info( "Loading Concept BSV Files in " + owlDir.getPath() + ":" );
      try ( DotLogger dotter = new DotLogger() ) {
         for ( File bsvFile : bsvFiles ) {
            bsvConcepts.putAll( createBsvConcepts( bsvFile.getPath() ) );
         }
      } catch ( IOException ioE ) {
         LOGGER.error( "Could not load Concept BSV Files in " + owlDir.getPath() );
      }
      return bsvConcepts;
   }

   static private Map<Long, Concept> createBsvConcepts( final String bsvFilePath ) {
      final Collection<CuiTuiUriTerm> cuiTuiTerms = parseBsvFile( bsvFilePath );
      final Map<Long, Concept> conceptMap = new HashMap<>( cuiTuiTerms.size() );
      for ( CuiTuiUriTerm cuiTuiTerm : cuiTuiTerms ) {
         final CollectionMap<String, String, ? extends Collection<String>> codes
               = new HashSetMap<>();
         codes.placeValue( Concept.TUI, TuiCodeUtil.getAsTui( cuiTuiTerm.getTui() ) );
         codes.placeValue( OwlConcept.URI_CODING_SCHEME, cuiTuiTerm.getUri() );
         conceptMap.put( CuiCodeUtil.getInstance().getCuiCode( cuiTuiTerm.getCui() ),
               new DefaultConcept( cuiTuiTerm.getCui(), Concept.PREFERRED_TERM_UNKNOWN, codes ) );
      }
      return conceptMap;
   }


   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing a bsv file.  The file can be in one of two columnar formats:
    * <p>
    * CUI|Text|TUI|URI
    * </p>
    *
    * @param bsvFilePath file containing term rows and bsv columns
    * @return collection of all valid terms read from the bsv file
    */
   static private Collection<CuiTuiUriTerm> parseBsvFile( final String bsvFilePath ) {
      final Collection<CuiTuiUriTerm> cuiTuiTerms = new ArrayList<>();
      try ( final BufferedReader reader
                  = new BufferedReader( new InputStreamReader( FileLocator.getAsStream( bsvFilePath ) ) ) ) {
         String line = reader.readLine();
         while ( line != null ) {
            if ( line.startsWith( "//" ) || line.startsWith( "#" ) ) {
               line = reader.readLine();
               continue;
            }
            final String[] columns = LookupUtil.fastSplit( line, '|' );
            final CuiTuiUriTerm cuiTuiTerm = createCuiTuiUriTerm( columns );
            if ( cuiTuiTerm != null ) {
               cuiTuiTerms.add( cuiTuiTerm );
            } else {
               LOGGER.warn( "Bad BSV line " + line + " in " + bsvFilePath );
            }
            line = reader.readLine();
         }
      } catch ( IOException ioE ) {
         LOGGER.error( ioE.getMessage() );
      }
      return cuiTuiTerms;
   }

   /**
    * @param columns two or three columns representing CUI,Text or CUI,TUI,Text respectively
    * @return a term created from the columns or null if the columns are malformed
    */
   static private CuiTuiUriTerm createCuiTuiUriTerm( final String... columns ) {
      if ( columns.length < 4 ) {
         return null;
      }
      final String cui = columns[ 0 ];
      final String term = columns[ 1 ].trim().toLowerCase();
      // default for an empty tui column is tui 0 = unknown
      final String tui = columns[ 2 ].trim().isEmpty() ? "T000" : columns[ 2 ].trim();
      final String uri = columns[ 3 ].trim();
      return new CuiTuiUriTerm( cui, tui, uri );
   }

   static public class CuiTuiUriTerm {

      final private String __cui;
      final private String __tui;
      final private String __uri;
      final private int __hashcode;

      public CuiTuiUriTerm( final String cui, final String tui, final String uri ) {
         __cui = cui;
         __tui = tui;
         __uri = uri;
         __hashcode = (__cui + "_" + __tui + "_" + __uri).hashCode();
      }

      public String getCui() {
         return __cui;
      }

      public String getTui() {
         return __tui;
      }

      public String getUri() {
         return __uri;
      }

      public boolean equals( final Object value ) {
         return value instanceof CuiTuiUriTerm
                && __uri.equals( ((CuiTuiUriTerm)value).__uri )
                && __tui.equals( ((CuiTuiUriTerm)value).__tui )
                && __cui.equals( ((CuiTuiUriTerm)value).__cui );
      }

      public int hashCode() {
         return __hashcode;
      }
   }

}
