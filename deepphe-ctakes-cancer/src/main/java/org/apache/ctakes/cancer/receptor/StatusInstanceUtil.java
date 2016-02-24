package org.apache.ctakes.cancer.receptor;


import org.apache.ctakes.cancer.instance.AbstractInstanceUtil;
import org.apache.ctakes.cancer.property.SpannedProperty;
import org.apache.ctakes.cancer.property.SpannedTest;
import org.apache.ctakes.cancer.property.Test;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/6/2015
 */
final public class StatusInstanceUtil extends AbstractInstanceUtil<StatusType, StatusValue, SignSymptomMention> {

   static private final Logger LOGGER = Logger.getLogger( "StatusInstanceUtil" );

   public StatusInstanceUtil() {
      super( "Receptor Status" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SignSymptomMention createInstance( final JCas jcas,
                                             final int windowStartOffset,
                                             final SpannedProperty<StatusType, StatusValue> status,
                                             final Iterable<IdentifiedAnnotation> neoplasms,
                                             final SpannedTest<? extends Test> spannedTest ) {
      final SignSymptomMention eventMention = createEventMention( jcas, windowStartOffset, status );
      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, status );
      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, windowStartOffset, status, eventMention, neoplasms );
      createEventMentionIndicator( jcas, windowStartOffset, eventMention, spannedTest );
      return eventMention;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected SignSymptomMention createEventMention( final JCas jcas, final int startOffset, final int endOffset ) {
      final SignSymptomMention disorder = new SignSymptomMention( jcas, startOffset, endOffset );
      disorder.setTypeID( NE_TYPE_ID_FINDING );
      return disorder;
   }


}
