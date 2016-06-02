package org.healthnlp.deepphe.uima.ae;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.fact.DefaultFactList;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.fact.TextMention;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.OntologyUtils;

public class EvaluationOutput  extends JCasAnnotator_ImplBase {
	private static final String I = "|";
	private static final String FS = ";";
	private final String EVAL_CANCER_FILE ="DeepPhe_Evaluation_Cancer.bsv";
	private final String EVAL_TUMOR_FILE  ="DeepPhe_Evaluation_Tumor.bsv";
	
	private File outputDir;
	public static final String PARAM_OUTPUTDIR = "OUTPUT_DIR";
	public static final String PARAM_EVALUATION_CANCER_MAP_FILE = "EVALUATION_CANCER_MAP_FILE";
	public static final String PARAM_EVALUATION_TUMOR_MAP_FILE = "EVALUATION_TUMOR_MAP_FILE";
	public static final String ENTRY_LABEL = "Label";
	public static final String ENTRY_CLASS = "Class";
	public static final String ENTRY_PROPERTY = "Property";
	public static final String ENTRY_PROVENANCE = "wasDerivedFrom";
	//public static final List<String> NORMALIZATION_EXPRESSIONS = Arrays.asList(".*([pc]?[TNM]\\w{1,4})_(Stage|TNM)_Finding");

	
	private List<Map<String,String>> cancerMapping, tumorMapping;

	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		outputDir = new File((String) aContext.getConfigParameterValue(PARAM_OUTPUTDIR),"EVAL");
		if(!outputDir.exists())
			outputDir.mkdirs();
		
