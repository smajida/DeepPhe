package org.healthnlp.deepphe.summarization.jess.kb;

public class Goal extends Identified {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int priority;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String toString() {
		return "Goal of " + getName();
	}

}
