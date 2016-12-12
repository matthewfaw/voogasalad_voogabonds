package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.DamageDealingComponent;
import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.factories.DamageStrategyFactory;

public class DamageDealingSystem extends AbstractSystem<DamageDealingComponent> {
	private DamageStrategyFactory myStrategyFactory;

	public DamageDealingSystem() {
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
		//c.getEntity().die();
		}
	}
}
