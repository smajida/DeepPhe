package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Disease;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.fhir.summary.CancerSummary;
import org.healthnlp.deepphe.fhir.summary.CancerSummary.CancerPhenotype;
import org.healthnlp.deepphe.fhir.summary.PatientSummary;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary;
import org.healthnlp.deepphe.fhir.summary.TumorSummary.TumorPhenotype;
import org.healthnlp.deepphe.uima.fhir.PhenotypeResourceFactory;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.UniqueFactory;
import org.neo4j.graphdb.index.UniqueFactory.UniqueNodeFactory;

public class GraphDBPhenotypeConsumerAE extends JCasAnnotator_ImplBase {
	// private ResourceFactory resourceFactory;

	private GraphDatabaseService graph;

	private UniqueNodeFactory patientFactory;

	public static final String PARAM_DBPATH = "DBPATH";
	public static final String PARAM_USERNAME = "USERNAME";
	public static final String PARAM_PASSWORD = "PASSWORD";

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		initializeGraphDatabase((String)aContext.getConfigParameterValue(PARAM_DBPATH));
	}
	
	

	public void initializeGraphDatabase(String dbPath) throws ResourceInitializationException {
	
		graph = new GraphDatabaseFactory().newEmbeddedDatabase(new File(dbPath));

		if (!graph.isAvailable(500))
			throw new ResourceInitializationException(new Exception("Could not initialize neo4j connection for:"
					+ dbPath));
		
	}



	@Override
	public void destroy() {
		graph.shutdown();
		super.destroy();
	}

	private static LinkedHashMap<String, Integer> patientNameIDMap = new LinkedHashMap<String, Integer>();
	private static int patientID = 0;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		processPatient(graph,PhenotypeResourceFactory.loadPatient(jcas),PhenotypeResourceFactory.loadReports(jcas));
	}

	public void processPatient(GraphDatabaseService graphDb, Patient patient, List<Report> reports) {
		try (Transaction tx = graphDb.beginTx()) {

			String namedID = patient.getPatientName();
			Node patientN = getPatientFactory(graphDb).getOrCreate("name", namedID);
			
			
			for(Report report:reports){
				Node documentN = getDocumentFromDB(graphDb, namedID, report.getTitle());
	
				// clean out old document from DB
				if (documentN != null) {
					deleteDocument(documentN);
				}
	
				// create new doc node.
				documentN = graphDb.createNode(GraphDBConstants.Nodes.Document);
				documentN.setProperty("name", report.getTitle());
				documentN.setProperty("text", report.getReportText());
				// relate doc to patient
				documentN.createRelationshipTo(patientN, GraphDBConstants.Relationships.hasSubject);
	
				for (Summary s : report.getCompositionSummaries()) {
					if(s instanceof CancerSummary){						
						Node csn = graphDb.createNode(GraphDBConstants.Nodes.CancerSummary);
						documentN.createRelationshipTo(csn, GraphDBConstants.Relationships.hasCancerSummary);
						
						saveCancerSummary(graphDb, s, csn);
					}
					else if (s instanceof TumorSummary){
						TumorSummary ts = (TumorSummary)s;
						
						Node tsn = graphDb.createNode(GraphDBConstants.Nodes.TumorSummary);
						documentN.createRelationshipTo(tsn, GraphDBConstants.Relationships.hasTumorSummary);
						
						saveTumorSummary(graphDb, ts, tsn);
					}
					else if (s instanceof PatientSummary){
						PatientSummary ps = (PatientSummary)s;
						
						Node psn = graphDb.createNode(GraphDBConstants.Nodes.PatientSummary);
						documentN.createRelationshipTo(psn, GraphDBConstants.Relationships.hasPatientSummary);
						
						savePatientSummary(graphDb, ps, psn);
					}
				}
				
				for (Disease dx : report.getDiagnoses()) {
	
					Node n = graphDb.createNode(GraphDBConstants.Nodes.Diagnosis);
					n.setProperty("name", dx.getDisplayText());
	
					List<CodeableConcept> cc = dx.getBodySite();
					List<String> ccnames = new ArrayList<String>();
					for (CodeableConcept c : cc) {
						ccnames.add(c.getText());
					}
					if(ccnames.size()>0)
						n.setProperty("bodySites", ccnames.toString());
	
					Stage s = dx.getStage();
					if(s!=null){
						String stage = s.getSummary().getText();
						n.setProperty("stage", stage);
					}
	
					documentN.createRelationshipTo(n, GraphDBConstants.Relationships.hasDiagnosis);
				}
				for (Procedure p : report.getProcedures()) {
					Node n = graphDb.createNode(GraphDBConstants.Nodes.Procedure);
					n.setProperty("name", p.getDisplayText());
	
					List<CodeableConcept> cc = p.getBodySite();
					List<String> ccnames = new ArrayList<String>();
					for (CodeableConcept c : cc) {
						ccnames.add(c.getText());
					}
					n.setProperty("bodySites", ccnames.toString());
	
					documentN.createRelationshipTo(n, GraphDBConstants.Relationships.hasProcedure);
				}
				for (Finding dx : report.getFindings()) {
					Node n = graphDb.createNode(GraphDBConstants.Nodes.Finding);
					n.setProperty("name", dx.getDisplayText());
	
					documentN.createRelationshipTo(n, GraphDBConstants.Relationships.hasFinding);
				}
				for (Observation p : report.getObservations()) {
					Node n = graphDb.createNode(GraphDBConstants.Nodes.Observation);
					n.setProperty("name", p.getDisplayText());
					n.setProperty("value", p.getObservationValue());
					CodeableConcept cc = p.getBodySite();
					n.setProperty("bodySites", new String[] { cc.getText() }.toString());
	
					documentN.createRelationshipTo(n, GraphDBConstants.Relationships.hasObservation);
				}
				for (Medication p : report.getMedications()) {
					Node n = graphDb.createNode(GraphDBConstants.Nodes.Medication);
					n.setProperty("name", p.getDisplayText());
	
					documentN.createRelationshipTo(n, GraphDBConstants.Relationships.hasMedication);
				}
			}

			tx.success();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void saveCancerSummary(GraphDatabaseService graphDb, Summary s, Node csn) {
		CancerSummary cs = (CancerSummary)s;
		
		csn.setProperty("name",cs.getDisplayText());
		csn.setProperty("resourceIdentifier",cs.getResourceIdentifier());
		csn.setProperty("summaryText", cs.getSummaryText());
		csn.setProperty("conceptURI", cs.getConceptURI().toString());
		
		saveCodeableConcepts(csn, "bodySites", cs.getBodySite());
		saveCodeableConcepts(csn, "outcomes", cs.getOutcomes());
		saveCodeableConcepts(csn, "treatments", cs.getTreatments());
		
		for(CancerPhenotype cp:cs.getPhenotypes()){
			Node cpn = graphDb.createNode(GraphDBConstants.Nodes.CancerPhenotype);
			csn.createRelationshipTo(cpn, GraphDBConstants.Relationships.hasCancerPhenotype);
			
			cpn.setProperty("name",cp.getDisplayText());
			cpn.setProperty("resourceIdentifier",cp.getResourceIdentifier());
			cpn.setProperty("summaryText", cp.getSummaryText());
			cpn.setProperty("conceptURI", cp.getConceptURI().toString());
			
			saveCodeableConcept(cpn, "cancerStage", cp.getCancerStage());
			saveCodeableConcept(cpn, "cancerType", cp.getCancerType());
			saveCodeableConcept(cpn, "distantMetastasisClassification", cp.getDistantMetastasisClassification());
			saveCodeableConcepts(cpn, "manifestations", cp.getManifestations());
			saveCodeableConcept(cpn, "primaryTumorClassification", cp.getPrimaryTumorClassification());
			saveCodeableConcept(cpn, "regionalLympNodeClassification", cp.getRegionalLymphNodeClassification());
			saveCodeableConcept(cpn, "tumorExtent", cp.getTumorExtent());

			
		}
		
		for(TumorSummary ts:cs.getTumors()){
			Node tsn = graphDb.createNode(GraphDBConstants.Nodes.TumorSummary);
			csn.createRelationshipTo(tsn, GraphDBConstants.Relationships.hasTumorSummary);
			
			saveTumorSummary(graphDb, ts, tsn);
		}
	}

	private void savePatientSummary(GraphDatabaseService graphDb, PatientSummary ps, Node psn) {
		
		psn.setProperty("name",ps.getDisplayText());
		psn.setProperty("resourceIdentifier",ps.getResourceIdentifier());
		psn.setProperty("summaryText", ps.getSummaryText());
		psn.setProperty("conceptURI", ps.getConceptURI().toString());
		
		saveCodeableConcepts(psn, "exposure", ps.getExposure());
		saveCodeableConcepts(psn, "germlineSequenceVariant",ps.getGermlineSequenceVariant());
		saveCodeableConcepts(psn, "outcomes", ps.getOutcomes());
		
	}

	private void saveTumorSummary(GraphDatabaseService graphDb, TumorSummary ts, Node tsn) {
		tsn.setProperty("name",ts.getDisplayText());
		tsn.setProperty("resourceIdentifier",ts.getResourceIdentifier());
		tsn.setProperty("summaryText", ts.getSummaryText());
		tsn.setProperty("conceptURI", ts.getConceptURI().toString());
		
		
		saveCodeableConcepts(tsn, "bodySites", ts.getBodySite());
		saveCodeableConcepts(tsn, "outcomes", ts.getOutcomes());
		saveCodeableConcepts(tsn, "treatments", ts.getTreatments());
		saveCodeableConcepts(tsn, "tumorSequenceVariants", ts.getTumorSequenceVarients());
		saveCodeableConcept(tsn, "tumorType", ts.getTumorType());
		
		TumorPhenotype tp = ts.getPhenotype();
		Node tpn = graphDb.createNode(GraphDBConstants.Nodes.TumorPhenotype);
		tsn.createRelationshipTo(tpn, GraphDBConstants.Relationships.hasTumorPhenotype);

		tpn.setProperty("name",tp.getDisplayText());
		tpn.setProperty("resourceIdentifier",tp.getResourceIdentifier());
		tpn.setProperty("summaryText", tp.getSummaryText());
		tpn.setProperty("conceptURI", tp.getConceptURI().toString());
		
		saveCodeableConcepts(tpn, "histologicTypes", tp.getHistologicTypes());
		saveCodeableConcepts(tpn, "manifestations", tp.getManifestations());
		saveCodeableConcepts(tpn, "tumorExtent", tp.getTumorExtent());
	}
	
	private void saveCodeableConcepts(Node n, String propertyName, List<CodeableConcept> cc){
		
		List<String> ccnames = new ArrayList<String>();
		for (CodeableConcept c : cc) {
			ccnames.add(c.getText());
		}
		if(ccnames.size()>0)
			n.setProperty(propertyName, ccnames.toString());
	}

	private void saveCodeableConcept(Node n, String propertyName, CodeableConcept cc){
		if(cc!=null)
			n.setProperty(propertyName, cc.getText());
	}


	private UniqueNodeFactory getPatientFactory(GraphDatabaseService graphDB) throws Exception {

		if (patientFactory == null) {
			try (Transaction tx = graphDB.beginTx()) {
				UniqueFactory.UniqueNodeFactory result = new UniqueFactory.UniqueNodeFactory(graphDB, "patients") {
					@Override
					protected void initialize(Node created, Map<String, Object> properties) {
						created.addLabel(GraphDBConstants.Nodes.Patient);
						created.setProperty("name", properties.get("name"));
					}
				};
				tx.success();
				patientFactory = result;
			}

			if (patientFactory == null)
				throw new Exception("Could not initialize patient factory");
		}

		return patientFactory;
	}

	private void deleteDocument(Node documentN) {
		for (Relationship r : documentN.getRelationships()) {
			r.delete();
		}
		documentN.delete();
	}

	private Node getDocumentFromDB(GraphDatabaseService graphDb, String patientName, String docName) {
		Result result = graphDb.execute("match (d {name:'" + docName + "'})-[:hasSubject]->(p {name:'" + patientName
				+ "'}) return d");

		while (result.hasNext()) {
			Map<String, Object> row = result.next();
			for (Entry<String, Object> column : row.entrySet()) {
				return (Node) column.getValue();
			}
		}

		return null;
	}

}
