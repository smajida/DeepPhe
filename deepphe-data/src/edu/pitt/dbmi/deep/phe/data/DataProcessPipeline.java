package edu.pitt.dbmi.deep.phe.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.pitt.dbmi.deep.phe.data.deid.DeIDNameResolver;
import edu.pitt.dbmi.deep.phe.data.deid.DeIDRealNameScrubber;
import edu.pitt.dbmi.deep.phe.data.deid.DeIDReportSplitter;
import edu.pitt.dbmi.deep.phe.data.deid.DeIDTagScrubber;
import edu.pitt.dbmi.deep.phe.data.model.Patient;
import edu.pitt.dbmi.deep.phe.data.sample.DataPatientSampler;
import edu.pitt.dbmi.deep.phe.data.sample.DataReportSampler;
import edu.pitt.dbmi.deep.phe.data.sample.PatientDateLoader;

public class DataProcessPipeline {

	/**
	 * creates a pipeline of things that need to be done for each dataset
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		File dir = new File("/home/tseytlin/Data/DeepPhe/Data/Sample/Work/");
		String dataPrefix = "EPIC_breast";
			
		File dataFile  = new File(dir,"EPIC_data.tsv");
		File summaryFile  = new File(dir,dataPrefix+"_data.tsv");
		File whoFile = new File(dir,dataPrefix+"_patient_sample.who");
		File patientDates  = new File(dir,"ovarian_patient_sample+dates.csv");
		File dataSampleFile = new File(dir,dataPrefix+"_data_filtered.bar");
		File dataSampeFileDeID = new File(dir,dataPrefix+"_data_filtered.deid");
		File dataSampeFileFixed = new File(dir,dataPrefix+"_data_filtered.deid.fixed");
		File dataSampeFileScrubbed = new File(dir,dataPrefix+"_data_filtered.scrubbed");
		File dataSampeDirectory = new File(dir,dataPrefix+"_data_filtered_scrubbed");
		File linkFile = new File(dir,dataPrefix+"_data_filtered.link");
		
		// create dates file
		if(false)
			createDates(dataFile,patientDates);

		// create patient sample 
		if(false)
			createPatientSample(summaryFile, patientDates, whoFile);
		
		// pull data from MARS
		
		// create report subsampe around dates of interest
		if(false)
			createReportSample(dataFile,patientDates,dataSampleFile);
	
		// de-identify with DeID
		//RUN DE-ID in Windows, produce dataSampeFileDeID
		
		if(true){	
			// fix DeID mistakes
			createFixedDeidentifiedDataset(dataSampleFile,dataSampeFileDeID,dataSampeFileFixed);
			
			// scrub DeID tags from document
			createScrubbedDataset(dataSampeFileFixed,dataSampeFileScrubbed);
			
			// split reports into directory
			splitDatasetReports(dataSampeFileScrubbed, dataSampeDirectory);
		
			// create a linage file for future cross-reference
			createLinkageFile(dataSampleFile, dataSampeFileScrubbed, dataSampeDirectory, linkFile);
		}
	}

	private static void createDates(File dataFile, File patientDates) throws IOException {
		System.out.print("creating patient dates of interest "+patientDates.getName()+" ..");
		PatientDateLoader.convert(dataFile,patientDates);
		System.out.println("ok");
	}
	
	private static void createReportSample(File dataFile,File patientDates, File dataSampleFile) throws IOException{
		System.out.println("creating a sub-sample of reports around dates of interest ..");
		if(!dataSampleFile.getParentFile().exists())
			dataSampleFile.getParentFile().mkdirs();
		if(dataSampleFile.exists())
			dataSampleFile.delete();
		
		boolean delimitedInput = dataFile.getName().endsWith(".tsv");
		
		DataReportSampler rs = new DataReportSampler();
		
		// load selected patient data into data structure
		Map<String,Patient> patientMap = (delimitedInput)?rs.loadDelimitedDataset(dataFile):rs.loadBARDataset(dataFile);
		
		// load extra date information
		rs.loadPatientDates(patientMap,patientDates);
		
		// now for a given list of patients only write reports that fit the given criteria 
		for(Patient p: patientMap.values()){
			// skip patients that didn't have extra dates associated with them
			if(!p.getDates().isEmpty())
				rs.saveReportSample(p,dataSampleFile);
		}
	}
	
	private static void createFixedDeidentifiedDataset(File dataSampleFile, File dataSampeFileDeID, File dataSampeFileFixed ) throws Exception{
		System.out.println("fixing DeID mistakes in report sub-sample .. ");
		DeIDRealNameScrubber scrubber = new DeIDRealNameScrubber();
		scrubber.loadRealNames(dataSampleFile);
		scrubber.process(dataSampeFileDeID,dataSampeFileFixed);
	}
	
	private static void createScrubbedDataset(File fd, File fs) throws FileNotFoundException, IOException{
		System.out.print("scrubbing DeID tags and replacing with fake names .. ");
		DeIDTagScrubber scrubber = new DeIDTagScrubber();
		//scrubber.loadRealNames(fn);
		String text = scrubber.process(fd);
		
		BufferedWriter w = new BufferedWriter(new FileWriter(fs));
		w.write(text);
		w.close();
		
		System.out.println("ok");
		//System.out.println(scrubber.nameMap);
	}
	
	private static void splitDatasetReports(File fs, File fd) throws IOException {
		System.out.print("splitting file into individual reports .. ");
		DeIDReportSplitter splitter = new DeIDReportSplitter();
		splitter.split(fs,fd);
		System.out.println("ok");
	}
	
	private static void createLinkageFile(File dataSampleFile, File dataSampeFileScrubbed, File dataSampeDirectory, File linkFile) throws Exception{
		DeIDNameResolver resolver = new DeIDNameResolver();
		System.out.println("reading in data files .. ");
		List<Map<String,String>> result = resolver.resolve(dataSampleFile, dataSampeFileScrubbed);
		System.out.println("resolve with  data directory .. ");
		resolver.resolve(result,dataSampeDirectory);
		System.out.println("saving a link file .. ");
		resolver.saveLink(result, linkFile);
	}
	
	private static void createPatientSample(File summaryFile, File patientDates, File whoFile ) throws Exception{
		DataPatientSampler ds = new DataPatientSampler();
	
		System.out.println("loading dataset .. "+summaryFile);
		
		// load patient/report data
		ds.loadSummaryDataset(summaryFile);
		ds.loadCancerDates(patientDates);
		ds.sortReports();
		
		// now do filtering & sampling
		List<Patient> patients = ds.getPatientSample(ds.getFilteredPatients(),50);
		
		// lets look at them 
		System.out.println("..");
		for(Patient p: patients){
			System.out.println(p.getMedicalRecordNumber()+" -> "+p.getReports().size()+" reports"+" .. "+p.getCancerDate()+" | "+p.getReports().get(0).getEventDate() +" .. "+p.getReports().get(p.getReportCount()-1).getEventDate());
		}
		System.out.println("..");
		
		// just save the MRNs for extraction
		ds.save(patients,whoFile);
	}
}
