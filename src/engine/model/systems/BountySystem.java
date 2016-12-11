package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.concrete.BountyComponent;
import engine.model.entities.IEntity;

public class BountySystem implements ISystem {
	List<BountyComponent> myComponents;
	
	public BountySystem() {
		myComponents = new ArrayList<BountyComponent>();
	}
	
	public int getBounty(IComponent c) {
		BountyComponent b = get(c);
		return (b == null) ? 0 : b.getBounty();
	}

	private BountyComponent get(IComponent c) {
		return get(c.getEntity());
	}

	private BountyComponent get(IEntity entity) {
		for (BountyComponent b: myComponents)
			if (b.getEntity().equals(entity))
				return b;
		return null;
	}
	
}
