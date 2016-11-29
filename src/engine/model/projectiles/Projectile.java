package engine.model.projectiles;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ProjectileData;
import engine.IObserver;
import engine.IViewable;
import engine.controller.timeline.TimelineController;
import engine.model.collision_detection.IDamageDealer;
import engine.model.game_environment.terrain.Terrain;
import engine.model.machine.Health;
import engine.model.machine.IHealth;
import engine.model.machine.Machine;
import javafx.util.Pair;
import utility.Damage;
import utility.Point;
import engine.model.strategies.*;
import engine.model.weapons.DamageInfo;
import engine.model.weapons.IKillerOwner;

/**
 * This class contains the information a projectile needs to move, deal damage to enemies, and be represented in the View.
 * @author Weston
 */
public class Projectile implements IViewable, IMovable, IObserver<TimelineController>, IDamageDealer {
	private static final double COLLISION_ERROR_TOLERANCE = Math.exp(-6);
	
	private String myImagePath;
	private IKillerOwner myOwner;
	private Machine myTarget;
	
	private IMovementStrategy myMovementCalc;
	private double mySpeed;
	private double myTurnSpeed;
	private double myTraveled;
	
	private double myHeading;
	private Point myLocation;
	private double myRadius;
	
	IDamageStrategy myDamageCalc;
	IHealth myDamage;
	int myMaxRange;
	int myAoERadius;
	
	List<Terrain> myValidTerrain;


	public Projectile(ProjectileData data, Machine target, IKillerOwner owner, TimelineController time) {
		
		myImagePath = data.getImagePath();
		myTarget = target;
		myOwner = owner;
		
		myMovementCalc = StrategyFactory.movementStrategy(data.getMovementStrategy());
		myLocation = myOwner.getLocation();
		myHeading = myOwner.getHeading();
		myTraveled = 0;
		mySpeed = data.getSpeed();
		myTurnSpeed = data.getTurnSpeed();
		myRadius = data.getCollisionRadius();
		
		myValidTerrain = data.getValidTerrains();

		myDamageCalc = StrategyFactory.damageStrategy(data.getDamageStrategy());
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		myDamage = new Health(data.getDamage());	
		
		time.attach(this);
	}


	@Override
	public double getHeading() {
		return myHeading;
	}

	@Override
	public Point getPosition() {
		return myLocation;
	}

	@Override
	public String getImagePath() {
		return myImagePath;
	}

	@Override
	public Point getLocation() {
		return myLocation;
	}

	@Override
	public Point getGoal() {
		return myTarget.onMap() ? myTarget.getPosition() : null;
	}

	@Override
	public double getTurnSpeed() {
		return myTurnSpeed;
	}

	@Override
	public double getMoveSpeed() {
		return mySpeed;
	}
	
	@Override
	public double getCollisionRadius() {
		return myRadius;
	}

	@Override
	public void update(TimelineController aChangedObject) {
		advance();
		
		//TODO: Remove if goes too far off map
		
	}
	
	private Point advance() {
		Pair<Double, Point> nextMove = myMovementCalc.nextMove(this);
		
		myTraveled += myLocation.euclideanDistance(nextMove.getValue());
		myHeading = nextMove.getKey();
		myLocation = nextMove.getValue();
		
//		notifyListenersUpdate();
		
		if (myTarget.onMap() && myTarget.getDistanceTo(myLocation) <= COLLISION_ERROR_TOLERANCE) {
			hitTarget();
		} else if (myTraveled >= myMaxRange) {
			explode();
		}
		
		return myLocation;
	}
	
	private void hitTarget() {
		myTarget.takeDamage(myDamageCalc.getTargetDamage());
		explode();
	}
	
	private void explode() {
		List<Machine> targets = new ArrayList<Machine>();
		//TODO: Get all Machines in AoE Range
		
		DamageInfo result = new DamageInfo();
		
		for (Machine m: targets) {
			Damage toDeal = myDamageCalc.getAoEDamage(this, myTarget.getPosition());
			result = result.add(m.takeDamage(toDeal));
		}
//		notifyListenersRemove();
		myOwner.notifyDestroy(result);
		
	}


	@Override
	public List<Terrain> getValidTerrains() {
		return myValidTerrain;
	}


	@Override
	public void setLocation(Point aLocation) {
		myLocation = aLocation;
	}

	/**
	 * IDamageDealer interface method
	 */
	@Override
	public IHealth getDamageDealt() {
		return myDamage;
	}
}
