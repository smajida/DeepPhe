package org.apache.ctakes.cancer.ae;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import edu.pitt.dbmi.nlp.noble.terminology.TerminologyException;
import edu.pitt.dbmi.nlp.noble.tools.ConText;
import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.apache.ctakes.typesystem.type.refsem.Event;
import org.apache.ctakes.typesystem.type.refsem.EventProperties;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textspan.Sentence;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import java.util.*;

final public class ConTextAnnotator extends JCasAnnotator_ImplBase {
   static private final Logger LOGGER = Logger.getLogger( "ConTextAnnotator" );


   private ConText context;

   @Override
   public void initialize( final UimaContext aContext ) throws ResourceInitializationException {
      super.initialize( aContext );
      context = new ConText();
   }

   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting Processing" );
      for ( Sentence ctakesSentence : JCasUtil.select( jcas, Sentence.class ) ) {

         // create noble coder mention to ctakes identified annotation map
         final Map<Mention, IdentifiedAnnotation> mentionMap = createMentionMap( jcas, ctakesSentence );
         if ( mentionMap.isEmpty() ) {
            continue;
         }

         // create noble coder sentence object
         final edu.pitt.dbmi.nlp.noble.coder.model.Sentence nobleSentence = createNobleSentence( ctakesSentence );

         // add Mentions to the noble coder sentence from cTAKES
         nobleSentence.setMentions( new ArrayList<>( mentionMap.keySet() ) );

         // run ConText on the sentence
         try {
            context.process( nobleSentence );
         } catch ( TerminologyException e ) {
            throw new AnalysisEngineProcessException( e );
         }

         for ( Map.Entry<Mention, IdentifiedAnnotation> entry : mentionMap.entrySet() ) {
            adjustAnnotationProperties( jcas, entry.getKey(), entry.getValue() );
         }
      }
      LOGGER.info( "Finished Processing" );
   }

   static private Map<Mention, IdentifiedAnnotation> createMentionMap( final JCas jcas, final Sentence sentence ) {
      final List<IdentifiedAnnotation> identifiedAnnotations
            = JCasUtil.selectCovered( jcas, IdentifiedAnnotation.class, sentence );
      if ( identifiedAnnotations.isEmpty() ) {
         return Collections.emptyMap();
      }
      final Map<Mention, IdentifiedAnnotation> mentions = new HashMap<>( identifiedAnnotations.size() );
      for ( IdentifiedAnnotation ia : identifiedAnnotations ) {

         // setup noble annotation
         final edu.pitt.dbmi.nlp.noble.terminology.Annotation a = new edu.pitt.dbmi.nlp.noble.terminology.Annotation();
         a.setText( ia.getCoveredText() );
         a.setOffset( ia.getBegin() );

         // setup concept
         final Concept c = new Concept( getConceptURI( ia ), ia.getCoveredText() );

         // setup mention
         final Mention m = new Mention();
         m.setConcept( c );
         m.setAnnotations( Collections.singletonList( a ) );

         mentions.put( m, ia );
      }
      return mentions;
   }

   static private edu.pitt.dbmi.nlp.noble.coder.model.Sentence createNobleSentence( final Sentence sentence ) {
      return new edu.pitt.dbmi.nlp.noble.coder.model.Sentence( sentence.getCoveredText(),
            sentence.getBegin(), edu.pitt.dbmi.nlp.noble.coder.model.Sentence.TYPE_PROSE );
   }

   static private void adjustAnnotationProperties( final JCas jcas,
                                                   final Mention mention,
                                                   final IdentifiedAnnotation annotation ) {
      if ( mention.isNegated() ) {
         annotation.setPolarity( CONST.NE_POLARITY_NEGATION_PRESENT );
      }
      if ( mention.isHedged() ) {
         annotation.setUncertainty( CONST.NE_UNCERTAINTY_PRESENT );
      }
      if ( mention.isHistorical() ) {
         annotation.setHistoryOf( CONST.NE_HISTORY_OF_PRESENT );
      }
      if ( mention.isFamilyMember() ) {
         annotation.setSubject( CONST.ATTR_SUBJECT_FAMILY_MEMBER );
      }
      if ( !EventMention.class.isInstance( annotation ) ) {
         return;
      }
      final String aspect = mention.getModifierValue( ConText.MODIFIER_TYPE_ASPECT );
      final String degree = mention.getModifierValue( ConText.MODIFIER_TYPE_DEGREE );
      final String permanence = mention.getModifierValue( ConText.MODIFIER_TYPE_PERMENENCE );
      if ( aspect == null && degree == null && permanence == null ) {
         return;
      }
      Event event = ((EventMention)annotation).getEvent();
      if ( event == null ) {
         event = new Event( jcas );
         ((EventMention)annotation).setEvent( event );
      }
      EventProperties properties = event.getProperties();
      if ( properties == null ) {
         properties = new EventProperties( jcas );
         event.setProperties( properties );
      }
      if ( aspect != null ) {
         properties.setContextualAspect( aspect );
      }
      if ( degree != null ) {
         properties.setDegree( degree );
      }
      if ( permanence != null ) {
         properties.setPermanence( permanence );
      }
   }


   static private String getConceptURI( final IdentifiedAnnotation ia ) {
      return OwlOntologyConceptUtil.getUris( ia ).stream().findFirst().orElse( OwlConstants.UNKNOWN_URI );
   }

}
