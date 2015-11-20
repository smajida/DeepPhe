package org.healthnlp.deepphe.workbench.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ConceptDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DemoDataSourceManager;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ObservationFact;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ObservationFactId;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.PatientDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ProviderDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.ProviderDimensionId;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.VisitDimension;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.VisitDimensionId;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.workbench.digestion.ExpertDocument;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Controller {

	private static final String CONST_PATH_TO_GOLD_SET = "F:\\Anafora\\breast_gold2";

	private I2b2DemoDataSourceManager i2b2DataDataSourceManager;
	private Session i2b2DataSession;

	private String sourcesystemCd = "DpheAnafora";

	// F:\Anafora\breast_gold2\patient01_report002_NOTE\patient01_report002_NOTE
	// F:\Anafora\breast_gold2\patient01_report002_NOTE\patient01_report002_NOTE.UmlsDeepPhe-Adjudication.dave.inprogress.xml
	// F:\Anafora\breast_gold2\patient01_report002_NOTE\patient01_report002_NOTE.UmlsDeepPhe.dave.completed.xml
	// F:\Anafora\breast_gold2\patient01_report002_NOTE\patient01_report002_NOTE.UmlsDeepPhe.guergana.completed.xml

	private Date timeNow = new Date();
	private String parsedPatientLimsId;
	private String parsedVisitLimsId;
	private String parsedNoteKind;
	private long idCounter = 0L;
	
	// Roving window of the star
	private PatientDimension currentDbPatient;
	private VisitDimension currentDbVisit;
	private ProviderDimension currentDbProvider;
	private ConceptDimension currentDbConcept;
	private String currentDbModifier;
	private String currentTextToStore;
	private ObservationFact currentDbObservation;
	
	// Stored for quick access
	private ProviderDimension cTakesDbProvider;
	private ConceptDimension codedDbConcept;

	private List<KbPatient> kbPatients = new ArrayList<KbPatient>();

	public static void main(String[] args) {
		Controller controller = new Controller();
		try {
			controller.cleanRdbmsData();
			controller.uploadFsDirectoryToRdbms();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.constructFastDagFromRdbms();
		controller.closeUp();
	}

	public Controller() {
		i2b2DataDataSourceManager = new I2b2DemoDataSourceManager();
		i2b2DataSession = i2b2DataDataSourceManager.getSession();
		findMaxIdFromObservationFact();
		findMaxIdFromProviderDimension();
		findOrCreateProvider("DpheProvider:cTakes");
		findOrCreateConcept("DpheConcept:coded");
		cTakesDbProvider = currentDbProvider;
		codedDbConcept = currentDbConcept;
	}

	public void closeUp() {
		i2b2DataDataSourceManager.destroy();
	}

	public void cleanRdbmsData() {
		i2b2DataSession = i2b2DataDataSourceManager.getSession();
		I2b2DemoDataCleaner cleaner = new I2b2DemoDataCleaner();
		cleaner.setDataSourceMgr(i2b2DataDataSourceManager);
		cleaner.setSourceSystemCd(sourcesystemCd);
		cleaner.execute();
	}

	public void uploadFsDirectoryToRdbms() throws IOException {
		uploadFsDirectoryRawTexts();
		uploadFsDirectoryAnaforaTexts();
	}

	private void uploadFsDirectoryAnaforaTexts() throws IOException {
		File goldDirectory = new File(CONST_PATH_TO_GOLD_SET);
		File[] patientFolders = goldDirectory.listFiles();
		for (File patientFolder : patientFolders) {
			cachePatientParametersFromFileName(patientFolder);
			findOrCreatePatient(parsedPatientLimsId);
			findOrCreateVisit(parsedVisitLimsId);
			currentDbModifier = parsedNoteKind;

			File[] annotationFiles = patientFolder.listFiles();
			for (File annotationFile : annotationFiles) {
				if (annotationFile.getName().equals(patientFolder.getName())) {
					; // skip the raw text already stored
				} else {
					// patient01_report002_NOTE.UmlsDeepPhe-Adjudication.dave.inprogress.xml
					// patient01_report002_NOTE.UmlsDeepPhe.dave.completed.xml
					// patient01_report002_NOTE.UmlsDeepPhe.guergana.completed.xml
					Pattern pattern = Pattern
							.compile("^[^:]+:([^:]+):([^:]+):([^:]+):xml$");
					Matcher matcher = pattern.matcher(annotationFile.getName()
							.replaceAll("\\.", ":"));
					if (matcher.matches()) {
						String parsedProjectName = matcher.group(1);
						String parsedAnnotatorName = matcher.group(2);
						String parsedStatusName = matcher.group(3);
						if (parsedProjectName.toLowerCase().contains(
								"adjudication")
								|| parsedAnnotatorName.equals("gold")) {
							parsedStatusName = "adjudicated";
						}
						parsedAnnotatorName = "DpheProvider:"
								+ parsedAnnotatorName;
						parsedStatusName = "DpheConcept:" + parsedStatusName;
						currentTextToStore = FileUtils
								.file2String(annotationFile);
						int numberOfAnnotations = countAnnotations(currentTextToStore);
						System.out.println(numberOfAnnotations + ", "
								+ parsedAnnotatorName + ", " + parsedStatusName
								+ ", " + annotationFile.getName());
						findOrCreateProvider(parsedAnnotatorName);
						findOrCreateConcept(parsedStatusName);
						findOrCreateObservation();
					} else {
						System.err.println("Failed to parse "
								+ annotationFile.getName());
					}
				}
			}
		}
	}

	private void uploadFsDirectoryRawTexts() throws IOException {
		File goldDirectory = new File(CONST_PATH_TO_GOLD_SET);
		File[] patientFolders = goldDirectory.listFiles();
		for (File patientFile : patientFolders) {
			// Store the raw text
			cachePatientParametersFromFileName(patientFile);
			findOrCreatePatient(parsedPatientLimsId);
			findOrCreateVisit(parsedVisitLimsId);
			findOrCreateProvider("DpheProvider:clinic");
			findOrCreateConcept("DpheConcept:unprocessed");
			currentDbModifier = parsedNoteKind;
			File rawNoteFile = new File(patientFile, patientFile.getName());
			currentTextToStore = FileUtils.file2String(rawNoteFile);
			findOrCreateObservation();

			// Store an xmi place holder
			findOrCreateProvider("DpheProvider:cTakes");
			findOrCreateConcept("DpheConcept:coded");
			currentTextToStore = "";
			findOrCreateObservation();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void constructFastDagFromRdbms() {
		findOrCreateProvider("DpheProvider:clinic");
		findOrCreateConcept("DpheConcept:unprocessed");
		String hql = "from ObservationFact o where ";
		hql += " o.id.providerId = :providerId and ";
		hql += " o.id.conceptCd = :conceptCd and ";
		hql += " o.sourcesystemCd like :sourceSystemCd ";
		hql += " order by o.id.patientNum, o.id.encounterNum, o.id.modifierCd ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setString("providerId", currentDbProvider.getId().getProviderId());
		query.setString("conceptCd", currentDbConcept.getConceptCd());
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ObservationFact> observationFacts = query.list();
		BigDecimal lastPatientNum = new BigDecimal(-1.0d);
		KbPatient kbPatient = null;
		int patientSeq = 0;
		int encounterSeq = 0;
		for (ObservationFact observationFact : observationFacts) {
			if (observationFact.getId().getPatientNum().intValue() > lastPatientNum.intValue()) {
				kbPatient = new KbPatient();
				kbPatient.setId(observationFact.getId().getPatientNum().intValue());
				kbPatient.setSequence(patientSeq++);
				kbPatients.add(kbPatient);
				lastPatientNum = observationFact.getId().getPatientNum();
				encounterSeq = 0;
			}
			KbEncounter enc = new KbEncounter();
			enc.setId((int) observationFact.getId().getInstanceNum());
			enc.setKind(observationFact.getId().getModifierCd());
			enc.setSequence(encounterSeq++);
			enc.setContent(observationFact.getObservationBlob());
			kbPatient.getEncounters().add(enc);
		}
	}

	@SuppressWarnings("unchecked")
	public void constructPatientDagFromRdbms() {

		String hql = "from PatientDimension p where ";
		hql += " p.sourcesystemCd like :sourceSystemCd ";
		hql += " order by p.patientNum ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setString("sourceSystemCd", sourcesystemCd);
		List<PatientDimension> sessionBoundPatients = (List<PatientDimension>) query
				.list();
		int patientSeq = 0;
		kbPatients.clear();
		for (PatientDimension sessionBoundPatient : sessionBoundPatients) {
			KbPatient kbPatient = new KbPatient();
			kbPatient.setId(sessionBoundPatient.getPatientNum().intValue());
			kbPatient.setSequence(patientSeq++);
			kbPatients.add(kbPatient);
			constructEncounterDagFromPatient(sessionBoundPatient, kbPatient);
		}
		i2b2DataDataSourceManager.getSession().clear();
	}

	@SuppressWarnings("unchecked")
	private void constructEncounterDagFromPatient(
			PatientDimension rdbmsPatient, KbPatient kbPatient) {
		String hql = "from VisitDimension v where ";
		hql += " v.id.patientNum = :patientNum and ";
		hql += " v.sourcesystemCd like :sourceSystemCd ";
		hql += " order by v.id.encounterNum ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setBigDecimal("patientNum", rdbmsPatient.getPatientNum());
		query.setString("sourceSystemCd", sourcesystemCd);
		List<VisitDimension> rdbmsVisits = (List<VisitDimension>) query.list();
		int encounterSeq = 0;
		for (VisitDimension rdbmsVisit : rdbmsVisits) {
			currentDbPatient = rdbmsPatient;
			currentDbVisit = rdbmsVisit;
			findOrCreateProvider("DpheProvider:clinic");
			findOrCreateConcept("DpheConcept:unprocessed");
			List<ObservationFact> observationFacts = pullEncounterContent();
			for (ObservationFact observationFact : observationFacts) {
				KbEncounter enc = new KbEncounter();
				enc.setId((int) observationFact.getId().getInstanceNum());
				enc.setKind(observationFact.getId().getModifierCd());
				enc.setSequence(encounterSeq++);
				enc.setContent(observationFact.getObservationBlob());
				kbPatient.getEncounters().add(enc);

				// Link the best Anafora annotation xml to the
				// Encounter using a KbSummary record
				KbSummary annotations = new KbSummary();
				annotations.setCode("Dphe:Anafora");
				String bestAnaforaAnnotation = findBestAnnotationXml(observationFact);
				annotations.setPath(bestAnaforaAnnotation);
				enc.addSummary(annotations);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void findMaxIdFromObservationFact() {
		String hql = "from ObservationFact o where";
		hql += " o.sourcesystemCd like :sourceSystemCd";
		hql += " order by o.id.instanceNum desc ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ObservationFact> observations = (List<ObservationFact>) query
				.list();
		if (!observations.isEmpty()) {
			long observationIdAsLong = observations.iterator().next().getId()
					.getInstanceNum();
			observationIdAsLong++;
			idCounter = Math.max(idCounter, observationIdAsLong);
			System.out
					.println("findMaxIdFromObservationFact resetting idCounter to "
							+ idCounter);
		}
		i2b2DataSession.clear();
	}

	@SuppressWarnings("unchecked")
	private void findMaxIdFromProviderDimension() {
		String hql = "from ProviderDimension o where";
		hql += " o.sourcesystemCd like :sourceSystemCd";
		hql += " order by o.id.providerId desc ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ProviderDimension> observations = (List<ProviderDimension>) query
				.list();
		if (!observations.isEmpty()) {
			long providerIdAsLong = Long.parseLong(observations.iterator()
					.next().getId().getProviderId());
			providerIdAsLong++;
			idCounter = Math.max(idCounter, providerIdAsLong);
			System.out
					.println("findMaxIdFromProviderDimension resetting idCounter to "
							+ idCounter);
		}
		i2b2DataSession.clear();
	}

	@SuppressWarnings("unchecked")
	private String findBestAnnotationXml(ObservationFact observationFact) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<data>");
		sb.append("</data>");
		String result = sb.toString();

		findOrCreateProvider("DpheProvider:clinic");
		currentDbModifier = observationFact.getId().getModifierCd();

		String hql = "from ObservationFact o where ";
		hql += " o.id.patientNum = :patientNum and ";
		hql += " o.id.encounterNum = :encounterNum and ";
		hql += " o.id.providerId != :providerId and ";
		hql += " o.id.conceptCd in " + getAnaforaStatusesList() + " and ";
		hql += " o.id.modifierCd = :modifier and ";
		hql += " o.sourcesystemCd like :sourceSystemCd ";
		hql += " order by o.id.conceptCd ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setBigDecimal("patientNum", observationFact.getId()
				.getPatientNum());
		query.setBigDecimal("encounterNum", observationFact.getId()
				.getEncounterNum());
		query.setString("providerId", currentDbProvider.getId().getProviderId());
		query.setString("modifier", currentDbModifier);
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ObservationFact> observations = (List<ObservationFact>) query
				.list();
		for (ObservationFact obs : observations) {
			if (countAnnotations(obs.getObservationBlob()) > 0) {
				result = obs.getObservationBlob();
				break;
			}
		}
		return result;
	}

	private int countAnnotations(String xmlContent) {
		int result = 0;
		try {
			ExpertDocument anaforaDoc = new ExpertDocument();
			anaforaDoc.setContent(xmlContent);
			anaforaDoc.cacheEntities();
			anaforaDoc.iterate();
			while (anaforaDoc.hasNext()) {
				anaforaDoc.next();
				result++;
			}
		} catch (Exception x) {
			System.err.println(xmlContent);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private List<ObservationFact> pullEncounterContent() {
		String hql = "";
		hql = "from ObservationFact o where ";
		hql += " o.id.patientNum = :patientNum and ";
		hql += " o.id.encounterNum = :encounterNum and ";
		hql += " o.id.providerId = :providerId and ";
		hql += " o.id.conceptCd = :conceptCd and ";
		hql += " o.sourcesystemCd like :sourceSystemCd";
		Query query = i2b2DataSession.createQuery(hql);
		query.setBigDecimal("patientNum", currentDbPatient.getPatientNum());
		query.setBigDecimal("encounterNum", currentDbVisit.getId()
				.getEncounterNum());
		query.setString("providerId", currentDbProvider.getId().getProviderId());
		query.setString("conceptCd", currentDbConcept.getConceptCd());
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ObservationFact> observationFacts = query.list();
		return observationFacts;
	}

	private void findOrCreateObservation() throws IOException {
		findObservationByDimensions();
		if (currentDbObservation == null) {
			createObservation();
		}
	}

	private void createObservation() {
		ObservationFactId observationFactId = new ObservationFactId();
		observationFactId.setInstanceNum(idCounter++);
		observationFactId.setConceptCd(currentDbConcept.getConceptCd());
		observationFactId.setEncounterNum(currentDbVisit.getId()
				.getEncounterNum());
		observationFactId.setModifierCd(currentDbModifier);
		observationFactId.setPatientNum(currentDbPatient.getPatientNum());
		observationFactId.setProviderId(currentDbProvider.getId()
				.getProviderId());
		observationFactId.setStartDate(timeNow);
		currentDbObservation = new ObservationFact();
		currentDbObservation.setId(observationFactId);
		currentDbObservation.setObservationBlob(currentTextToStore);
		currentDbObservation.setConfidenceNum(BigDecimal.ONE);
		currentDbObservation.setDownloadDate(timeNow);
		currentDbObservation.setEndDate(timeNow);
		currentDbObservation.setImportDate(timeNow);
		currentDbObservation.setLocationCd("PGH");
		currentDbObservation.setNvalNum(BigDecimal.ONE);
		currentDbObservation.setQuantityNum(BigDecimal.ONE);
		currentDbObservation.setTvalChar("NA");
		currentDbObservation.setUnitsCd("ug");
		currentDbObservation.setUpdateDate(timeNow);
		currentDbObservation.setUploadId(BigDecimal.ZERO);
		currentDbObservation.setValtypeCd("NA");
		currentDbObservation.setValtypeCd("NA");
		currentDbObservation.setSourcesystemCd(sourcesystemCd);
		i2b2DataSession.saveOrUpdate(currentDbObservation);
		Transaction tx = i2b2DataSession.beginTransaction();
		i2b2DataSession.flush();
		tx.commit();
		i2b2DataSession.clear();
	}

	private void findObservationByDimensions() {
		findObservationByDimensions(currentDbPatient.getPatientNum(),
				currentDbVisit.getId().getEncounterNum(), currentDbProvider
						.getId().getProviderId(),
				currentDbConcept.getConceptCd(), currentDbModifier);
	}
	
	private void findObservationByDimensions(BigDecimal patientNum,
			BigDecimal encounterNum, String providerId, String conceptCd,
			String modifier) {
		String hql = "from ObservationFact o where ";
		hql += " o.id.patientNum = :patientNum and ";
		hql += " o.id.encounterNum = :encounterNum and ";
		hql += " o.id.providerId = :providerId and ";
		hql += " o.id.conceptCd = :conceptCd and ";
		hql += " o.id.modifierCd = :modifier and ";
		hql += " o.sourcesystemCd like :sourceSystemCd";
		Query query = i2b2DataSession.createQuery(hql);
		query.setBigDecimal("patientNum", patientNum);
		query.setBigDecimal("encounterNum", encounterNum);
		query.setString("providerId", providerId);
		query.setString("conceptCd", conceptCd);
		query.setString("modifier", modifier);
		query.setString("sourceSystemCd", sourcesystemCd);
		currentDbObservation = (ObservationFact) query.uniqueResult();
		nullSafeEvict(currentDbObservation);
	}

	private void findObservationByInstanceNum(long instanceNum) {
		String hql = "from ObservationFact o where ";
		hql += " o.id.instanceNum = :instanceNum and ";
		hql += " o.sourcesystemCd like :sourceSystemCd";
		Query query = i2b2DataSession.createQuery(hql);
		query.setLong("instanceNum", instanceNum);
		query.setString("sourceSystemCd", sourcesystemCd);
		currentDbObservation = (ObservationFact) query.uniqueResult();
		nullSafeEvict(currentDbObservation);
	}

	public String findObservationTextByInstanceNum(long instanceNum) {
		findObservationByInstanceNum(instanceNum);
		String result = (currentDbObservation != null) ? currentDbObservation.getObservationBlob() : null;
		return result;
	}

	private void findOrCreateProvider(String providerNameChar) {
		currentDbProvider = findProviderByNameChar(providerNameChar);
		if (currentDbProvider == null) {
			currentDbProvider = new ProviderDimension();
			ProviderDimensionId providerId = new ProviderDimensionId();
			providerId.setProviderId(padId(idCounter++));
			providerId.setProviderPath(providerNameChar);
			currentDbProvider.setId(providerId);
			currentDbProvider.setImportDate(timeNow);
			currentDbProvider.setDownloadDate(timeNow);
			currentDbProvider.setNameChar(providerNameChar);
			currentDbProvider.setProviderBlob(providerNameChar);
			currentDbProvider.setUpdateDate(timeNow);
			currentDbProvider.setUploadId(new BigDecimal(1.0d));
			currentDbProvider.setSourcesystemCd(sourcesystemCd);
			i2b2DataSession.saveOrUpdate(currentDbProvider);
			Transaction tx = i2b2DataSession.beginTransaction();
			i2b2DataSession.flush();
			tx.commit();
			nullSafeEvict(currentDbProvider);
		}
	}

	@SuppressWarnings("unchecked")
	private String getAnaforaStatusesList() {
		String hql = "from ConceptDimension c where ";
		hql += " c.conceptCd in ('DpheConcept:adjudicated', 'DpheConcept:completed', 'DpheConcept:inprogress') and ";
		hql += " c.sourcesystemCd like :sourceSystemCd ";
		hql += " order by c.conceptCd ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ConceptDimension> statusConcepts = (List<ConceptDimension>) query
				.list();
		final String[] conceptCds = new String[statusConcepts.size()];
		for (int idx = 0; idx < conceptCds.length; idx++) {
			conceptCds[idx] = "'" + statusConcepts.get(idx).getConceptCd()
					+ "'";
		}
		return "(" + StringUtils.join(conceptCds, ",") + ")";
	}

	private ProviderDimension findProviderByNameChar(String providerNameChar) {
		Query query = i2b2DataSession
				.createQuery("from ProviderDimension p where nameChar like :providerNameChar and sourcesystemCd like :sourceSystemCd");
		query.setString("providerNameChar", providerNameChar);
		query.setString("sourceSystemCd", sourcesystemCd);
		ProviderDimension provider = (ProviderDimension) query.uniqueResult();
		if (provider != null) {
			i2b2DataSession.evict(provider);
		}
		return provider;
	}

	private void findOrCreateConcept(String conceptCd) {
		findConceptByConceptCd(conceptCd);
		if (currentDbConcept == null) {
			currentDbConcept = new ConceptDimension();
			currentDbConcept.setConceptCd(conceptCd);
			currentDbConcept.setConceptPath(conceptCd);
			currentDbConcept.setDownloadDate(timeNow);
			currentDbConcept.setImportDate(timeNow);
			currentDbConcept.setNameChar(conceptCd);
			currentDbConcept.setUpdateDate(timeNow);
			currentDbConcept.setUploadId(new BigDecimal(1.0d));
			currentDbConcept.setSourcesystemCd(sourcesystemCd);
			i2b2DataSession.saveOrUpdate(currentDbConcept);
			Transaction tx = i2b2DataSession.beginTransaction();
			i2b2DataSession.flush();
			tx.commit();
		}
		nullSafeEvict(currentDbConcept);
	}

	private void findConceptByConceptCd(String conceptCd) {
		Query query = i2b2DataSession
				.createQuery("from ConceptDimension p where conceptCd like :conceptCd and sourcesystemCd like :sourceSystemCd");
		query.setString("conceptCd", conceptCd);
		query.setString("sourceSystemCd", sourcesystemCd);
		currentDbConcept = (ConceptDimension) query.uniqueResult();
		nullSafeEvict(currentDbConcept);
	}

	private void cachePatientParametersFromFileName(File reportFile) {
		Pattern extractionPattern = Pattern
				.compile("patient(\\d+)_report(\\d+)_(.+)");
		Matcher matcher = extractionPattern.matcher(reportFile.getName());
		if (matcher.matches()) {
			parsedPatientLimsId = padId(Long.parseLong(matcher.group(1)));
			parsedVisitLimsId = parsedPatientLimsId + ":"
					+ padId(Long.parseLong(matcher.group(2)));
			parsedNoteKind = matcher.group(3);
		}
	}

	private void findOrCreatePatient(String limsAccession) {
		currentDbPatient = findPatientByLimsAccession(limsAccession);
		if (currentDbPatient == null) {
			currentDbPatient = new PatientDimension();
			currentDbPatient.setPatientNum(new BigDecimal(padId(idCounter++)));
			currentDbPatient.setPatientBlob(limsAccession);
			currentDbPatient.setAgeInYearsNum(new BigDecimal(0.0d));
			currentDbPatient.setBirthDate(timeNow);
			currentDbPatient.setDeathDate(timeNow);
			currentDbPatient.setDownloadDate(timeNow);
			currentDbPatient.setImportDate(timeNow);
			currentDbPatient.setIncomeCd("NA");
			currentDbPatient.setLanguageCd("NA");
			currentDbPatient.setMaritalStatusCd("NA");
			currentDbPatient.setRaceCd("NA");
			currentDbPatient.setReligionCd("NA");
			currentDbPatient.setSexCd("NA");
			currentDbPatient.setStatecityzipPath("NA");
			currentDbPatient.setUpdateDate(timeNow);
			currentDbPatient.setUploadId(new BigDecimal(0.0d));
			currentDbPatient.setVitalStatusCd("NA");
			currentDbPatient.setZipCd("NA");
			currentDbPatient.setSourcesystemCd(sourcesystemCd);
			i2b2DataSession.saveOrUpdate(currentDbPatient);
			Transaction tx = i2b2DataSession.beginTransaction();
			i2b2DataSession.flush();
			tx.commit();
			i2b2DataSession.clear();
		}
	}

	private void findPatientById(int id) {
		Query query = i2b2DataSession
				.createQuery("from PatientDimension p where p.id like :id and sourcesystemCd like :sourceSystemCd");
		query.setBigDecimal("id", new BigDecimal(id));
		query.setString("sourceSystemCd", sourcesystemCd);
		currentDbPatient = (PatientDimension) query.uniqueResult();
		nullSafeEvict(currentDbPatient);

	}

	private void findVisitById(int id) {
		Query query = i2b2DataSession
				.createQuery("from VisitDimension v where v.id.encounterNum = :id and sourcesystemCd like :sourceSystemCd");
		query.setBigDecimal("id", new BigDecimal(id));
		query.setString("sourceSystemCd", sourcesystemCd);
		currentDbVisit = (VisitDimension) query.uniqueResult();
		nullSafeEvict(currentDbVisit);
	}

	private PatientDimension findPatientByLimsAccession(String accession) {
		Query query = i2b2DataSession
				.createQuery("from PatientDimension p where patientBlob like :accessionPrefix and sourcesystemCd like :sourceSystemCd");
		query.setString("accessionPrefix", accession);
		query.setString("sourceSystemCd", sourcesystemCd);
		PatientDimension patient = (PatientDimension) query.uniqueResult();
		if (patient != null) {
			i2b2DataSession.evict(patient);
		}
		return patient;
	}

	private void findOrCreateVisit(String limsAccession) {
		currentDbVisit = findVisitByLimsAccession(limsAccession);
		if (currentDbVisit == null) {
			currentDbVisit = new VisitDimension();
			VisitDimensionId visitId = new VisitDimensionId();
			visitId.setEncounterNum(new BigDecimal(padId(idCounter++)));
			visitId.setPatientNum(currentDbPatient.getPatientNum());
			currentDbVisit.setId(visitId);
			currentDbVisit.setActiveStatusCd("0");
			currentDbVisit.setDownloadDate(timeNow);
			currentDbVisit.setEndDate(timeNow);
			currentDbVisit.setImportDate(timeNow);
			currentDbVisit.setInoutCd("NA");
			currentDbVisit.setLengthOfStay(new BigDecimal(0.0d));
			currentDbVisit.setLocationCd("NA");
			currentDbVisit.setLocationPath("NA");
			currentDbVisit.setStartDate(timeNow);
			currentDbVisit.setUpdateDate(timeNow);
			currentDbVisit.setUploadId(new BigDecimal(0.0d));
			currentDbVisit.setVisitBlob(limsAccession);
			currentDbVisit.setSourcesystemCd(sourcesystemCd);
			i2b2DataSession.saveOrUpdate(currentDbVisit);
			Transaction tx = i2b2DataSession.beginTransaction();
			i2b2DataSession.flush();
			tx.commit();
			i2b2DataSession.clear();
		}
	}

	private VisitDimension findVisitByLimsAccession(String accession) {
		Query query = i2b2DataSession
				.createQuery("from VisitDimension v where visitBlob like :accessionPrefix and sourcesystemCd like :sourceSystemCd");
		query.setString("accessionPrefix", accession);
		query.setString("sourceSystemCd", sourcesystemCd);
		VisitDimension visit = (VisitDimension) query.uniqueResult();
		if (visit != null) {
			i2b2DataSession.evict(visit);
		}
		return visit;
	}

	public void saveOrUpdateEncounterXmi(KbPatient kbPatient,
			KbEncounter kbEncounter, KbSummary xmiSummary) {
		i2b2DataSession = i2b2DataDataSourceManager.getSession();
		findObservationByInstanceNum((long) kbEncounter.getId());
		findPatientById(currentDbObservation.getId().getPatientNum().intValue());
		findVisitById(currentDbObservation.getId().getEncounterNum().intValue());
		findOrCreateProvider("DpheProvider:cTakes");
		findOrCreateConcept("DpheConcept:coded");
		currentDbModifier = currentDbObservation.getId().getModifierCd();
		findObservationByDimensions();
		currentDbObservation.setObservationBlob(xmiSummary.getPath());
		Transaction tx = i2b2DataSession.beginTransaction();
		i2b2DataSession.saveOrUpdate(currentDbObservation);
		i2b2DataSession.flush();
		tx.commit();
	}

	public long findCtakesIdForEncounter(KbEncounter kbEncounter) {
		i2b2DataSession = i2b2DataDataSourceManager.getSession();
		findObservationByInstanceNum((long) kbEncounter.getId());
		currentDbModifier = currentDbObservation.getId().getModifierCd();
		findObservationByDimensions(currentDbObservation.getId().getPatientNum(),
				currentDbObservation.getId().getEncounterNum(),
				cTakesDbProvider.getId().getProviderId(),
				codedDbConcept.getConceptCd(),
				currentDbObservation.getId().getModifierCd());
		return currentDbObservation.getId().getInstanceNum();
	}

	@SuppressWarnings("unchecked")
	public long findAnaforaIdForEncounter(KbEncounter kbEncounter) {
		i2b2DataSession = i2b2DataDataSourceManager.getSession();
		findObservationByInstanceNum((long) kbEncounter.getId());
		String hql = "from ObservationFact o where ";
		hql += " o.id.patientNum = :patientNum and ";
		hql += " o.id.encounterNum = :encounterNum and ";
		hql += " o.id.conceptCd in ('DpheConcept:adjudicated', 'DpheConcept:completed', 'DpheConcept:inprogress') and ";
		hql += " o.id.modifierCd = :modifier and ";
		hql += " o.sourcesystemCd like :sourceSystemCd ";
		hql += " order by o.id.conceptCd ";
		Query query = i2b2DataSession.createQuery(hql);
		query.setBigDecimal("patientNum", currentDbObservation.getId()
				.getPatientNum());
		query.setBigDecimal("encounterNum", currentDbObservation.getId()
				.getEncounterNum());
		query.setString("modifier", currentDbObservation.getId()
				.getModifierCd());
		query.setString("sourceSystemCd", sourcesystemCd);
		List<ObservationFact> observations = (List<ObservationFact>) query
				.list();
		long result = -1L;
		for (ObservationFact obs : observations) {
			if (countAnnotations(obs.getObservationBlob()) > 0) {
				result = obs.getId().getInstanceNum();
				break;
			}
		}
		return result;
	}

	private void nullSafeEvict(Object dbObject) {
		if (dbObject != null) {
			i2b2DataSession.evict(dbObject);
		}
	}

	private String padId(long idAsLong) {
		return StringUtils.leftPad(idAsLong + "", 12, "0");
	}

	public List<KbPatient> getKbPatients() {
		return kbPatients;
	}

	public void setKbPatients(List<KbPatient> kbPatients) {
		this.kbPatients = kbPatients;
	}

}
