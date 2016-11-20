package authoring.model.serialization;


import java.io.PrintWriter;

import com.google.gson.*;

public class SerializeJSON {
	
	private Gson gson;
	
	public String Serialize(Object obj){

		//overly verbose for now, but easily changeable later, for whether we want a JsonElement or String
		gson = new Gson();
		String json = gson.toJson(obj);
		JsonElement a = gson.toJsonTree(obj);
		System.out.println(a);
		return a.toString();
		
			
	}
	
	public void SerializeToFile(Object obj, String fileName){
		
		String fileLoc = "src/resources/";
		
		String str = Serialize(obj);
		
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
