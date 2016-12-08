package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.game_environment.paths.PathManager;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import javafx.util.Pair;
import utility.Point;

/**
 * A component that defines an entity's ability to move
 * @author matthewfaw, Weston
 *
 */
public class MoveableComponent implements IComponent, IMovable {

	//replace with: PhysicalSystem
//	private PhysicalComponent myPhysical;
	private IEntity myEntity;
	
	private IMovementStrategy myMovementCalc;
	private double myTurnSpeed;
	private double myMoveSpeed;
	private IPhysical myGoal;
	private PathManager myPath;
	
	//NOTE: So that entities can die after traveling a certain distance.
	private double myMaxDistance;
	private double myMovedDistance;


	
	public Pair<Double, Point> getMove(IPhysical p) {
		return myMovementCalc.nextMove(this, p);
	}

	//********************IComponent interface***********//
	@Override
	public IEntity getEntity() {
		return myEntity;
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
	
	public void setGoal(IPhysical p) {
		myGoal = p;
	}

}
