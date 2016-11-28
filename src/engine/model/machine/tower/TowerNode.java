package engine.model.machine.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.model.resourcestore.IMoney;
import engine.model.resourcestore.Money;

public class TowerNode {
	private Tower myTowerId;
	private IMoney myPrice;
	private IMoney mySellPrice;
	private TowerNode myParent;
	private List<TowerNode> myChildren = new ArrayList<TowerNode>();
	private Map<TowerNode, IMoney> myUpgradeCostMap = new HashMap<TowerNode, IMoney>();
	
	public TowerNode(Tower id, TowerNode parent){
		myTowerId = id;
		myParent = parent;
	}
	
	public List<TowerNode> getChildren(){
		return myChildren;
	}
	
	public TowerNode getParent(){
		return myParent;
	}

	public Tower getID() {
		return myTowerId;
	}
	
	public IMoney getPrice(){
		return myPrice;
	}
	
	public IMoney getSellPrice(){
		return mySellPrice;
	}

	public void setSellPrice(int sellPrice) {
		mySellPrice = new Money(sellPrice);
	}
	
	public void setPrice(int price) {
		myPrice = new Money(price);
	}
}
