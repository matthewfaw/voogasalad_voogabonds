package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.components.CollidableComponent;
import engine.model.components.IComponent;
import engine.model.components.PhysicalComponent;
import engine.model.strategies.IPhysical;

/**
 * A system to manage collision detection in the game
 * Entities with the proper components can register with the
 * Collision detection system so that collisions may be reported
 * 
 * This class is an observer to Physical components
 * 
 * This class is observable by any system
 *
 */
public class CollisionDetectionSystem implements ISystem, /*IObserver<MoveableComponent>,*/ IObservable<ISystem> {
	private List<CollidableComponent> myCollidableComponents;
	private List<IObserver<ISystem>> myObservers;
	private PhysicalSystem myPhysicalSystem;
	
	public CollisionDetectionSystem(PhysicalSystem physical) {
		myCollidableComponents = new ArrayList<CollidableComponent>();
	}

	/**
	 * A method to determine if entity a and entity b intersect
	 * @param a: first entity
	 * @param b: second entity
	 * @return true if a and b are in either of each other's 
	 * collision radii; false if not
	 */
	private boolean intersects(CollidableComponent a, CollidableComponent b)
	{
		IPhysical aPhysical = myPhysicalSystem.get(a);
		IPhysical bPhysical = myPhysicalSystem.get(b);
		double a_x = aPhysical.getPosition().getX();
		double a_y = aPhysical.getPosition().getY();
		double b_x = bPhysical.getPosition().getX();
		double b_y = bPhysical.getPosition().getY();
		
		double a_r = a.getCollisionRadius();
		double b_r = b.getCollisionRadius();
		
		return Math.pow(a_r - b_r, 2) <= 
				Math.pow(a_x - b_x, 2) + 
				Math.pow(a_y - b_y, 2);
	}

	
	/**
	 * Checks if a physical component collides with anything in
	 * it's collision radius.
	 * @param the physical component that changed position
	 */
	public void checkCollision(PhysicalComponent movedPhysical) {
		CollidableComponent movedCollidable = get(movedPhysical);
		if (movedCollidable != null) {
			// Check all Entities to see if any are intersecting with the current object
			for (CollidableComponent c: myCollidableComponents) {
				if (c != movedCollidable && intersects(movedCollidable, c))
					movedCollidable.collideInto(c);
			}
		}
	}
	
	public CollidableComponent get(IComponent c) {
		for (CollidableComponent t: myCollidableComponents)
			if (t.getEntity().equals(c.getEntity()))
				return t;
		return null;
	}
	
	/************ Attach and detach component methods ************/
	
	public void attachComponent(CollidableComponent aComponent) {
		myCollidableComponents.add(aComponent);
	}
	public void detachComponent(CollidableComponent aComponent) {
		myCollidableComponents.remove(aComponent);
	}


//	/************************* Observer interface ****************************/
//	
//	/**
//	 * When a MoveableComponent notifies collision detection,
//	 * the system checks if that entity collides with any other
//	 * collidable entities. If there is a collision, the collision
//	 * is passed to the collidable components to handle.
//	 * 
//	 * @param the observable moveable component that moved
//	 */
//	@Override
//	public void update(MoveableComponent movedObservable) {
//		// Make sure moveable is also collidable
//		CollidableComponent movedCollidable = findCollidableComponent(movedObservable.getEntity());
//		if (movedCollidable != null) {
//			// Check all Entities to see if any are intersecting with the current object
//			for(MoveableComponent observable: myMoveableComponents) {
//				if (intersects(movedObservable.getEntity(), observable.getEntity())) {
//					CollidableComponent unmovedCollidable = findCollidableComponent(observable.getEntity());
//					movedCollidable.collideInto(unmovedCollidable);
//				}
//			}
//		}
//	}

	/**************************** Observable interface **************************/
	@Override
	public void attach(IObserver<ISystem> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<ISystem> aObserver) {
		myObservers.remove(aObserver);
	}

	/**
	 * This method currently assumes that passing null to the update method won't cause issues
	 * We should probably check that this is ok...
	 */
	@Override
	public void notifyObservers() {
		//XXX: not sure how I feel about passing null here
		myObservers.forEach(observer -> observer.update(this));
	}

}
