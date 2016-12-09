package authoring.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author owenchung and alanguo
 */

public class EntityData implements IReadableData {
	private String myName;
	List<ComponentData> myComponents;
	
	public EntityData()
	{
		myComponents = new ArrayList<ComponentData>();
	}
	
	public String getName(){
		return myName;
	}
	
	public void setName(String s){
		this.myName = s;
	}
	
	public void addComponent(ComponentData comp){
		myComponents.add(comp);
	}
	
	public List<ComponentData> getComponents(){
		return myComponents;
	}
}
