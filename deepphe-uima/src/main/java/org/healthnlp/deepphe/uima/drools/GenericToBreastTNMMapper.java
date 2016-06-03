package org.healthnlp.deepphe.uima.drools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericToBreastTNMMapper {
	
	static List <String> tBreast = null;
	static List <String> nPathologicBreast = null;
	static List <String> nClinicalBreast = null;
	static List <String> mPathologicBreast = null;
	static List <String> mClinicalBreast = null;
	
	public static String getBreastTClassification(String prefix, String genericValue, String suffix){
		String pref = "";
		if(prefix.equals("p_modifier"))
			pref = "p";
		else if(prefix.equals("c_modifier"))
			pref = "c";
		
		if("".equals(pref)) return null;
		
		String genV = genericValue.substring(0, genericValue.indexOf("_"));
		if(!get_T_List().contains(genV)) return null;
		
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
	
	public static boolean hasBreastValue(String prefix, String category, String genericValue){
		if(!prefix.equals("p_modifier") && !prefix.equals("c_modifier"))
			return false;
		
		String prefType = prefix+"_"+category;
		List<String> lookInList = null;
		
		switch (prefType) {
			case "p_modifier_hasPathologicTClassification":
				lookInList = tBreast;
				break;
			case "c_modifier_hasClinicalTClassification":
				lookInList = tBreast;
				break;
		}
		
		if(lookInList != null)
			return lookInList.contains(genericValue);
		else
			return false;
			
	}
	
	public static List<String> get_T_List(){
		if(tBreast != null) return tBreast;
		tBreast = new ArrayList<String>();	
		String[] tBreasArr = new String[] {"T0", "T1", "T1a", "T1b", "T1c", "T1mic","T2", "T3", "T4","T4a", "T4b", "T4c","T4d", "Tis", "TX"};
		Collections.addAll(tBreast, tBreasArr); 
		return tBreast;
	}
	
	public static List<String> getClinical_N_List(){
		if(nClinicalBreast != null) return nClinicalBreast;
		nClinicalBreast = new ArrayList<String>();
		String[] tBreasArr = new String[] {"N0", "N1", "N1mic","N2", "N2a", "N2b","N3", "N3a", "N3b", "N3c","NX"};
		Collections.addAll(nClinicalBreast, tBreasArr); 
		return nClinicalBreast;
	}
	
	public static List<String> getPathologic_N_List(){
		if(nPathologicBreast != null) return nPathologicBreast;
		nPathologicBreast = new ArrayList<String>();
		String[] tBreasArr = new String[] {"N0", "N1", "N1a", "N1b", "N1c", "N1mic","N2", "N2a", "N2b","N3", "N3a", "N3b", "N3c","NX"};
		Collections.addAll(nPathologicBreast, tBreasArr); 
		return nPathologicBreast;
	}
	
	public static List<String> getPathologic_M_List(){
		if(mPathologicBreast != null) return mPathologicBreast;
		mPathologicBreast = new ArrayList<String>();
		String[] tBreasArr = new String[] {"M1", "MX"};
		Collections.addAll(mPathologicBreast, tBreasArr); 
		return mPathologicBreast;
	}
	
	public static List<String> getClinical_M_List(){
		if(mClinicalBreast != null) return mClinicalBreast;
		mClinicalBreast = new ArrayList<String>();
		String[] tBreasArr = new String[] {"M0", "M1"};
		Collections.addAll(mClinicalBreast, tBreasArr); 
		return mClinicalBreast;
	}

}
