package engine.model.machine.tower;

import engine.model.machine.Machine;
import engine.model.machine.weapon.DamageInfo;
import utility.Damage;

public class Tower extends Machine{
	
	private int upgradeCost;
	
	public int getUpgradeCost() {
		return upgradeCost;
	}

	@Override
	public DamageInfo takeDamage(Damage toDeal) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
