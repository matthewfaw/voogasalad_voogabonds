package engine.model.resourcestore;

import java.util.List;

import authoring.model.EntityData;
import authoring.model.TowerData;

/**
 * This class is the store that keeps all available resources
 * @author Owen Chung and matthewfaw
 */
public class ResourceStore implements /*IModifiableStore, */ IViewableStore {

	private EntityUpgradeStore myUpgradeStore;
	
	public ResourceStore(List<EntityData> aEntityInfoList) {
		myUpgradeStore = new EntityUpgradeStore(aEntityInfoList);
	}
	
	@Override
	public List<EntityData> getAvailableEntities() {
		return myUpgradeStore.getBaseEntitesData();
	}
	
	public int getTowerPrice(String aEntityName)
	{
		return myUpgradeStore.getPrice(aEntityName);
	}
}
