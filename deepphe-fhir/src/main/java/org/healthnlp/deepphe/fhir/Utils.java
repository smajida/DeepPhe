package org.healthnlp.deepphe.fhir;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.Time;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.LocationOfTextRelation;
import org.apache.ctakes.typesystem.type.relation.Relation;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.healthnlp.deepphe.util.FHIRParser;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.utils.NarrativeGenerator.ResourceWithReference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xml.XMLWriter;
import org.hl7.fhir.utilities.xml.XmlGenerator;

import edu.pitt.dbmi.nlp.noble.coder.model.Document;
import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;



public class Utils {
	public static final String DEFAULT_LANGUAGE = "English";
	public static final String SCHEMA_UMLS = "NCI Metathesaurus";
	public static final String SCHEMA_RXNORM = "RxNORM";
	public static final CodeableConcept CONDITION_CATEGORY_DIAGNOSIS = getCodeableConcept("Diagnosis","diagnosis","http://hl7.org/fhir/condition-category");
	public static final CodeableConcept CONDITION_CATEGORY_FINDING = getCodeableConcept("Finding","finding","http://hl7.org/fhir/condition-category");
	public static final CodeableConcept CONDITION_CATEGORY_SYMPTOM = getCodeableConcept("Symptom","symptom","http://hl7.org/fhir/condition-category");
	public static final CodeableConcept CONDITION_CATEGORY_COMPLAINT = getCodeableConcept("Complaint","complaint","http://hl7.org/fhir/condition-category");
	public static final String DOCUMENT_HEADER_REPORT_TYPE = "Record Type";
	public static final String DOCUMENT_HEADER_PRINCIPAL_DATE = "Principal Date";
	public static final String DOCUMENT_HEADER_PATIENT_NAME = "Patient Name";
	public static final String MENTION_URL = "http://hl7.org/fhir/mention"; 
	
	
	public static final String ELEMENT = "Element";
	public static final String COMPOSITION = "Composition";
	public static final String PATIENT = "Patient";
	public static final String DIAGNOSIS = "DiseaseDisorder";
	public static final String PROCEDURE = "ProcedureIntervention";
	public static final String OBSERVATION = "Observation";
	public static final String FINDING = "Finding";
	public static final String MEDICATION = "Medication_FHIR";
	public static final String ANATOMICAL_SITE = "AnatomicalSite";
	public static final String TUMOR_SIZE = "Tumor_Size";
	public static final String STAGE = "Generic_TNM_Finding";
	public static final String AGE = "Age";
	public static final String GENDER = "Gender";
	public static final String PHENOTYPIC_FACTOR = "PhenotypicFactor";
	public static final String GENOMIC_FACTOR = "GenomicFactor";
	public static final String TREATMENT_FACTOR = "TreatmentFactor";
	public static final String RELATED_FACTOR = "RelatedFactor";
	
	
	public static final String T_STAGE = "Generic_Primary_Tumor_TNM_Finding";
	public static final String M_STAGE = "Generic_Distant_Metastasis_TNM_Finding";
	public static final String N_STAGE = "Generic_Regional_Lymph_Nodes_TNM_Finding";
	
	public static final String STAGE_REGEX = "p?(T[X0-4a-z]{1,4})(N[X0-4a-z]{1,4})(M[X0-4a-z]{1,4})";
	
	public static final long MILLISECONDS_IN_YEAR = (long) 1000 * 60 * 60 * 24 * 365;
	
	private static Map<String,CodeableConcept> reportTypes;
	
	/**
	 * get document type
	 * @param type
	 * @return
	 */
	public static CodeableConcept getDocumentType(String type){
		if(reportTypes == null){
			reportTypes = new HashMap<String,CodeableConcept>();
			reportTypes.put("SP",getCodeableConcept("Pathology Report","C0807321",SCHEMA_UMLS));
			reportTypes.put("RAD",getCodeableConcept("Radiology Report","C1299496",SCHEMA_UMLS));
			reportTypes.put("DS",getCodeableConcept("Discharge Summary","C0743221",SCHEMA_UMLS));
			reportTypes.put("PGN",getCodeableConcept("Progress Note","C0747978",SCHEMA_UMLS));
			reportTypes.put("NOTE",getCodeableConcept("Progress Note","C0747978",SCHEMA_UMLS));
		}
		return reportTypes.get(type);
	}
	
