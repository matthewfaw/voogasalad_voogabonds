package authoring.model;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class AttributeFetcher {

	public static Map<String, List<String>> componentAttributeMap = new HashMap<String, List<String>>();
	public static List<String> componentList;
	public static List<String> attributeList;
	//src/engine/model/components
	private static final String PACKAGE = "engine.model.components.";
	//engine.model.components.
	private static final String PATH = "src/engine/model/components";

	public static void fetch(){
		componentList = new ArrayList<String>();
		File dir = new File(PATH);

		for (int i = 0; i<dir.listFiles().length; i++){

			attributeList = new ArrayList<String>();

			try{
				String tempString = PACKAGE + dir.listFiles()[i].getName();
				tempString = tempString.substring(0, tempString.length()-5);
				Class<?> cls = Class.forName(tempString);
				String newCompName = tempString.substring(PACKAGE.length(), tempString.length());
				componentList.add(newCompName);
				
				//if field does not have custom annotation, add to map
					for (Field f : cls.getDeclaredFields()){
					if (!f.isAnnotationPresent(Hide.class)){	
						attributeList.add(fieldManipulator(f));
					}
				}
				componentAttributeMap.put(newCompName, attributeList);
			}
			catch(ClassNotFoundException | SecurityException | IllegalArgumentException E){
				E.printStackTrace();
			}
		}

	}
	
	private static String fieldManipulator(Field f){	
		String fieldString = f.toString();
		fieldString = fieldString.substring(fieldString.lastIndexOf(".")+1, fieldString.length());
		return fieldString;
	}

	public static List<String> getComponentList(){
		return componentList;
	}

	public static List<String> getComponentAttributeList(String component){
		return componentAttributeMap.get(component);
	}

}
