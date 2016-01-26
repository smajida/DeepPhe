package org.apache.ctakes.cancer.receptor;


import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.DegreeOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.Modifier;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.log4j.Logger;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.Collection;

import static org.apache.ctakes.typesystem.type.constants.CONST.MODIFIER_TYPE_ID_SEVERITY_CLASS;
import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/6/2015
 */
final public class ReceptorStatusUtil {

   static private final Logger LOGGER = Logger.getLogger( "ReceptorStatusFactory" );

   private ReceptorStatusUtil() {
   }


   /**
    * @param annotation candidate Receptor Status annotation
    * @return true if the annotation is a Receptor Status according to URI match
    */
   static public boolean isReceptorStatus( final IdentifiedAnnotation annotation ) {
      final Collection<String> uris = OwlOntologyConceptUtil.getUris( annotation );
      for ( String uri : uris ) {
         for ( StatusType type : StatusType.values() ) {
            if ( uri.equals( type.getUri() ) ) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * @param jcas           -
    * @param receptorStatus should be a receptor status annotation
    * @return the StatusValue for the given annotation, or StatusValue#UNKNOWN if none exists,
    * or null if that annotation is not a Receptor Status
    */
   static public StatusValue getStatusValue( final JCas jcas, final IdentifiedAnnotation receptorStatus ) {
      if ( !isReceptorStatus( receptorStatus ) ) {
         LOGGER.warn( receptorStatus.getCoveredText() + " is not a Receptor Status annotation" );
         return null;
      }
      final Collection<DegreeOfTextRelation> degrees = JCasUtil.select( jcas, DegreeOfTextRelation.class );
      if ( degrees == null || degrees.isEmpty() ) {
         return StatusValue.UNKNOWN;
      }
      for ( DegreeOfTextRelation degree : degrees ) {
         final Annotation argument1 = degree.getArg1().getArgument();
         if ( !argument1.equals( receptorStatus ) ) {
            continue;
         }
         final Annotation argument2 = degree.getArg2().getArgument();
         final Collection<String> uris = OwlOntologyConceptUtil.getUris( (IdentifiedAnnotation)argument2 );
         for ( String uri : uris ) {
            final StatusValue statusValue = StatusValue.getUriStatusValue( uri );
            if ( statusValue != StatusValue.UNKNOWN ) {
               return statusValue;
            }
         }
      }
      return StatusValue.UNKNOWN;
   }


   /**
    * Create the receptor status type as a sign / symptom and add it to the cas,
    * create the receptor status value as a modifier and add it to the cas,
    * create the receptor type to value tie as a DegreeOfTextRelation
    *
    * @param jcas             -
    * @param typeStartOffset  -
    * @param typeEndOffset    -
    * @param statusType       -
    * @param valueStartOffset -
    * @param valueEndOffset   -
    * @param statusValue      -
    * @return the receptor status type as a sign / symptom
    */
   static public SignSymptomMention createFullReceptorStatusMention( final JCas jcas,
                                                                     final int typeStartOffset,
                                                                     final int typeEndOffset,
                                                                     final StatusType statusType,
                                                                     final int valueStartOffset,
                                                                     final int valueEndOffset,
                                                                     final StatusValue statusValue ) {
      final SpannedStatusType type = new SpannedStatusType( statusType, typeStartOffset, typeEndOffset );
      final SpannedStatusValue value = new SpannedStatusValue( statusValue, valueStartOffset, valueEndOffset );
      final ReceptorStatus receptorStatus = new ReceptorStatus( type, value );
      return createFullReceptorStatusMention( jcas, 0, receptorStatus );
   }

   /**
    * Create the receptor status type as a sign / symptom and add it to the cas,
    * create the receptor status value as a modifier and add it to the cas,
    * create the receptor type to value tie as a DegreeOfTextRelation
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param receptorStatus    -
    * @return the receptor status type as a sign / symptom
    */
   static public SignSymptomMention createFullReceptorStatusMention( final JCas jcas,
                                                                     final int windowStartOffset,
                                                                     final ReceptorStatus receptorStatus ) {
      final SignSymptomMention typeMention = createReceptorTypeMention( jcas, windowStartOffset, receptorStatus );
      final Modifier valueModifier = createValueModifier( jcas, windowStartOffset, receptorStatus );
//      createTypeValueDegree( jcas, typeMention, valueModifier );
      return typeMention;
   }

   /**
    * Create a sign/symptom and add it to the cas
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param receptorStatus    -
    * @return receptor status type as a sign / symptom
    */
   static private SignSymptomMention createReceptorTypeMention( final JCas jcas,
                                                                final int windowStartOffset,
                                                                final ReceptorStatus receptorStatus ) {
      final SpannedStatusType spannedType = receptorStatus.getStatusType();
      final SignSymptomMention typeMention = new SignSymptomMention( jcas,
            windowStartOffset + spannedType.getStartOffset(),
            windowStartOffset + spannedType.getEndOffset() );
      typeMention.setTypeID( NE_TYPE_ID_FINDING );
      final SpannedStatusValue spannedValue = receptorStatus.getStatusValue();
      final StatusValue value = spannedValue.getStatusValue();
      final StatusType type = spannedType.getStatusType();
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
    * Create a modifier and add it to the cas
    *
    * @param jcas              -
    * @param windowStartOffset character offset of window containing the receptor status
    * @param receptorStatus    -
    * @return the modifier representing the value of the receptor status
    */
   static private Modifier createValueModifier( final JCas jcas,
                                                final int windowStartOffset,
                                                final ReceptorStatus receptorStatus ) {
      final SpannedStatusValue spannedValue = receptorStatus.getStatusValue();
      final Modifier valueModifier = new Modifier( jcas,
            windowStartOffset + spannedValue.getStartOffset(),
            windowStartOffset + spannedValue.getEndOffset() );
      valueModifier.setTypeID( MODIFIER_TYPE_ID_SEVERITY_CLASS );
      final StatusValue value = spannedValue.getStatusValue();
//      receptorStatusModifier.setBooleanValue( SomeFeature, statusValue.getBooleanValue() );
//      receptorStatusAnnotation.setName( statusType.name() );
//      receptorStatusAnnotation.setDescription( statusType.getTitle() );
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
    * @param jCas        -
    * @param statusType  -
    * @param statusValue -
    */
   static private void createTypeValueDegree( final JCas jCas,
                                              final SignSymptomMention statusType,
                                              final Modifier statusValue ) {
      final RelationArgument typeArgument = new RelationArgument( jCas );
      typeArgument.setArgument( statusType );
      typeArgument.setRole( "Argument" );
      typeArgument.addToIndexes();
      final RelationArgument valueArgument = new RelationArgument( jCas );
      valueArgument.setArgument( statusValue );
      valueArgument.setRole( "Related_to" );
      valueArgument.addToIndexes();
      final DegreeOfTextRelation degreeOf = new DegreeOfTextRelation( jCas );
      degreeOf.setArg1( typeArgument );
      degreeOf.setArg2( valueArgument );
      degreeOf.setCategory( "Degree_of" );
      degreeOf.addToIndexes();
   }


}
