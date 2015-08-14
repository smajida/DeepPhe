package org.healthnlp.deepphe.uima.ae;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Summary;
import org.healthnlp.deepphe.xfer.CtakesToJessConverter;

public class TaggedI2b2WriterAE extends TaggedSummarizableAE {

	private JCas multiJCas;
	private JCas patientJCas;
	private Patient patient;

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		super.process(multiJCas);
		this.multiJCas = multiJCas;
		patientJCas = fetchActiveJCas(multiJCas);
		if (CasDetector.isPatientJCas(patientJCas)) {
			try {
				uploadPatientDAG();
				displayPatient();
			} catch (CASException e) {
				throw new AnalysisEngineProcessException(e);
			}
		}
	}
	
	private void displayPatient() {
		System.out.println(patient);
		for (Summary summary : patient.getSummaries()) {
			System.out.println(summary);
		}
		
	}

	private void uploadPatientDAG()
			throws CASException {
		CtakesToJessConverter ctakesToJessConverter = new CtakesToJessConverter();
		ctakesToJessConverter.setMultiJCas(multiJCas);
		ctakesToJessConverter.setPatientJCas(patientJCas);
		ctakesToJessConverter.setPatient(new Patient());
		ctakesToJessConverter.execute();
		patient = ctakesToJessConverter.getPatient();
	}

}