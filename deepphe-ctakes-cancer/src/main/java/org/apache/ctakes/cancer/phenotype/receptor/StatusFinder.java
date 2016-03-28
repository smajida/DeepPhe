package org.apache.ctakes.cancer.phenotype.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/29/2015
 */
final public class StatusFinder {

   private StatusFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "StatusFinder" );


   static private final String TYPE_REGEX
         = "Triple|(Estrogen( and Progesterone)?)|(ER( ?(/|and) ?PR)?)|(Progesterone|PR)|(HER-?2( ?/ ?neu)?)";
   static private final String INTERIM_EX = "(-?\\s*?Receptors?\\s*-?)?\\s*(status\\s*)?((is|are)\\s*)?\\s*:?";
   static private final String LONG_VALUE = "(strongly |weakly )?(\\+?pos(itive)?)|(-?neg(ative)?)"
                                            + "|N/?A\\b|unknown|indeterminate|equivocal|(not assessed)";
   static private final String SHORT_VALUE = "\\+(pos)?|-(neg)?";

   // Order is very important
   static private final String FULL_REGEX = "\\b(" + TYPE_REGEX + ")\\s*"
                                            + "((" + INTERIM_EX + "\\s*(" + LONG_VALUE + "))"
                                            + "|(" + SHORT_VALUE + "))";
   static private final Pattern FULL_PATTERN = Pattern.compile( FULL_REGEX, Pattern.CASE_INSENSITIVE );


   static public void addReceptorStatuses( final JCas jcas, final AnnotationFS lookupWindow,
                                           final Iterable<IdentifiedAnnotation> neoplasms,
                                           final Iterable<IdentifiedAnnotation> diagnostics ) {
      final Collection<Status> statuses = getReceptorStatuses( lookupWindow.getCoveredText() );
      if ( statuses.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      final Collection<IdentifiedAnnotation> candidateTests = new ArrayList<>();
      for ( Status status : statuses ) {
         final Collection<String> statusTestUris = status.getSpannedType().getType().getStatusTestUris();
         for ( IdentifiedAnnotation diagnostic : diagnostics ) {
            if ( OwlOntologyConceptUtil.getUris( diagnostic ).stream()
                  .anyMatch( statusTestUris::contains ) ) {
               candidateTests.add( diagnostic );
            }
         }
         StatusPhenotypeFactory.getInstance()
               .createPhenotype( jcas, windowStartOffset, status, neoplasms, candidateTests );
         candidateTests.clear();
      }
   }

   static List<Status> getReceptorStatuses( final String lookupWindow ) {
      if ( lookupWindow.length() < 3 ) {
         return Collections.emptyList();
      }
      final List<Status> statuses = new ArrayList<>();
      final Matcher fullMatcher = FULL_PATTERN.matcher( lookupWindow );
      while ( fullMatcher.find() ) {
         final String matchWindow = lookupWindow.substring( fullMatcher.start(), fullMatcher.end() );
         SpannedStatusType spannedType = null;
         SpannedStatusValue spannedValue = null;
         for ( StatusType type : StatusType.values() ) {
            final Matcher typeMatcher = type.getMatcher( matchWindow );
            while ( typeMatcher.find() ) {
               final int typeStart = fullMatcher.start() + typeMatcher.start();
               final int typeEnd = fullMatcher.start() + typeMatcher.end();
               spannedType = new SpannedStatusType( type, typeStart, typeEnd );
               final String valueLookupWindow = matchWindow.substring( typeMatcher.end() );
               spannedValue = null;
               for ( StatusValue value : StatusValue.values() ) {
                  final Matcher valueMatcher = value.getMatcher( valueLookupWindow );
                  if ( valueMatcher.find() ) {
                     spannedValue = new SpannedStatusValue( value,
                           typeEnd + valueMatcher.start(),
                           typeEnd + valueMatcher.end() );
                     break;
                  }
               }
               if ( spannedValue != null ) {
                  statuses.add( new Status( spannedType, spannedValue ) );
               }
            }
         }
      }
      Collections.sort( statuses, SpanOffsetComparator.getInstance() );
      return statuses;
   }

}