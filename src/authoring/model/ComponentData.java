package authoring.model;

import java.util.AbstractMap;
import java.util.HashMap;

/**
 * 
 * @author owenchung and alanguo
 *
 */

public class ComponentData {
	private AbstractMap<String, String> myFields = new HashMap<String, String>();
	private String myComponentName;
	
	public void addField(String fieldName, String fieldValue){
		myFields.put(fieldName, fieldValue);
	}

	public String getComponentName() {
		return myComponentName;
	}

	public void setComponentName(String myComponentName) {
		this.myComponentName = myComponentName;
	}

	public AbstractMap<String, String> getFields() {
	    return myFields;
	}
}
