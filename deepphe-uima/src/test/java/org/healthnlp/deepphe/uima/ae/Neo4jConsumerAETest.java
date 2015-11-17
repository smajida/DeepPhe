package org.healthnlp.deepphe.uima.ae;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ctakes.cancer.ae.XMIWriter;
import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Diagnosis;
import org.healthnlp.deepphe.fhir.Finding;
import org.healthnlp.deepphe.fhir.Medication;
import org.healthnlp.deepphe.fhir.Observation;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Procedure;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.ResourceFactory;
import org.healthnlp.deepphe.fhir.Stage;
import org.healthnlp.deepphe.util.TextUtils;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.UniqueFactory;
import org.neo4j.graphdb.index.UniqueFactory.UniqueNodeFactory;


public class Neo4jConsumerAE extends JCasAnnotator_ImplBase {
   private ResourceFactory resourceFactory;

   private GraphDatabaseService graphDb;

private UniqueNodeFactory patientFactory;

   public static final String PARAM_DBPATH = "DBPATH";
   public static final String PARAM_USERNAME = "USERNAME";
   public static final String PARAM_PASSWORD = "PASSWORD";


   @Override
   public void initialize( UimaContext aContext )
         throws ResourceInitializationException {
	   graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File((String)aContext.getConfigParameterValue( PARAM_DBPATH )));
	   
	   try ( Transaction tx = graphDb.beginTx() )
	   {
	       UniqueFactory.UniqueNodeFactory result = new UniqueFactory.UniqueNodeFactory( graphDb, "patients" )
	       {
	           @Override
	           protected void initialize( Node created, Map<String, Object> properties )
	           {
	               created.addLabel( Neo4jConstants.Nodes.Patient );
	               created.setProperty( "name", properties.get( "name" ) );
	           }
	       };
	       tx.success();
	       patientFactory = result;
	   }
   }


@Override
public void destroy() {
	graphDb.shutdown();
	super.destroy();
}


private static LinkedHashMap<String, Integer> patientNameIDMap = new LinkedHashMap<String, Integer>();
   private static int patientID = 0;

   @Override
   public void process( JCas jcas ) throws AnalysisEngineProcessException {

      try {

         for ( DocumentID docID : JCasUtil.select( jcas, DocumentID.class ) ) {
	            Patient patient = resourceFactory.getPatient( jcas );
	            String namedID = patient.getNameSimple();
				Report report = resourceFactory.getReport(jcas);
				report.setTitle(TextUtils.stripSuffix(docID.getDocumentID()));
				
				try ( Transaction tx = graphDb.beginTx() )
				{
					Node patientN = patientFactory.getOrCreate("name", namedID);
					Node documentN = getDocumentFromDB(namedID,report.getTitle());
				
					//clean out old document from DB
					if(documentN!=null){
						deleteDocument(documentN);
					}
					
					//create new doc node.
					documentN = graphDb.createNode(Neo4jConstants.Nodes.Document);
					documentN.setProperty("name", report.getTitle());
					documentN.setProperty("text", report.getTextSimple());
					//relate doc to patient
					Relationship relationship = documentN.createRelationshipTo(patientN,
							Neo4jConstants.Relationships.hasSubject);
				
					for(Diagnosis dx: report.getDiagnoses()){
						
						Node n = graphDb.createNode(Neo4jConstants.Nodes.Diagnosis);
						n.setProperty("name", dx.getDisplay());
						
						List<CodeableConcept> cc = dx.getBodySite();
						List<String> ccnames = new ArrayList<String>();
						for(CodeableConcept c:cc){
							ccnames.add(c.getText());
						}
						n.setProperty("bodySites", ccnames.toArray());
						
						Stage s = dx.getStage();
						String stage = s.getSummary().getText();
						n.setProperty("stage", stage);
					}
					for(Procedure p: report.getProcedures()){
						Node n = graphDb.createNode(Neo4jConstants.Nodes.Procedure);
						n.setProperty("name", p.getDisplay());
						
						List<CodeableConcept> cc = p.getBodySite();
						List<String> ccnames = new ArrayList<String>();
						for(CodeableConcept c:cc){
							ccnames.add(c.getText());
						}
						n.setProperty("bodySites", ccnames.toArray());
						
					}
					for(Finding dx: report.getFindings()){
						Node n = graphDb.createNode(Neo4jConstants.Nodes.Finding);
						n.setProperty("name", dx.getDisplay());
						

					}
					for(Observation p: report.getObservations()){
						Node n = graphDb.createNode(Neo4jConstants.Nodes.Observation);
						n.setProperty("name", p.getDisplay());
						
						CodeableConcept cc = p.getBodySite();
						n.setProperty("bodySites", new String[]{cc.getText()});
						
						p.getValueSimple();
					}
					for(Medication p: report.getMedications()){
						Node n = graphDb.createNode(Neo4jConstants.Nodes.Medication);
						n.setProperty("name", p.getDisplay());
					}

					tx.success();	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}


private void deleteDocument(Node documentN) {
	for(Relationship r:documentN.getRelationships()){
		r.delete();
	}
	documentN.delete();
}


private Node getDocumentFromDB(String patientName, String docName) {
	Result result = graphDb.execute( "match (d {name:" + docName + "})-[:hasSubject]->(p {name:" + patientName + "}) return d");
	
	while ( result.hasNext() ){
	        Map<String,Object> row = result.next();
	        for ( Entry<String,Object> column : row.entrySet() )
	        {
	           return (Node) column.getValue();
	        }
	}
	
	return null;
}

}
