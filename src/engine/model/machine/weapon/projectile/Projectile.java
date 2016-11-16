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
	
	IMovementStrategy myMovement;
	IDamageStrategy myDamage;
	int myMaxRange;
	int myAoERadius;
	Machine myTarget;
	
	double myTraveled;
	double myHeading;
	Point myLocation;

	public Projectile(ProjectileData data, Machine target, double heading, Point position) {
	}

	public Projectile(ProjectileData data, Machine target, Observer<IViewable> observer,
			ProjectileOwner owner, double heading, Point position) {
		
		myLocation = position;
		myHeading = heading;
		myTraveled = 0;
		myTarget = target;
		
		myImagePath = data.getImagePath();
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		
		/*
		myMovement = StrategyFactory.movementStrategy(data.getMovementStrategy());
		myDamage = StrategyFactory.damageStrategy(data.getDamageStrategy());
		*/
	}

	@Override
	public Point advance() {
		Pair<Double, Point> nextMove = myMovement.nextMove(this);
		
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
		myTarget.takeDamage(myDamage.getTargetDamage());
		explode();
	}
	
	private void explode() {
		List<Machine> targets = new ArrayList<Machine>();
		//TODO: Get all Machines in AoE Range
		
		for (Machine m: targets) {
			Damage toDeal = myDamage.getAoEDamage(this, myTarget.getLocation());
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
