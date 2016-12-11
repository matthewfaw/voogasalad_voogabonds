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

	public void fetch() throws ClassNotFoundException {
		componentList = new ArrayList<String>();
//		File dir = new File(PATH);
		List<String> fileList = new ArrayList<String>();
		FileRetriever fr = new FileRetriever(PACKAGE);
		fileList = fr.getFileNames("/");
		

		for (String fileName : fileList){

			attributeList = new ArrayList<String>();

			String tempString = fileName;
			tempString = fileName;
			index = tempString.indexOf(EXTENSION);
			tempString = tempString.substring(0, index);
			tempString = tempString.replace('/','.');
			try{
				Class<?> cls = Class.forName(tempString);
				String newCompName = tempString.substring(PACKAGE.length(), tempString.length());
				newCompName = newCompName.substring(1);
				String componentName = separateCapitalizedWords(newCompName);
				componentList.add(componentName);
				
				//if field does not have custom annotation, add to map
					for (Field f : cls.getDeclaredFields()){
					if (!f.isAnnotationPresent(Hide.class)){	
						attributeList.add(fieldManipulator(f));
					}
				}
				componentAttributeMap.put(componentName, attributeList);
			}
			catch(ClassNotFoundException | SecurityException | IllegalArgumentException e){
				throw new ClassNotFoundException(String.format("No such class: %s", tempString));
			}
		}

	}
	
	private String separateCapitalizedWords(String smooshed) {
	    return smooshed.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
	}
	
	private String fieldManipulator(Field f){	
		String fieldString = f.toString();
		fieldString = fieldString.substring(fieldString.lastIndexOf(".")+1, fieldString.length());
		// Assuming all fields start with 'my'
		fieldString = fieldString.substring(2);
		String fieldStringSpaced = this.separateCapitalizedWords(fieldString);
		return fieldStringSpaced;
	}

	public List<String> getComponentList(){
		return componentList;
	}

	public List<String> getComponentAttributeList(String component){
		return componentAttributeMap.get(component);
	}

}
