package engine.model.systems;

import java.util.List;


import engine.IObservable;
import engine.IObserver;
import engine.model.components.MoveableComponent;

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
public class CollisionDetection implements ISystem, IObserver<MoveableComponent>, IObservable<ISystem> {
	private List<MoveableComponent> myMoveableComponents;
	
	private List<IObserver<ISystem>> myObservers;
	
	public CollisionDetection()
	{
	}

	/**
	 * A method to determine if a and b intersect
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean intersects(MoveableComponent a, MoveableComponent b)
	{
		//TODO: check for collision
		return false;
	}

	//************************************Observer interface****************************//
	@Override
	public void update(MoveableComponent aChangedObject) {
		// Check all Entities to see if any are intersecting with the current object
		for (MoveableComponent component: myMoveableComponents) {
			if (intersects(aChangedObject, component)) {
				notifyObservers();
			}
		}
	}

	//TODO: maybe add these to the Observer interface... not sure if this makes sense tho
	public void addComponent(MoveableComponent aNewComponent)
	{
		myMoveableComponents.add(aNewComponent);
	}

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

	@Override
	public void register(Registerable registerable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deregister(Registerable registerable) {
		// TODO Auto-generated method stub
		
	}

}
