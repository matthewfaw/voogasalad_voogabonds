package engine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.model.EnemyData;
import authoring.model.IReadableData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.model.map.MapData;
import authoring.model.serialization.JSONDeserializer;
import engine.model.data_stores.DataStore;
import engine.model.game_environment.MapMediator;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.resourcestore.ResourceStore;
import gamePlayerView.Router;
import utility.FileRetriever;

/**
 * The primary gateway into the game engine
 * The purpose of this class is to manage constructing
 * all relevant data structures pertaining to the game engine
 * 
 * TODO: This class is getting large
 * We may consider further dividing it's responsibilities
 * to make it more maintainable
 * 
 * @author matthewfaw
 *
 */
public class BackendController {
	private static final String GAME_DATA_PATH = "resources/game_data_relative_paths/relative_paths";

	//Utilities
	private ResourceBundle myGameDataRelativePaths;
	private FileRetriever myFileRetriever;
	private JSONDeserializer myJsonDeserializer;
	
	//Primary backend objects
	private MapMediator myMapMediator;
	private ResourceStore myResourceStore;

	//Data relevant to constructing objects
	private DataStore<WeaponData> myWeaponDataStore;
	private DataStore<ProjectileData> myProjectileDataStore;
	private DataStore<EnemyData> myEnemyDataStore;
	
	//Controllers to manage events
	private TimelineController myTimelineController;
	private WaveController myWaveController;
	private Router myRouter;
	
	BackendController(String aGameDataPath, Router aRouter)
	{
		myRouter = aRouter;
		myGameDataRelativePaths = ResourceBundle.getBundle(GAME_DATA_PATH);
		myFileRetriever = new FileRetriever(aGameDataPath);
		myJsonDeserializer = new JSONDeserializer();

		myTimelineController = new TimelineController();
		
		constructStaticBackendObjects();
		
		constructDynamicBackendObjects();
	}
	
	private void constructDynamicBackendObjects()
	{
		myWaveController = new WaveController();
		myTimelineController.attach(myWaveController);
	}
	
	/**
	 * The primary dispatcher method for constructing objects from the GameDataFiles
	 */
	private void constructStaticBackendObjects()
	{
		constructMap();
		constructResourceStore();
		constructWeaponDataStore();
		constructProjectileDataStore();
		constructEnemyDataStore();
	}

	/**
	 * Helper method to create the backend map object
	 * from the GameData file
	 * 
	 * Assumes that the only map data to use is the first one
	 */
	private void constructMap()
	{
		List<MapData> data = getData(myGameDataRelativePaths.getString("MapPath"), MapData.class);
		TerrainMap terrainMap = new TerrainMap(data.get(0));
		myMapMediator = new MapMediator(terrainMap);
	}
	/**
	 * Helper method to create the backend resource store object
	 * from the GameData file
	 * 
	 */
	private void constructResourceStore()
	{
		List<TowerData> data = getData(myGameDataRelativePaths.getString("TowerPath"), TowerData.class);
		myResourceStore = new ResourceStore(data);
	}
	
	/**
	 * This method handles the construction of the object which will be used to query
	 * information about weapon data when the towers are being constructed
	 */
	private void constructWeaponDataStore()
	{
		List<WeaponData> data = getData(myGameDataRelativePaths.getString("WeaponPath"), WeaponData.class);
		myWeaponDataStore = new DataStore<WeaponData>(data);
	}
	
	/**
	 * This method handles the construction of the object which manages all of the projectile
	 * data
	 */
	private void constructProjectileDataStore()
	{
		List<ProjectileData> data = getData(myGameDataRelativePaths.getString("ProjectilePath"), ProjectileData.class);
		myProjectileDataStore = new DataStore<ProjectileData>(data);
	}
	/**
	 * This method handles the construction of the object which manages all of the enemy
	 * data
	 */
	private void constructEnemyDataStore()
	{
		List<EnemyData> data = getData(myGameDataRelativePaths.getString("EnemyPath"), EnemyData.class);
		myEnemyDataStore = new DataStore<EnemyData>(data);
	}
	
	/**
	 * A templated method to get the list of GameData objects from the specified file path
	 * This method assumes that all GameData in the file path is of the type aClass
	 * TODO: Add error throwing here
	 * @param aFilePath
	 * @param aClass
	 * @return
	 */
	private <T extends IReadableData> List<T> getData(String aFilePath, Class<? extends IReadableData> aClass)
	{
		List<String> files = myFileRetriever.getFileNames(aFilePath);
		List<T> data = new ArrayList<T>();
		for (String file: files) {
			T entry = (T) myJsonDeserializer.deserializeFromFile(file, aClass);
			data.add(entry);
		}
		return data;
	}
	
	/*
	public static void main(String[] args)
	{
		BackendController controller = new BackendController("SerializedFiles/default_game",null);
		controller.getClass();
	}
	*/
}
