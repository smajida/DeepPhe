package org.healthnlp.deepphe.workbench.treeview;

public class JessDefTemplateUserObj {
	private jess.Deftemplate template;
	public JessDefTemplateUserObj() {
	}
	public jess.Deftemplate getTemplate() {
		return template;
	}
	public void setTemplate(jess.Deftemplate template) {
		this.template = template;
	}
	public String toString() {
		return template.getNameWithoutBackchainingPrefix();
	}
}
