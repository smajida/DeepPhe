package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
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
import org.healthnlp.deepphe.uima.fhir.DocumentResourceFactory;
import org.healthnlp.deepphe.util.TextUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.UniqueFactory;
import org.neo4j.graphdb.index.UniqueFactory.UniqueNodeFactory;

public class GraphDBDocumentConsumerAE extends JCasAnnotator_ImplBase {
	// private ResourceFactory resourceFactory;

	private GraphDatabaseService graph;

	private UniqueNodeFactory patientFactory;

	public static final String PARAM_DBPATH = "DBPATH";
	public static final String PARAM_USERNAME = "USERNAME";
	public static final String PARAM_PASSWORD = "PASSWORD";

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		String dbPath = (String) aContext.getConfigParameterValue(PARAM_DBPATH);
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

		try {

			for (DocumentID docID : JCasUtil.select(jcas, DocumentID.class)) {
				Patient patient = DocumentResourceFactory.getPatient(jcas);

				Report report = DocumentResourceFactory.getReport(jcas);
				report.setTitle(TextUtils.stripSuffix(docID.getDocumentID()));

				processReport(graph, patient, report);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void processReport(GraphDatabaseService graphDb, Patient patient, Report report) {
		try (Transaction tx = graphDb.beginTx()) {

			String namedID = patient.getPatientName();
			Node patientN = getPatientFactory(graphDb).getOrCreate("name", namedID);
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

			for (Disease dx : report.getDiagnoses()) {

				Node n = graphDb.createNode(GraphDBConstants.Nodes.Diagnosis);
				n.setProperty("name", dx.getDisplayText());

				List<CodeableConcept> cc = dx.getBodySite();
				List<String> ccnames = new ArrayList<String>();
				for (CodeableConcept c : cc) {
					ccnames.add(c.getText());
				}
				n.setProperty("bodySites", ccnames.toArray().toString());

				Stage s = dx.getStage();
				String stage = s.getSummary().getText();
				n.setProperty("stage", stage);

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
				n.setProperty("bodySites", ccnames.toArray().toString());

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

			tx.success();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
