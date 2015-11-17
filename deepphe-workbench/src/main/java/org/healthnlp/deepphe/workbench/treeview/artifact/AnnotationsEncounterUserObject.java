package org.healthnlp.deepphe.workbench.treeview.artifact;

import org.apache.commons.lang.StringUtils;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;

public class AnnotationsEncounterUserObject {
	private KbEncounter encounter;

	public KbEncounter getEncounter() {
		return encounter;
	}

	public void setEncounter(KbEncounter encounter) {
		this.encounter = encounter;
	}

	private String formalizeNoteKind() {
		String formalNoteName = "Pathology";
		switch (encounter.getKind()) {
		case "RAD":
			formalNoteName = "Radiology";
			break;
		case "SP":
			formalNoteName = "Pathology";
			break;
		case "DS":
			formalNoteName = "Discharge";
			break;
		default:
			formalNoteName = "Note";
			break;
		}
		return formalNoteName;
	}

	public String toString() {
		return formalizeNoteKind()
				+ StringUtils.leftPad(encounter.getSequence() + "", 4, "0");
	}
}
