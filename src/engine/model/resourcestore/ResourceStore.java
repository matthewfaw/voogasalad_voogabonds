package engine.model.resourcestore;

import java.util.List;

import authoring.model.EntityData;
import authoring.model.TowerData;

/**
 * This class is the store that keeps all available resources
 * @author Owen Chung and matthewfaw
 */
public class ResourceStore implements /*IModifiableStore, */ IViewableStore {

	private TowerUpgradeStore myUpgradeStore;
	
	public ResourceStore(List<EntityData> aEntityInfoList) {
		myUpgradeStore = new TowerUpgradeStore(aEntityInfoList);
	}
	
	@Override
	public List<TowerData> getAvailableTowers() {
		return myUpgradeStore.getBaseTowersData();
	}
	
	public int getTowerPrice(String aTowerName)
	{
		return myUpgradeStore.getPrice(aTowerName);
	}
}
