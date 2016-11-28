package authoring.controller;

import authoring.model.TowerData;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class TowerDataController {

	private ObservableMap<String, TowerData> myTowerDataMap = FXCollections.observableHashMap();

	public ObservableMap<String, TowerData> finalizeTowerDataMap(){
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
	
	public void addControllerListener(MapChangeListener<String, TowerData> listener){
                myTowerDataMap.addListener(listener);
        }
}
