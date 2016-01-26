package org.healthnlp.deepphe.fhir;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.StringType;

public class Stage extends ConditionStageComponent implements Serializable{
	public static final String TNM_PRIMARY_TUMOR = FHIRUtils.STAGE_URL+"/PrimaryTumor";
	public static final String TNM_DISTANT_METASTASIS = FHIRUtils.STAGE_URL+"/DistantMetastasis";
	public static final String TNM_REGIONAL_LYMPH_NODES = FHIRUtils.STAGE_URL+"/RegionalLymphNodes";

	
	public void setStringExtension(String url, String value) {
		Extension e = new Extension();
		e.setUrl(url);
		e.setValue(new StringType(value));
		addExtension(e);
		
	}

	/**
	 * get primary tumor stage
	 * @return
	 */
	public String getPrimaryTumorStage(){
		Extension e = getExtension(TNM_PRIMARY_TUMOR); //FHIRUtils.CANCER_URL+"#"+FHIRUtils.T_STAGE
		return e != null? ((StringType)e.getValue()).getValue():null;
	}
	
	private Extension getExtension(String url) {
		List<Extension> list = getExtensionsByUrl(url);
		return list.isEmpty()?null:list.get(0);
	}

	/**
	 * get primary tumor stage
	 * @return
	 */
	public CodeableConcept getPrimaryTumorStageCode(){
		return getStageValue(TNM_PRIMARY_TUMOR);
	}
	
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public CodeableConcept getDistantMetastasisStageCode(){
		return getStageValue(TNM_DISTANT_METASTASIS);
	}
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public CodeableConcept getRegionalLymphNodeStageCode(){
		return getStageValue(TNM_REGIONAL_LYMPH_NODES);
	}
	
	
	private CodeableConcept getStageValue(String stage){
		Extension e = getExtension(stage);
		if(e == null)
			return null;
		String val = ((StringType)e.getValue()).getValue();
		return (val != null)?FHIRUtils.getCodeableConcept(URI.create(val)):null;
	}
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public String getDistantMetastasisStage(){
		Extension e = getExtension(TNM_DISTANT_METASTASIS);
		return e != null? ((StringType)e.getValue()).getValue():null;
	}
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public String getRegionalLymphNodeStage(){
		Extension e = getExtension(TNM_REGIONAL_LYMPH_NODES); 
		return e != null? ((StringType)e.getValue()).getValue():null;
	}
	
	public Stage copy() {
		Stage dst = new Stage();
		dst.summary = ((this.summary == null) ? null : this.summary.copy());
		dst.assessment = new ArrayList();
		for (Reference i : this.assessment)
			dst.assessment.add(i.copy());
		for(Extension e: getExtension()){
			dst.setStringExtension(e.getUrl(),((StringType) e.getValue()).asStringValue());
		}
	
		return dst;
	}
	

	public void copy(ConditionStageComponent st) {
		setSummary(st.getSummary());
		for(Extension e: st.getExtension()){
			setStringExtension(e.getUrl(),((StringType) e.getValue()).asStringValue());
		}
	}
	
	public String getDisplayText(){
		CodeableConcept c =  getSummary();
		return c != null ? c.getText(): "TNM unknown"; 
	}
	
	public String toString(){
		return getDisplayText();
	}
}
