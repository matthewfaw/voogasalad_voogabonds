package engine.model.collision_detection;

import engine.model.machine.IHealth;

/**
 * Interface for objects that deal damage.
 * @author alanguo
 *
 */
public interface IDamageDealer {
	public IHealth getDamageDealt();
}
