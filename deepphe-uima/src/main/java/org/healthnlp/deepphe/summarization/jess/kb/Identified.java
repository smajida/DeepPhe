package org.healthnlp.deepphe.summarization.jess.kb;

import java.io.Serializable;

public class Identified implements Serializable {
	
	private static final long serialVersionUID = 3569891592557068875L;

	public static int idGenerator = 0;
	
	private int id;
	private int isActive = 0;

	public Identified() {
		id = idGenerator++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	

}
