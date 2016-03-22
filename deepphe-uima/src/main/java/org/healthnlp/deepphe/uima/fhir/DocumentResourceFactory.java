package org.healthnlp.deepphe.uima.fhir;

import java.io.File;
import java.net.URI;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.size.SizePropertyUtil;
import org.apache.ctakes.cancer.stage.StagePropertyUtil;
import org.apache.ctakes.cancer.tnm.TnmPropertyUtil;
//import org.apache.ctakes.cancer.type.textsem.CancerSize;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.core.util.DocumentIDAnnotationUtil;
import org.apache.ctakes.typesystem.type.textsem.AnatomicalSiteMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.fhir.AnatomicalSite;
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Element;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.OntologyUtils;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.util.TextUtils;
import org.hl7.fhir.instance.model.BodySite;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Procedure.ProcedureStatus;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;

final public class DocumentResourceFactory {

	static private final Logger LOGGER = Logger.getLogger( "DocumentResourceFactory" );

	static private class SingletonHolder {
		static private DocumentResourceFactory _instance;

		synchronized static private void setInstance( final DocumentResourceFactory instance ) {
			if ( _instance != null ) {
				LOGGER.error( "DocumentResourceFactory singleton being overwritten!" );
			}
			_instance = instance;
		}

		synchronized static private DocumentResourceFactory getInstance() {
			if ( _instance == null ) {
				LOGGER.error( "DocumentResourceFactory singleton being referenced before creation!" );
			}
			return _instance;
		}
	}


	// TODO - made this a little more threadsafe "singleton"
//	private static DocumentResourceFactory instance;
	private IOntology ontology;
	private OntologyUtils ontologyUtils;

//	public DocumentResourceFactory(IOntology ont){
//		ontology = ont;
//		instance = this;
//	}

	static public void createInstance( final IOntology iOntology ) {
		SingletonHolder.setInstance( new DocumentResourceFactory( iOntology ) );
	}

	public static DocumentResourceFactory getInstance(){
//		return instance;
		return SingletonHolder.getInstance();
	}

	private DocumentResourceFactory( final IOntology iOntology ) {
		ontology = iOntology;
	}


	public IOntology getOntology() {
		return ontology;
	}

	public void setOntology(IOntology ontology) {
		this.ontology = ontology;
	}

	public OntologyUtils getOntologyUtils(){
		if(ontologyUtils == null && ontology != null){
			ontologyUtils = new OntologyUtils(ontology);
		}
		return ontologyUtils;
	}
	
	/**
	 * create a Report object from DocumentAnnotation
	 * @param cas -
	 * @return -
	 */
	public static Report getReport(JCas cas) {
		Report r = getReport(cTAKESUtils.getDocumentText(cas));
		
		// oh, well no document title available
		String title;
		try {
			title = DocumentIDAnnotationUtil.getDocumentIdForFile(cas);
		} catch (Exception e) {
			e.printStackTrace();
			title = "Untitled";
		}
		if(title != null){
			r.setTitle(TextUtils.stripSuffix(title));
		}
		
		// find patient if available
		Patient patient = getPatient(cas);
		if(patient != null){
			r.setPatient(patient);
		}

		// TODO - swapped iteration loops with foreach calls
		// now find all anatomical site found in a report
		getAnatomicalSite( cas ).forEach( r::addReportElement );
//		for(AnatomicalSite ob: getAnatomicalSite(cas)){
//			r.addReportElement(ob);
//		}
		
		// now find all observations found in a report
		getObservations( cas ).forEach( r::addReportElement );
//		for(Observation ob: getObservations(cas)){
//			r.addReportElement(ob);
//		}
		
		// now find all observations found in a report
		getFindings( cas ).forEach( r::addReportElement );
//		for(Finding ob: getFindings(cas)){
//			r.addReportElement(ob);
//		}
		
		// find all procedures mentioned in each report
		getProcedures( cas ).forEach( r::addReportElement );
//		for(Procedure p: getProcedures(cas)){
//			r.addReportElement(p);
//		}
		
		// now find all observations found in a report
		getMedications( cas ).forEach( r::addReportElement );
//		for(Medication ob: getMedications(cas)){
//			r.addReportElement(ob);
//		}
		
		// now find all primary disease that are found in a report
		getDiagnoses( cas ).forEach( r::addReportElement );
//		for(Disease dx: getDiagnoses(cas)){
//			r.addReportElement(dx);
//		}
		
		
		return r;
	}
	
