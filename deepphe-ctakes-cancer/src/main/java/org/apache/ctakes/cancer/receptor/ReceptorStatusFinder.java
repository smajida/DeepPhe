package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
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
final public class ReceptorStatusFinder {

   private ReceptorStatusFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "ReceptorStatusFinder" );


   static private final String TYPE_REGEX
         = "(Estrogen( and Progesterone)?)|(ER( ?(/|and) ?PR)?)|(Progesterone|PR)|(HER-?2( ?/ ?neu)?)";
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
                                           final Iterable<IdentifiedAnnotation> neoplasms ) {
      final Collection<ReceptorStatus> receptorStatuses = getReceptorStatuses( lookupWindow.getCoveredText() );
      if ( receptorStatuses.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( ReceptorStatus receptorStatus : receptorStatuses ) {
         final SignSymptomMention typeMention
               = ReceptorStatusUtil.createFullReceptorStatusMention( jcas, windowStartOffset, receptorStatus );
         final IdentifiedAnnotation closestNeoplasm
               = FinderUtil.getClosestAnnotation( windowStartOffset + receptorStatus.getStartOffset(),
               windowStartOffset + receptorStatus.getEndOffset(), neoplasms );
         addNeoplasmRelationToCas( jcas, typeMention, closestNeoplasm );
      }
   }

   static List<ReceptorStatus> getReceptorStatuses( final String lookupWindow ) {
      if ( lookupWindow.length() < 3 ) {
         return Collections.emptyList();
      }
      System.out.println( lookupWindow );
      final List<ReceptorStatus> receptorStatuses = new ArrayList<>();
      final Matcher fullMatcher = FULL_PATTERN.matcher( lookupWindow );
      while ( fullMatcher.find() ) {
         final String matchWindow = lookupWindow.substring( fullMatcher.start(), fullMatcher.end() );
         System.out.println( matchWindow );
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
               System.out.println( lookupWindow.substring( spannedType.getStartOffset(), spannedType.getEndOffset() ) );
               for ( StatusValue value : StatusValue.values() ) {
                  final Matcher valueMatcher = value.getMatcher( valueLookupWindow );
                  if ( valueMatcher.find() ) {
                     spannedValue = new SpannedStatusValue( value,
                           typeEnd + valueMatcher.start(),
                           typeEnd + valueMatcher.end() );
                     System.out.println( " ... " + lookupWindow
                           .substring( spannedValue.getStartOffset(), spannedValue.getEndOffset() ) );
                     break;
                  }
               }
               if ( spannedValue != null ) {
                  receptorStatuses.add( new ReceptorStatus( spannedType, spannedValue ) );
                  System.out.println( lookupWindow.substring( spannedType.getStartOffset(), spannedType.getEndOffset() )
                                      + " ... " + lookupWindow
                                            .substring( spannedValue.getStartOffset(), spannedValue.getEndOffset() ) );
               }
            }
         }
      }
      Collections.sort( receptorStatuses, SpanOffsetComparator.getInstance() );
      return receptorStatuses;
   }

   /**
    * Create a UIMA relation type based on arguments and the relation label. This
    * allows subclasses to create/define their own types: e.g. coreference can
    * create CoreferenceRelation instead of BinaryTextRelation
    *
    * @param jCas         - JCas object, needed to create new UIMA types
    * @param typeMention  - First argument to relation
    * @param neoplasm     - Second argument to relation
    */
   static private void addNeoplasmRelationToCas( final JCas jCas,
                                                 final SignSymptomMention typeMention,
                                                 final IdentifiedAnnotation neoplasm ) {
      NeoplasmRelationFactory.createNeoplasmRelation( jCas, typeMention, neoplasm, "Receptor_status_of" );
   }

}
