package authoring.model;

import java.util.AbstractMap;
import java.util.HashMap;

public class EntityData {
	private String myName;
	AbstractMap<String, ComponentData> myComponents = new HashMap<String, ComponentData>();
	
	public String getName(){
		return myName;
	}
	
	public void setName(String s){
		this.myName = s;
	}
	
	public void addComponent(String componentName, ComponentData comp){
		myComponents.put(componentName, comp);
	}
	
	public AbstractMap<String, ComponentData> getComponents(){
		return myComponents;
	}
}
