package org.healthnlp.deepphe.graph.summary;

import org.healthnlp.deepphe.fhir.*;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.util.TextUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.*;


/**
 * Simplified OGM POJO for fhir.Report
 * @author Girish Chavan
 *
 */
public class Report{

	String reportText;
	String summaryText;
	Collection<Summary> compositionSummaries;
	List<CancerSummary> cancerSummaries;
	List<TumorSummary> tumorSummaries;
	PatientSummary patientSummary;

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}

	public String getSummaryText() {
		return summaryText;
	}

	public void setSummaryText(String summaryText) {
		this.summaryText = summaryText;
	}

	public Collection<Summary> getCompositionSummaries() {
		return compositionSummaries;
	}

	public void setCompositionSummaries(Collection<Summary> compositionSummaries) {
		this.compositionSummaries = compositionSummaries;
	}

	public List<CancerSummary> getCancerSummaries() {
		return cancerSummaries;
	}

	public void setCancerSummaries(List<CancerSummary> cancerSummaries) {
		this.cancerSummaries = cancerSummaries;
	}

	public List<TumorSummary> getTumorSummaries() {
		return tumorSummaries;
	}

	public void setTumorSummaries(List<TumorSummary> tumorSummaries) {
		this.tumorSummaries = tumorSummaries;
	}

	public PatientSummary getPatientSummary() {
		return patientSummary;
	}

	public void setPatientSummary(PatientSummary patientSummary) {
		this.patientSummary = patientSummary;
	}

	public String getAnnotationType() {
		return FHIRConstants.ANNOTATION_TYPE_DOCUMENT;
	}
}
