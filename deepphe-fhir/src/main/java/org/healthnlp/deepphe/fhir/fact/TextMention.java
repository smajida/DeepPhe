package org.healthnlp.deepphe.fhir.fact;

public class TextMention {
	private String text,documentIdentifier,documentType;
	private int start, end;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDocumentIdentifier() {
		return documentIdentifier;
	}
	public void setDocumentIdentifier(String documentIdentifier) {
		this.documentIdentifier = documentIdentifier;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
