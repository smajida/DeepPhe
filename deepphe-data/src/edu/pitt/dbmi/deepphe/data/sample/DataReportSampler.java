package edu.pitt.dbmi.deepphe.data.sample;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.pitt.dbmi.deepphe.data.model.*;
import edu.pitt.dbmi.deepphe.util.TextUtils;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;

public class DataReportSampler {
	private List<Filter> filters;
	private interface Filter {
		/**
		 * @param p patient to look at
		 * @return true if it passes a filter
		 */
		public boolean filter(Report r);
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//String domain = "Melanoma";
		boolean delimitedInput = true;
		File dir = new File("/home/tseytlin/Data/DeepPhe/Data/TCGA/");
		File patientDates = new File(dir,"TCGA_patients+dates.csv");
		File dataFile = new File(dir,"TCGA_CARE_data.tsv");
		File outputFile = new File(dir,"TCGA_CARE_data_filtered.bar");
		if(!outputFile.getParentFile().exists())
			outputFile.getParentFile().mkdirs();
		if(outputFile.exists())
			outputFile.delete();
		
		
		DataReportSampler rs = new DataReportSampler();
		
		// load selected patient data into data structure
		Map<String,Patient> patientMap = (delimitedInput)?rs.loadDelimitedDataset(dataFile):rs.loadBARDataset(dataFile);
		
		// load extra date information
		rs.loadPatientDates(patientMap,patientDates);
		
		// now for a given list of patients only write reports that fit the given criteria 
		for(Patient p: patientMap.values()){
			// skip patients that didn't have extra dates associated with them
			if(!p.getDates().isEmpty())
				rs.saveReportSample(p,outputFile);
		}
	}

	
	/**
	 * Load dataset of BAR dataset
	 * @param dataFile
	 * @throws IOException 
	 */
	public Map<String,Patient> loadBARDataset(File dataFile) throws IOException {
		Map<String,Patient> results = new HashMap<String, Patient>();
		
		BufferedReader r = new BufferedReader(new FileReader(dataFile));
		boolean inReport = false;
		StringBuffer b = new StringBuffer();
		for(String l=r.readLine();l != null;l=r.readLine()){
			l = l.trim();
			if("S_O_H".equals(l)){
				inReport = true;
				b.append(l+"\n");
			}else if("E_O_R".equals(l)){
				b.append(l+"\n");
				Report rp = Report.readBARformat(b.toString());
				Patient p = results.get(rp.getPatient().getMedicalRecordNumber());
				if(p == null){
					p = rp.getPatient();
					results.put(p.getMedicalRecordNumber(),p);
				}
				p.addReport(rp);
				// reset
				inReport = false;
				b = new StringBuffer();
			}else if(inReport){
				b.append(l+"\n");
			}
			
		}
		r.close();
		return results;
	}
	
	
	/**
	 * Load dataset of BAR dataset
	 * @param dataFile
	 * @throws IOException 
	 */
	public Map<String,Patient> loadDelimitedDataset(File dataFile) throws IOException {
		Map<String,Patient> results = new HashMap<String, Patient>();
		
		BufferedReader r = new BufferedReader(new FileReader(dataFile));
		List<String> names = null;
		for(String l=r.readLine();l != null;l=r.readLine()){
			l = l.trim();
			if(names == null){
				names =  TextTools.parseCSVline(l,'\t');
			}else{
				List<String> values = TextTools.parseCSVline(l,'\t');
				Map<String,String> rmap = new LinkedHashMap<String, String>();
				for(int i=0;i<values.size();i++){
					String key = names.get(i);
					String val = values.get(i).trim();
					rmap.put(key,val);
				}
				if(!rmap.isEmpty() && rmap.containsKey("NOTE_TEXT")){
					Report rp = Report.readMAPformat(rmap);
					Patient p = results.get(rp.getPatient().getMedicalRecordNumber());
					if(p == null){
						p = rp.getPatient();
						results.put(p.getMedicalRecordNumber(),p);
					}
					// check if this report should be appended to a previous one
					if(Integer.parseInt(rmap.get("LINE")) > 1){
						Report last = p.getReport(rp.getRecordId());
						if(last != null){
							last.appendReport(rp);
						}else{
							// if we don't have report, then it must have been thrown out because
							// it was duplicated earlier
							//System.err.println(rp.getRecordId()+" "+rmap.get("LINE"));
						}
					}else{
						p.addReport(rp);
					}
				}
			}
		}
		r.close();
		return results;
	}
	
	
	
