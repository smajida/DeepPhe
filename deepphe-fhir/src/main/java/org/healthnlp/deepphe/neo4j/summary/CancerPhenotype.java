package org.healthnlp.deepphe.neo4j.summary;


import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;

import java.net.URI;

public class CancerPhenotype extends Summary {

	private FactList cancerStage;
	private FactList cancerType;
	private FactList tumorExtent;
	private FactList primaryTumorClassification;
	private FactList distantMetastasisClassification;
	private FactList regionalLympNodeClassification;

	public FactList getCancerStage() {
		return cancerStage;
	}

	public void setCancerStage(FactList cancerStage) {
		this.cancerStage = cancerStage;
	}

	public FactList getCancerType() {
		return cancerType;
	}

	public void setCancerType(FactList cancerType) {
		this.cancerType = cancerType;
	}

	public FactList getTumorExtent() {
		return tumorExtent;
	}

	public void setTumorExtent(FactList tumorExtent) {
		this.tumorExtent = tumorExtent;
	}

	public FactList getPrimaryTumorClassification() {
		return primaryTumorClassification;
	}

	public void setPrimaryTumorClassification(FactList primaryTumorClassification) {
		this.primaryTumorClassification = primaryTumorClassification;
	}

	public FactList getDistantMetastasisClassification() {
		return distantMetastasisClassification;
	}

	public void setDistantMetastasisClassification(FactList distantMetastasisClassification) {
		this.distantMetastasisClassification = distantMetastasisClassification;
	}

	public FactList getRegionalLympNodeClassification() {
		return regionalLympNodeClassification;
	}

	public void setRegionalLympNodeClassification(FactList regionalLympNodeClassification) {
		this.regionalLympNodeClassification = regionalLympNodeClassification;
	}

	public String getDisplayText() {
		return getClass().getSimpleName();
	}
	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}
	public URI getConceptURI() {
		return FHIRConstants.CANCER_PHENOTYPE_SUMMARY_URI;
	}

}