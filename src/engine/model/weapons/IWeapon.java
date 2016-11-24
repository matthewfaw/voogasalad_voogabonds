package engine.model.weapons;
import java.util.List;

import engine.model.machine.IHealth;
import engine.model.machine.Machine;
import engine.model.projectiles.IProjectile;
import utility.Point;
/**
 * The interface that a Weapon exposes to it's tower.
 * @author Weston
 *
 */
public interface IWeapon {
	
	/**
	 * If able, fires a projectile at a target in targets (which depends on the weapon's targeting strategy)
	 * If not able, ticks down the amount of time until this weapon can fire again.
	 * @param targets
	 * @param initialDirectionRadians
	 * @param initialLocation
	 */
	abstract public void fire(List<Machine> targets, double initialDirectionRadians, Point initialLocation);
	
	/**
	 * @return total number of finishing blows this weapon has dealt
	 */
	abstract public double getKills();
	
	/**
	 * @return total amount of money this weapon has earned from kills
	 */
	abstract public double getEarnings();
	
	/**
	 * @return total amount of damage this weapon has dealt
	 */
	abstract public double getDamage();
	
}
