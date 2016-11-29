package engine.model.systems;

import java.util.List;

import engine.model.collision_detection.ICollidable;

/**
 * A system to handle registering and unregistering of it's registered Elements
 *
 */
public class RegisterableSystem implements ISystem {
	
	List<ICollidable> myRegisterables;
	
	@Override
	public void register(ICollidable registerable) {
		myRegisterables.add(registerable);
	}

	@Override
	public void unregister(ICollidable registerable) {
		myRegisterables.remove(registerable);
		registerable = null;
	}

}
