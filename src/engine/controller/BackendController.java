package engine.controller;

import java.util.ArrayList;
import java.util.List;

import authoring.model.EnemyData;
import authoring.model.IReadableData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import authoring.model.map.MapData;
import authoring.model.serialization.DeserializeJSON;
import engine.model.data_stores.DataStore;
import engine.model.game_environment.MapMediator;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.resourcestore.ResourceStore;
import gamePlayerView.Router;
import utility.FileRetriever;

public class BackendController {
	private static final String GAME_DATA_PATH = "src/SerializedFiles";
	//TODO: Move this to resource file
	private static final String ENEMY_PATH = "/enemies";
	private static final String MAP_PATH = "/map";
	private static final String PLAYER_PATH = "/players";
	private static final String PROJECTILE_PATH = "/projectiles";
	private static final String TOWER_PATH = "/towers";
	private static final String WEAPON_PATH = "/weapons";
	
	private FileRetriever myFileRetriever;
	private DeserializeJSON myJsonDeserializer;
	
	private MapMediator myMapMediator;
	private ResourceStore myResourceStore;
	private DataStore<WeaponData> myWeaponDataStore;
	private DataStore<ProjectileData> myProjectileDataStore;
	private DataStore<EnemyData> myEnemyDataStore;
	
	private TimelineController myTimelineController;
	private WaveController myWaveController;
	private Router myRouter;
	
	BackendController(Router aRouter)
	{
		myRouter = aRouter;
		myFileRetriever = new FileRetriever(GAME_DATA_PATH);
		myJsonDeserializer = new DeserializeJSON();

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
		List<MapData> data = getData(MAP_PATH, MapData.class);
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
		List<TowerData> data = getData(TOWER_PATH, TowerData.class);
		myResourceStore = new ResourceStore(data);
	}
	
	/**
	 * This method handles the construction of the object which will be used to query
	 * information about weapon data when the towers are being constructed
	 */
	private void constructWeaponDataStore()
	{
		List<WeaponData> data = getData(WEAPON_PATH, WeaponData.class);
		myWeaponDataStore = new DataStore<WeaponData>(data);
	}
	
	/**
	 * This method handles the construction of the object which manages all of the projectile
	 * data
	 */
	private void constructProjectileDataStore()
	{
		List<ProjectileData> data = getData(PROJECTILE_PATH, ProjectileData.class);
		myProjectileDataStore = new DataStore<ProjectileData>(data);
	}
	private void constructEnemyDataStore()
	{
		List<EnemyData> data = getData(ENEMY_PATH, EnemyData.class);
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
			T entry = (T) myJsonDeserializer.DeserializeFromFile(file, aClass);
			data.add(entry);
		}
		return data;
	}
	
	public static void main(String[] args)
	{
		BackendController controller = new BackendController(null);
		controller.getClass();
	}
}
