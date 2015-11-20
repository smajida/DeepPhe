package org.healthnlp.deepphe.uima.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.pitt.dbmi.nlp.noble.ontology.*;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;
import edu.pitt.dbmi.nlp.noble.util.XMLUtils;

public class DeePheTypeGenerator {
	public static final String TYPE_PREFIX = "org.healthnlp.deepphe.uima.types.";
	private IOntology ontology;
	
	public DeePheTypeGenerator(IOntology ont){
		ontology = ont;
	}
	
	

	private Element createTypeDescription(Document doc, IClass cls) {
		Element element = doc.createElement("typeDescription");
		
		Element e = doc.createElement("name");
		e.setTextContent(TYPE_PREFIX+cls.getName());
		element.appendChild(e);
	
		e = doc.createElement("description");
		for(String str: cls.getComments()){
			e.setTextContent(str); break;
		}
		element.appendChild(e);
		
		String superType = "uima.tcas.Annotation"; //uima.cas.TOP
		for(IClass s: cls.getDirectSuperClasses()){
			if(!s.equals(ontology.getRoot()))
				superType = TYPE_PREFIX+s.getName(); break;
		}
		e = doc.createElement("supertypeName");
		e.setTextContent(superType);
		element.appendChild(e);
		
		Element features = doc.createElement("features");
		// add TOP level attribute
		if(superType.equals("uima.tcas.Annotation"))
			features.appendChild(createFeatureDesciption(doc,"URI"));
		
		
		
		// add other features
		for(Element fd: createFeatureDesciptions(doc,cls)){
			features.appendChild(fd);
		}
		element.appendChild(features);

		return element;
	}

	
	
	private List<Element> createFeatureDesciptions(Document doc, IClass cls) {
		List<Element> list = new ArrayList<Element>();
		
		for(Object obj: cls.getEquivalentRestrictions()){
			if(obj instanceof IRestriction){
				IRestriction r = (IRestriction) obj;
				if(isOKRestriction(cls,r))
					list.add(createFeatureDesciption(doc,r));
			}
		}
		for(Object obj: cls.getDirectNecessaryRestrictions()){
			if(obj instanceof IRestriction){
				IRestriction r = (IRestriction) obj;
				if(isOKRestriction(cls,r))
					list.add(createFeatureDesciption(doc,r));
			}
		}
		
		return list;
	}


	private boolean isOKRestriction(IClass cls, IRestriction r) {
		for(Object o: cls.getNecessaryRestrictions()){
			if(o instanceof IRestriction){
				IRestriction rr = (IRestriction) o;
				if(!r.equals(rr) && rr.getProperty().equals(r.getProperty()))
					return false;
			}
		}
		return true;
	}



	private Element createFeatureDesciption(Document doc, String name){
		return createFeatureDesciption(doc, name,null,null,null);
	}
	
	private Element createFeatureDesciption(Document doc, String name, String desc, String range, String elementType) {
		Element element = doc.createElement("featureDescription");
		
		Element e = doc.createElement("name");
		e.setTextContent(name);
		element.appendChild(e);
	
		e = doc.createElement("description");
		if(desc != null)
			e.setTextContent(desc);
		element.appendChild(e);
		
		
		// for now just handle single slot
		if(range == null)
			range = "uima.cas.String";
		
		e = doc.createElement("rangeTypeName");
		e.setTextContent(range); 
		element.appendChild(e);
		
		if(elementType != null){
			e = doc.createElement("elementType");
			e.setTextContent(elementType); 
			element.appendChild(e);
		}
		return element;
	}
	
	
	private Element createFeatureDesciption(Document doc, IRestriction r) {
		Element element = doc.createElement("featureDescription");
		
		Element e = doc.createElement("name");
		e.setTextContent(r.getProperty().getName());
		element.appendChild(e);
	
		e = doc.createElement("description");
		for(String str: r.getProperty().getComments()){
			e.setTextContent(str); break;
		}
		element.appendChild(e);
		
		
		// for now just handle single slot
		String range = "uima.cas.String";
		String elementType = null;
		
		Object value = r.getParameter().getOperand();
		if(r.getParameter().size() == 1){
			// if type worthy class
			if(value instanceof IClass && isTypeWorthy((IClass)value)){
				range = "uima.cas.FSArray"; 
				elementType = TYPE_PREFIX+((IClass)value).getName(); 
			}else if( value instanceof Float){
				range = "uima.cas.Float"; 
			}else if( value instanceof Double){
				range = "uima.cas.Double"; 
			}else if( value instanceof Integer){
				range = "uima.cas.Integer"; 
			}else if( value instanceof Boolean){
				range = "uima.cas.Boolean"; 
			}
		}else{
			System.err.println(r);
		}
		e = doc.createElement("rangeTypeName");
		e.setTextContent(range); 
		element.appendChild(e);
		
		if(elementType != null){
			e = doc.createElement("elementType");
			e.setTextContent(elementType); 
			element.appendChild(e);
		}
		
		
		return element;
	}



