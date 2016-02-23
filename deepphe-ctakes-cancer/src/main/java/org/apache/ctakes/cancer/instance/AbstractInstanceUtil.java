package org.apache.ctakes.cancer.instance;

import org.apache.ctakes.cancer.property.*;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.DegreeOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import java.util.Collections;
import java.util.logging.Logger;

import static org.apache.ctakes.typesystem.type.constants.CONST.MODIFIER_TYPE_ID_SEVERITY_CLASS;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/17/2016
 */
abstract public class AbstractInstanceUtil<T extends Type, V extends Value, E extends EventMention> {

   static private final Logger LOGGER = Logger.getLogger( "AbstractInstanceUtil" );

   private final String _instanceTypeName;

   public AbstractInstanceUtil( final String instanceTypeName ) {
      _instanceTypeName = instanceTypeName;
   }

   /**
    * @return the name of the type of instance, e.g. "TNM", "Receptor Status", "Stage"
    */
   final public String getInstanceTypeName() {
      return _instanceTypeName;
   }

   /**
    * Create the type as an event mention and add it to the cas,
    * create the value as a modifier and add it to the cas,
    * create the type to value tie as a DegreeOfTextRelation
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param spannedProperty   -
    * @return the property as a event mention
    */
   public E createInstance( final JCas jcas,
                            final int windowStartOffset,
                            final SpannedProperty<T, V> spannedProperty ) {
      return createInstance( jcas, windowStartOffset, spannedProperty, Collections.emptyList() );
   }

   /**
    * Create the type as an event mention and add it to the cas,
    * create the value as a modifier and add it to the cas,
    * create the type to value tie as a DegreeOfTextRelation
    * Tie the type as an event mention to the closest neoplasm
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param spannedProperty   -
    * @param neoplasms         nearby neoplasms
    * @return the property as a event mention
    */
   abstract public E createInstance( final JCas jcas,
                                     final int windowStartOffset,
                                     final SpannedProperty<T, V> spannedProperty,
                                     final Iterable<IdentifiedAnnotation> neoplasms );

   /**
    * Create a sign/symptom and add it to the cas
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the property
    * @param spannedProperty   -
    * @return the property as a event mention
    */
   final protected E createEventMention( final JCas jcas,
                                         final int windowStartOffset,
                                         final SpannedProperty<T, V> spannedProperty ) {
      final SpannedType<T> spannedType = spannedProperty.getSpannedType();
      final E typeMention = createEventMention( jcas,
            windowStartOffset + spannedType.getStartOffset(),
            windowStartOffset + spannedType.getEndOffset() );
      final SpannedValue<V> spannedValue = spannedProperty.getSpannedValue();
      final V value = spannedValue.getValue();
      final T type = spannedType.getType();
      // Main Umls Concept
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( type.getCui( value ) );
      umlsConcept.setTui( type.getTui() );
      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      umlsConcept.setCode( type.getUri() );
      umlsConcept.setPreferredText( type.getTitle() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      typeMention.setOntologyConceptArr( ontologyConcepts );
      typeMention.addToIndexes();
      return typeMention;
   }

   /**
    * @param jcas        -
    * @param startOffset -
    * @param endOffset   -
    * @return EventMention of the proper type at the given text span
    */
   abstract protected E createEventMention( final JCas jcas, final int startOffset, final int endOffset );

   /**
    * Create a modifier and add it to the cas
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the property
    * @param spannedProperty   -
    * @return the modifier representing the value of the property
    */
   final protected Modifier createValueModifier( final JCas jcas,
                                                 final int windowStartOffset,
                                                 final SpannedProperty<T, V> spannedProperty ) {
      final SpannedValue<V> spannedValue = spannedProperty.getSpannedValue();
      final Modifier valueModifier = new Modifier( jcas,
            windowStartOffset + spannedValue.getStartOffset(),
            windowStartOffset + spannedValue.getEndOffset() );
      valueModifier.setTypeID( MODIFIER_TYPE_ID_SEVERITY_CLASS );
      final V value = spannedValue.getValue();
      // Value uri concept
      final OntologyConcept valueConcept = new OntologyConcept( jcas );
      valueConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      valueConcept.setCode( value.getUri() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, valueConcept );
      valueModifier.setOntologyConceptArr( ontologyConcepts );
      valueModifier.addToIndexes();
      return valueModifier;
   }

   /**
    * Create the degree of relation and add is to the cas
    *
    * @param jCas         -
    * @param eventMention -
    * @param modifier     -
    */
   final protected void createEventMentionDegree( final JCas jCas,
                                                  final E eventMention,
                                                  final Modifier modifier ) {
      final RelationArgument typeArgument = new RelationArgument( jCas );
      typeArgument.setArgument( eventMention );
      typeArgument.setRole( "Argument" );
      typeArgument.addToIndexes();
      final RelationArgument valueArgument = new RelationArgument( jCas );
      valueArgument.setArgument( modifier );
      valueArgument.setRole( "Related_to" );
      valueArgument.addToIndexes();
      final DegreeOfTextRelation degreeOf = new DegreeOfTextRelation( jCas );
      degreeOf.setArg1( typeArgument );
      degreeOf.setArg2( valueArgument );
      degreeOf.setCategory( "Degree_of" );
      degreeOf.addToIndexes();
   }

   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param spannedProperty   -
    * @param eventMention      property as an event mention
    * @param neoplasms         nearby neoplasms
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final int windowStartOffset,
                                                    final SpannedProperty<T, V> spannedProperty,
                                                    final E eventMention,
                                                    final Iterable<IdentifiedAnnotation> neoplasms ) {
      final IdentifiedAnnotation closestNeoplasm
            = FinderUtil.getClosestAnnotation( windowStartOffset + spannedProperty.getStartOffset(),
            windowStartOffset + spannedProperty.getEndOffset(), neoplasms );
      if ( closestNeoplasm != null ) {
         final String relationName = spannedProperty.getSpannedType().getType().getTitle().replace( ' ', '_' ) + "_of";
         createEventMentionNeoplasm( jCas, eventMention, closestNeoplasm, relationName );
      }
   }

   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas         -
    * @param eventMention property as an event mention
    * @param neoplasm     the associated neoplasm
    * @param relationName e.g. tnm_of
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final E eventMention,
                                                    final IdentifiedAnnotation neoplasm,
                                                    final String relationName ) {
      if ( eventMention == null ) {
         LOGGER.info( "No argument for " + relationName
                      + ((neoplasm != null) ? " of " + neoplasm.getCoveredText() : "") );
         return;
      }
      if ( neoplasm == null ) {
         LOGGER.info( "No Neoplasm for " + relationName + " " + eventMention.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument eventArgument = new RelationArgument( jCas );
      eventArgument.setArgument( eventMention );
      eventArgument.setRole( "Argument" );
      eventArgument.addToIndexes();
      final RelationArgument neoplasmArgument = new RelationArgument( jCas );
      neoplasmArgument.setArgument( neoplasm );
      neoplasmArgument.setRole( "Related_to" );
      neoplasmArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( eventArgument );
      neoplasmRelation.setArg2( neoplasmArgument );
      neoplasmRelation.setCategory( relationName );
      neoplasmRelation.addToIndexes();
   }


}
