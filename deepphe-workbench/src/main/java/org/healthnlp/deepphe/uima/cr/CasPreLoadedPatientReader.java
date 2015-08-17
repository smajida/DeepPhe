package org.healthnlp.deepphe.uima.cr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Summarizable;

public class CasPreLoadedPatientReader extends CollectionReader_ImplBase {

	/**
	 * Name of configuration parameter that must be set to the path of a
	 * directory containing input files.
	 */
	public static final String PARAM_INPUTDIR = "InputDirectory";

	private final List<Patient> patients = new ArrayList<Patient>();
	private PatientListReader patientListReader = null;
	private List<Summarizable> summarizables = new ArrayList<Summarizable>();
	private Iterator<Summarizable> summarizableIterator = null;
	private int currentIndex = 0;


	
	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		File directory = new File(
				(String) getConfigParameterValue(PARAM_INPUTDIR));
		if (!directory.exists() || !directory.isDirectory()) {
			throw new ResourceInitializationException(
					ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTDIR,
							this.getMetaData().getName(), directory.getPath() });
		}

		patientListReader = new PatientListReader();
		patientListReader.setInputDirectoryPath(directory.getAbsolutePath());
		patientListReader.setPatients(patients);
		patientListReader.execute();

		Iterator<Patient> patientIterator = patientListReader.getPatients()
				.iterator();
		while (patientIterator.hasNext()) {
			Patient patient = patientIterator.next();
			Iterator<Encounter> encounterIterator = patient.getEncounters()
					.iterator();
			while (encounterIterator.hasNext()) {
				summarizables.add(encounterIterator.next());
			}
			summarizables.add(patient);
		}

		summarizableIterator = summarizables.iterator();
	}

	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		try {
			JCas multiJCas = aCAS.getJCas();
			Summarizable summarizable = summarizableIterator.next();
			while (!(summarizable instanceof Patient)) {
				JCas encounterJCas = multiJCas.createView(summarizable.getUuid());
				Encounter encounter = (Encounter) summarizable;
				encounterJCas.setDocumentText(encounter.getContent());
				summarizable = summarizableIterator.next();
			} 

			JCas patientJCas = multiJCas.createView(summarizable.getUuid());
			patientJCas.setDocumentText(" ");

		} catch (CASException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		boolean result = summarizableIterator.hasNext();
		if (result) {
			System.out.println("Keep going...");
		}
		return result;
	}

	/**
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#getProgress()
	 */
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentIndex,
				summarizables.size(), Progress.ENTITIES) };
	}

	@Override
	public void close() throws IOException {

	}

}