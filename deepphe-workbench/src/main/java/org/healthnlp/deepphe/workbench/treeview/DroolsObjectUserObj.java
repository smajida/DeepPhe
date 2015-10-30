package org.healthnlp.deepphe.workbench.treeview;

public class DroolsObjectUserObj {
	
	private Object droolsObj;
	
	public DroolsObjectUserObj() {
	}
	
	public String getContent() {
		return droolsObj.getClass().getSimpleName();
	}
	
	public String toString() {
		return droolsObj.getClass().getSimpleName();
	}

	public Object getFactType() {
		return droolsObj;
	}

	public void setFactType(Object droolsFact) {
		this.droolsObj = droolsFact;
	}

}