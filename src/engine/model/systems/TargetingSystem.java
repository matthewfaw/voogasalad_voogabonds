package engine.model.systems;

import engine.model.components.concrete.CreatorComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.TargetingComponent;
import engine.model.strategies.IPosition;

public class TargetingSystem extends AbstractSystem<TargetingComponent> {
	
	public IPosition getTarget(MoveableComponent c) {
		TargetingComponent t = getComponent(c);
		if (t != null)
			return t.getTarget();
		return c.getGoal();
	}
	
	public IPosition getTarget(CreatorComponent c) {
		TargetingComponent t = getComponent(c);
		if (t != null)
			return t.getTarget();
		return c.getTarget();
	}
}
