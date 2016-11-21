package engine.model.machine.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import engine.model.resourcestore.IMoney;
/**
 * Tower Upgrade Store that stores the hierarchies among towers
 * @author Owen Chung
 *
 */


public class TowerUpgradeStore implements ITowerUpgradeStore {
	private List<TowerNode> myBaseTowers = new ArrayList<>();
	private Map<Tower, TowerNode> myTowerNodeMap = new HashMap<Tower, TowerNode>();
	public TowerUpgradeStore(){
		constructTowerNodeMap();
	}
	/**
	 * consturct TowerNodeMap that maps a Tower to its corresponding TowerNode
	 * DFS using stack;
	 */
	public void constructTowerNodeMap(){
		for (TowerNode basenode : myBaseTowers) {
			Stack<TowerNode> stack = new Stack<TowerNode>();
			List<TowerNode> visitedList = new ArrayList<TowerNode>();
			stack.push(basenode);
			while(!stack.isEmpty()) {
				TowerNode current = stack.pop();
				if (visitedList.contains(current)) {
					continue;
				}
				visitedList.add(current);
				myTowerNodeMap.put(current.getID(), current);
				for (TowerNode childnode : current.getChildren()) {
					stack.push(childnode);
				}
			}
		}
	}
	/**
	 * get the TowerID given a list of TowerNodes
	 * @param nodes
	 * @return
	 */
	
	private List<Tower> getTowerfromNode(List<TowerNode> nodes) {
		List<Tower> ret = new ArrayList<Tower>();
		for (TowerNode node : nodes) {
			ret.add(node.getID());
		}
		return ret;
	}
	
	/**
	 * get all of the base towers in the game
	 */
	
	public List<Tower> getBaseTowers() {
		return getTowerfromNode(myBaseTowers);
	}
	/**
	 * return the price of a tower if it's a base tower, otherwise return null
	 * @param tower
	 * @return
	 */
	public IMoney getPrice(Tower tower) {
		for (TowerNode towernode : myBaseTowers) {
			if (towernode.getID().equals(tower)) {
				return towernode.getPrice();
			}
		}
		// not a base tower
		return null;
	}
	
	public List<Tower> getPossibleUpgrades(Tower tower) {
		TowerNode towernode = myTowerNodeMap.get(tower);
		return getTowerfromNode(towernode.getChildren());
	}

}
