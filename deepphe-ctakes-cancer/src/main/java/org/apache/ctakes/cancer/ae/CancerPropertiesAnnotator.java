package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.location.ModifierFinder;
import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.metastasis.MetastasisFinder;
import org.apache.ctakes.cancer.phenotype.receptor.StatusFinder;
import org.apache.ctakes.cancer.phenotype.size.SizeFinder;
import org.apache.ctakes.cancer.phenotype.stage.StageFinder;
import org.apache.ctakes.cancer.phenotype.tnm.TnmFinder;
import org.apache.ctakes.cancer.relation.NeoplasmRelationFactory;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceInitializationException;

import java.util.Collection;
import java.util.HashSet;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/28/2015
 */
public class CancerPropertiesAnnotator extends JCasAnnotator_ImplBase {

   /**
    * specifies the type of window to use for lookup
    */
   public static final String PARAM_WINDOW_ANNOT_PRP = "cancerWindowAnnotations";
   static private final Logger LOGGER = Logger.getLogger( "CancerPropertiesAnnotator" );
   @ConfigurationParameter( name = PARAM_WINDOW_ANNOT_PRP, mandatory = false )
//   private Class<? extends Annotation> _lookupWindowType = Segment.class;
   private Class<? extends Annotation> _lookupWindowType = DocumentAnnotation.class;


   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting processing" );

      final Collection<? extends Annotation> lookupWindows = JCasUtil.select( jcas, _lookupWindowType );
      for ( Annotation lookupWindow : lookupWindows ) {
         // All Neoplasms
         final Collection<IdentifiedAnnotation> breastNeoplasms = new HashSet<>();
         NeoplasmRelationFactory.getNeoplasmUris().forEach(
               u -> breastNeoplasms
                     .addAll( OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow, u ) ) );
         // Metastases are under neoplasm, but for now we do not want them as we are interested in primary neoplasms
         // Metastases
         final Collection<IdentifiedAnnotation> metastases
               = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, OwlConstants.BREAST_CANCER_OWL
                                                                         + "#Metastatic_Neoplasm" );
         breastNeoplasms.removeAll( metastases );
         // Find neoplasm location modifiers before adding phenotypes and metastases
         ModifierFinder.addLocationModifiers( jcas, lookupWindow );
         // Neoplasm phenotypes
         if ( !breastNeoplasms.isEmpty() ) {
            // TNM
            TnmFinder.addTnms( jcas, lookupWindow, breastNeoplasms );
            // Stage
            StageFinder.addStages( jcas, lookupWindow, breastNeoplasms );
            // Diagnostic Procedures
            final Collection<IdentifiedAnnotation> diagnosticProcedures
                  = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow,
                  OwlConstants.SCHEMA_OWL + "#DiagnosticProcedure" );
            // Receptor Status
            StatusFinder.addReceptorStatuses( jcas, lookupWindow, breastNeoplasms, diagnosticProcedures );
            // Metastases
            MetastasisFinder.addMetastases( jcas, lookupWindow, breastNeoplasms, metastases );
         }
         // want sizes for all neoplasms - metastatic and primary
         breastNeoplasms.addAll( metastases );
         if ( !breastNeoplasms.isEmpty() ) {
            // sizes
            SizeFinder.addSizes( jcas, lookupWindow, breastNeoplasms );
         }
      }
      LOGGER.info( "Finished processing" );
   }


   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( CancerPropertiesAnnotator.class );
   }


}
