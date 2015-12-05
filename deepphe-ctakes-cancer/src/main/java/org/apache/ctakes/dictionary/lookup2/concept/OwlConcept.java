package org.apache.ctakes.dictionary.lookup2.concept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlParserUtil;
import org.apache.ctakes.dictionary.lookup2.util.SemanticUtil;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
final public class OwlConcept implements Concept {

   static private final Logger LOGGER = Logger.getLogger( "OwlConcept" );

   static public final String URI_CODING_SCHEME = "OWL_URI";

   final private IClass _iClass;
   final private int _hashcode;

   /**
    * @param iClass -
    */
   public OwlConcept( final IClass iClass ) {
      _iClass = iClass;
      _hashcode = iClass.hashCode();
   }

   /**
    * @return the IClass within the Ontology
    */
   public IClass getIClass() {
      return _iClass;
   }

   public java.net.URI getUri() {
      return OwlParserUtil.getUri( _iClass );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getCui() {
      return OwlParserUtil.getCui( _iClass );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getPreferredText() {
      return PREFERRED_TERM_UNKNOWN;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Collection<String> getCodeNames() {
      return Arrays.asList( Concept.TUI, URI_CODING_SCHEME );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Collection<String> getCodes( final String codeType ) {
      if ( Concept.TUI.equals( codeType ) ) {
         return Collections.singletonList( OwlParserUtil.getTui( _iClass ) );
      } else if ( URI_CODING_SCHEME.equals( codeType ) ) {
         return Collections.singletonList( getUri().toASCIIString() );
      }
      return Collections.emptyList();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Collection<Integer> getCtakesSemantics() {
      final String tui = OwlParserUtil.getTui( _iClass.getConcept() );
      final Integer semanticGroupId = SemanticUtil.getTuiSemanticGroupId( tui );
      if ( semanticGroupId == null ) {
         return Collections.singletonList( CONST.NE_TYPE_ID_UNKNOWN );
      }
      return Collections.singletonList( semanticGroupId );
   }

   /**
    * Always return false, otherwise it is disregarded as useless
    * {@inheritDoc}
    */
   @Override
   public boolean isEmpty() {
      return false;
   }


   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals( final Object value ) {
      return value instanceof OwlConcept && getIClass().equals( ((OwlConcept)value).getIClass() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode() {
      return _hashcode;
   }

}
