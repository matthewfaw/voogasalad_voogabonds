package engine.model.systems;

import java.util.List;

import engine.model.collision_detection.ICollidable;

public abstract class Registerable implements ICollidable{
	
	List<ISystem> mySystems;
	
	public void unregister() {
		for(ISystem s: mySystems) {
			s.unregister(this);
			mySystems.remove(s);
		}
	}
}
