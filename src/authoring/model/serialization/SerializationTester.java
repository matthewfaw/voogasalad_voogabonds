package authoring.model.serialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import authoring.model.TowerData;
import authoring.view.objects.FrontEndEnemy;

public class SerializationTester {
	
	private String fileName;
	private String fileLoc = "src/resources/";
	
	public void Tester(Object obj){
		fileName = "test.txt";
		List<Map<Integer, Integer>> list = new ArrayList<Map<Integer, Integer>>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(100, 10);
		map.put(20, 50);
		list.add(map);
		TowerData tow = new TowerData();
		tow.setBuyPrice(40);
		tow.setCollisionRadius(2);
		tow.setImagePath("imagepath");
		tow.setInitialLocations(list);
		tow.setMaxHealth(1000);
		tow.setMovementStrategy("fast as fuck");
		tow.setName("tower 1");
		tow.setSellPrice(100);
		
		obj = tow;
		SerializeJSON ser = new SerializeJSON();
		// We should make a user prompt for when they want to save a game file, which will be set as fileName
		ser.SerializeToFile(obj, fileName);
		DeserializeJSON des = new DeserializeJSON();
//		des.Deserialize(fileLoc + fileName);
		System.out.println(des.Deserialize(fileLoc + fileName));
		
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

}
