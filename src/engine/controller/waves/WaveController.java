package engine.controller.waves;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import authoring.model.LevelData;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import utility.Point;
import utility.ResouceAccess;

/**
 * A class to handle which wave the game is currently in,
 * and the game time of the simulation
 * 
 * This class depends on the ActiveWaveManager to determine which enemies should be constructed
 * at a given clock tick
 * It also depends on the MapDistributor 
 * 
 * @author matthewfaw and owenchung
 *
 */
public class WaveController {
	private ActiveWaveManager myActiveWaveManager;
	private EntityFactory myEntityFactory;
	private MapDataContainer myMapData;
	private PhysicalSystem myPhysical;
	private MovementSystem myMovement;
	
	
	public WaveController (
			DataStore<EntityData> aEnemyDataStore,
			LevelData aLevelData,
			double startTime,
			EntityFactory aEntityFactory,
			PhysicalSystem physical,
			MovementSystem movement,
			MapDataContainer mapData
			) {
		
		myMapData = mapData;
		myPhysical = physical;
		myMovement = movement;
		myActiveWaveManager = new ActiveWaveManager(aEnemyDataStore, aLevelData, startTime);
		myEntityFactory = aEntityFactory;
		
	}


	public void distributeEntities(double aElapsedTime)
	{
		List<PathFollowerData> entitiesToConstruct = myActiveWaveManager.getEntitiesToConstruct(aElapsedTime);
		for (Iterator<PathFollowerData> iterator = entitiesToConstruct.iterator(); iterator.hasNext();) {
			PathFollowerData entityData = iterator.next();
			try {
				IEntity newEntity = myEntityFactory.constructEntity(entityData.getMyEntityData());
				
				List<Point> spawns = myMapData.getSpawnPoints(entityData.getSpawnPoint());
				List<Point> sinks = myMapData.getSinkPoints(entityData.getSinkPoint());
				
				Collections.shuffle(spawns);
				Collections.shuffle(sinks);
				
				myPhysical.get(newEntity).setPosition(spawns.get(0));
				myMovement.get(newEntity).setGoal(sinks.get(0));
				
			} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				throw new UnsupportedOperationException(ResouceAccess.getError("NoEntity"), e);
			}
		//XXX: Not sure if I wanna pass the Timeline Controller here... there's probably a better way
		//TODO: Change to a better way?
		//myMapDistributor.distribute(enemyData, enemiesToConstruct.get(enemyData), aChangedObject);
			
		}
	}

	public boolean isLevelFinished() {
		return !myActiveWaveManager.hasEnemiesToRelease();
	}


	public void newWave(DataStore<EntityData> store, LevelData levelData, double totalTimeElapsed) {
		myActiveWaveManager = new ActiveWaveManager(store, levelData, totalTimeElapsed);
		
	}

}