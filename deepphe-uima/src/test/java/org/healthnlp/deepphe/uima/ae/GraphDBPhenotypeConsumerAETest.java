package org.healthnlp.deepphe.uima.ae;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.Patient;
import org.healthnlp.deepphe.fhir.Report;
import org.healthnlp.deepphe.fhir.summary.Summary;
import org.healthnlp.deepphe.uima.fhir.FHIRObjectMocker;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;


public class GraphDBPhenotypeConsumerAETest {
	
   

	
	private static final String TEST_DB = "testdb";


	@BeforeClass
	public static void cleanupDB() throws IOException{
		Path p = FileSystems.getDefault().getPath(TEST_DB);
		FileUtils.deleteDirectory(p.toFile());
	}
	
   @Test
   public void initializationTest(){
	   try { 
		   String dbPath = TEST_DB + File.separator;
		   GraphDBPhenotypeConsumerAE ae = new GraphDBPhenotypeConsumerAE();
		   ae.initializeGraphDatabase(dbPath);
		   ae.destroy();
   
		} catch (ResourceInitializationException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
   }

   
   @Test
   public void processTest(){
	   String dbPath = TEST_DB + File.separator;
	   GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(new File(dbPath));
	   assertTrue("Database connection failed",db.isAvailable(500));

	   
	   GraphDBPhenotypeConsumerAE ae = new GraphDBPhenotypeConsumerAE();
	   
	   FHIRObjectMocker mocker = new FHIRObjectMocker();
	   
	   Patient patient = mocker.getPatient();
	   Report report = mocker.getReport();
	   
	   ae.processPatient(db, patient, Arrays.asList(new Report[]{report}));
	   
	   try ( Transaction ignored = db.beginTx();
			 Result reportResult = db.execute( "match (n {name: '" + report.getTitle() + "'}) return n" ))
		{
		   //check that report was saved
		   assertTrue("Document not created in database.",reportResult.hasNext());
		   
		   Iterator<Node> n_column = reportResult.columnAs( "n" );
		   for ( Node reportNode : IteratorUtil.asIterable( n_column ) )
		   {
			   
		       assertTrue("Document label not assigned",reportNode.hasLabel(GraphDBConstants.Nodes.Document));
		       
		       Node patientNode = null;
		       Node medicationNode = null;
		       Node obsNode = null;
		       Node findingNode = null;
		       Node diagNode = null;
		       Node procNode = null;
		       
		       Node csNode = null;
		       Node psNode = null;
		       Node tsNode = null;
		       
		       
		       for(Relationship r:reportNode.getRelationships(Direction.OUTGOING)){
		    	   Node n = r.getEndNode();
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.Patient)){
		    		   assertTrue("Relationship type is incorrect for document - patient",r.isType(GraphDBConstants.Relationships.hasSubject));
		    		   patientNode = n;
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.Diagnosis)){
		    		   assertTrue("Relationship type is incorrect for document - disease",r.isType(GraphDBConstants.Relationships.hasDiagnosis));
		    		   diagNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("Disease name property is incorrect",report.getDiagnoses().get(0).getDisplayText(),value);
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.Procedure)){
		    		   assertTrue("Relationship type is incorrect for document - procedure",r.isType(GraphDBConstants.Relationships.hasProcedure));
		    		   procNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("procedure name property is incorrect",report.getProcedures().get(0).getDisplayText(),value);
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.Finding)){
		    		   assertTrue("Relationship type is incorrect for document - finding",r.isType(GraphDBConstants.Relationships.hasFinding));
		    		   findingNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("finding name property is incorrect",report.getFindings().get(0).getDisplayText(),value);
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.Observation)){
		    		   assertTrue("Relationship type is incorrect for document - observation",r.isType(GraphDBConstants.Relationships.hasObservation));
		    		   obsNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("observation name property is incorrect",report.getObservations().get(0).getDisplayText(),value);
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.Medication)){
		    		   assertTrue("Relationship type is incorrect for document - medication",r.isType(GraphDBConstants.Relationships.hasMedication));
		    		   medicationNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("medication name property is incorrect",report.getMedications().get(0).getDisplayText(),value);
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.CancerSummary)){
		    		   assertTrue("Relationship type is incorrect for document - cancersummary",r.isType(GraphDBConstants.Relationships.hasCancerSummary));
		    		   csNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("CancerSummary name property is incorrect",((List<Summary>)report.getCompositionSummaries()).get(0).getDisplayText(),value);
		    	   }
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.TumorSummary)){
		    		   assertTrue("Relationship type is incorrect for document - tumorsummary",r.isType(GraphDBConstants.Relationships.hasTumorSummary));
		    		   tsNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("TumorSummary name property is incorrect",((List<Summary>)report.getCompositionSummaries()).get(1).getDisplayText(),value);
		    	   }		    	   
		    	   
		    	   if(n.hasLabel(GraphDBConstants.Nodes.PatientSummary)){
		    		   assertTrue("Relationship type is incorrect for document - patientsummary",r.isType(GraphDBConstants.Relationships.hasPatientSummary));
		    		   psNode = n;
		    		   Object value = n.getProperty("name");
		    		   assertEquals("PatientSummary name property is incorrect",((List<Summary>)report.getCompositionSummaries()).get(2).getDisplayText(),value);
		    	   }
		    	   
		    	   
		       }
		       
		       assertNotNull("Patient not linked to Document",patientNode);
		       assertNotNull("Disease not linked to Document",diagNode);
		       assertNotNull("Procedure not linked to Document",procNode);
		       assertNotNull("Finding not linked to Document",findingNode);
		       assertNotNull("Observation not linked to Document",obsNode);
		       assertNotNull("Medication not linked to Document",medicationNode);
		       
		       assertNotNull("CancerSummary not linked to Document",csNode);
		       assertNotNull("PatientSummary not linked to Document",psNode);
		       assertNotNull("TumorSummary not linked to Document",tsNode);

		   }
		}
	   
	  
	   db.shutdown();
   }

}
