package org.apache.ctakes.cancer.phenotype;

import org.apache.ctakes.cancer.owl.UriAnnotationFactory;
import org.apache.ctakes.cancer.phenotype.property.*;
import org.apache.ctakes.cancer.phenotype.receptor.StatusPhenotypeFactory;
import org.apache.ctakes.cancer.phenotype.stage.StagePhenotypeFactory;
import org.apache.ctakes.cancer.phenotype.tnm.TnmPhenotypeFactory;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.DegreeOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.IndicatesTextRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.ctakes.typesystem.type.textsem.SeverityModifier;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import java.util.Collections;

import static org.apache.ctakes.typesystem.type.constants.CONST.MODIFIER_TYPE_ID_SEVERITY_CLASS;

/**
 * Abstract for classes that should be used to create full neoplasm property instances.
 * An instance is defined as the collection of all property types and values associated with a single neoplasm.
 * Children of this factory exist for each property type, so full instance creation requires use of each.
 * {@link TnmPhenotypeFactory}
 * {@link StatusPhenotypeFactory}
 * {@link StagePhenotypeFactory}
 *
 *
 * Use of any {@code createPhenotype()} method will create:
 * <ul>
 * appropriate property type annotations
 * neoplasm relations between the property type annotations and the nearest provided neoplasm in the text
 * property value annotations
 * degree-of relations between the property type annotations and the appropriate value annotations
 * test-for relations between property type annotations and the nearest provided test in the text
 * </ul>
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/17/2016
 */
abstract public class AbstractPhenotypeFactory<T extends Type, V extends Value, E extends IdentifiedAnnotation> {

   // TODO add <..., P extends Test, ...>

   static private final Logger LOGGER = Logger.getLogger( "AbstractPhenotypeFactory" );

   private final String _phenotypeName;

   public AbstractPhenotypeFactory( final String phenotypeName ) {
      _phenotypeName = phenotypeName;
   }

