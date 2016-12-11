package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.BountyComponent;

public class BountySystem extends AbstractSystem<BountyComponent> {
	
	public int collectBounty(IComponent c) {
		BountyComponent b = getComponent(c);
		return (b == null) ? 0 : b.getBounty();
	}
}
