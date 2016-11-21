package engine.controller;

import java.awt.Point;

import authoring.controller.TowerDataController;
import authoring.model.TowerData;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import authoring.model.serialization.DeserializeJSON;
import authoring.model.serialization.SerializeJSON;
import engine.model.game_environment.terrain.TerrainMap;

public class ApplicationController {
	/**
	 * XXX: For testing purposes
	 */
	public void init()
	{
		DeserializeJSON derp = new DeserializeJSON(); 
		
		SerializeJSON ser = new SerializeJSON();
		
		MapData md = new MapData();
		TerrainData terrain1 = new TerrainData("grass", 0, 0);
		TerrainData terrain2 = new TerrainData("grass", 0, 1);
		TerrainData terrain3 = new TerrainData("grass", 1, 0);
		TerrainData terrain4 = new TerrainData("grass", 1, 1);
		try {
			md.setNumXCells(2);
			md.setNumYCells(2);
			md.addTerrainData(terrain1);
			md.addTerrainData(terrain2);
			md.addTerrainData(terrain3);
			md.addTerrainData(terrain4);
			md.addSpawnPoint(new Point(0,0));
			md.addSinkPoint(new Point(1,1));
			ser.SerializeToFile(md, "SampleMap");
			
			
			TerrainMap terrainMap = new TerrainMap(md);
			terrainMap.getDestination();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		ApplicationController appcont = new ApplicationController();
		appcont.init();
	}
}