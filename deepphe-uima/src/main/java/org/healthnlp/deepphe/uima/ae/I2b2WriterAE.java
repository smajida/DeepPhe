package org.healthnlp.deepphe.uima.ae;

import java.sql.SQLException;
import java.util.Base64;
import java.util.Iterator;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.i2b2.I2B2DataDataWriter;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DataDataSourceManager;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Summary;

public class I2b2WriterAE extends JCasAnnotator_ImplBase {

	private JCas forI2b2JCas;
	private Patient patient;
	
	static private final Logger logger = Logger
			.getLogger(I2b2WriterAE.class);

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		try {
			for (Iterator<JCas> iterator = multiJCas.getViewIterator(); iterator
					.hasNext();) {
				forI2b2JCas = iterator.next();
				if (forI2b2JCas.getViewName().equals("forI2b2")) {
					logger.debug("Pushing serialized patient to i2b2");
					uploadPatientDAG();
					replaceI2b2Data();
					break;
				}
			}
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}
	
	private void uploadPatientDAG()
			throws CASException {
		try {
			patient = (Patient) SerializationUtils.deserialize(Base64.getDecoder().decode(forI2b2JCas.getDocumentText()));		
			for (Summary s : patient.getSummaries()) {
				if (s.getCode().length() > 50) {
					truncateSummaryCodes(s);
				}
			}
			for (Encounter e : patient.getEncounters()) {
				for (Summary s : e.getSummaries()) {
					if (s.getCode().length() > 50) {
						truncateSummaryCodes(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void truncateSummaryCodes(Summary s) {
		System.err.println("Truncating summary " + s);
		String[] parts = s.getCode().split("#");
		s.setCode(parts[1]);
		s.setBaseCode(parts[1]);
		System.out.println("New code is " + s);
	}

	private void replaceI2b2Data() {
		try {
			final I2b2DataDataSourceManager i2b2DataDataSourceManager = new I2b2DataDataSourceManager();
			final I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE2");
			i2b2DataDataWriter.setPatient(patient);
			i2b2DataDataWriter.clearExistingPatientObservations();
			i2b2DataDataWriter.writeObservationsForPatient();
			i2b2DataDataSourceManager.destroy();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}