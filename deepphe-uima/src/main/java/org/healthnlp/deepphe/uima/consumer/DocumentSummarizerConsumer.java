package org.healthnlp.deepphe.uima.consumer;

import java.io.File;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;
import org.healthnlp.deepphe.util.TextUtils;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;



public class DocumentSummarizerConsumer extends CasConsumer_ImplBase {
	private ResourceFactory resourceFactory;
	
	
	
	
	public void initialize() throws ResourceInitializationException {
		super.initialize();
		File ontologyFile = new File("ontologies/breastCancer.owl");
		IOntology ontology;
		try {
			ontology = OOntology.loadOntology(ontologyFile);
			resourceFactory = new ResourceFactory(ontology);
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}




	public void processCas(CAS cas) throws ResourceProcessException {
		JCas jcas;
		try {
			jcas = cas.getJCas();
			Report report = resourceFactory.getReport(jcas);
			//report.setTitleSimple(TextUtils.stripSuffix(file.getName()));
			//report.save(new File(out,"CT"));
			
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}
	
	}

}
