package engine.model.systems;

import java.util.List;

import engine.model.components.CollidableComponent;
import engine.model.components.DamageDealingComponent;
import engine.model.components.HealthComponent;
import engine.model.entities.IEntity;

public class HealthSystem {
	private List<HealthComponent> myHealthComponents;
	private List<DamageDealingComponent> myDamageDealingComponents;
	
	public void updateHealthFromCollision(CollidableComponent moved, CollidableComponent unmoved){
		// need to check moved and unmoved to see if they are damage dealing
		DamageDealingComponent movedDamageDealingComponent = findDamageDealingComponent(moved.getEntity());
		DamageDealingComponent unmovedDamageDealingComponent = findDamageDealingComponent(unmoved.getEntity());
		HealthComponent movedHealthComponent = findHealthComponent(moved.getEntity());
		HealthComponent unmovedHealthComponent = findHealthComponent(unmoved.getEntity());
		
		if (movedDamageDealingComponent != null) {
			// moved deal damage to unmoved
			double damage = movedDamageDealingComponent.getDamage();
			// movedHealthComponent.updateHealth(damage);
			// if health < 0, mark as destroyOnCollision <-- should system or component handle?
		}
		
		if (unmovedDamageDealingComponent != null) {
			// unmoved deal damage to moved
			double damage = unmovedDamageDealingComponent.getDamage();
			// unmovedHealthComponent.updateHealth(damage);
			// if health < 0, mark as destroyOnCollision <-- should system or component handle?
		}
	}
	
	private DamageDealingComponent findDamageDealingComponent(IEntity entityQuery) {
		DamageDealingComponent found = null;
		for(DamageDealingComponent ddc: myDamageDealingComponents) {
			if (entityQuery == ddc.getEntity()) {
				found = ddc;
			}
		}
		return found;
	}
	
	private HealthComponent findHealthComponent(IEntity entityQuery) {
		HealthComponent found = null;
		for(HealthComponent hc: myHealthComponents) {
			if (entityQuery == hc.getEntity()) {
				found = hc;
			}
		}
		return found;
	}
}
