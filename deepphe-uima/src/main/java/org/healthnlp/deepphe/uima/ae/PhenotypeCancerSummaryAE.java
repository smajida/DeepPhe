package org.healthnlp.deepphe.uima.ae;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.fact.DefaultFactList;
import org.healthnlp.deepphe.fhir.fact.Fact;
import org.healthnlp.deepphe.fhir.fact.FactList;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.uima.drools.DroolsEngine;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.healthnlp.deepphe.util.FHIRConstants;
import org.kie.api.runtime.KieSession;

import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.ILogicExpression;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IOntologyException;
import edu.pitt.dbmi.nlp.noble.ontology.IRestriction;
import edu.pitt.dbmi.nlp.noble.ontology.owl.OOntology;

/**
 * this is Rule based phenotype cancer summary AE
 * it takes Composition Cancer/Tumor and Patient Summary objects and creates
 * a combined phenotype summary object
 * @author tseytlin
 *
 */

public class PhenotypeCancerSummaryAE extends JCasAnnotator_ImplBase {
	public static final String PARAM_ONTOLOGY_PATH = "ONTOLOGY_PATH";
	private IOntology ontology;
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			ontology = OOntology.loadOntology((String) aContext.getConfigParameterValue(PARAM_ONTOLOGY_PATH));
		} catch (IOntologyException e) {
			throw new ResourceInitializationException(e);
		}
	}
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		// for now, lets assume there is only one cancer summary
		PatientSummary patientSummary = new PatientSummary();
		patientSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);
		
		CancerSummary cancerSummary =  new CancerSummary();
		cancerSummary.setAnnotationType(FHIRConstants.ANNOTATION_TYPE_RECORD);
		
		// record to load into drools
		MedicalRecord record = new MedicalRecord();
		record.setPatient(PhenotypeResourceFactory.loadPatient(jcas));
		record.setPatientSummary(patientSummary);
		record.setCancerSummary(cancerSummary);
		
		// merte stuff around
		for(Report report: PhenotypeResourceFactory.loadReports(jcas)){

			record.addReport(report);
			PatientSummary p = report.getPatientSummary();
			if(p != null && patientSummary.isAppendable(p)){
				patientSummary.append(p);
			}
			
			//append cancer summary
			for(CancerSummary cs: report.getCancerSummaries()){
				if(cancerSummary.isAppendable(cs)){
					cancerSummary.append(cs);
				}else{
					//TODO: well we have another cancer summary, will ignore it for now
				}
			}
			
			//append tumor summaries that are by themselves
			for(TumorSummary ts: report.getTumorSummaries()){
				cancerSummary.append(ts);
			}

		}
		// check ancestors
		checkAncestors(record.getRecordLevelFacts());

		for(Fact f: record.getReportLevelFacts()){
			if(f.getAncestors().isEmpty())
				System.err.println(f.getCategory()+" "+f.getName()+" "+f.getUri());
			else
				System.out.println(f.getCategory()+" "+f.getName()+" "+f.getUri()+" "+f.getAncestors());
		}
		
		
	
		
		
		//long stT = System.currentTimeMillis();
		//Olga: for TMN and Stage testing empty *Classification and Cancer stage
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_CANCER_STAGE);
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_T_CLASSIFICATION);
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_N_CLASSIFICATION);
		record.getCancerSummary().getPhenotype().clearFactList(FHIRConstants.HAS_M_CLASSIFICATION);
		
		//insert record into drools
		DroolsEngine de = new DroolsEngine();
		KieSession droolsSession = null;
		try {
			droolsSession = de.getSession();
			//droolsSession.addEventListener( new DebugAgendaEventListener() );

			droolsSession.insert(record);					
			for(Fact f: record.getReportLevelFacts()){
				System.out.println(f.getInfo());
				droolsSession.insert(f);
			}		
			droolsSession.fireAllRules();
			droolsSession.dispose();

			//System.out.println("DROOLS TIME: "+(System.currentTimeMillis() - stT)/1000+"  sec");
			
			System.out.println("RECORD Summary: "+record.getSummaryText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		//this is where you save your work back to CAS

		PhenotypeResourceFactory.saveMedicalRecord(record, jcas);
		
	}
	
	public void checkAncestors(Collection<Fact> facts){
		org.healthnlp.deepphe.uima.fhir.OntologyUtils ou = new org.healthnlp.deepphe.uima.fhir.OntologyUtils(ontology);
		for(Fact f:	facts){
			if(f.getAncestors().isEmpty())
				ou.addAncestors(f);
		}
	}
	
	
	/**
	 * load template based on the ontology
	 * @param summary
	 */
	private void loadTemplate(Summary summary){
		IClass summaryClass = ontology.getClass(""+summary.getConceptURI());
		if(summaryClass != null){
			// see if there is a more specific
			for(IClass cls: summaryClass.getDirectSubClasses()){
				summaryClass = cls;
				break;
			}
			
			// now lets pull all of the properties
			for(Object o: summaryClass.getNecessaryRestrictions()){
				if(o instanceof IRestriction){
					IRestriction r = (IRestriction) o;
					if(isSummarizableRestriction(r)){
						if(!summary.getContent().containsKey(r.getProperty().getName())){
							FactList facts = new DefaultFactList();
							facts.setCategory(r.getProperty().getName());
							facts.setTypes(getClassNames(r.getParameter()));
							summary.getContent().put(r.getProperty().getName(),facts);
						}else{
							for(String type: getClassNames(r.getParameter())){
								summary.getContent().get(r.getProperty().getName()).getTypes().add(type);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * should this restriction be used for summarization
	 * @param r
	 * @return
	 */
	private boolean isSummarizableRestriction(IRestriction r){
		IClass bs = ontology.getClass(FHIRConstants.BODY_SITE);
		IClass event = ontology.getClass(FHIRConstants.EVENT);
	
		if(r.getProperty().isObjectProperty()){
			for(String name : getClassNames(r.getParameter())){
				IClass cls = ontology.getClass(name);
				return cls.hasSuperClass(event) || cls.equals(bs) || cls.hasSuperClass(bs);
			}
		}
		return false;
	}
	
	private List<String> getClassNames(ILogicExpression exp){
		List<String> list = new ArrayList<String>();
		for(Object o: exp){
			if(o instanceof IClass){
				list.add(((IClass)o).getName());
			}
		}
		return list;
	}

}