	/**
	 * get codable concept that has a name and code from UMLS
	 * @param cui
	 * @param name
	 * @return
	 */
	public static CodeableConcept getCodeableConcept(String name,String cui,String scheme){
		CodeableConcept c = new CodeableConcept();
		c.setText(name);
		Coding cc = c.addCoding();
		cc.setCode(cui);
		cc.setDisplay(name);
		cc.setSystem(scheme);
		return c;
	}
	
	/**
	 * get FHIR date object from cTAKES time mention
	 * @param tm
	 * @return
	 */
	public static Date getDate(TimeMention tm){
		//Date dt = tm.getDate();
		Time t  = tm.getTime();
		org.apache.ctakes.typesystem.type.refsem.Date dt = tm.getDate();
		//dt.getYear();
		//TODO: //
		return new Date();
	}
	
	
	/**
	 * parse date from string
	 * @param text
	 * @return
	 */
	public static Date getDate(String text){
		return TextTools.parseDate(text);
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
				ccc.setDisplay(ia.getCoveredText());
				ccc.setSystem(c.getCodingScheme());
				cuis.add(c.getCode());
				
				// add codign for UMLS
				if(c instanceof UmlsConcept){
					String cui = ((UmlsConcept)c).getCui();
					if(!cuis.contains(cui)){
						Coding cccc = cc.addCoding();
						cccc.setCode(cui);
						cccc.setDisplay(((UmlsConcept)c).getPreferredText());
						cccc.setSystem(SCHEMA_UMLS);
						cuis.add(cui);
					}
				}
			}
		}
		
		// add coding for class
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
		
		}
		
		
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
		IClass cls = getConceptClass(ia);
		return cls != null?cls.getConcept().getName():ia.getCoveredText();
		/*String name = null;
		for(int i=0;i<ia.getOntologyConceptArr().size();i++){
			OntologyConcept c = ia.getOntologyConceptArr(i);
			if(c instanceof UmlsConcept){
				name = ((UmlsConcept)c).getPreferredText();
			}
			if(name != null)
				break;
		}
		return name == null?ia.getCoveredText():name;*/
	}
	
	/**
	 * get preferred concept code for a given codable concept
	 * @param c
	 * @return
	 */
	public static String getConceptCode(CodeableConcept c){
		for(Coding cc: c.getCoding()){
			if(SCHEMA_UMLS.equals(cc.getSystem())){
				return cc.getCode();
			}
		}
		// else get first code you encouner
		return c.getCoding().isEmpty()?c.getText():c.getCoding().get(0).getCode();
	}
	
	/**
	 * get preferred concept code for a given codable concept
	 * @param c
	 * @return
	 */
	public static String getConceptName(CodeableConcept c){
		String name = c.getText();
		for(Coding cc: c.getCoding()){
			if(SCHEMA_UMLS.equals(cc.getSystem())){
				if(cc.getDisplay() != null)
					name = cc.getDisplay();
			}
		}
		// else get first code you encouner
		return name;
	}
	
	
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c
	 * @return
	 */
	public static CodeableConcept getCodeableConcept(Mention c){
		CodeableConcept cc = new CodeableConcept();
		setCodeableConcept(cc, c);
		return cc;
	}
	
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c
	 * @return
	 */
	public static CodeableConcept getCodeableConcept(IClass c){
		CodeableConcept cc = new CodeableConcept();
		setCodeableConcept(cc, c);
		return cc;
	}
	
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c
	 * @return
	 */
	public static CodeableConcept setCodeableConcept(CodeableConcept cc,Mention mm){
		Concept c = mm.getConcept();
		cc.setText(c.getName());
		
		// add coding for class
		IClass cls = getConceptClass(mm);
		if(cls != null){
			Coding ccc = cc.addCoding();
			ccc.setCode(cls.getURI().toString());
			ccc.setDisplay(c.getName());
			ccc.setSystem(cls.getOntology().getURI().toString());
		}
		// add CUI
		String cui = getConceptCode(c);
		if(cui != null){
			Coding cc2 = cc.addCoding();
			cc2.setCode(cui);
			cc2.setDisplay(c.getName());
			cc2.setSystem(SCHEMA_UMLS);
		}
		
		// add RxNORM codes
		for(String rxcode: OntologyUtils.getRXNORM_Codes(c)){
			Coding c2 = cc.addCoding();
			c2.setCode(rxcode);
			c2.setDisplay(cls.getName());
			c2.setSystem(SCHEMA_RXNORM);
		}
		
		
		return cc;
	}
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c
	 * @return
	 */
	public static CodeableConcept setCodeableConcept(CodeableConcept cc,IClass cls){
		Concept c = cls.getConcept();
		cc.setText(c.getName());
		
		// add coding for class
		if(cls != null){
			Coding ccc = cc.addCoding();
			ccc.setCode(cls.getURI().toString());
			ccc.setDisplay(c.getName());
			ccc.setSystem(cls.getOntology().getURI().toString());
		}
	
		// add CUI
		String cui = getConceptCode(c);
		if(cui != null){
			Coding cc2 = cc.addCoding();
			cc2.setCode(cui);
			cc2.setDisplay(c.getName());
			cc2.setSystem(SCHEMA_UMLS);
		}
		
		// add RxNORM codes
		for(String rxcode: OntologyUtils.getRXNORM_Codes(c)){
			Coding c2 = cc.addCoding();
			c2.setCode(rxcode);
			c2.setDisplay(cls.getName());
			c2.setSystem(SCHEMA_RXNORM);
		}
		
		
		return cc;
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
	 * create a narrative from the text
	 * @param coveredText
	 * @return
	 */
	public static Narrative getNarrative(String text) {
		Narrative n = new Narrative();
		n.setStatus(NarrativeStatus.GENERATED);
		XhtmlNode xn = new XhtmlNode(NodeType.Element,"div");
		xn.addTag("p").addText(text);
		n.setDiv(xn);
		return n;
	}
	
	
	/**
	 * parse specially formatted document to extract header information
	 * @param text
	 * @return
	 */
	public static Map<String,String> getHeaderValues(String text){
		Map<String,String> map = new java.util.LinkedHashMap<String,String>();
		Pattern h = Pattern.compile("([\\w\\s]+)\\.+([\\w\\s]+)");
		Pattern p = Pattern.compile("={5,}(.*)={5,}",Pattern.DOTALL|Pattern.MULTILINE);
		Matcher m = p.matcher(text);
		if(m.find()){
			String header = m.group(1);
			for(String l: header.split("\n")){
				m = h.matcher(l);
				if(m.matches()){
					map.put(m.group(1).trim(),m.group(2).trim());
				}
			}
		}
		return map;
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
	
	public static String getIdentifier(Identifier id){
		if(id==null)
			return null;
		return id.getValue();
	}
	
	public static String getIdentifier(List<Identifier> ids){
		for(Identifier i: ids){
			return getIdentifier(i);
		}
		return null;
	}
	
	public static Identifier createIdentifier(String  ident){
		return createIdentifier(new Identifier(), ident);
	}
	
	public static Identifier createIdentifier(Identifier id, String ident){
		id.setId("id");
		id.setSystem("local");
		id.setValue(ident);
		return id;
	}
	
	public static Identifier createIdentifier(Object obj,Mention m){
		return createIdentifier(new Identifier(), obj,m);
	}
	public static Identifier createIdentifier(Object obj,IdentifiedAnnotation m){
		return createIdentifier(new Identifier(), obj,m);
	}

	public static Identifier createIdentifier(Identifier id, Object obj,Mention m){
		return createIdentifier(id,obj,m.getConcept());
	}
	public static Identifier createIdentifier(Identifier id, Object obj,IClass m){
		return createIdentifier(id,obj,m.getConcept());
	}
	
	public static Identifier createIdentifier(Identifier id, Object obj,Concept c){
		String dn = c.getName().replaceAll("\\W+","_");
		String ident = obj.getClass().getSimpleName().toUpperCase()+"_"+dn; //+"_"+m.getStartPosition()
		return createIdentifier(id, ident);
	}
	
	public static Identifier createIdentifier(Identifier id, Object obj,IdentifiedAnnotation m){
		String dn = getConceptName(m).replaceAll("\\W+","_");
		String ident = obj.getClass().getSimpleName().toUpperCase()+"_"+dn; //+"_"+m.getStartPosition()
		return createIdentifier(id, ident);
	}
	
	public static String getText(Narrative text) {
		if(text == null)
			return null;
		return getXhtmlText(text.getDiv());
	}
	
	private static String getXhtmlText(XhtmlNode n){
		if(n == null)
			return null;
		if(n.getContent() != null)
			return n.getContent();
		StringBuffer b = new StringBuffer();
		for(XhtmlNode xn : n.getChildNodes()){
			String c = getXhtmlText(xn);
			if(c != null)
				b.append(c.trim());
		}
		return b.toString();
	}
	
	
	public static Reference getResourceReference(Element model){
		return getResourceReference(new Reference(), model);
	}
	
	public static Reference getResourceReference(Reference r,Element model){
		if(r == null)
			r = new Reference();
		r.setDisplay(model.getDisplay());
		r.setReference(model.getIdentifierSimple());
		return r;
	}
	
	
	public static void saveFHIR(Resource r,String name, File dir) throws Exception{
		File file = new File(dir,name+".xml");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		//XmlWriter xml = new FHIRComposer();
		//xml.compose(new FileOutputStream(file),r, true);
		//XmlGenerator xml = new XmlGenerator();
		//xml.generate(r, file);
		XmlParser xml = new FHIRParser();
		xml.compose(new FileOutputStream(file),r,true);
		
	}
	
	
	public static Resource loadFHIR(File file) throws Exception{
		FileInputStream is = null;
		XmlParser xml = new XmlParser();
		try{
			is = new FileInputStream(file);
			return xml.parse(is);
		}finally{
			if(is !=null)
				is.close();
		}
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(IOntology ontology,  Concept c){
		String code = c.getCode();
		if(code.contains(":"))
			code = code.substring(code.indexOf(':')+1);
		return ontology.getClass(code);
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(IOntology ontology, CodeableConcept c){
		for(Coding coding : c.getCoding()){
			if((""+ontology.getURI()).equals(coding.getSystem())){
				return ontology.getClass(coding.getCode());
			}
		}
		return null;
	}
	
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(IOntology ontology,  Mention m){
		return getConceptClass(ontology, m.getConcept());
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static String getConceptCode(Concept c){
		String cui = null;
		for(Object cc : c.getCodes().values()){
			Matcher m = Pattern.compile("(CL?\\d{6,7})( .+)?").matcher(cc.toString());
			if(m.matches()){
				cui = m.group(1);
				break;
			}
		}
		return cui;
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(Mention m){
		return getConceptClass(ResourceFactory.getInstance().getOntology(), m);
	}
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(CodeableConcept m){
		return getConceptClass(ResourceFactory.getInstance().getOntology(), m);
	}
	
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c
	 * @return
	 */
	public static IClass getConceptClass(IdentifiedAnnotation m){
		// CancerSize doesn't have a CUI, but can be mapped
		if(m instanceof CancerSize){
			return ResourceFactory.getInstance().getOntology().getClass(TUMOR_SIZE);
		}
		
		String cui = getConceptCode(m);
		return cui != null?ResourceFactory.getInstance().getOntologyUtils().getClass(cui):null;
	}
	
	public static boolean isDiagnosis(IClass cls){
		return (cls != null && cls.hasSuperClass(cls.getOntology().getClass(DIAGNOSIS)));
	}
	public static boolean isProcedure(IClass cls){
		return (cls != null && cls.hasSuperClass(cls.getOntology().getClass(PROCEDURE)));
	}
	
	/**
	 * get report elements
	 * @return
	 */
	public static List getSubList(List entireList, Class cls) {
		List list = new ArrayList();
		for(Object e: entireList){
			if(cls.isInstance(e))
				list.add(cls.cast(e));
		}
		return list;
	}

	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 */
	public static List<Mention> getMentionsByType(Document doc, String type){
		return getMentionsByType(doc,type,true);
	}
	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 */
	public static List<Mention> getMentionsByType(Document doc, String type, boolean elementOnly){
		List<Mention> list = new ArrayList<Mention>();
		for(Mention m: doc.getMentions()){
			IClass cls = getConceptClass(m);
			if(cls != null && (cls.equals(cls.getOntology().getClass(type)) || cls.hasSuperClass(cls.getOntology().getClass(type)))){
				// skip non-elements
				if(elementOnly &&  !cls.hasSuperClass(cls.getOntology().getClass(ELEMENT)))
					continue;
				// make sure there is no negation 
				if(!m.isNegated()){
					list.add(m);
				}
			}
		}
		return filter(list);
	}
	
	/**
	 * filter a list of mentions to include the most specific
	 * @param list
	 * @return
	 */
	public static List<Mention> filter(List<Mention> list){
		if(list.isEmpty() || list.size() == 1)
			return list;
		for(ListIterator<Mention> it = list.listIterator();it.hasNext();){
			Mention m = it.next();
			if(hasMoreSpecific(m,list))
				it.remove();
		}
		return list;
	}
	
	/**
	 * does this mention has another mention that is more specific?
	 * @param m
	 * @param list
	 * @return
	 */
	
	private static boolean hasMoreSpecific(Mention mm, List<Mention> list) {
		IClass cc = getConceptClass(mm);
		for(Mention m: list){
			IClass c = getConceptClass(m);
			if(cc.hasSubClass(c))
				return true;
		}
		return false;
	}

	/**
	 * get nearest mention to a target mention 
	 * @param m
	 * @param doc
	 * @param type
	 * @return
	 */
	public static Mention getNearestMention(Mention target, Document doc, String type){
		List<Mention> mentions = getMentionsByType(doc, type);
		Mention nearest = null;
		for(Mention m: mentions){
			if(nearest == null)
				nearest = m;
			else if(Math.abs(target.getStartPosition()-m.getStartPosition()) < Math.abs(target.getStartPosition()-nearest.getStartPosition())){
				nearest = m;
			}
		}
		return nearest;
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
			if(getConceptClass(ia) != null) 
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
		for(Relation r: Utils.getRelationsByType(cas,new LocationOfTextRelation(cas).getType())){
			LocationOfTextRelation lr = (LocationOfTextRelation) r;
			if(lr.getArg1().getArgument().equals(an) && lr.getArg2().getArgument() instanceof AnatomicalSiteMention){
				return (AnatomicalSiteMention) lr.getArg2().getArgument();
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
			if(hasMoreSpecific(m,list) || hasIdenticalSpan(m,list))
				it.remove();
		}
		return list;
	}
	
	private static boolean hasIdenticalSpan(IdentifiedAnnotation m, List<IdentifiedAnnotation> list) {
		for(IdentifiedAnnotation mm: list){
			if(!mm.equals(m) && mm.getCoveredText().equals(m.getCoveredText()))
				return true;
		}
		return false;
	}
	
	public static Extension createExtension(String url, String value) {
		Extension e = new Extension();
		e.setUrl(url);
		e.setValue(new StringType(value));
		return e;
	}

	
	/**
	 * does this mention has another mention that is more specific?
	 * @param m
	 * @param list
	 * @return
	 */
	
	private static boolean hasMoreSpecific(IdentifiedAnnotation mm, List<IdentifiedAnnotation> list) {
		IClass cc = getConceptClass(mm);
		if(cc == null)
			return true;
		
		for(IdentifiedAnnotation m: list){
			IClass c = getConceptClass(m);
			if(c != null && cc.hasSubClass(c))
				return true;
		}
		return false;
	}

	public static Extension createMentionExtension(String text, int st, int end){
		return createExtension(MENTION_URL,text+" ["+st+":"+end+"]");
	}

	public static List<String> getMentionExtensions(DomainResource r){
		List<String> mentions = new ArrayList<String>();
		for(Extension e: r.getExtension()){
			if(MENTION_URL.equals(e.getUrl())){
				mentions.add(((StringType)e.getValue()).getValue());
			}
		}
		return mentions;
	}
	public static int [] getMentionSpan(String text){
		int [] s = new int [2];
		Matcher m = Pattern.compile(".* \\[(\\d+):(\\d+)\\]").matcher(text);
		if(m.matches()){
			s[0] = Integer.parseInt(m.group(1));
			s[1] = Integer.parseInt(m.group(2));
		}
		return s;
	}
	
	public static void main(String [] args) throws Exception{
		System.out.println(getHeaderValues(TextTools.getText(new FileInputStream(new File("/home/tseytlin/Work/DeepPhe/data/sample/docs/doc1.txt")))));
	}

	
	
}
