package authoring.view.buttons;

import authoring.authoring_interfaces.IEnemy;
import authoring.authoring_interfaces.IMachine;
import authoring.authoring_interfaces.IWeapon;
import javafx.geometry.Point2D;

public class FrontEndEnemy implements IEnemy, IMachine{
	
	private double mySpeed;
	private Point2D mySpawnPoint;
	private Point2D myEndPoint;
	private String myName;
	private double myHealth;
	private IWeapon myWeapon;
	private String myType;
	private String myImage;
	private String mySound;
	
	public FrontEndEnemy(double speed, Point2D spawn, Point2D end, String name, double health, String type, 
			String image){
		mySpeed = speed;
		mySpawnPoint = spawn;
		myEndPoint = end;
		myName = name;
		myHealth = health;
		myType = type;
		myImage = image;
	}

	@Override
	public double getSpeed() {
		return mySpeed;
	}

	@Override
	public Point2D getSpawnPoint() {
		return mySpawnPoint;
	}

	@Override
	public Point2D getEndPoint() {
		return myEndPoint;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public double getHealth() {
		return myHealth;
	}

	@Override
	public IWeapon getWeapon() {
		return myWeapon;
	}

	@Override
	public String getType() {
		return myType;
	}

	@Override
	public String getImage() {
		return myImage;
	}

	@Override
	public String getSound() {
		return mySound;
	}

}
