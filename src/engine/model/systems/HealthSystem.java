package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.model.components.CollidableComponent;
import engine.model.components.DamageDealingComponent;
import engine.model.components.HealthComponent;
import engine.model.components.IComponent;
import engine.model.components.MoveableComponent;
import engine.model.entities.IEntity;
import engine.model.weapons.DamageInfo;
import utility.Damage;

/**
 * @author Weston
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
		if (healthComponent == null)
			return false;
		else
			return healthComponent.getCurrentHealth() <= 0;
	}
	
	/**
	 * Adds the change in health to the entity's health.
	 * @param entity to add health to
	 * @param deltaHealth: change in health
	 */
	public void takeDamage(IComponent component, Damage dmg) {
		HealthComponent healthComponent = get(component);
		if (healthComponent != null)
			healthComponent.takeDamage(dmg);
	}
	
	private HealthComponent get(IComponent component) {
		return findHealthComponent(component.getEntity());
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
	public DamageInfo dealDamageTo(IComponent target, Damage damageToTake) {
		HealthComponent health = get(target);
		if (health != null) {
			return health.takeDamage(damageToTake);
		}
		return new DamageInfo();
	}
	
}
