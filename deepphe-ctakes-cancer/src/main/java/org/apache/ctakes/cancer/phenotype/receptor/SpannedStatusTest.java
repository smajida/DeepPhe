package org.apache.ctakes.cancer.phenotype.receptor;

import org.apache.ctakes.cancer.phenotype.property.SpannedTest;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;

import javax.annotation.concurrent.Immutable;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/24/2016
 */
@Immutable
final class SpannedStatusTest extends SpannedTest<StatusTest> {

   SpannedStatusTest( final IdentifiedAnnotation procedure, final StatusTest statusTest ) {
      super( statusTest, procedure.getBegin(), procedure.getEnd() );
   }

}
