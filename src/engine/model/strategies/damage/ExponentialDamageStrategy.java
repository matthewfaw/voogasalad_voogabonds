package engine.model.strategies.damage;

import engine.model.machine.weapon.projectile.Projectile;
import engine.model.strategies.IDamageStrategy;
import utility.Damage;
import utility.Point;

public class ExponentialDamageStrategy implements IDamageStrategy {

	@Override
	public Damage getAoEDamage(Projectile missile, Point location) {
		return new Damage(Math.exp(-(location.euclideanDistance(missile.getPosition()))));
	}

	@Override
	public Damage getTargetDamage() {
		return new Damage(0);
	}

}
