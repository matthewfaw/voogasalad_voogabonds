package engine.model.machine.tower;

import engine.model.machine.IPurchasable;
import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;
import engine.model.machine.weapon.DamageInfo;
import utility.Damage;

public class Tower extends Machine implements IPurchasable{

	private IMoney sellPrice;
	private IMoney upgradeCost;
	
	public IMoney getUpgradeCost() {
		return upgradeCost;
	}
	
	public IMoney getSellPrice() {
		return sellPrice;
	}
	
	/**
	 * Upgrades this tower to the
	 * @param newTower
	 * Replaces all fields of this tower with newTower
	 */
	public void upgrade(ITowerUpgradeStore towerUpgradeStore) {
		
	}

	@Override
	public DamageInfo takeDamage(Damage toDeal) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
