package engine.model.strategies.damage;

import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.IPhysical;
import utility.Damage;

public class ExponentialDamageStrategy implements IDamageStrategy {

	@Override
	public Damage getAoEDamage(IPhysical dealer, IPhysical taker, double damage) {
		return new Damage(damage * Math.exp(-(dealer.getPosition().euclideanDistance(taker.getPosition()))));
	}
	
	@Override
	public Damage getAoEAllyDamage(IPhysical dealer, IPhysical taker, double damage) {
		return new Damage(0);
	}

	@Override
	public Damage getTargetDamage(double damage) {
		return new Damage(0);
	}

}
