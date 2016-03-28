package org.healthnlp.deepphe.uima.ae;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;

public class SummaryTextOutput extends JCasAnnotator_ImplBase {
	private File outputDir;
	public static final String PARAM_OUTPUTDIR = "OUTPUT_DIR";;

	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		outputDir = new File((String) aContext.getConfigParameterValue(PARAM_OUTPUTDIR),"SUMMARY");
		if(!outputDir.exists())
			outputDir.mkdirs();
	}
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		Patient patient = PhenotypeResourceFactory.loadPatient(jcas);
		String patientName = patient != null?patient.getPatientName():"unknown";
		
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){
			try {
				saveText(report.getSummaryText(),new File(outputDir,patientName+File.separator+report.getTitle()+".txt"));
			} catch (IOException e) {
				throw new AnalysisEngineProcessException(e);
			}
		}
		
		StringBuffer summary = new StringBuffer();
		
		MedicalRecord record = PhenotypeResourceFactory.loadMedicalRecord(jcas);
		
		PatientSummary patientSummary = record.getPatientSummary();
		if(patientSummary != null)
			summary.append(patientSummary.getSummaryText()+"\n-------------\n");
		CancerSummary cancerSummary = record.getCancerSummary();
		if(cancerSummary != null)
			summary.append(cancerSummary.getSummaryText()+"\n-------------\n");
		
		// save all summaries
		try {
			saveText(summary.toString(),new File(outputDir,patientName+File.separator+"MEDICAL_RECORD_SUMMARY.txt"));
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		}
	
	}
	
	/**
	 * save generic text file
	 * @param text
	 * @param file
	 * @throws IOException
	 */
	private void saveText(String text, File file) throws IOException{
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		BufferedWriter w = new BufferedWriter(new FileWriter(file));
		w.write(text+"\n");
		w.close();
	}

}
