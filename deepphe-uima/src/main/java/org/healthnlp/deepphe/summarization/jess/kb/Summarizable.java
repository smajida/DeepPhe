package org.healthnlp.deepphe.summarization.jess.kb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Summarizable extends Identified {
	
	protected final List<Summary> summaries = new ArrayList<Summary>();

	public void addSummary(Summary summary) {
		summaries.add(summary);
	}
	
	public List<Summary> getSummaries() {
		return summaries;
	}
	
	public void clearSummaries() {
		summaries.clear();
	}

	public String getUuid() {
		return getClass().getSimpleName() + StringUtils.leftPad(getId()+"",4,"0");
	}


}
