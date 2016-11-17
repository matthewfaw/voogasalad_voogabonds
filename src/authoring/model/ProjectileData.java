package authoring.model;

import java.util.List;

public class ProjectileData {

	private String name;
	private int maxRange;
	private int areaOfEffectRadius;
	private String imagePath;
	private String damage;
	private String movement;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setMaxRange(int maxRange){
		this.maxRange = maxRange;
	}
	
	public int getMaxRange(){
		return maxRange;
	}
	
	public void setAreaOfEffectRadius(int AOERadius){
		this.areaOfEffectRadius = AOERadius;
	}
	
	public int getAreaOfEffectRadius(){
		return areaOfEffectRadius;
	}
	
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	
	public String getImagePath(){
		return imagePath;
	}
	
	public void setDamageStrategy(String damage){
		this.damage = damage;
	}
	
	public String getDamageStrategy(){
		return damage;
	}
	
	public void setMovementStrategy(String movement){
		this.movement = movement;
	}
	
	public String getMovementStrategy(){
		return movement;
	}
}
