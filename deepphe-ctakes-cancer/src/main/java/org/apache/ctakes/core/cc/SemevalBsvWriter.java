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
import java.util.stream.Stream;

/**
 *
 *
 *
 *
 * FFFFFFFFF  RRRRRR    EEEEEEE  EEEEEEE   ZZZZZZZ   EEEEEEE
 * FF         RR    RR  EE       EE            ZZ    EE
 * FFFFF      RRRRRR    EEEEE    EEEEE        ZZ     EEEEE
 * FF         RR  RR    EE       EE         ZZ       EE
 * FF         RR   RR   EE       EE        ZZ        EE
 * FF         RR    RR  EEEEEEE  EEEEEEE  ZZZZZZZZZ  EEEEEEE
 *
 *
 *
 *       AT SPRINT 10 ONTOLOGY !!!!!!!!!!!!!!!!!!!!!!
 *
 *
 *
 *
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
         writePhenotypeBsv( jcas, getOutputDir( "CancerStage" ), fileName, StagePropertyUtil.getParentUri() );
         writePhenotypeBsv( jcas, getOutputDir( "ReceptorStatus" ), fileName, StatusPropertyUtil.getParentUri(),
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Triple_Negative_Breast_Carcinoma" );
         writeTnmBsv( jcas, getOutputDir( "TnmClassification" ), fileName, TnmPropertyUtil.getParentUri() );
         writeDisorderBsv( jcas, getOutputDir( "Disorder" ), fileName );
         writeMultiLocationBsv( jcas, getOutputDir( "Metastasis" ), fileName,
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Metastasis",
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Metastatic_Neoplasm" );
      } catch ( IOException ioE ) {
         throw new AnalysisEngineProcessException( ioE );
      }
   }

   private File getOutputDir( final String phenotypeName ) {
      final File dir = new File( _outputRootDir, phenotypeName );
      dir.mkdirs();
      return dir;
   }

   static private Stream<ConceptInstance> getConceptInstanceStream( final JCas jCas, final String... uris ) {
      return Arrays.stream( uris )
            .map( uri -> ConceptInstanceUtil.getBranchConceptInstances( jCas, uri ) )
            .flatMap( Collection::stream )
            .filter( ci -> getCuiValue( ci ) != null );
   }

   static private void writePhenotypeBsv( final JCas jCas, final File outputDir, final String fileName,
                                          final String... uris )
         throws IOException {
      final Collection<ConceptInstance> conceptInstances = getConceptInstanceStream( jCas, uris )
            .collect( Collectors.toList() );
      writeBsv( conceptInstances, new File( outputDir, fileName ) );
   }

   static private void writeTnmBsv( final JCas jCas, final File outputDir, final String fileName,
                                    final String... uris )
         throws IOException {
      final Collection<ConceptInstance> conceptInstances = getConceptInstanceStream( jCas, uris )
            .collect( Collectors.toList() );
      writeTnmBsv( conceptInstances, new File( outputDir, fileName ) );
   }

   static private void writeDisorderBsv( final JCas jCas, final File outputDir, final String fileName )
         throws IOException {
      final String[] uris2 = { StagePropertyUtil.getParentUri(), StatusPropertyUtil.getParentUri(),
                               StatusPropertyUtil.getParentUri(),
                               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Triple_Negative_Breast_Carcinoma",
                               TnmPropertyUtil.getParentUri() };
      final Collection<ConceptInstance> conceptInstances2 = getConceptInstanceStream( jCas, uris2 )
            .map( ci -> ConceptInstanceUtil.getPhenotypeNeoplasms( jCas, ci ) )
            .flatMap( Collection::stream )
            .collect( Collectors.toSet() );
      writeMultiLocationBsv( conceptInstances2, new File( outputDir, fileName ) );
   }

   static private void writeMultiLocationBsv( final JCas jCas, final File outputDir, final String fileName,
                                              final String... uris )
         throws IOException {
      final Collection<ConceptInstance> conceptInstances = getConceptInstanceStream( jCas, uris )
            .collect( Collectors.toList() );
      writeMultiLocationBsv( conceptInstances, new File( outputDir, fileName ) );
   }


   /**
    * Serialize a CAS to a file in semeval BSV format
    *
    * @param conceptInstances -
    * @param bsvFile   output file
    * @throws IOException -
    */
   public static void writeBsv( final Collection<ConceptInstance> conceptInstances, final File bsvFile )
         throws IOException {
      final String sourceFilename = bsvFile.getName().replace( ".pipe", "" );
      try ( BufferedWriter writer = new BufferedWriter( new FileWriter( bsvFile ) ) ) {
         for ( ConceptInstance conceptInstance : conceptInstances ) {
            // DocName|
            writer.write( sourceFilename + "|" );
            writer.write( createPhenotypeLine( conceptInstance ) );
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
         for ( ConceptInstance conceptInstance : conceptInstances ) {
            // We are interested in value annotations, some of which are null for tnm
            if ( PhenotypeAnnotationUtil.getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ).isEmpty() ) {
               continue;
            }
            // DocName|
            writer.write( sourceFilename + "|" );
            writer.write( createTnmPhenotypeLine( conceptInstance ) );
            writer.write( "\n" );
         }
      } catch ( IOException ioE ) {
         LOGGER.error( ioE.getMessage() );
      }
   }

   public static void writeMultiLocationBsv( final Collection<ConceptInstance> conceptInstances, final File bsvFile )
         throws IOException {
      final String sourceFilename = bsvFile.getName().replace( ".pipe", "" );
      try ( BufferedWriter writer = new BufferedWriter( new FileWriter( bsvFile ) ) ) {
         for ( ConceptInstance conceptInstance : conceptInstances ) {
            // We want a line per location
            final Collection<IdentifiedAnnotation> locations = PhenotypeAnnotationUtil.getLocations( conceptInstance
                  .getIdentifiedAnnotation() );
            if ( locations.isEmpty() ) {
               // DocName| standard line
               writer.write( sourceFilename + "|" );
               writer.write( createPhenotypeLine( conceptInstance ) );
               writer.write( "\n" );
            } else {
               for ( IdentifiedAnnotation location : locations ) {
                  // DocName| standard line
                  writer.write( sourceFilename + "|" );
                  writer.write( createMultiLocationLine( conceptInstance, location ) );
                  writer.write( "\n" );
               }
            }
         }
      } catch ( IOException ioE ) {
         LOGGER.error( ioE.getMessage() );
      }
   }


   static private String createPhenotypeLine( final ConceptInstance conceptInstance ) {
      final StringBuilder sb = new StringBuilder();
      // Phenotype_Spans|CUI|
      sb.append( getSpannedCuiText( conceptInstance.getIdentifiedAnnotation(), "unmarked" ) );
      // All negation, subject, uncertainty, course, severity, conditional, generic, location, doctimerel
      sb.append( createStandardColumns( conceptInstance ) );
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

   static private String createMultiLocationLine( final ConceptInstance conceptInstance,
                                                  final IdentifiedAnnotation location ) {
      final StringBuilder sb = new StringBuilder();
      // Phenotype_Spans|CUI|
      sb.append( getSpannedCuiText( conceptInstance.getIdentifiedAnnotation(), "unmarked" ) );
      // All negation, subject, uncertainty, course, severity, conditional, generic, location, doctimerel
      sb.append( createMultiLocationColumns( conceptInstance, location ) );
      // Value_Spans|CUI|  -----> null for TNM
      sb.append( "unmarked|NULL|" );
//      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
//            .getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // Neoplasm_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeNeoplasms( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // AssociatedTest_Spans|CUI|    --> TNM "Diagnostic Test" is actually the prefix (if present)
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getDiagnosticTests( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      return sb.toString();
   }

   static private String createTnmPhenotypeLine( final ConceptInstance conceptInstance ) {
      final StringBuilder sb = new StringBuilder();
      // Phenotype_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // All negation, subject, uncertainty, course, severity, conditional, generic, location, doctimerel
      sb.append( createStandardColumns( conceptInstance ) );
      // Value_Spans|CUI|  -----> null for TNM
      sb.append( "unmarked|NULL|" );
//      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
//            .getPhenotypeValues( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // Neoplasm_Spans|CUI|
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getPhenotypeNeoplasms( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      // AssociatedTest_Spans|CUI|    --> TNM "Diagnostic Test" is actually the prefix (if present)
      sb.append( getSpannedCuiText( PhenotypeAnnotationUtil
            .getDiagnosticTests( conceptInstance.getIdentifiedAnnotation() ), "unmarked" ) );
      return sb.toString();
   }

   static private String createStandardColumns( final ConceptInstance conceptInstance ) {
      final StringBuilder sb = new StringBuilder();
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
            .getLocations( conceptInstance.getIdentifiedAnnotation() ), "null" ) );
      // DocTimeRel|DocTimeRelSpan|
      sb.append( getAttributeText( conceptInstance.getDocTimeRel(), "unmarked" ) );
      return sb.toString();
   }

   static private String createMultiLocationColumns( final ConceptInstance conceptInstance,
                                                     final IdentifiedAnnotation location ) {
      final StringBuilder sb = new StringBuilder();
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
      sb.append( getNormalizedCuiText( location ) + '|' + getSpanText( location, "null" ) + '|' );
      // DocTimeRel|DocTimeRelSpan|
      sb.append( getAttributeText( conceptInstance.getDocTimeRel(), "unmarked" ) );
      return sb.toString();
   }

   static private IdentifiedAnnotation getFirstAnnotation( final Collection<IdentifiedAnnotation> annotations ) {
      return annotations.stream().findFirst().orElse( null );
   }


   static private String getSpannedCuiText( final Collection<IdentifiedAnnotation> annotations,
                                            final String defaultText ) {
      return getSpannedCuiText( getFirstAnnotation( annotations ), defaultText );
   }

   static private String getSpannedCuiText( final IdentifiedAnnotation annotation, final String defaultText ) {
      return getSpanText( annotation, defaultText ) + '|' + getNormalizedCuiText( annotation ) + '|';
   }

   static private String getCuiSpannedText( final Collection<IdentifiedAnnotation> annotations,
                                            final String defaultText ) {
      final IdentifiedAnnotation annotation = getFirstAnnotation( annotations );
      return getNormalizedCuiText( annotation ) + '|' + getSpanText( annotation, defaultText ) + '|';
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

//   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
//      return createAnnotatorDescription( "" );
//   }

   public static AnalysisEngineDescription createAnnotatorDescription( String outputDirectoryPath )
         throws ResourceInitializationException {
      return AnalysisEngineFactory
            .createEngineDescription( SemevalBsvWriter.class, "OutputDirectory", outputDirectoryPath );
   }

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

   static private String getSpanText( final IdentifiedAnnotation annotation, final String defaultSpanText ) {
      return annotation == null ? defaultSpanText : (annotation.getBegin() + "-" + annotation.getEnd());
   }


//   static private String getNormalizedCuiText( final ConceptInstance conceptInstance ) {
//      return getNormalizedCuiText( conceptInstance.getIdentifiedAnnotation() );
//   }

//   static private String getNormalizedCuiText( final Collection<IdentifiedAnnotation> annotations ) {
//      return getNormalizedCuiText( getFirstAnnotation( annotations ) );
//   }

   static private String getNormalizedCuiText( final IdentifiedAnnotation annotation ) {
      final String cuiValue = getCuiValue( annotation );
      return cuiValue == null ? "unmarked" : getNormalizedCuiText( cuiValue );
   }

   static private String getNormalizedCuiText( final String cui ) {
      switch ( cui ) {
         // Stage 4, 3a, 2b
         case "C0278488":
            return "C0441772";
         case "C0278489":
            return "C0456598";
         case "C1336178":
            return "C0441769";
         // ER
         case "C1719706":
            return "C1516974";
         case "C1719707":
            return "C1516974";
         case "C0279758":
            return "C1516974";
         // PR
         case "C0279759":
            return "C1514471";
         case "C0279766":
            return "C1514471";
         case "C0279768":
            return "C1514471";
         // Her2
         case "C2348909":
            return "C1512413";
         case "C2348908":
            return "C1512413";
         case "C2348910":
            return "C1512413";
         // Triple -
         case "C2348820":
            return "C2348819";
         // Breast Neoplasm
         case "CL437240":
            return "C1458155";
         case "C2981607":    // CDISC
            return "C0027651";
         // Metastasis
         case "C0027627":
            return "C1384494";
//         case "C2939419" :
//            return "C1384494";
      }
      return cui;
   }


}
