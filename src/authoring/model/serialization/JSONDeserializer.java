package authoring.model.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import authoring.model.TowerData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JSONDeserializer {

	private Gson gson;
	private Scanner scanner;
	private String fileText;
	//matthewfaw: after some revisions to scanner initialization, this string is no longer necessary
	// this should make our code more flexible
	//String fileLoc = "src/SerializedFiles/";
	
	public Object Deserialize(JsonElement json, Class cls){
		
		gson = new Gson();
		return (gson.fromJson(json, cls));
		
	}

	public Object deserializeFromFile(String filepath, Class cls){
		
		try {
			//The following lines look up a file relative to the project path
			URL url = getClass().getClassLoader().getResource(filepath);
			File file = new File(url.getPath());
			scanner = new Scanner(file);
			fileText = scanner.useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			//XXX matthewfaw to Tripp: PLEASE REMOVE THIS
			e.printStackTrace();
		} finally{
			scanner.close();
		}
		
		gson = new Gson();
		
		return (gson.fromJson(fileText, cls));
		
	}

}