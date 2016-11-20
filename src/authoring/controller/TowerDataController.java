package authoring.controller;

import java.util.AbstractMap;

import authoring.model.TowerData;

public class TowerDataController {

	private AbstractMap<String, TowerData> myTowerDataMap;

	public AbstractMap finalizeTowerDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myTowerDataMap;
	}
	
	public void createTowerData(String tower){
		//Parse the FrontEndEnemy object
		//Error check
		//Add it to map
	}
	
	
	public TowerData getTowerData(String towerName){
		return myTowerDataMap.get(towerName);
	}
	
	
	public void updateTowerData(String originalName, String updatedTower){
		//Find old enemyData in map
		//create new EnemyData Object from FrontEndEnemy
	}
}
