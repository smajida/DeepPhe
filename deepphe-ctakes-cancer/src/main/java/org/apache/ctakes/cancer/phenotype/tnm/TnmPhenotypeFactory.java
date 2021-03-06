package org.apache.ctakes.cancer.phenotype.tnm;

import org.apache.ctakes.cancer.phenotype.AbstractPhenotypeFactory;
import org.apache.ctakes.cancer.phenotype.property.SpannedProperty;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

/**
 * Singleton that should be used to create full neoplasm T N M property instances.
 * An instance is defined as the collection of all property types and values associated with a single neoplasm.
 *
 *
 * Use of any {@code createPhenotype()} method will create:
 * <ul>
 * T N M type annotations
 * neoplasm relations between the T N M type annotations and the nearest provided neoplasm in the text
 * T N M value annotations
 * degree-of relations between the T N M type annotations and the appropriate value annotations
 * test-for relations between T N M type annotations and the nearest provided test in the text
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class TnmPhenotypeFactory extends AbstractPhenotypeFactory<TnmType, TnmValue, SignSymptomMention> {

   static private final Logger LOGGER = Logger.getLogger( "TnmPhenotypeFactory" );

   static private class SingletonHolder {
      static private TnmPhenotypeFactory INSTANCE = new TnmPhenotypeFactory();
   }

   static public TnmPhenotypeFactory getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private TnmPhenotypeFactory() {
      super( "TNM" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SignSymptomMention createPhenotype( final JCas jcas,
                                              final int windowStartOffset,
                                              final SpannedProperty<TnmType, TnmValue> tnm,
                                              final Iterable<IdentifiedAnnotation> neoplasms,
                                              final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final SignSymptomMention eventMention = createTypeEventMention( jcas, windowStartOffset, tnm );
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
   protected SignSymptomMention createSpanEventMention( final JCas jcas, final int startOffset, final int endOffset ) {
      final SignSymptomMention finding = new SignSymptomMention( jcas, startOffset, endOffset );
      finding.setTypeID( NE_TYPE_ID_FINDING );
      return finding;
   }

}
