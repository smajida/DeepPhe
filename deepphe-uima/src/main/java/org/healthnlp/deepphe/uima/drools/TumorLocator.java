package org.healthnlp.deepphe.uima.drools;

import org.healthnlp.deepphe.fhir.fact.Fact;

public class TumorLocator {
	private String bodySite, docTumorId, recordId, bodySide, quadrant="", clockFacePos="";
	private String histologicType = "";
	private Fact provenanceFact;
	public String getBodySite() {
		return bodySite;
	}
	public void setBodySite(String bodySite) {
		this.bodySite = bodySite;
	}
	public String getDocTumorId() {
		return docTumorId;
	}
	public void setDocTumorId(String docTumorId) {
		this.docTumorId = docTumorId;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getBodySide() {
		return bodySide;
	}
	public void setBodySide(String bodySide) {
		this.bodySide = bodySide;
	}
	public String getQuadrant() {
		return quadrant;
	}
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
	public String getClockFacePos() {
		return clockFacePos;
	}
	public void setClockFacePos(String clockFacePos) {
		this.clockFacePos = clockFacePos;
	}
	public Fact getProvenanceFact() {
		return provenanceFact;
	}
	public void setProvenanceFact(Fact provenanceFact) {
		this.provenanceFact = provenanceFact;
	}
	public String getHistologicType() {
		return histologicType;
	}
	public void setHistologicType(String histologicType) {
		this.histologicType = histologicType;
	}
	
	public String getInfo(){
		StringBuffer b = new StringBuffer();
		b.append("histologicType: "+getHistologicType()+"|");
		b.append("docTumorId: "+getDocTumorId()+"|");
		b.append("bodySide: "+getBodySide()+"|");
		b.append("Quadrant: "+getQuadrant()+"|");
		b.append("clockFacePos: "+getClockFacePos()+"\n");
		b.append("ProvenanceFact: "+provenanceFact.getInfo()+"\n");
		return b.toString();
	}
	
	

}
