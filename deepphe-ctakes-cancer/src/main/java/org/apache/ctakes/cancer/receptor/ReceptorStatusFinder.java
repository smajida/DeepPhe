package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
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
                                           final Iterable<DiseaseDisorderMention> lookupWindowT191s ) {
      final Collection<ReceptorStatus> receptorStatuses = getReceptorStatuses( lookupWindow.getCoveredText() );
      if ( receptorStatuses.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( ReceptorStatus receptorStatus : receptorStatuses ) {
         final DiseaseDisorderMention closestDiseaseMention
               = FinderUtil.getClosestEventMention( windowStartOffset + receptorStatus.getStartOffset(),
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
      receptorStatusAnnotation.setCode( receptorStatus.getStatusType().name() );
      receptorStatusAnnotation.setDescription( receptorStatus.getStatusType().getTitle() );
      receptorStatusAnnotation.setValue( receptorStatus.getStatusValue().getBooleanValue() );
      // Sets the receptor status annotation to match the umls concept.  I'm not sure that we want/need this
      receptorStatusAnnotation.setTypeID( NE_TYPE_ID_FINDING );
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( receptorStatus.getStatusType().getCui( receptorStatus.getStatusValue() ) );
      umlsConcept.setTui( receptorStatus.getStatusType().getTui() );
//      umlsConcept.setCodingScheme( "SNOMED" ); ---> These are more NCI than SNOMED
//      umlsConcept.setCode( "385379008" );
      umlsConcept.setPreferredText(
            receptorStatus.getStatusValue().getTitle() + " " + receptorStatus.getStatusType().getTitle() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      receptorStatusAnnotation.setOntologyConceptArr( ontologyConcepts );


      receptorStatusAnnotation.addToIndexes();
      return receptorStatusAnnotation;
   }


   /**
    * Create a UIMA relation type based on arguments and the relation label. This
    * allows subclasses to create/define their own types: e.g. coreference can
    * create CoreferenceRelation instead of BinaryTextRelation
    *
    * @param jCas            - JCas object, needed to create new UIMA types
    * @param receptorStatus  - First argument to relation
    * @param disorderMention - Second argument to relation
    */
   static private void addReceptorRelationToCas( final JCas jCas,
                                                 final org.apache.ctakes.cancer.type.textsem.ReceptorStatus receptorStatus,
                                                 final DiseaseDisorderMention disorderMention ) {
      if ( disorderMention == null ) {
         LOGGER.info( "No Neoplasm discovered to relate to " + receptorStatus.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument receptorStatusArgument = new RelationArgument( jCas );
      receptorStatusArgument.setArgument( receptorStatus );
      receptorStatusArgument.setRole( "Argument" );
      receptorStatusArgument.addToIndexes();
      final RelationArgument disorderMentionArgument = new RelationArgument( jCas );
      disorderMentionArgument.setArgument( disorderMention );
      disorderMentionArgument.setRole( "Related_to" );
      disorderMentionArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( receptorStatusArgument );
      neoplasmRelation.setArg2( disorderMentionArgument );
      neoplasmRelation.setCategory( "Receptor_status_of" );
      neoplasmRelation.addToIndexes();
   }

}
