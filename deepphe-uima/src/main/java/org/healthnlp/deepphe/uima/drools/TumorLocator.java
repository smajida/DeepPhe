package org.healthnlp.deepphe.uima.drools;

import org.healthnlp.deepphe.fhir.fact.Fact;

public class TumorLocator {
	private String bodySite="", docTumorId, recordId, bodySide="", quadrant="", clockFacePos="";
	private String histologicType = "";
	private Fact tumorSiteFact = null, bodySideFact = null, quadrantFact = null, clockfacePosFact = null;
	
	private boolean readyForRetraction = false;

	
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
	
	public String getHistologicType() {
		return histologicType;
	}
	public void setHistologicType(String histologicType) {
		this.histologicType = histologicType;
	}
	
	public String getInfo(){
		StringBuffer b = new StringBuffer();
		b.append("hashcode: "+hashCode()+"|");
		b.append("histologicType: "+getHistologicType()+"|");
		b.append("docTumorId: "+getDocTumorId()+"|");
		b.append("bodySide: "+getBodySide()+"|");
		b.append("Quadrant: "+getQuadrant()+"|");
		b.append("clockFacePos: "+getClockFacePos()+"|");
		b.append("readyForRetraction: "+readyForRetraction+"\n");
		b.append("tumorSiTTFact: "+tumorSiteFact.getInfo()+"\n");
		if(bodySideFact != null)
			b.append("bodySideFact: "+bodySideFact.getInfo()+"\n");
		if(quadrantFact != null)
			b.append("quadrantFact: "+quadrantFact.getInfo()+"\n");
		if(clockfacePosFact != null)
			b.append("clockfacePosFact: "+clockfacePosFact.getInfo()+"\n");
		return b.toString();
	}
	
	
	public Fact getTumorSiteFact() {
		return tumorSiteFact;
	}
	public void setTumorSiteFact(Fact tumorSiteFact) {
		this.tumorSiteFact = tumorSiteFact;
	}
	public Fact getBodySideFact() {
		return bodySideFact;
	}
	public void setBodySideFact(Fact bodySideFact) {
		this.bodySideFact = bodySideFact;
	}
	public Fact getQuadrantFact() {
		return quadrantFact;
	}
	public void setQuadrantFact(Fact quadrantFact) {
		this.quadrantFact = quadrantFact;
	}
	public Fact getClockfacePosFact() {
		return clockfacePosFact;
	}
	public void setClockfacePosFact(Fact clockfacePosFact) {
		this.clockfacePosFact = clockfacePosFact;
	}
	
	public boolean compareTo(TumorLocator otherTL){
		if(bodySite.equals(otherTL.getBodySite()) && bodySide.equals(otherTL.getBodySide()) && 
				quadrant.equals(otherTL.getQuadrant()) && clockFacePos.equals(otherTL.getClockFacePos()) &&
				bodySideFact == otherTL.getBodySideFact() && quadrantFact == otherTL.getQuadrantFact() && clockfacePosFact == otherTL.getClockfacePosFact())
		return true;
		return false;
			
			
	}
	public boolean isReadyForRetraction() {
		return readyForRetraction;
	}
	public void setReadyForRetraction(boolean readyForRetraction) {
		this.readyForRetraction = readyForRetraction;
	}
	
