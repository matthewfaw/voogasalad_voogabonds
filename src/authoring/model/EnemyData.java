package authoring.model;

import java.util.List;

import authoring.model.map.TerrainData;

public class EnemyData {
	private String name;
	private String weaponName;
	private String imagePath;
	private int killReward;
	private int maxHealth;
	private int collisionRadius;
	private int speed;
	private List<String> terrainList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) throws Exception{
		if (name == null || name.length() == 0){
			throw new Exception("Enemy must have a name.");
		}
		this.name = name;
	}
	public String getWeaponName() {
		return weaponName;
	}
	public void setWeaponName(String weaponName) throws Exception{
		if (weaponName == null || weaponName.length() == 0){
			throw new Exception("Enemy must reference a valid weapon name.");
		}
		this.weaponName = weaponName;
	}
	public int getKillReward(){
		return killReward;
	}
	public void setKillReward(int killReward){
		this.killReward = killReward;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	public String getImagePath(){
		return imagePath;
	}
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	public int getCollisionRadius(){
		return collisionRadius;
	}
	public void setCollisionRadius(int collisionRadius) throws Exception{
		if (collisionRadius < 0){
			throw new Exception("Collision radius cannot be negative.");
		}
		this.collisionRadius = collisionRadius;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed) throws Exception{
		if (speed < 0){
			throw new Exception("Enemy cannot have negative speed.");
		}
		this.speed = speed;
	}
	public void setTerrainList(List<String> terrainList){
		this.terrainList = terrainList;
	}
	public List<String> getTerrainList(){
		return terrainList;
	}
	
}
