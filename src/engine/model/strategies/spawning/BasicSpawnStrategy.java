package engine.model.strategies.spawning;

import engine.model.components.ICreator;
import engine.model.entities.EntityFactory;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;
import engine.model.strategies.ISpawningStrategy;

public class BasicSpawnStrategy implements ISpawningStrategy {
	private String mySpawnName;

	@Override
	public void spawn(EntityFactory entityFactory, IPosition target, IPhysical location, ICreator creator) {
		//entityFactory.constructEntity(mySpawnName);

	}

}
