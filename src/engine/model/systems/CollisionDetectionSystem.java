package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.components.IComponent;
import engine.model.components.concrete.CollidableComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.strategies.IPhysical;
import utility.Point;

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
	
	public CollisionDetectionSystem() {
		myCollidableComponents = new ArrayList<CollidableComponent>();
	}

	
	/**
	 * Checks if a physical component collides with anything in
	 * it's collision radius.
	 * @param the physical component that changed position
	 */
	public void checkCollision(PhysicalComponent movedPhysical) {
		CollidableComponent movedCollidable = get(movedPhysical);
		if (movedCollidable != null) {
			for (CollidableComponent unmovedCollidable: myCollidableComponents)	
				movedCollidable.checkCollision(unmovedCollidable);
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


	/**************************** Observable interface **************************/
	@Override
	public void attach(IObserver<ISystem> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<ISystem> aObserver) {
		myObservers.remove(aObserver);
	}
	
	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

}
