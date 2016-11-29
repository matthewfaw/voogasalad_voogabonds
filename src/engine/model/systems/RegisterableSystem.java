package engine.model.systems;

import java.util.List;

/**
 * A system to handle movement of it's registered Elements
 * @author matthewfaw
 *
 */
public class RegisterableSystem implements ISystem {
	
	List<Registerable> myRegisterables;
	
	@Override
	public void register(Registerable registerable) {
		myRegisterables.add(registerable);
	}

	@Override
	public void deregister(Registerable registerable) {
		myRegisterables.remove(registerable);
	}

}