	/**
	 * create a Report object from DocumentAnnotation
	 * @param text -
	 * @return -
	 */
	public static Report getReport(String text) {
		if(text == null)
			return null;
		
		Report r = new Report();
		r.setText(text);
		// some hard-coded report type values
		Map<String,String> header = FHIRUtils.getHeaderValues(text);
		String dt = header.get(FHIRUtils.DOCUMENT_HEADER_PRINCIPAL_DATE);
		if(dt != null)
			r.setDate(FHIRUtils.getDate(dt));
		String tp = header.get(FHIRUtils.DOCUMENT_HEADER_REPORT_TYPE);
		if(tp != null)
			r.setType(tp);
		return r;
	}
	
	
	public static Patient getPatient(JCas cas) {
		Patient p =  getPatient(cTAKESUtils.getDocumentText(cas));
		// TODO: age and gender
		
		return p;
	}
	
	/**
	 * get patient from the document
	 * @param p -
	 * @return -
	 */
	public static Patient getPatient(org.hl7.fhir.instance.model.Patient p) {
		Patient patient = new Patient();
		patient.copy(p);
		FHIRRegistry.getInstance().addResource(patient);
		return patient;
	}
	
	/**
	 * get patient from the document
	 * @param text -
	 * @return -
	 */
	public static Patient getPatient(String text) {
		if(text == null)
			return null;
		Map<String,String> header = FHIRUtils.getHeaderValues(text);
		String pn = header.get(FHIRUtils.DOCUMENT_HEADER_PATIENT_NAME);
		if(pn != null){
			Patient p = new Patient();
			p.setPatientName(pn);
			int n = text.indexOf(pn);
			p.addExtension(FHIRUtils.createMentionExtension(pn,n,n+pn.length()));
			
			// register
			FHIRRegistry.getInstance().addResource(p);
			
			return p;
		}
		return null;
	}


	/**
	 * get concept class from a default ontology based on Concept
	 * @param c -
	 * @return -
	 */
	public IClass getConceptClass(Concept c){
		String code = c.getCode();
		if(code.contains(":"))
			code = code.substring(code.indexOf(':')+1);
		return ontology.getClass(code);
	}


	// TODO - added method to simplify specific Element list creation

	/**
	 * Obtain a list of Elements of type T in the given cas with the given uri
	 *
	 * @param jcas   ye olde ...
	 * @param uri    uri for the identified annotation
	 * @param mapper creates the desired output class T from an identified annotation
	 * @param <T>    some extension of fhir element
	 * @return list of Elements of type T in the given cas with the given uri
	 */
	static private <T extends Element> List<T> getElementList( final JCas jcas,
																				  final URI uri,
																				  final Function<IdentifiedAnnotation, ? extends T> mapper ) {
		return cTAKESUtils.getAnnotationsByType( jcas, uri ).stream()
				.map( mapper )
				.collect( Collectors.toList() );
	}


