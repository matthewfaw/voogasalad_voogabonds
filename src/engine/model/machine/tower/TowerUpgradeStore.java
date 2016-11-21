package engine.model.machine.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TowerUpgradeStore implements ITowerUpgradeStore {
	private List<TowerNode> myBaseTowers = new ArrayList<>();
	private Map<Tower, TowerNode> myTowerNodeMap = new HashMap<Tower, TowerNode>();
	public TowerUpgradeStore(){
		
	}
	public void constructTowerNodeMap(){
		for (TowerNode towernode : myBaseTowers) {
			
		}
	}
	
	public List<Tower> getBaseTowers() {
		List<Tower> ret = new ArrayList<>();
		for (TowerNode towernode : myBaseTowers) {
			ret.add(towernode.getID());
		}
		return ret;
	}
	
	public int getPrice(Tower tower){
		for (TowerNode towernode : myBaseTowers){
			if (towernode.getID().equals(tower)){
				return towernode.getPrice();
			}
		}
		// not a base tower
		return -1;
	}
	// TODO 
	public List<Tower> getPossibleUpgrades(Tower tower){
		return null;
	}
}
