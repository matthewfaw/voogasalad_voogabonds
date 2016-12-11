package engine.model.strategies;

import engine.model.components.ICreator;
import engine.model.entities.EntityFactory;

public interface ISpawningStrategy {
	void spawn(EntityFactory entityFactory, IPosition target, IPhysical location, ICreator creator);
	
}
