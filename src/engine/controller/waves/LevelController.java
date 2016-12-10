package engine.controller.waves;

import authoring.controller.LevelDataContainer;
import authoring.model.EntityData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
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
	private EntityFactory myEntityFactory;
	private int myCurrentLevel;

	public LevelController(LevelDataContainer aGameLevelsData, int aStartingLevel, DataStore<EntityData> aEntityDataStore, EntityFactory aEntityFactory) {
		myLevelDataContainer = aGameLevelsData;
		myEntityDataStore = aEntityDataStore;
		
		myWaveController = new WaveController(aEntityDataStore, aGameLevelsData.getLevelData(aStartingLevel), DEFAULT_START_TIME, aEntityFactory);
	}

	@Override
	//*******************Observer interface***************//
	public void update(TimelineController aChangedObject) {
		if (myWaveController.isLevelFinished()) {
			myCurrentLevel ++;
			if (myLevelDataContainer.hasLevel(myCurrentLevel)) {
				myWaveController = new WaveController(myEntityDataStore, myLevelDataContainer.getLevelData(myCurrentLevel), aChangedObject.getTotalTimeElapsed(), myEntityFactory);
			}
		}
		
		myWaveController.distributeEntities(aChangedObject.getTotalTimeElapsed());
	}
}
