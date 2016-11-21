package authoring.model.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import authoring.model.TowerData;

import com.google.gson.Gson;

public class DeserializeJSON {

	private Gson gson;
	private Scanner scanner;
	private String fileText;
	String fileLoc = "src/resources/";

	public Object DeserializeFromFile(String filepath, Class cls){
		
		try {
			scanner = new Scanner( new File(fileLoc+filepath) );
			fileText = scanner.useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			scanner.close();
		}
		
		gson = new Gson();
		
		return (gson.fromJson(fileText, cls));
		
	}

}
