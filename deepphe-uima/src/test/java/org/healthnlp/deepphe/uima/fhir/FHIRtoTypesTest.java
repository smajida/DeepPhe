package org.healthnlp.deepphe.uima.fhir;

import static org.junit.Assert.*;

import org.apache.ctakes.cancer.ae.XMIWriter;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.healthnlp.deepphe.uima.cr.FHIRCollectionReader;
import org.junit.Test;

public class FHIRtoTypesTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public void runPipeline(String input, String output) throws Exception {
		//TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription();
		
		CollectionReader cr = CollectionReaderFactory.createReader(FHIRCollectionReader.class,FHIRCollectionReader.PARAM_INPUTDIR, input);
	
		AnalysisEngine ae = AnalysisEngineFactory.createEngine( XMIWriter.class, XMIWriter.PARAM_OUTPUTDIR,  output );
		SimplePipeline.runPipeline(cr, ae);
	}
	

	public static void main(String [] args) throws Exception{
		FHIRtoTypesTest test = new FHIRtoTypesTest();
		//test.test();
		test.runPipeline(args[0], args[1]);
	}
}
