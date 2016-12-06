package engine.model.components;

import engine.model.entities.IEntity;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
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
	
	//XXX: ???
	private double myMaxDistance;
	private double myMovedDistance;
	

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
	
	//TODO
	public void move() {
		/*
		Pair<Double, Point> toMove = myMovementCalc.nextMove(this);
		myMovedDistance += toMove.getValue().getDistanceTo(getPosition());
		myPhysical.setPosition(toMove);
		if (myMovedDistance >= myMaxDistance){
			//Delete entity 
		}
		*/
	}


}
