package org.healthnlp.deepphe.uima.drools;

import java.util.HashSet;
import java.util.Set;

import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.util.FHIRConstants;

/**
 * Represents tumors, merged across all documents. Used in drools only
 * @author opm1
 *
 */
public class MergedTumor {
	private String bodySite="", mergedTumorId, bodySide="", quadrant="", clockFacePos="", histologicType = "";
	private Set<Fact> tumorSiteFactSet, bodySideFactSet, quadrantFactSet, clockfacePosFactSet;
	private Set<String> tumorSummaryIdSet;
	private boolean readyForRetraction = false;
	
	private Set<String> receptors;
	
	public MergedTumor(){
		setMergedTumorId("MergedTumor-"+hashCode());
		tumorSiteFactSet = new HashSet<Fact>();
		bodySideFactSet = new HashSet<Fact>();
		quadrantFactSet = new HashSet<Fact>();
		clockfacePosFactSet = new HashSet<Fact>();
		setReceptors(new HashSet<String>());
		setTumorSummaryIdSet(new HashSet<String>());
	}
	
	public String getBodySite() {
		return bodySite;
	}
	public void setBodySite(String bodySite) {
		this.bodySite = bodySite;
	}
	public String getMergedTumorId() {
		return mergedTumorId;
	}
	public void setMergedTumorId(String mergedTumorId) {
		this.mergedTumorId = mergedTumorId;
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
	public Set<Fact> getTumorSiteFactSet() {
		return tumorSiteFactSet;
	}
	
	public void setTumorSiteFactSet(Set<Fact> tumorSiteFact) {
		this.tumorSiteFactSet = tumorSiteFact;
	}
	public Set<Fact> getBodySideFactSet() {
		return bodySideFactSet;
	}
	public void setBodySideFactSet(Set<Fact> bodySideFact) {
		this.bodySideFactSet = bodySideFact;
	}
	public Set<Fact> getQuadrantFactSet() {
		return quadrantFactSet;
	}
	public void setQuadrantFactSet(Set<Fact> quadrantFact) {
		this.quadrantFactSet = quadrantFact;
	}
	public Set<Fact> getClockfacePosFactSet() {
		return clockfacePosFactSet;
	}
	public void setClockfacePosFactSet(Set<Fact> clockfacePosFact) {
		this.clockfacePosFactSet = clockfacePosFact;
	}

	public String getHistologicType() {
		return histologicType;
	}

	public void setHistologicType(String histologicType) {
		this.histologicType = histologicType;
	}
	
	public void addTumorFact(String setId, Fact f) {
		tumorSummaryIdSet.add(f.getSummaryId());
		switch (setId){
		case FHIRConstants.BODY_SITE:
			tumorSiteFactSet.add(f);
			break;
		case FHIRConstants.BODY_SIDE:
			bodySideFactSet.add(f);
			break;
		case FHIRConstants.QUADRANT:
			quadrantFactSet.add(f);
			break;
		case FHIRConstants.CLOCKFACE_POSITION:
			clockfacePosFactSet.add(f);
			break;
		}
	}
	
	public String getInfo(){
		StringBuffer b = new StringBuffer();
		b.append("mergedTumorId: "+mergedTumorId+"|");
		b.append("histologicType: "+getHistologicType()+"|");
		b.append("bodySite: "+getBodySite()+"|");
		b.append("bodySide: "+getBodySide()+"|");
		b.append("Quadrant: "+getQuadrant()+"|");
		b.append("clockFacePos: "+getClockFacePos()+"\n");
		
		b.append("Doc TumorSummaryIds: ");
		for(String s : tumorSummaryIdSet){
			b.append(s+", ");
		}
		b.append("\n");
		if(bodySideFactSet != null)
			b.append("bodySideFacts: "+bodySideFactSet.size()+"\n");
		if(quadrantFactSet != null)
			b.append("quadrantFacts: "+quadrantFactSet.size()+"\n");
		if(clockfacePosFactSet != null)
			b.append("clockfacePosFacts: "+clockfacePosFactSet.size()+"\n");
		return b.toString();
	}
	
	public String toString(){
		return getInfo();
	}

	public boolean isReadyForRetraction() {
		return readyForRetraction;
	}

	public void setReadyForRetraction(boolean readyForRetraction) {
		this.readyForRetraction = readyForRetraction;
	}

	public Set<String> getTumorSummaryIdSet() {
		return tumorSummaryIdSet;
	}

	public void setTumorSummaryIdSet(Set<String> tumorSummaryIdSet) {
		this.tumorSummaryIdSet = tumorSummaryIdSet;
	}

	public Set<String> getReceptors() {
		return receptors;
	}

	public void setReceptors(Set<String> receptors) {
		this.receptors = receptors;
	}
	
	public void addReceptor(String v){
		receptors.add(v);
	}
	
}
