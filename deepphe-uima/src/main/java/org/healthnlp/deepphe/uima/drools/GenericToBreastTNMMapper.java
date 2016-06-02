package org.healthnlp.deepphe.uima.drools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericToBreastTNMMapper {
	
	static List <String> tBreast = null;
	static List <String> nPathologicBreast = null;
	
	public static String getBreastTClassification(String prefix, String genericValue, String suffix){
		String pref = "";
		if(prefix.equals("p_modifier"))
			pref = "p";
		else if(prefix.equals("c_modifier"))
			pref = "c";
		
		if("".equals(pref)) return null;
		
		String genV = genericValue.substring(0, genericValue.indexOf("_"));
		if(!getTList().contains(genV)) return null;
		
		String suff = "";
		if(genV.equals("Tis") && (suffix.equals("DCIS") || suffix.equals("LCIS") || suffix.equals("Paget")))
			 suff = "_"+suffix;
		return "Breast_Cancer_"+pref+genV+suff+"_TNM_Finding";
	}
	
	public static String getBreastNClassification(String prefix, String genericValue, String suffix){
		String pref = "";
		if(prefix.equals("p_modifier"))
			pref = "p";
		else if(prefix.equals("c_modifier"))
			pref = "c";
		
		if("".equals(pref)) return null;
		String genV = genericValue.substring(0, genericValue.indexOf("_"));
		
	
	    return "";
	}
	
	public static List<String> getTList(){
		if(tBreast != null) return tBreast;
		tBreast = new ArrayList<String>();	
		String[] tBreasArr = new String[] {"T0", "T1", "T1a", "T1b", "T1c", "T1mic","T2", "T3", "T4","T4a", "T4b", "T4c","T4d", "Tis", "TX"};
		Collections.addAll(tBreast, tBreasArr); 
		return tBreast;
	}
	
	public static List<String> getPathologicNMap(){
		if(nPathologicBreast != null) return nPathologicBreast;
		nPathologicBreast = new ArrayList<String>();
		String[] tBreasArr = new String[] {"N0", "N1", "N1a", "N1b", "N1c", "N1mic","N2", "N2a", "N2b","N3", "N3a", "N3b", "N3c","NX"};
		Collections.addAll(nPathologicBreast, tBreasArr); 
		return nPathologicBreast;
	}

}
