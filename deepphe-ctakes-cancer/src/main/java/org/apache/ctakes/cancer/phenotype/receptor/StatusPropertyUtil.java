package org.apache.ctakes.cancer.phenotype.receptor;


import org.apache.ctakes.cancer.phenotype.property.AbstractPropertyUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;

/**
 * Singleton class with Utilities to interact with neoplasm Receptor Status property annotations, mostly by uri.
 *
 * should be used to:
 * <ul>
 * test that an annotation is of the desired property {@link #isCorrectProperty(IdentifiedAnnotation)}
 * get the property type uri from text {@link #getTypeUri(String)}
 * get the property value uri from text {@link #getValueUri(String)}
 *</ul>
 *
 * In addition there are static methods to:
 * <ul>
 * get the parent uri of the tnm property types {@link #getParentUri()}
 * </ul>
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/6/2015
 */
final public class StatusPropertyUtil extends AbstractPropertyUtil<StatusType, StatusValue> {

   static private final Logger LOGGER = Logger.getLogger( "StatusPropertyUtil" );

   static private class SingletonHolder {
      static private StatusPropertyUtil INSTANCE = new StatusPropertyUtil();
   }

   static public StatusPropertyUtil getInstance() {
      return SingletonHolder.INSTANCE;
   }


   private StatusPropertyUtil() {
      super( "Receptor Status" );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean isCorrectProperty( final IdentifiedAnnotation annotation ) {
      return isCorrectProperty( annotation, StatusType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected StatusValue getUriValue( final String uri ) {
      return StatusValue.getUriValue( uri );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTypeUri( final String typeText ) {
      return getTypeUri( typeText, StatusType.values() );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getValueUri( final String valueText ) {
      return getValueUri( valueText, StatusValue.values() );
   }


   /**
    * {@inheritDoc}
    */
   @Override
   protected StatusValue getUnknownValue() {
      return StatusValue.UNKNOWN;
   }

   /**
    * @return the uri acting as parent to all individual receptor status property uris
    */
   static public String getParentUri() {
      return Status.RECEPTOR_STATUS_URI;
   }

//
//
//
//
//
//
//
//
//   /**
//    * @param annotation candidate Receptor Status annotation
//    * @return true if the annotation is a Receptor Status according to URI match
//    */
//   static public boolean isReceptorStatus( final IdentifiedAnnotation annotation ) {
//      final Collection<String> uris = OwlOntologyConceptUtil.getUris( annotation );
//      for ( String uri : uris ) {
//         for ( StatusType type : StatusType.values() ) {
//            if ( uri.equals( type.getUri() ) ) {
//               return true;
//            }
//         }
//      }
//      return false;
//   }
//
//   /**
//    * @param jcas           -
//    * @param receptorStatus should be a receptor status annotation
//    * @return the StatusValue for the given annotation, or StatusValue#UNKNOWN if none exists,
//    * or null if that annotation is not a Receptor Status
//    */
//   static public StatusValue getStatusValue( final JCas jcas, final IdentifiedAnnotation receptorStatus ) {
//      if ( !isReceptorStatus( receptorStatus ) ) {
//         LOGGER.warn( receptorStatus.getCoveredText() + " is not a Receptor Status annotation" );
//         return null;
//      }
//      final Collection<DegreeOfTextRelation> degrees = JCasUtil.select( jcas, DegreeOfTextRelation.class );
//      if ( degrees == null || degrees.isEmpty() ) {
//         return StatusValue.UNKNOWN;
//      }
//      for ( DegreeOfTextRelation degree : degrees ) {
//         final Annotation argument1 = degree.getArg1().getArgument();
//         if ( !argument1.equals( receptorStatus ) ) {
//            continue;
//         }
//         final Annotation argument2 = degree.getArg2().getArgument();
//         final Collection<String> uris = OwlOntologyConceptUtil.getUris( (IdentifiedAnnotation)argument2 );
//         for ( String uri : uris ) {
//            final StatusValue statusValue = StatusValue.getUriValue( uri );
//            if ( statusValue != StatusValue.UNKNOWN ) {
//               return statusValue;
//            }
//         }
//      }
//      return StatusValue.UNKNOWN;
//   }
//
//
//   /**
//    * Create the receptor status type as a sign / symptom and add it to the cas,
//    * create the receptor status value as a modifier and add it to the cas,
//    * create the receptor type to value tie as a DegreeOfTextRelation
//    *
//    * @param jcas             -
//    * @param typeStartOffset  -
//    * @param typeEndOffset    -
//    * @param statusType       -
//    * @param valueStartOffset -
//    * @param valueEndOffset   -
//    * @param statusValue      -
//    * @return the receptor status type as a sign / symptom
//    */
//   static public SignSymptomMention createFullReceptorStatusMention( final JCas jcas,
//                                                                     final int typeStartOffset,
//                                                                     final int typeEndOffset,
//                                                                     final StatusType statusType,
//                                                                     final int valueStartOffset,
//                                                                     final int valueEndOffset,
//                                                                     final StatusValue statusValue ) {
//      final SpannedStatusType type = new SpannedStatusType( statusType, typeStartOffset, typeEndOffset );
//      final SpannedStatusValue value = new SpannedStatusValue( statusValue, valueStartOffset, valueEndOffset );
//      final ReceptorStatus receptorStatus = new ReceptorStatus( type, value );
//      return createFullReceptorStatusMention( jcas, 0, receptorStatus );
//   }
//
//   /**
//    * Create the receptor status type as a sign / symptom and add it to the cas,
//    * create the receptor status value as a modifier and add it to the cas,
//    * create the receptor type to value tie as a DegreeOfTextRelation
//    *
//    * @param jcas              -
//    * @param windowStartOffset character offset of window containing the receptor status
//    * @param receptorStatus    -
//    * @return the receptor status type as a sign / symptom
//    */
//   static public SignSymptomMention createFullReceptorStatusMention( final JCas jcas,
//                                                                     final int windowStartOffset,
//                                                                     final ReceptorStatus receptorStatus ) {
//      final SignSymptomMention typeMention = createReceptorTypeMention( jcas, windowStartOffset, receptorStatus );
//      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, receptorStatus );
//      createEventMentionDegree( jcas, typeMention, valueModifier );
//      return typeMention;
//   }
//
//   /**
//    * Create a sign/symptom and add it to the cas
//    *
//    * @param jcas              -
//    * @param windowStartOffset character offset of window containing the receptor status
//    * @param receptorStatus    -
//    * @return receptor status type as a sign / symptom
//    */
//   static private SignSymptomMention createReceptorTypeMention( final JCas jcas,
//                                                                final int windowStartOffset,
//                                                                final ReceptorStatus receptorStatus ) {
//      final SpannedStatusType spannedType = receptorStatus.getStatusType();
//      final SignSymptomMention typeMention = new SignSymptomMention( jcas,
//            windowStartOffset + spannedType.getStartOffset(),
//            windowStartOffset + spannedType.getEndOffset() );
//      typeMention.setTypeID( NE_TYPE_ID_FINDING );
//      final SpannedStatusValue spannedValue = receptorStatus.getStatusValue();
//      final StatusValue value = spannedValue.getStatusValue();
//      final StatusType type = spannedType.getStatusType();
//      // Main Umls Concept
//      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
//      umlsConcept.setCui( type.getCui( value ) );
//      umlsConcept.setTui( type.getTui() );
//      umlsConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
//      umlsConcept.setCode( type.getUri() );
//      umlsConcept.setPreferredText( type.getTitle() );
//      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
//      ontologyConcepts.set( 0, umlsConcept );
//      typeMention.setOntologyConceptArr( ontologyConcepts );
//      typeMention.addToIndexes();
//      return typeMention;
//   }
//
//   /**
//    * Create a modifier and add it to the cas
//    *
//    * @param jcas              -
//    * @param windowStartOffset character offset of window containing the receptor status
//    * @param receptorStatus    -
//    * @return the modifier representing the value of the receptor status
//    */
//   static private Modifier createValueModifier( final JCas jcas,
//                                                final int windowStartOffset,
//                                                final ReceptorStatus receptorStatus ) {
//      final SpannedStatusValue spannedValue = receptorStatus.getStatusValue();
//      final Modifier valueModifier = new Modifier( jcas,
//            windowStartOffset + spannedValue.getStartOffset(),
//            windowStartOffset + spannedValue.getEndOffset() );
//      valueModifier.setTypeID( MODIFIER_TYPE_ID_SEVERITY_CLASS );
//      final StatusValue value = spannedValue.getStatusValue();
////      receptorStatusModifier.setBooleanValue( SomeFeature, statusValue.getBooleanValue() );
////      receptorStatusAnnotation.setName( statusType.name() );
////      receptorStatusAnnotation.setDescription( statusType.getTitle() );
//      // Value uri concept
//      final OntologyConcept valueConcept = new OntologyConcept( jcas );
//      valueConcept.setCodingScheme( OwlConcept.URI_CODING_SCHEME );
//      valueConcept.setCode( value.getUri() );
//      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
//      ontologyConcepts.set( 0, valueConcept );
//      valueModifier.setOntologyConceptArr( ontologyConcepts );
//      valueModifier.addToIndexes();
//      return valueModifier;
//   }
//
//   /**
//    * Create the degree of relation and add is to the cas
//    *
//    * @param jCas        -
//    * @param statusType  -
//    * @param statusValue -
//    */
//   static private void createEventMentionDegree( final JCas jCas,
//                                              final SignSymptomMention statusType,
//                                              final Modifier statusValue ) {
//      final RelationArgument typeArgument = new RelationArgument( jCas );
//      typeArgument.setArgument( statusType );
//      typeArgument.setRole( "Argument" );
//      typeArgument.addToIndexes();
//      final RelationArgument valueArgument = new RelationArgument( jCas );
//      valueArgument.setArgument( statusValue );
//      valueArgument.setRole( "Related_to" );
//      valueArgument.addToIndexes();
//      final DegreeOfTextRelation degreeOf = new DegreeOfTextRelation( jCas );
//      degreeOf.setArg1( typeArgument );
//      degreeOf.setArg2( valueArgument );
//      degreeOf.setCategory( "Degree_of" );
//      degreeOf.addToIndexes();
//   }


}
