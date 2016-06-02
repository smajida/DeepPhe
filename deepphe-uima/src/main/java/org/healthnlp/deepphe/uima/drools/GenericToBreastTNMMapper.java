package org.healthnlp.deepphe.uima.drools;

import java.util.HashMap;
import java.util.Map;

public class GenericToBreastTNMMapper {
	
	static Map <String, String> tGenericToBreast = null;
	
	public static String getBreastTClassification(String prefix, String genericValue, String suffix){
		String pref = "";
		if(prefix.equals("p_modifier"))
			pref = "p";
		else if(prefix.equals("c_modifier"))
			pref = "c";
		
		if("".equals(pref)) return null;
		
		String genV = genericValue.substring(0, genericValue.indexOf("_"));
		
		String val = getTMap().get(genV);
		if(val == null) return null;
		
		String suff = "";
		if(suffix.equals("DCIS") || suffix.equals("LCIS") || suffix.equals("Paget"))
			 suff = "_"+suffix;
		return "Breast_Cancer_"+pref+val+suff+"_TNM_Finding";
	}
	
	
	
	public static Map<String, String> getTMap(){
		if(tGenericToBreast != null) return tGenericToBreast;
		tGenericToBreast = new HashMap<String, String>();
		
		tGenericToBreast.put("T0", "T0"); tGenericToBreast.put("T1", "T1");
		tGenericToBreast.put("T1a", "T1a"); tGenericToBreast.put("T1a2", "T1a");
		tGenericToBreast.put("T1b", "T1b"); tGenericToBreast.put("T1b1", "T1b");
		tGenericToBreast.put("T1b2", "T1b"); tGenericToBreast.put("T1c", "T1c");
		tGenericToBreast.put("T1mic", "T1mic"); tGenericToBreast.put("T2", "T2");
		tGenericToBreast.put("T2a", "T2"); tGenericToBreast.put("T2b", "T2");
		tGenericToBreast.put("T2c", "T2"); tGenericToBreast.put("T3", "T3");
		tGenericToBreast.put("T3a", "T3"); tGenericToBreast.put("T3b", "T3");
		tGenericToBreast.put("T3c", "T3"); tGenericToBreast.put("T4", "T4");
		tGenericToBreast.put("T4a", "T4a"); tGenericToBreast.put("T4b", "T4b");
		
		tGenericToBreast.put("T4b", "T4b"); tGenericToBreast.put("T4c", "T4c");
		tGenericToBreast.put("T4d", "T4d"); tGenericToBreast.put("Tis", "Tis");
		tGenericToBreast.put("TX", "TX"); 	
		return tGenericToBreast;
	}

}
