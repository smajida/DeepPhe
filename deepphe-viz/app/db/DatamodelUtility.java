package db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DatamodelUtility extends Neo4JRESTCaller{

	public DatamodelUtility(String serverRootURI, String username, String password) {
		super(serverRootURI, username, password);
	}
	
	public List<Patient> getPatients() throws JsonParseException, JsonMappingException, IOException{
		List<LinkedHashMap<String,Object>> rows = getNodesWithLabel("Patient");
		
		List<Patient> patientList = new ArrayList<Patient>();
		
		for(LinkedHashMap<String,Object> datamap:rows){
			Patient p = new Patient();
			patientList.add(p);
			p.setId((int) datamap.get("id"));
			p.setName((String) datamap.get("name"));
			p.setDocuments(new ArrayList<Document>());
			List<LinkedHashMap<String,Object>> docrows = getIncomingNodesWithRelationshipType(p.getId()+"", "hasSubject");
			
			for(LinkedHashMap<String,Object> docdatamap:docrows){
				Document d = new Document();
				d.setId((int) docdatamap.get("id"));
				d.setName((String) docdatamap.get("name"));
				d.setText((String)docdatamap.get("text"));
				
				d.setDiagnoses(new ArrayList<Diagnosis>());
				d.setProcedures(new ArrayList<Procedure>());
				d.setMedications(new ArrayList<Medication>());
				
				d.setSubject(p);
				p.getDocuments().add(d);
				
				List<LinkedHashMap<String,Object>> drows = getOutgoingNodesWithRelationshipType(d.getId()+"", "hasDiagnosis");
			
				for(LinkedHashMap<String,Object> dmap:drows){
					Diagnosis di = new Diagnosis();
					di.setId((int) dmap.get("id"));
					di.setName((String) dmap.get("name"));
					
					d.getDiagnoses().add(di);
				}
				
				drows = getOutgoingNodesWithRelationshipType(d.getId()+"", "hasProcedure");
				
				for(LinkedHashMap<String,Object> dmap:drows){
					Procedure di = new Procedure();
					di.setId((int) dmap.get("id"));
					di.setName((String) dmap.get("name"));
					
					d.getProcedures().add(di);
				}
				
				drows = getOutgoingNodesWithRelationshipType(d.getId()+"", "hasMedication");
				
				for(LinkedHashMap<String,Object> dmap:drows){
					Medication di = new Medication();
					di.setId((int) dmap.get("id"));
					di.setName((String) dmap.get("name"));
					
					d.getMedications().add(di);
				}
			}
		}
		
		return patientList;
	}
	
}
