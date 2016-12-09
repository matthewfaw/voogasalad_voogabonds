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
	public LevelController(LevelDataContainer aGameLevelsData, DataStore<EntityData> aEntityDataStore) {
		myLevelDataContainer = aGameLevelsData;
		myEntityDataStore = aEntityDataStore;
	}

	@Override
	//*******************Observer interface***************//
	public void update(TimelineController aChangedObject) {
		if (myWaveController == null || myWaveController.isLevelFinished()) {
			myCurrentLevel ++;
			myWaveController = new WaveController(myEntityDataStore, myLevelDataContainer.getLevelData(myCurrentLevel), aChangedObject.getTotalTimeElapsed());
		}
		
	}
	
	
}
