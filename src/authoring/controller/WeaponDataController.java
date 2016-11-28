package authoring.controller;

import java.util.AbstractMap;

import authoring.model.EnemyData;
import authoring.model.WeaponData;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class WeaponDataController {

	private ObservableMap<String, WeaponData> myWeaponDataMap = FXCollections.observableHashMap();

	public ObservableMap<String, WeaponData> finalizeWeaponDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myWeaponDataMap;
	}
	
	public void createWeaponData(WeaponData weaponData){
		myWeaponDataMap.put(weaponData.getName(), weaponData);
	}
	
	public WeaponData getWeaponData(String weaponName){
		return myWeaponDataMap.get(weaponName);
	}
	
	public void updateWeaponData(String originalName, WeaponData updatedWeapon){
		myWeaponDataMap.remove(originalName);
		createWeaponData(updatedWeapon);
	}
	
	public void addControllerListener(MapChangeListener<String, WeaponData> listener){
		myWeaponDataMap.addListener(listener);
	}
}