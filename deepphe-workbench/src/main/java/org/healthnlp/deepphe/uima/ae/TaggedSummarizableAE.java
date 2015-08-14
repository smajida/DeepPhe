package org.healthnlp.deepphe.uima.ae;

import java.util.Iterator;

import org.apache.ctakes.typesystem.type.syntax.BaseToken;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.tcas.Annotation;

public class TaggedSummarizableAE extends JCasAnnotator_ImplBase {
	
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
	}
	
	protected JCas fetchActiveJCas(JCas jCas) throws AnalysisEngineProcessException {
		JCas resultJCas = jCas;
		try {
			for (Iterator<JCas> viewIterator = jCas.getViewIterator(); viewIterator
					.hasNext();) {
				JCas jCasComponent = viewIterator.next();
				if (isActiveJcas(jCasComponent)) {
					resultJCas = jCasComponent;
					break;
				}			
			}
		} catch (CASException e) {
			e.printStackTrace();
		}
		return resultJCas;
	}

	protected boolean isActiveJcas(JCas jCasComponent) {
		boolean result = false;
		JFSIndexRepository indexes = jCasComponent.getJFSIndexRepository();
		FSIterator<Annotation> tokenItr = indexes.getAnnotationIndex(
				BaseToken.type).iterator();
		while (tokenItr.hasNext()) {
			BaseToken token = (BaseToken) tokenItr.next();
			if (token.getBegin() == 0 && token.getEnd() == 0
					&& token.getPartOfSpeech().equals("ACTIVE")) {
				result = true;
				break;
			}
		}
		return result;
	}


}
