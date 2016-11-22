package engine.controller;

import java.awt.Point;

import authoring.model.TowerData;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import authoring.model.serialization.DeserializeJSON;
import authoring.model.serialization.SerializeJSON;
import engine.model.game_environment.terrain.TerrainMap;

/**
 * A main controller whose primary purpose is to
 * create the frontend and backend objects and to manage 
 * communication from frontend to backend
 * 
 * Communication from backend to frontend will be established
 * by the Router object, and will be maintained using the 
 * Observer/Observable pattern of communication
 * 
 * @author matthewfaw
 *
 */
public class ApplicationController {
	/**
	 * XXX: For testing purposes
	 */
	public void init()
	{
		/*
		 * AppScene scene  = new AppScene()
		 * 
		 * myRouter = new Router(appScene)
		 * 
		 * void  createNewEnemy()
		 * {
		 * 		Enemy enemy = new Enemy();
		 * 		myRouter.distributeEnemy(enemy);
		 * }
		 * 
		 */
		
		
		
		DeserializeJSON derp = new DeserializeJSON(); 
		
		TowerData data = (TowerData)derp.DeserializeFromFile("towers/ActualTower", TowerData.class);
		System.out.println(data.getBuyPrice());

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
	/**
	 * Helper method to create the backend map object
	 * from the GameData file
	 */
	private void constructMap()
	{
		
	}
	/**
	 * Helper method to create the backend resource store object
	 * from the GameData file
	 */
	private void constructResourceStore()
	{
		
	}
	/**
	 * Helper method to create the view object
	 * from the GameData file
	 */
	private void constructGUI()
	{
		
	}
	/*
	public static void main(String[] args)
	{
		ApplicationController appcont = new ApplicationController();
		appcont.init();
	}
	*/
}