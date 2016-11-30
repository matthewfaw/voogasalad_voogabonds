package engine.model.collision_detection;

import java.util.List;

import engine.IObservable;
import engine.IObserver;
import engine.model.systems.ISystem;

/**
 * All objects must be subscribed as ICollidable
 * NOT IObservables
 *
 */
public class CollisionDetectionTemp implements ISystem, IObserver<ICollidable>, IObservable<ISystem>{
	
	ICollisionHandler collisionHandler;
	List<ICollidable> myCollidables;
	private List<IObserver<ISystem>> myObservers;
	
	public CollisionDetectionTemp() {
		//TODO: initialize all collidables
		//TODO: initialize with all observers
		//TODO: initialize collision handler
	}

	/**
	 * Determine if observable a intersects with observable b
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean intersects(ICollidable a, ICollidable b) {
		double a_x = a.getPosition().getX();
		double a_y = a.getPosition().getY();
		double a_r = a.getCollisionRadius();
		
		double b_x = b.getPosition().getX();
		double b_y = b.getPosition().getY();
		double b_r = b.getCollisionRadius();
		
		return Math.pow(a_r - b_r, 2) <= 
				Math.pow(a_x - b_x, 2) + 
				Math.pow(a_y - b_y, 2);
	}
	
	
	//************************************Observer interface****************************//

	/**
	 * On collision, this system will be notified and
	 * this method will be called.
	 */
	@Override
	public void update(ICollidable movedObservable) {
		for(ICollidable observable: myCollidables) {
			if (intersects(movedObservable, observable)) {
				// notify collision detection strategy
				collisionHandler.handleCollision(movedObservable, observable);
			}
		}
	}
	
	public void addComponent(ICollidable aNewComponent)
	{
		myCollidables.add(aNewComponent);
	}

	public void removeComponent(ICollidable aNewComponent)
	{
		myCollidables.remove(aNewComponent);
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

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		myObservers.forEach(observer -> observer.update(this));
	}

}
