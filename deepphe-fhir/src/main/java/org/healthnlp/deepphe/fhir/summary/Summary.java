package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;

import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.Resource;


public abstract class Summary extends List_ {
	public abstract String getDisplayText();
	public abstract String getResourceIdentifier();
	public abstract String getSummaryText();
	public Resource getResource(){
		return this;
	}
	public abstract URI getConceptURI();
}
