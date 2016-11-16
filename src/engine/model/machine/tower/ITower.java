package engine.model.machine.tower;

import engine.model.resourcestore.IMoney;

public interface ITower {
	public IMoney getUpgradeCost();
	public void upgrade(ITowerUpgradeStore towerUpgradeStore);
}
