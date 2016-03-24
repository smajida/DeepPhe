package org.apache.ctakes.cancer.phenotype.size;

import org.apache.ctakes.cancer.phenotype.AbstractPhenotypeFactory;
import org.apache.ctakes.cancer.phenotype.property.SpannedProperty;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.MeasurementAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_PHENOMENA;

/**
 * Singleton that should be used to create full neoplasm size property instances.
 * An instance is defined as the collection of all property types and values associated with a single neoplasm.
 * <p>
 * <p>
 * Use of any {@code createPhenotype()} method will create:
 * <ul>
 * size type annotations
 * neoplasm relations between the size type annotations and the nearest provided neoplasm in the text
 * size value annotations
 * degree-of relations between the size type annotations and the appropriate value annotations
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
final public class SizePhenotypeFactory
      extends AbstractPhenotypeFactory<DimensionType, DimensionValue, MeasurementAnnotation> {

   static private final Logger LOGGER = Logger.getLogger( "SizePhenotypeFactory" );

   static private class SingletonHolder {
      static private SizePhenotypeFactory INSTANCE = new SizePhenotypeFactory();
   }

   static public SizePhenotypeFactory getInstance() {
      return SingletonHolder.INSTANCE;
   }

   private SizePhenotypeFactory() {
      super( "Size" );
   }


   final public MeasurementAnnotation createInstance( final JCas jcas,
                                                      final int typeBegin, final int typeEnd,
                                                      final int valueBegin, final int valueEnd,
                                                      final String value ) {
      final SpannedDimensionType spannedType
            = new SpannedDimensionType( DimensionType.MEASUREMENT, typeBegin, typeEnd );
      final DimensionValue dimensionValue = new DimensionValue( value );
      final SpannedDimensionValue spannedValue = new SpannedDimensionValue( dimensionValue, valueBegin, valueEnd );
      final Dimension dimension = new Dimension( spannedType, spannedValue );
      return createPhenotype( jcas, 0, dimension );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public MeasurementAnnotation createPhenotype( final JCas jcas,
                                                 final int windowStartOffset,
                                                 final SpannedProperty<DimensionType, DimensionValue> size,
                                                 final Iterable<IdentifiedAnnotation> neoplasms,
                                                 final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final MeasurementAnnotation eventMention = createEventMention( jcas, windowStartOffset, size );
      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, size );
      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, windowStartOffset, size, eventMention, neoplasms );
      createEventMentionIndicator( jcas, windowStartOffset, size, eventMention, diagnosticTests );
      return eventMention;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected MeasurementAnnotation createEventMention( final JCas jcas, final int startOffset, final int endOffset ) {
      final MeasurementAnnotation measurement = new MeasurementAnnotation( jcas, startOffset, endOffset );
      measurement.setTypeID( NE_TYPE_ID_PHENOMENA );
      return measurement;
   }

}
