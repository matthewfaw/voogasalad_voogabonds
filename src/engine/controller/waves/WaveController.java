package engine.controller.waves;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import authoring.controller.LevelDataContainer;
import authoring.model.EntityData;
import authoring.model.GameLevelsData;
import authoring.model.LevelData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.playerinfo.Player;
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
	
	public WaveController (DataStore<EntityData> aEnemyDataStore, LevelData aLevelData, double startTime, EntityFactory aEntityFactory) {
		myActiveWaveManager = new ActiveWaveManager(aEnemyDataStore, aLevelData, startTime);
		myEntityFactory = aEntityFactory;
	}
	
	public void distributeEntities(double aElapsedTime)
	{
		Map<EntityData, String> entitiesToConstruct = myActiveWaveManager.getEntitiesToConstruct(aElapsedTime);
		for (Iterator<EntityData> iterator = entitiesToConstruct.keySet().iterator(); iterator.hasNext();) {
			EntityData entityData = iterator.next();
			//TODO: add this back
//			try {
//				myEntityFactory.constructEntity(entityData);
//			} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
//					| IllegalArgumentException | InvocationTargetException e) {
//				throw new UnsupportedOperationException(ResouceAccess.getError("NoEntity"), e);
//			}
		//XXX: Not sure if I wanna pass the Timeline Controller here... there's probably a better way
		//TODO: Change to a better way?
		//myMapDistributor.distribute(enemyData, enemiesToConstruct.get(enemyData), aChangedObject);
			
		}
	}

	public boolean isLevelFinished() {
		return !myActiveWaveManager.hasEnemiesToRelease();
	}
}