package authoring.controller;

import authoring.model.IReadableData;
import authoring.model.TowerData;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;


public class TowerDataController implements IDataController {

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

	
    @Override
    public IReadableData getObjectData (String name) {
        return getTowerData(name);
    }

    @Override
    public void createObjectData (IReadableData data) {
        createTowerData((TowerData)data);
    }

    @Override
    public void updateObjectData (String oldName, IReadableData data) throws Exception {
        if (!myTowerDataMap.containsKey(oldName)) {
            throw new Exception("This tower has not been created.");
        }
        updateTowerData(oldName, (TowerData)data);
    }
}
