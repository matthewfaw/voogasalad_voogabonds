package authoring.controller;
import authoring.model.EnemyData;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import java.util.List;


public class EnemyDataController{
	private ObservableMap<String, EnemyData> myEnemyDataMap = FXCollections.observableHashMap();
	
	public ObservableMap finalizeEnemyDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myEnemyDataMap;
	}
	
	public void createEnemyData(EnemyData enemy){
		myEnemyDataMap.put(enemy.getName(), enemy);
	}
	
	public EnemyData getEnemyData(String enemyName){
		return myEnemyDataMap.get(enemyName);
	}
	
	public void updateEnemyData(String originalName, EnemyData updatedEnemy){
		myEnemyDataMap.remove(originalName);
		createEnemyData(updatedEnemy);
	}
	
	public void addControllerListener(MapChangeListener<String, EnemyData> listener){
		myEnemyDataMap.addListener(listener);
	}
}
