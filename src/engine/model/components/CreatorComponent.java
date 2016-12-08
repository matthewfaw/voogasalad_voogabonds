package engine.model.components;

import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.strategies.IPhysical;
import engine.model.strategies.ISpawningStrategy;
import engine.model.systems.PhysicalSystem;

/**
 * The purpose of this class is to manage the work necessary
 * to creating child entites
 * An example of where this class is used:
 * if a certain entity needs to fire a projectile, then this component would
 * be responsible for that action
 * @author matthewfaw
 *
 */
public class CreatorComponent implements IComponent, ICreator {
	private IEntity myEntity;
	
	private int myTimeBetweenSpawns;
	private int myTimeSinceSpawning;

	
	//TODO:
	private ISpawningStrategy mySpawningStrategy;
	private EntityFactory myEntityFactory;
	private PhysicalSystem myPhysicalSystem;
	
	//TODO:
	/**
	 * this method creates a new entity based on the
	 * entity spawning strategy.  This entity will be launched
	 * towards the target determined by the targeting strategy
	 */

	@Override
	public IEntity getEntity() {
		return myEntity;
	}

	public void spawn(IPhysical target) {
		
		if (myTimeSinceSpawning >= myTimeSinceSpawning) {
			mySpawningStrategy.spawn(myEntityFactory, target, myPhysicalSystem.get(this), this);
			myTimeSinceSpawning = 0;
		} else
			myTimeSinceSpawning++;
		
	}
}
