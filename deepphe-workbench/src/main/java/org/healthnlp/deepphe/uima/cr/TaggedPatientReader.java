package org.healthnlp.deepphe.uima.cr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ctakes.typesystem.type.syntax.BaseToken;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummarizable;

public class TaggedPatientReader extends CollectionReader_ImplBase {

	/**
	 * Name of configuration parameter that must be set to the path of a
	 * directory containing input files.
	 */
	public static final String PARAM_INPUTDIR = "InputDirectory";

	private final List<KbPatient> patients = new ArrayList<>();
	private PatientListReader patientListReader = null;
	private List<KbSummarizable> summarizables = new ArrayList<>();
	private Iterator<KbSummarizable> summarizableIterator = null;
	private int currentIndex = 0;

	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		File directory = new File(
				(String) getConfigParameterValue(PARAM_INPUTDIR));

		// if input directory does not exist or is not a directory, throw
		// exception
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

		Iterator<KbPatient> patientIterator = patientListReader.getPatients()
				.iterator();
		while (patientIterator.hasNext()) {
			KbPatient patient = patientIterator.next();
			Iterator<KbEncounter> encounterIterator = patient.getEncounters()
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
			KbSummarizable summarizable = summarizableIterator.next();
			JCas jCas = aCAS.getJCas();
			JCas jCasComponent = jCas.createView(summarizable.toString());
			if (summarizable instanceof KbEncounter) {
				KbEncounter encounter = (KbEncounter) summarizable;
				jCasComponent.setDocumentText(encounter.getContent());		
			}
			else {
				jCasComponent.setDocumentText(" ");
			}
			BaseToken token = new BaseToken(jCasComponent);
			token.setBegin(0);
			token.setEnd(0);
			token.setPartOfSpeech("ACTIVE");
			token.addToIndexes(jCasComponent);
			
		} catch (CASException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return summarizableIterator.hasNext();
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
