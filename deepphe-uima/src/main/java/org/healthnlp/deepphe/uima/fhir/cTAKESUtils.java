package org.healthnlp.deepphe.uima.fhir;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.tools.TextTools;
import org.apache.ctakes.cancer.concept.instance.ConceptInstance;
import org.apache.ctakes.cancer.concept.instance.ConceptInstanceUtil;
import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.PhenotypeAnnotationUtil;
import org.apache.ctakes.cancer.phenotype.stage.StagePropertyUtil;
import org.apache.ctakes.cancer.phenotype.tnm.TnmPropertyUtil;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.core.util.OntologyConceptUtil;
import org.apache.ctakes.typesystem.type.refsem.OntologyConcept;
import org.apache.ctakes.typesystem.type.refsem.Time;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.Relation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.TimeMention;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Extension;

import java.net.URI;
import java.util.*;
import java.util.regex.Pattern;

public class cTAKESUtils {

	static private final Logger LOGGER = Logger.getLogger( "cTAKESUtils" );


	/**
	 * get FHIR date object from cTAKES time mention
	 * @param tm -
	 * @return -
	 */
	public static java.util.Date getDate( TimeMention tm ) {
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


	public static Extension createMentionExtension( final ConceptInstance conceptInstance ) {
		IdentifiedAnnotation mention = conceptInstance.getIdentifiedAnnotation();
		return FHIRUtils.createMentionExtension( mention.getCoveredText(), mention.getBegin(), mention.getEnd() );
	}


	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param ia -
	 * @return -
	 */
	public static CodeableConcept getCodeableConcept(IdentifiedAnnotation ia){
		return setCodeableConcept(new CodeableConcept(),ia);
	}


	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param ia -
	 * @return -
	 */
	public static CodeableConcept getCodeableConcept( ConceptInstance ia ) {
		//TODO: maybe make better
		return setCodeableConcept( new CodeableConcept(), ia.getIdentifiedAnnotation() );
	}

	/**
	 * get codeblce concept form OntologyConcept annotation
	 * @param cc -
	 * @param ia -
	 * @return -
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
		// set display text if unavialble
		if(cc.getText() == null){
			for(Coding ccc: cc.getCoding()){
				if(ccc.getDisplay() != null){
					cc.setText(ccc.getDisplay());
					break;
				}
			}
		}
		
		
		return cc;
	}


	static private final Pattern CUI_PATTERN = Pattern.compile( "CL?\\d{6,7}" );
	static private final java.util.function.Predicate<String> CUI_PREDICATE = CUI_PATTERN.asPredicate();
	/**
	 * get concept class from a default ontology based on Concept
	 * @param ia -
	 * @return -
	 */
	public static String getConceptCode(IdentifiedAnnotation ia){
		// TODO - can use OntologyConceptUtil.getCuis( ia );
		return OntologyConceptUtil.getCuis( ia ).stream().filter( CUI_PREDICATE ).findFirst().get();
//		String cui = null;
//		for(int i=0;i<ia.getOntologyConceptArr().size();i++){
//			OntologyConcept c = ia.getOntologyConceptArr(i);
//			if(c instanceof UmlsConcept){
//				cui = ((UmlsConcept)c).getCui();
//			}else{
//				cui = c.getCode();
//			}
//			if(cui != null && cui.matches("CL?\\d{6,7}"))
//				break;
//		}
//		return cui;
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param ia -
	 * @return -
	 */
	public static String getConceptURI(IdentifiedAnnotation ia){
		// TODO - can use OwlOntologyConceptUtil.getUris( ia );
		return OwlOntologyConceptUtil.getUris( ia ).stream().findFirst().orElse( OwlOntologyConceptUtil.UNKNOWN_URI );
//		String cui = null;
//		for(int i=0;i<ia.getOntologyConceptArr().size();i++){
//			OntologyConcept c = ia.getOntologyConceptArr(i);
//			cui = c.getCode();
//			if(cui != null && cui.startsWith("http://"))
//				break;
//		}
//		return cui;
	}
	
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param ia -
	 * @return -
	 */
	public static String getConceptName(IdentifiedAnnotation ia){
		if(ia == null)
			return null;
		String name = getConceptURI(ia);
		if(name != null)
			name = FHIRUtils.getConceptName(URI.create(name));
		return name == null?ia.getCoveredText():name;
	}
	
	
	/**
	 * get related item from cTAKES
	 * @param source -
	 * @param relation -
	 * @return -
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
	 * @param cas -
	 * @return -
	 */
	public static String getDocumentText(JCas cas){
		Iterator<Annotation> it = cas.getAnnotationIndex(DocumentAnnotation.type).iterator();
		if(it.hasNext())
			return it.next().getCoveredText();
		return null;
	}
	
	/**
	 * get concept class from a default ontology based on Concept
	 * @param ont -
	 * @param m -
	 * @return -
	 */
	public static IClass getConceptClass(IOntology ont, IdentifiedAnnotation m){
		// CancerSize doesn't have a CUI, but can be mapped
		/*if(m instanceof CancerSize){
			return ont.getClass(TUMOR_SIZE);
		}*/
		
		String cui = getConceptURI(m);
		return cui != null?ont.getClass(cui):null;
	}
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param cas -
	 * @param type -
	 * @return -
	 */
	public static List<IdentifiedAnnotation> getAnnotationsByType(JCas cas, int type){
		List<IdentifiedAnnotation> list = new ArrayList<>();
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
	 * @param cas -
	 * @param type -
	 * @return -
	 */
	public static List<ConceptInstance> getAnnotationsByType( JCas cas, URI type ) {
		// TODO is manipulation required?
		return new ArrayList<>( ConceptInstanceUtil.getBranchConceptInstances( cas, type.toString() ) );
//		List<IdentifiedAnnotation> annotations = new ArrayList<IdentifiedAnnotation>();
//		for(IdentifiedAnnotation a: OwlOntologyConceptUtil.getAnnotationsByUriBranch(cas,type.toString())){
//			annotations.add(a);
//		}
//		return annotations;
	}
	
	
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param cas -
	 * @param type -
	 * @return -
	 */
	public static List<Relation> getRelationsByType(JCas cas, Type type){
		List<Relation> list = new ArrayList<>();
		Iterator<FeatureStructure> it = cas.getFSIndexRepository().getAllIndexedFS(type);
		while(it.hasNext()){
			list.add((Relation)it.next());
		}
		return list;
	}
	
	/**
	 * get a set of concept by type from the annotated document
	 * @param an -
	 * @param classType -
	 * @return -
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
	 //	 * @param an -
	 * @return -
	 */
	/*
	public static AnatomicalSiteMention getAnatimicLocation(IdentifiedAnnotation an){
//		JCas cas = null;
//		try {
//			cas = an.getCAS().getJCas();
//		} catch (CASException e) {
//			e.printStackTrace();
//		}
//		for(Relation r: getRelationsByType(cas,new LocationOfTextRelation(cas).getType())){
//			LocationOfTextRelation lr = (LocationOfTextRelation) r;
//			if(equals(lr.getArg1(),an) && lr.getArg2().getArgument() instanceof AnatomicalSiteMention){
//				return (AnatomicalSiteMention) lr.getArg2().getArgument();
//			}
//		}
//		return null;
		// TODO - can use InstanceUtil.getLocations( an ) - there may be occasions where there are more than 1: breast ; nipple
		final JCas jcas = getJcas( an );
		if ( jcas == null ) {
			return null;
		}
		return (AnatomicalSiteMention)InstanceUtil.getLocations( jcas, an ).stream()
				.filter( AnatomicalSiteMention.class::isInstance )
				.findFirst().get();
	}
	*/
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
	 * @param an -
	 * @return -
	 */
	public static IdentifiedAnnotation getDegreeOf(IdentifiedAnnotation an){
		final JCas jcas = getJcas( an );
		if ( jcas == null ) {
			return null;
		}
		if(PhenotypeAnnotationUtil.getPropertyValues( jcas, an ).isEmpty())
			return null;
		return PhenotypeAnnotationUtil.getPropertyValues( jcas, an ).stream().findFirst().get();
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
	 * @param dm -
	 * @return -
	 */
	public static SizeMeasurement getSizeMeasurement(IdentifiedAnnotation dm){
		// TODO - can we safely use a MeasurementAnnotation ?  Should we just stick to CancerSize for now?
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

	//	public static TnmClassification getTnmClassification(IdentifiedAnnotation dm){
//		for(Annotation  a: cTAKESUtils.getRelatedAnnotationsByType(dm,NeoplasmRelation.class)){
//			if(a instanceof TnmClassification){
//				return (TnmClassification) a;
//			}
//		}
//		return null;
//	}
	public static Collection<IdentifiedAnnotation> getTnmClassifications( final IdentifiedAnnotation neoplasm ) {
		final JCas jcas = getJcas( neoplasm );
		if ( jcas == null ) {
			return null;
		}
		return PhenotypeAnnotationUtil.getNeoplasmPropertiesBranch( jcas, neoplasm, TnmPropertyUtil.getParentUri() );
		
		
		
	//	return ConceptInstanceUtil.getBranchConceptInstances(jcas, TnmPropertyUtil.getParentUri()).stream().map(ConceptInstanceUtil::getPropertyValues);
		
		
	}

	//	public static CancerStage getCancerStage(IdentifiedAnnotation dm){
//		for(Annotation  a: cTAKESUtils.getRelatedAnnotationsByType(dm,NeoplasmRelation.class)){
//			if(a instanceof CancerStage){
//				return (CancerStage) a;
//			}
//		}
//		return null;
//	}
	public static Collection<IdentifiedAnnotation> getCancerStages( final IdentifiedAnnotation neoplasm ) {
		final JCas jcas = getJcas( neoplasm );
		if ( jcas == null ) {
			return null;
		}
		return PhenotypeAnnotationUtil.getNeoplasmPropertiesBranch( jcas, neoplasm, StagePropertyUtil.getParentUri() );
	}

	/**
	 * @param annotation any type of annotation
	 * @return jcas containing the annotation or null if there is none
	 */
	static public JCas getJcas( final TOP annotation ) {
		try {
			return annotation.getCAS().getJCas();
		} catch ( CASException casE ) {
			LOGGER.error( casE.getMessage() );
		}
		return null;
	}

	private static boolean isEmpty(String s){
		return s == null || s.trim().length() == 0;
	}
	
	public static void addLanguageContext( ConceptInstance conceptInstance, DomainResource dx ) {
		if (!isEmpty(conceptInstance.getDocTimeRel())) {
			dx.addExtension( FHIRUtils.createDocTimeRelExtension( conceptInstance.getDocTimeRel() ) );
		}
		if (!isEmpty(conceptInstance.getModality())) {
			dx.addExtension( FHIRUtils.createModalityExtension( conceptInstance.getModality() ) );
		}
		if(conceptInstance.isNegated()){
			dx.addExtension( FHIRUtils.createExtension(FHIRUtils.LANGUAGE_ASPECT_NEGATED_URL,""+conceptInstance.isNegated() ) );
		}
		if(conceptInstance.isUncertain()){
			dx.addExtension( FHIRUtils.createExtension(FHIRUtils.LANGUAGE_ASPECT_UNCERTAIN_URL,""+conceptInstance.isUncertain() ) );
		}
		if(conceptInstance.isConditional()){
			dx.addExtension( FHIRUtils.createExtension(FHIRUtils.LANGUAGE_ASPECT_CONDITIONAL_URL,""+conceptInstance.isConditional() ) );
		}
		if(conceptInstance.isIntermittent()){
			dx.addExtension( FHIRUtils.createExtension(FHIRUtils.LANGUAGE_ASPECT_INTERMITTENT_URL,""+conceptInstance.isIntermittent() ) );
		}
		if(conceptInstance.isHypothetical()){
			dx.addExtension( FHIRUtils.createExtension(FHIRUtils.LANGUAGE_ASPECT_HYPOTHETICAL_URL,""+conceptInstance.isHypothetical() ) );
		}
		if(conceptInstance.isPermanent()){
			dx.addExtension( FHIRUtils.createExtension( FHIRUtils.LANGUAGE_ASPECT_PERMENENT_URL,
					"" + conceptInstance.isPermanent() ) );
		}
		if(conceptInstance.inPatientHistory()){
			dx.addExtension( FHIRUtils.createExtension( FHIRUtils.LANGUAGE_ASPECT_HISTORICAL_URL,
					"" + conceptInstance.inPatientHistory() ) );
		}
	}
}
