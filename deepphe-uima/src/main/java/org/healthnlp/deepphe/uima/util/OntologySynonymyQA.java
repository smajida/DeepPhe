package org.healthnlp.deepphe.uima.util;

import edu.pitt.dbmi.nlp.noble.ontology.*;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;

public class OntologySynonymyQA {

	public static void main(String[] args) throws IOntologyException {
		String ontology = "/home/tseytlin/Work/ontologies/deepphe/nlpBreastCancer.owl";
		IOntology ont = OOntology.loadOntology(ontology);
		for(IClass cls: ont.getRoot().getSubClasses()){
			Concept c = cls.getConcept();
			if(c.getName().contains(" ")){
				for(String s: c.getSynonyms()){
					if(!s.contains(" ")){
						System.out.println(s+"\t"+cls.getURI()+"\t"+c.getName());
					}
				}
			}
		}
	}

}
