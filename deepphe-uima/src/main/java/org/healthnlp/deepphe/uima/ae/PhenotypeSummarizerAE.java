package org.healthnlp.deepphe.uima.ae;

import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.commons.lang.SerializationUtils;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;




public class PhenotypeSummarizerAE extends JCasAnnotator_ImplBase {

	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";


	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		
		try {
			Patient patient = (Patient) SerializationUtils.deserialize(Base64.getDecoder().decode(jcas.getDocumentText()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