   /**
    * @return the name of the type of instance, e.g. "TNM", "Receptor Status", "Stage"
    */
   final public String getPhenotypeName() {
      return _phenotypeName;
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
   final public E createPhenotype( final JCas jcas,
                                   final int windowStartOffset,
                                   final SpannedProperty<T, V> spannedProperty ) {
      return createPhenotype( jcas, windowStartOffset, spannedProperty, Collections.emptyList() );
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
   final public E createPhenotype( final JCas jcas,
                                   final int windowStartOffset,
                                   final SpannedProperty<T, V> spannedProperty,
                                   final Iterable<IdentifiedAnnotation> neoplasms ) {
      return createPhenotype( jcas, windowStartOffset, spannedProperty, neoplasms, null );
   }

   /**
    * Create the type as an event mention and add it to the cas,
    * create the value as a modifier and add it to the cas,
    * create the type to value tie as a DegreeOfTextRelation
    * Tie the type as an event mention to the closest neoplasm
    * Tie the type to the closest diagnostic test
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param spannedProperty   -
    * @param neoplasms         nearby neoplasms
    * @param diagnosticTests   nearby diagnostic tests
    * @return the property as a event mention
    */
   abstract public E createPhenotype( final JCas jcas,
                                      final int windowStartOffset,
                                      final SpannedProperty<T, V> spannedProperty,
                                      final Iterable<IdentifiedAnnotation> neoplasms,
                                      final Iterable<IdentifiedAnnotation> diagnosticTests );


   /**
    * Create the type as an event mention and add it to the cas,
    * create the value as a modifier and add it to the cas,
    * create the type to value tie as a DegreeOfTextRelation
    * Tie the type as an event mention to the closest neoplasm
    * Tie the type to the closest diagnostic test
    * @param jcas            -
    * @param typeUri         -
    * @param typeBegin       -
    * @param typeEnd         -
    * @param valueUri        -
    * @param valueBegin      -
    * @param valueEnd        -
    * @param neoplasms       -
    * @param diagnosticTests -
    * @return -
    */
   final public E createPhenotype( final JCas jcas,
                                   final String typeUri, final int typeBegin, final int typeEnd,
                                   final String valueUri, final int valueBegin, final int valueEnd,
                                   final Iterable<IdentifiedAnnotation> neoplasms,
                                   final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final E eventMention = createSpanEventMention( jcas, typeBegin, typeEnd );
      final UmlsConcept umlsConcept = UriAnnotationFactory.createUmlsConcept( jcas, typeUri );
      final FSArray conceptArray = new FSArray( jcas, 1 );
      conceptArray.set( 0, umlsConcept );
      eventMention.setOntologyConceptArr( conceptArray );
      final Modifier valueModifier = createValueModifier( jcas, valueUri, valueBegin, valueEnd );
      createEventMentionDegree( jcas, eventMention, valueModifier );
      createEventMentionNeoplasm( jcas, typeBegin, typeEnd, typeUri, eventMention, neoplasms );
      createEventMentionIndicator( jcas, typeBegin, typeEnd, eventMention, diagnosticTests );
      return eventMention;
   }

   /**
    * Create an event mention based upon spanned property type and add it to the cas
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the property
    * @param spannedProperty   -
    * @return the property as a event mention
    */
   final protected E createTypeEventMention( final JCas jcas,
                                             final int windowStartOffset,
                                             final SpannedProperty<T, V> spannedProperty ) {
      final SpannedType<T> spannedType = spannedProperty.getSpannedType();
      final E typeMention = createSpanEventMention( jcas,
            windowStartOffset + spannedType.getStartOffset(),
            windowStartOffset + spannedType.getEndOffset() );
      final SpannedValue<V> spannedValue = spannedProperty.getSpannedValue();
      final V value = spannedValue.getValue();
      final T type = spannedType.getType();
      // Main Umls Concept
      final String cui = type.getCui( value );
      final String tui = type.getTui();
      final String title = type.getTitle();
      UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( cui == null ? "" : cui );
      umlsConcept.setTui( tui == null ? "" : tui );
      umlsConcept.setPreferredText( title == null ? "" : title );
      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      umlsConcept.setCode( type.getUri() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      typeMention.setOntologyConceptArr( ontologyConcepts );
      typeMention.addToIndexes();
      return typeMention;
   }

   /**
    * Create a sign/symptom and add it to the cas
    *
    * @param jcas              -
    * @param eventUri -
    * @param windowStartOffset -
    * @param spannedProperty   -
    * @return the property as a event mention
    */
   final protected E createPropertyEventMention( final JCas jcas, final String eventUri,
                                                 final int windowStartOffset,
                                                 final SpannedProperty<T, V> spannedProperty ) {

      final E eventMention = createSpanEventMention( jcas,
            windowStartOffset + spannedProperty.getStartOffset(),
            windowStartOffset + spannedProperty.getEndOffset() );
      // Main Umls Concept
      final UmlsConcept umlsConcept = UriAnnotationFactory.createUmlsConcept( jcas, eventUri );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      eventMention.setOntologyConceptArr( ontologyConcepts );
      eventMention.addToIndexes();
      return eventMention;
   }

   /**
    * @param jcas        -
    * @param startOffset -
    * @param endOffset   -
    * @return EventMention of the proper type at the given text span
    */
   abstract protected E createSpanEventMention( final JCas jcas, final int startOffset, final int endOffset );

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
      final SeverityModifier valueModifier = new SeverityModifier( jcas,
            windowStartOffset + spannedValue.getStartOffset(),
            windowStartOffset + spannedValue.getEndOffset() );
      valueModifier.setTypeID( MODIFIER_TYPE_ID_SEVERITY_CLASS );
      final V value = spannedValue.getValue();
      // Value uri concept
      final String cui = value.getCui();
      final String tui = value.getTui();
      final String title = value.getTitle();
      UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( cui == null ? "" : cui );
      umlsConcept.setTui( tui == null ? "" : tui );
      umlsConcept.setPreferredText( title == null ? "" : title );
      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
      umlsConcept.setCode( value.getUri() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      valueModifier.setOntologyConceptArr( ontologyConcepts );
      valueModifier.addToIndexes();
      return valueModifier;
   }

   /**
    * Create a modifier and add it to the cas
    *
    * @param jcas        -
    * @param valueUri    uri for the value modifier.  Cui, Tui and Preferred text will be based upon uri iclass
    * @param beginOffset -
    * @param endOffset   -
    * @return the modifier representing the value of the property
    */
   static public Modifier createValueModifier( final JCas jcas, final String valueUri,
                                               final int beginOffset, final int endOffset ) {
      final SeverityModifier valueModifier = new SeverityModifier( jcas, beginOffset, endOffset );
      valueModifier.setTypeID( MODIFIER_TYPE_ID_SEVERITY_CLASS );
      // Value uri concept
      final UmlsConcept umlsConcept = UriAnnotationFactory.createUmlsConcept( jcas, valueUri );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      valueModifier.setOntologyConceptArr( ontologyConcepts );
      valueModifier.addToIndexes();
      return valueModifier;
   }

   /**
    * Create the degree of relation and add is to the cas
    * http://blulab.chpc.utah.edu/ontologies/v2/ConText.owl#Severity
    * http://blulab.chpc.utah.edu/ontologies/v2/ConText.owl#Degree
    *
    * @param jCas         -
    * @param eventMention -
    * @param modifier     -
    */
   final public void createEventMentionDegree( final JCas jCas,
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
    * @param windowStartOffset character offset of window containing the property
    * @param spannedProperty   -
    * @param eventMention      property as an event mention
    * @param neoplasms         nearby neoplasms
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final int windowStartOffset,
                                                    final SpannedProperty<T, V> spannedProperty,
                                                    final E eventMention,
                                                    final Iterable<IdentifiedAnnotation> neoplasms ) {
      createEventMentionNeoplasm( jCas,
            windowStartOffset + spannedProperty.getStartOffset(),
            windowStartOffset + spannedProperty.getEndOffset(),
            spannedProperty.getSpannedType().getType().getTitle(),
            eventMention, neoplasms );
   }

   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas         -
    * @param typeBegin    character offset of the property
    * @param typeEnd      -
    * @param typeTitle    -
    * @param eventMention property as an event mention
    * @param neoplasms    nearby neoplasms
    */
   final protected void createEventMentionNeoplasm( final JCas jCas,
                                                    final int typeBegin, final int typeEnd,
                                                    final String typeTitle,
                                                    final E eventMention,
                                                    final Iterable<IdentifiedAnnotation> neoplasms ) {
      final IdentifiedAnnotation closestNeoplasm
            = FinderUtil.getClosestAnnotation( typeBegin, typeEnd, neoplasms );
      if ( closestNeoplasm != null ) {
         final String relationName = typeTitle.replace( ' ', '_' ) + "_of";
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


   /**
    * Create a "test_indicates_property" relation
    *
    * @param jCas              -
    * @param windowStartOffset character offset of window containing the property
    * @param spannedProperty   -
    * @param eventMention      property as an event mention
    * @param diagnosticTests   nearby diagnostic tests
    */
   final protected void createEventMentionIndicator( final JCas jCas,
                                                     final int windowStartOffset,
                                                     final SpannedProperty<T, V> spannedProperty,
                                                     final E eventMention,
                                                     final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      if ( diagnosticTests == null ) {
         return;
      }
      createEventMentionIndicator( jCas, windowStartOffset + spannedProperty.getStartOffset(),
            windowStartOffset + spannedProperty.getEndOffset(), eventMention, diagnosticTests );
   }

   /**
    * Create a "test_indicates_property" relation to a neoplasm.
    *
    * @param jCas            -
    * @param typeBegin       character offset of the property
    * @param typeEnd         -
    * @param eventMention    property as an event mention
    * @param diagnosticTests nearby diagnostic tests
    */
   final public void createEventMentionIndicator( final JCas jCas,
                                                  final int typeBegin, final int typeEnd,
                                                  final E eventMention,
                                                  final Iterable<IdentifiedAnnotation> diagnosticTests ) {
      final IdentifiedAnnotation closestDiagnostic
            = FinderUtil.getClosestAnnotation( typeBegin, typeEnd, diagnosticTests );
      if ( closestDiagnostic != null ) {
         createEventMentionIndicator( jCas, closestDiagnostic, eventMention, "Diagnostic_Test_for" );
      }
   }


   /**
    * Create a "property_type_of" relation to a neoplasm.
    *
    * @param jCas           -
    * @param diagnosticTest the diagnostic test for the property
    * @param eventMention   property as an event mention
    * @param relationName   e.g. Diagnostic_Test_for
    */
   final protected void createEventMentionIndicator( final JCas jCas,
                                                     final IdentifiedAnnotation diagnosticTest,
                                                     final E eventMention,
                                                     final String relationName ) {
      if ( eventMention == null ) {
         LOGGER.info( "No argument for " + relationName
                      + ((diagnosticTest != null) ? " of " + diagnosticTest.getCoveredText() : "") );
         return;
      }
      if ( diagnosticTest == null ) {
         LOGGER.info( "No Test for " + relationName + " " + eventMention.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument diagnosticArgument = new RelationArgument( jCas );
      diagnosticArgument.setArgument( diagnosticTest );
      diagnosticArgument.setRole( "Argument" );
      diagnosticArgument.addToIndexes();
      final RelationArgument eventArgument = new RelationArgument( jCas );
      eventArgument.setArgument( eventMention );
      eventArgument.setRole( "Related_to" );
      eventArgument.addToIndexes();
      final IndicatesTextRelation indicatesRelation = new IndicatesTextRelation( jCas );
      indicatesRelation.setArg1( diagnosticArgument );
      indicatesRelation.setArg2( eventArgument );
      indicatesRelation.setCategory( relationName );
      indicatesRelation.addToIndexes();
   }


}
