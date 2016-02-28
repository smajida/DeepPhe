package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.instance.AbstractInstanceFactory;
import org.apache.ctakes.cancer.property.SpannedProperty;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class TnmInstanceFactory extends AbstractInstanceFactory<TnmType, TnmValue, SignSymptomMention> {

   static private final Logger LOGGER = Logger.getLogger( "TnmInstanceFactory" );

   static private class SingletonHolder {
      static private TnmInstanceFactory INSTANCE = new TnmInstanceFactory();
   }

   static public TnmInstanceFactory getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private TnmInstanceFactory() {
      super( "TNM" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SignSymptomMention createInstance( final JCas jcas,
                                             final int windowStartOffset,
                                             final SpannedProperty<TnmType, TnmValue> tnm,
                                             final Iterable<IdentifiedAnnotation> neoplasms,
                                             final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final SignSymptomMention eventMention = createEventMention( jcas, windowStartOffset, tnm );
      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, tnm );
      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, windowStartOffset, tnm, eventMention, neoplasms );
      createEventMentionIndicator( jcas, windowStartOffset, tnm, eventMention, diagnosticTests );
      return eventMention;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected SignSymptomMention createEventMention( final JCas jcas, final int startOffset, final int endOffset ) {
      final SignSymptomMention finding = new SignSymptomMention( jcas, startOffset, endOffset );
      finding.setTypeID( NE_TYPE_ID_FINDING );
      return finding;
   }

}