		try {
			cancerMapping = loadEvaluationMap(new File((String) aContext.getConfigParameterValue(PARAM_EVALUATION_CANCER_MAP_FILE)));
			tumorMapping  = loadEvaluationMap(new File((String) aContext.getConfigParameterValue(PARAM_EVALUATION_TUMOR_MAP_FILE)));
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
		
		
		try {
			writeHeader(cancerMapping,new File(outputDir,EVAL_CANCER_FILE));
			writeHeader(tumorMapping,new File(outputDir,EVAL_TUMOR_FILE));
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
	}
	


	public void process(JCas jcas) throws AnalysisEngineProcessException {
		MedicalRecord record = PhenotypeResourceFactory.loadMedicalRecord(jcas);
		
		if(record.getPatient()==null) {
			throw new AnalysisEngineProcessException(new Exception("Medical Record has no patient attached. Skipping"));
		}

		System.out.println(record.getSummaryText());
		
		writeDataFile(cancerMapping, new File(outputDir,EVAL_CANCER_FILE), record.getCancerSummary());
		writeDataFile(tumorMapping,  new File(outputDir,EVAL_TUMOR_FILE), record.getCancerSummary().getTumors());
	}


	private void writeHeader(List<Map<String, String>> mapList, File file) throws IOException {
		// has actual columns described above
		StringBuffer buffer  = new StringBuffer();
		for(Map<String,String> map: mapList){
			String label = map.get(ENTRY_LABEL);
			buffer.append(label+I);
		}
		//buffer.append("\n");
		saveText(buffer.toString(), file,false);
	}

	
	private String getPatientIdentifier(Patient patient){
		return patient.getPatientName().toLowerCase();
	}
	
	private String getCancerIdentifier(CancerSummary cancer){
		String temporality = "Current";
		StringBuffer b = new StringBuffer("cancer_");
		b.append(getPatientIdentifier(cancer.getPatient())+"_");
		String bodySite = "";
		for(Fact site: cancer.getBodySite()){
			bodySite = site.getFullName()+"_";
			//TODO: this is bad, I should not hard code this. 
			// Will fix after demo, perhaps infer organ from ontology name?
			if(FHIRConstants.BREAST.equals(site.getName())){
				break;
			}
		}
		b.append(bodySite);
		b.append(temporality);
		//b.replace(b.length()-1,b.length(),"");
		return b.toString(); 
	}
	
	
	private void writeDataFile(List<Map<String,String>> mapping, File dataFile, CancerSummary cancer) throws AnalysisEngineProcessException {
		StringBuffer buffer = new StringBuffer();

		FactList valueFacts = null;
		for(Map<String,String> map: mapping){
			String dataLabel = map.get(ENTRY_LABEL);
			String entryClass = map.get(ENTRY_CLASS);
			String entryProperty = map.get(ENTRY_PROPERTY);
		
			// special case for some data labels
			String value = "";
			if(dataLabel.startsWith("*") && dataLabel.contains("patient")){
				value = getPatientIdentifier(cancer.getPatient());
			}else if(dataLabel.startsWith("*") && dataLabel.contains("cancer")){
				value = getCancerIdentifier(cancer);
			}else if(dataLabel.startsWith("-") && ENTRY_PROVENANCE.equals(entryProperty)){
				value = getProvenance(valueFacts);
			}else{
				valueFacts = getSummaryFactValue(cancer, entryClass, entryProperty);
				if(valueFacts.isEmpty())
					valueFacts = getSummaryFactValue(cancer.getPhenotype(), entryClass, entryProperty);
				value = getFactValue(valueFacts,entryClass,entryProperty);
			}
			buffer.append(value).append(I);

		}
		buffer.append("\n");


		// save to file
		try {
			saveText(buffer.toString(),dataFile,true);
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}
	
	private String getProvenance(FactList valueFacts) {
		if(valueFacts == null)
			return "";
		StringBuffer b = new StringBuffer();
		Set<String> mentions = new LinkedHashSet<String>();
		for(Fact f : valueFacts){
			//System.err.println(f.getDocumentIdentifier());
			for(TextMention t: f.getProvenanceText()){
				mentions.add(f.getDocumentName()+": "+t.toString());
			}
		}
		for(String s: mentions){
			b.append(s+FS);
		}
		
		return b.toString();
	}



	private void writeDataFile(List<Map<String,String>> mapping, File dataFile, List<TumorSummary> tumors) throws AnalysisEngineProcessException {
		StringBuffer buffer = new StringBuffer();
		FactList valueFacts = null;
		for(TumorSummary tumor : tumors){
			for(Map<String,String> map: mapping){
				String dataLabel = map.get(ENTRY_LABEL);
				String entryClass = map.get(ENTRY_CLASS);
				String entryProperty = map.get(ENTRY_PROPERTY);
			
				// special case for some data labels
				String value = "";
				if(dataLabel.startsWith("*") && dataLabel.contains("patient")){
					value = getPatientIdentifier(tumor.getPatient());
				}else if(dataLabel.startsWith("*") && dataLabel.contains("cancer")){
					value = getCancerIdentifier(tumor.getCancerSummary());
				}else if(dataLabel.startsWith("-") && ENTRY_PROVENANCE.equals(entryProperty)){
					value = getProvenance(valueFacts);
				}else{
					valueFacts = getSummaryFactValue(tumor, entryClass, entryProperty);
					if(valueFacts.isEmpty())
						valueFacts = getSummaryFactValue(tumor.getPhenotype(), entryClass, entryProperty);
					value = getFactValue(valueFacts,entryClass,entryProperty);
				}
				buffer.append(value).append(I);
	
			}
			buffer.append("\n");
		}
		// remove last newline
		if(buffer.toString().endsWith("\n"))
			buffer.replace(buffer.length()-1,buffer.length(),"");
		
		// save to file
		try {
			saveText(buffer.toString(),dataFile,true);
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	/**
	 * get value from summary
	 * @param summary
	 * @param entryClass
	 * @param entryRestriction
	 * @param entryProperty
	 * @return
	 */
	
	private FactList getSummaryFactValue(Summary summary, String entryClass,String entryProperty){
		if(summary == null || entryClass == null || entryClass.length() == 0)
			return new DefaultFactList();
		
		// are we getting simple info from summary?
		entryClass = OntologyUtils.toResourceName(entryClass);
		
		FactList list = summary.getFacts(entryProperty);
		if(list != null){
			return list;
			//return list.isEmpty()?"":getFactValue(list,entryClass,"");
		}
		// no such category exists in summary, lookup on the class
		list = new DefaultFactList();
		for(FactList factList : summary.getContent().values()){
			for(Fact fact: factList){
				// we found a class, awesome
				if(isType(fact,entryClass)){
					if(!list.contains(fact))
						list.add(fact);
					// lets check restriction and if it is satisfied 
					//return getFactValue(list,entryClass,entryProperty);
				}
			}
		}
		//if(!list.isEmpty())
		//	return getFactValue(list,entryClass,entryProperty);
				
		return list;
	}
	
	
	
	
	private boolean isType(Fact fact, String entryClass){
		if(fact.getName().equals(entryClass))
			return true;
		// check sub-classes
		if(org.healthnlp.deepphe.util.OntologyUtils.hasInstance()){
			return org.healthnlp.deepphe.util.OntologyUtils.getInstance().hasSuperClass(fact,entryClass);
		}
		
		return false;
	}
	
	

	/**
	 * get fact value for a given property
	 * @param fact
	 * @param entryProperty
	 * @return
	 */
	private String getFactValue(FactList facts, String entryClass, String entryProperty) {
		StringBuffer b = new StringBuffer();
		if(facts == null)
			return "";
		
		for(Fact fact: facts ){
			Fact value = fact.getValue(entryProperty);
			if(value == null)
				continue;
			// if the value is the same thing that we asked for, then presense/absence is really inquired
			if(entryClass.equals(value.getName())){
				b.append("True"+FS);
			}else{
				b.append(getNormalizedValue(value)+FS);
			}
		}
		// remove last field separator
		if(b.length() > 0)
			b.replace(b.length()-1,b.length(),"");
		
		return  b.toString();
	}

	
	private String getNormalizedValue(Fact val){
		// take care of reg/ex
		/*for(String re: NORMALIZATION_EXPRESSIONS){
			Matcher m = Pattern.compile(re).matcher(val.getName());
			if(m.matches())
				return m.group(1);
		}*/
		return val.getName();
	}
	


	private boolean isRestrictionSatisfied(Fact fact, String entryRestriction) {
		// if no restriction specified, then just return true
		if(entryRestriction == null || entryRestriction.trim().length() == 0)
			return true;
		// if this is a common restriction lets split it up into property and value
		if(entryRestriction.contains(" some ")){
			String [] p = entryRestriction.split(" some ");
			if(p.length == 2){
				String prop = p[0].trim();
				String val  = OntologyUtils.toResourceName(p[1].trim());
				// now lets check the value for a given method
				Fact valueFact = fact.getValue(prop);
				if(valueFact != null){
					return valueFact.getName().equals(val) || valueFact.getAncestors().contains(val);
				}
			}
		}
		return false;
	}



	/**
	 * load TCGA map from a given file
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	private List<Map<String, String>> loadEvaluationMap(File file) throws IOException {
		List<Map<String,String>> mapping = new ArrayList<Map<String,String>>();
		List<String> header = Arrays.asList(ENTRY_LABEL,ENTRY_CLASS,ENTRY_PROPERTY);
		BufferedReader r = new BufferedReader(new FileReader(file));
		for(String l=r.readLine();l != null;l = r.readLine()){
			String [] parts = l.split("\t");
			Map<String,String> map = new LinkedHashMap<String, String>();
			for(int i=0;i<header.size();i++){
				String val = (parts.length > i)?parts[i]:"";
				map.put(header.get(i).trim(),val);
			}
				mapping.add(map);
		}
		r.close();
		return mapping;
	}
	
	
	/**
	 * save generic text file
	 * @param text
	 * @param file
	 * @throws IOException
	 */
	private void saveText(String text, File file, boolean append) throws IOException{
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		BufferedWriter w = new BufferedWriter(new FileWriter(file,append));
		w.write(text+"\n");
		w.close();
	}

}

