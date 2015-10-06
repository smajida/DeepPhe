package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;

import org.apache.ctakes.cancer.pipeline.CancerPipelineFactory;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;

public class CtakesCancerEncounterPipeBuilder {

	public static AnalysisEngineDescription getPipelineDescription()
			throws ResourceInitializationException, InvalidXMLException,
			IOException {
		return CancerPipelineFactory.createPipelineDescription();
	}

}
