package org.apache.ctakes.cancer.owl;

import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 3/28/2016
 */
final public class OwlUriUtil {

   static private final Logger LOGGER = Logger.getLogger( "OwlUriUtil" );

   private OwlUriUtil() {
   }


   /**
    * @return a Collection of the most specific uris for all the given annotations
    */
   static public String getSpecificUri( final IdentifiedAnnotation... annotations ) {
      return getSpecificUri( Arrays.asList( annotations ) );
   }

   /**
    * @return a Collection of the most specific uris for all the given annotations
    */
   static public String getSpecificUri( final Collection<IdentifiedAnnotation> annotations ) {
      return getSpecificUris( annotations ).stream().findFirst().orElse( OwlConstants.UNKNOWN_URI );
   }

   /**
    * @return a Collection of the most specific uris for all the given annotations
    */
   // TODO Implement for most precise; now it returns most broad
   static public Collection<String> getSpecificUris( final Collection<IdentifiedAnnotation> annotations ) {
      if ( annotations == null || annotations.isEmpty() ) {
         return Collections.emptyList();
      }
      final Collection<String> specificUris = new HashSet<>();
      for ( IdentifiedAnnotation value : annotations ) {
         specificUris.addAll( OwlOntologyConceptUtil.getUris( value ) );
      }
      final Collection<String> backup = new ArrayList<>( specificUris );
      for ( IdentifiedAnnotation value : annotations ) {
         final Collection<String> uris = OwlOntologyConceptUtil.getUris( value );
         for ( String uri : uris ) {
            final Collection<String> uriBranch = OwlOntologyConceptUtil.getUriBranchStream( uri )
                  .collect( Collectors.toList() );
            specificUris.retainAll( uriBranch );
         }
      }
      if ( specificUris.isEmpty() ) {
         LOGGER.warn( "Value URIs are not in the same branch" );
         return backup;
      }
      return specificUris;
   }


}
