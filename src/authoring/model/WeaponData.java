package authoring.model;

public class WeaponData {

	private String name;
	private String projectileName;
	private double range;
	private int fireRate;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setProjectileName(String projectileName){
		this.projectileName = projectileName;
	}
	
	public String getProjectileName(){
		return projectileName;
	}
	
	public void setRange(int range){
		this.range = range;
	}
	
	public double getRange(){
		return range;
	}
	
	public void setFireRate(int fireRate){
		this.fireRate = fireRate;
	}
	
	public int getFireRate(){
		return fireRate;
	}

}

