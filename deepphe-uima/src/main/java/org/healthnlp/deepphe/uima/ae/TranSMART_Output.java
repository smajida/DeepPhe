package org.healthnlp.deepphe.uima.ae;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;

import edu.pitt.dbmi.nlp.noble.ontology.OntologyUtils;


public class TranSMART_Output  extends JCasAnnotator_ImplBase {
	private final String T = "\t";
	private static final String CATEGORY_CODE = "Category Code";
	private static final String CATEGORY_NUM = "Column Number";
	private static final String DATA_LABEL = "Data Label";
	private static final String ENTRY_CLASS = "Class";
	private static final String ENTRY_RESTRICTION = "Required Avs";
	private static final String ENTRY_PROPERTY = "Property to Return Value";
	
	private static final String STUDY_ID = "STUDY ID";
	private static final String SUBJECT_ID = "SUBJ_ID";
	private static final String DEFAULT_STUDY = "DeepPhe-TCGA";
	

	
	private File outputDir;
	public static final String PARAM_OUTPUTDIR = "OUTPUT_DIR";
	public static final String PARAM_TRANSMART_MAP_FILE = "TRANSMART_MAP_FILE";
	public static final String PARAM_TCGA_ID_MAP_FILE = "TCGA_ID_MAP_FILE";
	private Map<String,String> nameMap;
	private List<Map<String,String>> mapping;

	boolean firstRun = true;

	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		firstRun = true;

		outputDir = new File((String) aContext.getConfigParameterValue(PARAM_OUTPUTDIR),"tranSMART");
		if(!outputDir.exists())
			outputDir.mkdirs();
		try {
			mapping = loadTCGAmap(new File((String) aContext.getConfigParameterValue(PARAM_TRANSMART_MAP_FILE)));
			nameMap = loadNameMap((String) aContext.getConfigParameterValue(PARAM_TCGA_ID_MAP_FILE));
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
	
		

		File metaFile = null;
		File dataFile = null;
		try {
			metaFile = new File(outputDir,"tcga-meta-data-import.tsv");
			metaFile.delete();
			metaFile.createNewFile();

			dataFile = new File(outputDir,"tcga-data-import.tsv");
			dataFile.delete();
			dataFile.createNewFile();
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}

		writeMetaFile(metaFile, dataFile);
	}
	


	private Map<String,String> loadNameMap(String nameMapFile) throws IOException {
		Map<String,String> nameMap = new LinkedHashMap<String, String>();
		if(nameMapFile != null ){
			File file = new File(nameMapFile);
			BufferedReader r = new BufferedReader(new FileReader(file));
			for(String l=r.readLine();l != null;l = r.readLine()){
				String [] parts = l.split(T);
				if(parts.length==2)
					nameMap.put(parts[0].trim().toUpperCase(),parts[1].trim());
			}
			r.close();
		}
		return nameMap;
	}



	public void process(JCas jcas) throws AnalysisEngineProcessException {

		MedicalRecord record = PhenotypeResourceFactory.loadMedicalRecord(jcas);

		if(record.getPatient()==null) {
			throw new AnalysisEngineProcessException(new Exception("Medical Record has no patient attached. Skipping"));
		}

		File dataFile = new File(outputDir,"tcga-data-import.tsv");
		writeDataFile(dataFile, record);
	}

