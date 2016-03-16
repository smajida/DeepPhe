package org.healthnlp.deepphe.fhir.fact;

import org.healthnlp.deepphe.util.FHIRConstants;

public class ValueFact extends Fact {
	private String unit;
	private double value;
	public ValueFact(){
		setType(FHIRConstants.QUANTITY);
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public String getSummaryText(){
		StringBuffer b = new StringBuffer(String.format("%.4d", getValue()));
		if(unit != null)
			b.append(" "+unit);
		return b.toString();
	}
	
}
