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


	public Projectile(ProjectileData data, Machine target, IKillerOwner owner, Observer<IViewable> observer,
			double heading, Point position) {
		
		myLocation = position;
		myHeading = heading;
		mySpeed = data.getSpeed();
		myTurnSpeed = data.getTurnSpeed();
		myTraveled = 0;
		myTarget = target;
		
		myImagePath = data.getImagePath();
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		myDamage = data.getDamage();
		
		myOwner = owner;
		
		/*
		myMovementCalc = StrategyFactory.movementStrategy(data.getMovementStrategy());
		myDamage = StrategyFactory.damageStrategy(data.getDamageStrategy());
		*/
		
		//TODO: Use StrategyFactory for Strategy assignment
		myMovementCalc = new GreedyMovementStrategy();
		myDamageCalc = new ConstantDamageStrategy();
		
		
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
		myObserver.add((IViewable) this);
		
	}

	private void notifyListenersRemove() {
		myObserver.remove((IViewable) this);
		
	}
	
	private void notifyListenersUpdate() {
		myObserver.update((IViewable) this);
		
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

}
