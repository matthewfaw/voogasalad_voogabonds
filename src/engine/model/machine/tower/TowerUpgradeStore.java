package engine.model.machine.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import authoring.model.TowerData;
import engine.model.resourcestore.IMoney;

/**
 * Tower Upgrade Store that stores the hierarchies among towers
 * @author Owen Chung
 */


public class TowerUpgradeStore implements ITowerUpgradeStore {
	private List<TowerNode> myBaseTowers;
	private Map<String, TowerNode> myConstructedTowerNodes;
	
	public TowerUpgradeStore(List<TowerData> aTowerInfoList) {
		myConstructedTowerNodes = new HashMap<String, TowerNode>();
		Stack<TowerNode> towerNodes = constructNodes(aTowerInfoList);
		connectNodes(towerNodes);
	}
	
	
	/**
	 * A helper method to construct all of the node objects from the tower data objects
	 * This method only construct the nodes, it does not connect them
	 * 
	 * Note that this method also populate the myConstructedTowerNodes map, 
	 * and the myBaseTowers map
	 */
	private Stack<TowerNode> constructNodes(List<TowerData> aTowerInfoList)
	{
		Stack<TowerNode> towerNodes = new Stack<TowerNode>();
		for (TowerData towerData: aTowerInfoList) {
			TowerNode towerNode = new TowerNode(towerData);
			towerNodes.add(towerNode);
			myConstructedTowerNodes.put(towerNode.getName(), towerNode);
			if (towerData.getBuyPrice() >= 0) {
				myBaseTowers.add(towerNode);
			}
		}
		return towerNodes;
	}

	/**
	 * This method connects all of the Tower Nodes in the input stack
	 * Note that this method depends on myConstructedTowerNodes map being fully
	 * populated in order to easily connect the graph
	 * @param aTowerNodes
	 */
	private void connectNodes(Stack<TowerNode> aTowerNodes)
	{
		while (!aTowerNodes.isEmpty()) {
			TowerNode towerNode = aTowerNodes.pop();
			for (String nodeName: towerNode.getTowerData().getUpgradeTo().keySet()) {
				towerNode.addUpgradeTo(myConstructedTowerNodes.get(nodeName));
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
//			ret.add(node.getID());
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
//			if (towernode.getID().equals(tower)) {
//				return towernode.getPrice();
//			}
		}
		// not a base tower
		return null;
	}
	/*
	public List<Tower> getPossibleUpgrades(Tower tower) {
		TowerNode towernode = myTowerNodeMap.get(tower);
//		return getTowerfromNode(towernode.getChildren());
	}
	*/

}
