package engine.model.systems;

import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.PhysicalComponent;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.IPhysical;

public class PhysicalSystem implements ISystem {
	
	List<PhysicalComponent> myComponents;
	MapMediator myMap;
	
	public PhysicalSystem(MapMediator map) {
		myMap = map;
	}
	
	public IPhysical get(IComponent c) {
		for (PhysicalComponent p: myComponents)
			if (p.getEntity().equals(c.getEntity()))
				return p;
		return null;
	}

	public MapMediator getMap() {
		return myMap;
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(PhysicalComponent aComponent) {
		myComponents.add(aComponent);
	}
	public void detachComponent(PhysicalComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
