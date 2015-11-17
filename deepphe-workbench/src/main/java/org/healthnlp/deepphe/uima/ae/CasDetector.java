package org.healthnlp.deepphe.uima.ae;

import org.apache.uima.jcas.JCas;

public class CasDetector {
	
	public static boolean isPatientJCas(JCas potentialPatientJCas) {
		return potentialPatientJCas != null
				&& potentialPatientJCas.getViewName().startsWith("Patient");
	}
	
	public static boolean isEncounterJCas(JCas potentialEncounterJCas) {
		return potentialEncounterJCas != null
				&& potentialEncounterJCas.getViewName().startsWith("Encounter");
	}

}
