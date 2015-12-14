package controllers;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.neo4jdemo;

public class Neo4JDemo extends Controller {

    
    public Result connect() {
    	
    	listAll();
    	String SERVER_ROOT_URI = "http://localhost:7474/";
    	 ClientConfig config = new ClientConfig();

	    Client client = ClientBuilder.newClient(config);
	    HttpAuthenticationFeature authFeature =
	            HttpAuthenticationFeature.basic("neo4j", "neo4jpass");
	         
	    client.register(authFeature);
	    
	    WebTarget target = client.target(SERVER_ROOT_URI);
	    
	    Response response = target.
	              request().
	              accept(MediaType.APPLICATION_JSON_TYPE).
	              get(Response.class);
    	System.out.println(response);
    	
    	Boolean isOK = false;
    	isOK = response.getStatus()==200;
        return ok(neo4jdemo.render(isOK));
    	
    }
    
    
    public void listAll() {
    	String SERVER_ROOT_URI = "http://localhost:7474/";
    	final String txUri = SERVER_ROOT_URI + "transaction/commit";
    	
    	ClientConfig config = new ClientConfig();
    	Client client = ClientBuilder.newClient(config);
	    HttpAuthenticationFeature authFeature =
	            HttpAuthenticationFeature.basic("neo4j", "neo4jpass");
	    
 	    WebTarget target = client.target(txUri);
 	    
 	    String query = "";
    	String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
    	
    	String bean =
    			target.request(MediaType.APPLICATION_JSON_TYPE)
    			    .post(Entity.entity(payload,MediaType.APPLICATION_JSON_TYPE),
    			        String.class);

    	System.out.println(bean);
  	
    }
    

}
