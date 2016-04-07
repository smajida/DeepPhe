package org.apache.ctakes.core.cc;


import org.apache.ctakes.cancer.concept.instance.ConceptInstance;
import org.apache.ctakes.cancer.concept.instance.ConceptInstanceUtil;
import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.PhenotypeAnnotationUtil;
import org.apache.ctakes.cancer.phenotype.receptor.StatusPropertyUtil;
import org.apache.ctakes.cancer.phenotype.stage.StagePropertyUtil;
import org.apache.ctakes.cancer.phenotype.tnm.TnmPropertyUtil;
import org.apache.ctakes.core.util.DocumentIDAnnotationUtil;
import org.apache.ctakes.core.util.OntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.component.CasConsumer_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 3/22/2016
 */
public class SemevalBsvWriter extends CasConsumer_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "SemevalBsvWriter" );


   /**
    * Name of configuration parameter that must be set to the path of a directory into which the
    * output files will be written.
    */
   public static final String PARAM_OUTPUTDIR = "OutputDirectory";
   @ConfigurationParameter( name = PARAM_OUTPUTDIR,
         description = "Root output directory to write semeval pipe files",
         mandatory = true )
   private File _outputRootDir;


   /**
    * {@inheritDoc}
    */
   @Override
   public void initialize( final UimaContext context ) throws ResourceInitializationException {
      super.initialize( context );
      if ( !_outputRootDir.exists() ) {
         _outputRootDir.mkdirs();
      }
   }

   /**
    * Write the cas into an xmi output file named based upon the document id and located based upon the document id prefix.
    * {@inheritDoc}
    */
   @Override
   public void process( final CAS cas ) throws AnalysisEngineProcessException {
      JCas jcas;
      try {
         jcas = cas.getJCas();
      } catch ( CASException casE ) {
         throw new AnalysisEngineProcessException( casE );
      }
      final String fileName = DocumentIDAnnotationUtil.getDocumentIdForFile( jcas ) + ".pipe";
      try {
         writeFhirLikeBsv( jcas, getOutputDir( "CancerStage" ), fileName, StagePropertyUtil.getParentUri() );
         writeFhirLikeBsv( jcas, getOutputDir( "ReceptorStatus" ), fileName, StatusPropertyUtil.getParentUri() );
         writeFhirLikeTnmBsv( jcas, getOutputDir( "TnmClassification" ), fileName, TnmPropertyUtil.getParentUri() );
         writeFhirLikeDisBsv( jcas, getOutputDir( "Disorder" ), fileName,
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Neoplasm",
               OwlOntologyConceptUtil.CANCER_OWL + "#Cancer",
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Metastasis" );
      } catch ( IOException ioE ) {
         throw new AnalysisEngineProcessException( ioE );
      }
   }

   private File getOutputDir( final String phenotypeName ) {
      final File dir = new File( _outputRootDir, phenotypeName );
      dir.mkdirs();
      return dir;
   }

   static private void writeFhirLikeBsv( final JCas jCas, final File outputDir, final String fileName,
                                         final String... uris )
         throws IOException {
      final Collection<ConceptInstance> conceptInstances = Arrays.stream( uris )
            .map( uri -> ConceptInstanceUtil.getBranchConceptInstances( jCas, uri ) )
            .flatMap( Collection::stream )
            .collect( Collectors.toList() );
      writeBsv( conceptInstances, new File( outputDir, fileName ) );
   }

   static private void writeFhirLikeTnmBsv( final JCas jCas, final File outputDir, final String fileName,
                                            final String... uris )
         throws IOException {
      final Collection<ConceptInstance> conceptInstances = Arrays.stream( uris )
            .map( uri -> ConceptInstanceUtil.getBranchConceptInstances( jCas, uri ) )
            .flatMap( Collection::stream )
            .collect( Collectors.toList() );
      writeTnmBsv( conceptInstances, new File( outputDir, fileName ) );
   }


   static private void writeFhirLikeDisBsv( final JCas jCas, final File outputDir, final String fileName,
                                            final String... uris )
         throws IOException {
      final Collection<ConceptInstance> conceptInstances = Arrays.stream( uris )
            .map( uri -> ConceptInstanceUtil.getBranchConceptInstances( jCas, uri ) )
            .flatMap( Collection::stream )
            .filter( p -> !ConceptInstanceUtil.getNeoplasmAllPhenotypes( jCas, p ).isEmpty() )
            .collect( Collectors.toList() );
      writeBsv( conceptInstances, new File( outputDir, fileName ) );
   }

   /**
    * Serialize a CAS to a file in semeval BSV format
    *
    * @param conceptInstances fhirlike resources
    * @param bsvFile   output file
    * @throws IOException -
    */
   public static void writeBsv( final Collection<ConceptInstance> conceptInstances, final File bsvFile )
         throws IOException {
      final String sourceFilename = bsvFile.getName().replace( ".pipe", "" );
      try ( BufferedWriter writer = new BufferedWriter( new FileWriter( bsvFile ) ) ) {
         for ( ConceptInstance resource : conceptInstances ) {
            writer.write( sourceFilename + "|" );
            writer.write( createPhenotypeLine( resource ) );
            writer.write( "\n" );
         }
      } catch ( IOException ioE ) {
         LOGGER.error( ioE.getMessage() );
      }
   }

   public static void writeTnmBsv( final Collection<ConceptInstance> conceptInstances, final File bsvFile )
         throws IOException {
      final String sourceFilename = bsvFile.getName().replace( ".pipe", "" );
      try ( BufferedWriter writer = new BufferedWriter( new FileWriter( bsvFile ) ) ) {
         for ( ConceptInstance resource : conceptInstances ) {
            writer.write( sourceFilename + "|" );
            writer.write( createTnmPhenotypeLine( resource ) );
            writer.write( "\n" );
         }
      } catch ( IOException ioE ) {
         LOGGER.error( ioE.getMessage() );
      }
   }


   static private String createPhenotypeLine( final ConceptInstance conceptInstance ) {
      final StringBuilder sb = new StringBuilder();
      // DocName|Phenotype_Spans|CUI|
      sb.append( getSpannedCuiText( conceptInstance.getIdentifiedAnnotation(), "unmarked" ) );
      // Neg_value|Neg_span|
      sb.append( getAttributeTextYN( conceptInstance.isNegated() ) );
      // Subj_value|Subj_span|
      sb.append( getAttributeText( conceptInstance.getSubject(), "patient" ) );
      // Uncertain_value|Uncertain_span|
      sb.append( getAttributeTextYN( conceptInstance.isUncertain() ) );
      // Course_value|Course_span|
      sb.append( "unmarked|NULL|" );
      // Severity_value|Severity_span|
      sb.append( "unmarked|NULL|" );
      // Cond_value|Cond_span|
      sb.append( getAttributeTextTF( conceptInstance.isConditional() ) );
      // Generic_value|Generic_span|
      sb.append( getAttributeTextTF( conceptInstance.isHypothetical() ) );
      // Bodyloc_value|Bodyloc_span|
      sb.append( getCuiSpannedText( PhenotypeAnnotationUtil
            .getLocations( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // DocTimeRel|DocTimeRelSpan|
      sb.append( getAttributeText( conceptInstance.getDocTimeRel(), "unmarked" ) );
      // Value_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // Neoplasm_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeNeoplasms( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // AssociatedTest_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getDiagnosticTests( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      return sb.toString();
   }

   static private String createTnmPhenotypeLine( final ConceptInstance conceptInstance ) {
      final StringBuilder sb = new StringBuilder();
      // DocName|Phenotype_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // Neg_value|Neg_span|
      sb.append( getAttributeTextYN( conceptInstance.isNegated() ) );
      // Subj_value|Subj_span|
      sb.append( getAttributeText( conceptInstance.getSubject(), "patient" ) );
      // Uncertain_value|Uncertain_span|
      sb.append( getAttributeTextYN( conceptInstance.isUncertain() ) );
      // Course_value|Course_span|
      sb.append( "unmarked|NULL|" );
      // Severity_value|Severity_span|
      sb.append( "unmarked|NULL|" );
      // Cond_value|Cond_span|
      sb.append( getAttributeTextTF( conceptInstance.isConditional() ) );
      // Generic_value|Generic_span|
      sb.append( getAttributeTextTF( conceptInstance.isHypothetical() ) );
      // Bodyloc_value|Bodyloc_span|
      sb.append( getCuiSpannedText( PhenotypeAnnotationUtil
            .getLocations( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // DocTimeRel|DocTimeRelSpan|
      sb.append( getAttributeText( conceptInstance.getDocTimeRel(), "unmarked" ) );
      // Value_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // Neoplasm_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeNeoplasms( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // AssociatedTest_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getDiagnosticTests( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      return sb.toString();
   }

   static private String getSpannedCuiText( final Collection<IdentifiedAnnotation> annotations,
                                            final String defaultText ) {
      return getSpannedCuiText( annotations.stream().findFirst().orElse( null ), defaultText );
   }

   static private String getSpannedCuiText( final IdentifiedAnnotation annotation, final String defaultText ) {
      if ( annotation == null ) {
         return defaultText + "|NULL|";
      }
      final StringBuilder sb = new StringBuilder();
      sb.append( annotation.getBegin() ).append( '-' ).append( annotation.getEnd() ).append( '|' );
      final Collection<String> cuis = OntologyConceptUtil.getCuis( annotation );
      final String cui = cuis == null ? "NULL"
                                      : cuis.stream().filter( c -> !c.isEmpty() ).findFirst().orElse( "NULL" );
      sb.append( cui ).append( '|' );
      return sb.toString();
   }

   static private String getCuiSpannedText( final Collection<IdentifiedAnnotation> annotations,
                                            final String defaultText ) {
      if ( annotations == null || annotations.isEmpty() ) {
         return "NULL|" + defaultText + "|";
      }
      final IdentifiedAnnotation annotation = annotations.stream().findFirst().get();
      final StringBuilder sb = new StringBuilder();
      final Collection<String> cuis = OntologyConceptUtil.getCuis( annotation );
      final String cui = cuis == null ? "NULL"
                                      : cuis.stream().filter( c -> !c.isEmpty() ).findFirst().orElse( "NULL" );
      sb.append( cui ).append( '|' );
      sb.append( annotation.getBegin() ).append( '-' ).append( annotation.getEnd() ).append( '|' );
      return sb.toString();
   }

   static private String getAttributeTextYN( final boolean isAttributePositive ) {
      final String value = isAttributePositive ? "yes" : "no";
      return value + "|NULL|";
   }

   static private String getAttributeTextTF( final boolean isAttributePositive ) {
      final String value = isAttributePositive ? "true" : "false";
      return value + "|NULL|";
   }

   static private String getAttributeText( final String attributeValue, final String defaultText ) {
      final String value = attributeValue.isEmpty() ? defaultText : attributeValue;
      return value + "|NULL|";
   }

   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
      return createAnnotatorDescription( "" );
   }

   public static AnalysisEngineDescription createAnnotatorDescription( String outputDirectoryPath )
         throws ResourceInitializationException {
      return AnalysisEngineFactory
            .createEngineDescription( SemevalBsvWriter.class, "OutputDirectory", outputDirectoryPath );
   }

}
