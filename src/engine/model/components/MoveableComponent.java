package engine.model.components;

import authoring.model.ComponentData;
import engine.model.game_environment.paths.PathManager;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;
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
	private IPosition myGoal;
	private PathManager myPath;
	
	//NOTE: So that entities can die after traveling a certain distance.
	private double myMaxDistance;
	private double myMovedDistance;

	public MoveableComponent (MovementSystem movement, ComponentData data) {
		myMovedDistance = 0;
		
		//TODO: get fields from data
		
		
		movement.attachComponent(this);
	}

	
	public Pair<Double, Point> getMove(IPhysical p) {
		return myMovementCalc.nextMove(this, p);
	}

	//********************IMovable interface***********//
	@Override
	public Point getGoal() {
		return myGoal.getPosition();
	}

	@Override
	public double getTurnSpeed() {
		return myTurnSpeed;
	}

	@Override
	public double getMoveSpeed() {
		return myMoveSpeed;
	}

	@Override
	public PathManager getPath() {
		return myPath;
	}
	
	public void setGoal(IPosition p) {
		myGoal = p;
	}

}
