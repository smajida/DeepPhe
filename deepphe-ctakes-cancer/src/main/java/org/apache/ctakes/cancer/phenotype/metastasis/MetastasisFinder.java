package org.apache.ctakes.cancer.phenotype.metastasis;


import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.Collection;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/29/2016
 */
public class MetastasisFinder {

   static private final Logger LOGGER = Logger.getLogger( "MetastasisFinder" );


   static public void addMetastases( final JCas jcas, final Annotation lookupWindow,
                                     final Iterable<IdentifiedAnnotation> neoplasms,
                                     final Collection<IdentifiedAnnotation> metastases ) {
      if ( metastases.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( IdentifiedAnnotation metastasis : metastases ) {
         MetastasisPhenotypeFactory.getInstance().createPhenotype( jcas, metastasis, neoplasms );
      }
   }


}