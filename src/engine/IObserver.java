package engine;

import engine.model.entities.IEntity;

/**
 * 
 * An interface used to define 
 * a method for updating the object when a particular object being observed 
 * has changed state
 * 
 * @author matthewfaw 
 * 
 */
public interface IObserver {
	/**
	 * A method to update a subscriber of a change that has occurred
	 * in a specific publisher
	 * 
	 * @param toUpdate: the object which has changed
	 */
	abstract public void update(IEntity aObjectToUpdate);

}