	private List<Element> createTypeDescriptions(Document doc) {
		List<Element> list = new ArrayList<Element>();
	
		for(IClass cls: ontology.getRoot().getSubClasses()){
			if(isTypeWorthy(cls))
				list.add(createTypeDescription(doc,cls));
		}
		
		return list;
	}
	

	private boolean isTypeWorthy(IClass cls) {
		if(cls == null)
			return false;
		IClass annotation = ontology.getClass("Annotation");
		IClass attribute = ontology.getClass("Attribute");
		
		if(cls.equals(annotation) || cls.hasSuperClass(annotation))
			return true;
		if(cls.equals(attribute) || cls.hasDirectSuperClass(attribute))
			return true;
		if(cls.hasSuperClass(attribute)){
			// don't create type for attributes that don't have any useful "slots"
			if(!cls.getDirectNecessaryRestrictions().isEmpty() || !cls.getEquivalentRestrictions().isEmpty()){
				return true;
			}
			// don't include classes that happen to be concepts that are simply widely used
			//return cls.getPropertyValue(ontology.getProperty("code")) == null;
		}
		return false;
	}



	private Element createTypeSystemElement(Document doc){
		Element root = doc.createElement("typeSystemDescription");
		root.setAttribute("xmlns","http://uima.apache.org/resourceSpecifier");
		
		Element e = doc.createElement("name");
		e.setTextContent("TypeSystem");
		root.appendChild(e);
	
		e = doc.createElement("description");
		e.setTextContent("DeepPhe Phenotype TypeSystem");
		root.appendChild(e);
		
		e = doc.createElement("version");
		e.setTextContent("1.0");
		root.appendChild(e);
		
		root.appendChild(doc.createElement("vendor"));
		
		Element types = doc.createElement("types");
		
		for(Element td: createTypeDescriptions(doc)){
			types.appendChild(td);
		}
		
		root.appendChild(types);
		
		return root;
	}
	



	/**
	 * save output
	 * @param output
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws TransformerException 
	 * @throws FileNotFoundException 
	 */
	public void save(File output) throws ParserConfigurationException, FileNotFoundException, TransformerException, IOException{
		// initialize document and root
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().newDocument();
		
		// create DOM object
		doc.appendChild(createTypeSystemElement(doc));
		
		// write out XML
		XMLUtils.writeXML(doc, new FileOutputStream(output));
	}
	
	
	public static void main(String[] args) throws IOntologyException, FileNotFoundException, ParserConfigurationException, TransformerException, IOException {
		if(args.length == 0){
			System.err.println("Usage: java "+DeePheTypeGenerator.class.getName()+" <output TypeSystem.xml>");
			System.exit(1);
		}
		File file = new File(args[0]);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		IOntology ont = OOntology.loadOntology("http://ontologies.dbmi.pitt.edu/deepphe/cancer.owl");
		DeePheTypeGenerator dtg = new DeePheTypeGenerator(ont);
		dtg.save(file);
	}

}