package authoring.controller;

import java.util.AbstractMap;

import authoring.model.TowerData;
import authoring.view.objects.FrontEndTower;

public class TowerDataController {

	private AbstractMap<String, TowerData> myTowerDataMap;

	public AbstractMap finalizeTowerDataMap(){
		return myTowerDataMap;
	}
	
	
	//public void createTowerData(FrontEndTower tower){
	public void createTowerData(String towerName, TowerData towerData){
		//Parse the FrontEndEnemy object
		//Error check
		//Add it to map
		myTowerDataMap.put(towerName, towerData);
	}
	
	
	public TowerData getTowerData(String towerName){
		return myTowerDataMap.get(towerName);
	}
	
	
	public void updateTowerData(String originalName, String updatedTower){
		//Find old enemyData in map
		//create new EnemyData Object from FrontEndEnemy
	}
}
