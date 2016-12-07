package engine.model.systems;

import java.util.List;

import engine.IObserver;
import engine.model.components.CollidableComponent;
import engine.model.components.DamageDealingComponent;
import engine.model.components.HealthComponent;
import engine.model.entities.IEntity;

public class HealthSystem implements ISystem{
	private List<HealthComponent> myHealthComponents;
	
	public boolean isDead(IEntity entity) {
		HealthComponent healthComponent = findHealthComponent(entity);
		return healthComponent.getCurrentHealth() <= 0;
	}
	
	/**
	 * Adds the change in health to the entity's health.
	 * @param entity to add health to
	 * @param deltaHealth: change in health
	 */
	// TODO: What if entity does not have a health component?
	public void takeDamage(IEntity entity, int damage) {
		HealthComponent healthComponent = findHealthComponent(entity);
		healthComponent.takeDamage(damage);
	}
	
	/**
	 * Given the input entity, returns the corresponding health component.
	 * 
	 * @param entityQuery
	 * @return health component that the input entity owns
	 */
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
