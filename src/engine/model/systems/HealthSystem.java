package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.HealthComponent;
import engine.model.entities.IEntity;
import engine.model.weapons.DamageInfo;
import utility.Damage;

/**
 * @author Weston
 * @author owenchung(edited)
 *
 */
public class HealthSystem extends AbstractSystem<HealthComponent> {
	public HealthSystem() {
		
	}
	public boolean isDead(IEntity entity) {
		HealthComponent healthComponent = getComponent(entity);
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
		HealthComponent healthComponent = getComponent(component);
		if (healthComponent != null)
			healthComponent.takeDamage(dmg);
	}
	
	
	public DamageInfo dealDamageTo(IComponent target, Damage damageToTake) {
		HealthComponent health = getComponent(target);
		if (health != null) {
			return health.takeDamage(damageToTake);
		}
		return new DamageInfo();
	}
	
}
