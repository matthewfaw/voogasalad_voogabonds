package engine.model.systems;

import java.util.ArrayList;
import java.util.List;
import engine.model.components.IComponent;
import engine.model.components.concrete.CreatorComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.TargetingComponent;
import engine.model.entities.IEntity;
import engine.model.strategies.IPosition;
import engine.model.strategies.ITargetingStrategy;
import engine.model.strategies.factories.TargetingStrategyFactory;

public class TargetingSystem implements ISystem<TargetingComponent> {
	private List<TargetingComponent> myComponents;
	private transient TargetingStrategyFactory myStrategyFactory;
	
	public TargetingSystem() {
		myComponents = new ArrayList<TargetingComponent>();
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
	/***********ISystem interface*******/
	@Override
	public List<TargetingComponent> getComponents() {
		return myComponents;
	}
	@Override
	public TargetingComponent getComponent(IComponent component) {
		return component == null ? null : getComponent(component.getEntity());
	}
	@Override
	public TargetingComponent getComponent(IEntity entity) {
		for (TargetingComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(TargetingComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(TargetingComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
