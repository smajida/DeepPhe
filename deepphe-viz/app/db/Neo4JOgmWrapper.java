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


	private SessionFactory sessionFactory;
	public Neo4JOgmWrapper() {
	
		sessionFactory = initializeGraphDatabase("foo"); //fix	
	
	}


	public SessionFactory initializeGraphDatabase(String dbPath)  {
		File f = new File(dbPath);
		if(f.exists()){
			try {
				FileUtils.deleteRecursively(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Components.configuration()
        .driverConfiguration()
        .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
        .setURI(f.toURI().toString());
		SessionFactory sf = new SessionFactory(GraphObjectFactory.POJO_PACKAGE);

		return sf;
	}


}