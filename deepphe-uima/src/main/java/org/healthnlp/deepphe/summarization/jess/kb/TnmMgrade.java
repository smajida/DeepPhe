package org.healthnlp.deepphe.summarization.jess.kb;

public class TnmMgrade extends Summary {
	
	private static final long serialVersionUID = 7740620973857052246L;
	
	private String providingDepartment;
	private int groupIndex = -1;

	public String getProvidingDepartment() {
		return providingDepartment;
	}

	public void setProvidingDepartment(String providingDepartment) {
		this.providingDepartment = providingDepartment;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}

}
