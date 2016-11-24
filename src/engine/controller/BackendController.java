package engine.controller;

import java.util.ArrayList;
import java.util.List;

import authoring.model.TowerData;
import authoring.model.map.MapData;
import authoring.model.serialization.DeserializeJSON;
import engine.model.game_environment.MapMediator;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.resourcestore.ResourceStore;
import gamePlayerView.Router;

public class BackendController {
	private static final String GAME_DATA_PATH = "src/SerializedFiles";
	//TODO: Move this to resource file
	private static final String ENEMY_PATH = "/enemies";
	private static final String MAP_PATH = "/map";
	private static final String PLAYER_PATH = "/players";
	private static final String PROJECTILE_PATH = "/proejctiles";
	private static final String TOWER_PATH = "/towers";
	private static final String WEAPON_PATH = "/weapons";
	
	private FileRetriever myFileRetriever;
	private DeserializeJSON myJsonDeserializer;
	Router myRouter;
	
	BackendController(Router aRouter)
	{
		myRouter = aRouter;
		myFileRetriever = new FileRetriever(GAME_DATA_PATH);
		myJsonDeserializer = new DeserializeJSON();
	}

	/**
	 * Helper method to create the backend map object
	 * from the GameData file
	 */
	private void constructMap()
	{
		List<String> myMapFiles = myFileRetriever.getFileNames(MAP_PATH);
		MapData mapData = (MapData) myJsonDeserializer.DeserializeFromFile(myMapFiles.get(0), MapData.class);
		System.out.println(mapData.getSinkPoints());
		
		TerrainMap terrainMap = new TerrainMap(mapData);
		MapMediator mapMediator = new MapMediator(terrainMap);
	}
	/**
	 * Helper method to create the backend resource store object
	 * from the GameData file
	 * 
	 */
	private void constructResourceStore()
	{
		List<String> myTowerFiles = myFileRetriever.getFileNames(TOWER_PATH);
		List<TowerData> myTowerData = new ArrayList<TowerData>();
		for (String file: myTowerFiles) {
			TowerData data = (TowerData) myJsonDeserializer.DeserializeFromFile(file, TowerData.class);
			myTowerData.add(data);
		}
		
		ResourceStore myResourceStore = new ResourceStore(myTowerData);
	}
	
	/**
	 * This method handles the construction of the object which will be used to query
	 * information about weapon data when the towers are being constructed
	 */
	private void constructWeaponDataStore()
	{
		List<String> myWeaponFiles = myFileRetriever.getFileNames(WEAPON_PATH);
		List<String> myProjectileFiles = myFileRetriever.getFileNames(PROJECTILE_PATH);
		//TODO: Construct the backend object
	}
}