	public List<Filter> getFilters() {
		if(filters == null){
			filters = new ArrayList<Filter>();
			filters.add(new Filter(){
				public boolean filter(Report r) {
					Patient p = r.getPatient();
					long millis2days = 1000*60*60*24;
					long event  = r.getEventDate().getTime()/millis2days;
					
					SortedSet<Date> treatments = p.getTreatmentDates();
					Date init =  p.getInitialDiagnosisDate();
					Date recur =  p.getRecurrenceDate();
					Date death = p.getDeathDate();
					
					// if we don't have any dates, we are skipping this report
					if(init == null)
						return false;
					
					// 1st chunk starts 1 month before initial date 
					// and ends with 6 month after last treatment (before first recurrence)
					long st1 = init.getTime()/millis2days - 28;
					long en1 = getLastTreatment(treatments,init,recur).getTime()/millis2days  + 182;
					
					// passed 1st chunk
					if(st1 <= event && event <= en1)
						return true;
					
					// if we have recurrence
					if(recur != null){
						long st2 = recur.getTime()/millis2days - 28;
						long en2 = getLastTreatment(treatments,recur,death).getTime()/millis2days + 182;
						// passed 2nd chunk
						if(st2 <= event && event <= en2)
							return true;
					}
					
					// if we have death
					if(death != null){
						long st3 = death.getTime()/millis2days - 28;
						long en3 = death.getTime()/millis2days;
						
						// passed 3rd chunk
						if(st3 <= event && event <= en3)
							return true;
					}
					return false;
				}

			});
		}
		return filters;
	}
	

	private Date getLastTreatment(SortedSet<Date> treatments,Date from, Date to) {
		Date dt = from;
		for(Date d: treatments){
			if(d.after(from)){
				if(to != null && d.after(to))
					break;
				dt = d;
			}
		}
		return dt;
	}
	
	/**
	 * Is this PatientElement kosher for our selection
	 * @param p
	 * @return
	 */
	public boolean filter(Report p){
		for(Filter f: getFilters()){
			if(!f.filter(p))
				return false;
		}
		return true;
	}
	
	/**
	 * filter reports based on cancer dates
	 * @param p
	 * @return
	 */
	private List<Report> filterReports(Patient p) {
		List<Report> filtered = new ArrayList<Report>();
		for(Report r: p.getReports()){
			if(filter(r))
				filtered.add(r);
		}
		return filtered;
	}


	
	
	/**
	 * save filtered reports to an output file
	 * @param p
	 * @param outputFile
	 * @throws IOException 
	 */
	public void saveReportSample(Patient p, File outputFile) throws IOException {
		List<Report> filtered = filterReports(p);
		System.out.println(p.getMedicalRecordNumber()+" -> sampled "+filtered.size()+" out of "+p.getReportCount());
		BufferedWriter w = new BufferedWriter(new FileWriter(outputFile,true));
		for(Report r: filtered){
			w.write(r.getText()+"\n");
		}
		w.close();
	}

	/**
	 * load additional dates into a patient map
	 * @param patientMap
	 * @param patientDates
	 * @throws IOException 
	 */
	public void loadPatientDates(Map<String, Patient> patientMap, File patientDates) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(patientDates));
		for(String l = r.readLine();l != null; l = r.readLine()){
			String [] p = l.split(",");
			if(p.length > 2){
				String mrn = p[0].trim();
				String date = p[1].trim();
				String type = p[2].trim();
				Patient pt = patientMap.get(mrn);
				if(pt == null){
					Matcher m = Pattern.compile("(\\d+)[A-Z]{2,3}").matcher(mrn);
					if(m.matches()){
						pt = patientMap.get(m.group(1));
					}
				}
				if(pt != null){
					pt.addDate(TextUtils.parseDateString(date),type);
				}
			}
		}
		r.close();
	}

}
