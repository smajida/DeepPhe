package edu.pitt.dbmi.deepphe.data.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SEER_DocumentTransformer {

	/**
	 * transfoer file
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public void transform(File input, File output) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(input));
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		
		StringBuffer header = new StringBuffer();
		StringBuffer report = new StringBuffer();
		boolean inHeader = false;
		for(String l = reader.readLine(); l != null; l = reader.readLine()){
			l = l.trim();
			if("**PROTECTED[begin]".equals(l)){
				// write report
				if(header.length() > 0)
					writeReport(header.toString(),report.toString(),writer);
				// reset buffers
				header =  new StringBuffer();
				report = new StringBuffer();
				inHeader = true;
			}else if("**PROTECTED[end]".equals(l)){
				inHeader = false;
			}else if(inHeader){
				header.append(l);
			}else if(l.matches("</[A-Z_]+>") || "E_O_R".equals(l) || l.matches("={10,}") || l.matches(".*\\.{10,}.*")){
				// skip XML like tag lines and end of reports
				// also skip E_O_R and DeID header info 
			}else{
				// clean up section headers 
				Matcher m = Pattern.compile("<([A-Z _\\(\\)]+)>").matcher(l);
				if(m.find()){
					String section = m.group(1);
					if(section.startsWith("TEXT_PATH_"))
						section = section.substring("TEXT_PATH_".length());
					section = section.replaceAll("_"," ");
					l = l.replaceAll("<([A-Z _\\(\\)]+)>","\n"+section+":");
				}
				l = l.replaceAll("\\Q\\X0D\\\\X0A\\\\E","\n");
				report.append(l+"\n");
			}
		}
		
		// write report
		if(header.length() > 0)
			writeReport(header.toString(),report.toString(),writer);
		
		reader.close();
		writer.close();
		
	}
	
	
	private void writeReport(String header, String report, Writer writer) throws IOException {
		Matcher m1 = Pattern.compile("<PATIENT_DISPLAY_ID>(.*)</PATIENT_DISPLAY_ID>").matcher(header);
		Matcher m2 = Pattern.compile("<RECORD_DOCUMENT_ID>(.*)</RECORD_DOCUMENT_ID>").matcher(header);
		m1.find();
		m2.find();
		String reportId = m2.group(1).trim();
		String patientId = m1.group(1).trim();
		String patientName = "**NAME[AAA, BBB]";
		String principalDate = "20000101"; // no principal date, given, lets just make it up
		String recordType = "SP"; //sergical pathology
		String deIDBlurb = "[Report de-identified (Safe-harbor compliant) by De-ID v.6.24.5.2]";
		
		// write header info
		writer.write("===================================================================\n");
		writer.write("Report ID....................."+reportId+"\n");
		writer.write("Patient ID...................."+patientId+"\n");
		writer.write("Patient Name.................."+patientName+"\n");
		writer.write("Principal Date................"+principalDate+"\n");
		writer.write("Record Type..................."+recordType+"\n");
		writer.write("===================================================================\n");
		writer.write(deIDBlurb+"\n");
		writer.write(report+"E_O_R\n\n");
	}


	public static void main(String[] args) throws IOException {
		File in = new File("/home/tseytlin/Data/DeepPhe/Data/SEER/Breast");
		File out = new File(in+"-Transformed");
		out.mkdirs();
		
		SEER_DocumentTransformer seer = new SEER_DocumentTransformer();
		
		for(File i: in.listFiles()){
			System.out.println("transforming "+i.getName()+" ..");
			seer.transform(i,new File(out,i.getName()));
		}
		System.out.println("done");
	}

	

}
