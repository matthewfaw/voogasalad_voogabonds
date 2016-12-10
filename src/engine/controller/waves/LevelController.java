package engine.controller.waves;

import authoring.controller.LevelDataContainer;
import authoring.model.EntityData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
/**
 * 
 * @author owenchung
 *
 */
public class LevelController implements IObserver<TimelineController> {
	private LevelDataContainer myLevelDataContainer;
	private WaveController myWaveController;
	private DataStore<EntityData> myEntityDataStore;
	private int myCurrentLevel;

	public LevelController(LevelDataContainer aGameLevelsData, int aStartingLevel, DataStore<EntityData> aEntityDataStore) {
		myLevelDataContainer = aGameLevelsData;
		myEntityDataStore = aEntityDataStore;
		
		myWaveController = new WaveController(aEntityDataStore, aGameLevelsData.getLevelData(aStartingLevel), myCurrentLevel);
	}

	@Override
	//*******************Observer interface***************//
	public void update(TimelineController aChangedObject) {
		if (myWaveController.isLevelFinished()) {
			myCurrentLevel ++;
			if (myLevelDataContainer.hasLevel(myCurrentLevel)) {
				myWaveController = new WaveController(myEntityDataStore, myLevelDataContainer.getLevelData(myCurrentLevel), aChangedObject.getTotalTimeElapsed());
			}
		}
		
		myWaveController.distributeEntities(aChangedObject.getTotalTimeElapsed());
	}
}
