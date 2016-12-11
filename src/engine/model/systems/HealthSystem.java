package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.model.components.CollidableComponent;
import engine.model.components.DamageDealingComponent;
import engine.model.components.HealthComponent;
import engine.model.components.MoveableComponent;
import engine.model.entities.IEntity;
import utility.Damage;

/**
 * 
 * @author owenchung(edited)
 *
 */
public class HealthSystem implements ISystem{
	private List<HealthComponent> myHealthComponents;
	
	public HealthSystem () {
		myHealthComponents = new ArrayList<HealthComponent>();
	}
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
	public void takeDamage(IEntity entity, Damage dmg) {
		HealthComponent healthComponent = findHealthComponent(entity);
		healthComponent.takeDamage(dmg);
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
	
	/************ Attach and detach component methods ************/
	public void attachComponent(HealthComponent aComponent) {
		myHealthComponents.add(aComponent);
	}
	public void detachComponent(HealthComponent aComponent) {
		myHealthComponents.remove(aComponent);
	}
	
}
