package org.healthnlp.deepphe.workbench.treeview.artifact;

import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;

public class AnnotationsAnaforaUserObject {
	private KbEncounter kbEncounter;
	private long annotationIndex = -1L;
	public long getAnnotationIndex() {
		return annotationIndex;
	}
	public void setAnnotationIndex(long annotationIndex) {
		this.annotationIndex = annotationIndex;
	}
	public KbEncounter getKbEncounter() {
		return kbEncounter;
	}
	public void setKbEncounter(KbEncounter kbEncounter) {
		this.kbEncounter = kbEncounter;
	}
	public String toString() {
		return "Anafora";
	}
}
