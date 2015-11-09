package org.healthnlp.deepphe.workbench;

import org.drools.runtime.ObjectFilter;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;

public class IdentifiedObjectFilter implements ObjectFilter {

	@Override
	public boolean accept(Object obj) {
		return KbIdentified.class.isAssignableFrom(obj.getClass());
	}

}