	public static List<Procedure> getProcedures( JCas cas ) {
		// TODO - can use new method
		return getElementList( cas, FHIRConstants.PROCEDURE_URI, DocumentResourceFactory::createProcedure );
//		List<Procedure> list = new ArrayList<Procedure>();
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.PROCEDURE_URI)){
//			list.add(createProcedure(m));
//		}
//		return list;
	}

	/**
	 *
	 */
	public static List<Disease> getDiagnoses( JCas cas ) {
		// TODO - can use new method
		return getElementList( cas, FHIRConstants.DIAGNOSIS_URI, DocumentResourceFactory::createDiagnosis );
//		List<Disease> list = new ArrayList<Disease>();
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.DIAGNOSIS_URI)){
//			list.add(createDiagnosis(m));
//		}
//		return list;
	}

	public static List<Medication> getMedications( JCas cas ) {
		// TODO - can use new method
		return getElementList( cas, FHIRConstants.MEDICATION_URI, DocumentResourceFactory::createMedication );
//		List<Medication> list = new ArrayList<Medication>();
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas,FHIRConstants.MEDICATION_URI)){
//			list.add(createMedication(m));
//		}
//		return list;
	}


	public static List<Finding> getFindings( JCas cas ) {
		// TODO - can use new method
		final List<Finding> list
				= getElementList( cas, FHIRConstants.FINDING_URI, DocumentResourceFactory::createFinding );
//		List<Finding> list = new ArrayList<Finding>();
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.FINDING_URI) ){
//			list.add(createFinding(m));
//		}
		//TODO: add TNM stuff for now
//		for(Annotation  a: cTAKESUtils.getAnnotationsByType(cas, TnmClassification.type)){
//			TnmClassification tnm = (TnmClassification) a;
//			if(tnm.getSize() != null){
//				list.add(createFinding(tnm.getSize()));
//			}
//			if(tnm.getMetastasis() != null){
//				list.add(createFinding(tnm.getMetastasis()));
//			}
//			if(tnm.getNodeSpread() != null){
//				list.add(createFinding(tnm.getNodeSpread()));
//			}
//		}
		// add individual T N M to list
		OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( cas, TnmPropertyUtil.getParentUri() )
				.map( DocumentResourceFactory::createFinding ).forEach( list::add );
//		for(Annotation  a: cTAKESUtils.getAnnotationsByType(cas,DiseaseDisorderMention.type)){
//			CancerStage st = cTAKESUtils.getCancerStage((IdentifiedAnnotation)a);
//			if(st != null)
//				list.add(createFinding(st));
//		}
		// add Stage to list
		OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( cas, StagePropertyUtil.getParentUri() )
				.map( DocumentResourceFactory::createFinding ).forEach( list::add );
		return list;
	}


	public static List<AnatomicalSite> getAnatomicalSite( JCas cas ) {
		// TODO - can use new method
		return getElementList( cas, FHIRConstants.BODY_SITE_URI, DocumentResourceFactory::createAnatomicalSite );
//		List<AnatomicalSite> list = new ArrayList<AnatomicalSite>();
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas,FHIRConstants.BODY_SITE_URI)){
//			list.add(createAnatomicalSite(m));
//		}
//		return list;
	}

	public static List<Observation> getObservations( JCas cas ) {
		// TODO - can use new method
		final List<Observation> list
				= getElementList( cas, FHIRConstants.OBSERVATION_URI, DocumentResourceFactory::createObservation );
//		List<Observation> list = new ArrayList<Observation>();
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas, FHIRConstants.OBSERVATION_URI) ){
//			list.add(createObservation(m));
//		}
		//TODO: this is a hack that will be removed after CancerSize becomes an obervation in cTAKES API
//		cTAKESUtils.getAnnotationsByType( cas, CancerSize.type ).stream()
//				.map( DocumentResourceFactory::createObservation )
//				.forEach( list::add );
		OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( cas, SizePropertyUtil.getParentUri() )
				.map( DocumentResourceFactory::createObservation ).forEach( list::add );
//		for(IdentifiedAnnotation m: cTAKESUtils.getAnnotationsByType(cas,CancerSize.type)){
//			list.add(createObservation(m));
//		}
		return list;
	}
	
	private static boolean isType(File f, Class cls){
		return f.getName().endsWith(".xml") && f.getName().startsWith(cls.getSimpleName().toUpperCase());
	}
	
	
	public static Report getReport(Composition c){
		if(c == null)
			return null;
		Report report = new Report();
		report.copy(c);
		FHIRRegistry.getInstance().addResource(report);
		return report;
	}
	
	public static Finding getFinding(Condition c){
		if(c == null)
			return null;
		Finding d = new Finding();
		d.copy(c);
		FHIRRegistry.getInstance().addResource(d);
		return d;
	}
	public static Disease getDiagnosis(Condition c){
		if(c == null)
			return null;
		Disease d = new Disease();
		d.copy(c);
		FHIRRegistry.getInstance().addResource(d);
		return d;
	}
	
	public static Procedure getProcedure(org.hl7.fhir.instance.model.Procedure p){
		if(p == null)
			return null;
		Procedure pp = new Procedure();
		pp.copy(p);
		FHIRRegistry.getInstance().addResource(pp);
		return pp;
	}
	
	public static Observation getObservation(org.hl7.fhir.instance.model.Observation p){
		if(p == null)
			return null;
		Observation pp = new Observation();
		pp.copy(p);
		FHIRRegistry.getInstance().addResource(pp);
		return pp;
	}
	
	public static Medication getMedication(org.hl7.fhir.instance.model.Medication p){
		if(p == null)
			return null;
		Medication pp = new Medication();
		pp.copy(p);
		FHIRRegistry.getInstance().addResource(pp);
		return pp;
	}
	
	

	public static AnatomicalSite getAnatomicalSite(BodySite p) {
		if(p == null)
			return null;
		AnatomicalSite pp = new AnatomicalSite();
		pp.copy(p);
		FHIRRegistry.getInstance().addResource(pp);
		return pp;
	}

	
	
	public static Stage getStage(ConditionStageComponent c){
		if(c == null)
			return null;
		Stage s = new Stage();
		s.copy(c);
		return s;
	}
	
	/**
	 * if possible re-create Report FHIR object from REPORT file directory that has FHIR XML files
	 * @param reportDir -
	 * @return -
	 */
	public static Report loadReport( File reportDir ) throws Exception {
		// TODO check first, otherwise may get NPE
		if ( reportDir == null || !reportDir.isDirectory() ) {
			return null;
		}
		final File[] files = reportDir.listFiles();
		if ( files == null ) {
			return null;
		}
		Report report = null;
		Patient patient = null;

//		if(reportDir.exists()){
			// find report & patient first
		for (File f: files ){
				if(isType(f, Report.class)){
					report =  getReport((Composition) FHIRUtils.loadFHIR(f));
				}else if(isType(f, Patient.class ) ) {
					patient = getPatient( (org.hl7.fhir.instance.model.Patient)FHIRUtils.loadFHIR( f ) );
				}
		}
		if ( report == null ) {
			return null;
		}
		// add patient to report
//			if(report != null && patient != null){
		if ( patient != null ) {
				report.setPatient(patient);
			}
			
			// load other components into report
		for (File f: files ){
				if(isType(f, Finding.class)){
					report.addReportElement(getFinding((Condition) FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Disease.class)){
					report.addReportElement(getDiagnosis((Condition)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Procedure.class)){
					report.addReportElement(getProcedure((org.hl7.fhir.instance.model.Procedure)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Observation.class)){
					report.addReportElement(getObservation((org.hl7.fhir.instance.model.Observation)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, Medication.class)){
					report.addReportElement(getMedication((org.hl7.fhir.instance.model.Medication)FHIRUtils.loadFHIR(f)));
				}else if(isType(f, AnatomicalSite.class)){
					report.addReportElement(getAnatomicalSite((org.hl7.fhir.instance.model.BodySite)FHIRUtils.loadFHIR(f ) ) );
				}
			}
//		}
		
		return report;
	}

	
	public static Disease createDiagnosis(IdentifiedAnnotation dm ) {
		return load( new Disease(), dm );
	}

	/**
	 *
	 * @param anatomicalSite -
	 * @param mention -
	 * @return -
	 */
	public static AnatomicalSite load(AnatomicalSite anatomicalSite, IdentifiedAnnotation mention) {
		anatomicalSite.setCode(cTAKESUtils.getCodeableConcept(mention));
		// add mention text
		anatomicalSite.addExtension(FHIRUtils.createMentionExtension(mention.getCoveredText(),mention.getBegin(),mention.getEnd()));
		
		// create identifier
		FHIRUtils.createIdentifier(anatomicalSite.addIdentifier(),anatomicalSite);
	
		
		// register
		FHIRRegistry.getInstance().addResource(anatomicalSite,mention);
		
		return anatomicalSite;
	}

	/**
	 *
	 * @param finding -
	 * @param mention -
	 * @return -
	 */
	public static Finding load(Finding finding, IdentifiedAnnotation mention) {
		finding.setCode(cTAKESUtils.getCodeableConcept(mention));
		
		// see if we have annotations or maybe parent might have them
		if ( mention.getBegin() == 0 && mention.getEnd() == 0 ) {
//			TnmClassification tnm = null;
			try {
//				for(IdentifiedAnnotation t: cTAKESUtils.getAnnotationsByType(mention.getCAS().getJCas(),TnmClassification.type)){
//					TnmClassification tt = (TnmClassification) t;
//					if(Arrays.asList(tt.getSize(),tt.getMetastasis(),tt.getNodeSpread()).contains(mention)){
//						tnm = tt;
//						break;
//					}
//				}
				// TODO - use OwlOntologyConceptUtil.getAnnotationsByUriBranch  - should this use a coallesced annotation?
				final IdentifiedAnnotation tnm
						= OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( mention.getCAS().getJCas(),
						TnmPropertyUtil.getParentUri() )
						.filter( mention::equals )
						.findFirst().get();
				if ( tnm != null ) {
					mention.setBegin( tnm.getBegin() );
					mention.setEnd( tnm.getEnd() );
				}
			} catch (CASException e) {
				e.printStackTrace();
			}
//			if(tnm != null){
//				mention.setBegin(tnm.getBegin());
//				mention.setEnd(tnm.getEnd());
//			}
		}
		
		// add mention text
		finding.addExtension(FHIRUtils.createMentionExtension(mention.getCoveredText(),mention.getBegin(),mention.getEnd()));
		
		// create identifier
		FHIRUtils.createIdentifier(finding.addIdentifier(),finding);
	
		
		// register
		FHIRRegistry.getInstance().addResource(finding,mention);
		
		return finding;
	}
	
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx -
	 * @param dm -
	 * @return -
	 */
	public static Disease load(Disease dx, IdentifiedAnnotation dm){
		// set some properties
		dx.setCode(cTAKESUtils.getCodeableConcept(dm));
		//setCertainty(); --> dm.getConfidence()
		//setSeverity(value); -- > dm.getSeverity()???
		
		
		// perhaps have annotation from Document time
		/*TimeMention tm = dm.getStartTime();
		if(tm != null){
			dx.setDateRecorded(cTAKESUtils.getDate(tm));
		}*/
			
		// now lets take a look at the location of this disease
		AnatomicalSiteMention as =  cTAKESUtils.getAnatimicLocation(dm);
		if(as != null){
			CodeableConcept cc = cTAKESUtils.getCodeableConcept(as);
			// see if there is a registered resource available
			AnatomicalSite site = (AnatomicalSite) FHIRRegistry.getInstance().getResource(as);
			if(site != null)
				FHIRUtils.addResourceReference(cc,site);
			dx.addBodySite(cc);
		}
	
		// now lets get the location relationships
//		CancerStage st = cTAKESUtils.getCancerStage(dm);
//		TnmClassification tnm = cTAKESUtils.getTnmClassification( dm );
//		if(tnm != null || st != null){
//			dx.setStage(createStage(st,tnm));
//		}

		Collection<IdentifiedAnnotation> stages = cTAKESUtils.getCancerStages( dm );
		Collection<IdentifiedAnnotation> tnms = cTAKESUtils.getTnmClassifications( dm );
		dx.setStage( createStage( stages, tnms ) );

		// add mention text
		dx.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		// create identifier
		FHIRUtils.createIdentifier(dx.addIdentifier(),dx);
		
		// register
		FHIRRegistry.getInstance().addResource(dx,dm);
		
		return dx;
	}
	
	public static Medication createMedication(IdentifiedAnnotation dm){
		return load(new Medication(),dm);
	}
	
	/**
	 * load medication
	 * @param md
	 * @param dm
	 * @return
	 */
	public static Medication load(Medication md, IdentifiedAnnotation dm){
		md.setCode(cTAKESUtils.getCodeableConcept(dm));
		md.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		// register
		FHIRRegistry.getInstance().addResource(md,dm);
		return md;
	}
	
	
	public static Observation createObservation(IdentifiedAnnotation dm){
		return load(new Observation(),dm);
	}
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param ob -
	 * @param dm -
	 * @return -
	 */
	public static Observation load(Observation ob,IdentifiedAnnotation dm){
		// set some properties
		ob.setCode(cTAKESUtils.getCodeableConcept(dm));
		
		// see if there is an ordinal interpretation
		IdentifiedAnnotation interpretation =  cTAKESUtils.getDegreeOf(dm);
		if(interpretation != null){
			ob.setInterpretation(cTAKESUtils.getCodeableConcept(interpretation));
		}

		// TODO - a lot of things got mucked up with the new "stick to ctakes" methods
		// TODO - what to do with this?  We need measurement and unit.  Can we just split(" ") ?
		// if cancer size, then use their value
		SizeMeasurement num = cTAKESUtils.getSizeMeasurement(dm);
		if(num != null){
			ob.setValue(num.getValue(),num.getUnit());
		}
		

		// add mention text
		ob.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		// add id
		FHIRUtils.createIdentifier(ob.addIdentifier(),ob);
		
		// register
		FHIRRegistry.getInstance().addResource(ob,dm);
		
		return ob;
	}
	
	
	public static Procedure createProcedure(IdentifiedAnnotation dm){
		return load(new Procedure(),dm);
	}
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param pr -
	 * @param dm -
	 * @return -
	 */
	public static Procedure load(Procedure pr,IdentifiedAnnotation dm){
		// set some properties
		pr.setCode(cTAKESUtils.getCodeableConcept(dm));
		pr.setStatus(ProcedureStatus.COMPLETED);
				
		// now lets take a look at the location of this disease
		AnatomicalSiteMention as = cTAKESUtils.getAnatimicLocation(dm);
		if(as != null){
			CodeableConcept location = pr.addBodySite();
			cTAKESUtils.setCodeableConcept(location,as);
			AnatomicalSite site = (AnatomicalSite) FHIRRegistry.getInstance().getResource(as);
			if(site != null)
				FHIRUtils.addResourceReference(location,site);
		}

		
		// now lets add observations
		//addEvidence();
		//addRelatedItem();

		// add mention text
		pr.addExtension(FHIRUtils.createMentionExtension(dm.getCoveredText(),dm.getBegin(),dm.getEnd()));
		
		FHIRUtils.createIdentifier(pr.addIdentifier(),pr);


		// register
		FHIRRegistry.getInstance().addResource(pr, dm );
		return pr;
	}


	//	public static Stage createStage(CancerStage st, TnmClassification tnm) {
	//		return load(new Stage(),st,tnm);
	//	}
	public static Stage createStage( final Collection<IdentifiedAnnotation> stages,
												final Collection<IdentifiedAnnotation> tnms ) {
		return load( new Stage(),stages, tnms );
	}


	public static AnatomicalSite createAnatomicalSite(IdentifiedAnnotation m) {
		return load(new AnatomicalSite(),m);
	}

	public static Finding createFinding(IdentifiedAnnotation m) {
		return load(new Finding(),m);
	}
	
	


	/**
	 * load stage object
	 */

//	public static Stage load(Stage stage, CancerStage st, TnmClassification tnm) {
//
//		// set cancer stage, if available
//		if(st != null){
//			CodeableConcept c = cTAKESUtils.getCodeableConcept(st);
//			c.setText(st.getCoveredText());
//			stage.setSummary(c);
//
//			// add id to cancer stage
//			Finding f = (Finding) FHIRRegistry.getInstance().getResource(st);
//			if(f != null)
//				FHIRUtils.addResourceReference(c,f);
//
//			// add extension
//			stage.addExtension(FHIRUtils.createMentionExtension(st.getCoveredText(),st.getBegin(),st.getEnd()));
//		}else{
//
//			// for now just add a generic TNM
//			CodeableConcept c = cTAKESUtils.getCodeableConcept(tnm);
//			c.setText(tnm.getCoveredText());
//			stage.setSummary(c);
//		}
//
//
//		// extract individual Stage levels if values are conflated
//		if(tnm != null){
//			if(tnm.getSize() != null){
//				Finding f = (Finding) FHIRRegistry.getInstance().getResource(tnm.getSize());
//				if(f == null)
//					f = createFinding(tnm.getSize());
//				stage.addAssessment(f);
//				//stage.setStringExtension(Stage.TNM_PRIMARY_TUMOR,cTAKESUtils.getConceptURI(st.getSize()));
//			}
//			if(tnm.getNodeSpread() != null){
//				Finding f = (Finding) FHIRRegistry.getInstance().getResource(tnm.getNodeSpread());
//				if(f == null)
//					f = createFinding(tnm.getNodeSpread());
//				stage.addAssessment(f);
//				//stage.setStringExtension(Stage.TNM_REGIONAL_LYMPH_NODES,cTAKESUtils.getConceptURI(st.getNodeSpread()));
//			}
//			if(tnm.getMetastasis() != null){
//				Finding f = (Finding) FHIRRegistry.getInstance().getResource(tnm.getMetastasis());
//				if(f == null)
//					f = createFinding(tnm.getMetastasis());
//				stage.addAssessment(f);
//				//stage.setStringExtension(Stage.TNM_DISTANT_METASTASIS,cTAKESUtils.getConceptURI(st.getMetastasis()));
//			}
//			stage.addExtension(FHIRUtils.createMentionExtension(tnm.getCoveredText(),tnm.getBegin(),tnm.getEnd()));
//		}
//
//
//		// register
//		//FHIRRegistry.getInstance().addResource(stage,st);
//
//		return stage;
//	}
	public static Stage load( Stage stage,
									  final Collection<IdentifiedAnnotation> stageAnnotations,
									  final Collection<IdentifiedAnnotation> tnmAnnotations ) {
		// set cancer stage, if available
		if ( stageAnnotations != null && !stageAnnotations.isEmpty() ) {
			final JCas jcas = stageAnnotations.stream().map( cTAKESUtils::getJcas ).findFirst().get();
			if ( jcas == null ) {
				LOGGER.error( "No Cas exists for Stage annotations" );
			} else {
				final IdentifiedAnnotation fullStageAnnotation = StagePropertyUtil
						.createCoallescedProperty( jcas, stageAnnotations );
				CodeableConcept c = cTAKESUtils.getCodeableConcept( fullStageAnnotation );
				c.setText( fullStageAnnotation.getCoveredText() );
				stage.setSummary( c );
				// add id to cancer stage
				Finding f = (Finding)FHIRRegistry.getInstance().getResource( fullStageAnnotation );
				if ( f != null ) {
					FHIRUtils.addResourceReference( c, f );
				}
				// add extension
				stage.addExtension(
						FHIRUtils.createMentionExtension( fullStageAnnotation.getCoveredText(),
								fullStageAnnotation.getBegin(), fullStageAnnotation.getEnd() ) );
			}
		} else {
			// for now just add a generic TNM
			if ( tnmAnnotations != null && !tnmAnnotations.isEmpty() ) {
				final JCas jcas = tnmAnnotations.stream().map( cTAKESUtils::getJcas ).findFirst().get();
				if ( jcas == null ) {
					LOGGER.error( "No Cas exists for TNM annotations" );
				} else {
					final IdentifiedAnnotation fullTnmAnnotation = TnmPropertyUtil
							.createCoallescedProperty( jcas, tnmAnnotations );
					CodeableConcept c = cTAKESUtils.getCodeableConcept( fullTnmAnnotation );
					c.setText( fullTnmAnnotation.getCoveredText() );
					stage.setSummary( c );
				}
			}
		}
		// extract individual Stage levels if values are conflated
		if ( tnmAnnotations != null && !tnmAnnotations.isEmpty() ) {
			final JCas jcas = tnmAnnotations.stream().map( cTAKESUtils::getJcas ).findFirst().get();
			if ( jcas == null ) {
				LOGGER.error( "No Cas exists for TNM annotations" );
			} else {
				for ( IdentifiedAnnotation tnm : tnmAnnotations ) {
					final IdentifiedAnnotation indiTnm = TnmPropertyUtil
							.createCoallescedProperty( jcas, Collections.singletonList( tnm ) );
					Finding f = (Finding)FHIRRegistry.getInstance().getResource( indiTnm );
					if ( f == null ) {
						f = createFinding( indiTnm );
					}
					stage.addAssessment( f );
					//stage.setStringExtension(Stage.TNM_PRIMARY_TUMOR,cTAKESUtils.getConceptURI(st.getSize()));
				}
			}
			final IdentifiedAnnotation fullTnmAnnotation = TnmPropertyUtil
					.createCoallescedProperty( jcas, tnmAnnotations );
			stage.addExtension( FHIRUtils.createMentionExtension( fullTnmAnnotation.getCoveredText(),
					fullTnmAnnotation.getBegin(), fullTnmAnnotation.getEnd() ) );
		}
		// register
		//FHIRRegistry.getInstance().addResource(stage,st);
		return stage;
	}


}
