package org.apache.ctakes.cancer.phenotype.metastasis;

import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.jcas.JCas;

import java.util.logging.Logger;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/29/2016
 */
public class MetastasisPhenotypeFactory {

   static private final Logger LOGGER = Logger.getLogger( "MetastasisPhenotypeFactory" );

   static private class SingletonHolder {
      static private MetastasisPhenotypeFactory INSTANCE = new MetastasisPhenotypeFactory();
   }

   static public MetastasisPhenotypeFactory getInstance() {
      return SingletonHolder.INSTANCE;
   }


   public void createPhenotype( final JCas jcas,
                                final IdentifiedAnnotation metastasis,
                                final Iterable<IdentifiedAnnotation> neoplasms ) {
//      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, metastasis, neoplasms );
   }


   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas       -
    * @param metastasis -
    * @param neoplasms  nearby neoplasms
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final IdentifiedAnnotation metastasis,
                                                    final Iterable<IdentifiedAnnotation> neoplasms ) {
      createEventMentionNeoplasm( jCas,
            metastasis.getBegin(),
            metastasis.getEnd(),
            metastasis,
            "metastasis_of", neoplasms );
   }

   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas      -
    * @param typeBegin character offset of the property
    * @param typeEnd   -
    * @param typeTitle -
    * @param neoplasms nearby neoplasms
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final int typeBegin, final int typeEnd,
                                                    final IdentifiedAnnotation metastasis,
                                                    final String typeTitle,
                                                    final Iterable<IdentifiedAnnotation> neoplasms ) {
      final IdentifiedAnnotation closestNeoplasm
            = FinderUtil.getClosestAnnotation( typeBegin, typeEnd, neoplasms );
      if ( closestNeoplasm != null ) {
         final String relationName = typeTitle.replace( ' ', '_' ) + "_of";
         createEventMentionNeoplasm( jCas, metastasis, closestNeoplasm, relationName );
      }
   }


   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas         -
    * @param eventMention property as an event mention
    * @param neoplasm     the associated neoplasm
    * @param relationName e.g. tnm_of
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final IdentifiedAnnotation eventMention,
                                                    final IdentifiedAnnotation neoplasm,
                                                    final String relationName ) {
      if ( eventMention == null ) {
         LOGGER.info( "No argument for " + relationName
                      + ((neoplasm != null) ? " of " + neoplasm.getCoveredText() : "") );
         return;
      }
      if ( neoplasm == null ) {
         LOGGER.info( "No Neoplasm for " + relationName + " " + eventMention.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument eventArgument = new RelationArgument( jCas );
      eventArgument.setArgument( eventMention );
      eventArgument.setRole( "Argument" );
      eventArgument.addToIndexes();
      final RelationArgument neoplasmArgument = new RelationArgument( jCas );
      neoplasmArgument.setArgument( neoplasm );
      neoplasmArgument.setRole( "Related_to" );
      neoplasmArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( eventArgument );
      neoplasmRelation.setArg2( neoplasmArgument );
      neoplasmRelation.setCategory( relationName );
      neoplasmRelation.addToIndexes();
   }


}
