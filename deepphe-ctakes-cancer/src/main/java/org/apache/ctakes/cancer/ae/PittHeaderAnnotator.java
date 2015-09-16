package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.ctakes.typesystem.type.textspan.Segment;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;

/**
* Grabs the document time from the header
*/
final public class PittHeaderAnnotator extends JCasAnnotator_ImplBase {

   static private final Pattern DATE_PATTERN = Pattern.compile( ".*Principal Date\\D+(\\d+) (\\d+).*", DOTALL );

   /**
    * Grabs the document time from the header
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      // TODO -- use a document creation time type?
      final String docText = jcas.getDocumentText();
      final Matcher m = DATE_PATTERN.matcher( docText );
      if ( m.matches() ) {
         final TimeMention docTime = new TimeMention( jcas );
         docTime.setBegin( m.start( 1 ) );
         docTime.setEnd( m.end( 2 ) );
         docTime.setId( 0 );
         docTime.addToIndexes();
      }

      int headerEnd = docText.indexOf("\n", docText.indexOf("[Report de-identified"));
      
      Segment mainSegment = new Segment(jcas, headerEnd+1, docText.length()-1);
      mainSegment.setId("SIMPLE_SEGMENT");
      mainSegment.addToIndexes();
   }
}
