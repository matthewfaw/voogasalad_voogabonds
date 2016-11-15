package authoring.model;

public class EnemyData {
	private String name;
	private String weaponName;
	private String imagePath;
	private int killReward;
	private int maxHealth;
	private int collisionRadius;
	
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
	
	
}
