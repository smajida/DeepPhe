package edu.pitt.dbmi.deep.phe.data;

import java.io.*;
import java.util.*;

import edu.pitt.dbmi.deep.phe.data.model.*;
import edu.pitt.dbmi.deep.phe.util.TextUtils;

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
		String domain = "Breast";
		File patientDates = new File("/home/tseytlin/Data/DeepPhe/Samples/Sample-Jan-2015/"+domain+"/"+domain.toLowerCase()+"_patient_sample+dates.csv");
		File dataFile = new File("/home/tseytlin/Data/DeepPhe/Samples/Sample-Jan-2015/"+domain+"/"+domain.toLowerCase()+"_sample.bar");
		File outputFile = new File("/home/tseytlin/Data/DeepPhe/Samples/Sample-Jan-2015/"+domain+"/"+domain.toLowerCase()+"_sample_filtered.bar");
		if(outputFile.exists())
			outputFile.delete();
		
		
		DataPatientSampler ps = new DataPatientSampler();
		DataReportSampler rs = new DataReportSampler();
		
		// load selected patient data into data structure
		Map<String,Patient> patientMap = ps.loadBARDataset(dataFile);
		
		// load extra date information
		rs.loadPatientDates(patientMap,patientDates);
		
		// now for a given list of patients only write reports that fit the given criteria 
		for(Patient p: patientMap.values()){
			rs.saveReportSample(p,outputFile);
		}
		
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
	private void saveReportSample(Patient p, File outputFile) throws IOException {
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
				if(pt != null){
					pt.addDate(TextUtils.parseDateString(date),type);
				}
			}
		}
		r.close();
	}

}