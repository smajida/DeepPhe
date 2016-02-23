package org.apache.ctakes.cancer.relation;


import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/5/2015
 */
@Immutable
final public class NeoplasmRelationFactory {

   private NeoplasmRelationFactory() {
   }

   static private final Logger LOGGER = Logger.getLogger( "NeoplasmRelationFactory" );

   static public void createNeoplasmRelation( final JCas jCas,
                                              final IdentifiedAnnotation argumentEvent,
                                              final IdentifiedAnnotation neoplasm,
                                              final String relationType ) {
      if ( argumentEvent == null ) {
         LOGGER.info( "No argument neoplasm relation " + ((neoplasm != null) ? neoplasm.getCoveredText() : "") );
         return;
      }
      if ( neoplasm == null ) {
         LOGGER.info( "No Neoplasm to relate to " + argumentEvent.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument eventArgument = new RelationArgument( jCas );
      eventArgument.setArgument( argumentEvent );
      eventArgument.setRole( "Argument" );
      eventArgument.addToIndexes();
      final RelationArgument neoplasmArgument = new RelationArgument( jCas );
      neoplasmArgument.setArgument( neoplasm );
      neoplasmArgument.setRole( "Related_to" );
      neoplasmArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( eventArgument );
      neoplasmRelation.setArg2( neoplasmArgument );
      neoplasmRelation.setCategory( relationType );
      neoplasmRelation.addToIndexes();
   }

}
