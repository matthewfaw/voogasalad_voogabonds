package authoring.controller;

import java.util.AbstractMap;

import authoring.model.ProjectileData;

public class ProjectileController {

	private AbstractMap<String, ProjectileData> myProjectileDataMap;

	public AbstractMap finalizeProjectileDataMap(){
		//TODO: Error checking to make sure that enemies at least exist
		return myProjectileDataMap;
	}
	
	public void createProjectileData(FrontEndProjectile projectile){
		//Parse the FrontEndEnemy object
		//Error check
		//Add it to map
	}
	
	
	public ProjectileData getProjectileData(String projectileName){
		return myProjectileDataMap.get(projectileName);
	}
	
	
	public ProjectileData updateTowerData(String originalName, FrontEndProjectile updatedEnemy){
		//Find old enemyData in map
		//create new EnemyData Object from FrontEndEnemy
	}
}