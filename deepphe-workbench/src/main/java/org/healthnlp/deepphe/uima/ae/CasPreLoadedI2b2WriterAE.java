package org.healthnlp.deepphe.uima.ae;

import java.sql.SQLException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.i2b2.I2B2DataDataWriter;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DataDataSourceManager;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.xfer.CtakesToJessConverter;

public class CasPreLoadedI2b2WriterAE extends JCasAnnotator_ImplBase {

	private JCas multiJCas;
	private JCas patientJCas;
	private Patient patient;
	
	static private final Logger logger = Logger
			.getLogger(CasPreLoadedI2b2WriterAE.class);

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		try {
			this.multiJCas = multiJCas;
			for (Iterator<JCas> iterator = multiJCas.getViewIterator(); iterator
					.hasNext();) {
				patientJCas = iterator.next();
				if (CasDetector.isPatientJCas(patientJCas)) {
					logger.debug("Processing patient");
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
		CtakesToJessConverter ctakesToJessConverter = new CtakesToJessConverter();
		ctakesToJessConverter.setMultiJCas(multiJCas);
		ctakesToJessConverter.setPatientJCas(patientJCas);
		ctakesToJessConverter.setPatient(new Patient());
		ctakesToJessConverter.execute();
		patient = ctakesToJessConverter.getPatient();
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