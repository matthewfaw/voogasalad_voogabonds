package authoring.model.serialization;


import java.io.PrintWriter;

import com.google.gson.*;

public class SerializeJSON {
	
	private Gson gson;
	
	public JsonElement Serialize(Object obj){

		//overly verbose for now, but easily changeable later, for whether we want a JsonElement or String
		gson = new Gson();
		String jsonString = gson.toJson(obj);
		JsonElement json = gson.toJsonTree(obj);
		System.out.println(json);
		return json;
		
			
	}
	
	public void SerializeToFile(Object obj, String fileName){
		
		String fileLoc = "src/SerializedFiles/";
		
		String str = Serialize(obj).toString();
		
		try{
			PrintWriter out = new PrintWriter(fileLoc+fileName);
			out.println(str);
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


}
