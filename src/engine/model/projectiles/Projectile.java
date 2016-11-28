package engine.model.projectiles;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ProjectileData;
import engine.IViewable;
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
public class Projectile implements IProjectile, IViewable, IMovable {
	private static final double COLLISION_ERROR_TOLERANCE = Math.exp(-6);
	
	String myImagePath;
	IKillerOwner myOwner;
	Machine myTarget;
	
	IMovementStrategy myMovementCalc;
	double mySpeed;
	double myTurnSpeed;
	double myTraveled;
	double myHeading;
	Point myLocation;
	double myRadius;
	
	IDamageStrategy myDamageCalc;
	double myDamage;
	int myMaxRange;
	int myAoERadius;


	public Projectile(ProjectileData data, Machine target, IKillerOwner owner) {
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

		myDamageCalc = StrategyFactory.damageStrategy(data.getDamageStrategy());
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		myDamage = data.getDamage();		
		
//		notifyListenersAdd();
	}

	@Override
	public Point advance() {
		Pair<Double, Point> nextMove = myMovementCalc.nextMove(this);
		
		myTraveled += myLocation.euclideanDistance(nextMove.getValue());
		myHeading = nextMove.getKey();
		myLocation = nextMove.getValue();
		
//		notifyListenersUpdate();
		
		if (myTarget.getDistanceTo(myLocation) <= COLLISION_ERROR_TOLERANCE) {
			hitTarget();
		} else if (myTraveled >= myMaxRange) {
			explode();
		}
		
		return myLocation;
	}
	


	@Override
	public Machine getTargetMachine() {
		return myTarget;
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
		return myTarget.getPosition();
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
}
