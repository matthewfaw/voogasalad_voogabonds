package engine.model.machine;

import engine.model.weapons.DamageInfo;
import utility.Damage;

public interface IModifiableMachine {
	abstract public double getHealth();
	abstract public DamageInfo takeDamage(Damage damageDealt);
}
