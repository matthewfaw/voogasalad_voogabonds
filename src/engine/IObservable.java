package engine;

/**
 * This is the interface used by any object that wants to publish changes to other objects
 * 
 * @author matthewfaw
 *
 */
public interface IObservable {
	/**
	 * Adds the observer to the list of objects to be notified by the publisher
	 * @param aObserver
	 */
	public void attach(IObserver aObserver);

	/**
	 * Removes the observer from the list of objects to be notified by the publisher
	 * @param aObserver
	 */
	public void detach(IObserver aObserver);

	/**
	 * A method to announce a change to all subscribers
	 */
	public void notifyObservers();
}
