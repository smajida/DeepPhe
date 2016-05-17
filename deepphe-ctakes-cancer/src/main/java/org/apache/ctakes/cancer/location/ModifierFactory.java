package org.apache.ctakes.cancer.location;


import org.apache.ctakes.core.ontology.UriAnnotationFactory;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/16/2016
 */
final public class ModifierFactory {

   private ModifierFactory() {
   }

   static private final Logger LOGGER = Logger.getLogger( "ModifierFactory" );


   static public SignSymptomMention createLocationModifier( final JCas jcas,
                                                            final int windowStartOffset,
                                                            final SpannedModifier spannedModifier ) {
      final SignSymptomMention modifierMention
            = new SignSymptomMention( jcas,
            windowStartOffset + spannedModifier.getStartOffset(),
            windowStartOffset + spannedModifier.getEndOffset() );
      modifierMention.setTypeID( CONST.NE_TYPE_ID_FINDING );
      // Main Umls Concept
      final UmlsConcept umlsConcept
            = UriAnnotationFactory.createUmlsConcept( jcas, spannedModifier.getModifier().getUri() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      modifierMention.setOntologyConceptArr( ontologyConcepts );
      modifierMention.addToIndexes();
      return modifierMention;
   }


   static public SignSymptomMention createLocationModifier( final JCas jcas,
                                                            final IdentifiedAnnotation annotation ) {
      final SignSymptomMention modifierMention
            = new SignSymptomMention( jcas, annotation.getBegin(), annotation.getEnd() );
      modifierMention.setTypeID( CONST.NE_TYPE_ID_FINDING );
      final FSArray ontologyConcepts = annotation.getOntologyConceptArr();
      if ( ontologyConcepts != null ) {
         modifierMention.setOntologyConceptArr( ontologyConcepts );
      }
      modifierMention.addToIndexes();
      return modifierMention;
   }

}