	public static String inferQuadrant(String bodySide, String clockfacePos){
		String toret = "";
		float clockfacePosNum = 0;
		try{
			clockfacePosNum = Float.valueOf(clockfacePos.substring(0, clockfacePos.indexOf("_")));
			
		} catch (NumberFormatException e){
			return toret;
		}
	//	if(clockfacePosNum == 12 || clockfacePosNum ==3 || clockfacePosNum == 6 || clockfacePosNum ==9)
	//		return toret;
		if(bodySide.equalsIgnoreCase("LEFT")){
			if(clockfacePosNum <= 3)
				toret = "Upper_Outer_Quadrant_of_the_Breast";
			else if(clockfacePosNum > 3 && clockfacePosNum <= 6)
				toret = "Lower_Outer_Quadrant_of_the_Breast";
			else if(clockfacePosNum > 6 && clockfacePosNum <= 9)
				toret = "Lower_Inner_Quadrant_of_the_Breast";
			else if(clockfacePosNum > 9 && clockfacePosNum <= 12)
				toret = "Upper_Inner_Quadrant_of_the_Breast";
		}
		else if(bodySide.equalsIgnoreCase("RIGHT")){
			if(clockfacePosNum <= 3)
				toret = "Upper_Inner_Quadrant_of_the_Breast";
			else if(clockfacePosNum > 3 && clockfacePosNum <= 6)
				toret = "Lower_Inner_Quadrant_of_the_Breast";
			else if(clockfacePosNum > 6 && clockfacePosNum <= 9)
				toret = "Lower_Outer_Quadrant_of_the_Breast";
			else if(clockfacePosNum > 9 && clockfacePosNum <= 12)
				toret = "Upper_Outer_Quadrant_of_the_Breast";
		}
		return toret;
	}
	
	public static boolean clockFactInQuadrant(String bodySide, String clockfacePos, String quadrant){
		float clockfacePosNum = 0;
		try{
			clockfacePosNum = Float.valueOf(clockfacePos.substring(0, clockfacePos.indexOf("_")));
			
		} catch (NumberFormatException e){
			return false;
		}
		
		if(bodySide.equalsIgnoreCase("RIGHT")){
			if(clockfacePosNum < 3 && quadrant.equals("Upper_Inner_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 3 && (quadrant.equals("Upper_Inner_Quadrant_of_the_Breast") || quadrant.equals("Lower_Inner_Quadrant_of_the_Breast")))
				return true;
			else if(clockfacePosNum > 3 && clockfacePosNum < 6 && quadrant.equals("Lower_Inner_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 6 && (quadrant.equals("Lower_Inner_Quadrant_of_the_Breast") || quadrant.equals("Lower_Outer_Quadrant_of_the_Breast")))
				return true;
			else if(clockfacePosNum > 6 && clockfacePosNum < 9 && quadrant.equals("Lower_Outer_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 9 && (quadrant.equals("Lower_Outer_Quadrant_of_the_Breast") || quadrant.equals("Upper_Outer_Quadrant_of_the_Breast")))
				return true;
			else if(clockfacePosNum > 9 && clockfacePosNum < 12 && quadrant.equals("Upper_Outer_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 12 && (quadrant.equals("Upper_Outer_Quadrant_of_the_Breast") || quadrant.equals("Upper_Inner_Quadrant_of_the_Breast")))
				return true;
		}
		else if(bodySide.equalsIgnoreCase("LEFT")){
			if(clockfacePosNum < 3 && quadrant.equals("Upper_Outer_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 3 && (quadrant.equals("Upper_Outer_Quadrant_of_the_Breast") || quadrant.equals("Lower_Outer_Quadrant_of_the_Breast")))
				return true;
			else if(clockfacePosNum > 3 && clockfacePosNum < 6 && quadrant.equals("Lower_Outer_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 6 && (quadrant.equals("Lower_Outer_Quadrant_of_the_Breast") || quadrant.equals("Lower_Inner_Quadrant_of_the_Breast")))
				return true;
			else if(clockfacePosNum > 6 && clockfacePosNum < 9 && quadrant.equals("Lower_Inner_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 9 && (quadrant.equals("Lower_Inner_Quadrant_of_the_Breast") || quadrant.equals("Upper_Inner_Quadrant_of_the_Breast")))
				return true;
			else if(clockfacePosNum > 9 && clockfacePosNum < 12 && quadrant.equals("Upper_Inner_Quadrant_of_the_Breast"))
				return true;
			else if (clockfacePosNum == 12 && (quadrant.equals("Upper_Inner_Quadrant_of_the_Breast") || quadrant.equals("Upper_Outer_Quadrant_of_the_Breast")))
				return true;
		}
		
		return false;
		
	}
}
