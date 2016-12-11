package engine.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import authoring.model.PlayerData;
import authoring.model.serialization.JSONDeserializer;
import engine.controller.timeline.TimelineController;
import engine.controller.waves.LevelController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.game_environment.MapMediator;
import engine.model.game_environment.distributor.MapDistributor;
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
	private static final int DEFAULT_STARTING_LEVEL=0;

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
	private MapMediator myMapMediator;
	
	//Controllers to manage events
	private TimelineController myTimelineController;
	private PlayerController myPlayerController;
	private LevelController myLevelController;
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
	private TeamSystem myTeamSystem;
	private MapDataContainer myMapData;
	
	public BackendController(String aGameDataPath, Router aRouter)
	{
		myRouter = aRouter;
		myGameDataRelativePaths = ResourceBundle.getBundle(GAME_DATA_PATH);
		myFileRetriever = new FileRetriever(aGameDataPath);
		myJsonDeserializer = new JSONDeserializer();

		myTimelineController = new TimelineController();
		myPlayerController = new PlayerController(myRouter);
		
		//Must construct static before dynamic.
		constructStaticBackendObjects();
		myPlayerController.addPlayer(myPlayerData);
		myPlayerController.addResourceStoreForAllPlayers(myResourceStore);
		constructDynamicBackendObjects();
	}
	
	private void constructSystems() {
		myTeamSystem = new TeamSystem();
		myHealthSystem = new HealthSystem();
		myRewardSystem = new RewardSystem();
		myDamageDealingSystem = new DamageDealingSystem();
		
		// ORDERING MATTERS for physical -> targeting -> collision -> movement
		myPhysicalSystem = new PhysicalSystem(myMapMediator);
		
		myTargetingSystem = new TargetingSystem(myPhysicalSystem, myTeamSystem);
		myCollisionDetectionSystem = new CollisionDetectionSystem(myPhysicalSystem);
		
		myMovementSystem = new MovementSystem(myPhysicalSystem, myCollisionDetectionSystem, myTargetingSystem, myTimelineController);
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
//		myWaveController = new WaveController(myLevelDataContainer, myEntityDataStore, myPlayerController.getActivePlayer());
		myLevelController = new LevelController(myLevelDataContainer, DEFAULT_STARTING_LEVEL, myEntityDataStore, myEntityFactory, myPhysicalSystem, myMovementSystem, myMapData);
		myTimelineController.attach(myLevelController);
	}
	
	/**
	 * The primary dispatcher method for constructing objects from the GameDataFiles
	 */
	private void constructStaticBackendObjects()
	{
		constructEntityDataStore();
		constructPlayerData();
		
		constructLevelData();
		constructMap();
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
		try {
			List<MapDataContainer> data = getData(myGameDataRelativePaths.getString("MapPath"), MapDataContainer.class);
			MapDataContainer mapData = data.get(0);
			myMapData = mapData;
			
			//XXX: is the map mediator needed anywhere? Could we just keep the map distributor? this would be ideal
			myMapMediator = new MapMediator(mapData);
			myMapDistributor = new MapDistributor(myMapMediator, myResourceStore, myEntityFactory, myPlayerController);

			//distribute to frontend
			myRouter.distributeMapData(mapData);
		} catch (FileNotFoundException e) {
			//TODO: Make error message come from resource file
			myRouter.distributeErrors("The file for MapDataContainer cannot be found!");
		}
		
	}
	
	/**
	 * Constructs level data object, assuming there's exactly one of them
	 */
	private void constructLevelData() {
		try {
			List<LevelDataContainer> data = getData(myGameDataRelativePaths.getString("LevelPath"), LevelDataContainer.class);
			myLevelDataContainer = data.get(0);
		} catch (FileNotFoundException e) {
			myRouter.distributeErrors("The file for LevelData cannot be found!");
		}
	}
	
	/**
	 * This method handles the construction of the object which manages all of the enemy
	 * data
	 */
	private void constructEntityDataStore()
	{
		try {
			List<EntityData> data = getData(myGameDataRelativePaths.getString("EntityPath"), EntityData.class);
			myEntityDataStore = new DataStore<EntityData>(data);
			myResourceStore = new ResourceStore(data);
		} catch (FileNotFoundException e) {
			myRouter.distributeErrors("The file for EntityData cannot be found!");
		}
		
	}
	
	/**
	 * This method constructs the player data
	 * Assumes there is exactly one player data specified
	 * 
	 * TODO: Possibly change this to make it more flexible?
	 * @throws FileNotFoundException 
	 */
	private void constructPlayerData() 
	{
		try {
			List<PlayerData> data = getData(myGameDataRelativePaths.getString("PlayerPath"), PlayerData.class);
			myPlayerData = data.get(0);
		} catch (FileNotFoundException e) {
			myRouter.distributeErrors("The file for PlayerData cannot be found!");
		}
	}
	
	/**
	 * A templated method to get the list of GameData objects from the specified file path
	 * This method assumes that all GameData in the file path is of the type aClass
	 * TODO: Add error throwing here
	 * @param aFilePath
	 * @param aClass
	 * @return
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> getData(String aFilePath, Class<T> aClass) throws FileNotFoundException
	{
		List<String> files = myFileRetriever.getFileNames(aFilePath);
		List<T> data = new ArrayList<T>();
		for (String file: files) {
			T entry;
			entry = (T) myJsonDeserializer.deserializeFromFile(file, aClass);
			data.add(entry);
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
