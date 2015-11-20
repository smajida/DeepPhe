package org.healthnlp.deepphe.uima.fhir;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ctakes.cancer.ae.XMIWriter;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.uima.cr.FHIRCollectionReader;
import org.healthnlp.deepphe.uima.types.Composition;
import org.healthnlp.deepphe.uima.types.DiseaseDisorder;
import org.healthnlp.deepphe.uima.types.Medication;
import org.healthnlp.deepphe.uima.types.Observation;
import org.junit.Test;

public class DeepPheTypesystemTest {

	@Test
	public void testFHIRimport() {
		char F = File.separatorChar;
		// find project dir
		File projectDir = new File(System.getProperty("user.dir")+F+".."+F+"data"+F+"sample");
		File inputFHIR = new File(projectDir,"output"+F+"FHIR");
		File outputTS = new File(projectDir,"output"+F+"TYPES");
		
		try {
			// run pipeline
			CollectionReader cr = CollectionReaderFactory.createReader(FHIRCollectionReader.class,FHIRCollectionReader.PARAM_INPUTDIR,inputFHIR.getAbsolutePath());
			AnalysisEngine ae1 = AnalysisEngineFactory.createEngine( XMIWriter.class, XMIWriter.PARAM_OUTPUTDIR, outputTS );
			AnalysisEngine ae2 = AnalysisEngineFactory.createEngine(DeepPheTypeTester.class);
			
			SimplePipeline.runPipeline(cr,ae1,ae2);
	
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error running pipeline: "+e.getMessage());
		}
	}
	
	
	/**
	 * tester method for typesystem
	 * @author tseytlin
	 *
	 */
	
	public static class DeepPheTypeTester extends JCasAnnotator_ImplBase {

		private List<Annotation> getAnnotations(JCas cas,int type){
			List<Annotation> a = new ArrayList<Annotation>();
			Iterator<Annotation> it = cas.getAnnotationIndex(type).iterator();
			while(it.hasNext())
				a.add(it.next());
			return a;
		}
		
		public void process(JCas jcas) throws AnalysisEngineProcessException {
			
			// now lets run tests
			assertNotNull("JCas not null",jcas);
						
			// check compositions
			List<Annotation> cc = getAnnotations(jcas,Composition.type);
			assertEquals("number of docs error",3,cc.size());
			
			// check disease
			cc = getAnnotations(jcas,DiseaseDisorder.type);
			assertEquals("number of diseases error",3,cc.size());
						
			// check medications
			cc = getAnnotations(jcas,Medication.type);
			assertEquals("number of medication error",4,cc.size());
			
			// check medications
			cc = getAnnotations(jcas,Observation.type);
			assertEquals("number of observation error",5,cc.size());
			
			
		}
	
	}
	private void runPipeline(String input, String output) throws Exception {
		CollectionReader cr = CollectionReaderFactory.createReader(FHIRCollectionReader.class,FHIRCollectionReader.PARAM_INPUTDIR, input);
		AnalysisEngine ae = AnalysisEngineFactory.createEngine( XMIWriter.class, XMIWriter.PARAM_OUTPUTDIR,  output );
		SimplePipeline.runPipeline(cr, ae);
	}
	
}
