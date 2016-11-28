package authoring.model.serialization;


import java.io.PrintWriter;

import com.google.gson.*;

public class JSONSerializer {
	private static final String DEFAULT_FILE_LOCATION = "scr/SerializedFiles";
	
	private Gson gson;
	
	public JsonElement serialize(Object obj){

		//overly verbose for now, but easily changeable later, for whether we want a JsonElement or String
		gson = new Gson();
		String jsonString = gson.toJson(obj);
		JsonElement json = gson.toJsonTree(obj);
		System.out.println(json);
		return json;
		
			
	}
	
	public void serializeToFile(Object obj, String fileName){
		
		String str = serialize(obj).toString();
		
		try{
			PrintWriter out = new PrintWriter(DEFAULT_FILE_LOCATION+fileName);
			out.println(str);
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


}