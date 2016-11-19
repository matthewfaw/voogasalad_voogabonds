package engine.model.resourcestore;

import java.util.List;

import engine.model.machine.tower.Tower;
import engine.model.machine.tower.TowerUpgradeStore;

/**
 * This class is the store that keep all available resources
 * @author Owen Chung
 */
public class ResourceStore implements IModifiableStore, IViewableStore{
	private TowerUpgradeStore myUpgradeStore;
	private List<Tower> myAvailableTowerOption;
	private int myPlayerID;
	
	public ResourceStore(){
		
	}
	@Override
	public void updatePlayerMoney(int deltaMoney) {
		
	}

}
