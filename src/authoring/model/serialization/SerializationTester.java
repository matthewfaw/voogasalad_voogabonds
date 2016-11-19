package authoring.model.serialization;

import authoring.model.TowerData;

public class SerializationTester {
	
	private String fileName;
	
	public void Tester(){

		SerializeJSON ser = new SerializeJSON();
		// We should make a user prompt for when they want to save a game file, which will be set as fileName
		ser.SerializeToFile(new TowerData(), fileName);
		DeserializeJSON des = new DeserializeJSON();
		des.Deserialize("src/resources/SavedConfig.txt");
		
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

}
