package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.property.SpannedTest;
import org.apache.ctakes.cancer.property.Test;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.ProcedureMention;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_PROCEDURE;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/28/2015
 */
final public class TnmFinder {

   private TnmFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "TnmFinder" );


   static private final Pattern WHITESPACE_PATTERN = Pattern.compile( "\\s+" );

   static private final String PREFIX_REGEX = "(c|p|y|r|a|u)?";
   static private final String T_REGEX = "T(x|is|a|([I]{1,3}V?)|([0-4][a-z]?))(\\((m|\\d+)?,?(is)?\\))?";
   static private final String N_REGEX = "N(x|([I]{1,3})|([0-3][a-z]?))";
   static private final String M_REGEX = "M(x|I|([0-1][a-z]?))";

   static private final String FULL_T_REGEX = "\\b(" + PREFIX_REGEX + T_REGEX + ")"
                                              + "(" + PREFIX_REGEX + N_REGEX + ")?"
                                              + "(" + PREFIX_REGEX + M_REGEX + ")?\\b";

   static private final String FULL_N_REGEX = "\\b(" + PREFIX_REGEX + T_REGEX + ")?"
                                              + "(" + PREFIX_REGEX + N_REGEX + ")"
                                              + "(" + PREFIX_REGEX + M_REGEX + ")?\\b";

   static private final String FULL_M_REGEX = "\\b"
                                              + "(" + PREFIX_REGEX + T_REGEX + ")?"
                                              + "(" + PREFIX_REGEX + N_REGEX + ")?"
                                              + "(" + PREFIX_REGEX + M_REGEX + ")\\b";

   static private final String FULL_REGEX = "(" + FULL_T_REGEX + ")|(" + FULL_N_REGEX + ")|(" + FULL_M_REGEX + ")";

   static private final Pattern FULL_PATTERN = Pattern.compile( FULL_REGEX, Pattern.CASE_INSENSITIVE );


   static public void addTnms( final JCas jcas, final AnnotationFS lookupWindow,
                               final Iterable<IdentifiedAnnotation> neoplasms ) {
      final Collection<Tnm> tnms = getTnms( lookupWindow.getCoveredText() );
      if ( tnms.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( Tnm tnm : tnms ) {
         final SpannedTnmPrefix tnmPrefix = getTnmPrefix( tnm, lookupWindow.getCoveredText() );
         if ( tnmPrefix != null ) {
            final IdentifiedAnnotation diagnosticTest = createTestProcedure( jcas, windowStartOffset, tnmPrefix );
            TnmResourceFactory.getInstance().createResource( jcas, windowStartOffset, tnm, neoplasms, Collections
                  .singletonList( diagnosticTest ) );
         } else {
            TnmResourceFactory.getInstance().createResource( jcas, windowStartOffset, tnm, neoplasms );
         }
      }
   }

   static List<Tnm> getTnms( final String lookupWindow ) {
      if ( lookupWindow.length() < 2 ) {
         return Collections.emptyList();
      }
      final List<SpannedTnmType> types = new ArrayList<>();
      final List<Tnm> tnms = new ArrayList<>();
      final Matcher fullMatcher = FULL_PATTERN.matcher( lookupWindow );
      while ( fullMatcher.find() ) {
         final String matchWindow = lookupWindow.substring( fullMatcher.start(), fullMatcher.end() );
         if ( matchWindow.trim().isEmpty() ) {
            continue;
         }
         for ( TnmType type : TnmType.values() ) {
            final Matcher typeMatcher = type.getMatcher( matchWindow );
            while ( typeMatcher.find() ) {
               final int typeStart = fullMatcher.start() + typeMatcher.start();
               final int typeEnd = fullMatcher.start() + typeMatcher.end();
               types.add( new SpannedTnmType( type, typeStart, typeEnd ) );
            }
         }
         if ( types.isEmpty() ) {
            continue;
         }
         SpannedTnmType currentType = types.get( 0 );
         for ( int i = 1; i < types.size(); i++ ) {
            SpannedTnmType nextType = types.get( i );
            final SpannedTnmValue spannedValue = getSpannedValue( currentType.getType(),
                  lookupWindow, currentType.getEndOffset(), nextType.getStartOffset() );
            if ( spannedValue != null ) {
               tnms.add( new Tnm( currentType, spannedValue ) );
            }
            currentType = nextType;
         }
         final SpannedTnmValue spannedValue = getSpannedValue( currentType.getType(),
               lookupWindow, currentType.getEndOffset(), fullMatcher.end() );
         if ( spannedValue != null ) {
            tnms.add( new Tnm( currentType, spannedValue ) );
         }
         types.clear();
      }
      Collections.sort( tnms, SpanOffsetComparator.getInstance() );
      return tnms;
   }

   static private SpannedTnmValue getSpannedValue( final TnmType tnmType,
                                                   final String matchWindow,
                                                   final int startOffset,
                                                   final int endOffset ) {
      switch ( tnmType ) {
         case T:
            return getSpannedValue( Tvalue.values(), matchWindow, startOffset, endOffset );
         case N:
            return getSpannedValue( Nvalue.values(), matchWindow, startOffset, endOffset );
         case M:
            return getSpannedValue( Mvalue.values(), matchWindow, startOffset, endOffset );
      }
      return null;
   }

   static private SpannedTnmValue getSpannedValue( final TnmValue[] tnmValues,
                                                   final String matchWindow,
                                                   final int startOffset,
                                                   final int endOffset ) {
      final String valueLookupWindow = matchWindow.substring( startOffset, endOffset );
      if ( valueLookupWindow.isEmpty() ) {
         return null;
      }
      SpannedTnmValue bestValue = null;
      for ( TnmValue value : tnmValues ) {
         final Matcher valueMatcher = value.getMatcher( valueLookupWindow );
         if ( valueMatcher.find() ) {
            if ( bestValue == null
                 || (valueMatcher.end() - valueMatcher.start() > bestValue.getEndOffset() - bestValue.getStartOffset())
                 || (startOffset + valueMatcher.start() < bestValue.getStartOffset()) ) {
               bestValue = new SpannedTnmValue( value,
                     startOffset + valueMatcher.start(),
                     startOffset + valueMatcher.end() );
            }
         }
      }
      return bestValue;
   }

   static SpannedTnmPrefix getTnmPrefix( final Tnm tnm, final String lookupWindow ) {
      if ( tnm.getStartOffset() == 0 || lookupWindow.length() < 3 ||
           lookupWindow.charAt( tnm.getStartOffset() - 1 ) == ' ' ) {
         return null;
      }
      final String matchWindow = lookupWindow.substring( tnm.getStartOffset() - 1, tnm.getStartOffset() );
      for ( TnmPrefix prefix : TnmPrefix.values() ) {
         final Matcher prefixMatcher = prefix.getMatcher( matchWindow );
         if ( prefixMatcher.find() ) {
            return new SpannedTnmPrefix( prefix, tnm.getStartOffset() - 1, tnm.getStartOffset() );
         }
      }
      return null;
   }


   /**
    * Create a modifier and add it to the cas
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the property
    * @param spannedTest       -
    * @return the procedure mention representing the test for a property
    */
   static private ProcedureMention createTestProcedure( final JCas jcas,
                                                        final int windowStartOffset,
                                                        final SpannedTest<? extends Test> spannedTest ) {
      final ProcedureMention procedure = new ProcedureMention( jcas,
            windowStartOffset + spannedTest.getStartOffset(),
            windowStartOffset + spannedTest.getEndOffset() );
      procedure.setTypeID( NE_TYPE_ID_PROCEDURE );
      // Test uri concept
      final Test test = spannedTest.getTest();
      final String cui = test.getCui();
      final String tui = test.getTui();
      final String title = test.getTitle();
      UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( cui == null ? "" : cui );
      umlsConcept.setTui( tui == null ? "" : tui );
      umlsConcept.setPreferredText( title == null ? "" : title );
      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      umlsConcept.setCode( test.getUri() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      procedure.setOntologyConceptArr( ontologyConcepts );
      procedure.addToIndexes();
      return procedure;
   }

}
