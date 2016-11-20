package engine;

public interface Observer<A> {
	
	abstract public void add(A toAdd);
	abstract public void update(A toUpdate);
	abstract public void remove(A toUpdate);

}
