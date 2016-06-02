package org.healthnlp.deepphe.uima.drools;

public class Domain {
	private String domainName;
	
	public Domain(String domainName){
		setDomainName(domainName);
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}
