package engine.model.components;

import java.util.List;

import engine.IObserver;
import engine.model.entities.IEntity;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import javafx.util.Pair;
import utility.Point;

/**
 * A component that defines an entities ability to move
 * @author matthewfaw, Weston
 *
 */
public class MoveableComponent implements IComponent, IMovable {
	private List<IObserver<IComponent>> myObservers;
	private PhysicalComponent myPhysical;
	
	private IMovementStrategy myMovementCalc;
	private double myTurnSpeed;
	private double myMoveSpeed;
	private IPhysical myGoal;
	
	private double myMaxDistance;
	private double myMovedDistance;
	
	//********************IObservable interface***********//
	@Override
	public void attach(IObserver<IComponent> aObserver) {
		myObservers.add(aObserver);		
	}

	@Override
	public void detach(IObserver<IComponent> aObserver) {
		myObservers.remove(aObserver);		
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(o -> o.update(this));		
	}

	//********************IComponent interface***********//
	@Override
	public IEntity getEntity() {
		return myPhysical.getEntity();
	}

	@Override
	public IModifiablePlayer getOwner() {
		return myPhysical.getOwner();
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
	
	public void move() {
		Pair<Double, Point> toMove = myMovementCalc.nextMove(this);
		myMovedDistance += toMove.getValue().getDistanceTo(getPosition());
		myPhysical.setPosition(toMove);
		if (myMovedDistance >= myMaxDistance){
			//Delete entity 
		}
	}

	//********************IPhysical interface***********//
	@Override
	public double getCollisionRadius() {
		return myPhysical.getCollisionRadius();
	}

	@Override
	public Point getPosition() {
		return myPhysical.getPosition();
	}

	@Override
	public double getHeading() {
		return myPhysical.getHeading();
	}

	@Override
	public List<String> getValidTerrains() {
		return myPhysical.getValidTerrains();
	}

	@Override
	public void setPosition(Pair<Double, Point> p) {
		myPhysical.setPosition(p);
	}


}
