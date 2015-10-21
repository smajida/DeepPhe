package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.ctakes.typesystem.type.textspan.Segment;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;

/**
 * Grabs the document time from the header
 */
final public class PittHeaderAnnotator extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "PittHeaderAnnotator" );

   static private final Pattern DATE_PATTERN = Pattern.compile( ".*Principal Date\\D+(\\d+) (\\d+).*", DOTALL );
   static private final Pattern DIVIDER_PATTERN = Pattern.compile( "^[\\_\\-=]{4,}$", MULTILINE );
   static private final Pattern DE_ID_PATTERN = Pattern.compile( "^\\[Report de\\-identified [^\\]]+\\]$", MULTILINE );


   /**
    * Grabs the document time from the header
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      // TODO -- use a document creation time type?
      final String docText = jcas.getDocumentText();
      final Matcher dateMatcher = DATE_PATTERN.matcher( docText );
      if ( dateMatcher.matches() ) {
         final TimeMention docTime = new TimeMention( jcas );
         docTime.setBegin( dateMatcher.start( 1 ) );
         docTime.setEnd( dateMatcher.end( 2 ) );
         docTime.setId( 0 );
         docTime.addToIndexes();
      }

      // Get all of the section dividers:  "============================"
      final Matcher dividerMatcher = DIVIDER_PATTERN.matcher( docText );
      final List<Integer> dividerBegins = new ArrayList<>();
      final List<Integer> dividerEnds = new ArrayList<>();
      int dividerIdNum = 1;
      while ( dividerMatcher.find() ) {
         dividerBegins.add( dividerMatcher.start() );
         dividerEnds.add( dividerMatcher.end() );
         final Segment dividerSegment = new Segment( jcas, dividerMatcher.start(), dividerMatcher.end() - 1 );
         dividerSegment.setId( "DIVIDER_" + dividerIdNum );
         dividerSegment.addToIndexes();
         dividerIdNum++;
      }
      // Get the de-identification divider
      final Matcher deIdMatcher = DE_ID_PATTERN.matcher( docText );
      if ( deIdMatcher.find() ) {
         final Segment deIdSegment = new Segment( jcas, deIdMatcher.start(), deIdMatcher.end() - 1 );
         deIdSegment.setId( "DE_ID_SEGMENT" );
         deIdSegment.addToIndexes();
         dividerBegins.add( deIdMatcher.start() );
         dividerEnds.add( deIdMatcher.end() );
      }
      if ( dividerBegins.isEmpty() ) {
         // whole text is simple segment
         final Segment docSegment = new Segment( jcas, 0, docText.length() - 1 );
         docSegment.setId( "SIMPLE_SEGMENT" );
         docSegment.addToIndexes();
         return;
      }
      Collections.sort( dividerBegins );
      Collections.sort( dividerEnds );
      int sectionIdNum = 1;
      if ( dividerBegins.get( 0 ) != 0 ) {
         // Add first section
         final Segment dividerSegment = new Segment( jcas, 0, dividerBegins.get( 0 ) - 1 );
         dividerSegment.setId( "SIMPLE_SEGMENT_" + sectionIdNum );
         dividerSegment.addToIndexes();
         sectionIdNum++;
      }
      for ( int i = 0; i < dividerEnds.size() - 1; i++ ) {
         if ( dividerBegins.contains( dividerEnds.get( i ) ) ) {
            // is a divider
            continue;
         }
         final Segment dividerSegment = new Segment( jcas, dividerEnds.get( i ), dividerBegins.get( i + 1 ) - 1 );
         dividerSegment.setId( "SIMPLE_SEGMENT_" + sectionIdNum );
         dividerSegment.addToIndexes();
         sectionIdNum++;
      }
      if ( dividerEnds.get( dividerEnds.size() - 1 ) != docText.length() - 1 ) {
         // Add last section
         final Segment dividerSegment = new Segment( jcas, dividerEnds.get( dividerEnds.size() - 1 ),
               docText.length() - 1 );
         dividerSegment.setId( "SIMPLE_SEGMENT_" + sectionIdNum );
         dividerSegment.addToIndexes();
      }

//      int headerEnd = docText.indexOf("\n", docText.indexOf("[Report de-identified"));
//      Segment mainSegment = new Segment(jcas, headerEnd+1, docText.length()-1);
//      mainSegment.setId("SIMPLE_SEGMENT");
//      mainSegment.addToIndexes();
      LOGGER.info( "Finished Processing" );
   }


}
