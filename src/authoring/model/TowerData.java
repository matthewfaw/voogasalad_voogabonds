package authoring.model;

import java.io.File;
import java.util.List;
import java.util.Map;
import utility.Point;

public class TowerData implements IReadableData {

	private String name;
	private String weaponName;
	private int buyPrice;
	private int sellPrice;
	private int maxHealth;
	private Map<String, Integer> upgradeFrom;
	private String imagePath;
	private List<String> traversableTerrain;
	private int collisionRadius;
	private List<Point> initialLocations;
	private String movement;
	
	public void setName(String name) throws Exception {
		if (name == null || name.length() == 0){
			throw new Exception("Must enter a valid name.");
		}
		this.name = name;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	public void setWeaponName(String weaponName) throws Exception{
		if (weaponName == null || weaponName.length() == 0){
			throw new Exception("Must enter a valid weapon name.");
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
	
	public void addUpgradeFrom(String name, Integer upgradeCost){
		this.upgradeFrom.put(name, upgradeCost);
	}
	
	public Map<String, Integer> getUpgradeFrom(){
		return upgradeFrom;
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
	
	public void setInitialLocations(List<Point> initialLocations){
		this.initialLocations = initialLocations;
	}
	
	public List<Point> getInitialLocations(){
		return initialLocations;
	}
	
	public void setMovementStrategy(String movement){
		this.movement = movement;
	}
	
	public String getMovementStrategy(){
		return movement;
	}
}
