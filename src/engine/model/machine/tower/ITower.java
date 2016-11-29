package engine.model.machine.tower;

import java.util.Collection;

/**
 * Core interface for tower behaviors
 */
public interface ITower {
	abstract public int getSellPrice();
	abstract public void upgrade(String upgradeName, ITowerUpgradeStore towerUpgradeStore);
	abstract public int getUpgradeCost(String upgradeName);
	abstract public Collection<String> getUpgrades();
}
