package org.healthnlp.deepphe.uima.cr;

import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.util.Progress;

public class PatientCollectionReader extends CollectionReader_ImplBase {

	public static final String PARAM_INPUTDIR = "INPUT_DIR";
	@Override
	public void getNext(CAS arg0) throws IOException, CollectionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return false;
	}

}
