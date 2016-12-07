package authoring.model;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import utility.FileRetriever;

public class AttributeFetcher {

	public Map<String, List<String>> componentAttributeMap = new HashMap<String, List<String>>();
	public List<String> componentList;
	public List<String> attributeList;
	private final String PACKAGE = "engine/model/components";
	private final String EXTENSION = ".class";
	private int index;

	public void fetch(){
		componentList = new ArrayList<String>();
//		File dir = new File(PATH);
		List<String> fileList = new ArrayList<String>();
		FileRetriever fr = new FileRetriever(PACKAGE);
		fileList = fr.getFileNames("/");
		

		for (String fileName : fileList){

			attributeList = new ArrayList<String>();

			try{
				String tempString = fileName;
				index = tempString.indexOf(EXTENSION);
				tempString = tempString.substring(0, index);
				tempString = tempString.replace('/','.');
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
	
	private String fieldManipulator(Field f){	
		String fieldString = f.toString();
		fieldString = fieldString.substring(fieldString.lastIndexOf(".")+1, fieldString.length());
		return fieldString;
	}

	public List<String> getComponentList(){
		return componentList;
	}

	public List<String> getComponentAttributeList(String component){
		return componentAttributeMap.get(component);
	}

}
