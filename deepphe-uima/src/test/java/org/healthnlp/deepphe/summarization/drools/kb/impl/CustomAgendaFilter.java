package org.healthnlp.deepphe.summarization.drools.kb.impl;

import java.util.Arrays;
import java.util.List;

import org.kie.api.runtime.rule.ActivationGroup;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.AgendaGroup;
import org.kie.api.runtime.rule.RuleFlowGroup;

public class CustomAgendaFilter implements Agenda {
	
	private List<String> rulesAllowed;
	
	public CustomAgendaFilter(String[] rules) {
		rulesAllowed = Arrays.asList(rules);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AgendaGroup getAgendaGroup(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivationGroup getActivationGroup(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuleFlowGroup getRuleFlowGroup(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
