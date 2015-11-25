package org.apache.ctakes.cancer.ae;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import org.apache.ctakes.cancer.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.receptor.ReceptorStatusFinder;
import org.apache.ctakes.cancer.size.SizeFinder;
import org.apache.ctakes.cancer.stage.StageFinder;
import org.apache.ctakes.cancer.tnm.TnmFinder;
import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.core.util.OntologyConceptUtil;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textspan.Segment;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


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


   private Predicate<IdentifiedAnnotation> _hasNeoplasm;
   private Predicate<IdentifiedAnnotation> _hasMass;


   /**
    * {@inheritDoc}
    */
   @Override
   public void initialize( final UimaContext uimaContext ) throws ResourceInitializationException {
      super.initialize( uimaContext );

      final Function<String, Predicate<IClass>> isClass
            = name -> iClass -> iClass.getName().equals( name )
                                || Arrays.toString( iClass.getSuperClasses() ).contains( name );

      _hasNeoplasm = annotation -> OwlOntologyConceptUtil.getOwlClasses( annotation ).stream()
            .anyMatch( isClass.apply( "Breast_Neoplasm" ) );

      _hasMass = annotation -> OwlOntologyConceptUtil.getOwlClasses( annotation ).stream()
            .anyMatch( isClass.apply( "Mass" ) );
   }


   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      LOGGER.info( "Starting processing" );

      final Collection<? extends Annotation> lookupWindows = JCasUtil.select( jcas, _lookupWindowType );
      for ( Annotation lookupWindow : lookupWindows ) {
         final Collection<IdentifiedAnnotation> annotations
               = JCasUtil.selectCovered( jcas, IdentifiedAnnotation.class, lookupWindow );
         final Collection<IdentifiedAnnotation> breastNeoplasms = annotations.stream()
               .filter( _hasNeoplasm )
               .collect( Collectors.toList() );

         if ( !breastNeoplasms.isEmpty() ) {
            TnmFinder.addTnmTumorClasses( jcas, lookupWindow, breastNeoplasms );
            ReceptorStatusFinder.addReceptorStatuses( jcas, lookupWindow, breastNeoplasms );
            StageFinder.addStages( jcas, lookupWindow, breastNeoplasms );
         }

         final Collection<IdentifiedAnnotation> masses = annotations.stream()
               .filter( _hasMass )
               .collect( Collectors.toList() );
         SizeFinder.addSizes( jcas, lookupWindow, breastNeoplasms, masses );
//         if ( LOGGER.isDebugEnabled() ) {
            printCancerFindings( jcas );
//         }
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
         LOGGER.info( "DISCOVERY! \t" + relation.getCategory().replace( '_', ' ' )
                      + " " + relation.getArg2().getArgument().getCoveredText()
                      + "\t is: \t" + relation.getArg1().getArgument().getCoveredText() );
      }
   }

   public static AnalysisEngineDescription createAnnotatorDescription() throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( CancerPropertiesAnnotator.class );
   }


}
