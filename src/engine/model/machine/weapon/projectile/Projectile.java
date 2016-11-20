package engine.model.machine.weapon.projectile;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ProjectileData;
import engine.IViewable;
import engine.Observer;
import engine.model.machine.Machine;
import engine.model.machine.weapon.DamageInfo;
import engine.model.machine.weapon.IKillerOwner;
import javafx.util.Pair;
import utility.Damage;
import utility.Point;
import engine.model.strategies.*;

/**
 * This class contains the information a projectile needs to move, deal damage to enemies, and be represented in the View.
 * @author Weston
 */
public class Projectile implements IProjectile, IViewable, IMovable {
	private static final double COLLISION_ERROR_TOLERANCE = Math.exp(-6);
	
	String myImagePath;
	Observer<IViewable> myObserver;
	IKillerOwner myOwner;
	Machine myTarget;
	
	IMovementStrategy myMovementCalc;
	double mySpeed;
	double myTurnSpeed;
	double myTraveled;
	double myHeading;
	Point myLocation;
	
	IDamageStrategy myDamageCalc;
	double myDamage;
	int myMaxRange;
	int myAoERadius;


	public Projectile(ProjectileData data, Machine target, IKillerOwner owner, Observer<IViewable> observer) {
		myImagePath = data.getImagePath();
		myTarget = target;
		myOwner = owner;
		
		myMovementCalc = StrategyFactory.movementStrategy(data.getMovementStrategy());
		myLocation = myOwner.getLocation();
		myHeading = myOwner.getHeading();
		myTraveled = 0;
		mySpeed = data.getSpeed();
		myTurnSpeed = data.getTurnSpeed();

		myDamageCalc = StrategyFactory.damageStrategy(data.getDamageStrategy());
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		myDamage = data.getDamage();		
		
		notifyListenersAdd();
	}

	@Override
	public Point advance() {
		Pair<Double, Point> nextMove = myMovementCalc.nextMove(this);
		
		myTraveled += myLocation.euclideanDistance(nextMove.getValue());
		myHeading = nextMove.getKey();
		myLocation = nextMove.getValue();
		
		notifyListenersUpdate();
		
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
		return myTarget.getLocation();
	}

	@Override
	public double getTurnSpeed() {
		return myTurnSpeed;
	}

	@Override
	public double getMoveSpeed() {
		return mySpeed;
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
			Damage toDeal = myDamageCalc.getAoEDamage(this, myTarget.getLocation());
			result = result.add(m.takeDamage(toDeal));
		}
		notifyListenersRemove();
		myOwner.notifyDestroy(result);
		
	}
	
	private void notifyListenersAdd() {
		myObserver.add(this);
		
	}

	private void notifyListenersRemove() {
		myObserver.remove((IViewable) this);
		
	}
	
	private void notifyListenersUpdate() {
		myObserver.update((IViewable) this);
		
	}

}
