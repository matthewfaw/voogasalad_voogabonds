package engine.model.machine.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TowerNode {
	private Tower myTowerId;
	private int myPrice;
	private int mySellPrice;
	private TowerNode myParent;
	private List<TowerNode> myChildren = new ArrayList<TowerNode>();
	private Map<TowerNode, Integer> myUpgradeCostMap = new HashMap<TowerNode, Integer>();
	
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
	
	public int getPrice(){
		return myPrice;
	}
	
}
