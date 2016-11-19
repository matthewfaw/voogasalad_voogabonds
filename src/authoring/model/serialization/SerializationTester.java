package authoring.model.serialization;

public class SerializationTester {
	
	private String fileName;
	private String fileLoc = "src/resources/";
	
	public void Tester(Object obj){

		SerializeJSON ser = new SerializeJSON();
		// We should make a user prompt for when they want to save a game file, which will be set as fileName
		ser.SerializeToFile(obj, fileName);
		DeserializeJSON des = new DeserializeJSON();
		des.Deserialize(fileLoc + fileName);
		
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

}
