package engine.model.components.concrete;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TargetingSystem;
import javafx.util.Pair;
import utility.Point;

/**
 * A component that defines an entity's ability to move
 * @author matthewfaw, Weston
 *
 */
public class MoveableComponent extends AbstractComponent implements IMovable {
	@Hide
	private transient MovementSystem myMovement;
	@Hide
	private transient PhysicalSystem myPhysical;
	@Hide
	private transient TargetingSystem myTargeting;
	@Hide
	private transient CollisionDetectionSystem myCollision;
	@Hide
	private transient DamageDealingSystem myDamage;
	
	private transient IMovementStrategy myMovementCalc;
	private double myTurnSpeed;
	private double myMoveSpeed;
	
	@Hide
	private transient IPosition myGoal;
	
	//NOTE: So that entities can die after traveling a certain distance.
	private boolean explodesAtMaxDistance;
	private double myMaxDistance;
	@Hide
	private double myMovedDistance;
	private boolean removeOnGoal;
	private boolean explodesOnGoal;

	

	public MoveableComponent(
			MovementSystem movement,
			PhysicalSystem physical,
			TargetingSystem targeting,
			CollisionDetectionSystem collision,
			DamageDealingSystem damage,
			ComponentData data
			) throws ClassNotFoundException {
		
		myMovement = movement;
		myPhysical = physical;
		myTargeting = targeting;
		myCollision = collision;
		myDamage = damage;
		
		myMovedDistance = 0;
		myMaxDistance = Double.parseDouble(data.getFields().get("myMaxDistance"));
		explodesAtMaxDistance = Boolean.parseBoolean(data.getFields().get("explodesAtMaxDistance"));
		
		explodesOnGoal = Boolean.parseBoolean(data.getFields().get("explodesOnGoal"));
		removeOnGoal = Boolean.parseBoolean(data.getFields().get("removeOnGoal"));
		
		myTurnSpeed = Double.parseDouble(data.getFields().get("myTurnSpeed"));
		myMoveSpeed = Double.parseDouble(data.getFields().get("myMoveSpeed"));
		myMovementCalc = movement.getStrategyFactory().newStrategy(data.getFields().get("myMovementCalc"));
		
		movement.attachComponent(this);
	}
	
	private Pair<Double, Point> getMove(IPhysical p) {
		if (myMaxDistance - myMovedDistance < myMoveSpeed)
			myMoveSpeed = Math.max(0.0, myMovedDistance - myMaxDistance);
		
		Pair<Double, Point> nextMove = myMovementCalc.nextMove(this, p);
		myMovedDistance += nextMove.getValue().euclideanDistance(p.getPosition());
		return nextMove;
	}

	//********************IMovable interface***********//
	@Override
	public Point getGoal() {
		return (myGoal == null) ? null : myGoal.getPosition();
	}

	@Override
	public double getTurnSpeed() {
		return myTurnSpeed;
	}

	@Override
	public double getMoveSpeed() {
		return myMoveSpeed;
	}
	
	public void setGoal(IPosition p) {
		myGoal = p;
	}

	public void move() {
		setGoal(myTargeting.getTarget(this));
		PhysicalComponent p = myPhysical.get(this);
		if (p != null)
			p.setPosition(getMove(p));
		
		myCollision.checkCollision(p);
		
		if ((myMovedDistance >= myMaxDistance && explodesAtMaxDistance) || (explodesOnGoal && atGoal())) {
			myDamage.explode(this);
		}
		
		if (removeOnGoal && atGoal()) {
			//subtract player's lives
			getEntity().delete();
		}
	}

	@Override
	public void delete() {
		myMovement.detachComponent(this);
	}

	private boolean atGoal() {
		IPhysical p = myPhysical.get(this);
		if (p != null)
			return myPhysical.get(this).getPosition().equals(getGoal());
		else
			return false;
	}
}
