package org.apache.ctakes.dictionary.lookup2.dictionary;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.ctakes.core.util.DotLogger;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.dictionary.lookup2.term.RareWordTerm;
import org.apache.ctakes.dictionary.lookup2.util.FastLookupToken;
import org.apache.ctakes.dictionary.lookup2.util.LookupUtil;
import org.apache.ctakes.dictionary.lookup2.util.collection.CollectionMap;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm;
import static org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory.MODIFIER_ELEMENT_NAME;
import static org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory.ROOT_ELEMENT_NAME;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
public class OwlDictionary implements RareWordDictionary {

   static private final Logger LOGGER = Logger.getLogger( "OwlDictionary" );

   static private final String OWL_FILE_PATH = "owlPath";

   private RareWordDictionary _delegateDictionary;

   public OwlDictionary( final String name, final UimaContext uimaContext, final Properties properties ) {
      this( name, properties.getProperty( OWL_FILE_PATH ) );
   }


   public OwlDictionary( final String name, final String owlFilePath ) {
      try {
         OwlConnectionFactory.getInstance().getOntology( owlFilePath );
      } catch ( IOntologyException | FileNotFoundException ontE ) {
         LOGGER.error( ontE.getMessage() );
      }
      // Get the bsv terms first just in case there are crazy overlaps
      final Collection<CuiTerm> cuiTerms = parseBsvFiles( owlFilePath );
      cuiTerms.addAll( parseOwlFile( owlFilePath ) );
      final CollectionMap<String, RareWordTerm, ? extends Collection<RareWordTerm>> rareWordTermMap
            = RareWordTermMapCreator.createRareWordTermMap( cuiTerms );
      _delegateDictionary = new MemRareWordDictionary( name, rareWordTermMap );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getName() {
      return _delegateDictionary.getName();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Collection<RareWordTerm> getRareWordHits( final FastLookupToken fastLookupToken ) {
      return _delegateDictionary.getRareWordHits( fastLookupToken );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Collection<RareWordTerm> getRareWordHits( final String rareWordText ) {
      return _delegateDictionary.getRareWordHits( rareWordText );
   }


   static private Collection<CuiTerm> createCuiTerms( final IClass iClass ) {
      final Concept concept = iClass.getConcept();
      final String cui = OwlParserUtil.getCui( concept );
      if ( cui == null ) {
         return Collections.emptyList();
      }
      final String tui = OwlParserUtil.getTui( concept );
      if ( tui == null ) {
         return Collections.emptyList();
      }
      final String[] synonyms = concept.getSynonyms();
      if ( synonyms == null || synonyms.length == 0 ) {
         return Collections.emptyList();
      }
      if ( OwlParserUtil.isPhenotypeUri( OwlParserUtil.getUriString( iClass ) ) ) {
         return Collections.emptyList();
      }
      return Arrays.stream( synonyms )
            .map( synonym -> new CuiTerm( cui, synonym.toLowerCase() ) )
            .collect( Collectors.toSet() );
   }


   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing a owl file.
    *
    * @param owlFilePath path to file containing ontology owl
    * @return collection of all valid terms read from the owl file
    */
   static private Collection<CuiTerm> parseOwlFile( final String owlFilePath ) {
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getOntology( owlFilePath );
         final Collection<CuiTerm> cuiTerms = new ArrayList<>();
         final IClass root = ontology.getClass( ROOT_ELEMENT_NAME );
         for ( IClass childClass : root.getSubClasses() ) {
            cuiTerms.addAll( createCuiTerms( childClass ) );
         }
         final IClass root2 = ontology.getClass( MODIFIER_ELEMENT_NAME );
         for ( IClass childClass : root2.getSubClasses() ) {
            cuiTerms.addAll( createCuiTerms( childClass ) );
         }
         return cuiTerms;
      } catch ( IOntologyException | FileNotFoundException ontE ) {
         LOGGER.error( ontE.getMessage() );
      }
      return Collections.emptyList();
   }

   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing all of the bsv files in the same directory as the owl file.
    *
    * @param owlFilePath path to file containing ontology owl
    * @return collection of all valid terms read from bsv files
    */
   static private Collection<CuiTerm> parseBsvFiles( final String owlFilePath ) {
      File owlDir;
      try {
         final File owlParent = FileLocator.locateFile( owlFilePath );
         owlDir = owlParent.getParentFile();
      } catch ( IOException ioE ) {
         return Collections.emptyList();
      }
      final FilenameFilter bsvFilter = ( dir, name ) -> name.toLowerCase().endsWith( ".bsv" );
      final File[] bsvFiles = owlDir.listFiles( bsvFilter );
      if ( bsvFiles == null || bsvFiles.length == 0 ) {
         return Collections.emptyList();
      }
      LOGGER.info( "Loading Dictionary BSV Files in " + owlDir.getPath() + ":" );
      final Collection<CuiTerm> cuiTerms = new HashSet<>();
      try ( DotLogger dotter = new DotLogger() ) {
         for ( File bsvFile : bsvFiles ) {
            cuiTerms.addAll( parseBsvFile( bsvFile.getPath() ) );
         }
      } catch ( IOException ioE ) {
         LOGGER.error( "Could not load Dictionary BSV Files in " + owlDir.getPath() );
      }
      LOGGER.info( "Dictionary BSV Files loaded" );
      return cuiTerms;
   }

   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing a bsv file.  The file should be in the following format:
    * <p>
    * CUI|Text|TUI|URI
    * </p>
    *
    * @param bsvFilePath path to file containing term rows and bsv columns
    * @return collection of all valid terms read from the bsv file
    */
   static private Collection<CuiTerm> parseBsvFile( final String bsvFilePath ) {
      final Collection<CuiTerm> cuiTerms = new ArrayList<>();
      try ( final BufferedReader reader = new BufferedReader( new InputStreamReader( FileLocator
            .getAsStream( bsvFilePath ) ) ) ) {
         String line = reader.readLine();
         while ( line != null ) {
            if ( line.startsWith( "//" ) || line.startsWith( "#" ) ) {
               line = reader.readLine();
               continue;
            }
            final String[] columns = LookupUtil.fastSplit( line, '|' );
            final CuiTerm cuiTerm = createCuiTuiTerm( columns );
            if ( cuiTerm != null ) {
               cuiTerms.add( cuiTerm );
            } else {
               LOGGER.debug( "Bad BSV line " + line + " in " + bsvFilePath );
            }
            line = reader.readLine();
         }
      } catch ( IOException ioE ) {
         LOGGER.error( ioE.getMessage() );
      }
      return cuiTerms;
   }

   /**
    * @param columns four columns representing CUI,Text,TUI,URI respectively
    * @return a term created from the columns or null if the columns are malformed
    */
   static private CuiTerm createCuiTuiTerm( final String... columns ) {
      if ( columns.length < 4 ) {
         return null;
      }
      final String uri = columns[ 3 ].trim();
      if ( OwlParserUtil.isPhenotypeUri( uri ) ) {
         return null;
      }
      final String cui = columns[ 0 ];
      final String term = columns[ 1 ].trim().toLowerCase();
      return new CuiTerm( cui, term );
   }


}
