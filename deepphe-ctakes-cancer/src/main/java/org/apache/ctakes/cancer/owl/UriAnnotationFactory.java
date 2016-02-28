package org.apache.ctakes.cancer.owl;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.dictionary.lookup2.util.SemanticUtil;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.textsem.*;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import javax.annotation.concurrent.Immutable;

import static org.apache.ctakes.typesystem.type.constants.CONST.*;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/25/2016
 */
@Immutable
final public class UriAnnotationFactory {

   static private final Logger LOGGER = Logger.getLogger( "UriAnnotationFactory" );

   private UriAnnotationFactory() {
   }


   static public IdentifiedAnnotation createIdentifiedAnnotation( final JCas jcas, final int beginOffset,
                                                                  final int endOffset, final String uri ) {
      final IClass iClass = OwlOntologyConceptUtil.getIClass( uri );
      if ( iClass == null ) {
         return new IdentifiedAnnotation( jcas, beginOffset, endOffset );
      }
      final String tui = OwlParserUtil.getTui( iClass );
      final Integer semanticGroupId = (tui == null) ? NE_TYPE_ID_UNKNOWN : SemanticUtil.getTuiSemanticGroupId( tui );
      final IdentifiedAnnotation annotation = createSemanticAnnotation( jcas, semanticGroupId );
      annotation.setTypeID( semanticGroupId );
      annotation.setBegin( beginOffset );
      annotation.setEnd( endOffset );
      annotation.setDiscoveryTechnique( NE_DISCOVERY_TECH_GOLD_ANNOTATION );
      final UmlsConcept umlsConcept = createUmlsConcept( jcas, iClass, uri );
      final FSArray conceptArray = new FSArray( jcas, 1 );
      conceptArray.set( 0, umlsConcept );
      annotation.setOntologyConceptArr( conceptArray );
//      annotation.addToIndexes();
      return annotation;
   }

   static private IdentifiedAnnotation createSemanticAnnotation( final JCas jcas, final int cTakesSemantic ) {
      switch ( cTakesSemantic ) {
         case NE_TYPE_ID_DRUG: {
            return new MedicationMention( jcas );
         }
         case NE_TYPE_ID_ANATOMICAL_SITE: {
            return new AnatomicalSiteMention( jcas );
         }
         case NE_TYPE_ID_DISORDER: {
            return new DiseaseDisorderMention( jcas );
         }
         case NE_TYPE_ID_FINDING: {
            return new SignSymptomMention( jcas );
         }
         case NE_TYPE_ID_LAB: {
            return new LabMention( jcas );
         }
         case NE_TYPE_ID_PROCEDURE: {
            return new ProcedureMention( jcas );
         }
      }
      return new EntityMention( jcas );
   }

   static public UmlsConcept createUmlsConcept( final JCas jcas, final String uri ) {
      final IClass iClass = OwlOntologyConceptUtil.getIClass( uri );
      if ( iClass == null ) {
         final UmlsConcept umlsConcept = new UmlsConcept( jcas );
         umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
         umlsConcept.setCode( uri );
         return umlsConcept;
      }
      return createUmlsConcept( jcas, iClass, uri );
   }


   static private UmlsConcept createUmlsConcept( final JCas jcas, final IClass iClass, final String uri ) {
      final String cui = OwlParserUtil.getCui( iClass );
      final String tui = OwlParserUtil.getTui( iClass );
      final String title = OwlParserUtil.getPreferredText( iClass );
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( cui == null ? "" : cui );
      umlsConcept.setTui( tui == null ? "" : tui );
      umlsConcept.setPreferredText( title == null ? "" : title );
      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      umlsConcept.setCode( uri );
      return umlsConcept;
   }

}
