package engine.controller.waves;

import java.util.List;
import java.util.Map;

import authoring.model.EnemyData;
import engine.IObserver;
import engine.controller.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.game_environment.distributors.MapDistributor;

/**
 * A class to handle which wave the game is currently in,
 * and the game time of the simulation
 * 
 * This class depends on the ActiveWaveManager to determine which enemies should be constructed
 * at a given clock tick
 * It also depends on the MapDistributor 
 * 
 * @author matthewfaw
 *
 */
public class WaveController implements IObserver<TimelineController>{
	private MapDistributor myMapDistributor;
	private ActiveWaveManager myActiveWaveManager;
	
	public WaveController(MapDistributor aMapDistributor, DummyWaveOperationData aWaveOperationData, DataStore<EnemyData> aEnemyDataStore)
	{
		myMapDistributor = aMapDistributor;
		myActiveWaveManager = new ActiveWaveManager(aWaveOperationData, aEnemyDataStore);
	}

	//*******************Observer interface***************//
	@Override
	public void update(TimelineController aChangedObject) {
		//TODO: check if we should spawn a new enemy
		// if true, then distribute the enemy through the mediator
		Map<EnemyData, String> enemiesToConstruct = myActiveWaveManager.getEnemiesToConstruct(aChangedObject.getTotalTimeElapsed());
		for (EnemyData enemyData: enemiesToConstruct.keySet()) {
			myMapDistributor.distribute(enemyData, enemiesToConstruct.get(enemyData));
		}
		//TODO: check if we should transition to the next wave
	}
}