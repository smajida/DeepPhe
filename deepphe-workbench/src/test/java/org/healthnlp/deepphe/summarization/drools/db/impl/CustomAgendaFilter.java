package org.healthnlp.deepphe.summarization.drools.db.impl;

import java.util.Arrays;
import java.util.List;

import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

public class CustomAgendaFilter implements AgendaFilter {
	
	private List<String> rulesAllowed;
	
	public CustomAgendaFilter(String[] rules) {
		rulesAllowed = Arrays.asList(rules);
	}
	
	@Override
	public boolean accept(Activation activation) {
		if (rulesAllowed.contains(activation.getRule().getName())) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
