package org.apache.ctakes.cancer.stage;

import org.apache.ctakes.cancer.instance.AbstractInstanceUtil;
import org.apache.ctakes.cancer.property.SpannedProperty;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_DISORDER;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class StageInstanceUtil extends AbstractInstanceUtil<StageType, StageValue, DiseaseDisorderMention> {

   static private final Logger LOGGER = Logger.getLogger( "StageInstanceUtil" );


   public StageInstanceUtil() {
      super( "Stage" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public DiseaseDisorderMention createInstance( final JCas jcas,
                                                 final int windowStartOffset,
                                                 final SpannedProperty<StageType, StageValue> stage,
                                                 final Iterable<IdentifiedAnnotation> neoplasms ) {
      final DiseaseDisorderMention eventMention = createEventMention( jcas, windowStartOffset, stage );
      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, stage );
      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, windowStartOffset, stage, eventMention, neoplasms );
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
