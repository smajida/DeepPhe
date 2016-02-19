package org.healthnlp.deepphe.uima.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import edu.pitt.dbmi.nlp.noble.terminology.SemanticType;
import edu.pitt.dbmi.nlp.noble.terminology.Source;
import edu.pitt.dbmi.nlp.noble.terminology.Terminology;
import edu.pitt.dbmi.nlp.noble.terminology.TerminologyException;
import edu.pitt.dbmi.nlp.noble.terminology.impl.NobleCoderTerminology;

/**
 * create a custom dictionary that will map a dictionary of intest to our existing ontology
 * to give a list of terms, cuis values and mapped URIs
 * @author tseytlin
 *
 */
public class DictionaryGenerator {
	private static final String I = "|";
	private Terminology terminology,metathesaurus;
	private Terminology ontologyTerminology;
	private List<SemanticType> semanticTypeFilter;
	private List<Concept> rootFilter;
	private List<Source> sourceFilter;
	private URI defaultNOS;
	private Set<String> visited;
	
	
	public DictionaryGenerator(Terminology terminology, IOntology ontology) throws IOException, TerminologyException, IOntologyException{
		this.terminology = terminology;
		this.ontologyTerminology = new NobleCoderTerminology(ontology);
	}
	
	
	public List<SemanticType> getSemanticTypeFilter() {
		if(semanticTypeFilter == null)
			semanticTypeFilter = new ArrayList<SemanticType>();
		return semanticTypeFilter;
	}


	public void setSemanticTypeFilter(List<SemanticType> semanticTypeFilter) {
		this.semanticTypeFilter = semanticTypeFilter;
	}


	public List<Concept> getRootFilter() {
		if(rootFilter == null)
			rootFilter = new ArrayList<Concept>();
		return rootFilter;
	}


	public void setRootFilter(List<Concept> rootFilter) {
		this.rootFilter = rootFilter;
	}


	public List<Source> getSourceFilter() {
		if(sourceFilter == null)
			sourceFilter = new ArrayList<Source>();
		return sourceFilter;
	}


	public void setSourceFilter(List<Source> sourceFilter) {
		this.sourceFilter = sourceFilter;
	}

	private Terminology getMetathesaurus(){
		if(metathesaurus == null)
			try {
				metathesaurus = new NobleCoderTerminology("NCI_Metathesaurus");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return metathesaurus;
	}
	
	
	public URI getDefaultNOS() {
		return defaultNOS;
	}


	public void setDefaultNOS(URI defaultNOS) {
		this.defaultNOS = defaultNOS;
	}


	/**
	 * is concept filtered out by sem
	 * @param c
	 * @param semanticTypeFilter
	 * @return
	 */
	private boolean isFilteredOut(Concept c){
		if(getSemanticTypeFilter().isEmpty())
			return false;
		for(SemanticType st: c.getSemanticTypes()){
			if(getSemanticTypeFilter().contains(st))
				return false;
		}
		return true;
	}
	
	
	
	public void generate() throws Exception{
		visited = new HashSet<String>();
		List<Concept> roots = (getRootFilter().isEmpty())?Arrays.asList(terminology.getRootConcepts()):getRootFilter();
		for(Concept c: roots){
			exportConcept(c,defaultNOS);
		}
	}
	
	
	/**
	 * export single concept as class
	 * @param c
	 * @param hashSet
	 * @param semanticTypeFilter
	 * @param root
	 */
	private void exportConcept(Concept c, URI defaultURL) throws Exception {
		// first make sure that it fits the filter
		if(c == null || isFilteredOut(c) || visited.contains(c.getCode())){
			return;
		}
		
		// find mapped URL
		URI url = getMatchingURL(c);
		if(url == null)
			url = defaultURL;
		
		// print concept information
		for(String s: getTerms(c)){
			StringBuffer tui= new StringBuffer();
			String sep = "";
			for(SemanticType st: c.getSemanticTypes()){
				tui.append(sep+st.getCode());
				sep = ",";
			}
			System.out.println(c.getCode()+I+s+I+tui+I+url);
		}
		
		// remember 
		visited.add(c.getCode());
		
		// now go into children
		for(Concept child: c.getChildrenConcepts()){
			exportConcept(child,url);
		}
	}
	
	private Set<String> getTerms(Concept c) throws TerminologyException{
		Set<String> terms = new LinkedHashSet<String>();
		terms.add(c.getName());
		Collections.addAll(terms,c.getSynonyms());
		// lookup in UMLS
		Concept cc = getMetathesaurus().lookupConcept(c.getCode());
		if(cc != null){
			Collections.addAll(terms,cc.getSynonyms());
		}
		return terms;
		
	}
	
	
	
	private URI getMatchingURL(Concept c) throws TerminologyException {
		Concept cc = ontologyTerminology.lookupConcept(c.getCode());
		// if not found try to match by other codes
		if(cc == null){
			for(Object code: c.getCodes().values()){
				cc =  ontologyTerminology.lookupConcept(""+code);
				if(cc != null)
					break;
			}
		}
		// try search
		if(cc == null){
			for(String s: c.getSynonyms()){
				Concept [] ans = ontologyTerminology.search(s);
				if(ans.length > 0 && ans[0].getMatchedTerm().equals(s)){
					cc = ans[0];
					break;
				}
				
			}
		}
		
		
		if(cc != null){
			String uri = cc.getCode();
			if(cc.getCode(Source.URI) != null ){
				uri = cc.getCode(Source.URI);
			}
			return URI.create(uri);
		}
		return null;
	}



	public static void main(String[] args) throws Exception {
		/*
		 
		// Anatomic Sites
		 	SNOMED_CT 
		 	(Body Part, Organ, or Organ Component )
		// Medications
			RxNORM
		// Procedure :
		    SNOMED_CT (Procedure branch)
			Semantic Types: 
			Laboratory Procedure
        	Diagnostic Procedure
        	Therapeutic or Preventive Procedure 
		*/
		
		
		Terminology term = new NobleCoderTerminology("NCI_Thesaurus");
		IOntology ont = OOntology.loadOntology("/home/tseytlin/Work/DeepPhe/data/ontology/nlpBreastCancer.owl");
		//generate dictionary
		DictionaryGenerator dg = new DictionaryGenerator(term,ont);
		dg.setDefaultNOS(URI.create("http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#BodySite_NOS"));
		dg.setSemanticTypeFilter(Arrays.asList(SemanticType.getSemanticTypes(new String [] {"Body Part, Organ, or Organ Component"})));
		dg.setRootFilter(Arrays.asList(term.lookupConcept("C13018")));
		dg.generate();
	}

}
