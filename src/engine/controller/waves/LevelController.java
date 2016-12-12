package engine.controller.waves;

import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
/**
 * 
 * @author owenchung
 *
 */
public class LevelController implements IObserver<TimelineController> {
	private static final double DEFAULT_START_TIME = 0.0;
	private LevelDataContainer myLevelDataContainer;
	private WaveController myWaveController;
	private DataStore<EntityData> myEntityDataStore;
	private int myCurrentLevel;

	public LevelController(
			LevelDataContainer aGameLevelsData,
			int aStartingLevel,
			DataStore<EntityData> aEntityDataStore,
			EntityFactory aEntityFactory,
			PhysicalSystem physical,
			MovementSystem movement,
			MapDataContainer mapData) {
		myLevelDataContainer = aGameLevelsData;
		myEntityDataStore = aEntityDataStore;
		
		myWaveController = new WaveController(
				aEntityDataStore,
				aGameLevelsData.getLevelData(aStartingLevel),
				DEFAULT_START_TIME,
				aEntityFactory,
				physical,
				movement,
				mapData);
	}

	@Override
	//*******************Observer interface***************//
	public void update(TimelineController aChangedObject) {
		System.out.println("updating with elapsedTime: " + aChangedObject.getTotalTimeElapsed()); 
		if (myWaveController.isLevelFinished()) {
			myCurrentLevel ++;
			if (myLevelDataContainer.hasLevel(myCurrentLevel)) {
				myWaveController.newWave(myEntityDataStore, myLevelDataContainer.getLevelData(myCurrentLevel), aChangedObject.getTotalTimeElapsed());
			}
		}
		
		myWaveController.distributeEntities(aChangedObject.getTotalTimeElapsed());
	}
}
