package org.healthnlp.deepphe.uima.ae;

import java.io.FileNotFoundException;

import org.apache.ctakes.cancer.pipeline.CancerPipelineFactory;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;

public class TaggedEncounterAE extends TaggedSummarizableAE {

	private AnalysisEngine cTakesCancerAnalysisEngine = null;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			buildCtakesAggregateAnalysisEngine();
		} catch (FileNotFoundException fnf) {
			throw new ResourceInitializationException(fnf);
		}
	}

	private void buildCtakesAggregateAnalysisEngine() throws ResourceInitializationException, FileNotFoundException {
	    final  AnalysisEngineDescription ctakesCancerDescription = CancerPipelineFactory.createPipelineDescription();
	    cTakesCancerAnalysisEngine = AnalysisEngineFactory.createEngine( ctakesCancerDescription );
	}

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		super.process(multiJCas);
		JCas jCasComponent = fetchActiveJCas(multiJCas);
		if (jCasComponent != null
				&& jCasComponent.getViewName().startsWith("Encounter")) {
			try {
				processEncounter(jCasComponent);
			} catch (ResourceInitializationException e) {
				throw new AnalysisEngineProcessException(e);
			}
		}
	}
	
	private void processEncounter(JCas jCasComponent) throws ResourceInitializationException, AnalysisEngineProcessException {
		JCas encounterJCas = cTakesCancerAnalysisEngine.newJCas();
		encounterJCas.setDocumentText(jCasComponent.getDocumentText());
		cTakesCancerAnalysisEngine.process(encounterJCas);
		boolean aCopySofa = true;
		CasCopier.copyCas(encounterJCas.getCas(), jCasComponent.getCas(), aCopySofa);
	}
	
}
