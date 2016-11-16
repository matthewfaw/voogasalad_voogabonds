package engine.model.strategies;

import engine.model.machine.weapon.projectile.Projectile;
import utility.Damage;
import utility.Point;

public interface IDamageStrategy {

	Damage getAoEDamage(Projectile missile, Point location);

	Damage getTargetDamage();

}
