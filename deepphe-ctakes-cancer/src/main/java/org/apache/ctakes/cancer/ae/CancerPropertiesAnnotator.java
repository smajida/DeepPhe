package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.cancer.receptor.ReceptorStatusFinder;
import org.apache.ctakes.cancer.size.SizeFinder;
import org.apache.ctakes.cancer.stage.StageFinder;
import org.apache.ctakes.cancer.tnm.TnmFinder;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.SignSymptomMention;
import org.apache.ctakes.typesystem.type.textspan.Sentence;
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

import java.util.ArrayList;
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
   private Class<? extends Annotation> _lookupWindowType = Sentence.class;


   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting processing" );

      final Collection<DiseaseDisorderMention> disorderMentions = new ArrayList<>();
      final Collection<DiseaseDisorderMention> lookupDisorders = new HashSet<>();
      final Collection<SignSymptomMention> symptomMentions = new ArrayList<>();
      final Collection<SignSymptomMention> lookupSymptoms = new HashSet<>();
      final Collection<? extends Annotation> lookupWindows = JCasUtil.select( jcas, _lookupWindowType );
      for ( Annotation lookupWindow : lookupWindows ) {
         disorderMentions.addAll( JCasUtil.selectCovered( jcas, DiseaseDisorderMention.class, lookupWindow ) );
         for ( DiseaseDisorderMention disorderMention : disorderMentions ) {
            if ( FinderUtil.hasWantedTui( disorderMention, "T191" ) ) {
               lookupDisorders.add( disorderMention );
            }
         }
         disorderMentions.clear();
         symptomMentions.addAll( JCasUtil.selectCovered( jcas, SignSymptomMention.class, lookupWindow ) );
         for ( SignSymptomMention symptomMention : symptomMentions ) {
            if ( FinderUtil.hasWantedTui( symptomMention, "T033", "T034", "T184" ) ) {
               lookupSymptoms.add( symptomMention );
            }
         }
         symptomMentions.clear();
         if ( !lookupDisorders.isEmpty() ) {
            TnmFinder.addTnmTumorClasses( jcas, lookupWindow, lookupDisorders );
            ReceptorStatusFinder.addReceptorStatuses( jcas, lookupWindow, lookupDisorders );
            StageFinder.addStages( jcas, lookupWindow, lookupDisorders );
         }
         SizeFinder.addSizes( jcas, lookupWindow, lookupDisorders, lookupSymptoms );
         lookupDisorders.clear();
         lookupSymptoms.clear();

         if ( LOGGER.isDebugEnabled() ) {
            printCancerFindings( jcas );
         }
      }

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
         LOGGER.debug( relation.getCategory().replace( '_', ' ' ) + " " + relation.getArg1().getArgument().getCoveredText()
                       + "\n\tis: " + relation.getArg2().getArgument().getCoveredText() );
      }
   }

   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( CancerPropertiesAnnotator.class );
   }


}
