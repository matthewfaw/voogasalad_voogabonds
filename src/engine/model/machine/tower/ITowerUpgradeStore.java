package engine.model.machine.tower;

import java.util.List;

import authoring.model.TowerData;

/**
 * Tower upgrade store interface
 * TODO: Move to resource store packages
 *
 */
public interface ITowerUpgradeStore {
	public List<TowerData> getBaseTowersData();
}
