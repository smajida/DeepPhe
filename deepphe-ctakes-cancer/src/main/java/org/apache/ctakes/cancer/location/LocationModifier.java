package org.apache.ctakes.cancer.location;


import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.dictionary.lookup2.concept.Concept;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/16/2016
 */
public class LocationModifier {

   static private final Logger LOGGER = Logger.getLogger( "LocationModifier" );


   public enum Quadrant {
      UPPER_INNER( 222596l, "Upper_Inner_Quadrant_of_the_Breast", "upper-inner", "upper inner" ),
      LOWER_INNER( 222597l, "Lower_Inner_Quadrant_of_the_Breast", "lower-inner", "lower inner" ),
      UPPER_OUTER( 222598l, "Upper_Outer_Quadrant_of_the_Breast", "upper-outer", "upper outer" ),
      LOWER_OUTER( 222599l, "Lower_Outer_Quadrant_of_the_Breast", "lower-outer", "lower outer" );
      private final long __cui;
      private final String __uri;
      private final String[] __synonyms;

      private Quadrant( final long cui, final String uri, final String... synonyms ) {
         __cui = cui;
         __uri = uri;
         __synonyms = synonyms;
      }

      private long getCui() {
         return __cui;
      }

      // tui T082 is "Spatial Concept" and might be better than T033 "Finding"
      private String getTui() {
         return "T082";
      }

      public String getUri() {
         return OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + __uri;
      }

      private String[] getSynonyms() {
         return __synonyms;
      }
   }

   private enum Laterality {

   }


   /**
    * Create a collection of {@link org.apache.ctakes.dictionary.lookup2.dictionary.RareWordTermMapCreator.CuiTerm} Objects
    * by parsing all of the bsv files in the same directory as the owl file.
    *
    * @return collection of all valid terms read from bsv files
    */
   static public Map<Long, Concept> addLocationConcepts() {
//      File owlDir;
//      try {
//         final File owlParent = FileLocator.locateFile( owlFilePath );
//         owlDir = owlParent.getParentFile();
//      } catch ( IOException ioE ) {
//         return Collections.emptyMap();
//      }
//      final FilenameFilter bsvFilter = ( dir, name ) -> name.toLowerCase().endsWith( ".bsv" );
//      final File[] bsvFiles = owlDir.listFiles( bsvFilter );
//      if ( bsvFiles == null || bsvFiles.length == 0 ) {
      return Collections.emptyMap();
//      }
//      final Map<Long, Concept> bsvConcepts = new HashMap<>();
//      LOGGER.info( "Loading Concept BSV Files in " + owlDir.getPath() + ":" );
//      try ( DotLogger dotter = new DotLogger() ) {
//         for ( File bsvFile : bsvFiles ) {
//            bsvConcepts.putAll( parseConceptFile( bsvFile.getPath() ) );
//         }
//      } catch ( IOException ioE ) {
//         LOGGER.error( "Could not load Concept BSV Files in " + owlDir.getPath() );
//      }
//      return bsvConcepts;
   }

}
