package engine.model.machine.weapon.projectile;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ProjectileData;
import engine.IViewable;
import engine.Observer;
import engine.model.machine.Machine;
import javafx.util.Pair;
import utility.Damage;
import utility.Point;
import engine.model.strategies.*;

public class Projectile implements IProjectile, IViewable {
	private static final double COLLISION_RADIUS = Math.exp(-6);
	
	String myImagePath;
	Observer<IViewable> myObserver;
	ProjectileOwner myOwner;
	
	IMovementStrategy myMovementCalc;
	double mySpeed;
	IDamageStrategy myDamageCalc;
	double myDamage;
	int myMaxRange;
	int myAoERadius;
	Machine myTarget;
	
	double myTraveled;
	double myHeading;
	Point myLocation;

	public Projectile(ProjectileData data, Machine target, ProjectileOwner owner, Observer<IViewable> observer,
			double heading, Point position) {
		
		myLocation = position;
		myHeading = heading;
		mySpeed = data.getSpeed();
		myTraveled = 0;
		myTarget = target;
		
		myImagePath = data.getImagePath();
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		myDamage = data.getDamage();
		
		/*
		myMovement = StrategyFactory.movementStrategy(data.getMovementStrategy());
		myDamage = StrategyFactory.damageStrategy(data.getDamageStrategy());
		*/
		
		notifyListenersAdd();
	}

	@Override
	public Point advance() {
		Pair<Double, Point> nextMove = myMovementCalc.nextMove(this);
		
		myTraveled += myLocation.euclideanDistance(nextMove.getValue());
		myHeading = nextMove.getKey();
		myLocation = nextMove.getValue();
		
		notifyListenersUpdate();
		
		if (myTarget.getDistanceTo(myLocation) <= COLLISION_RADIUS) {
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
		
		for (Machine m: targets) {
			Damage toDeal = myDamageCalc.getAoEDamage(this, myTarget.getLocation());
			m.takeDamage(toDeal);
		}
		notifyListenersRemove();
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
 


	
	

}
