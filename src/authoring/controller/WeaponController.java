package authoring.controller;

import java.util.AbstractMap;

import authoring.model.EnemyData;
import authoring.model.WeaponData;

public class WeaponController {

	private AbstractMap<String, WeaponData> myWeaponDataMap;

	public AbstractMap finalizeWeaponDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myWeaponDataMap;
	}
	
	public void createTowerData(String weapon){
		//Parse the FrontEndEnemy object
		//Error check
		//Add it to map
	}
	
	
	public WeaponData getWeaponData(String weaponName){
		return myWeaponDataMap.get(weaponName);
	}
	
	
	public void updateWeaponData(String originalName, String updatedWeapon){
		//Find old enemyData in map
		//create new EnemyData Object from FrontEndEnemy
	}
}