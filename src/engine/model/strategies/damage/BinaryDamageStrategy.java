package engine.model.strategies.damage;

import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.IPhysical;
import engine.model.strategies.factories.DamageStrategyFactory;
import utility.Damage;

public class BinaryDamageStrategy implements IDamageStrategy {
	
	public BinaryDamageStrategy(DamageStrategyFactory factory) {
		//Do nothing.
	}

	@Override
	public Damage getAoEDamage(IPhysical dealer, IPhysical taker, double damage) {
		return new Damage(0);
	}
	
	@Override
	public Damage getAoEAllyDamage(IPhysical dealer, IPhysical taker, double damage) {
		return new Damage(0);
	}

	@Override
	public Damage getTargetDamage(double damage) {
		return new Damage(damage);
	}

}
