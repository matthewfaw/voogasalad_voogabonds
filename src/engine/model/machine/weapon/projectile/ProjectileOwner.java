package engine.model.machine.weapon.projectile;

import utility.Point;

public interface ProjectileOwner {
	abstract public double getHeading();
	abstract public Point getLocation();
}
