package engine.model.systems;

import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.PhysicalComponent;
import engine.model.strategies.IPhysical;

public class PhysicalSystem implements ISystem {
	
	List<PhysicalComponent> myComponents;
	
	public IPhysical get(IComponent c) {
		for (PhysicalComponent p: myComponents)
			if (p.getEntity().equals(c.getEntity()))
				return p;
		return null;
	}

}
