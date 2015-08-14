package org.healthnlp.deepphe.uima.cr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
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
import org.healthnlp.deepphe.summarization.jess.kb.Identified;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.summarization.jess.kb.Summary;

public class PatientCollectionReader extends CollectionReader_ImplBase {

	public static final String PARAM_INPUTDIR = "INPUT_DIR";

	int progressIndex = 0;

	private File inputDirectory;

	private Iterator<Patient> patientIterator;

	/**
	 * Initialize shell patient objects. We don't populate patients completely
	 * to support a high number of patients in the future. This way patient init
	 * is done on a per patient basis during CAS initialization.
	 * 
	 */
	@Override
	public void initialize() throws ResourceInitializationException {
		super.initialize();

		inputDirectory = new File(
				(String) getConfigParameterValue(PARAM_INPUTDIR));

		// if input directory does not exist or is not a directory, throw
		// exception
		if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
			throw new ResourceInitializationException(
					ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTDIR,
							this.getMetaData().getName(),
							inputDirectory.getPath() });
		}

		createPatients(inputDirectory);
		
		patientIterator = patients.iterator();
	}

	List<Patient> patients = null;

	private void createPatients(File inputDirectory) {
		patients = new ArrayList<Patient>();

		for (File f : inputDirectory.listFiles()) {
			if (f.isDirectory()) {
				Patient p = new Patient();
				p.setId(Integer.parseInt(f.getName()));
				patients.add(p);
			}
		}

	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return patientIterator.hasNext();
	}

	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		progressIndex++;
		Patient p = patientIterator.next();

		try {
			loadPatient(inputDirectory, p);
			JCas jcas = aCAS.getJCas();
			byte[] serializedPatient = SerializationUtils.serialize(p);
			jcas.setDocumentText(Base64.getEncoder().encodeToString(
					serializedPatient));

		} catch (CASException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the patient with actual data. We do this separately than creation
	 * of shell patient objects because we want to support a high number of
	 * patients in the future.
	 * 
	 * @param p
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadPatient(File inputDirectory, Patient p)
			throws IOException, ClassNotFoundException {
		File patientDir = new File(inputDirectory, "" + p.getId());
		
		for (File f : patientDir.listFiles()) {
			if (!f.isDirectory()) {

				FileInputStream inputFileStream = new FileInputStream(f);
				Encounter encounter = (Encounter) SerializationUtils
						.deserialize(inputFileStream);
				p.addEncounter(encounter);
				inputFileStream.close();
			}
		}
		
		reIdentifyDAG(p);

	}

	private void reIdentifyDAG(Patient p) {
		p.setId(Identified.idGenerator++);
		p.setSequence(p.getId());
		for (Encounter e : p.getEncounters()) {
			e.setId(Identified.idGenerator++);
			e.setSequence(e.getId());
			e.setPatientId(p.getId());
		}
		for (Summary s : p.getSummaries()) {
			s.setId(Identified.idGenerator++);
			s.setSummarizableId(p.getId());
		}
		for (Encounter e : p.getEncounters()) {
			for (Summary s : e.getSummaries()) {
				s.setId(Identified.idGenerator++);
				s.setSummarizableId(e.getId());
			}
		}
	}

	@Override
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(progressIndex,
				patients.size(), Progress.ENTITIES) };
	}

	@Override
	public void close() throws IOException {
	}

}
