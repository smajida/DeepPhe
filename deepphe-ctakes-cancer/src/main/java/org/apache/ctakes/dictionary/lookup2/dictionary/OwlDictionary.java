package org.apache.ctakes.dictionary.lookup2.dictionary;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.dictionary.lookup2.term.RareWordTerm;
import org.apache.ctakes.dictionary.lookup2.util.FastLookupToken;
import org.apache.ctakes.dictionary.lookup2.util.collection.CollectionMap;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;

import java.io.FileNotFoundException;
import java.util.*;

import static org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
public class OwlDictionary implements RareWordDictionary {

   static private final Logger LOGGER = Logger.getLogger( "OntologyDictionary" );

   static private final String OWL_FILE_PATH = "owlPath";

   private RareWordDictionary _delegateDictionary;

   public OwlDictionary( final String name, final UimaContext uimaContext, final Properties properties ) {
      this( name, properties.getProperty( OWL_FILE_PATH ) );
   }


   public OwlDictionary( final String name, final String owlFilePath ) {
      final Collection<CuiTerm> cuiTerms = parseOwlFile( owlFilePath );
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
      final Collection<CuiTerm> cuiTerms = new HashSet<>();
//      Arrays.stream( synonyms ).map( String::toLowerCase ).forEach( s -> cuiTerms.add( new CuiTerm( cui, s ) ) );
      for ( String synonym : synonyms ) {
         cuiTerms.add( new CuiTerm( cui, synonym.toLowerCase() ) );
      }
      return cuiTerms;
   }


   // TODO Can copy from DictionaryTool, then a stream would make sense
//   static private Collection<String> getFormattedTexts( final Concept concept, final UmlsTermUtil umlsTermUtil ) {
//      final String[] synonyms = concept.getSynonyms();
//      if ( synonyms.length == 0 ) {
//         return Collections.emptyList();
//      }
//      final Collection<String> formattedTexts = new ArrayList<>();
//      for ( String synonym : synonyms ) {
//         formattedTexts.addAll( umlsTermUtil.getFormattedTexts( synonym, false, 1, Integer.MAX_VALUE ) );
//      }
//      return formattedTexts;
//   }


   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing a owl file.
    *
    * @param owlFilePath path to file containing ontology owl
    * @return collection of all valid terms read from the bsv file
    */
   static private Collection<CuiTerm> parseOwlFile( final String owlFilePath ) {
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getOntology( owlFilePath );
         final Collection<CuiTerm> cuiTerms = new ArrayList<>();
         final IClass root = ontology.getClass( "Element" );
         for ( IClass childClass : root.getSubClasses() ) {
            cuiTerms.addAll( createCuiTerms( childClass ) );
         }
         return cuiTerms;
      } catch ( IOntologyException | FileNotFoundException ontE ) {
         LOGGER.error( ontE.getMessage() );
      }
      return Collections.emptyList();
   }


}
