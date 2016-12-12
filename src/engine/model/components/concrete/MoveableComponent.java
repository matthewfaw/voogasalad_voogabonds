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
import engine.model.systems.DamageDealingSystem;
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
	@Hide
	private DamageDealingSystem myDamage;
	
	private IMovementStrategy myMovementCalc;
	private double myTurnSpeed;
	private double myMoveSpeed;
	
	@Hide
	private IPosition myGoal;
	
	//NOTE: So that entities can die after traveling a certain distance.
	private boolean explodesAtMaxDistance;
	private double myMaxDistance;
	@Hide
	private double myMovedDistance;

	
	@Hide
	private List<IObserver<IViewableMovable>> myObservers;

	public MoveableComponent(
			MovementSystem movement,
			PhysicalSystem physical,
			TargetingSystem targeting,
			CollisionDetectionSystem collision,
			Router router,
			DamageDealingSystem damage,
			ComponentData data
			) throws ClassNotFoundException {
		super(router);
		
		myPhysical = physical;
		myTargeting = targeting;
		myCollision = collision;
		myDamage = damage;
		
		myMovedDistance = 0;
		myMaxDistance = Double.parseDouble(data.getFields().get("myMaxDistance"));
		explodesAtMaxDistance = Boolean.parseBoolean(data.getFields().get("explodesAtMaxDistance"));
		
		myTurnSpeed = Double.parseDouble(data.getFields().get("myTurnSpeed"));
		myMoveSpeed = Double.parseDouble(data.getFields().get("myMoveSpeed"));
		myMovementCalc = movement.getStrategyFactory().newStrategy(data.getFields().get("myMovementCalc"));
		
		myObservers = new ArrayList<IObserver<IViewableMovable>>();
		
		movement.attachComponent(this);
	}
	
	private Pair<Double, Point> getMove(IPhysical p) {
		if (myMovedDistance - myMaxDistance < myMoveSpeed)
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
		p.setPosition(getMove(p));
		myCollision.checkCollision(p);
		
		if (myMovedDistance >= myMaxDistance && explodesAtMaxDistance) {
			myDamage.explode(this);
		}
	}
	
	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewableMovable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewableMovable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

	@Override
	public void distributeInfo() {
		getRouter().distributeViewableComponent(this);
	}

	@Override
	public IMovementStrategy getMovementStrategy() {
		return myMovementCalc;
	}

	@Override
	public Integer getEntityID() {
		return getEntity().getId();
	}
	
}
