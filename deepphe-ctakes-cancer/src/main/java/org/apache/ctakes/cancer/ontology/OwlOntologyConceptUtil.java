package org.apache.ctakes.cancer.ontology;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import org.apache.ctakes.core.util.OntologyConceptUtil;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 11/24/2015
 */
final public class OwlOntologyConceptUtil {

   static private final Logger LOGGER = Logger.getLogger( "OwlOntologyConceptUtil" );

   private OwlOntologyConceptUtil() {
   }

   static private final Function<String, String> asSelf = self -> self;

   static private final BinaryOperator<Collection<IdentifiedAnnotation>> mergeSets
         = ( set1, set2 ) -> {
      set1.addAll( set2 );
      return set1;
   };


   /**
    * Convenience method that calls {@link OntologyConceptUtil#getCodes} with {@link OwlConcept#URI} as the scheme
    *
    * @param annotation -
    * @return all owl URIs that exist for the given annotation
    */
   static public Collection<String> getOwlUris( final IdentifiedAnnotation annotation ) {
      return OntologyConceptUtil.getCodes( annotation, OwlConcept.URI );
   }

   /**
    * @param annotation -
    * @return all iClasses for the given annotation
    */
   static public Collection<IClass> getOwlClasses( final IdentifiedAnnotation annotation ) {
//      final Collection<String> uris = getOwlUris( annotation );
//      if ( uris.isEmpty() ) {
//         return Collections.emptyList();
//      }
//      final Collection<IClass> iClasses = new HashSet<>();
//      final Collection<String> ontologyPaths = OwlConnectionFactory.getInstance().listOntologyPaths();
//      for ( String ontologyPath : ontologyPaths ) {
//         try {
//            final IOntology ontology = OwlConnectionFactory.getInstance().getOntology( ontologyPath );
//            uris.stream().map( ontology::getClass ).forEach( iClasses::add );
//         } catch ( IOntologyException | FileNotFoundException multE ) {
//            LOGGER.error( multE.getMessage(), multE );
//         }
//      }
//      return iClasses;
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getDefaultOntology();
         return getOwlUris( annotation ).stream()
               .map( ontology::getClass )
               .collect( Collectors.toSet() );
      } catch ( IOntologyException | FileNotFoundException multE ) {
         LOGGER.error( multE.getMessage(), multE );
      }
      return Collections.emptyList();
   }

   /**
    * Convenience method that calls {@link OntologyConceptUtil#getCodes} with {@link OwlConcept#URI} as the scheme
    *
    * @param jcas -
    * @return all owl URIs that exist for the given annotation
    */
   static public Collection<String> getOwlUris( final JCas jcas ) {
      return OntologyConceptUtil.getCodes( jcas, OwlConcept.URI );
   }

   /**
    * @param jcas -
    * @return all iClasses in the jcas
    */
   static public Collection<IClass> getOwlClasses( final JCas jcas ) {
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getDefaultOntology();
         return getOwlUris( jcas ).stream()
               .map( ontology::getClass )
               .collect( Collectors.toSet() );
      } catch ( IOntologyException | FileNotFoundException multE ) {
         LOGGER.error( multE.getMessage(), multE );
      }
      return Collections.emptyList();
   }

   /**
    * Convenience method that calls {@link OntologyConceptUtil#getAnnotationsByCode}
    *
    * @param jcas -
    * @param uri  uri of interest
    * @return all IdentifiedAnnotations that have the given uri
    */
   static public Collection<IdentifiedAnnotation> getAnnotationsByOwlUri( final JCas jcas,
                                                                          final String uri ) {
      return OntologyConceptUtil.getAnnotationsByCode( jcas, uri );
   }


   /**
    * @param jcas -
    * @param uri  uri of interest
    * @return all IdentifiedAnnotations for the given uri and its children
    */
   static public Collection<IdentifiedAnnotation> getAnnotationsByOwlUriBranch( final JCas jcas,
                                                                                final String uri ) {
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getDefaultOntology();
         final IClass branchRoot = ontology.getClass( uri );
         if ( branchRoot == null ) {
            LOGGER.error( "No Class exists for URI " + uri );
            return Collections.emptyList();
         }
         final Collection<IdentifiedAnnotation> branch
               = Arrays.stream( branchRoot.getSubClasses() )
               .map( OwlParserUtil::getUriString )
               .map( u -> getAnnotationsByOwlUri( jcas, u ) )
               .flatMap( Collection::stream )
               .collect( Collectors.toSet() );
         branch.addAll( getAnnotationsByOwlUri( jcas, uri ) );
         return branch;
      } catch ( IOntologyException | FileNotFoundException multE ) {
         LOGGER.error( multE.getMessage(), multE );
      }
      return Collections.emptyList();
   }

   /**
    * @param jcas -
    * @param uri  uri of interest
    * @return map of uris and IdentifiedAnnotations for the given uri and its children
    */
   static public Map<String, Collection<IdentifiedAnnotation>> getUriAnnotationsByOwlUriBranch( final JCas jcas,
                                                                                                final String uri ) {
      try {
         final IOntology ontology = OwlConnectionFactory.getInstance().getDefaultOntology();
         final IClass branchRoot = ontology.getClass( uri );
         if ( branchRoot == null ) {
            LOGGER.error( "No Class exists for URI " + uri );
            return Collections.emptyMap();
         }
         final Map<String, Collection<IdentifiedAnnotation>> branch
               = Arrays.stream( branchRoot.getSubClasses() )
               .map( OwlParserUtil::getUriString )
               .collect( Collectors.toMap( asSelf, u -> getAnnotationsByOwlUri( jcas, u ), mergeSets ) );
         branch.put( uri, getAnnotationsByOwlUri( jcas, uri ) );
         return branch;
      } catch ( IOntologyException | FileNotFoundException multE ) {
         LOGGER.error( multE.getMessage(), multE );
      }
      return Collections.emptyMap();
   }


}
