package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.concrete.DamageDealingComponent;
import engine.model.entities.IEntity;
import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.factories.DamageStrategyFactory;

public class DamageDealingSystem implements ISystem<DamageDealingComponent> {
	private List<DamageDealingComponent> myComponents;
	private DamageStrategyFactory myStrategyFactory;

	public DamageDealingSystem() {
		myComponents = new ArrayList<DamageDealingComponent>();
		myStrategyFactory = new DamageStrategyFactory();
	}
	
	public DamageDealingComponent get(IComponent c) {
		return getComponent(c);
	}

	public void dealDamageToTarget(IComponent a, IComponent b) {
		if (get(a) != null && !a.equals(b))
			get(a).explode(b);
	}
	
	public IDamageStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}

	public void explode(IComponent c) {
		DamageDealingComponent dmg = get(c);
		if(dmg != null)
			dmg.explode();
		else {
			c.getEntity().delete();
		}
	}
	/***********ISystem interface*******/
	@Override
	public List<DamageDealingComponent> getComponents() {
		return myComponents;
	}
	@Override
	public DamageDealingComponent getComponent(IComponent component) {
		return getComponent(component.getEntity());
	}
	@Override
	public DamageDealingComponent getComponent(IEntity entity) {
		for (DamageDealingComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(DamageDealingComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(DamageDealingComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
