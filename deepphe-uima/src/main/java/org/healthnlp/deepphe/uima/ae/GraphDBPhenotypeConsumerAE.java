package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.neo4j.io.fs.FileUtils;
import org.neo4j.ogm.service.Components;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;



public class GraphDBPhenotypeConsumerAE extends JCasAnnotator_ImplBase {
	private SessionFactory sessionFactory;

	public static final String PARAM_DBPATH = "DBPATH";
	public static final String PARAM_USERNAME = "USERNAME";
	public static final String PARAM_PASSWORD = "PASSWORD";

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		sessionFactory = initializeGraphDatabase((String)aContext.getConfigParameterValue(PARAM_DBPATH));
	}
	
	

	public SessionFactory initializeGraphDatabase(String dbPath) throws ResourceInitializationException {
		File f = new File(dbPath);
		if(f.exists()){
			try {
				FileUtils.deleteRecursively(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Components.configuration()
        .driverConfiguration()
        .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
        .setURI(f.toURI().toString());

		SessionFactory sf = new SessionFactory("org.healthnlp.deepphe.fhir");

		return sf;
	}



	@Override
	public void destroy() {
		
		super.destroy();
	}

	private static LinkedHashMap<String, Integer> patientNameIDMap = new LinkedHashMap<String, Integer>();
	private static int patientID = 0;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		processPatient(sessionFactory.openSession(),PhenotypeResourceFactory.loadMedicalRecord(jcas));
	}

	public void processPatient(Session session, MedicalRecord mr) {
		Transaction tx = session.beginTransaction();
		
		session.save(mr);
		tx.commit();
		tx.close();

	}


}
