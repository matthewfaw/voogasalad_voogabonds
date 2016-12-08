package engine.model.components;

import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;

/**
 * The purpose of this class is to manage the work necessary
 * to creating child entites
 * An example of where this class is used:
 * if a certain entity needs to fire a projectile, then this component would
 * be responsible for that action
 * @author matthewfaw
 *
 */
public class CreatorComponent implements IComponent {
	private IEntity myEntity;
	
	//TODO:
//	private ISpawningStrategy myEnemySpawningStrategy;
//	private ITargetingStrategy myTargetingStrategy;
	private EntityFactory myEntityFactory;
	
	//TODO:
	/**
	 * this method creates a new entity based on the
	 * entity spawning strategy.  This entity will be launched
	 * towards the target determined by the targeting strategy
	 */
	public void spawnEntity()
	{
		//myEnemySpawningStrategy.getEntityToSpawn();
		//Create it
		//set it's target
		//maybe register with that entity
	}

	@Override
	public IEntity getEntity() {
		return myEntity;
	}
}
