package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.CreatorComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.TargetingComponent;
import engine.model.strategies.IPosition;
import engine.model.strategies.ITargetingStrategy;
import engine.model.strategies.factories.TargetingStrategyFactory;

public class TargetingSystem extends AbstractSystem<TargetingComponent> {
	private TargetingStrategyFactory myStrategyFactory;
	
	public TargetingSystem() {
		myStrategyFactory = new TargetingStrategyFactory();
	}
	
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
	
	public boolean canTarget(IComponent c) {
		return getComponent(c) != null;
	}

	public ITargetingStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}
}
