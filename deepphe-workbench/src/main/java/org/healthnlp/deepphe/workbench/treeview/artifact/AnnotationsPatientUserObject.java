package org.healthnlp.deepphe.workbench.treeview.artifact;

import org.apache.commons.lang.StringUtils;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;

public class AnnotationsPatientUserObject {
	
	private KbPatient patient;

	public KbPatient getPatient() {
		return patient;
	}

	public void setPatient(KbPatient encounter) {
		this.patient = encounter;
	}

	public String toString() {
		return "Patient" +
				StringUtils.leftPad(patient.getSequence() + "", 4, "0");
	}
}