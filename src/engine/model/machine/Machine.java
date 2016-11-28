package engine.model.machine;

import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.game_environment.terrain.Terrain;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.strategies.IMovable;
import engine.model.weapons.DamageInfo;
import engine.model.weapons.IWeapon;
import utility.Damage;
import utility.Point;

abstract public class Machine implements IViewableMachine, IModifiableMachine, IMovable, IObserver<TimelineController> {

	private IModifiablePlayer myModifiablePlayer;
	private Health myHealth;
	private double myHeading;
	private Point myPosition;
	private double myTurnSpeed;
	private double myMoveSpeed;
	private double myCollisionRadius;
	private List<Terrain> myValidTerrains;
	private IWeapon myWeapon;
	private String myName;
	
	public Machine () {
		
	}
	
	public Machine(String name, int maxHealth) {
		myName = name;
		myHealth = new Health(maxHealth);
	}
	
	public String getName() {
		return myName;
	}
	
	@Deprecated
	public void setPossibleTerrains(List<Terrain> l) {
		myValidTerrains = l;
	}

	@Override
	public void update(TimelineController time) {
		move();
		myWeapon.fire(myHeading, myPosition);
	}
	
	private void move() {
		//TODO: Move with movement strategy
	}

	@Override
	public String getImagePath() {
		return null;
	}

	@Override
	public double getHeading() {
		return myHeading;
	}


	@Override
	public double getHealth() {
		return myHealth.getHealth();
	}
	
	@Override
	public DamageInfo takeDamage(Damage dmg) {
		double dmgTaken = myHealth.takeDamage(dmg);
		
		int unitsKilled = 0;
		int moneyEarned = 0;
		
		if (myHealth.getHealth() <= 0){
			unitsKilled++;
			moneyEarned = die();
		}
		
		return new DamageInfo(dmgTaken, unitsKilled, moneyEarned);
	}
	
	abstract protected int die();

	public double getDistanceTo(Point p) {
		return getPosition().euclideanDistance(p) - myCollisionRadius;
	}
	
	public IModifiablePlayer getModifiablePlayerInfo() {
		return myModifiablePlayer;
	}

	public boolean onMap() {
		return true;
	}
	
	@Override
	public Point getPosition() {
		return myPosition;
	}
	
	@Override
	public Point getGoal() {
		return null;
	}
	@Override
	public double getTurnSpeed() {
		return myTurnSpeed;
	}
	@Override
	public double getMoveSpeed() {
		return myMoveSpeed;
	}
	@Override
	public double getCollisionRadius() {
		return myCollisionRadius;
	}
	
	@Override
	public List<Terrain> getValidTerrains() {
		return myValidTerrains;
	}

	@Override
	public void setPosition(Point p) {
		myPosition = p;
	}

	@Override
	public double getSize() {
		return myCollisionRadius;
	}

	
}
