package engine.model.strategies;

import utility.Damage;

public interface IDamageStrategy {

	abstract public Damage getAoEDamage(IPhysical dealer, IPhysical taker, double damage);
	abstract public Damage getAoEAllyDamage(IPhysical dealer, IPhysical taker, double damage);
	abstract public Damage getTargetDamage(double damage);

}
