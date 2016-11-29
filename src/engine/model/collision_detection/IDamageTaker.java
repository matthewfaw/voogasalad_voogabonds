package engine.model.collision_detection;

import engine.model.machine.IHealth;

/**
 * Interface for objects that take damage.
 * @author alanguo
 *
 */
public interface IDamageTaker {
	public void takeDamage(IHealth damageTaken);
}
