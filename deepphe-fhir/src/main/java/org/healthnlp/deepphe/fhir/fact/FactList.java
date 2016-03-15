package org.healthnlp.deepphe.fhir.fact;

import java.util.*;
/**
 * represents a list of facts with several niceties for convinience
 * @author tseytlin
 */
public class FactList extends ArrayList<Fact> {
	private List<String> types;
	private String category;

	public List<String> getTypes() {
		if(types == null)
			types = new ArrayList<String>();
		return types;
	}

	public void setTypes(List<String> type) {
		this.types = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
