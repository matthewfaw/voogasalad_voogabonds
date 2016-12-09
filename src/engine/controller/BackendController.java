package engine.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.EnemyData;
import authoring.model.EntityData;
import authoring.model.GameLevelsData;
import authoring.model.IReadableData;
import authoring.model.PlayerData;
import authoring.model.TowerData;
import authoring.model.map.MapData;
import authoring.model.serialization.JSONDeserializer;
import engine.controller.timeline.TimelineController;
import engine.controller.waves.WaveController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.game_environment.MapMediator;
import engine.model.game_environment.distributor.MapDistributor;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.resourcestore.ResourceStore;
import engine.model.systems.*;
import gamePlayerView.gamePlayerView.Router;
import utility.FileRetriever;
import utility.Point;

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
	private ResourceStore myResourceStore;

	//Data relevant to constructing objects
	private DataStore<EntityData> myEntityDataStore;
	private PlayerData myPlayerData;
	private LevelDataContainer myLevelDataContainer;
	private MapDistributor myMapDistributor;
	private MapMediator mapMediator;
	
	//Controllers to manage events
	private TimelineController myTimelineController;
	private PlayerController myPlayerController;
	private WaveController myWaveController;
	private Router myRouter;
	
	//Factories
	private EntityFactory myEntityFactory;
	
	//Systems
	private CollisionDetectionSystem myCollisionDetectionSystem;
	private DamageDealingSystem myDamageDealingSystem;
	private HealthSystem myHealthSystem;
	private MovementSystem myMovementSystem;
	private PhysicalSystem myPhysicalSystem;
	private RewardSystem myRewardSystem;
	private SpawningSystem mySpawningSystem;
	private TargetingSystem myTargetingSystem;
	
	public BackendController(String aGameDataPath, Router aRouter)
	{
		myRouter = aRouter;
		myGameDataRelativePaths = ResourceBundle.getBundle(GAME_DATA_PATH);
		myFileRetriever = new FileRetriever(aGameDataPath);
		myJsonDeserializer = new JSONDeserializer();

		myTimelineController = new TimelineController();
		myPlayerController = new PlayerController(myRouter);
		
		constructStaticBackendObjects();
		myPlayerController.addPlayer(myPlayerData);
		myPlayerController.addResourceStoreForAllPlayers(myResourceStore);
		constructDynamicBackendObjects();
	}
	
	private void constructSystems() {
		myCollisionDetectionSystem = new CollisionDetectionSystem();
		myDamageDealingSystem = new DamageDealingSystem();
		myHealthSystem = new HealthSystem();
		// ORDERING MATTERS for physical -> targeting -> movement
		myPhysicalSystem = new PhysicalSystem(mapMediator);
		myTargetingSystem = new TargetingSystem();
		myMovementSystem = new MovementSystem(myPhysicalSystem, myTargetingSystem);
		
		myRewardSystem = new RewardSystem();
		mySpawningSystem = new SpawningSystem(myPhysicalSystem, myTargetingSystem);
		
	}
	
	//TODO
	/**
	 * Places the tower, if it can
	 * @param aTowerName
	 * @param aLocation
	 * @return true if it is successfully placed, false otherwise
	 */
	public void attemptToPlaceEntity(String aEntityName, Point aLocation)
	{
//		myMapDistributor.distribute(aEntityName, aPlayerID, aLocation);
	}
	
	//TODO: Update when WaveData is ready from Authoring
	private void constructDynamicBackendObjects()
	{
		//List<DummyWaveOperationData> data = getData(myGameDataRelativePaths.getString("WavePath"), DummyWaveOperationData.class);
		//XXX: This depends on the map distributor already being constructed
		// we should refactor this to remove the depenency in calling
		myWaveController = new WaveController(myLevelDataContainer, myEntityDataStore, myPlayerController.getActivePlayer());
		myTimelineController.attach(myWaveController);
	}
	
	/**
	 * The primary dispatcher method for constructing objects from the GameDataFiles
	 */
	private void constructStaticBackendObjects()
	{
		constructResourceStore();
		constructEntityDataStore();
		constructPlayerData();
		constructMap();
		constructLevelData();
		constructSystems();
		
		constructEntityFactory(); //depends on constructing systems first
	}

	private void constructEntityFactory() {
		List<ISystem> mySystems = new ArrayList<ISystem>();
		mySystems.add(myCollisionDetectionSystem);
		mySystems.add(myDamageDealingSystem);
		mySystems.add(myHealthSystem);
		mySystems.add(myMovementSystem);
		mySystems.add(myPhysicalSystem);
		mySystems.add(myRewardSystem);
		mySystems.add(mySpawningSystem);
		mySystems.add(myTargetingSystem);
		
		myEntityFactory = new EntityFactory(mySystems, myEntityDataStore, myRouter);
	}

	/**
	 * Helper method to create the backend map object
	 * from the GameData file
	 * 
	 * Assumes that the only map data to use is the first one
	 */
	private void constructMap()
	{
		List<MapDataContainer> data = getData(myGameDataRelativePaths.getString("MapPath"), MapDataContainer.class);
		MapDataContainer mapData = data.get(0);
		TerrainMap terrainMap = new TerrainMap(mapData);
		//XXX: is the map mediator needed anywhere? Could we just keep the map distributor? this would be ideal
		MapMediator mapMediator = new MapMediator(terrainMap);
		myMapDistributor = new MapDistributor(mapMediator, myEntityFactory);

		//distribute to frontend
		myRouter.distributeMapData(mapData);
		
	}
	
	/**
	 * Constructs level data object, assuming there's exactly one of them
	 */
	private void constructLevelData() {
		List<LevelDataContainer> data = getData(myGameDataRelativePaths.getString("LevelPath"), LevelDataContainer.class);
		myLevelDataContainer = data.get(0);
	}
	
	/**
	 * Helper method to create the backend resource store object
	 * from the GameData file
	 * 
	 */
	//TODO
	private void constructResourceStore()
	{
//		List<TowerData> data = getData(myGameDataRelativePaths.getString("TowerPath"), TowerData.class);
//		myResourceStore = new ResourceStore(data);
	}
	
	/**
	 * This method handles the construction of the object which manages all of the enemy
	 * data
	 */
	private void constructEntityDataStore()
	{
		List<EntityData> data = getData(myGameDataRelativePaths.getString("EntityPath"), EntityData.class);
		myEntityDataStore = new DataStore<EntityData>(data);
	}
	
	/**
	 * This method constructs the player data
	 * Assumes there is exactly one player data specified
	 * 
	 * TODO: Possibly change this to make it more flexible?
	 */
	private void constructPlayerData()
	{
		List<PlayerData> data = getData(myGameDataRelativePaths.getString("PlayerPath"), PlayerData.class);
		myPlayerData = data.get(0);
	}
	
	/**
	 * A templated method to get the list of GameData objects from the specified file path
	 * This method assumes that all GameData in the file path is of the type aClass
	 * TODO: Add error throwing here
	 * @param aFilePath
	 * @param aClass
	 * @return
	 */
	private <T> List<T> getData(String aFilePath, Class<T> aClass)
	{
		List<String> files = myFileRetriever.getFileNames(aFilePath);
		List<T> data = new ArrayList<T>();
		for (String file: files) {
			T entry;
			try {
				entry = (T) myJsonDeserializer.deserializeFromFile(file, aClass);
				data.add(entry);
			} catch (Exception e) {
				//XXX: REMOVE PLS
				e.printStackTrace();
			}
		}
		return data;
	}

	public void startTimeLine() {
		myTimelineController.start();
	}
	public void pauseTimeline()
	{
		myTimelineController.pause();
	}
	
	
	/*
	public static void main(String[] args)
	{
		BackendController controller = new BackendController("SerializedFiles/exampleGame",null);
		controller.getClass();
	}
	*/
	
}
