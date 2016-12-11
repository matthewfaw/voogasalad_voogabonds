package engine.model.components;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;
import engine.model.strategies.StrategyFactory;
import engine.model.systems.MovementSystem;
import javafx.util.Pair;
import utility.Point;

/**
 * A component that defines an entity's ability to move
 * @author matthewfaw, Weston
 *
 */
public class MoveableComponent extends AbstractComponent implements IMovable {

	//replace with: PhysicalSystem
//	private PhysicalComponent myPhysical;
	
	private IMovementStrategy myMovementCalc;
	private double myTurnSpeed;
	private double myMoveSpeed;
	
	@Hide
	private IPosition myGoal;
	
	//NOTE: So that entities can die after traveling a certain distance.
	private double myMaxDistance;
	@Hide
	private double myMovedDistance;

	public MoveableComponent (MovementSystem movement, ComponentData data) {
		myMovedDistance = 0;
		myMaxDistance = Double.parseDouble(data.getFields().get("myMaxDistance"));
		
		myTurnSpeed = Double.parseDouble(data.getFields().get("myTurnSpeed"));
		myMoveSpeed = Double.parseDouble(data.getFields().get("myMoveSpeed"));
		myMovementCalc = StrategyFactory.movementStrategy(data.getFields().get("myMovementCalc"));
		
		movement.attachComponent(this);
	}
	
	public Pair<Double, Point> getMove(IPhysical p) {
		Pair<Double, Point> nextMove = myMovementCalc.nextMove(this, p);
		myMovedDistance += nextMove.getValue().euclideanDistance(p.getPosition());
		//If myMovedDistance >= myMaxDistance, do something.
		return nextMove;
	}

	//********************IMovable interface***********//
	@Override
	public Point getGoal() {
		return myGoal == null ? null : myGoal.getPosition();
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

}
