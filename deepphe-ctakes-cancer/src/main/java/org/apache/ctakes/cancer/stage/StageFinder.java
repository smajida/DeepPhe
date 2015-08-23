package org.apache.ctakes.cancer.stage;


import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.CancerStage;
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

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_DISORDER;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/5/2015
 */
final public class StageFinder {

   private StageFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "StageFinder" );


   static List<Stage> getStages( final String lookupWindow ) {
      if ( lookupWindow.length() < 6 ) {
         return Collections.emptyList();
      }
      final List<Stage> stages = new ArrayList<>();
      for ( StageValue stageValue : StageValue.values() ) {
         final Matcher matcher = stageValue.getMatcher( lookupWindow );
         while ( matcher.find() ) {
            stages.add( new Stage( matcher.start(), matcher.end(), stageValue ) );
         }
      }
      Collections.sort( stages, SpanOffsetComparator.getInstance() );
      return stages;
   }

   static public void addStages( final JCas jcas, final AnnotationFS lookupWindow,
                                 final Iterable<DiseaseDisorderMention> lookupWindowT191s ) {
      final Collection<Stage> stages = getStages( lookupWindow.getCoveredText() );
      if ( stages.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( Stage stage : stages ) {
         final DiseaseDisorderMention closestDiseaseMention
               = FinderUtil.getClosestEventMention( windowStartOffset + stage.getStartOffset(),
               windowStartOffset + stage.getEndOffset(), lookupWindowT191s );
         final CancerStage cancerStageAnnotation = createStageAnnotation( jcas, lookupWindow.getBegin(), stage );
         addStageRelationToCas( jcas, cancerStageAnnotation, closestDiseaseMention );
      }
   }

   static private CancerStage createStageAnnotation( final JCas jcas, final int windowStartOffset, final Stage stage ) {
      final int startOffset = windowStartOffset + stage.getStartOffset();
      final int endOffset = windowStartOffset + stage.getEndOffset();
      final CancerStage cancerStage = new CancerStage( jcas, startOffset, endOffset );
//      cancerStage.setValue( stage.getValue().getCode() );
      // Sets the receptor status annotation to match the umls concept.  I'm not sure that we want/need this
      cancerStage.setTypeID( NE_TYPE_ID_DISORDER );
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( stage.getValue().getCui() );
      umlsConcept.setTui( stage.getValue().getTui() );
      umlsConcept.setPreferredText( stage.getValue().getTitle() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      cancerStage.setOntologyConceptArr( ontologyConcepts );
      return cancerStage;
   }


   /**
    * Create a UIMA relation type based on arguments and the relation label. This
    * allows subclasses to create/define their own types: e.g. coreference can
    * create CoreferenceRelation instead of BinaryTextRelation
    *
    * @param jCas            - JCas object, needed to create new UIMA types
    * @param cancerStage     - First argument to relation
    * @param diseaseDisorder - Second argument to relation
    */
   static private void addStageRelationToCas( final JCas jCas,
                                              final CancerStage cancerStage,
                                              final DiseaseDisorderMention diseaseDisorder ) {
      if ( diseaseDisorder == null ) {
         LOGGER.info( "No Neoplasm discovered to relate to " + cancerStage.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument cancerStageArgument = new RelationArgument( jCas );
      cancerStageArgument.setArgument( cancerStage );
      cancerStageArgument.setRole( "Argument" );
      cancerStageArgument.addToIndexes();
      final RelationArgument disorderMentionArgument = new RelationArgument( jCas );
      disorderMentionArgument.setArgument( diseaseDisorder );
      disorderMentionArgument.setRole( "Related_to" );
      disorderMentionArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( cancerStageArgument );
      neoplasmRelation.setArg2( disorderMentionArgument );
      neoplasmRelation.setCategory( "Cancer_Stage_of" );
      neoplasmRelation.addToIndexes();
   }

}
