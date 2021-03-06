package org.healthnlp.deepphe.fhir.fact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.neo4j.ogm.annotation.GraphId;

public class TextMention {
	
	@GraphId
	Long objectId;
	
	public Long getObjectId() {
		 return objectId;
	}
	
	public void setObjectId(Long id){
		this.objectId = id;
	}
	
	private String text;
	private int start, end;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String toString(){
		return getMention();
	}
	public String getMention(){
		return text+" ["+start+":"+end+"]";
	}
	public boolean equals(Object o){
		return toString().equals(o.toString());
	}
	public int hashCode(){
		return toString().hashCode();
	}
}
