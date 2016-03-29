package org.healthnlp.deepphe.uima.ae;

import org.apache.log4j.Logger;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.xfer.CtakesToDroolsConverter;

import java.util.Iterator;

public class CasPreLoadedI2b2WriterAE extends JCasAnnotator_ImplBase {

	private JCas multiJCas;
	private JCas patientJCas;
	private KbPatient patient;
	
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
		CtakesToDroolsConverter ctakesToJessConverter = CtakesToDroolsConverter.getInstance();
		ctakesToJessConverter.setMultiJCas(multiJCas);
		ctakesToJessConverter.setPatientJCas(patientJCas);
		ctakesToJessConverter.setPatient(new KbPatient());
		ctakesToJessConverter.execute();
		patient = ctakesToJessConverter.getPatient();
	}
	
	private void replaceI2b2Data() {
		throw new UnsupportedOperationException( "I2B2DataDataWriter no longer exists." );
//		try {
//			final I2b2DemoDataSourceManager i2b2DataDataSourceManager = new I2b2DemoDataSourceManager();
//			final I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
//			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
//			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE2");
//			i2b2DataDataWriter.setPatient(patient);
//			i2b2DataDataWriter.clearExistingPatientObservations();
//			i2b2DataDataWriter.writeObservationsForPatient();
//			i2b2DataDataSourceManager.destroy();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}


}