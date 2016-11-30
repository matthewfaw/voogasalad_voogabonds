package authoring.model;

import java.util.List;

import utility.ResouceAccess;

/**
 * An abstract class to contain all fields that both Towers and Enemies have.
 * @author Weston
 *
 */
abstract public class MachineData implements IReadableData {
	private static final String EMPTY_NAME_ERROR = "EmptyName";
	private static final String NEGATIVE_RADIUS_ERROR = "NegativeRadius";
	private static final String NEGATIVE_HEALTH_ERROR = "NegativeHealth";
	
	private String myName;
	private String myImagePath;
	
	private String myMoveStrategyName;
	private List<String> myValidTerrains;
	private double myCollisionRadius;
	private double myMoveSpeed;
	private double myTurnSpeed;
	
	private double myMaxHealth;
	private String myWeaponName;

	
	@Override
	public String getName() {
		return myName;
	}
	public void setName(String name) throws IllegalArgumentException {
		if (name == null || name.length() == 0){
			throw new IllegalArgumentException(ResouceAccess.getError(EMPTY_NAME_ERROR));
		}
		myName = name;
	}
	
	public String getWeaponName() {
		return myWeaponName;
	}
	public void setWeaponName(String name) throws Exception{
		if (name == null || name.length() == 0){
			throw new IllegalArgumentException(ResouceAccess.getError(EMPTY_NAME_ERROR));
		}
		myWeaponName = name;
	}
	
	public String getMoveStrategyName() {
		return myMoveStrategyName;
	}
	public void setMoveStrategyName(String name) throws IllegalArgumentException{
		if (name == null || name.length() == 0){
			throw new IllegalArgumentException(ResouceAccess.getError(EMPTY_NAME_ERROR));
		}
		myMoveStrategyName = name;
	}

	public double getMaxHealth(){
		return myMaxHealth;
	}
	public void setMaxHealth(double maxHealth){
		if (maxHealth < 0){
			throw new IllegalArgumentException(ResouceAccess.getError(NEGATIVE_HEALTH_ERROR));
		}
		myMaxHealth = maxHealth;
	}
	
	public String getImagePath(){
		return myImagePath;
	}
	
	public void setImagePath(String imagePath){
		myImagePath = imagePath;
	}
	public double getCollisionRadius(){
		return myCollisionRadius;
	}
	public void setCollisionRadius(double collisionRadius) throws Exception{
		if (collisionRadius < 0){
			throw new IllegalArgumentException(ResouceAccess.getError(NEGATIVE_RADIUS_ERROR));
		}
		myCollisionRadius = collisionRadius;
	}
	
	public double getMoveSpeed(){
		return myMoveSpeed;
	}
	public void setMoveSpeed(double speed){
		myMoveSpeed = speed;
	}
	public double getTurnSpeed(){
		return myTurnSpeed;
	}
	public void setTurnSpeed(double speed){
		myTurnSpeed = speed;
	}
	
	public void setTerrainList(List<String> terrainList){
		myValidTerrains = terrainList;
	}
	public List<String> getTerrainList(){
		return myValidTerrains;
	}
	
}
