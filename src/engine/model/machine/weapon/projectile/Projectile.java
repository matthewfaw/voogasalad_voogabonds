package engine.model.machine.weapon.projectile;

import java.util.ArrayList;
import java.util.List;

import engine.model.machine.Machine;
import javafx.util.Pair;
import utility.Damage;
import utility.Point;
import engine.model.strategies.*;

public class Projectile implements IProjectile {
	private static final double COLLISION_RADIUS = Math.exp(-6);
	
	String myImagePath;
	IMovementStrategy myMovement;
	IDamageStrategy myDamage;
	int myMaxRange;
	int myAoERadius;
	Machine myTarget;
	
	double myTraveled;
	double myHeading;
	Point myLocation;

	public Projectile(ProjectileData data, Machine target, double heading, Point position) {
		myLocation = position;
		myHeading = heading;
		myTraveled = 0;
		myTarget = target;
		
		myImagePath = data.getImagePath();
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		
		myMovement = StrategyFactory.movementStrategy(data.getMovementStrategy());
		myDamage = StrategyFactory.damageStrategy(data.getDamageStrategy());
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
			Damage toDeal = myDamage.getAoEDamage(myLocation, myHeading, myTarget.getLocation());
			m.takeDamage(toDeal);
		}
		notifyListenersRemove();
	}

	private void notifyListenersRemove() {
		// TODO Auto-generated method stub
		
	}
	
	private void notifyListenersUpdate() {
		// TODO Auto-generated method stub
		
	}
 


	
	

}
