package org.healthnlp.deepphe.summarization.drools.kb;

import java.io.Serializable;

public interface KbIdentifiedInterface extends Serializable {
	public int getId();
	public void setId(int id);
	public int getIsActive();
	public void setIsActive(int isActive);
}
