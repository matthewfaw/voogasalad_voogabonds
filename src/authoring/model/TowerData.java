package authoring.model;

import java.util.List;

public class TowerData {

	private String name;
	private String weaponName;
	private int buyPrice;
	private int sellPrice;
	private int maxHealth;
	private List<Entry<String, Integer>> upgrades;
	private String imagePath;
	private List<Terrain> traversableTerrain;
	private int collisionRadius;
	private List<Point> initialLocations;
	private MovementStrategy movement;
	
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
	
	public void setUpgrades(List<Entry<String, Integer>> upgrades){
		this.upgrades = upgrades;
	}
	
	public List<Entry<String, Integer>> getUpgrades(){
		return upgrades;
	}
	
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	
	public String getImagePath(){
		return imagePath;
	}
	
	public void setTraversableTerrain(List<Terrain> traversableTerrain){
		this.traversableTerrain = traversableTerrain;
	}
	
	public List<Terrain> getTraversableTerrain(){
		return traversableTerrain;
	}
	
	public void setCollisionRadius(int collisionRadius){
		this.collisionRadius = collisionRadius;
	}
	
	public int getCollisionRadius(){
		return collisionRadius;
	}
	
	public void setInitialLocations(List<Point> initialLocations){
		this.initialLocations = initialLocations;
	}
	
	public List<Point> getInitialLocations(){
		return initialLocations;
	}
	
	public void setMovementStrategy(MovementStrategy movement){
		this.movement = movement;
	}
	
	public MovementStrategy getMovementStrategy(){
		return movement;
	}
}
