package org.apache.ctakes.cancer.concept.instance;

import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.receptor.StatusPropertyUtil;
import org.apache.ctakes.cancer.phenotype.stage.StagePropertyUtil;
import org.apache.ctakes.cancer.phenotype.tnm.TnmPropertyUtil;
import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.core.util.OntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import javax.annotation.concurrent.Immutable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 5/19/2016
 */
@Immutable
final public class ToyAE extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "ToyAE" );


   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {

//      LOGGER.info( jcas.getDocumentText() );
      getAllNeoplasms( jcas ).forEach( n -> LOGGER.info( getNeoplasmDescription( n ) ) );

   }


   /**
    * @param jCas -
    * @return all neoplasms in the cas
    */
   static private Collection<ConceptInstance> getAllNeoplasms( final JCas jCas ) {
      final Collection<ConceptInstance> breastNeoplasms = new HashSet<>();
      NeoplasmRelationFactory.getNeoplasmUris().forEach(
            u -> breastNeoplasms.addAll( ConceptInstanceUtil.getBranchConceptInstances( jCas, u ) ) );
      return breastNeoplasms;
   }

   /**
    * @param jCas -
    * @return all neoplasms in the cas that have at least one associated phenotype
    */
   static private Collection<ConceptInstance> getPhenotypedNeoplasms( final JCas jCas ) {
      final String[] phenotype_uris = {
            StagePropertyUtil.getParentUri(),
            StatusPropertyUtil.getParentUri(),
            StatusPropertyUtil.getParentUri(),
            OwlConstants.BREAST_CANCER_OWL + "#Triple_Negative_Breast_Carcinoma",
            TnmPropertyUtil.getParentUri() };
      return getConceptInstanceStream( jCas, phenotype_uris )
            .map( ci -> ConceptInstanceUtil.getPhenotypeNeoplasms( jCas, ci ) )
            .flatMap( Collection::stream )
            .collect( Collectors.toSet() );
   }


   static private Stream<ConceptInstance> getConceptInstanceStream( final JCas jCas, final String... uris ) {
      return Arrays.stream( uris )
            .map( uri -> ConceptInstanceUtil.getBranchConceptInstances( jCas, uri ) )
            .flatMap( Collection::stream )
            .filter( ci -> getCuiValue( ci ) != null );
   }

   /**
    * We don't want modifiers OR branch parents that don't have any CUI.  This provides
    *
    * @param conceptInstance -
    * @return cui
    */
   static private String getCuiValue( final ConceptInstance conceptInstance ) {
      return conceptInstance == null ? null : getCuiValue( conceptInstance.getIdentifiedAnnotation() );
   }

   static private String getCuiValue( final IdentifiedAnnotation annotation ) {
      if ( annotation == null ) {
         return null;
      }
      final Collection<String> cuis = OntologyConceptUtil.getCuis( annotation );
      return cuis.isEmpty() ? null : cuis.stream().filter( c -> !c.isEmpty() ).findFirst().orElse( null );
   }


   static private String getNeoplasmDescription( final ConceptInstance neoplasm ) {
      final StringBuilder sb = new StringBuilder();
      sb.append( "\n" ).append( neoplasm.toString() ).append( "\n" );
      for ( ConceptInstance location : ConceptInstanceUtil.getLocations( neoplasm ) ) {
         sb.append( "\tConceptInstanceUtil.getLocations(^) " ).append( location.toString() ).append( "\n" );
         forEachText( sb, "QuadrantUris", ConceptInstanceUtil.getQuadrantUris( location ) );
         forEachText( sb, "BodySideUris", ConceptInstanceUtil.getBodySideUris( location ) );
         forEachText( sb, "ClockwiseUris", ConceptInstanceUtil.getClockwiseUris( location ) );
      }
      ConceptInstanceUtil.getNeoplasmT( neoplasm )
            .forEach( t -> forEachConcept( sb, "NeoplasmT", t ) );
      ConceptInstanceUtil.getNeoplasmN( neoplasm )
            .forEach( n -> forEachConcept( sb, "NeoplasmN", n ) );
      ConceptInstanceUtil.getNeoplasmM( neoplasm )
            .forEach( m -> forEachConcept( sb, "NeoplasmM", m ) );
      ConceptInstanceUtil.getNeoplasmStage( neoplasm )
            .forEach( s -> forEachConcept( sb, "NeoplasmStage", s ) );
      ConceptInstanceUtil.getNeoplasmReceptorStatus( neoplasm )
            .forEach( r -> forEachConcept( sb, "NeoplasmReceptorStatus", r ) );
      for ( ConceptInstance size : ConceptInstanceUtil.getNeoplasmSizes( neoplasm ) ) {
         sb.append( "\tConceptInstanceUtil.getNeoplasmSizes(^) " ).append( size.toString() ).append( "\n" );
//         ConceptInstanceUtil.getPropertyValues( size ).forEach( sb::append );
//         ConceptInstanceUtil.getDiagnosticTests( size ).forEach( sb::append );
      }

      return sb.toString();
   }

   static private void forEachConcept( final StringBuilder sb, final String getter,
                                       final ConceptInstance conceptInstance ) {
      sb.append( "\tConceptInstanceUtil.get" ).append( getter ).append( "(^) " )
            .append( conceptInstance.toString() ).append( "\n" );
      forEachText( sb, "PropertyValues", ConceptInstanceUtil.getPropertyValues( conceptInstance ) );
      forEachText( sb, "DiagnosticTests", ConceptInstanceUtil.getDiagnosticTests( conceptInstance ) );

   }

   static private void forEachText( final StringBuilder sb, final String getter,
                                    final Collection<? extends Object> collection ) {
      collection.forEach( u -> sb.append( "\t\tConceptInstanceUtil.get" )
            .append( getter ).append( "(^) " )
            .append( u ).append( "\n" ) );
   }


}
