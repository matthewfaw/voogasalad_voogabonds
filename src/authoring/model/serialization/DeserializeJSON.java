package authoring.model.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.gson.Gson;

public class DeserializeJSON {

	private Gson gson;
	private Scanner scanner;
	private String fileText;

	public Object Deserialize(String filepath){
		
		try {
			scanner = new Scanner( new File(filepath) );
			fileText = scanner.useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			scanner.close();
		}
		
		gson = new Gson();
		return (gson.fromJson(fileText, fileText.getClass().getGenericSuperclass()));
		
	}

}
