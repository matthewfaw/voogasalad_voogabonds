package engine.model.resourcestore;

import java.util.List;

import authoring.model.TowerData;
import engine.model.machine.tower.TowerUpgradeStore;

/**
 * This class is the store that keeps all available resources
 * @author Owen Chung and matthewfaw
 */
public class ResourceStore implements /*IModifiableStore, */ IViewableStore {

	private TowerUpgradeStore myUpgradeStore;
	
	public ResourceStore(List<TowerData> aTowerInfoList) {
		myUpgradeStore = new TowerUpgradeStore(aTowerInfoList);
	}
	
	@Override
	public List<TowerData> getAvailableTowers() {
		return myUpgradeStore.getBaseTowersData();
	}
	
}
