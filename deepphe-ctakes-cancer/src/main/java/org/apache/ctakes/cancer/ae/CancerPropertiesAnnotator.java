package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.receptor.StatusFinder;
import org.apache.ctakes.cancer.phenotype.size.SizeFinder;
import org.apache.ctakes.cancer.phenotype.stage.StageFinder;
import org.apache.ctakes.cancer.phenotype.tnm.TnmFinder;
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
         final Collection<IdentifiedAnnotation> breastNeoplasms
               = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow,
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Neoplasm" );
         breastNeoplasms.addAll( OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow,
               OwlOntologyConceptUtil.CANCER_OWL + "#Cancer" ) );

         if ( !breastNeoplasms.isEmpty() ) {
            TnmFinder.addTnms( jcas, lookupWindow, breastNeoplasms );
            StageFinder.addStages( jcas, lookupWindow, breastNeoplasms );

            final Collection<IdentifiedAnnotation> diagnosticProcedures
                  = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow,
                  OwlOntologyConceptUtil.SCHEMA_OWL + "#DiagnosticProcedure" );
            StatusFinder.addReceptorStatuses( jcas, lookupWindow, breastNeoplasms, diagnosticProcedures );
         }

         final Collection<IdentifiedAnnotation> masses
               = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow,
               OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Metastasis" );

         masses.addAll( breastNeoplasms );
         if ( !masses.isEmpty() ) {
            SizeFinder.addSizes( jcas, lookupWindow, masses );
         }
      }

//      OwlOntologyConceptUtil.getUris( jcas ).stream().forEach( System.out::println );
//      OwlOntologyConceptUtil.getUris( jcas ).stream()
//            .map( uri -> OwlOntologyConceptUtil.getAnnotationsByUri( jcas, uri ) )
//            .flatMap( Collection::stream )
//            .map( Annotation::getCoveredText )
//            .forEach( System.out::println );

      LOGGER.info( "Finished processing" );
   }


   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( CancerPropertiesAnnotator.class );
   }


}
