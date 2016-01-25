package org.healthnlp.deepphe.uima.fhir;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.typesystem.type.refsem.*;
import org.apache.ctakes.typesystem.type.relation.*;
import org.apache.ctakes.typesystem.type.textsem.*;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;

public class cTAKESUtils {
	/**
	 * get FHIR date object from cTAKES time mention
	 * @param tm
	 * @return
	 */
	public static Date getDate(TimeMention tm){
		Time t  = tm.getTime();
		org.apache.ctakes.typesystem.type.refsem.Date dt = tm.getDate();
		String yr = dt.getYear();
		String dy = dt.getDay();
		String mo = dt.getMonth();
		
		String time  = "";
		if(tm.getTime() != null)
			time = tm.getTime().getNormalizedForm();
		
		String dateTime = (mo+"/"+dy+"/"+yr+" "+time).trim();
		
		return  TextTools.parseDate(dateTime);
	}
	
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c
	 * @return
	 */
	public static CodeableConcept getCodeableConcept(IdentifiedAnnotation ia){
		return setCodeableConcept(new CodeableConcept(),ia);
	}
	
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c
	 * @return
	 */
	public static CodeableConcept setCodeableConcept(CodeableConcept cc,IdentifiedAnnotation ia){
		cc.setText(ia.getCoveredText());
		
		// go over mapped concepts (merge them into multiple coding systems)
		if(ia.getOntologyConceptArr() != null){
			List<String> cuis = new ArrayList<String>();
			for(int i=0;i<ia.getOntologyConceptArr().size();i++){
				OntologyConcept c = ia.getOntologyConceptArr(i);
				
				// add coding for this concept
				Coding ccc = cc.addCoding();
				ccc.setCode(c.getCode());
				ccc.setDisplay(getConceptName(ia));
				ccc.setSystem(c.getCodingScheme());
				cuis.add(c.getCode());
				
				// add codign for UMLS
				if(c instanceof UmlsConcept){
					String cui = ((UmlsConcept)c).getCui();
					if(!cuis.contains(cui)){
						Coding cccc = cc.addCoding();
						cccc.setCode(cui);
						cccc.setDisplay(((UmlsConcept)c).getPreferredText());
						cccc.setSystem(FHIRUtils.SCHEMA_UMLS);
						cuis.add(cui);
					}
				}
			}
		}
		
		// add coding for class
		/*
		IClass cls = getConceptClass(ia);
		if(cls != null){
			// add class URI
			Coding ccc = cc.addCoding();
			ccc.setCode(cls.getURI().toString());
			ccc.setDisplay(cls.getName());
			ccc.setSystem(cls.getOntology().getURI().toString());
			cc.setText(cls.getConcept().getName());
		
			// add RxNORM codes
			for(String rxcode: OntologyUtils.getRXNORM_Codes(cls)){
				Coding c2 = cc.addCoding();
				c2.setCode(rxcode);
				c2.setDisplay(cls.getName());
				c2.setSystem(SCHEMA_RXNORM);
			}
		
		}*/
		
		
		return cc;
	}
	
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static String getConceptCode(IdentifiedAnnotation ia){
		String cui = null;
		for(int i=0;i<ia.getOntologyConceptArr().size();i++){
			OntologyConcept c = ia.getOntologyConceptArr(i);
			if(c instanceof UmlsConcept){
				cui = ((UmlsConcept)c).getCui();
			}else{
				cui = c.getCode();
			}
			if(cui != null && cui.matches("CL?\\d{6,7}"))
				break;
		}
		return cui;
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static String getConceptName(IdentifiedAnnotation ia){
		if(ia == null)
			return null;
		//IClass cls = getConceptClass(ia);
		//return cls != null?cls.getConcept().getName():ia.getCoveredText();
		String name = null;
		if(ia.getOntologyConceptArr() != null){
			for(int i=0;i<ia.getOntologyConceptArr().size();i++){
				OntologyConcept c = ia.getOntologyConceptArr(i);
				if(c instanceof UmlsConcept &&  "URI".equals(c.getCodingScheme())){
					//name = ((UmlsConcept)c).getPreferredText();
					name = FHIRUtils.getConceptName(URI.create(c.getCode()));
				}
				if(name != null)
					break;
			}
		}
		return name == null?ia.getCoveredText():name;
	}
	
	
	/**
	 * get related item from cTAKES
	 * @param source
	 * @param relation
	 * @return
	 */
	public static IdentifiedAnnotation getRelatedItem(IdentifiedAnnotation source , Relation relation){
		if(relation != null ){
			if(relation instanceof BinaryTextRelation){
				BinaryTextRelation r = (BinaryTextRelation) relation;
				if(r.getArg1().getArgument().equals(source))
					return (IdentifiedAnnotation) r.getArg2().getArgument();
				else
					return (IdentifiedAnnotation) r.getArg1().getArgument();
			}
		}
		return null;
	}
	
	/**
	 * get document text for a given annotated JCas
	 * @param cas
	 * @return
	 */
	public static String getDocumentText(JCas cas){
		Iterator<Annotation> it = cas.getAnnotationIndex(DocumentAnnotation.type).iterator();
		if(it.hasNext())
			return it.next().getCoveredText();
		return null;
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(IOntology ont, IdentifiedAnnotation m){
		// CancerSize doesn't have a CUI, but can be mapped
		/*if(m instanceof CancerSize){
			return ont.getClass(TUMOR_SIZE);
		}*/
		
		String cui = getConceptCode(m);
		return cui != null?ont.getClass(cui):null;
	}
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 */
	public static List<IdentifiedAnnotation> getAnnotationsByType(JCas cas, int type){
		List<IdentifiedAnnotation> list = new ArrayList<IdentifiedAnnotation>();
		Iterator<Annotation> it = cas.getAnnotationIndex(type).iterator();
		while(it.hasNext()){
			IdentifiedAnnotation ia = (IdentifiedAnnotation) it.next();
			// don't add stuff that doesn't have a Class or ontology array
			//if(getConceptClass(ia) != null) 
			list.add(ia);
		}
		return filterAnnotations(list);
	}
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 */
	public static List<IdentifiedAnnotation> getAnnotationsByType(JCas cas, URI type){
		List<IdentifiedAnnotation> annotations = new ArrayList<IdentifiedAnnotation>();
		for(IdentifiedAnnotation a: OwlOntologyConceptUtil.getAnnotationsByUriBranch(cas,type.toString())){
			annotations.add(a);
		}
		return annotations;
	}
	
	
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 */
	public static List<Relation> getRelationsByType(JCas cas, Type type){
		List<Relation> list = new ArrayList<Relation>();
		Iterator<FeatureStructure> it = cas.getFSIndexRepository().getAllIndexedFS(type);
		while(it.hasNext()){
			list.add((Relation)it.next());
		}
		return list;
	}
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 */
	public static List<Annotation> getRelatedAnnotationsByType(IdentifiedAnnotation an, Class classType){
		JCas cas = null;
		try {
			cas = an.getCAS().getJCas();
		} catch (CASException e1) {
			e1.printStackTrace();
		}
		Type type = null;
		try {
			type = (Type) classType.getMethod("getType").invoke(classType.getDeclaredConstructor(JCas.class).newInstance(cas));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Annotation> list = new ArrayList<Annotation>();
		Iterator<FeatureStructure> it = cas.getFSIndexRepository().getAllIndexedFS(type);
		while(it.hasNext()){
			BinaryTextRelation br = (BinaryTextRelation)it.next();
			if(br.getArg1().getArgument().getCoveredText().equals(an)){ 
				list.add(br.getArg2().getArgument());
			}else if (br.getArg2().getArgument().equals(an)){
				list.add(br.getArg1().getArgument());
			}
		}
		return list;
	}
	
	/**
	 * get anatomic location of an annotation
	 * @param an
	 * @return
	 */
	public static AnatomicalSiteMention getAnatimicLocation(IdentifiedAnnotation an){
		JCas cas = null;
		try {
			cas = an.getCAS().getJCas();
		} catch (CASException e) {
			e.printStackTrace();
		}
		for(Relation r: getRelationsByType(cas,new LocationOfTextRelation(cas).getType())){
			LocationOfTextRelation lr = (LocationOfTextRelation) r;
			if(equals(lr.getArg1(),an) && lr.getArg2().getArgument() instanceof AnatomicalSiteMention){
				return (AnatomicalSiteMention) lr.getArg2().getArgument();
			}
		}
		return null;
	}
	/*
	 * is relation argument equals to identified annotation?
	 */
	private static boolean equals(RelationArgument a, Annotation b){
		if(a.getArgument().equals(b))
			return true;
		return (a.getArgument().getCoveredText().equals(b.getCoveredText()) && a.getArgument().getBegin() == b.getBegin());
	}
	
	
	/**
	 * get anatomic location of an annotation
	 * @param an
	 * @return
	 */
	public static IdentifiedAnnotation getDegreeOf(IdentifiedAnnotation an){
		JCas cas = null;
		try {
			cas = an.getCAS().getJCas();
		} catch (CASException e) {
			e.printStackTrace();
		}
		for(Relation r: getRelationsByType(cas,new DegreeOfTextRelation(cas).getType())){
			DegreeOfTextRelation lr = (DegreeOfTextRelation) r;
			if(lr.getArg1().getArgument().equals(an) && lr.getArg2().getArgument() instanceof IdentifiedAnnotation){
				return (IdentifiedAnnotation) lr.getArg2().getArgument();
			}
		}
		return null;
	}
	
	
	
	private static List<IdentifiedAnnotation> filterAnnotations(List<IdentifiedAnnotation> list) {
		if(list.isEmpty() || list.size() == 1)
			return list;
		for(ListIterator<IdentifiedAnnotation> it = list.listIterator();it.hasNext();){
			IdentifiedAnnotation m = it.next();
			// keep annotation that might be part of relationship
			if(!getRelatedAnnotationsByType(m, BinaryTextRelation.class).isEmpty())
				continue;
			
			// filter out if something more specific exists
			//if(hasMoreSpecific(m,list) || hasIdenticalSpan(m,list))
			//	it.remove();
		}
		return list;
	}
	
	/**
	 * get size measurement of an identified annotation, if such exists
	 * @param dm
	 * @return
	 */
	public static SizeMeasurement getSizeMeasurement(IdentifiedAnnotation dm){
		// if cancer size, then use their value
		if(dm instanceof CancerSize){
			//ob.setCode(FHIRUtils.getCodeableConcept(FHIRConstants.TUMOR_SIZE_URI));
			FSArray arr = ((CancerSize)dm).getMeasurements();
			if(arr != null){
				for(int i=0;i<arr.size();){
					return (SizeMeasurement) arr.get(i);
				}
			}
		}
		return null;
	}
	
	private static boolean hasIdenticalSpan(IdentifiedAnnotation m, List<IdentifiedAnnotation> list) {
		for(IdentifiedAnnotation mm: list){
			if(!mm.equals(m) && mm.getCoveredText().equals(m.getCoveredText()))
				return true;
		}
		return false;
	}
	
}
