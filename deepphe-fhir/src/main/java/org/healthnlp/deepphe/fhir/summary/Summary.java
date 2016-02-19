package org.healthnlp.deepphe.fhir.summary;

import java.io.File;
import java.net.URI;

import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.Resource;


public abstract class Summary extends List_  implements Element {
	private Report report;
	private String annotationType = FHIRConstants.ANNOTATION_TYPE_DOCUMENT;
	public abstract String getDisplayText();
	public abstract String getResourceIdentifier();
	public abstract String getSummaryText();
	public Resource getResource(){
		return this;
	}
	public abstract URI getConceptURI();
	

	public void setReport(Report r) {
		report = r;
	}

	public void save(File e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void copy(Resource r) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * is this summary appendable?
	 * @param s
	 * @return
	 */
	public abstract boolean isAppendable(Summary s);
	
	/**
	 * append summary appendable?
	 * @param s
	 * @return
	 */
	public abstract void append(Summary s);
	
	public String getAnnotationType() {
		return annotationType;
	}
	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}
	
}
