package db;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.io.fs.FileUtils;
import org.neo4j.ogm.service.Components;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.healthnlp.deepphe.graph.GraphObjectFactory;

public class Neo4JOgmWrapper {

	
	  private static Neo4JOgmWrapper factory = new Neo4JOgmWrapper();
	  private static  SessionFactory sessionFactory;
	  /** HARDWIRE ALERT - replace with something more configurable **/
	  private static final String GRAPHDB_URI="http://localhost:7474";

	  public static Neo4JOgmWrapper getInstance() {
		  return factory;
	  }	
	
	  private Neo4JOgmWrapper() {
	
		  Components.configuration()
		  .driverConfiguration()
		  .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
		  .setURI(GRAPHDB_URI);
		
		  sessionFactory = new SessionFactory("db"); // was GraphObjectFactory.POJO_PACKAGE);
	  }

	  public Session getNeo4JSession() {
		  return sessionFactory.openSession();
	  }	
	  
	 

}