package org.healthnlp.deepphe.workbench.treeview.drools;

import org.drools.definition.rule.Rule;

public class DroolsRuleUserObj {
	private Rule rule;
	public DroolsRuleUserObj() {
	}
	public String toString() {
		return rule.getName();
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public String getContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("KnowledgeType: " + rule.getKnowledgeType() + "\n");
		sb.append("Package: " + rule.getPackageName() + "\n");
		sb.append("Namespace: " + rule.getNamespace() + "\n");
		sb.append("Namespace: " + rule.getNamespace() + "\n");
	    for (String metaDataKey : rule.getMetaData().keySet()) {
	    	Object metaDataObj = rule.getMetaData().get(metaDataKey);
	    		sb.append("\t" + metaDataKey + " ==> " + metaDataObj +  "\n");
	    }
		return sb.toString();
	}
}