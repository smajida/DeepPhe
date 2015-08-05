package edu.pitt.dbmi.deepphe.summarization.jess;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.pitt.dbmi.deepphe.fhir.model.Report;
import edu.pitt.dbmi.deepphe.fhir.summary.DocumentSummarizer;
import edu.pitt.dbmi.deepphe.fhir.util.TextUtils;
import edu.pitt.dbmi.deepphe.summarization.jess.kb.Encounter;
import edu.pitt.dbmi.deepphe.summarization.jess.kb.Patient;
import edu.pitt.dbmi.deepphe.summarization.jess.kb.SummaryFactory;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;

/**
 * fill in Summarization Patient/Encounter objects from FHIR objects
 * @author tseytlin
 *
 */

public class FhirEncounterKnowledgeExtractor implements EncounterKnowledgeExtractor {

	private String projectLocation;
	private Patient patient;
	private List<Report> reports;
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void execute() {
		File project = new File(projectLocation);
		File ontology = new File(project,"data" + File.separator + "ontology" + File.separator + "breastCancer.owl");//breastCAEx.owl
		File sample = new File(project,"data"+File.separator+"sample");
		File types = new File(project,"data"+File.separator+"desc"+File.separator+"TypeSystem.xml");
		
		// load ontology
		try{
			System.out.println("loading ontology .."+ontology.getName());
			IOntology ont = OOntology.loadOntology(ontology);
			DocumentSummarizer summarizer = new DocumentSummarizer(ont);
			
			File [] docs = new File(sample,"xmi").listFiles();
			Arrays.sort(docs);
			// process reports
			reports = new ArrayList<Report>();
			for(File file: docs){
				System.out.println("reading XMI file .."+file.getName());
				JCas cas = summarizer.loadCAS(file,types);
				System.out.println("generating summary ..");;
				Report report = summarizer.process(cas);
				report.setTitleSimple(TextUtils.stripSuffix(file.getName()));
				System.out.println(report.getSummary());
				reports.add(report);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		if(patient == null || reports == null)
			return;
		
		// clear previous encounters
		patient.clearEncounters();
		
		// assume that all reports are from a given patient for now
		int n = 0;
		for(Report r: reports){
			// create an encounter representing the document in question
			Encounter encounter = SummaryFactory.getEncounter(r);
			encounter.setPatientId(patient.getId());
			encounter.setSequence(n++);
			patient.addEncounter(encounter);
		}

	}

	@Override
	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
		
	}

}
