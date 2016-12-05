package authoring.model;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AttributeFetcher {

	public static Map<String, List<String>> componentAttributeMap = new HashMap<String, List<String>>();
	public static List<String> componentList;
	public static List<String> attributeList;

	public static void fetch(){
		
		componentList = new ArrayList<String>();
		File dir = new File("src/authoring/model");
		
		for (int i = 0; i<dir.listFiles().length; i++){

			attributeList = new ArrayList<String>();
			
			try{
				String initialPath = "authoring.model.";
				String tempString = initialPath + dir.listFiles()[i].getName();
				tempString = tempString.substring(0, tempString.length()-5);
				Class<?> cls = Class.forName(tempString);
				Constructor<?> cst = cls.getConstructor();
				Object instance = cst.newInstance();
				String newCompName = tempString.substring(initialPath.length(), tempString.length());
				componentList.add(newCompName);
				for (int j = 0; j<cls.getDeclaredFields().length; j++){
					
					//if it does have custom annotation, add to map
					String temp2 = cls.getDeclaredFields()[j].toString();
					temp2 = temp2.substring(temp2.lastIndexOf(".")+1, temp2.length()); //temp2.indexOf(newCompName)
					System.out.println(temp2);
					attributeList.add(temp2);
					
				}
				componentAttributeMap.put(newCompName, attributeList);
				System.out.println("Success");
			}
			catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException E){
				System.out.println("Failure");
				E.printStackTrace();
			}
		}

	}
	
	public static List<String> getComponentList(){
		return componentList;
	}
	
	public static List<String> getComponentAttributeList(String component){
		return componentAttributeMap.get(component);
	}

}
