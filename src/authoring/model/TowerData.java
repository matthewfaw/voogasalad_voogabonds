package authoring.model;

import java.util.List;
import java.util.Map;

public class TowerData {

	private String name;
	private String weaponName;
	private int buyPrice;
	private int sellPrice;
	private int maxHealth;
	private List<Map<String, Integer>> upgrades;
	private String imagePath;
	private List<String> traversableTerrain;
	private int collisionRadius;
	private List<Map<Integer, Integer>> initialLocations;
	private String movement;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setWeaponName(String weaponName){
		this.weaponName = weaponName;
	}
	
	public String getWeaponName(){
		return weaponName;
	}
	
	public void setBuyPrice(int buyPrice){
		this.buyPrice = buyPrice;
	}
	
	public int getBuyPrice(){
		return buyPrice;
	}
	
	public void setSellPrice(int sellPrice){
		this.sellPrice = sellPrice;
	}
	
	public int getSellPrice(){
		return sellPrice;
	}
	
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public void setUpgrades(List<Map<String, Integer>> upgrades){
		this.upgrades = upgrades;
	}
	
	public List<Map<String, Integer>> getUpgrades(){
		return upgrades;
	}
	
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	
	public String getImagePath(){
		return imagePath;
	}
	
	public void setTraversableTerrain(List<String> traversableTerrain){
		this.traversableTerrain = traversableTerrain;
	}
	
	public List<String> getTraversableTerrain(){
		return traversableTerrain;
	}
	
	public void setCollisionRadius(int collisionRadius){
		this.collisionRadius = collisionRadius;
	}
	
	public int getCollisionRadius(){
		return collisionRadius;
	}
	
	public void setInitialLocations(List<Map<Integer, Integer>> initialLocations){
		this.initialLocations = initialLocations;
	}
	
	public List<Map<Integer, Integer>> getInitialLocations(){
		return initialLocations;
	}
	
	public void setMovementStrategy(String movement){
		this.movement = movement;
	}
	
	public String getMovementStrategy(){
		return movement;
	}
}
