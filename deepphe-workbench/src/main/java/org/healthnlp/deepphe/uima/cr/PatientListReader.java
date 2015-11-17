package org.healthnlp.deepphe.uima.cr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;

public class PatientListReader {
	
	private String inputDirectoryPath;
	private List<KbPatient> patients;
	private int patientSequence = -1;
	
	public void execute() {
		try {
			tryExecute();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void tryExecute() throws IOException {
		File reportsDirectory = new File(getInputDirectoryPath());
		File[] rawFiles = reportsDirectory.listFiles();
		for (File rawFile : rawFiles) {
			if (rawFile.isDirectory()) {
				patientSequence++;
				createPatient(rawFile);
			}
		}
	}

	private void createPatient(File patientDirectory) throws IOException {
		KbPatient patient = new KbPatient();
		patient.setSequence(patientSequence);
		patients.add(patient);
		File[] rawFiles = patientDirectory.listFiles();
		int encounterSequence = 0;
		for (File rawFile : rawFiles) {
			if (rawFile.isFile()) {
				KbEncounter encounter = new KbEncounter();
				encounter.setPatientId(patient.getId());
				encounter.setUri(rawFile.getAbsolutePath());
				encounter.setContent(FileUtils.readFileToString(rawFile));
				encounter.setSequence(encounterSequence++);
				patient.addEncounter(encounter);
			}
		}		
	}

	public String getInputDirectoryPath() {
		return inputDirectoryPath;
	}

	public void setInputDirectoryPath(String inputDirectoryPath) {
		this.inputDirectoryPath = inputDirectoryPath;
	}

	public List<KbPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;
	}
	

}
