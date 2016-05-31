package org.healthnlp.deepphe.util;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.fact.BodySiteFact;
import org.healthnlp.deepphe.fhir.fact.ConditionFact;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactFactory;
import org.healthnlp.deepphe.fhir.fact.ObservationFact;
import org.healthnlp.deepphe.fhir.fact.ProcedureFact;
import org.hl7.fhir.instance.model.CodeableConcept;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.ILogicExpression;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.IRestriction;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;

public class OntologyUtils {
	private static OntologyUtils instance; 
	private IOntology ontology;
	private Map<String,IClass> clsMap;
	
	
	private OntologyUtils(IOntology ont){
		ontology = ont;
	}
	
	
	public static OntologyUtils getInstance(){
		return instance;
	}
	
	public static OntologyUtils getInstance(IOntology ont){
		instance = new OntologyUtils(ont);
		return instance;
	}
	
	public static boolean hasInstance(){
		return instance != null;
	}
	
	
	
	public IOntology getOntology(){
		return ontology;
	}
	
	public void addAncestors(Fact fact){
		IClass cls = ontology.getClass(fact.getUri());
		if(cls == null){
			cls = ontology.getClass(fact.getName());
			fact.setUri(cls.getURI().toString());
		}
		if(cls != null){
			Queue<IClass> parents = new LinkedList<IClass>(); 
			parents.add(cls);
			while(!parents.isEmpty()){
				IClass c = parents.remove();
				for(IClass parent: c.getDirectSuperClasses()){
					parents.add(parent);
					// stop, if we have a parent that is defined in upper level ontology
					if(parent.getURI().toString().startsWith(FHIRConstants.SCHEMA_URL) || parent.getURI().toString().startsWith(FHIRConstants.CONTEXT_URL)){
						return;		
					}
					fact.addAncestor(parent.getName());	
				}
			}
		}
	}

