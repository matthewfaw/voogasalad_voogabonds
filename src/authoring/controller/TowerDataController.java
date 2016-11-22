package authoring.controller;

import java.util.AbstractMap;
import java.util.HashMap;

import authoring.model.TowerData;

public class TowerDataController {

	private AbstractMap<String, TowerData> myTowerDataMap = new HashMap<String, TowerData>();

	public AbstractMap finalizeTowerDataMap(){
		return myTowerDataMap;
	}
	
	public void createTowerData(TowerData towerData){
		myTowerDataMap.put(towerData.getName(), towerData);
	}
	
	public TowerData getTowerData(String towerName){
		return myTowerDataMap.get(towerName);
	}
	
	public void updateTowerData(String originalName, TowerData updatedTower){
		myTowerDataMap.remove(originalName);
		createTowerData(updatedTower);
	}
}
