package authoring.model;

import java.io.File;
import java.util.List;
import java.util.Map;

import utility.Point;

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
	private Point initialLocations;
	private String movement;
	
	public void setName(String name) throws Exception {
		if (name == null || name.length() == 0){
			throw new Exception("Must enter a valid name.");
		}
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setWeaponName(String weaponName) throws Exception{
		if (weaponName == null || weaponName.length() == 0){
			throw new Exception("Must enter a valid name.");
		}
		this.weaponName = weaponName;
	}
	
	public String getWeaponName(){
		return weaponName;
	}
	
	public void setBuyPrice(int buyPrice) throws Exception{
		if (buyPrice < 0){
			throw new Exception("Price cannot be negative.");
		}
		this.buyPrice = buyPrice;
	}
	
	public int getBuyPrice(){
		return buyPrice;
	}
	
	public void setSellPrice(int sellPrice) throws Exception{
		if (sellPrice < 0){
			throw new Exception("Price cannot be negative.");
		}
		this.sellPrice = sellPrice;
	}
	
	public int getSellPrice(){
		return sellPrice;
	}
	
	public void setMaxHealth(int maxHealth) throws Exception{
		if (maxHealth <= 0){
			throw new Exception("Health cannot be less than or equal to zero.");
		}
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
	
	public void setImagePath(String imagePath) throws Exception{
		if (!imagePath.endsWith(".png") || !(new File(imagePath).exists())){
			throw new Exception("Image file is invalid.");
		}
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
	
	public void setCollisionRadius(int collisionRadius) throws Exception{
		if (collisionRadius < 0){
			throw new Exception("Collision Radius cannot be negative.");
		}
		this.collisionRadius = collisionRadius;
	}
	
	public int getCollisionRadius(){
		return collisionRadius;
	}
	
	public void setInitialLocations(Point initialLocations){
		this.initialLocations = initialLocations;
	}
	
	public Point getInitialLocations(){
		return initialLocations;
	}
	
	public void setMovementStrategy(String movement){
		this.movement = movement;
	}
	
	public String getMovementStrategy(){
		return movement;
	}
}
