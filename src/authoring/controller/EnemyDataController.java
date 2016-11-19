package authoring.controller;
import authoring.model.EnemyData;
import authoring.view.objects.FrontEndEnemy;

import java.util.AbstractMap;

public class EnemyDataController {
	private AbstractMap<String, EnemyData> myEnemyDataMap;
	
	public AbstractMap finalizeEnemyDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myEnemyDataMap;
	}
	
	public void createEnemyData(FrontEndEnemy enemy){
		//Parse the FrontEndEnemy object
		//Error check
		//Add it to map
	}
	
	
	public EnemyData getEnemyData(String enemyName){
		return myEnemyDataMap.get(enemyName);
	}
	
	
	public void updateEnemyData(String originalName, String updatedEnemy){
		//Find old enemyData in map
		//create new EnemyData Object from FrontEndEnemy
	}
	
}
