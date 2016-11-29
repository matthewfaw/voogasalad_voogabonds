package engine.model.projectiles;
import java.util.ArrayList;
import java.util.List;
import authoring.model.ProjectileData;
import engine.IObserver;
import engine.IViewable;
import engine.controller.timeline.TimelineController;
import engine.model.collision_detection.ICollidable;
import engine.model.game_environment.terrain.Terrain;
import engine.model.machine.Machine;
import engine.model.playerinfo.IModifiablePlayer;
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

public class Projectile implements IViewable, IMovable, ICollidable, IObserver<TimelineController> {

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
	private double myCollisionRadius;
	
	IDamageStrategy myDamageCalc;
	double myDamage;
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
		myCollisionRadius = data.getCollisionRadius();
		
		myValidTerrain = data.getValidTerrains();
		myDamageCalc = StrategyFactory.damageStrategy(data.getDamageStrategy());
		myMaxRange = data.getMaxRange();
		myAoERadius = data.getAreaOfEffectRadius();
		myDamage = data.getDamage();	
		
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
		return myCollisionRadius;
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
			hitUnit(myTarget);
		} else if (myTraveled >= myMaxRange) {
			explode();
		}
		
		return myLocation;
	}
	
	private void hitUnit(Machine hit) {
		hit.takeDamage(myDamageCalc.getTargetDamage());
		explode();
	}
	
	private void explode() {
		List<Machine> targets = new ArrayList<Machine>();
		//TODO: Get all Machines in AoE Range
		
		DamageInfo result = new DamageInfo();
		
		for (Machine m: targets) {

			Damage toDeal;
			if (getOwner().isAlly(m.getOwner()))
				toDeal = myDamageCalc.getAoEAllyDamage(this, myTarget.getPosition());
			else
				toDeal = myDamageCalc.getAoEDamage(this, myTarget.getPosition());
			
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
	public void setPosition(Point aLocation) {
		myLocation = aLocation;
	}
	@Override
	public double getSize() {
		return myCollisionRadius;
	}
	
	@Override
	public IModifiablePlayer getOwner() {
		return myOwner.getOwner();
	}



	/********** ICollidable Interface Methods ************/

	@Override
	public void collideInto(ICollidable movedCollidable) {
		movedCollidable.collideInto(this);
	}
	
	@Override
	public void collideInto(Machine unmoved) {
		//This method is a bit of a mess; refactor?
		if (getOwner().isAlly(myTarget.getOwner()))
			if (getOwner().isAlly(unmoved.getOwner()))
				//Projectile targets allies, unmoved is an ally
				hitUnit((Machine) unmoved);
		else
			if (!getOwner().isAlly(unmoved.getOwner()))
				//Projectile targets enemies, unmoved is an enemy
				hitUnit((Machine) unmoved);
	}
	
	@Override
	public void collideInto(Projectile unmovedCollidable) {
		//Do nothing, probably
	}
}

