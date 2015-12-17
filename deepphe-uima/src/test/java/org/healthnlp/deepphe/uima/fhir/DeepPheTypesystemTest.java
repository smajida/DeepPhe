package org.healthnlp.deepphe.uima.fhir;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
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
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.uima.cr.FHIRCollectionReader;
import org.healthnlp.deepphe.uima.types.Composition;
import org.healthnlp.deepphe.uima.types.DiseaseDisorder;
import org.healthnlp.deepphe.uima.types.Medication;
import org.healthnlp.deepphe.uima.types.Observation;
import org.junit.Test;

import static org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory.*;

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
			AnalysisEngine ae3 = AnalysisEngineFactory.createEngine(FHIRTester.class);
			
			SimplePipeline.runPipeline(cr,ae1,ae2,ae3);
	
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
	
	/**
	 * tester method for typesystem
	 * @author tseytlin
	 *
	 */
	
	public static class FHIRTester extends JCasAnnotator_ImplBase {
		
		public void process(JCas jcas) throws AnalysisEngineProcessException {
			
			List<Report> reports = PhenotypeResourceFactory.loadReports(jcas);
			for(Report r: reports ){
				System.out.println(r.getSummaryText());
			}
			
		}
	
	}
	
}
