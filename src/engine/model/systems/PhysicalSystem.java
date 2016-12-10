package engine.model.systems;

import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.IPhysical;

public class PhysicalSystem implements ISystem {
	
	List<PhysicalComponent> myComponents;
	MapMediator myMap;
	
	public PhysicalSystem(MapMediator map) {
		myMap = map;
	}
	
	public PhysicalComponent get(IComponent c) {
		return get(c.getEntity());
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

	public PhysicalComponent get(IEntity entity) {
		for (PhysicalComponent p: myComponents)
			if (p.getEntity().equals(entity))
				return p;
		return null;
	}
}
