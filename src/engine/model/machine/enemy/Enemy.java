package engine.model.machine.enemy;

import engine.model.components.IComponent;
import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;
import engine.model.weapons.DamageInfo;
import utility.Damage;

public class Enemy extends Machine implements IEnemy {
	private IMoney killValue;
	

	@Override
	public DamageInfo takeDamage(Damage toDeal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMoney getKillValue() {
		return killValue;
	}

}
