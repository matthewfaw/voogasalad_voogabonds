package engine.model.strategies.spawning;

import java.lang.reflect.InvocationTargetException;

import engine.model.components.concrete.CreatorComponent;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.strategies.IPosition;
import engine.model.strategies.ISpawningStrategy;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import utility.ResouceAccess;

public class BasicSpawnStrategy implements ISpawningStrategy {
	private String mySpawnName;
	
	@Override
	public IEntity spawn(EntityFactory myEntityFactory, IPosition myTarget, MovementSystem myMovement,
			PhysicalSystem myPhysical, CreatorComponent creatorComponent) {
		try {
			if (myPhysical.get(creatorComponent) != null) {
				IEntity spawn = myEntityFactory.constructEntity(mySpawnName);
				myMovement.get(spawn).setGoal(myTarget);
				myPhysical.get(spawn).setPosition(myPhysical.get(creatorComponent).getPosition());
				return spawn;
			} else 
				return null;
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new UnsupportedOperationException(ResouceAccess.getError("NoSpawn"), e);
		}
	}


}
