package org.healthnlp.deepphe.fhir;

import java.io.File;
import java.io.Serializable;

import org.hl7.fhir.instance.model.Resource;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;


/**
 * DeepPhe model interface that 
 * @author tseytlin
 */
public interface Element extends Serializable {
	public String getDisplayText();
	public String getResourceIdentifier();
	public String getSummaryText();
	public Resource getResource();
	public IClass getConceptClass();
	public String getConceptURI();
	public void setReport(Report r);
	public void save(File e) throws Exception;
	public void copy(Resource r);
}