	private void writeDataFile(File dataFile, MedicalRecord record) throws AnalysisEngineProcessException {
		StringBuffer buffer;

		// create a Transmart data file
		// has actual columns described above
		buffer  = new StringBuffer();

		//Add header
		if(firstRun) {
			firstRun = false;
			for (Map<String, String> map : mapping) {
				String dataLabel = map.get(DATA_LABEL);
				buffer.append(dataLabel).append(T);
			}
			buffer.append("\n");
		}

		//Add Content
		for(Map<String,String> map: mapping){
			String dataLabel = map.get(DATA_LABEL);
			String category = map.get(CATEGORY_CODE);
			String entryClass = map.get(ENTRY_CLASS);
			String entryRestriction = map.get(ENTRY_RESTRICTION);
			String entryProperty = map.get(ENTRY_PROPERTY);

			// special case for some data labels
			String value = "";
			if("OMIT".equals(category))
				;
			else if(STUDY_ID.equals(dataLabel)){
				value = DEFAULT_STUDY;
			}else if(SUBJECT_ID.equals(dataLabel)){
				value = getSubjectId(record);
			}else{
				// add value
				Summary summary = getSummary(record,category,entryRestriction);
				if(summary!=null)
					value = getSummaryFactValue(summary, entryClass, entryRestriction,entryProperty);
			}
			buffer.append(value).append(T);

		}
		//buffer.append("\n");


		// save to file
		try {
			saveText(buffer.toString(),dataFile,true);
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	/**
	 *  create a Transmart meta file (.tsv)
	 *  filename: file where the data is
	 *	category: + separated category code
	 *	col #:	column number in data
	 *	data label: leaf column name
	 * @param metaFile
	 * @param dataFile
	 * @throws AnalysisEngineProcessException
     */
	private void writeMetaFile(File metaFile, File dataFile) throws ResourceInitializationException {
		int count = 1;
		StringBuffer buffer = new StringBuffer();

		//Add Header
		buffer.append("Filename").append(T).append("Category Code").append(T).append("Column Number").append(T).append("Data Label").append("\n");

		//Add Contents
		for(Map<String,String> map: mapping){
			buffer.append(dataFile.getName()).append(T);
			buffer.append(map.get(CATEGORY_CODE)).append(T);
			//buffer.append(map.get(CATEGORY_NUM)+T);
			buffer.append(count).append(T);
			buffer.append(map.get(DATA_LABEL)).append("\n");
			count++;
		}
		// save to file
		try {
			saveText(buffer.toString(),metaFile,false);
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
	}


	private String getSubjectId(MedicalRecord record) {
		String name = record.getPatient().getPatientName().toUpperCase();
		return nameMap.containsKey(name)?nameMap.get(name):name;
	}



	/**
	 * get appropriate summary for a given category code
	 * @param category
	 * @return
	 */
	private Summary getSummary(MedicalRecord record, String category, String restriction){
		if(category.startsWith("Cancer")){
			if(category.endsWith("Phenotype"))
				return record.getCancerSummary().getPhenotype();
			else
				return record.getCancerSummary();
		}else if(category.startsWith("Tumor")){
			TumorSummary tumor = getTumor(record.getCancerSummary().getTumors(),restriction);
			if(tumor!=null && category.endsWith("Phenotype"))
				return tumor.getPhenotype();
			else
				return tumor;
		}else if(category.startsWith("Patient")){
			if(category.endsWith("Phenotype"))
				return record.getPatientSummary().getPhenotype();
			else
				return record.getPatientSummary();
		}
		
		return null;
	}
	
	/**
	 * select appropriate tumor
	 * @param tumors
	 * @param restriction
	 * @return
	 */
	private TumorSummary getTumor(List<TumorSummary> tumors, String restriction) {
		//TODO: this is messed up as we need to know which tumor we need
		if(tumors.isEmpty())
			return null;
		return tumors.get(0);
	}



	/**
	 * get value from summary
	 * @param summary
	 * @param entryClass
	 * @param entryRestriction
	 * @param entryProperty
	 * @return
	 */
	
	private String getSummaryFactValue(Summary summary, String entryClass, String entryRestriction,String entryProperty){
		if(summary == null)
			return "";
		
		// are we getting simple info from summary?
		entryClass = OntologyUtils.toResourceName(entryClass);
		
		FactList list = summary.getFacts(entryProperty);
		if(list != null){
			return list.isEmpty()?"":getFactValue(list.get(0),entryClass,"");
		}
		// no such category exists in summary, lookup on the class
		
		for(FactList factList : summary.getContent().values()){
			for(Fact fact: factList){
				// we found a class, awesome
				if(fact.getName().equals(entryClass)){
					// lets check restriction and if it is satisfied 
					if(isRestrictionSatisfied(fact,entryRestriction)){
						return getFactValue(fact,entryClass,entryProperty);
					}
				}
			}
		}
				
		return "";
	}
	
	

	/**
	 * get fact value for a given property
	 * @param fact
	 * @param entryProperty
	 * @return
	 */
	private String getFactValue(Fact fact, String entryClass, String entryProperty) {
		Fact value = fact.getValue(entryProperty);
		if(value == null)
			return "";
		// if the value is the same thing that we asked for, then presense/absence is really inquired
		if(entryClass.equals(value.getName())){
			return "True";
		}
		return value.getName();
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
	private List<Map<String, String>> loadTCGAmap(File file) throws IOException {
		List<Map<String,String>> mapping = new ArrayList<Map<String,String>>();
		List<String> header = new ArrayList<String>();
		BufferedReader r = new BufferedReader(new FileReader(file));
		for(String l=r.readLine();l != null;l = r.readLine()){
			String [] parts = l.split(T);
			// add to header
			if(header.isEmpty()){
				Collections.addAll(header,parts);
				continue;
			}
			// handle data
			Map<String,String> map = new LinkedHashMap<String, String>();
			for(int i=0;i<parts.length;i++){
				map.put(header.get(i).trim(),parts[i].trim());
			}
			// if not omit then add it
//			if(!"OMIT".equals(map.get(CATEGORY_CODE))){
//				mapping.add(map);
//			}

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

