package org.healthnlp.deepphe.uima.ae;

import org.apache.ctakes.typesystem.type.syntax.BaseToken;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.tcas.Annotation;

public class TaggedDeactivatorAE extends TaggedSummarizableAE {

	@Override
	public void process(JCas multiCas) throws AnalysisEngineProcessException {
		super.process(multiCas);
		JCas activeJCas = fetchActiveJCas(multiCas);
		if (activeJCas != null) {
			deActivateJcas(activeJCas);
			if (CasDetector.isPatientJCas(activeJCas)) {
				multiCas.reset();
			}
		}
	}
	
	
	protected void deActivateJcas(JCas jCasComponent) {
		JFSIndexRepository indexes = jCasComponent.getJFSIndexRepository();
		FSIterator<Annotation> tokenItr = indexes.getAnnotationIndex(
				BaseToken.type).iterator();
		while (tokenItr.hasNext()) {
			BaseToken token = (BaseToken) tokenItr.next();
			if (token.getBegin() == 0 && token.getEnd() == 0
					&& token.getPartOfSpeech().equals("ACTIVE")) {
				token.removeFromIndexes(jCasComponent);
				break;
			}
		}
	}

	
}