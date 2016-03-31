package org.apache.ctakes.cancer.phenotype.stage;

import org.apache.ctakes.cancer.phenotype.AbstractPhenotypeFactory;
import org.apache.ctakes.cancer.phenotype.property.SpannedProperty;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_DISORDER;

/**
 * Singleton that should be used to create full neoplasm Stage property instances.
 * An instance is defined as the collection of all property types and values associated with a single neoplasm.
 *
 *
 * Use of any {@code createPhenotype()} method will create:
 * <ul>
 * Stage type annotations
 * neoplasm relations between the Stage type annotations and the nearest provided neoplasm in the text
 * Stage value annotations
 * degree-of relations between the Stage type annotations and the appropriate value annotations
 * test-for relations between Stage type annotations and the nearest provided test in the text
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class StagePhenotypeFactory
      extends AbstractPhenotypeFactory<StageType, StageValue, DiseaseDisorderMention> {

   static private final Logger LOGGER = Logger.getLogger( "StagePhenotypeFactory" );

   static private class SingletonHolder {
      static private StagePhenotypeFactory INSTANCE = new StagePhenotypeFactory();
   }

   static public StagePhenotypeFactory getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private StagePhenotypeFactory() {
      super( "Stage" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public DiseaseDisorderMention createPhenotype( final JCas jcas,
                                                  final int windowStartOffset,
                                                  final SpannedProperty<StageType, StageValue> stage,
                                                  final Iterable<IdentifiedAnnotation> neoplasms,
                                                  final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final DiseaseDisorderMention eventMention = createTypeEventMention( jcas, windowStartOffset, stage );
      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, stage );
      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, windowStartOffset, stage, eventMention, neoplasms );
      createEventMentionIndicator( jcas, windowStartOffset, stage, eventMention, diagnosticTests );
      return eventMention;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected DiseaseDisorderMention createSpanEventMention( final JCas jcas, final int startOffset,
                                                            final int endOffset ) {
      final DiseaseDisorderMention disorder = new DiseaseDisorderMention( jcas, startOffset, endOffset );
      disorder.setTypeID( NE_TYPE_ID_DISORDER );
      return disorder;
   }

}
