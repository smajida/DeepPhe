package org.healthnlp.deepphe.i2b2;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;

import org.healthnlp.deepphe.i2b2.orm.i2b2data.ConceptDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DataDataSourceManager;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ObservationFact;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ObservationFactId;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.PatientDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.VisitDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.VisitDimensionId;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

public class I2B2DataDataWriter {

	private Date timeNow = new Date();
	private I2b2DataDataSourceManager dataSourceMgr;
	private List<KbPatient> patients;
	private KbPatient kbPatient;
	private KbEncounter kbEncounter;
	private KbSummary kbSummary;

	private int uploadId = 0;

	private String sourceSystemCd;

	private final TreeSet<KbSummary> activeSummaries = new TreeSet<KbSummary>(
			new Comparator<KbSummary>() {
				@Override
				public int compare(KbSummary o1, KbSummary o2) {
					return o1.getPath().compareTo(o2.getPath());
				}
			});

	public static void main(String[] args) {
		
		try {
			I2b2DataDataSourceManager i2b2DataDataSourceManager = new I2b2DataDataSourceManager();
			I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE2");
			i2b2DataDataWriter.execute();
			i2b2DataDataSourceManager.destroy();
			
			i2b2DataDataSourceManager = new I2b2DataDataSourceManager();
			i2b2DataDataWriter = new I2B2DataDataWriter();
			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE");
			i2b2DataDataWriter.execute();
			i2b2DataDataSourceManager.destroy();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/****
	 * 
	 *
	 * Patient
	 *
	 */
	public void clearExistingPatientObservations() throws SQLException {
		String sql = "delete from OBSERVATION_FACT where PATIENT_NUM = :patientNum AND SOURCESYSTEM_CD = :sourceSystemCd";
		SQLQuery sqlUpdate = dataSourceMgr.getSession().createSQLQuery(sql);
		sqlUpdate.setInteger("patientNum", kbPatient.getId());
		sqlUpdate.setString("sourceSystemCd", getSourceSystemCd());
		sqlUpdate.executeUpdate();
	}

	public PatientDimension fetchOrCreatePatient() {
		PatientDimension existingPatient = fetchPatient();
		if (existingPatient == null) {
			PatientDimension newPatient = newPatient();
			dataSourceMgr.getSession().saveOrUpdate(newPatient);
			Transaction tx = dataSourceMgr.getSession().beginTransaction();
			dataSourceMgr.getSession().flush();
			tx.commit();
			existingPatient = fetchPatient();
		}
		return existingPatient;
	}

	private PatientDimension fetchPatient() {
		PatientDimension patientDimension = new PatientDimension();
		patientDimension.setPatientNum(new BigDecimal(kbPatient.getId()));
		patientDimension.setSourcesystemCd(getSourceSystemCd());
		Query q = dataSourceMgr
				.getSession()
				.createQuery(
						"from PatientDimension as p where p.patientNum=:patientNum and p.sourcesystemCd=:sourcesystemCd");
		q.setProperties(patientDimension);
		PatientDimension result = (PatientDimension) q.uniqueResult();
		return result;
	}

	private PatientDimension newPatient() {
		PatientDimension patientDimension = new PatientDimension();
		Date timeNow = new Date();
		patientDimension.setPatientNum(new BigDecimal(kbPatient.getId()));
		patientDimension.setVitalStatusCd((String) null);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(1967, 3, 20);
		patientDimension.setBirthDate(calendar.getTime());
		patientDimension.setDeathDate(null);
		patientDimension.setSexCd("F");
		patientDimension.setAgeInYearsNum(new BigDecimal(78));
		patientDimension.setLanguageCd("Chinese");
		patientDimension.setRaceCd("asian");
		patientDimension.setMaritalStatusCd("single");
		patientDimension.setReligionCd("Budhism");
		patientDimension.setZipCd("15232");
		patientDimension
				.setStatecityzipPath("Zip codes\\Massachusetts\\Boston\\02115\\");
		patientDimension.setIncomeCd("Medium");
		patientDimension.setPatientBlob(null);
		patientDimension.setUpdateDate(timeNow);
		patientDimension.setDownloadDate(timeNow);
		patientDimension.setImportDate(timeNow);
		patientDimension.setSourcesystemCd(getSourceSystemCd());
		patientDimension.setUploadId(new BigDecimal(kbPatient.getId()));
		return patientDimension;
	}

	/****
	 * 
	 *
	 * Encounter
	 *
	 */
	public VisitDimension fetchOrCreateVisit() {
		VisitDimension existingVisit = fetchVisit();
		if (existingVisit == null) {
			VisitDimension newVisit = newVisit();
			dataSourceMgr.getSession().saveOrUpdate(newVisit);
			Transaction tx = dataSourceMgr.getSession().beginTransaction();
			dataSourceMgr.getSession().flush();
			tx.commit();
			existingVisit = fetchVisit();
		}
		return existingVisit;
	}

	private VisitDimension fetchVisit() {
		VisitDimension visitDimension = new VisitDimension();
		VisitDimensionId visitDimensionId = new VisitDimensionId();
		visitDimensionId.setPatientNum(new BigDecimal(kbPatient.getId()));
		visitDimensionId.setEncounterNum(new BigDecimal(kbEncounter.getId()));
		visitDimension.setId(visitDimensionId);
		visitDimension.setSourcesystemCd(getSourceSystemCd());
		Query q = dataSourceMgr
				.getSession()
				.createQuery(
						"from VisitDimension as v where v.id=:id and v.sourcesystemCd=:sourcesystemCd");
		q.setProperties(visitDimension);
		VisitDimension result = (VisitDimension) q.uniqueResult();
		return result;
	}

	private VisitDimension newVisit() {
		Date timeNow = new Date();
		VisitDimension visitDimension = new VisitDimension();
		VisitDimensionId visitId = new VisitDimensionId();
		visitId.setPatientNum(new BigDecimal(kbPatient.getId()));
		visitId.setEncounterNum(new BigDecimal(kbEncounter.getId()));
		visitDimension.setId(visitId);
		visitDimension.setActiveStatusCd("Active");
		visitDimension.setDownloadDate(timeNow);
		visitDimension.setEndDate(timeNow);
		visitDimension.setImportDate(timeNow);
		visitDimension.setInoutCd("in");
		visitDimension.setLengthOfStay(new BigDecimal(4.0d));
		visitDimension.setLocationCd("Pennsylvania");
		visitDimension.setLocationPath("Pittsburgh/Pennsylvania");
		visitDimension.setSourcesystemCd(getSourceSystemCd());
		visitDimension.setStartDate(timeNow);
		visitDimension.setUpdateDate(timeNow);
		visitDimension.setVisitBlob(null);
		visitDimension.setUploadId(new BigDecimal(99));
		return visitDimension;
	}

	/****
	 * 
	 *
	 * Concept (aka Summary)
	 *
	 */
	public ConceptDimension fetchOrCreateConcept() {
		ConceptDimension existingConcept = fetchConcept();
		if (existingConcept == null) {
			ConceptDimension newConcept = newConcept();
			dataSourceMgr.getSession().saveOrUpdate(newConcept);
			Transaction tx = dataSourceMgr.getSession().beginTransaction();
			dataSourceMgr.getSession().flush();
			tx.commit();
			existingConcept = fetchConcept();
		}

		return existingConcept;
	}

	private ConceptDimension fetchConcept() {
		ConceptDimension conceptDimension = new ConceptDimension();
		conceptDimension.setConceptCd(kbSummary.getCode());
		conceptDimension.setSourcesystemCd(getSourceSystemCd());
		Query q = dataSourceMgr
				.getSession()
				.createQuery(
						"from ConceptDimension as c where c.conceptCd=:conceptCd and c.sourcesystemCd=:sourcesystemCd");
		q.setProperties(conceptDimension);
		ConceptDimension result = (ConceptDimension) q.uniqueResult();
		return result;
	}
	
	

	private ConceptDimension newConcept() {
		ConceptDimension conceptDimension = new ConceptDimension();
		conceptDimension.setConceptPath(kbSummary.getPath() + "\\");
		conceptDimension.setConceptCd(kbSummary.getCode());
		conceptDimension.setNameChar(kbSummary.getNameChar());
		conceptDimension.setConceptBlob(null);
		conceptDimension.setUpdateDate(timeNow);
		conceptDimension.setDownloadDate(timeNow);
		conceptDimension.setImportDate(timeNow);
		conceptDimension.setSourcesystemCd(getSourceSystemCd());
		conceptDimension.setUploadId(new BigDecimal(uploadId++));
		return conceptDimension;
	}

	public void writeObservationsForPatient() {
		fetchOrCreatePatient();
		for (KbSummary summary : kbPatient.getSummaries()) {
			kbSummary = summary;
			fetchOrCreateConcept();
			writeObservation(kbPatient, null, kbSummary);
		}
		for (KbEncounter encounter : kbPatient.getEncounters()) {
			kbEncounter = encounter;
			fetchOrCreateVisit();
			for (KbSummary summary : kbEncounter.getSummaries()) {
				kbSummary = summary;
				fetchOrCreateConcept();
				writeObservation(kbPatient, kbEncounter, kbSummary);
			}
		}
	}

	public void execute() throws ClassNotFoundException, SQLException {
		cleanOldRecords();
//		writePatients();
//		writeEncounters();
//		writeConcepts();
//		writeObservations();
	}

	public void cleanOldRecords() throws SQLException {

		dataSourceMgr.getSession().clear();

		String sql = "delete from OBSERVATION_FACT where SOURCESYSTEM_CD = :sourceSystemCd";
		SQLQuery sqlUpdate = dataSourceMgr.getSession().createSQLQuery(sql);
		sqlUpdate.setString("sourceSystemCd", getSourceSystemCd());
		sqlUpdate.executeUpdate();

		sql = "delete from CONCEPT_DIMENSION where SOURCESYSTEM_CD = :sourceSystemCd";
		sqlUpdate = dataSourceMgr.getSession().createSQLQuery(sql);
		sqlUpdate.setString("sourceSystemCd", getSourceSystemCd());
		sqlUpdate.executeUpdate();

		sql = "delete from VISIT_DIMENSION where SOURCESYSTEM_CD = :sourceSystemCd";
		sqlUpdate = dataSourceMgr.getSession().createSQLQuery(sql);
		sqlUpdate.setString("sourceSystemCd", getSourceSystemCd());
		sqlUpdate.executeUpdate();

		sql = "delete from PATIENT_DIMENSION where SOURCESYSTEM_CD = :sourceSystemCd";
		sqlUpdate = dataSourceMgr.getSession().createSQLQuery(sql);
		sqlUpdate.setString("sourceSystemCd", getSourceSystemCd());
		sqlUpdate.executeUpdate();
	}

	@SuppressWarnings("unused")
	private void writePatients() throws SQLException {
		for (KbPatient patient : patients) {
			kbPatient = patient;
			PatientDimension patientDimension = newPatient();
			dataSourceMgr.getSession().saveOrUpdate(patientDimension);
		}
		Transaction tx = dataSourceMgr.getSession().beginTransaction();
		dataSourceMgr.getSession().flush();
		tx.commit();
	}

	@SuppressWarnings("unused")
	private void writeEncounters() {
		for (KbPatient kbPatient : patients) {
			for (KbEncounter kbEncounter : kbPatient.getEncounters()) {
				Date timeNow = new Date();
				VisitDimension visitDimension = new VisitDimension();
				VisitDimensionId visitId = new VisitDimensionId();
				visitId.setPatientNum(new BigDecimal(kbPatient.getId()));
				visitId.setEncounterNum(new BigDecimal(kbEncounter.getId()));
				visitDimension.setId(visitId);
				visitDimension.setActiveStatusCd("Active");
				visitDimension.setDownloadDate(timeNow);
				visitDimension.setEndDate(timeNow);
				visitDimension.setImportDate(timeNow);
				visitDimension.setInoutCd("in");
				visitDimension.setLengthOfStay(new BigDecimal(4.0d));
				visitDimension.setLocationCd("Pennsylvania");
				visitDimension.setLocationPath("Pittsburgh/Pennsylvania");
				visitDimension.setSourcesystemCd(getSourceSystemCd());
				visitDimension.setStartDate(timeNow);
				visitDimension.setUpdateDate(timeNow);
				visitDimension.setVisitBlob(null);
				visitDimension.setUploadId(new BigDecimal(99));
				dataSourceMgr.getSession().saveOrUpdate(visitDimension);
			}
		}

		Transaction tx = dataSourceMgr.getSession().beginTransaction();
		dataSourceMgr.getSession().flush();
		tx.commit();
	}

	@SuppressWarnings("unused")
	private void writeConcepts() throws SQLException {
		activeSummaries.clear();
		for (KbPatient kbPatient : patients) {
			for (KbSummary kbSummary : kbPatient.getSummaries()) {
				if (kbSummary.getIsActive() == 1) {
					activeSummaries.add(kbSummary);
				}
			}
			for (KbEncounter kbEncounter : kbPatient.getEncounters()) {
				for (KbSummary kbSummary : kbEncounter.getSummaries()) {
					if (kbSummary.getIsActive() == 1) {
						activeSummaries.add(kbSummary);
					}
				}
			}
		}
		for (KbSummary kbSummary : activeSummaries) {
			ConceptDimension conceptDimension = new ConceptDimension();
			conceptDimension.setConceptPath(kbSummary.getPath() + "\\");
			conceptDimension.setConceptCd(kbSummary.getBaseCode());
			conceptDimension.setNameChar(kbSummary.getNameChar());
			conceptDimension.setConceptBlob(null);
			conceptDimension.setUpdateDate(timeNow);
			conceptDimension.setDownloadDate(timeNow);
			conceptDimension.setImportDate(timeNow);
			conceptDimension.setSourcesystemCd(getSourceSystemCd());
			conceptDimension.setUploadId(new BigDecimal(uploadId++));
			dataSourceMgr.getSession().saveOrUpdate(conceptDimension);
		}
		Transaction tx = dataSourceMgr.getSession().beginTransaction();
		dataSourceMgr.getSession().flush();
		tx.commit();
	}

	@SuppressWarnings("unused")
	private void writeObservations() throws SQLException {
		for (KbPatient kbPatient : patients) {
			for (KbSummary kbSummary : kbPatient.getSummaries()) {
				if (kbSummary.getIsActive() == 1) {
					writeObservation(kbPatient, null, kbSummary);
				}
			}
			for (KbEncounter kbEncounter : kbPatient.getEncounters()) {
				for (KbSummary kbSummary : kbEncounter.getSummaries()) {
					if (kbSummary.getIsActive() == 1) {
						writeObservation(kbPatient, kbEncounter, kbSummary);
					}
				}
			}
		}

		Transaction tx = dataSourceMgr.getSession().beginTransaction();
		dataSourceMgr.getSession().flush();
		tx.commit();
	}
	
	private ObservationFact fetchObservationFact(ObservationFactId observationFactId) {
		Query q = dataSourceMgr
				.getSession()
				.createQuery(
						"from ObservationFact as o where o.id=:id and o.sourcesystemCd=:sourcesystemCd");
		q.setParameter("id", observationFactId);
		q.setParameter("sourcesystemCd", getSourceSystemCd());
		ObservationFact result = (ObservationFact) q.uniqueResult();
		return result;
	}

	private void writeObservation(KbPatient kbPatient, KbEncounter kbEncounter,
			KbSummary kbSummary) {

		ObservationFactId observationFactId = new ObservationFactId();
		if (kbEncounter != null) {
			observationFactId.setEncounterNum(new BigDecimal(kbEncounter
					.getId()));
		} else {
			observationFactId
					.setEncounterNum(new BigDecimal(kbPatient.getId()));
		}
		
		observationFactId.setPatientNum(new BigDecimal(kbPatient.getId()));
		observationFactId.setConceptCd(kbSummary.getCode());
		observationFactId.setProviderId(getSourceSystemCd());
		observationFactId.setInstanceNum(1L);
		observationFactId.setModifierCd("@");
		observationFactId.setStartDate(timeNow);

		ObservationFact observationFact = fetchObservationFact(observationFactId);
		 
		if (observationFact == null) {
			
			System.out.println("\n\n\nWriting obs (" + observationFactId + ")\n\n");

			observationFact = new ObservationFact();
			observationFact.setId(observationFactId);
			observationFact.setValtypeCd("@");
			observationFact.setTvalChar("@");
			observationFact.setNvalNum(new BigDecimal(-1));
			observationFact.setValueflagCd("@");
			observationFact.setQuantityNum(new BigDecimal(1));
			observationFact.setUnitsCd("@");
			observationFact.setEndDate(timeNow);
			observationFact.setLocationCd("@");
			observationFact.setObservationBlob(null);
			observationFact.setConfidenceNum(new BigDecimal(1.0));
			observationFact.setUpdateDate(timeNow);
			observationFact.setDownloadDate(timeNow);
			observationFact.setImportDate(timeNow);
			observationFact.setSourcesystemCd(getSourceSystemCd());
			observationFact.setUploadId(new BigDecimal(uploadId++));

			dataSourceMgr.getSession().saveOrUpdate(observationFact);
		}

	}

	public void setPatientNum(int patientNum) {
	}

	public String getSourceSystemCd() {
		return sourceSystemCd;
	}

	public void setSourceSystemCd(String sourceSystemCd) {
		this.sourceSystemCd = sourceSystemCd;
	}

	public I2b2DataDataSourceManager getDataSourceMgr() {
		return dataSourceMgr;
	}

	public void setDataSourceMgr(I2b2DataDataSourceManager dataSourceMgr) {
		this.dataSourceMgr = dataSourceMgr;
	}

	public List<KbPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;
	}

	public void setPatient(KbPatient kbPatient) {
		this.kbPatient = kbPatient;
	}

	public KbEncounter getEncounter() {
		return kbEncounter;
	}

	public void setEncounter(KbEncounter encounter) {
		this.kbEncounter = encounter;
	}

}
