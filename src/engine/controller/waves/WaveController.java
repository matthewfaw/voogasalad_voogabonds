package engine.controller.waves;

import java.util.Map;

import authoring.model.EntityData;
import authoring.model.GameLevelsData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.playerinfo.Player;

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
public class WaveController implements IObserver<TimelineController> {
//	private MapDistributor myMapDistributor;
	private ActiveWaveManager myActiveWaveManager;
	private EntityFactory myEntityFactory;
	private Player myEnemy;
	
	public WaveController( GameLevelsData aGameLevelsData, DataStore<EntityData> aEnemyDataStore, Player p)
	{
//		myMapDistributor = aMapDistributor;
		myEnemy = p;
		myActiveWaveManager = new ActiveWaveManager(aGameLevelsData, aEnemyDataStore);
	}

	//*******************Observer interface***************//
	@Override
	public void update(TimelineController aChangedObject) {
		//TODO: check if we should spawn a new enemy
		// if true, then distribute the enemy through the mediator
		Map<EntityData, String> enemiesToConstruct = myActiveWaveManager.getEnemiesToConstruct(aChangedObject.getTotalTimeElapsed());
		for (EntityData enemyData: enemiesToConstruct.keySet()) {
		
		//XXX: Not sure if I wanna pass the Timeline Controller here... there's probably a better way
		//TODO: Change to a better way?
		//myMapDistributor.distribute(enemyData, enemiesToConstruct.get(enemyData), aChangedObject);
			myEntityFactory.constructEntity(enemyData);
		}
		
		//TODO: check if we should transition to the next wave
	}
}