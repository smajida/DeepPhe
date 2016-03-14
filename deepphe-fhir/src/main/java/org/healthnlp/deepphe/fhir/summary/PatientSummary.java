package org.healthnlp.deepphe.fhir.summary;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRUtils;



public class PatientSummary extends Summary {
	private PatientPhenotype phenotype;

	public static class PatientPhenotype extends Summary {
		public String getSummaryText() {
			StringBuffer st = new StringBuffer();
			st.append(getDisplayText()+":\n");
			for(String category: getContent().keySet()){
				st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
				for(Fact c: getFacts(category)){
					st.append("\t\t"+c.getSummaryText()+"\n");
				}
			}
			return st.toString();
		}
		public String getDisplayText() {
			return  getClass().getSimpleName();
		}
		public String getResourceIdentifier() {
			return getClass().getSimpleName()+"_"+Math.abs(hashCode());
		}
		public URI getConceptURI() {
			return FHIRConstants.PATIENT_PHENOTYPE_SUMMARY_URI;
		}
		public boolean isAppendable(Summary s) {
			return s instanceof PatientPhenotype;
		}
	}
	
	public FactList getName() {
		return getFacts(FHIRConstants.HAS_NAME);
	}
	
	public FactList getGender() {
		return getFacts(FHIRConstants.HAS_GENDER);
	}

	public FactList getBirthDate() {
		return getFacts(FHIRConstants.HAS_BIRTH_DATE);
	}

	public FactList getDeathDate() {
		return getFacts(FHIRConstants.HAS_DEATH_DATE);
	}


	public PatientPhenotype getPhenotype(){
		if(phenotype == null)
			phenotype = new PatientPhenotype();
		return phenotype;
	}
	
	
	public void setPhenotype(PatientPhenotype phenotype) {
		this.phenotype = phenotype;
	}


	public FactList getOutcomes() {
		return getFacts(FHIRConstants.HAS_OUTCOME);
	}

	public FactList getSequenceVariant() {
		return getFacts(FHIRConstants.HAS_SEQUENCE_VARIENT);
	}

	public String getDisplayText() {
		return getClass().getSimpleName();
	}

	public String getResourceIdentifier() {
		return getClass().getSimpleName()+"_"+Math.abs(hashCode());
	}

	public String getSummaryText() {
		StringBuffer st = new StringBuffer();
		st.append(getDisplayText()+":\n");
		
		for(String category: getContent().keySet()){
			st.append("\t"+FHIRUtils.getPropertyDisplayLabel(category)+":\n");
			for(Fact c: getFacts(category)){
				st.append("\t\t"+c.getSummaryText()+"\n");
			}
		}
		
		st.append(getPhenotype().getSummaryText()+"\n");
		return st.toString();
	}

	public URI getConceptURI() {
		return FHIRConstants.PATIENT_SUMMARY_URI;
	}

	public boolean isAppendable(Summary s) {
		return s instanceof PatientSummary;
	}

	public void append(Summary s) {
		super.append(s);
		PatientSummary summary = (PatientSummary) s;
		getPhenotype().append(summary.getPhenotype());
		
	}
}
