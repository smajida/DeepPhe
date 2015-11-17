package org.apache.ctakes.cancer.ae;

import org.apache.ctakes.typesystem.type.refsem.Event;
import org.apache.ctakes.typesystem.type.refsem.EventProperties;
import org.apache.ctakes.typesystem.type.textsem.EventMention;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import java.util.Collection;

/**
* Add Event for each EventMention
*/
// TODO This should really be someplace else - Right now there are ~4 redundant internal copies in ctakes code
final public class EventAdder extends JCasAnnotator_ImplBase {

   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jCas ) throws AnalysisEngineProcessException {
      final Collection<EventMention> eventMentions = JCasUtil.select( jCas, EventMention.class );
      if ( eventMentions == null ) {
         return;
      }
      for ( EventMention emention : eventMentions ) {
         final EventProperties eventProperties = new EventProperties( jCas );
         // create the event object
         final Event event = new Event( jCas );

         // add the links between event, mention and properties
         event.setProperties( eventProperties );
         emention.setEvent( event );

         // add the annotations to the indexes
         eventProperties.addToIndexes();
         event.addToIndexes();
      }
   }
}
