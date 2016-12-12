package engine.model.strategies;

import engine.model.components.concrete.CreatorComponent;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;

public interface ISpawningStrategy {

	abstract public IEntity spawn(EntityFactory myEntityFactory, IPosition myTarget, MovementSystem myMovement, PhysicalSystem myPhysical,
			CreatorComponent creatorComponent);
	
}
