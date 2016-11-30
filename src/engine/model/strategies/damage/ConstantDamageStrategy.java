package engine.model.strategies.damage;

import engine.model.projectiles.Projectile;
import engine.model.strategies.IDamageStrategy;
import utility.Damage;
import utility.Point;

public class ConstantDamageStrategy implements IDamageStrategy {

	@Override
	public Damage getAoEDamage(Projectile missile, Point location, double damage) {
		return new Damage(damage);
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
