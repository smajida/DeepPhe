package org.apache.ctakes.core.util;


import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/25/2016
 */
@Immutable
final public class RelationUtil {
   private RelationUtil() {
   }

   static private final Logger LOGGER = Logger.getLogger( "RelationUtil" );


   static public Collection<IdentifiedAnnotation> getFirstArguments(
         final Collection<? extends BinaryTextRelation> relations,
         final IdentifiedAnnotation identifiedAnnotation ) {
      return relations.stream()
            .filter( r -> r.getArg2().getArgument().equals( identifiedAnnotation ) )
            .map( r -> r.getArg1().getArgument() )
            .filter( IdentifiedAnnotation.class::isInstance )
            .map( a -> (IdentifiedAnnotation)a )
            .collect( Collectors.toList() );
   }

   static public Collection<IdentifiedAnnotation> getSecondArguments(
         final Collection<? extends BinaryTextRelation> relations,
         final IdentifiedAnnotation identifiedAnnotation ) {
      return relations.stream()
            .filter( r -> r.getArg1().getArgument().equals( identifiedAnnotation ) )
            .map( r -> r.getArg2().getArgument() )
            .filter( IdentifiedAnnotation.class::isInstance )
            .map( a -> (IdentifiedAnnotation)a )
            .collect( Collectors.toList() );
   }


}
