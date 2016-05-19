package org.apache.ctakes.core.ae;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;


/**
 * All Annotation Engines should be logger their start and finish.
 * Such logging not only keeps track of what is actually in the pipeline, but it also helps with debugging and
 */
final public class StartEndProgressLogger extends JCasAnnotator_ImplBase {

   public static final String PARAM_LOGGER_NAME = "LOGGER_NAME";
   @ConfigurationParameter(
         name = PARAM_LOGGER_NAME,
         mandatory = true,
         description = "provides the full name of the Annotator Engine for which start / end logging should be done.",
         defaultValue = { "StartEndProgressLogger" }
   )
   private String _loggerName;

   public static final String PARAM_IS_START = "IS_START";
   @ConfigurationParameter(
         name = PARAM_IS_START,
         mandatory = false,
         description = "indicates whether this should log a atart."
   )
   private Boolean _isStart;

   private Logger LOGGER;

   @Override
   public void initialize( UimaContext context )
         throws ResourceInitializationException {
      super.initialize( context );
      LOGGER = Logger.getLogger( _loggerName );
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jcas ) throws AnalysisEngineProcessException {
      if ( _isStart ) {
         LOGGER.info( "Starting processing" );
      } else {
         LOGGER.info( "Finished processing" );
      }
   }


   public static AnalysisEngineDescription getDescription( final String loggerName, final boolean isStart ) throws
                                                                                                            ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription(
            StartEndProgressLogger.class,
            StartEndProgressLogger.PARAM_LOGGER_NAME,
            loggerName,
            StartEndProgressLogger.PARAM_IS_START,
            isStart );
   }


}
