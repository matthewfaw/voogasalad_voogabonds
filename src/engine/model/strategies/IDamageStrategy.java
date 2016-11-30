package engine.model.strategies;

import engine.model.projectiles.Projectile;
import utility.Damage;
import utility.Point;

public interface IDamageStrategy {

	abstract public Damage getAoEDamage(Projectile missile, Point location, double damage);
	abstract public Damage getAoEAllyDamage(Projectile missile, Point location, double damage);
	abstract public Damage getTargetDamage(double damage);

}
