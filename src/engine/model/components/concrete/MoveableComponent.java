package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewable;
import engine.model.components.viewable_interfaces.IViewableMovable;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import engine.model.systems.TargetingSystem;
import gamePlayerView.gamePlayerView.Router;
import javafx.util.Pair;
import utility.Point;

/**
 * A component that defines an entity's ability to move
 * @author matthewfaw, Weston
 *
 */
public class MoveableComponent extends AbstractComponent implements IMovable, IViewableMovable {
	@Hide
	private PhysicalSystem myPhysical;
	@Hide
	private TargetingSystem myTargeting;
	@Hide
	private CollisionDetectionSystem myCollision;
	
	private IMovementStrategy myMovementCalc;
	private double myTurnSpeed;
	private double myMoveSpeed;
	
	@Hide
	private IPosition myGoal;
	
	//NOTE: So that entities can die after traveling a certain distance.
	private double myMaxDistance;
	@Hide
	private double myMovedDistance;
	
	@Hide
	private List<IObserver<IViewable>> myObservers;

	public MoveableComponent(
			MovementSystem movement,
			PhysicalSystem physical,
			TargetingSystem targeting,
			CollisionDetectionSystem collision,
			ComponentData data,
			Router router
			) throws ClassNotFoundException {
		super(router);
		
		myPhysical = physical;
		myTargeting = targeting;
		myCollision = collision;
		
		myMovedDistance = 0;
		myMaxDistance = Double.parseDouble(data.getFields().get("myMaxDistance"));
		
		myTurnSpeed = Double.parseDouble(data.getFields().get("myTurnSpeed"));
		myMoveSpeed = Double.parseDouble(data.getFields().get("myMoveSpeed"));
		myMovementCalc = movement.getStrategyFactory().newStrategy(data.getFields().get("myMovementCalc"));
		
		myObservers = new ArrayList<IObserver<IViewable>>();
		
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
		p.setPosition(getMove(p));
		myCollision.checkCollision(p);
	}
	
	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

	@Override
	public void distributeInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMovementStrategy getMovementStrategy() {
		return myMovementCalc;
	}

}
