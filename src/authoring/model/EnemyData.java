package authoring.model;

public class EnemyData implements IReadableData {
	private String name;
	private String weaponName;
	private String imagePath;
	private int killReward;
	private int maxHealth;
	private int collisionRadius;
	private int speed;
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeaponName() {
		return weaponName;
	}
	public void setWeaponName(String weaponName) {
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
	public void setCollisionRadius(int collisionRadius){
		this.collisionRadius = collisionRadius;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
}
