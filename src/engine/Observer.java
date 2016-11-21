package engine;

/**
 * This Interface allows an object to be notified of changes to objects of a specific type, given the implementing object
 * registers as an observer of the object of type A. 
 * @author Weston
 *
 * @param <A> The type of object the observer is observing.
 */
public interface Observer<A> {
	
	abstract public void add(A toAdd);
	abstract public void update(A toUpdate);
	abstract public void remove(A toUpdate);

}
