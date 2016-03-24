package org.apache.ctakes.cancer.concept.instance;

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
final public class ConceptInstanceUtil {

   static private final Logger LOGGER = Logger.getLogger( "ConceptInstanceUtil" );

   private ConceptInstanceUtil() {
   }

   /**
    * @param jCas ye olde ...
    * @param uri  uri for instance of interest
    * @return collection of instances with the given uri
    */
   static public Collection<ConceptInstance> getExactConceptInstances( final JCas jCas, final String uri ) {
      final Function<IdentifiedAnnotation, ConceptInstance> createInstance = a -> new ConceptInstance( uri, a );
      return OwlOntologyConceptUtil.getAnnotationsByUri( jCas, uri )
            .stream()
            .map( createInstance )
            .collect( Collectors.toList() );
   }

   /**
    * @param jCas ye olde ...
    * @param uri  uri for instance of interest
    * @return collection of instances with the given uri
    */
   static public Collection<ConceptInstance> getBranchConceptInstances( final JCas jCas, final String uri ) {
      final Function<IdentifiedAnnotation, ConceptInstance> createInstance = a -> new ConceptInstance( uri, a );
      return OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( jCas, uri )
            .map( createInstance )
            .collect( Collectors.toList() );
   }


}
