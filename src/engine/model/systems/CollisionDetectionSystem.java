package engine.model.systems;

import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.components.CollidableComponent;
import engine.model.components.MoveableComponent;
import engine.model.components.PhysicalComponent;
import engine.model.entities.IEntity;

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
public class CollisionDetectionSystem implements ISystem, IObserver<MoveableComponent>, IObservable<ISystem> {
	private List<MoveableComponent> myMoveableComponents;
	private List<CollidableComponent> myCollidableComponents;
	private List<IObserver<ISystem>> myObservers;
	private List<PhysicalComponent> myPhysicalComponents;
	
	public CollisionDetectionSystem() {
	}

	/**
	 * A method to determine if entity a and entity b intersect
	 * @param a: first entity
	 * @param b: second entity
	 * @return true if a and b are in either of each other's 
	 * collision radii; false if not
	 */
	private boolean intersects(IEntity a, IEntity b)
	{
		PhysicalComponent aPhysical = findPhysicalComponent(a);
		PhysicalComponent bPhysical = findPhysicalComponent(b);
		double a_x = aPhysical.getPosition().getX();
		double a_y = aPhysical.getPosition().getY();
		double b_x = bPhysical.getPosition().getX();
		double b_y = bPhysical.getPosition().getY();
		
		CollidableComponent aCollidable = findCollidableComponent(a);
		CollidableComponent bCollidable = findCollidableComponent(b);
		double a_r = aCollidable.getCollisionRadius();
		double b_r = bCollidable.getCollisionRadius();
		
		return Math.pow(a_r - b_r, 2) <= 
				Math.pow(a_x - b_x, 2) + 
				Math.pow(a_y - b_y, 2);
	}
	
	/**
	 * Given the input entity, returns the corresponding physical component.
	 * 
	 * @param query: entity being queried
	 * @return physical component that the input entity owns
	 */
	private PhysicalComponent findPhysicalComponent(IEntity query) {
		PhysicalComponent found = null;
		for (PhysicalComponent cc: myPhysicalComponents) {
			if (cc.getEntity() == query) {
				found = cc;
			}
		}
		return found;
	}

	/**
	 * Given the input entity, returns the corresponding collidable component.
	 * 
	 * @param query: entity being queried
	 * @return collidable component that the input entity owns
	 */
	private CollidableComponent findCollidableComponent(IEntity query) {
		CollidableComponent found = null;
		for (CollidableComponent cc: myCollidableComponents) {
			if (cc.getEntity() == query) {
				found = cc;
			}
		}
		return found;
	}

	//************************************Observer interface****************************//
	
	/**
	 * When a MoveableComponent notifies collision detection,
	 * the system checks if that entity collides with any other
	 * collidable entities. If there is a collision, the collision
	 * is passed to the collidable components to handle.
	 * 
	 * @param the observable moveable component that moved
	 */
	@Override
	public void update(MoveableComponent movedObservable) {
		// Make sure moveable is also collidable
		CollidableComponent movedCollidable = findCollidableComponent(movedObservable.getEntity());
		if (movedCollidable != null) {
			// Check all Entities to see if any are intersecting with the current object
			for(MoveableComponent observable: myMoveableComponents) {
				if (intersects(movedObservable.getEntity(), observable.getEntity())) {
					CollidableComponent unmovedCollidable = findCollidableComponent(observable.getEntity());
					movedCollidable.collideInto(unmovedCollidable);
				}
			}
		}
	}

	/**
	 * Adds component to list so that there is collision detection
	 * for that component. Added component should be ICollidable.
	 * @param aNewComponent
	 */
	public void addComponent(MoveableComponent aNewComponent)
	{
		myMoveableComponents.add(aNewComponent);
	}

	/**
	 * Removes component from being tracked by collision detection.
	 * Component will no longer be able to collide.
	 * @param aNewComponent
	 */
	public void removeComponent(MoveableComponent aNewComponent)
	{
		myMoveableComponents.remove(aNewComponent);
	}

	//************************************Observable interface****************************//
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
