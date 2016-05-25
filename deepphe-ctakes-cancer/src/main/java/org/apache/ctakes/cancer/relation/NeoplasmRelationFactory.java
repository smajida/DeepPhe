package org.apache.ctakes.cancer.relation;


import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.AbstractPhenotypeFactory;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import javax.annotation.concurrent.Immutable;
import java.util.Arrays;
import java.util.Collection;

/**
 * A Neoplasm Relation is a special relation that links a neoplasm annotation with a property annotation.
 * Though useful, neoplasm relations should normally be created using subclasses of
 * {@link AbstractPhenotypeFactory}
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/5/2015
 */
@Immutable
final public class NeoplasmRelationFactory {

   private NeoplasmRelationFactory() {
   }

   static private final Logger LOGGER = Logger.getLogger( "NeoplasmRelationFactory" );

   static public Collection<String> getNeoplasmUris() {
      return Arrays.asList( OwlConstants.CANCER_OWL + "#Neoplasm",
            OwlConstants.BREAST_CANCER_OWL + "#Carcinoma",
            OwlConstants.CANCER_OWL + "#CancerType" );
   }

   /**
    * @param jCas          ye olde ...
    * @param argumentEvent neoplasm property annotation
    * @param neoplasm      neoplasm
    * @param relationType  name for neoplasm relation, e.g. "size_of"
    * @return a new neoplasm relation that has been added to the cas
    */
   static public NeoplasmRelation createNeoplasmRelation( final JCas jCas,
                                              final IdentifiedAnnotation argumentEvent,
                                              final IdentifiedAnnotation neoplasm,
                                              final String relationType ) {
      if ( argumentEvent == null ) {
         LOGGER.info( "No argument neoplasm relation " + ((neoplasm != null) ? neoplasm.getCoveredText() : "") );
         return null;
      }
      if ( neoplasm == null ) {
         LOGGER.info( "No Neoplasm to relate to " + argumentEvent.getCoveredText() );
         return null;
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
      return neoplasmRelation;
   }

}
