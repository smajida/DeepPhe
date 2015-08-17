package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.uima.ae.TaggedDeactivatorAE;
import org.healthnlp.deepphe.uima.ae.TaggedEncounterAE;
import org.healthnlp.deepphe.uima.ae.TaggedI2b2WriterAE;
import org.healthnlp.deepphe.uima.ae.TaggedPatientAE;
import org.healthnlp.deepphe.uima.cr.TaggedPatientReader;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

public class TaggedPatientPipelineRunner {

	static interface Options {
		@Option(shortName = "i", description = "specify the path to the directory containing the clinical notes to be processed")
		public String getInputDirectory();
	}
	
	private CollectionReader collectionReader;
	private AnalysisEngine analysisEngine;

	public static void main(final String... args) {
		final Options options = CliFactory.parseArguments(Options.class, args);
		TaggedPatientPipelineRunner runner = new TaggedPatientPipelineRunner();
		try {
			runner.runCancerPipeline(options.getInputDirectory());
		} catch (UIMAException | IOException e) {
			e.printStackTrace();
		}
	}

	public void runCancerPipeline(final String inputDirectory)
			throws UIMAException, IOException {
		collectionReader = CollectionReaderFactory.createReader(
				TaggedPatientReader.class,
				TaggedPatientReader.PARAM_INPUTDIR, inputDirectory);
		analysisEngine = buildAnalysisEngine();
		SimplePipeline.runPipeline(collectionReader, analysisEngine);
	}
	
	private AnalysisEngine buildAnalysisEngine()
			throws ResourceInitializationException {
		final AggregateBuilder builder = new AggregateBuilder();
		builder.add(AnalysisEngineFactory
				.createEngineDescription(TaggedEncounterAE.class));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(TaggedPatientAE.class));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(TaggedI2b2WriterAE.class));
		builder.add(AnalysisEngineFactory
				.createEngineDescription(TaggedDeactivatorAE.class));
		return builder.createAggregate();
	}

}
