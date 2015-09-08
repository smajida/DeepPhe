package org.healthnlp.deepphe.summarization.jess.kb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Patient extends Summarizable {
	
	private int sequence = -1;
	
	protected final List<Encounter> encounters = new ArrayList<Encounter>();

	String path;
	
	public void addEncounter(Encounter encounter) {
		encounters.add(encounter);
	}
	
	public List<Encounter> getEncounters() {
		return encounters;
	}
	
	public void clearEncounters() {
		encounters.clear();
	}

	public Object getGender() {
		return "Female";
	}
	
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String fetchInfo() {
		String delimitedId = "<" + getId() + ">";
		String paddedSequence = StringUtils.leftPad(getSequence() + "", 4, "0");
		StringBuilder sb = new StringBuilder();
		sb.append("Patient" + paddedSequence + delimitedId + "\n");
		if (getSummaries().size() == 0) {
			sb.append("\n\n\nYet to be summarized");
		}
		else {
			sb.append("\n\n\nPatient Summary Information: \n\n");
			for (Summary summary : getSummaries()) {
				sb.append(summary.toString() + "\n");
			}
		}
		return sb.toString();
	}
	
	public String toString() {
		return "Patient" + StringUtils.leftPad(getSequence() + "", 4, "0");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
