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
		   
   
   public static String createPatientQuery = "'{' \"statement\": \"create (p:Patient '{'name: \\\"{0}'\\\"'}') return id(p)\" '}' ";
   public static String createDocumentQuery = "'{' \"statement\": \"create (d:Document '{'name: \\\"{0}'\\\"'}') return id(d)\" '}' ";
   public static String createHasSubjectQuery = "'{' \"statement\": \"match (p:Patient),(d:Document) where p.name=\\\"{0}\\\" and d.name=\\\"{1}\\\" create (d)-[:hasSubject]->(p) return id(p);\"'}' ";
	
   public Result populate() {
	   
	    String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	   	String username = "neo4j";
	   	String password = "neo4jpass";
	   
	   	try {
	   		DataCreatorUtility caller = new DataCreatorUtility(SERVER_ROOT_URI, username, password);
	   		String p1 = createPatient("George");
	   		String p2 = createPatient("Harry");
	   		String p3 = createDocument("doc1");
	   		String p4 = createHasSubjectQuery("Harry","doc1");
	   		String[] allStatements = new String[]{p1,p2,p3,p4};
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
   
   public String createDocument(String name) {
	   	return formatQuery(createDocumentQuery,new Object[]{name});
   }
   
   public String createHasSubjectQuery(String p,String d) {
	   return formatQuery(createHasSubjectQuery,new Object[]{p,d});
   }
   
  private String formatQuery(String template,Object[] params) {
	  	MessageFormat form = new MessageFormat(template);
	  	String q = form.format(params);
	   	System.err.println(q);
	   	return q;	  
  }
 

}
