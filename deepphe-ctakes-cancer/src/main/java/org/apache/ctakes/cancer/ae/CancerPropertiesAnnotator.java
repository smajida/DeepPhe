package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.receptor.StatusFinder;
import org.apache.ctakes.cancer.size.SizeFinder;
import org.apache.ctakes.cancer.stage.StageFinder;
import org.apache.ctakes.cancer.tnm.TnmFinder;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textspan.Segment;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
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
//   private Class<? extends Annotation> _lookupWindowType = Sentence.class;
//   private Class<? extends Annotation> _lookupWindowType = Paragraph.class;
   private Class<? extends Annotation> _lookupWindowType = Segment.class;


   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting processing" );

      final Collection<? extends Annotation> lookupWindows = JCasUtil.select( jcas, _lookupWindowType );
      for ( Annotation lookupWindow : lookupWindows ) {
         final Collection<IdentifiedAnnotation> breastNeoplasms
               = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow, "Breast_Neoplasm" );

         if ( !breastNeoplasms.isEmpty() ) {
            TnmFinder.addTnms( jcas, lookupWindow, breastNeoplasms );
            StageFinder.addStages( jcas, lookupWindow, breastNeoplasms );

            final Collection<IdentifiedAnnotation> diagnosticProcedures
                  = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow, "DiagnosticProcedure" );
            StatusFinder.addReceptorStatuses( jcas, lookupWindow, breastNeoplasms, diagnosticProcedures );
         }

         final Collection<IdentifiedAnnotation> masses
               = OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow, "Metastasis" );
         // Metastasis does not have all metastases as children
         masses.addAll( OwlOntologyConceptUtil.getAnnotationsByUriBranch( jcas, lookupWindow, "Metastatic_Neoplasm" ) );

         masses.addAll( breastNeoplasms );
         SizeFinder.addSizes( jcas, lookupWindow, masses );
//         if ( LOGGER.isDebugEnabled() ) {
//         printCancerFindings( jcas );
//         }
      }

//      OwlOntologyConceptUtil.getUris( jcas ).stream().forEach( System.out::println );
//      OwlOntologyConceptUtil.getUris( jcas ).stream()
//            .map( uri -> OwlOntologyConceptUtil.getAnnotationsByUri( jcas, uri ) )
//            .flatMap( Collection::stream )
//            .map( Annotation::getCoveredText )
//            .forEach( System.out::println );

      LOGGER.info( "Finished processing" );
   }

   /**
    * Only run for debug
    *
    * @param jcas -
    */
   static private void printCancerFindings( final JCas jcas ) {
      final Collection<NeoplasmRelation> neoplasmRelations = JCasUtil.select( jcas, NeoplasmRelation.class );
      for ( NeoplasmRelation relation : neoplasmRelations ) {
         LOGGER.info( "DISCOVERY! \t" + relation.getCategory().replace( '_', ' ' )
                      + " " + relation.getArg2().getArgument().getCoveredText()
                      + "\t is: \t" + relation.getArg1().getArgument().getCoveredText() );
      }
//      final Collection<BinaryTextRelation> binaryRelations = JCasUtil.select( jcas, BinaryTextRelation.class );
//      for ( BinaryTextRelation relation : binaryRelations ) {
//         if ( relation instanceof NeoplasmRelation ) {
//            continue;
//         }
//         LOGGER.info( "RELATION! \t" + relation.getCategory().replace( '_', ' ' )
//                      + " " + relation.getArg2().getArgument().getCoveredText()
//                      + "\t is: \t" + relation.getArg1().getArgument().getCoveredText() );
//      }
   }

   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( CancerPropertiesAnnotator.class );
   }


}
