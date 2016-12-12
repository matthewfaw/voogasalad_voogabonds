package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.DamageDealingComponent;

public class DamageDealingSystem extends AbstractSystem<DamageDealingComponent> {
	
	public DamageDealingComponent get(IComponent c) {
		return getComponent(c);
	}

	public void dealDamageToTarget(IComponent a, IComponent b) {
		if (get(a) != null && !a.equals(b))
			get(a).explode(b);
	}
}
