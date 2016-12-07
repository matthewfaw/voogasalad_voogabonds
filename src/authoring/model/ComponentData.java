package authoring.model;

import java.util.AbstractMap;
import java.util.HashMap;

public class ComponentData {
	AbstractMap<String, String> myFields = new HashMap<String, String>();
	
	public void addField(String fieldName, String fieldValue){
		myFields.put(fieldName, fieldValue);
	}
}
