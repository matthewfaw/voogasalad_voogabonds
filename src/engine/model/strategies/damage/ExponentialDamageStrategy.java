package engine.model.strategies.damage;

import engine.model.projectiles.Projectile;
import engine.model.strategies.IDamageStrategy;
import utility.Damage;
import utility.Point;

public class ExponentialDamageStrategy implements IDamageStrategy {

	@Override
	public Damage getAoEDamage(Projectile missile, Point location, double damage) {
		return new Damage(damage * Math.exp(-(location.euclideanDistance(missile.getPosition()))));
	}
	
	@Override
	public Damage getAoEAllyDamage(Projectile missile, Point location, double damage) {
		return new Damage(0);
	}

	@Override
	public Damage getTargetDamage(double damage) {
		return new Damage(0);
	}

}
