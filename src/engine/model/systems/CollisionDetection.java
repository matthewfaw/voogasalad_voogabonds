package engine.model.systems;

import java.util.ArrayList;
import java.util.List;



import engine.IObservable;
import engine.IObserver;
import engine.model.collision_detection.ICollidable;

/**
 * A system to manage collision detection in the game
 * Entities with the proper componets can register with the
 * Collision detection system so that collisions may be reported
 * 
 * This class is an observer to ICollidable entities
 * 
 * This class is observable by any system
 *
 */
public class CollisionDetection implements ISystem, IObserver<ICollidable>, IObservable<ISystem> {
	private List<ICollidable> myCollidableComponents;
	
	private List<IObserver<ISystem>> myObservers;
	
	public CollisionDetection() {
		myCollidableComponents = new ArrayList<ICollidable>();
		myObservers = new ArrayList<IObserver<ISystem>>();
	}

	/**
	 * A method to determine if a and b intersect
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean intersects(ICollidable a, ICollidable b)
	{
		/*
		double a_x = a.getPosition().getX();
		double a_y = a.getPosition().getY();
		double a_r = a.getCollisionRadius();
		
		double b_x = b.getPosition().getX();
		double b_y = b.getPosition().getY();
		double b_r = b.getCollisionRadius();
		
		return Math.pow(a_r - b_r, 2) <= 
				Math.pow(a_x - b_x, 2) + 
				Math.pow(a_y - b_y, 2);
				*/
		return false;
	}

	//************************************Observer interface****************************//
	@Override
	public void update(ICollidable movedObservable) {
		// Check all Entities to see if any are intersecting with the current object
		for(ICollidable observable: myCollidableComponents) {
			if (intersects(movedObservable, observable)) {
				/* notify component and let component handle which 
				systems to talk to in order to handle collision properly*/
				movedObservable.collideInto(observable);
			}
		}
	}

	/**
	 * Adds component to list so that there is collision detection
	 * for that component. Added component should be ICollidable.
	 * @param aNewComponent
	 */
	public void addComponent(ICollidable aNewComponent)
	{
		myCollidableComponents.add(aNewComponent);
	}

	/**
	 * Removes component from being tracked by collision detection.
	 * Component will no longer be able to collide.
	 * @param aNewComponent
	 */
	public void removeComponent(ICollidable aNewComponent)
	{
		myCollidableComponents.remove(aNewComponent);
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
