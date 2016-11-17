package engine.model.machine.weapon;

import engine.model.machine.IHealth;
import engine.model.machine.Machine;
import engine.model.machine.weapon.projectile.IProjectile;
import utility.Point;

public interface IWeapon {
	public void fire(Machine target, double initialDirectionRadians, Point initialLocation);
	public IProjectile getProjectile();
	public IHealth getHealth();
}
