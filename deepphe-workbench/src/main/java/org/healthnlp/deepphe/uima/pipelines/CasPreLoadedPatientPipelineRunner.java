package org.healthnlp.deepphe.uima.pipelines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.ctakes.cancer.ae.CancerPropertiesAnnotator;
import org.apache.ctakes.chunker.ae.Chunker;
import org.apache.ctakes.chunker.ae.adjuster.ChunkAdjuster;
import org.apache.ctakes.constituency.parser.MaxentParserWrapper;
import org.apache.ctakes.constituency.parser.ae.ConstituencyParser;
import org.apache.ctakes.contexttokenizer.ae.ContextDependentTokenizerAnnotator;
import org.apache.ctakes.core.ae.CDASegmentAnnotator;
import org.apache.ctakes.core.ae.SentenceDetector;
import org.apache.ctakes.core.ae.TokenizerAnnotatorPTB;
import org.apache.ctakes.dependency.parser.ae.ClearNLPDependencyParserAE;
import org.apache.ctakes.dictionary.lookup2.ae.AbstractJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.dictionary.DictionaryDescriptorParser;
import org.apache.ctakes.lvg.resource.LvgCmdApiResourceImpl;
import org.apache.ctakes.postagger.POSTagger;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.healthnlp.deepphe.uima.ae.CasPreLoadedEncounterAE;
import org.healthnlp.deepphe.uima.ae.CasPreLoadedI2b2WriterAE;
import org.healthnlp.deepphe.uima.ae.CasPreLoadedPatientAE;
import org.healthnlp.deepphe.uima.cr.CasPreLoadedPatientReader;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

public class CasPreLoadedPatientPipelineRunner {

	static interface Options {
		@Option(shortName = "i", description = "specify the path to the directory containing the clinical notes to be processed")
		public String getInputDirectory();
	}

	private CollectionReader collectionReader;
	private AnalysisEngine analysisEngine;

	public static void main(final String... args) {
		final Options options = CliFactory.parseArguments(Options.class, args);
		CasPreLoadedPatientPipelineRunner runner = new CasPreLoadedPatientPipelineRunner();
		try {
			runner.runCancerPipeline(options.getInputDirectory());
		} catch (UIMAException | IOException e) {
			e.printStackTrace();
		}
	}

	public void runCancerPipeline(final String inputDirectory)
			throws UIMAException, IOException {
		adjustDiagnostics();
		collectionReader = CollectionReaderFactory.createReader(
				CasPreLoadedPatientReader.class,
				CasPreLoadedPatientReader.PARAM_INPUTDIR, inputDirectory);
		analysisEngine = buildCasPreLoadedPatientAnalysisEngine();
		SimplePipeline.runPipeline(collectionReader, analysisEngine);
	}

	@SuppressWarnings("unchecked")
	private void adjustDiagnostics() {
		
		Logger.getRootLogger().setLevel(Level.OFF);
		
		final List<Logger> allLoggers = new ArrayList<Logger>();
		Enumeration<Logger> loggerEnumeration = LogManager.getCurrentLoggers();
		while (loggerEnumeration.hasMoreElements()) {
			Logger currentLogger = loggerEnumeration.nextElement();
			allLoggers.add(currentLogger);
		}
		for (Logger logger : allLoggers) {
			logger.setLevel(Level.OFF);
			
		}
	
		Logger.getLogger(SentenceDetector.class).setLevel(Level.FATAL);
		Logger.getLogger(LvgCmdApiResourceImpl.class).setLevel(Level.FATAL);
		Logger.getLogger(TokenizerAnnotatorPTB.class).setLevel(Level.FATAL);
		Logger.getLogger(ContextDependentTokenizerAnnotator.class).setLevel(
				Level.FATAL);
		Logger.getLogger(ConstituencyParser.class).setLevel(Level.FATAL);
		Logger.getLogger(CDASegmentAnnotator.class).setLevel(Level.FATAL);
		Logger.getLogger(Chunker.class).setLevel(Level.FATAL);
		Logger.getLogger(AbstractJCasTermAnnotator.class).setLevel(Level.FATAL);
		Logger.getLogger(DictionaryDescriptorParser.class)
				.setLevel(Level.FATAL);
		Logger.getLogger(ClearNLPDependencyParserAE.class)
				.setLevel(Level.FATAL);
		Logger.getLogger(POSTagger.class).setLevel(Level.FATAL);
		Logger.getLogger(ChunkAdjuster.class).setLevel(Level.FATAL);
		Logger.getLogger(MaxentParserWrapper.class).setLevel(Level.FATAL);
		Logger.getLogger(MaxentParserWrapper.class).setLevel(Level.FATAL);
		Logger.getLogger(CancerPropertiesAnnotator.class).setLevel(Level.FATAL);
	}

	private AnalysisEngine buildCasPreLoadedPatientAnalysisEngine()
			throws ResourceInitializationException {
		final TypeSystemDescription typeSystemDescription = establishTypeSystem();
		final AggregateBuilder builder = new AggregateBuilder();
		builder.add(AnalysisEngineFactory.createEngineDescription(
				CasPreLoadedEncounterAE.class, typeSystemDescription));
		builder.add(AnalysisEngineFactory.createEngineDescription(
				CasPreLoadedPatientAE.class, typeSystemDescription));
		builder.add(AnalysisEngineFactory.createEngineDescription(
				CasPreLoadedI2b2WriterAE.class, typeSystemDescription));
		return builder.createAggregate();
	}

	private TypeSystemDescription establishTypeSystem() {
		TypeSystemDescription typeSystemDescription = TypeSystemDescriptionFactory
				.createTypeSystemDescriptionFromPath("../deepphe-ctakes-cancer/src/main/resources/org/apache/ctakes/cancer/types/TypeSystem.xml");
		return typeSystemDescription;
	}

}
