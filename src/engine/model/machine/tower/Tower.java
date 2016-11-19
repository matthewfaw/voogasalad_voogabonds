package engine.model.machine.tower;

import engine.model.machine.IPurchasable;
import engine.model.machine.Machine;
import engine.model.resourcestore.IMoney;

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
}
