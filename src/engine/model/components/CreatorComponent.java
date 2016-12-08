package engine.model.components;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.strategies.IPhysical;
import engine.model.strategies.ISpawningStrategy;
import engine.model.strategies.ITargetingStrategy;
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
public class CreatorComponent implements IComponent, IObserver<TimelineController>, ICreator {
	private IEntity myEntity;
	
	private double mySightRange;
	private double mySightWidth;
	
	private int myTimeBetweenSpawns;
	private int myTimeSinceSpawning;
	
	//TODO:
	private ISpawningStrategy mySpawningStrategy;
	private ITargetingStrategy myTargetingStrategy;
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

	@Override
	public void update(TimelineController aChangedObject) {
		IPhysical target = myTargetingStrategy.target(myPhysicalSystem.get(this), this);
		
		if (myTimeSinceSpawning >= myTimeSinceSpawning) {
			mySpawningStrategy.spawn(myEntityFactory, target, myPhysicalSystem.get(this), this);
			myTimeSinceSpawning = 0;
		} else
			myTimeSinceSpawning++;
		
	}

	@Override
	public double getTargetWedgeWidth() {
		return mySightWidth;
	}

	@Override
	public double getTargetWedgeRadius() {
		return mySightRange;
	}
}
