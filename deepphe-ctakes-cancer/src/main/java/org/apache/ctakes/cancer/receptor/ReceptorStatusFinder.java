package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/29/2015
 */
final public class ReceptorStatusFinder {

   private ReceptorStatusFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "ReceptorStatusFinder" );

   static public void addReceptorStatuses( final JCas jcas, final AnnotationFS lookupWindow,
                                           final Iterable<IdentifiedAnnotation> lookupWindowT191s ) {
      final Collection<ReceptorStatus> receptorStatuses = getReceptorStatuses( lookupWindow.getCoveredText() );
      if ( receptorStatuses.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( ReceptorStatus receptorStatus : receptorStatuses ) {
         final IdentifiedAnnotation closestDiseaseMention
               = FinderUtil.getClosestAnnotation( windowStartOffset + receptorStatus.getStartOffset(),
               windowStartOffset + receptorStatus.getEndOffset(), lookupWindowT191s );
         final org.apache.ctakes.cancer.type.textsem.ReceptorStatus receptorStatusAnnotation
               = createReceptorStatusAnnotation( jcas, lookupWindow.getBegin(), receptorStatus );
         addReceptorRelationToCas( jcas, receptorStatusAnnotation, closestDiseaseMention );
      }
   }

   static List<ReceptorStatus> getReceptorStatuses( final String lookupWindow ) {
      if ( lookupWindow.length() < 3 ) {
         return Collections.emptyList();
      }
      final List<ReceptorStatus> receptorStatuses = new ArrayList<>();
      for ( ReceptorStatusType receptorStatusType : ReceptorStatusType.values() ) {
         final Matcher matcher = receptorStatusType.getMatcher( lookupWindow );
         while ( matcher.find() ) {
            receptorStatuses.add( new ReceptorStatus( receptorStatusType, matcher.start(), matcher.end(),
                  getReceptorStatusValue( lookupWindow, matcher.start(), matcher.end() ) ) );
         }
      }
      Collections.sort( receptorStatuses, SpanOffsetComparator.getInstance() );
      return receptorStatuses;
   }


   static private ReceptorStatusValue getReceptorStatusValue( final String text, final int startOffset,
                                                              final int endOffset ) {
      return getReceptorStatusValue( text.substring( startOffset, endOffset ) );
   }

   static private ReceptorStatusValue getReceptorStatusValue( final CharSequence receptorText ) {
      for ( DefinedReceptorStatusValue receptorStatusValue : DefinedReceptorStatusValue.values() ) {
         final Matcher matcher = receptorStatusValue.getMatcher( receptorText );
         if ( matcher.find() ) {
            return receptorStatusValue;
         }
      }
      return DefinedReceptorStatusValue.UNKNOWN;
   }

   static private org.apache.ctakes.cancer.type.textsem.ReceptorStatus createReceptorStatusAnnotation( final JCas jcas,
                                                                                                       final int windowStartOffset,
                                                                                                       final ReceptorStatus receptorStatus ) {
      final org.apache.ctakes.cancer.type.textsem.ReceptorStatus receptorStatusAnnotation
            = new org.apache.ctakes.cancer.type.textsem.ReceptorStatus( jcas,
            windowStartOffset + receptorStatus.getStartOffset(),
            windowStartOffset + receptorStatus.getEndOffset() );
      final ReceptorStatusType statusType = receptorStatus.getStatusType();
      final ReceptorStatusValue statusValue = receptorStatus.getStatusValue();
      receptorStatusAnnotation.setCode( statusType.name() );
      receptorStatusAnnotation.setDescription( statusType.getTitle() );
      receptorStatusAnnotation.setValue( statusValue.getBooleanValue() );
      receptorStatusAnnotation.setTypeID( NE_TYPE_ID_FINDING );
      // Main Umls Concept
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( statusType.getCui( statusValue ) );
      umlsConcept.setTui( statusType.getTui() );
      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      umlsConcept.setCode( statusType.getUri() );
      umlsConcept.setPreferredText( statusValue.getTitle() + " " + statusType.getTitle() );
      // Value uri concept
      final OntologyConcept valueConcept = new OntologyConcept( jcas );
      valueConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      valueConcept.setCode( statusValue.getUri() );
      // Add to cas
      final FSArray ontologyConcepts = new FSArray( jcas, 2 );
      ontologyConcepts.set( 0, umlsConcept );
      ontologyConcepts.set( 1, valueConcept );
      receptorStatusAnnotation.setOntologyConceptArr( ontologyConcepts );
      receptorStatusAnnotation.addToIndexes();
      return receptorStatusAnnotation;
   }


   /**
    * Create a UIMA relation type based on arguments and the relation label. This
    * allows subclasses to create/define their own types: e.g. coreference can
    * create CoreferenceRelation instead of BinaryTextRelation
    *
    * @param jCas           - JCas object, needed to create new UIMA types
    * @param receptorStatus - First argument to relation
    * @param neoplasm     - Second argument to relation
    */
   static private void addReceptorRelationToCas( final JCas jCas,
                                                 final org.apache.ctakes.cancer.type.textsem.ReceptorStatus receptorStatus,
                                                 final IdentifiedAnnotation neoplasm ) {
      NeoplasmRelationFactory.createNeoplasmRelation( jCas, receptorStatus, neoplasm, "Receptor_status_of" );
   }

}
