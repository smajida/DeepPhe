package controllers;

import java.io.IOException;
import java.util.List;
import java.text.MessageFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import db.DataCreatorUtility;
import db.Patient;
import play.api.libs.json.JsValue;
import play.api.libs.json.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.neo4jdemo;

public class DummyData extends Controller {

   public static String queries ="{ \"statements\": " +
		  " [ {  \"statement\": \"create (p:Patient {name: \\\"Elena\\\"}) return id(p)\" }, "+
		   "  {  \"statement\": \"create (d:Document {name: \\\"ed1\\\"})  return id(d)\" }, " +
		   "  {  \"statement\": \"match (p:Patient),(d:Document) where p.name=\\\"Elena\\\" and d.name=\\\"ed1\\\" create (d)-[:hasSubject]->(p) return id(p);\"} " +
		    " ] }";
   
   public static String statementWrapper = "'{' \"statements\": [ {0} ] \" '}' ";
		   
   
   public static String createPatientQuery = "'{' \"statement\": \"create (p:Patient '{'name: \\\"{0}\\\"'}') return id(p)\" '}' ";
   public static String createDocumentQuery = "'{' \"statement\": \"create (d:Document '{'name: \\\"{0}\\\", date: \\\"{1}\\\" '}') return id(d)\" '}' ";
   public static String createHasSubjectQuery = "'{' \"statement\": \"match (p:Patient),(d:Document) where p.name=\\\"{0}\\\" and d.name=\\\"{1}\\\" create (d)-[:hasSubject]->(p) return id(p);\"'}' ";
   public static String createDiagnosisQuery = "'{' \"statement\": \"create (dx:Diagnosis '{'name: \\\"{0}\\\", bodySites: \\\"{1}\\\", Stage: \\\"{2}\\\" '}') return id(dx)\" '}' ";
   public static String createHasDiagnosisQuery = "'{' \"statement\": \"match (dx:Diagnosis),(d:Document) where dx.name=\\\"{1}\\\" and d.name=\\\"{0}\\\" create (d)-[:hasDiagnosis]->(dx) return id(d);\"'}' ";
   
   
   
   public Result populate() {
	   
	    String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	   	String username = "neo4j";
	   	String password = "neo4jpass";
	   
	   	try {
	   		DataCreatorUtility caller = new DataCreatorUtility(SERVER_ROOT_URI, username, password);
	   		String p1 = createPatient("George");
	   		String p2 = createPatient("Harry");
	   		String p3 = createDocument("doc1","2015-12-15 09:00");
	   		String p4 = createHasSubjectQuery("Harry","doc1");
	   		String p5 = createDiagnosisQuery("Malignant Breast Neoplasm","[left breast]","II");
	   		String p6 = createHasDiagnosisQuery("doc1","Malignant Breast Neoplasm");
	   		String[] allStatements = new String[]{p1,p2,p3,p4,p5,p6};
	   		String statementString = String.join(",",allStatements);
	   		// split with a comma
	   		Object[] params = new Object[]{statementString};
	   		String queries = MessageFormat.format(statementWrapper,params);
	   		String result=caller.executeQueries(queries);
	   		return ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ok(index.render(e.getMessage()));
		}
	    
    }
   
   public String createPatient(String name) {
	   return formatQuery(createPatientQuery,new Object[]{name});
   }
   
   public String createDocument(String name,String date) {
	   	return formatQuery(createDocumentQuery,new Object[]{name,date});
   }
   
   public String createHasSubjectQuery(String p,String d) {
	   return formatQuery(createHasSubjectQuery,new Object[]{p,d});
   }
   
   public String createDiagnosisQuery(String name,String sites,String stage) {
	   	return formatQuery(createDiagnosisQuery,new Object[]{name,sites,stage});
   }
   
   public String createHasDiagnosisQuery(String dx,String d) {
	   return formatQuery(createHasDiagnosisQuery,new Object[]{dx,d});
   }
   
   
  private String formatQuery(String template,Object[] params) {
	  	MessageFormat form = new MessageFormat(template);
	  	String q = form.format(params);
	   	System.err.println(q);
	   	return q;	  
  }
 

}
