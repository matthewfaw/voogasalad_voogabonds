package engine.model.machine.tower;

import authoring.model.TowerData;

/**
 * Core interface for tower behaviors
 */
public interface IUpgradable {	
	abstract public void upgrade(TowerData upgradeData);
}
