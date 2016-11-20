package engine.model.strategies;

import engine.model.machine.weapon.projectile.Projectile;
import utility.Damage;
import utility.Point;

public class ConstantDamageStrategy implements IDamageStrategy {

	@Override
	public Damage getAoEDamage(Projectile missile, Point location) {
		return new Damage(1);
	}

	@Override
	public Damage getTargetDamage() {
		return new Damage(0);
	}

}