	/**
	 * create a fact from codeable concept
	 * @param cc
	 * @return
	 */
	public Fact createFact(CodeableConcept cc){
		Fact fact = null;
		URI uri = FHIRUtils.getConceptURI(cc);
		if(uri != null){
			IClass cls = ontology.getClass(""+uri);
			if(cls != null){
				fact = new Fact();
				if(cls.hasSuperClass(ontology.getClass(FHIRConstants.OBSERVATION)))
					fact = new ObservationFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.CONDITION)))
					fact = new ConditionFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.BODY_SITE)))
					fact = new BodySiteFact();
				else if(cls.hasSuperClass(ontology.getClass(FHIRConstants.PROCEDURE)))
					fact = new ProcedureFact();
				fact = FactFactory.createFact(cc,fact);
				addAncestors(fact);
				
			}else{
				//TODO:
				System.err.println("ERROR: WTF no class; "+cc.getText()+" "+uri);
			}
		}
		return fact;
	}
	
	
	public boolean hasSuperClass(Element e, String entryClass){
		return hasSuperClass(""+e.getConceptURI(), entryClass);
	}
	
	public boolean hasSuperClass(Fact fact, String entryClass){
		return hasSuperClass(fact.getName(), entryClass);
	}
	
	
	public boolean hasSuperClass(String fact, String entryClass){
		if(ontology == null)
			throw new Error("Ontology is not defined");
		
		IClass cls = ontology.getClass(fact);
		IClass ent = ontology.getClass(entryClass);
		return (cls != null && ent != null && cls.hasSuperClass(ent));
	}

	public boolean hasSubClass(Element fact, String entryClass){
		return hasSuperClass(""+fact.getConceptURI(), entryClass);
	}
	
	public boolean hasSubClass(Fact fact, String entryClass){
		return hasSuperClass(fact.getName(), entryClass);
	}
	
	public boolean hasSubClass(String fact, String entryClass){
		if(ontology == null)
			throw new Error("Ontology is not defined");
		
		IClass cls = ontology.getClass(fact);
		IClass ent = ontology.getClass(entryClass);
		return (cls != null && ent != null && cls.hasSubClass(ent));
	}
	
	/**
	 * get class for a given concept code
	 * @param code
	 * @return
	 */
	public IClass getClass(String code){
		if(code != null && code.startsWith("http://"))
			return ontology.getClass(code);
		return getClassMap().get(code);
	}
	
	
	private Map<String, IClass> getClassMap() {
		if(clsMap == null){
			clsMap = new HashMap<String, IClass>();
			for(IClass c: ontology.getRoot().getSubClasses()){
				for(String code : getUMLS_Codes(c)){
					clsMap.put(code,c);
				}
			}
		}
		return clsMap;
	}

	/**
	 * save dictionary as BSV file
	 * @param root
	 * @param output
	 * @throws IOException 
	 */
	public void saveDictionary(File output) throws IOException{
		saveDictionary(ontology.getRoot(), output);
	}
	
	/**
	 * save dictionary as BSV file
	 * @param root
	 * @param output
	 * @throws IOException 
	 */
	public static void saveDictionary(IClass root, File output) throws IOException{
		// write out BSV file
		BufferedWriter w = new BufferedWriter(new FileWriter(output));
		w.write(convertCls(root));
		for(IClass c: root.getSubClasses()){
			w.write(convertCls(c));
		}
		w.close();
	}
	
	public static List<String> getUMLS_Codes(IClass cls){
		List<String> codes = new ArrayList<String>();
		// find UMLS CUIS
		for(Object cc : cls.getConcept().getCodes().values()){
			Matcher m = Pattern.compile("(CL?\\d{6,7})( .+)?").matcher(cc.toString());
			if(m.matches()){
				codes.add(m.group(1));
			}
		}
		return codes;
	}
	
	public static List<String> getRXNORM_Codes(IClass cls){
		return getRXNORM_Codes(cls.getConcept());
	}
	
	public static List<String> getRXNORM_Codes(Concept cls){
		List<String> codes = new ArrayList<String>();
		// find UMLS CUIS
		for(Object cc : cls.getCodes().values()){
			Matcher m = Pattern.compile("(\\d+) \\[RXNORM\\]").matcher(cc.toString());
			if(m.matches()){
				codes.add(m.group(1));
			}
		}
		return codes;
	}
	
	
	
	
	public static String getCode(IClass cls){
		List<String> codes = getUMLS_Codes(cls);
		return codes == null || codes.isEmpty()?null:codes.get(0);
	}
	
	/**
	 * convert Class to BSV entry
	 * @param root
	 * @return
	 */
	private static String convertCls(IClass cls) {
		Concept c = cls.getConcept();
		// find UMLS CUIS
		String cui = getCode(cls);
		if(cui != null){
			String tui = "";
			if(c.getSemanticTypes().length > 0)
				tui = c.getSemanticTypes()[0].getCode();
			StringBuffer b = new StringBuffer();
			for(String s: c.getSynonyms()){
				b.append(cui+"|"+tui+"|"+s+"\n");
			}
			return b.toString();
		}else{
			System.out.println("No CUI in cls "+cls.getName());
		}
		return "";
	}


	private static void printClass(IClass c,String s){
		System.out.println(s+c.getName());
		Concept cc = c.getConcept();
		cc.getCode();
		cc.getCodes();
		cc.getSynonyms();
		cc.getDefinition();
		
		for(IClass ch: c.getDirectSubClasses()){
			printClass(ch,s+"  ");
		}
	}
	
	

	public static void main(String [] args) throws Exception{
		//File f = new File("/home/tseytlin/Work/DeepPhe/ontologies/breastCancer.owl");
		File of = new File("/home/tseytlin/breastCancer.bsv"); //Work/DeepPhe/ontologies/
		
		IOntology ont = OOntology.loadOntology("http://ontologies.dbmi.pitt.edu/deepphe/breastCancer.owl");
		printClass(ont.getClass("Element"),"");
		
		
		//OntologyUtils ou = new OntologyUtils(ont);
		//saveDictionary(ont.getClass("Element"),of);
		System.out.println("done");
		//System.out.println(ou.getClass("C0441960"));
	}
}
