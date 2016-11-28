package engine.model.strategies.damage;

import engine.model.projectiles.Projectile;
import engine.model.strategies.IDamageStrategy;
import utility.Damage;
import utility.Point;

public class BinaryDamageStrategy implements IDamageStrategy {

	@Override
	public Damage getAoEDamage(Projectile missile, Point location) {
		return new Damage(0);
	}

	@Override
	public Damage getTargetDamage() {
		return new Damage(1);
	}

}