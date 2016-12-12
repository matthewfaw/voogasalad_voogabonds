package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.components.ICreator;
import engine.model.entities.ConcreteEntity;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.strategies.IPosition;
import engine.model.strategies.ISpawningStrategy;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.SpawningSystem;
import engine.model.systems.TargetingSystem;
import engine.model.weapons.DamageInfo;

/**
 * The purpose of this class is to manage the work necessary
 * to creating child entites
 * An example of where this class is used:
 * if a certain entity needs to fire a projectile, then this component would
 * be responsible for that action
 * @author matthewfaw
 * @author Weston
 *
 */
public class CreatorComponent extends AbstractComponent implements ICreator {

	private transient ISpawningStrategy mySpawningStrategy;
	private int myTimeBetweenSpawns;
	private String mySpawnName;

	@Hide
	private transient IPosition myTarget;
	@Hide
	private int myTimeSinceSpawning;
	
	@Hide
	private transient EntityFactory myEntityFactory;
	@Hide
	private transient PhysicalSystem myPhysical;
	@Hide
	private transient TargetingSystem myTargeting;
	@Hide
	private transient MovementSystem myMovement;
	@Hide
	private transient SpawningSystem mySpawning;
	@Hide
	private List<ConcreteEntity> myChildren;
	@Hide
	private DamageInfo myStats;

	
	public CreatorComponent(SpawningSystem spawning,
			PhysicalSystem physical,
			TargetingSystem targeting,
			MovementSystem movement,
			EntityFactory factory,
			ComponentData data) {
		mySpawning = spawning;
		myPhysical = physical;
		myTargeting = targeting;
		myMovement = movement;
		myEntityFactory = factory;
		
		mySpawnName = data.getFields().get("mySpawnName");
		myTimeBetweenSpawns = Integer.parseInt(data.getFields().get("myTimeBetweenSpawns"));
		
		myTimeSinceSpawning = 0;
		myChildren = new ArrayList<ConcreteEntity>();
		spawning.attachComponent(this);
	}

	/**
	 * this method creates a new entity based on the
	 * entity spawning strategy.  This entity will be launched
	 * towards the target determined by the targeting strategy
	 */
	public void spawnIfReady() {
		if (myTimeSinceSpawning >= myTimeBetweenSpawns && myTarget != null && myPhysical.get(this) != null) {
			myChildren.add(mySpawningStrategy.spawn(myEntityFactory, myTarget, myMovement, myPhysical, this));
			myTimeSinceSpawning = 0;
		} else
			myTimeSinceSpawning++;
		
	}
	
	@Override
	public boolean isParent(IEntity entity) {
		return myChildren.contains(entity);
	}

	@Override
	public void setTarget(IPosition target) {
		myTarget = target;
	}

	@Override
	public IPosition getTarget() {
		return myTarget;
	}

	@Override
	public String getSpawnName() {
		return mySpawnName;
	}

	@Override
	public void updateStats(DamageInfo data) {
		myStats.add(data);
	}
	
	@Override
	public DamageInfo getStats() {
		return myStats;
	}

	@Override
	public void delete() {
		mySpawning.detachComponent(this);
	}
}
