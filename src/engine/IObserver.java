package engine;

/**
 * 
 * An interface used to define 
 * a method for updating the object when a particular object being observed 
 * has changed state
 * 
 * @author Weston Carvalho and Matthew Faw 
 * 
 * @param A: the type of object that has been updated
 */
public interface IObserver<A> {
	/**
	 * A method to update a subscriber of a change that has occurred
	 * in a specific publisher
	 * 
	 * @param aObjectToUpdate: the object which has changed
	 */
	public void update(A aChangedObject);
	
}