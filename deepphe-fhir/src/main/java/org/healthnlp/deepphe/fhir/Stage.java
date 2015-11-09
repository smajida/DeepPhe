package org.healthnlp.deepphe.fhir;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.StringType;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;

public class Stage extends ConditionStageComponent implements Serializable{
	public void load(Mention st) {
		CodeableConcept c = Utils.getCodeableConcept(st);
		c.setText(st.getText());
		setSummary(c);
		// extract individual Stage levels if values are conflated
		Matcher m = Pattern.compile(Utils.STAGE_REGEX).matcher(st.getText());
		if(m.matches()){
			IOntology o = ResourceFactory.getInstance().getOntology();
			/*setStringExtension(""+o.getClass(Utils.T_STAGE).getURI(),m.group(1));
			setStringExtension(""+o.getClass(Utils.N_STAGE).getURI(),m.group(2));
			setStringExtension(""+o.getClass(Utils.M_STAGE).getURI(),m.group(3));*/	
		}
	}
	
	public void load(TnmClassification st) {
		CodeableConcept c = Utils.getCodeableConcept(st);
		c.setText(st.getCoveredText());
		setSummary(c);
		// extract individual Stage levels if values are conflated
		IOntology o = ResourceFactory.getInstance().getOntology();
		if(st.getSize() != null)
			setStringExtension(""+o.getClass(Utils.T_STAGE).getURI(),st.getSize().getCode()+st.getSize().getValue());
		if(st.getNodeSpread() != null)
			setStringExtension(""+o.getClass(Utils.N_STAGE).getURI(),st.getNodeSpread().getCode()+st.getNodeSpread().getValue());
		if(st.getMetastasis() != null)
			setStringExtension(""+o.getClass(Utils.M_STAGE).getURI(),st.getMetastasis().getCode()+st.getMetastasis().getValue());
		

		// add mention text
		addExtension(Utils.createMentionExtension(st.getCoveredText(),st.getBegin(),st.getEnd()));
	}
	
	private void setStringExtension(String url, String value) {
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
		IOntology o = ResourceFactory.getInstance().getOntology();
		Extension e = getExtension(""+o.getClass(Utils.T_STAGE).getURI());
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
		return getStageValue(Utils.T_STAGE);
	}
	
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public CodeableConcept getDistantMetastasisStageCode(){
		return getStageValue(Utils.M_STAGE);
	}
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public CodeableConcept getRegionalLymphNodeStageCode(){
		return getStageValue(Utils.N_STAGE);
	}
	
	
	private CodeableConcept getStageValue(String stage){
		IOntology o = ResourceFactory.getInstance().getOntology();
		IClass cls = o.getClass(stage);
		if(cls == null)
			return null;
		Extension e = getExtension(""+cls.getURI());
		if(e == null)
			return null;
		
		String val = ((StringType)e.getValue()).getValue();
		for(IClass c : cls.getSubClasses()){
			for(String s: c.getConcept().getSynonyms()){
				if(s.matches("\\b"+val+"\\b")){
					return Utils.getCodeableConcept(c);
				}
			}
		}
		return null;
	}
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public String getDistantMetastasisStage(){
		IOntology o = ResourceFactory.getInstance().getOntology();
		Extension e = getExtension(""+o.getClass(Utils.M_STAGE).getURI());
		return e != null? ((StringType)e.getValue()).getValue():null;
	}
	
	/**
	 * get primary tumor stage
	 * @return
	 */
	public String getRegionalLymphNodeStage(){
		IOntology o = ResourceFactory.getInstance().getOntology();
		Extension e = getExtension(""+o.getClass(Utils.N_STAGE).getURI());
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
	public String toString(){
		CodeableConcept c =  getSummary();
		return c != null ? c.getText(): "TNM unknown";
	}
}
