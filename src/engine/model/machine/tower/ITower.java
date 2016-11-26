package engine.model.machine.tower;

import engine.model.machine.IHealth;
import engine.model.resourcestore.IMoney;
/**
 * Core interface for tower behaviors
 */
public interface ITower {
	public IMoney getUpgradeCost();
	public IMoney getSellPrice();
	public void upgrade(ITowerUpgradeStore towerUpgradeStore);
}
