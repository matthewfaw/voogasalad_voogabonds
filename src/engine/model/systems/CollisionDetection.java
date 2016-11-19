package engine.model.systems;

import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.entities.IEntity;

/**
 * A system to manage collision detection in the game
 * Entities with the proper componets can register with the
 * Collision detection system so that collisions may be reported
 * 
 * This class is an observer to Entities
 * 
 * This class is observable by any system
 * 
 * @author matthewfaw
 *
 */
public class CollisionDetection implements ISystem, IObserver, IObservable {
	private List<IEntity> myEntities;
	
	private List<IObserver> myObservers;
	
	public CollisionDetection()
	{
	}

	//************************************Observer interface****************************//
	@Override
	public void update(IEntity aObjectToUpdate) {
		// Check all Entities to see if any are intersecting with the current object
		for (IEntity entity: myEntities) {
			if (intersects(aObjectToUpdate, entity)) {
				notifyObservers();
			}
		}
	}
	/**
	 * A method to determine if a and b intersect
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean intersects(IEntity a, IEntity b)
	{
		//TODO: check for collision
		return false;
	}

	//************************************Observable interface****************************//
	@Override
	public void attach(IObserver aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver aObserver) {
		myObservers.remove(aObserver);
	}

	/**
	 * This method currently assumes that passing null to the update method won't cause issues
	 * We should probably check that this is ok...
	 */
	@Override
	public void notifyObservers() {
		//XXX: not sure how I feel about passing null here
		myObservers.forEach(observer -> observer.update(null));
	}

}
