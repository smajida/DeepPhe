package org.apache.ctakes.cancer.fhir.resource;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.jcas.JCas;

import java.util.Collection;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 3/17/2016
 */
final public class OwlInstanceUtil {

   static private final Logger LOGGER = Logger.getLogger( "OwlInstanceUtil" );

   private OwlInstanceUtil() {
   }

   /**
    * @param jCas ye olde ...
    * @param uri  uri for instance of interest
    * @return collection of instances with the given uri
    */
   static public Collection<Resource> getOwlInstances( final JCas jCas, final String uri ) {
      final Function<IdentifiedAnnotation, Resource> createInstance = a -> new Resource( uri, a );
      return OwlOntologyConceptUtil.getAnnotationsByUri( jCas, uri )
            .stream()
            .map( createInstance )
            .collect( Collectors.toList() );
   }

}
