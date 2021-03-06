package org.healthnlp.deepphe.uima.pipelines;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;
import org.apache.ctakes.core.ae.XMIWriter;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.uima.ae.CompositionCancerSummaryAE;
import org.healthnlp.deepphe.uima.ae.EvaluationOutput;
import org.healthnlp.deepphe.uima.ae.GraphDBPhenotypeConsumerAE;
import org.healthnlp.deepphe.uima.ae.PhenotypeCancerSummaryAE;
import org.healthnlp.deepphe.uima.ae.SummaryTextOutput;
import org.healthnlp.deepphe.uima.ae.TranSMART_Output;
import org.healthnlp.deepphe.uima.cr.FHIRCollectionReader;

import javax.annotation.concurrent.Immutable;
import java.io.File;
import java.io.IOException;

@Immutable
final public class PhenotypeSummarizerPipeline {

	private PhenotypeSummarizerPipeline() {
	}

	static interface Options {
		@Option(shortName = "i", description = "specify the path to the directory containing the clinical notes to be processed")
		public String getInputDirectory();

		@Option(shortName = "m", description = "specify the path to the model ontology file to be used.")
		public String getOntologyPath();

		// @Option(shortName = "c", description =
		// "specify the path to the jess clips files to be used.")
		// public String getClipsDirectoryPath();

		@Option(shortName = "o", description = "specify the path to the directory where the output xmi files are to be saved")
		public String getOutputDirectory();
		
		@Option(shortName = "t", description = "transmart mapping file")
		public String getTransmartMappingFile();
		
		@Option(shortName = "p", description = "TCGA name mapping file", defaultToNull = true)
		public String getTCGANameMappingFile();
		
		@Option(shortName = "c", description = "Evaluation cancer mapping file")
		public String getEvaluationCancerMappingFile();
		
		@Option(shortName = "u", description = "Evaluation tumor mapping file")
		public String getEvaluationTumorMappingFile();
	}

	public static void main(final String... args) throws UIMAException, IOException {
		final Options options = CliFactory.parseArguments(Options.class, args);
		runPhenotypeSummarizerPipeline(options.getInputDirectory(), options.getOntologyPath(),options.getOutputDirectory(),
					options.getTransmartMappingFile(),options.getTCGANameMappingFile(),
					options.getEvaluationCancerMappingFile(),options.getEvaluationTumorMappingFile());
	}

	public static void runPhenotypeSummarizerPipeline(final String inputDirectory, final String ontologyPath, final String outputDirectory,
			final String mappingFile, final String tcgaMap, 
			final String cancerFile, final String tumorFile) throws UIMAException, IOException {
		final CollectionReader collectionReader = createCollectionReader(inputDirectory);
		final AnalysisEngine compositionSummarizerAE = AnalysisEngineFactory.createEngine(
				CompositionCancerSummaryAE.class, CompositionCancerSummaryAE.PARAM_ONTOLOGY_PATH, ontologyPath);
		final AnalysisEngine cancerSummarizerAE = AnalysisEngineFactory.createEngine(PhenotypeCancerSummaryAE.class,
				PhenotypeCancerSummaryAE.PARAM_ONTOLOGY_PATH, ontologyPath);
		final AnalysisEngine xmiWriter = AnalysisEngineFactory.createEngine(XMIWriter.class, XMIWriter.PARAM_OUTPUTDIR,
				new File(outputDirectory).getAbsolutePath() + File.separator + "TYPE");
		final AnalysisEngine summaryAE = AnalysisEngineFactory.createEngine(SummaryTextOutput.class,SummaryTextOutput.PARAM_OUTPUTDIR,outputDirectory); 
		
		final AnalysisEngine graphDBConsumerAE = AnalysisEngineFactory.createEngine(GraphDBPhenotypeConsumerAE.class,
				GraphDBPhenotypeConsumerAE.PARAM_DBPATH, outputDirectory + File.separator + "neo4jogmdb2");

		final AnalysisEngine transmartAE = AnalysisEngineFactory.createEngine(TranSMART_Output.class,TranSMART_Output.PARAM_TRANSMART_MAP_FILE,mappingFile,
													TranSMART_Output.PARAM_TCGA_ID_MAP_FILE,tcgaMap,TranSMART_Output.PARAM_OUTPUTDIR,outputDirectory); 
		
		final AnalysisEngine evaluateAE = AnalysisEngineFactory.createEngine(EvaluationOutput.class,EvaluationOutput.PARAM_EVALUATION_CANCER_MAP_FILE,cancerFile,
				EvaluationOutput.PARAM_EVALUATION_TUMOR_MAP_FILE,tumorFile,TranSMART_Output.PARAM_OUTPUTDIR,outputDirectory); 
		
		
		// run the damn pipeline
		SimplePipeline.runPipeline(collectionReader, compositionSummarizerAE, cancerSummarizerAE, xmiWriter,summaryAE, transmartAE,evaluateAE,graphDBConsumerAE);
		//SimplePipeline.runPipeline(collectionReader, compositionSummarizerAE,cancerSummarizerAE,xmiWriter);

	}

	private static CollectionReader createCollectionReader(String inputDirectory)
			throws ResourceInitializationException {
		/*
		 * return CollectionReaderFactory.createReader(
		 * PatientCollectionReader.class,
		 * PatientCollectionReader.PARAM_INPUTDIR, inputDirectory);
		 */
		return CollectionReaderFactory.createReader(FHIRCollectionReader.class, FHIRCollectionReader.PARAM_INPUTDIR,
				inputDirectory);
	}


	
}
