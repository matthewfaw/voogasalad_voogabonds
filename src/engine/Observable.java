package engine;

/**
 * To keep or not to keep, this is the question
 * 
 * @author matthewfaw
 *
 */
public interface Observable<A> {
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
	public void notifyObservers(A aValue);
}
