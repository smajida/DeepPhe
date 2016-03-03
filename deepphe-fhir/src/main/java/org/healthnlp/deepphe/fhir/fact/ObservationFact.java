package org.healthnlp.deepphe.fhir.fact;

public class ObservationFact extends Fact {
	private Fact interpretation, method, value;
	
	public Fact getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(Fact interpretation) {
		this.interpretation = interpretation;
	}

	public Fact getMethod() {
		return method;
	}

	public void setMethod(Fact method) {
		this.method = method;
	}

	public Fact getValue() {
		return value;
	}

	public void setValue(Fact value) {
		this.value = value;
	}

	public String getSummaryText(){
		StringBuffer b = new StringBuffer(getLabel());
		if(value != null)
			b.append(" value: "+value.getSummaryText());
		if(interpretation != null)
			b.append(" interpretation: "+interpretation.getSummaryText());
		if(method != null)
			b.append(" method: "+method.getSummaryText());
		return b.toString();
	}
}
