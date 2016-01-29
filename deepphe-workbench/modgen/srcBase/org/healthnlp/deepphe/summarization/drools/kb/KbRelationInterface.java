package org.healthnlp.deepphe.summarization.drools.kb;

public interface KbRelationInterface extends KbSummaryInterface {
	public int getDomainId();
	public void setDomainId(int domainId);
	public int getRangeId();
	public void setRangeId(int rangeId);
}
