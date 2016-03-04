package org.apache.ctakes.cancer.stage;

import org.apache.ctakes.cancer.instance.AbstractInstanceFactory;
import org.apache.ctakes.cancer.property.SpannedProperty;
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
 * Use of any {@code createInstance()} method will create:
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
final public class StageInstanceFactory extends AbstractInstanceFactory<StageType, StageValue, DiseaseDisorderMention> {

   static private final Logger LOGGER = Logger.getLogger( "StageInstanceFactory" );

   static private class SingletonHolder {
      static private StageInstanceFactory INSTANCE = new StageInstanceFactory();
   }

   static public StageInstanceFactory getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private StageInstanceFactory() {
      super( "Stage" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public DiseaseDisorderMention createInstance( final JCas jcas,
                                                 final int windowStartOffset,
                                                 final SpannedProperty<StageType, StageValue> stage,
                                                 final Iterable<IdentifiedAnnotation> neoplasms,
                                                 final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final DiseaseDisorderMention eventMention = createEventMention( jcas, windowStartOffset, stage );
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
   protected DiseaseDisorderMention createEventMention( final JCas jcas, final int startOffset, final int endOffset ) {
      final DiseaseDisorderMention disorder = new DiseaseDisorderMention( jcas, startOffset, endOffset );
      disorder.setTypeID( NE_TYPE_ID_DISORDER );
      return disorder;
   }

}
