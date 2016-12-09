package engine.model.resourcestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import authoring.model.EntityData;
import authoring.model.TowerData;
import engine.model.machine.tower.ITowerUpgradeStore;
import engine.model.machine.tower.TowerNode;

/**
 * Tower Upgrade Store that stores the hierarchies among towers
 * @author Owen Chung and matthewfaw
 */


public class TowerUpgradeStore implements ITowerUpgradeStore {
	private ArrayList<EntityNode> myBaseTowers;
	private Map<String, TowerNode> myConstructedTowerNodes;
	
	public TowerUpgradeStore(List<EntityData> aTowerInfoList) {
		myBaseTowers = new ArrayList<EntityNode>();
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
			TowerData towerData = towerNode.getTowerData();
			if (!towerData.getUpgrades().isEmpty()) {
				for (String nodeName: towerData.getUpgrades().keySet()) {
					towerNode.addUpgradeTo(myConstructedTowerNodes.get(nodeName));
				}
			}
		}
	}

	/**
	 * get all of the base towers data in the game
	 */
	@Override
	public List<TowerData> getBaseTowersData() {
		return convertTowerNodesToTowerData(myBaseTowers);
	}

	/**
	 * TODO
	 * return the price of a tower if it's a base tower, otherwise return null
	 * @param tower
	 * @return
	 */
	public int getPrice(String aTowerName) {
		return myConstructedTowerNodes.get(aTowerName).getTowerData().getBuyPrice();
	}
	public List<TowerData> getPossibleUpgrades(TowerData tower) 
	{
		List<TowerNode> upgradeNodes = myConstructedTowerNodes.get(tower.getName()).getUpgradeTos();
		return convertTowerNodesToTowerData(upgradeNodes);
	}
	
	/**
	 * A helper method to convert a list of TowerNodes to a list of TowerData
	 * @param aTowerNodes
	 * @return corresponding list of tower data
	 */
	private List<TowerData> convertTowerNodesToTowerData(List<TowerNode> aTowerNodes)
	{
		return aTowerNodes.stream().map(towerNode -> towerNode.getTowerData()).collect(Collectors.toList());
	}

}
