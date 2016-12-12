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
	
	public DamageInfo dealDamageTo(IComponent target, Damage damageToTake) {
		HealthComponent health = getComponent(target);
		if (health != null) {
			return health.takeDamage(damageToTake);
		}
		return new DamageInfo();
	}
	
}
