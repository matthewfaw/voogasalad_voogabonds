package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.collision_detection.ICollidable;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewableCollidable;
import engine.model.systems.BountySystem;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.PhysicalSystem;
import gamePlayerView.gamePlayerView.Router;

/**
 * The purpose of this class is to encapsulate the information relevant
 * to entities that can collide
 * These entities will register with the CollisionDetectionSystem, and this
 * class will define what happens when a collision occurs
 * @author matthewfaw
 *
 */
public class CollidableComponent extends AbstractComponent implements ICollidable, IViewableCollidable {
	private double myCollisionRadius;
	
	@Hide
	private PhysicalSystem myPhysicalSystem;
	@Hide
	private DamageDealingSystem myDamageDealingSystem;
	
	@Hide
	private List<IObserver<IViewable>> myObservers;
	
	public CollidableComponent (
			CollisionDetectionSystem collisionDetectionSystem, 
			PhysicalSystem physicalSystem,
			HealthSystem healthSystem,
			DamageDealingSystem damageDealingSystem, 
			BountySystem rewardSystem,
			ComponentData data,
			Router router) {
		super(router);
		myObservers = new ArrayList<IObserver<IViewable>>();
		myPhysicalSystem = physicalSystem;
		myDamageDealingSystem = damageDealingSystem;
		
		myCollisionRadius = Double.parseDouble("myCollisionRadius");
		
		collisionDetectionSystem.attachComponent(this);
	}

	//*******************ICollidable interface***********//
	@Override
	public double getCollisionRadius()
	{
		return myCollisionRadius;
	}
	
	@Override
	public void checkCollision(CollidableComponent unmovedCollidable) {
		if (!equals(unmovedCollidable) && intersects(unmovedCollidable))
			collideInto(unmovedCollidable);
	}
	
	/**
	 * A method to determine if collidables a and b intersect
	 * @param this: first entity
	 * @param c: second entity
	 * @return true if a and b are in either of each other's 
	 * collision radii; false if not
	 */
	private boolean intersects(CollidableComponent c)
	{
		double a_x = myPhysicalSystem.get(this).getPosition().getX();
		double a_y = myPhysicalSystem.get(this).getPosition().getY();
		double b_x = myPhysicalSystem.get(c).getPosition().getX();
		double b_y = myPhysicalSystem.get(c).getPosition().getY();
		
		double a_r = getCollisionRadius();
		double b_r = c.getCollisionRadius();

		return Math.pow(a_r - b_r, 2) <= 
				Math.pow(a_x - b_x, 2) + 
				Math.pow(a_y - b_y, 2);
	}
	
	private void collideInto(CollidableComponent unmovedCollidable) {
		dealDamage(this, unmovedCollidable);
		dealDamage(unmovedCollidable, this);
	}

	private void dealDamage(CollidableComponent a, CollidableComponent b) {
		
		myDamageDealingSystem.dealDamageToTarget(a, b);

	}

	@Override
	public void distributeInfo() {
		getRouter().distributeViewableComponent(this);
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
