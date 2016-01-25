package org.healthnlp.deepphe.util;

import java.util.HashMap;
import java.util.Map;

import org.healthnlp.deepphe.fhir.Element;

public class FHIRRegistry {
	private Map<Object,Element> registry;
	private static FHIRRegistry instance;
	
	private FHIRRegistry(){
		registry = new HashMap<Object, Element>();
	}
	
	public static FHIRRegistry getInstance(){
		if(instance == null)
			instance = new FHIRRegistry();
		return instance;
	}
	
	public Element getResource(Object id){
		return registry.get(id);
	}
	
	public int getSize(){
		return registry.size();
	}
	
	public void clear(){
		registry.clear();
	}
	
	public void addResource(Element e){
		registry.put(e.getResourceIdentifier(),e);
	}
	
	public void addResource(Element e, Object id){
		registry.put(id,e);
		addResource(e);
	}
	
	public boolean hasElement(Object id){
		return registry.containsKey(id);
	}
}
