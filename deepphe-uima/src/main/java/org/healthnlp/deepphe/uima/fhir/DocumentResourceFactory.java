package org.healthnlp.deepphe.uima.fhir;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.IResource;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import org.apache.ctakes.cancer.concept.instance.ConceptInstance;
import org.apache.ctakes.cancer.concept.instance.ConceptInstanceUtil;
import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.size.SizePropertyUtil;
import org.apache.ctakes.cancer.phenotype.tnm.TnmPropertyUtil;
import org.apache.ctakes.cancer.type.textsem.SizeMeasurement;
import org.apache.ctakes.core.util.DocumentIDAnnotationUtil;
import org.apache.ctakes.dictionary.lookup2.ontology.OwlConnectionFactory;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.healthnlp.deepphe.fhir.*;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.healthnlp.deepphe.util.FHIRRegistry;
import org.healthnlp.deepphe.util.FHIRUtils;
import org.healthnlp.deepphe.util.TextUtils;
import org.hl7.fhir.instance.model.BodySite;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStageComponent;
import org.hl7.fhir.instance.model.Procedure.ProcedureStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



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

		// now find all anatomical site found in a report
		getAnatomicalSite( cas ).forEach( r::addReportElement );

		// now find all observations found in a report
		getObservations( cas ).forEach( r::addReportElement );
		
		// now find all observations found in a report
		getFindings( cas ).forEach( r::addReportElement );
		
		// find all procedures mentioned in each report
		getProcedures( cas ).forEach( r::addReportElement );
		
		// now find all observations found in a report
		getMedications( cas ).forEach( r::addReportElement );
		
		// now find all primary disease that are found in a report
		getDiagnoses( cas ).forEach( r::addReportElement );
		
		
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
	static private <T extends Element> List<T> getElementList( final JCas jcas, final URI uri,
																				  final Function<ConceptInstance, ? extends T> mapper ) {
		return ConceptInstanceUtil.getBranchConceptInstanceStream( jcas, uri.toString() )
				.filter( t -> !t.isNegated() )
				.map( mapper )
				.collect( Collectors.toList() );
	}


	public static List<Procedure> getProcedures( JCas cas ) {
		return getElementList( cas, FHIRConstants.PROCEDURE_URI, DocumentResourceFactory::createProcedure );
	}
	public static List<Disease> getDiagnoses( JCas cas ) {
		return getElementList( cas, FHIRConstants.DIAGNOSIS_URI, DocumentResourceFactory::createDiagnosis );
	}

	public static List<Medication> getMedications( JCas cas ) {
		return getElementList( cas, FHIRConstants.MEDICATION_URI, DocumentResourceFactory::createMedication );
	}

	public static List<Finding> getFindings( JCas cas ) {
		return getElementList( cas, FHIRConstants.FINDING_URI, DocumentResourceFactory::createFinding );
		// TNM and Stage should be under the #Finding URI, so there is no need to fetch them explicitly
//		// add individual T N M to list
//		ConceptInstanceUtil.getBranchConceptInstanceStream( cas, TnmPropertyUtil.getParentUri() )
//				.map( DocumentResourceFactory::createFinding )
//				.forEach( list::add );
//		// add Stage to list
//		ConceptInstanceUtil.getBranchConceptInstanceStream( cas, StagePropertyUtil.getParentUri() )
//				.map( DocumentResourceFactory::createFinding )
//				.forEach( list::add );
	}


	public static List<AnatomicalSite> getAnatomicalSite( JCas cas ) {
		return getElementList( cas, FHIRConstants.BODY_SITE_URI, DocumentResourceFactory::createAnatomicalSite );
	}

	public static List<Observation> getObservations( JCas cas ) {
		final List<Observation> list
				= getElementList( cas, FHIRConstants.OBSERVATION_URI, DocumentResourceFactory::createObservation );
		// add cancer size
		ConceptInstanceUtil.getBranchConceptInstanceStream( cas, SizePropertyUtil.getParentUri() )
				.map( DocumentResourceFactory::createObservation )
				.forEach( list::add );
		// add Receptor Status to list	- no need as it is under #Observation
//		ConceptInstanceUtil.getBranchConceptInstanceStream( cas, StatusPropertyUtil.getParentUri() )
//				.map( DocumentResourceFactory::createFinding )
//				.forEach( list::add );
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


	public static Disease createDiagnosis( final ConceptInstance conceptInstance ) {
		return load( new Disease(), conceptInstance );
	}

	/**
	 *
	 * @param anatomicalSite -
	 * @param conceptInstance -
	 * @return -
	 */
	public static AnatomicalSite load( AnatomicalSite anatomicalSite, final ConceptInstance conceptInstance ) {
		anatomicalSite.setCode( cTAKESUtils.getCodeableConcept( conceptInstance ) );

		// add language contexts
		cTAKESUtils.addLanguageContext( conceptInstance, anatomicalSite );
		
		
		// add mention text
		anatomicalSite.addExtension( cTAKESUtils.createMentionExtension( conceptInstance ) );
		
		// create identifier
		FHIRUtils.createIdentifier(anatomicalSite.addIdentifier(),anatomicalSite);
		
		// register
		FHIRRegistry.getInstance().addResource( anatomicalSite, conceptInstance );
		
		return anatomicalSite;
	}

	/**
	 *
	 * @param finding -
	 * @param conceptInstance -
	 * @return -
	 */
	public static Finding load( Finding finding, final ConceptInstance conceptInstance ) {
		finding.setCode( cTAKESUtils.getCodeableConcept( conceptInstance ) );

		cTAKESUtils.addLanguageContext( conceptInstance, finding );


		// see if we have annotations or maybe parent might have them
		IdentifiedAnnotation ia = conceptInstance.getIdentifiedAnnotation();
		if ( ia.getBegin() == 0 && ia.getEnd() == 0 ) {
			try {
				final IdentifiedAnnotation tnm
						= OwlOntologyConceptUtil.getAnnotationStreamByUriBranch( ia.getCAS().getJCas(),
						TnmPropertyUtil.getParentUri() )
						.filter( conceptInstance::equals )
						.findFirst().get();
				if ( tnm != null ) {
					ia.setBegin( tnm.getBegin() );
					ia.setEnd( tnm.getEnd() );
				}
			} catch (CASException e) {
				e.printStackTrace();
			}
		}
		
		// add mention text
		finding.addExtension( cTAKESUtils.createMentionExtension( conceptInstance ) );
		
		// create identifier
		FHIRUtils.createIdentifier(finding.addIdentifier(),finding);
	
		
		// register
		FHIRRegistry.getInstance().addResource( finding, conceptInstance );
		
		return finding;
	}
	
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param dx -
	 * @param conceptInstance -
	 * @return -
	 */
	public static Disease load( Disease dx, final ConceptInstance conceptInstance ) {
		// set some properties
		dx.setCode( cTAKESUtils.getCodeableConcept( conceptInstance ) );
		
		cTAKESUtils.addLanguageContext( conceptInstance, dx );
		
		// now lets take a look at the location of this disease
		for ( ConceptInstance ci : ConceptInstanceUtil.getLocations( conceptInstance ) ) {
			final IdentifiedAnnotation as = ci.getIdentifiedAnnotation();
			CodeableConcept location = dx.addBodySite();
			cTAKESUtils.setCodeableConcept( location, as );
			AnatomicalSite site = (AnatomicalSite) FHIRRegistry.getInstance().getResource(as);
			if ( site != null ) {
				FHIRUtils.addResourceReference( location, site );
			}
		}

		Collection<IdentifiedAnnotation> stages = cTAKESUtils.getCancerStages( conceptInstance.getIdentifiedAnnotation() );
		Collection<IdentifiedAnnotation> tnms = cTAKESUtils.getTnmClassifications( conceptInstance.getIdentifiedAnnotation());
		dx.setStage( createStage( stages, tnms ) );

		// add mention text
		dx.addExtension( cTAKESUtils.createMentionExtension( conceptInstance ) );
		
		// create identifier
		FHIRUtils.createIdentifier(dx.addIdentifier(),dx);
		
		// register
		FHIRRegistry.getInstance().addResource( dx, conceptInstance );
		
		return dx;
	}

	public static Medication createMedication( final ConceptInstance conceptInstance ) {
		return load( new Medication(), conceptInstance );
	}
	
	/**
	 * load medication
	 * @param md
	 * @param conceptInstance
	 * @return
	 */
	public static Medication load( Medication md, final ConceptInstance conceptInstance ) {
		md.setCode( cTAKESUtils.getCodeableConcept( conceptInstance ) );
		md.addExtension( cTAKESUtils.createMentionExtension( conceptInstance ) );
		cTAKESUtils.addLanguageContext( conceptInstance, md );
		// register
		FHIRRegistry.getInstance().addResource( md, conceptInstance );
		return md;
	}


	public static Observation createObservation( final ConceptInstance conceptInstance ) {
		return load( new Observation(), conceptInstance );
	}
	
	
	private static URI getResolvedURL(String name){
		try {
			IOntology ont = OwlConnectionFactory.getInstance().getDefaultOntology();
			if(ont != null){
				IResource r = ont.getResource(name);
				return r != null?r.getURI():URI.create(ont.getURI()+"#"+name);
			}
		} catch (FileNotFoundException e) {
			new Error(e);
		} catch (IOntologyException e) {
			new Error(e);
		}
		
		return URI.create(FHIRConstants.MODEL_CANCER_URL+"#"+name);
	}
	
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param ob -
	 * @param conceptInstance -
	 * @return -
	 */
	public static Observation load( Observation ob, final ConceptInstance conceptInstance ) {
		// set some properties
		ob.setCode( cTAKESUtils.getCodeableConcept( conceptInstance ) );

		cTAKESUtils.addLanguageContext( conceptInstance, ob );
		
		// see if there is an ordinal interpretation
		IdentifiedAnnotation interpretation = cTAKESUtils.getDegreeOf( conceptInstance.getIdentifiedAnnotation() );
		if(interpretation != null){
			ob.setInterpretation(cTAKESUtils.getCodeableConcept(interpretation));
		}

		// TODO - a lot of things got mucked up with the new "stick to ctakes" methods
		// TODO - what to do with this?  We need measurement and unit.  Can we just split(" ") ?
		// if cancer size, then use their value
		//conceptInstance.get
		
		//ConceptInstanceUtil.get(phenotype)
		SizeMeasurement num = cTAKESUtils.getSizeMeasurement( conceptInstance.getIdentifiedAnnotation() );
		if(num != null){
			ob.setCode( FHIRUtils.getCodeableConcept(getResolvedURL(FHIRConstants.TUMOR_SIZE)));
			ob.setValue(num.getValue(),num.getUnit());
		}
		

		// add mention text
		ob.addExtension( cTAKESUtils.createMentionExtension( conceptInstance ) );
		
		// add id
		FHIRUtils.createIdentifier(ob.addIdentifier(),ob);
		
		// register
		FHIRRegistry.getInstance().addResource( ob, conceptInstance );
		
		return ob;
	}


	public static Procedure createProcedure( final ConceptInstance conceptInstance ) {
		return load( new Procedure(), conceptInstance );
	}
	
	/**
	 * Initialize disease from a DiseaseDisorderMention in cTAKES typesystem
	 * @param pr -
	 * @param conceptInstance -
	 * @return -
	 */
	public static Procedure load( Procedure pr, final ConceptInstance conceptInstance ) {
		// set some properties
		pr.setCode( cTAKESUtils.getCodeableConcept( conceptInstance ) );
		pr.setStatus(ProcedureStatus.COMPLETED);

		cTAKESUtils.addLanguageContext( conceptInstance, pr );
		
		// now lets take a look at the location of this disease
		for ( ConceptInstance ci : ConceptInstanceUtil.getLocations( conceptInstance ) ) {
			final IdentifiedAnnotation as = ci.getIdentifiedAnnotation();
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
		pr.addExtension( cTAKESUtils.createMentionExtension( conceptInstance ) );
		
		FHIRUtils.createIdentifier(pr.addIdentifier(),pr);


		// register
		FHIRRegistry.getInstance().addResource( pr, conceptInstance );
		return pr;
	}


	//	public static Stage createStage(CancerStage st, TnmClassification tnm) {
	//		return load(new Stage(),st,tnm);
	//	}
	public static Stage createStage( final Collection<IdentifiedAnnotation> stages,
												final Collection<IdentifiedAnnotation> tnms ) {
		return load( new Stage(),stages, tnms );
	}


	public static AnatomicalSite createAnatomicalSite( final ConceptInstance conceptInstance ) {
		return load( new AnatomicalSite(), conceptInstance );
	}

	public static Finding createFinding( final ConceptInstance conceptInstance ) {
		return load( new Finding(), conceptInstance );
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
			final JCas jcas = stageAnnotations.stream().map( cTAKESUtils::getJcas ).findFirst().orElse( null );
			if ( jcas == null ) {
				LOGGER.error( "No Cas exists for Stage annotations" );
			} else {
				final IdentifiedAnnotation firstStageAnnotation = stageAnnotations.stream().findFirst().get();
				CodeableConcept c = cTAKESUtils.getCodeableConcept( firstStageAnnotation );
				c.setText( firstStageAnnotation.getCoveredText() );
				stage.setSummary( c );
				// add id to cancer stage
				Finding f = (Finding)FHIRRegistry.getInstance().getResource( firstStageAnnotation );
				if ( f != null ) {
					FHIRUtils.addResourceReference( c, f );
				}
				// add extension
				stage.addExtension(
						FHIRUtils.createMentionExtension( firstStageAnnotation.getCoveredText(),
								firstStageAnnotation.getBegin(), firstStageAnnotation.getEnd() ) );
			}
		} else {
			// for now just add a generic TNM
			if ( tnmAnnotations != null && !tnmAnnotations.isEmpty() ) {
				final JCas jcas = tnmAnnotations.stream().map( cTAKESUtils::getJcas ).findFirst().orElse( null );
				if ( jcas == null ) {
					LOGGER.error( "No Cas exists for TNM annotations" );
				} else {
					final IdentifiedAnnotation firstTnmAnnotation = tnmAnnotations.stream().findFirst().get();
					CodeableConcept c = cTAKESUtils.getCodeableConcept( firstTnmAnnotation );
					c.setText( firstTnmAnnotation.getCoveredText() );
					stage.setSummary( c );
				}
			}
		}
		// extract individual Stage levels if values are conflated
		if ( tnmAnnotations != null && !tnmAnnotations.isEmpty() ) {
			final JCas jcas = tnmAnnotations.stream().map( cTAKESUtils::getJcas ).findFirst().orElse( null );
			if ( jcas == null ) {
				LOGGER.error( "No Cas exists for TNM annotations" );
			} else {
				for ( IdentifiedAnnotation tnm : tnmAnnotations ) {
					Finding f = (Finding)FHIRRegistry.getInstance().getResource( tnm );
					if ( f == null ) {
						f = createFinding( new ConceptInstance( tnm ) );
					}
					stage.addAssessment( f );
					//stage.setStringExtension(Stage.TNM_PRIMARY_TUMOR,cTAKESUtils.getConceptURI(st.getSize()));
				}
			}
			final IdentifiedAnnotation firstTnmAnnotation = tnmAnnotations.stream().findFirst().get();
			stage.addExtension( FHIRUtils.createMentionExtension( firstTnmAnnotation.getCoveredText(),
					firstTnmAnnotation.getBegin(), firstTnmAnnotation.getEnd() ) );
		}
		// register
		//FHIRRegistry.getInstance().addResource(stage,st);
		return stage;
	}


}
