package authoring.controller;

import java.util.AbstractMap;

import authoring.model.ProjectileData;

public class ProjectileDataController {

	private AbstractMap<String, ProjectileData> myProjectileDataMap;

	public AbstractMap getProjectileDataMap(){
		return myProjectileDataMap;
	}
	
	public void createProjectileData(ProjectileData projectileData){
		myProjectileDataMap.put(projectileData.getName(), projectileData);
	}
	
	public ProjectileData getProjectileData(String projectileName){
		return myProjectileDataMap.get(projectileName);
	}
	
	public void updateTowerData(String originalName, ProjectileData updatedProjectile){
		myProjectileDataMap.remove(originalName);
		createProjectileData(updatedProjectile);
	}
}