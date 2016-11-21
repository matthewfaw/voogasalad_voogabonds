package authoring.controller;

import java.util.AbstractMap;

import authoring.model.EnemyData;
import authoring.model.WeaponData;

public class WeaponDataController {

	private AbstractMap<String, WeaponData> myWeaponDataMap;

	public AbstractMap finalizeWeaponDataMap(){
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
}