package engine.model.components;

import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.strategies.IPhysical;
import engine.model.strategies.ISpawningStrategy;

/**
 * The purpose of this class is to manage the work necessary
 * to creating child entites
 * An example of where this class is used:
 * if a certain entity needs to fire a projectile, then this component would
 * be responsible for that action
 * @author matthewfaw
 *
 */
public class CreatorComponent extends AbstractComponent implements ICreator {
	private IEntity myEntity;
	
	private int myTimeBetweenSpawns;
	private int myTimeSinceSpawning;
	private IPhysical myTarget;

	private ISpawningStrategy mySpawningStrategy;
	private EntityFactory myEntityFactory;

	//TODO:
	/**
	 * this method creates a new entity based on the
	 * entity spawning strategy.  This entity will be launched
	 * towards the target determined by the targeting strategy
	 */
	public void spawn(IPhysical locationOfThis) {
		if (myTimeSinceSpawning >= myTimeBetweenSpawns && myTarget != null) {
			mySpawningStrategy.spawn(myEntityFactory, myTarget, locationOfThis, this);
			myTimeSinceSpawning = 0;
		} else
			myTimeSinceSpawning++;
		
	}

	@Override
	public void setTarget(IPhysical target) {
		myTarget = target;
	}
}
