package org.healthnlp.deepphe.uima.pipelines;

import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.apache.ctakes.cancer.ae.XMIWriter;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.uima.ae.CompositionCancerSummaryAE;
import org.healthnlp.deepphe.uima.ae.GraphDBPhenotypeConsumerAE;
import org.healthnlp.deepphe.uima.ae.I2b2WriterAE;
import org.healthnlp.deepphe.uima.ae.PhenotypeCancerSummaryAE;
import org.healthnlp.deepphe.uima.ae.PhenotypeSummarizerAE;
import org.healthnlp.deepphe.uima.cr.FHIRCollectionReader;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

@Immutable
final public class PhenotypeSummarizerPipeline {

	private PhenotypeSummarizerPipeline() {
	}

	static interface Options {
		@Option(shortName = "i", description = "specify the path to the directory containing the clinical notes to be processed")
		public String getInputDirectory();

		@Option(shortName = "m", description = "specify the path to the model ontology file to be used.")
		public String getOntologyPath();

		//@Option(shortName = "c", description = "specify the path to the jess clips files to be used.")
		//public String getClipsDirectoryPath();
		
	    @Option(shortName = "o", description = "specify the path to the directory where the output xmi files are to be saved" )
	    public String getOutputDirectory();
	}

	public static void main(final String... args) throws UIMAException,
			IOException {
		final Options options = CliFactory.parseArguments(Options.class, args);
		runPhenotypeSummarizerPipeline(options.getInputDirectory(),
				options.getOntologyPath(), options.getOutputDirectory());
	}

	public static void runPhenotypeSummarizerPipeline(
			final String inputDirectory, final String ontologyPath, final String outputDirectory)
			throws UIMAException, IOException {
		final CollectionReader collectionReader = createCollectionReader(inputDirectory);
		final AnalysisEngine compositionSummarizerAE =AnalysisEngineFactory.createEngine(CompositionCancerSummaryAE.class,CompositionCancerSummaryAE.PARAM_ONTOLOGY_PATH,ontologyPath);
		final AnalysisEngine cancerSummarizerAE =AnalysisEngineFactory.createEngine(PhenotypeCancerSummaryAE.class,PhenotypeCancerSummaryAE.PARAM_ONTOLOGY_PATH,ontologyPath);
		//final AnalysisEngine phenotypeSummarizerAE = createPhenotypeSummarizerAE(clipsDirectoryPath,ontologyPath);
		//final AnalysisEngine i2B2OutputAE = createI2B2OutputAE();
		final AnalysisEngine xmiWriter = AnalysisEngineFactory.createEngine( XMIWriter.class, XMIWriter.PARAM_OUTPUTDIR, outputDirectory);
		//SimplePipeline.runPipeline(collectionReader, phenotypeSummarizerAE, i2B2OutputAE);
		
	      final AnalysisEngine graphDBConsumerAE
	      = AnalysisEngineFactory
	      .createEngine( GraphDBPhenotypeConsumerAE.class, GraphDBPhenotypeConsumerAE.PARAM_DBPATH,outputDirectory + File.separator + "neo4jdb" );

	      
		SimplePipeline.runPipeline(collectionReader,compositionSummarizerAE,cancerSummarizerAE,xmiWriter,graphDBConsumerAE);
	}

	private static CollectionReader createCollectionReader(String inputDirectory)
			throws ResourceInitializationException {
		/*return CollectionReaderFactory.createReader(
				PatientCollectionReader.class,
				PatientCollectionReader.PARAM_INPUTDIR, inputDirectory);*/
		return CollectionReaderFactory.createReader(
				FHIRCollectionReader.class,
				FHIRCollectionReader.PARAM_INPUTDIR, inputDirectory);
	}

	static public AnalysisEngine createPhenotypeSummarizerAE(String clipsDirectoryPath,String ontologyPath)
			throws ResourceInitializationException {
		return AnalysisEngineFactory.createEngine(PhenotypeSummarizerAE.class,
				PhenotypeSummarizerAE.PARAM_CLIPS_DIRECTORY_PATH, clipsDirectoryPath,PhenotypeSummarizerAE.PARAM_ONTOLOGY_PATH,ontologyPath);
	}

	static private AnalysisEngine createI2B2OutputAE()
			throws ResourceInitializationException {
		return AnalysisEngineFactory.createEngine(I2b2WriterAE.class);
	}

}
