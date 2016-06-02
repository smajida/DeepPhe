package org.healthnlp.deepphe.util;


import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;

import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class FHIRUtils {
	public static final String DEFAULT_LANGUAGE = "English";
	public static final String SCHEMA_UMLS = "NCI Metathesaurus";
	public static final String SCHEMA_RXNORM = "RxNORM";
	public static final String SCHEMA_OWL = "OWL_URI";
	public static final String SCHEMA_REFERENCE = "FHIR_ID";
	public static final CodeableConcept CONDITION_CATEGORY_DIAGNOSIS = getCodeableConcept("Disease","diagnosis","http://hl7.org/fhir/condition-category");
	public static final CodeableConcept CONDITION_CATEGORY_FINDING = getCodeableConcept("Finding","finding","http://hl7.org/fhir/condition-category");
	public static final CodeableConcept CONDITION_CATEGORY_SYMPTOM = getCodeableConcept("Symptom","symptom","http://hl7.org/fhir/condition-category");
	public static final CodeableConcept CONDITION_CATEGORY_COMPLAINT = getCodeableConcept("Complaint","complaint","http://hl7.org/fhir/condition-category");
	public static final String DOCUMENT_HEADER_REPORT_TYPE = "Record Type";
	public static final String DOCUMENT_HEADER_PRINCIPAL_DATE = "Principal Date";
	public static final String DOCUMENT_HEADER_PATIENT_NAME = "Patient Name";
	public static final String MENTION_URL = "http://hl7.org/fhir/mention";
	public static final String STAGE_URL = "http://hl7.org/fhir/stage";
	public static final String TNM_MODIFIER_URL = "http://hl7.org/fhir/TNM_modifier";
	
	public static final String LANGUAGE_ASPECT_MODALITY_URL = "http://hl7.org/fhir/modality";
	public static final String LANGUAGE_ASPECT_DOC_TIME_REL_URL = "http://hl7.org/fhir/doc_time_rel"; 
	public static final String LANGUAGE_ASPECT_NEGATED_URL = "http://hl7.org/fhir/negation";
	public static final String LANGUAGE_ASPECT_UNCERTAIN_URL = "http://hl7.org/fhir/uncertainty";
	public static final String LANGUAGE_ASPECT_CONDITIONAL_URL = "http://hl7.org/fhir/conditionality";
	public static final String LANGUAGE_ASPECT_INTERMITTENT_URL = "http://hl7.org/fhir/intermittency";
	public static final String LANGUAGE_ASPECT_HYPOTHETICAL_URL = "http://hl7.org/fhir/hypothetical";
	public static final String LANGUAGE_ASPECT_PERMENENT_URL = "http://hl7.org/fhir/permanency";
	public static final String LANGUAGE_ASPECT_HISTORICAL_URL = "http://hl7.org/fhir/historical";
	
	
	public static final String CANCER_URL = "http://ontologies.dbmi.pitt.edu/deepphe/cancer.owl";

	public static final String INTERPRETATION_POSITIVE = "Positive";
	public static final String INTERPRETATION_NEGATIVE = "Negative";
	
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
	
	
	/*public static final String T_STAGE = "Generic_Primary_Tumor_TNM_Finding";
	public static final String M_STAGE = "Generic_Distant_Metastasis_TNM_Finding";
	public static final String N_STAGE = "Generic_Regional_Lymph_Nodes_TNM_Finding";*/
	
	public static final String STAGE_REGEX = "p?(T[X0-4a-z]{1,4})(N[X0-4a-z]{1,4})(M[X0-4a-z]{1,4})";
	
	public static final long MILLISECONDS_IN_YEAR = (long) 1000 * 60 * 60 * 24 * 365;


	private static Map<String,CodeableConcept> reportTypes;
	
	/**
	 * get document type
	 * @param type -
	 * @return -
	 */
	public static CodeableConcept getDocumentType(String type){
		if(reportTypes == null){
			reportTypes = new HashMap<String,CodeableConcept>();
			reportTypes.put("SP",getCodeableConcept("Pathology Report","C0807321",SCHEMA_UMLS));
			reportTypes.put("RAD",getCodeableConcept("Radiology Report","C1299496",SCHEMA_UMLS));
			reportTypes.put("DS",getCodeableConcept("Discharge Summary","C0743221",SCHEMA_UMLS));
			reportTypes.put("PGN",getCodeableConcept("Progress Note","C0747978",SCHEMA_UMLS));
			reportTypes.put("NOTE",getCodeableConcept("Progress Note","C0747978",SCHEMA_UMLS));
			
			/*
			reportTypes.put("SP",getCodeableConcept("Pathology Report",""+FHIRConstants.PATHOLOGY_REPORT_URI,SCHEMA_OWL)); //"C0807321",SCHEMA_UMLS
			reportTypes.put("RAD",getCodeableConcept("Radiology Report",""+FHIRConstants.RADIOLOGY_REPORT_URI,SCHEMA_OWL)); //"C1299496",SCHEMA_UMLS
			reportTypes.put("DS",getCodeableConcept("Discharge Summary",""+FHIRConstants.DISCHARGE_SUMMARY_URI,SCHEMA_OWL)); //"C0743221",SCHEMA_UMLS
			reportTypes.put("PGN",getCodeableConcept("Progress Note",""+FHIRConstants.PROGRESS_NOTE_URI,SCHEMA_OWL)); //"C0747978",SCHEMA_UMLS
			reportTypes.put("NOTE",getCodeableConcept("Progress Note",""+FHIRConstants.PROGRESS_NOTE_URI,SCHEMA_OWL)); //"C0747978",SCHEMA_UMLS
			*/
			
			reportTypes.put("Pathology Report",reportTypes.get("SP"));
			reportTypes.put("Radiology Report",reportTypes.get("RAD"));
			reportTypes.put("Discharge Summary",reportTypes.get("DS"));
			reportTypes.put("Progress Note",reportTypes.get("PGN"));
			
		}
		return reportTypes.get(type);
	}
	
	/**
	 * get codable concept that has a name and code from UMLS
	 * @param cui -
	 * @param name -
	 * @return -
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
	
	public static CodeableConcept getCodeableConcept(URI uri){
		if(uri == null)
			return null;
		return getCodeableConcept(getConceptName(uri),uri.toString(),SCHEMA_OWL);
	}
	
	
	public static CodeableConcept getCodeableConcept(AdministrativeGender gender){
		if(AdministrativeGender.MALE.equals(gender))
			return getCodeableConcept(FHIRConstants.MALE_URI);
		else if(AdministrativeGender.FEMALE.equals(gender))
			return getCodeableConcept(FHIRConstants.FEMALE_URI);
		return null;
	}

	
	/**
	 * parse date from string
	 * @param text -
	 * @return -
	 */
	public static Date getDate(String text){
		return TextTools.parseDate(text);
	}
	
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param u -
	 * @return -
	 */
	public static String getConceptName(URI u){
		if(u == null)
			return null;
		
		try {
			return u.toURL().getRef(); //.replaceAll("_"," ")
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return u.toString();
	}
	/**
	 * get preferred concept code for a given codable concept
	 * @param c -
	 * @return -
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
	 * @param c -
	 * @return -
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
	 * @param c -
	 * @return -
	 *
	public static CodeableConcept getCodeableConcept(Mention c){
		CodeableConcept cc = new CodeableConcept();
		setCodeableConcept(cc, c);
		return cc;
	}
	*/
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param c -
	 * @return -
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
	 *
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
	*/
	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param cc -
	 * @param cls -
	 * @return -
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
	 * create a narrative from the text
	 * @param text -
	 * @return -
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
	 * @param text -
	 * @return -
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
	
	/**
	 * create a string resource identifier for a given element
	 * @param e - element
	 * @return -
	 */
	public static String createResourceIdentifier(Element e){
		int hash = FHIRUtils.getMentionExtensions((DomainResource)e.getResource()).hashCode();  
		return e.getClass().getSimpleName().toUpperCase()+"_"+e.getDisplayText().replaceAll("\\W+","_")+"_"+Math.abs(hash);
	}
	
	
	public static Identifier createIdentifier(Object obj,Mention m){
		return createIdentifier(new Identifier(), obj,m);
	}
	public static Identifier createIdentifier(Element el){
		return createIdentifier(new Identifier(),el);
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
	
	/*
	public static Identifier createIdentifier(Identifier id, Object obj,IdentifiedAnnotation m){
		String dn = getConceptName(m).replaceAll("\\W+","_");
		String ident = obj.getClass().getSimpleName().toUpperCase()+"_"+dn; //+"_"+m.getStartPosition()
		return createIdentifier(id, ident);
	}
	*/
	public static Identifier createIdentifier(Identifier id, Element e){
		return createIdentifier(id, createResourceIdentifier(e));
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
	
	public static void addResourceReference(CodeableConcept cc, Element el) {
		Coding coding = cc.addCoding();
		coding.setCode(el.getResourceIdentifier());
		coding.setDisplay(el.getDisplayText());
		coding.setSystem(SCHEMA_REFERENCE);
	}
	
	public static void addResourceReference(CodeableConcept cc, String name, String id) {
		Coding coding = cc.addCoding();
		coding.setCode(id);
		coding.setDisplay(name);
		coding.setSystem(SCHEMA_REFERENCE);
	}

	
	
	public static Reference getResourceReference(Reference r,Element model){
		if(r == null)
			r = new Reference();
		r.setDisplay(model.getDisplayText());
		r.setReference(model.getResourceIdentifier());
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
		
		FileOutputStream ios = new FileOutputStream(file);
		XmlParser xml = new FHIRParser();
		xml.compose(ios,r,true);
		ios.close();
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
	 * @param ontology -
	 * @param c -
	 * @return -
	 */
	public static IClass getConceptClass(IOntology ontology,  Concept c){
		String code = c.getCode();
		if(code.contains(":"))
			code = code.substring(code.indexOf(':')+1);
		return ontology.getClass(code);
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param ontology -
	 * @param c -
	 * @return -
	 */
	public static IClass getConceptClass(IOntology ontology, CodeableConcept c){
		for(Coding coding : c.getCoding()){
			if((""+ontology.getURI()).equals(coding.getSystem())){
				return ontology.getClass(coding.getCode());
			}
		}
		return null;
	}
	
	public static URI getConceptURI(CodeableConcept c){
		if(c == null)
			return null;
		for(Coding coding : c.getCoding()){
			if(coding.getCode() != null && coding.getCode().startsWith("http://")){
				return URI.create(coding.getCode());
			}
		}
		return null;
	}
	
	public static String getResourceIdentifer(CodeableConcept c){
		if(c != null){
			for(Coding coding : c.getCoding()){
				if(coding.getCode() != null && SCHEMA_REFERENCE.equals(coding.getSystem())){
					return coding.getCode();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param ontology -
	 * @param m -
	 * @return -
	 */
	public static IClass getConceptClass(IOntology ontology,  Mention m){
		return getConceptClass(ontology, m.getConcept());
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param c -
	 * @return -
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
	
	
	
	
	public static boolean isDiagnosis(IClass cls){
		return (cls != null && cls.hasSuperClass(cls.getOntology().getClass(DIAGNOSIS)));
	}
	public static boolean isProcedure(IClass cls){
		return (cls != null && cls.hasSuperClass(cls.getOntology().getClass(PROCEDURE)));
	}
	
	/**
	 * get report elements
	 * @param entireList -
	 * @param cls -
	 * @return -
	 */
	public static List getSubList(Collection entireList, Class cls) {
		// TODO can use stream
//	public static <T extends TT, TT extends Object> List<T> getSubList(Collection<TT> entireList, Class<T> cls) {
//		return entireList.stream()
// 			.filter( cls::isInstance )
// 			.map( cls::cast )
// 			.collect( Collectors.toList() );
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
	 *
	public static List<Mention> getMentionsByType(Document doc, String type){
		return getMentionsByType(doc,type,true);
	}
	*/
	/**
	 * get a set of concept by type from the annotated document
	 * @param doc
	 * @param type
	 * @return
	 *
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
	*/
	
	/**
	 * filter a list of mentions to include the most specific
	 * @param list
	 * @return
	 *
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
	*/
	/**
	 * does this mention has another mention that is more specific?
	 * @param m
	 * @param list
	 * @return
	 *
	
	private static boolean hasMoreSpecific(Mention mm, List<Mention> list) {
		IClass cc = getConceptClass(mm);
		for(Mention m: list){
			IClass c = getConceptClass(m);
			if(cc.hasSubClass(c))
				return true;
		}
		return false;
	}
	*/

	/**
	 * get nearest mention to a target mention 
	 //	 * @param target -
	 //	 * @param doc -
	 //	 * @param type -
	 * @return -
	 *
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
	*/

	
	public static Extension createExtension(String url, String value) {
		Extension e = new Extension();
		e.setUrl(url);
		e.setValue(new StringType(value));
		return e;
	}

	
	/**
	 * does this mention has another mention that is more specific?
	 //	 * @param mm -
	 //	 * @param list -
	 * @return -
	 
	
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
	*/

	public static Extension createMentionExtension(String text, int st, int end){
		return createExtension(MENTION_URL,text+" ["+st+":"+end+"]");
	}

	public static Extension createMentionExtension(String text){
		return createExtension(MENTION_URL,text);
	}

	public static Extension createDocTimeRelExtension( String text ) {
		return createExtension( LANGUAGE_ASPECT_DOC_TIME_REL_URL, text );
	}

	public static Extension createModalityExtension( String text ) {
		return createExtension( LANGUAGE_ASPECT_MODALITY_URL, text );
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
	
	public static Map<String,String> getProperties(DomainResource r){
		Map<String,String> p = new LinkedHashMap<String,String>();
		for(Extension e: r.getExtension()){
			if(!MENTION_URL.equals(e.getUrl())){
				p.put(e.getUrl(), ((StringType)e.getValue()).getValue());
			}
		}
		return p;
	}
	
	
	public static List<String> getProperty(DomainResource r,String URL){
		List<String> mentions = new ArrayList<String>();
		for(Extension e: r.getExtension()){
			if(URL.equals(e.getUrl())){
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
	public static String getMentionText(String text){
		Matcher m = Pattern.compile("(.*) \\[(\\d+):(\\d+)\\]").matcher(text);
		if(m.matches()){
			return m.group(1);
		}
		return text;
	}
	
	public static String getOntologyURL(String uri){
		int x = uri.lastIndexOf("#");
		if(x > -1)
			return uri.substring(0,x);
		x = uri.lastIndexOf("/");
		if(x > -1)
			return uri.substring(0,x);
		return uri;
	}
	
	public static boolean contains(List<CodeableConcept> list, CodeableConcept cc){
		URI uu = getConceptURI(cc);
		if(uu != null){
			for(CodeableConcept c: list){
				URI u = getConceptURI(c);
				if(u != null && uu.equals(u))
					return true;
			}
		}
		return false;
	}

	public static boolean contains(List<Fact> list, Fact cc){
		if(list == null)
			return false;
		
		for(Fact c: list){
			if(cc.getUri().equals(c.getUri()))
				return true;
		}
		return false;
	}
	
	public static boolean hasConceptURI(CodeableConcept cc){
		return getConceptURI(cc) != null;
		
	}
	
	public static boolean isEmpty(CodeableConcept cc){
		return cc == null || cc.getCoding() == null || cc.getCoding().isEmpty();
	}
	
	
	public static void main(String [] args) throws Exception{
		//System.out.println(getHeaderValues(TextTools.getText(new FileInputStream(new File("/home/tseytlin/Work/DeepPhe/data/sample/docs/doc1.txt")))));
		//System.out.println(getOntologyURL("http://ontologies.dbmi.pitt.edu/deepphe/cancer.owl#ClinicalPhenotypicComponent"));
	}

	public static String getPropertyDisplayLabel(String str) {
		if(str == null)
			return "Unknown";
		
		if(str.startsWith("has"))
			str = str.substring(3);
		// insert space into camel back
		return str.replaceAll("([a-z])([A-Z])","$1 $2");
	}

	

	public static boolean equals(CodeableConcept concept,URI u){
		return u.equals(getConceptURI(concept));
	}
	

	public static URI getPathologicalTNM_URI(String name) {
		if(OntologyUtils.hasInstance()){
			Matcher m = Pattern.compile(".*([pc]?[TNM]\\w{1,4})_(Stage|TNM)_Finding").matcher(name);
			if(m.matches()){
				IClass cls = OntologyUtils.getInstance().getOntology().getClass("Breast_Cancer_p"+m.group(1)+"_TNM_Finding");
				if(cls != null)
					return cls.getURI();
			}
		}
		return null;
	}

	
	public static URI getClinicalTNM_URI(String name) {
		if(OntologyUtils.hasInstance()){
			Matcher m = Pattern.compile(".*([pc]?[TNM]\\w{1,4})_(Stage|TNM)_Finding").matcher(name);
			if(m.matches()){
				IClass cls = OntologyUtils.getInstance().getOntology().getClass("Breast_Cancer_c"+m.group(1)+"_TNM_Finding");
				if(cls != null)
					return cls.getURI();
			}
		}
		return null;
	}
	
}
