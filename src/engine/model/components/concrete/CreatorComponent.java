package engine.model.components.concrete;

import java.util.ArrayList;

import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.ICreator;
import engine.model.components.viewable_interfaces.IViewableCreator;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.strategies.IPosition;
import engine.model.strategies.ISpawningStrategy;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.SpawningSystem;
import engine.model.systems.TargetingSystem;
import gamePlayerView.gamePlayerView.Router;
import engine.model.weapons.DamageInfo;
import utility.Point;

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
public class CreatorComponent extends AbstractComponent implements ICreator, IViewableCreator {

	private ISpawningStrategy mySpawningStrategy;
	private int myTimeBetweenSpawns;
	private String mySpawnName;

	@Hide
	private IPosition myTarget;
	@Hide
	private int myTimeSinceSpawning;
	
	@Hide
	private EntityFactory myEntityFactory;
	@Hide
	private PhysicalSystem myPhysical;
	@Hide
	private TargetingSystem myTargeting;
	@Hide
	private MovementSystem myMovement;
	@Hide
	private SpawningSystem mySpawning;
	@Hide
	private List<IEntity> myChildren;
	@Hide
	private DamageInfo myStats;
	@Hide
	private List<IObserver<IViewableCreator>> myObservers;
	
	public CreatorComponent(SpawningSystem spawning,
			PhysicalSystem physical,
			TargetingSystem targeting,
			MovementSystem movement,
			ComponentData data,
			Router router) {
		super(router);
		
		myObservers = new ArrayList<IObserver<IViewableCreator>>();
		
		mySpawning = spawning;
		myPhysical = physical;
		myTargeting = targeting;
		myMovement = movement;
		myEntityFactory = mySpawning.getFactory();
		
		mySpawnName = data.getFields().get("mySpawnName");
		myTimeBetweenSpawns = Integer.parseInt(data.getFields().get("myTimeBetweenSpawns"));
		mySpawningStrategy = mySpawning.newStrategy(data.getFields().get("mySpawningStrategy"));
		
		myTimeSinceSpawning = 0;
		myChildren = new ArrayList<IEntity>();
		
		spawning.attachComponent(this);
	}

	/**
	 * this method creates a new entity based on the
	 * entity spawning strategy.  This entity will be launched
	 * towards the target determined by the targeting strategy
	 */
	public void spawnIfReady() {
		myTarget = myTargeting.getTarget(this);
		if (myTimeSinceSpawning >= myTimeBetweenSpawns && myTarget != null && myPhysical.get(this) != null) {
			myChildren.add(mySpawningStrategy.spawn(myEntityFactory, myTarget, myMovement, myPhysical, this));
			myTimeSinceSpawning = 0;
		} else{
			myTimeSinceSpawning++;
		}
		
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
	public void distributeInfo() {
		getRouter().distributeViewableComponent(this);
	}

	@Override
	public int getTimeBetweenSpawns() {
		return myTimeBetweenSpawns;
	}

	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewableCreator> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewableCreator> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
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
	public String getEntityID() {
		return getEntity().getId();
	}

	@Override
	public void delete() {
		mySpawning.detachComponent(this);
	}
}
