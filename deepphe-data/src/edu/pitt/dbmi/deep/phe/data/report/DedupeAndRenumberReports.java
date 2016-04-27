package edu.pitt.dbmi.deep.phe.data.report;

import java.io.File;
import java.io.IOException;

import edu.pitt.dbmi.deep.phe.data.model.Patient;
import edu.pitt.dbmi.deep.phe.data.model.Report;


public class DedupeAndRenumberReports {

	public static void main(String[] args) throws IOException {
		File inputDir = new File("/home/tseytlin/Data/DeepPhe/Data/Sample/DeepPhe_130_patients");
		File outputDir = new File("/home/tseytlin/Data/DeepPhe/Data/Sample/DeepPhe_130_patients.clean");
		
		DedupeAndRenumberReports dr = new DedupeAndRenumberReports();
		dr.process(inputDir,outputDir);

	}

	private void process(File inputDir, File outputDir) throws IOException {
		for(File f: inputDir.listFiles()){
			if(f.isDirectory()){
				if(f.getName().startsWith("patient")){
					processPatient(f,new File(outputDir,f.getName()));
				}else{
					process(f,new File(outputDir,f.getName()));
				}
			}
		}
	}

	private void processPatient(File patientDir, File outputDir) throws IOException {
		System.out.print("processing "+patientDir.getName()+" .. ");
		Patient p = new Patient();
		p.setName(patientDir.getName());
		int n = 0;
		for(File f: patientDir.listFiles()){
			if(f.getName().endsWith(".txt")){
				Report r = Report.readDeIDformat(f);
				p.addReport(r);
				n++;
			}
		}
		p.sortReports();
		System.out.print(" "+n+" -> "+p.getReportCount()+" ");
		
		// create output
		outputDir.mkdirs();
		
		// renumber reports
		p.writeReports(outputDir);
		
		System.out.println("ok");
		
	}
}
