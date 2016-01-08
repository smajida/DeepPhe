package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.net.URI;

import org.hl7.fhir.instance.model.BodySite;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Resource;


public class AnatomicalSite extends BodySite implements Element{

	public String getDisplayText() {
		return getCode().getText();
	}

	public String getResourceIdentifier() {
		return Utils.getIdentifier(getIdentifier());
	}

	public String getSummaryText() {
		return getDisplayText();
	}

	public Resource getResource() {
		return this;
	}

	public URI getConceptURI() {
		return Utils.getConceptURI(getCode());
	}

	public void setReport(Report r) {
		Patient p = r.getPatient();
		if(p != null){
			setPatient(Utils.getResourceReference(p));
			setPatientTarget(p);
		}
	}

	public void save(File dir) throws Exception {
		Utils.saveFHIR(this,getResourceIdentifier(),dir);
	}

	public void copy(Resource r) {
		BodySite bs = (BodySite)r;
		setCode(bs.getCode());
		setDescription(bs.getDescription());
		setLanguage(bs.getLanguage());
		setPatient(bs.getPatient());
		setPatientTarget(bs.getPatientTarget());
		for(CodeableConcept c: bs.getModifier()){
			addModifier(c);
		}
		for(Extension ex: bs.getExtension()){
			addExtension(ex);
		}
		
	}

}
