package authoring.controller;

import authoring.model.TowerData;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;


public class TowerDataController {

	private ObservableMap<String, TowerData> myTowerDataMap = FXCollections.observableHashMap();

	/**
	 * @return
	 */
	public ObservableMap<String, TowerData> finalizeTowerDataMap(){
		return myTowerDataMap;
	}
	
	/**
	 * @param towerData
	 */
	public void createTowerData(TowerData towerData){
		myTowerDataMap.put(towerData.getName(), towerData);
	}
	
	/**
	 * @return
	 */
	public int getNumTowers() {
	        return myTowerDataMap.size();
	}
	
	/**
	 * @param towerName
	 * @return
	 */
	public TowerData getTowerData(String towerName){
		return myTowerDataMap.get(towerName);
	}
	
	/**
	 * @param originalName
	 * @param updatedTower
	 */
	public void updateTowerData(String originalName, TowerData updatedTower){
		myTowerDataMap.remove(originalName);
		createTowerData(updatedTower);
	}
	
	/**
	 * @param listener
	 */
	public void addControllerListener(MapChangeListener<String, TowerData> listener){
                myTowerDataMap.addListener(listener);
        }
}
