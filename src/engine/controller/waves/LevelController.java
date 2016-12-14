package engine.controller.waves;

import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import engine.IObserver;
import engine.controller.system.SystemsController;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
/**
 * LevelController listens to timelinecontroller and updates the levels of the waves
 * @author owenchung
 *
 */
public class LevelController implements IObserver<TimelineController> {
	private static final double DEFAULT_START_TIME = 0.0;
	private transient LevelDataContainer myLevelDataContainer;
	private transient WaveController myWaveController;
	private transient DataStore<EntityData> myEntityDataStore;
	private int myCurrentLevel;
	private transient EntityFactory myEntityFactory;
	private transient PhysicalSystem myPhysicalSystem;
	private transient MovementSystem myMovementSystem;
	private transient MapDataContainer myMapDataContainer;
	
	public LevelController(
			LevelDataContainer aGameLevelsData,
			int aStartingLevel,
			DataStore<EntityData> aEntityDataStore,
			EntityFactory aEntityFactory,
			SystemsController aSystemsController,
			MapDataContainer aMapDataContainer) 
	{
		myCurrentLevel = aStartingLevel;
		reinitialize(aGameLevelsData, aEntityDataStore, aEntityFactory, aSystemsController, aMapDataContainer);
	}
	
	public void reinitialize(LevelDataContainer aGameLevelsData, DataStore<EntityData> aEntityDataStore, EntityFactory aEntityFactory,
							SystemsController aSystemsController, MapDataContainer aMapDataContainer)
	{
		myLevelDataContainer = aGameLevelsData;
		myEntityDataStore = aEntityDataStore;
		myEntityFactory = aEntityFactory;
		//TODO: remove this hack
		myPhysicalSystem = aSystemsController.getPhysicalSystem();
		myMovementSystem = aSystemsController.getMovementSystem();
		myMapDataContainer = aMapDataContainer;
	}

	@Override
	//*******************Observer interface***************//
	public void update(TimelineController aChangedObject) {

		if (myWaveController == null) {
			myWaveController = new WaveController(
					myEntityDataStore,
					myLevelDataContainer.getLevelData(myCurrentLevel),
					DEFAULT_START_TIME,
					myEntityFactory,
					myPhysicalSystem,
					myMovementSystem,
					myMapDataContainer);
		}

		if (myWaveController.isLevelFinished()) {
			myCurrentLevel ++;
			if (myLevelDataContainer.hasLevel(myCurrentLevel)) {
				myWaveController.newWave(myEntityDataStore, myLevelDataContainer.getLevelData(myCurrentLevel), aChangedObject.getTotalTimeElapsed());
			}
		}
		myWaveController.distributeEntities(aChangedObject.getTotalTimeElapsed());
	}

	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing
	}
}
