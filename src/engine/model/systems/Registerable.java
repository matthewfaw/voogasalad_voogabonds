package engine.model.systems;

import java.util.List;

public class Registerable {
	List<ISystem> mySystems;
	
	public void deregister() {
		for(ISystem s: mySystems) {
			s.deregister(this);
			mySystems.remove(s);
		}
	}
}
