package db;

import org.neo4j.ogm.session.Session;
import java.util.Collections;
import java.util.Map;
import java.util.Iterator;

public class Neo4JUtils {
	
	public static String clearDb() {
		String query = "match n, optional match (n)-[r]-() delete n,r";
		Iterable<Map<String,Object>> result = Neo4JOgmWrapper.getInstance().getNeo4JSession()
				.query(query,Collections.EMPTY_MAP);
		return "Deleted";
	}

}