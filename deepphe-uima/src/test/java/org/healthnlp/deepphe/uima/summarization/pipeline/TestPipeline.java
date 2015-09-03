/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.healthnlp.deepphe.uima.summarization.pipeline;

import java.util.Collection;

import org.apache.ctakes.assertion.medfacts.cleartk.ConditionalCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.GenericCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.HistoryCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.PolarityCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.SubjectCleartkAnalysisEngine;
import org.apache.ctakes.assertion.medfacts.cleartk.UncertaintyCleartkAnalysisEngine;
import org.apache.ctakes.cancer.ae.CancerPropertiesAnnotator;
import org.apache.ctakes.clinicalpipeline.ClinicalPipelineFactory;
import org.apache.ctakes.contexttokenizer.ae.ContextDependentTokenizerAnnotator;
import org.apache.ctakes.core.ae.SentenceDetector;
import org.apache.ctakes.core.ae.SimpleSegmentAnnotator;
import org.apache.ctakes.core.ae.TokenizerAnnotatorPTB;
import org.apache.ctakes.core.resource.FileLocator;
import org.apache.ctakes.core.resource.FileResourceImpl;
import org.apache.ctakes.dictionary.lookup2.ae.AbstractJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.DefaultJCasTermAnnotator;
import org.apache.ctakes.dictionary.lookup2.ae.JCasTermAnnotator;
import org.apache.ctakes.postagger.POSTagger;
import org.apache.ctakes.typesystem.type.syntax.BaseToken;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;


public class TestPipeline {	
	
	@Test
	public void test() throws Exception {
		String text = "patient took 50mg of aspirin for pain in knee.";
		 AnalysisEngine pipeline = getAggregateBuilder().createAggregate();
		 JCas jcas = pipeline.newJCas();
			jcas.setDocumentText(text);
			pipeline.process(jcas);
		//Do something with the cas
		Collection<BaseToken> tokens = JCasUtil.select(jcas, BaseToken.class);
		for(BaseToken token : tokens) {
			System.out.print(token);
		}
		jcas.reset();
	}

	public static AggregateBuilder getAggregateBuilder() throws Exception {
		
		//Add the cTAKES pipeline
		AggregateBuilder builder = new AggregateBuilder();
	      builder.add( SimpleSegmentAnnotator.createAnnotatorDescription() );
	      builder.add( SentenceDetector.createAnnotatorDescription() );
	      builder.add( TokenizerAnnotatorPTB.createAnnotatorDescription() );
	      //builder.add( LvgAnnotator.createAnnotatorDescription() );
	      builder.add( ContextDependentTokenizerAnnotator.createAnnotatorDescription() );
	      builder.add( POSTagger.createAnnotatorDescription() );
		  //Note Fast pipeline Requires umls user and pw- set in -Dctakes.umlsuser= -Dctakes.umlspw=
		  //Fast also inlcudes LVG
	      //cTakesHsql.xml also needs to be unpacked somehwere 
	      // because it can't currently be read in from a stream/jar!!!
		  //builder.add(ClinicalPipelineFactory.getFastPipeline()); 
	      
//		 builder.add( AnalysisEngineFactory.createEngineDescription( DefaultJCasTermAnnotator.class,
//	               AbstractJCasTermAnnotator.PARAM_WINDOW_ANNOT_PRP,
//	               "org.apache.ctakes.typesystem.type.textspan.Sentence",
//	               JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY,
//	               ExternalResourceFactory.createExternalResourceDescription(
//	                     FileResourceImpl.class,
//	                     FileLocator.locateFile( "org/apache/ctakes/dictionary/lookup/fast/cTakesHsql.xml" ) )
//	         ) );
	      builder.add( PolarityCleartkAnalysisEngine.createAnnotatorDescription() );
	      builder.add( UncertaintyCleartkAnalysisEngine.createAnnotatorDescription() );
	      builder.add( HistoryCleartkAnalysisEngine.createAnnotatorDescription() );
	      builder.add( ConditionalCleartkAnalysisEngine.createAnnotatorDescription() );
	      builder.add( GenericCleartkAnalysisEngine.createAnnotatorDescription() );
	      builder.add( SubjectCleartkAnalysisEngine.createAnnotatorDescription() );		
	     
	     //Add the cancer pipeline that extracts the cancer attributes
	      builder.add(CancerPropertiesAnnotator.createAnnotatorDescription());
	      
		return builder;
	}

}