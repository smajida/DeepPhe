package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Resource;


public class Medication extends org.hl7.fhir.instance.model.Medication implements Element {
	private String annotationType = FHIRConstants.ANNOTATION_TYPE_DOCUMENT;
	public String getDisplayText() {
		return getCode().getText();
	}

	public String getResourceIdentifier() {
		return  FHIRUtils.createResourceIdentifier(this);
	}

	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append("Medication:\t"+getDisplayText());
		// add text provenance
				st.append(" [ "+FHIRUtils.getMentions(this)+" ]");
		return st.toString();
	}
	public Resource getResource() {
		return this;
	}


	public URI getConceptURI(){
		return FHIRUtils.getConceptURI(getCode());
	}
	
	/**
	 * assign report instance and add appropriate information from there
	 */
	public void setReport(Report r) {
	}
	
	
	public void save(File dir) throws Exception {
		FHIRUtils.saveFHIR(this,getResourceIdentifier(),dir);
	}

	public void copy(Resource r) {
		org.hl7.fhir.instance.model.Medication p = (org.hl7.fhir.instance.model.Medication) r;
		code = p.getCode();
		isBrand = p.getIsBrandElement();
		manufacturer = p.getManufacturer();
		product = p.getProduct();
		package_ = p.getPackage();
		extension = new ArrayList<Extension>();
		for(Extension e: p.getExtension())
			extension.add(e);
		
	}
	public String toString(){
		return getDisplayText();
	}

	public String getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}
		
}
