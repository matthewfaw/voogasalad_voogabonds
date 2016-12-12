package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.ICreator;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewableCreator;
import engine.model.entities.EntityFactory;
import engine.model.strategies.IPosition;
import engine.model.strategies.ISpawningStrategy;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.SpawningSystem;
import engine.model.systems.TargetingSystem;
import gamePlayerView.gamePlayerView.Router;

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
	private List<IObserver<IViewable>> myObservers;
	
	public CreatorComponent(SpawningSystem spawning,
			PhysicalSystem physical,
			TargetingSystem targeting,
			MovementSystem movement,
			EntityFactory factory,
			ComponentData data,
			Router router) {
		super(router);
		myObservers = new ArrayList<IObserver<IViewable>>();
		myPhysical = physical;
		myTargeting = targeting;
		myMovement = movement;
		myEntityFactory = factory;
		
		mySpawnName = data.getFields().get("mySpawnName");
		myTimeBetweenSpawns = Integer.parseInt(data.getFields().get("myTimeBetweenSpawns"));
		
		myTimeSinceSpawning = 0;
		spawning.attachComponent(this);
	}

	/**
	 * this method creates a new entity based on the
	 * entity spawning strategy.  This entity will be launched
	 * towards the target determined by the targeting strategy
	 */
	public void spawnIfReady() {
		if (myTimeSinceSpawning >= myTimeBetweenSpawns && myTarget != null && myPhysical.get(this) != null) {
			mySpawningStrategy.spawn(myEntityFactory, myTarget, myMovement, myPhysical, this);
			myTimeSinceSpawning = 0;
		} else
			myTimeSinceSpawning++;
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTimeBetweenSpawns() {
		return myTimeBetweenSpawns;
	}

	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}
}
