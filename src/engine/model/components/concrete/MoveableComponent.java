package engine.model.components.concrete;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewableMovable;
import engine.model.entities.IEntity;
import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.IPhysical;
import engine.model.strategies.IPosition;
import engine.model.systems.BountySystem;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.ISystem;
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
	private transient MovementSystem myMovement;
	@Hide
	private transient PhysicalSystem myPhysical;
	@Hide
	private transient TargetingSystem myTargeting;
	@Hide
	private transient CollisionDetectionSystem myCollision;
	@Hide
	private transient DamageDealingSystem myDamage;
	@Hide
	private transient BountySystem myBounty;
	
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

	
	@Hide
	private List<IObserver<IViewableMovable>> myObservers;

	public MoveableComponent(IEntity aEntity, 
			MovementSystem movement,
			PhysicalSystem physical,
			TargetingSystem targeting,
			CollisionDetectionSystem collision,
			BountySystem bounty,
			Router router,
			DamageDealingSystem damage,
			ComponentData data
			) throws ClassNotFoundException {
		super(aEntity, router);
		
		myMovement = movement;
		myPhysical = physical;
		myTargeting = targeting;
		myCollision = collision;
		myBounty = bounty;
		myDamage = damage;
		
		myMovedDistance = 0;
		updateComponentData(data);
		
		myObservers = new ArrayList<IObserver<IViewableMovable>>();
		
		myMovement.attachComponent(this);
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
		if (p != null && myGoal == null && myTargeting.getTarget(this) == null)
			p.setPosition(getMove(p));
		else if (p != null && (myGoal != null || myTargeting.getTarget(this) == null))
			p.setPosition(getMove(p));
		
		myCollision.checkCollision(p);
		
		if ((myMovedDistance >= myMaxDistance && explodesAtMaxDistance) || (explodesOnGoal && atGoal())) {
			myDamage.explode(this);
		}
		
		if (removeOnGoal && atGoal()) {
			myBounty.pillagePlayerBase(this);
			getEntity().delete();
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
	@Override
	public void setSystems(List<ISystem<?>> aSystemList) {
		for (ISystem<?> system: aSystemList) {
			if (system instanceof MovementSystem) {
				myMovement = (MovementSystem) system;
				myMovement.attachComponent(this);
			} else if (system instanceof DamageDealingSystem) {
				myDamage = (DamageDealingSystem) system;
 			} else if (system instanceof BountySystem){
 				myBounty = (BountySystem) system;
 			} else if (system instanceof PhysicalSystem){
 				myPhysical = (PhysicalSystem) system;
 			} else if (system instanceof TargetingSystem){
 				myTargeting = (TargetingSystem) system;
 			} else if (system instanceof CollisionDetectionSystem){
 				myCollision = (CollisionDetectionSystem) system;
 			}
		}
	}
	@Override
	public void redistributeThroughRouter(Router aRouter) {
		super.setRouter(aRouter);
		myObservers = new ArrayList<IObserver<IViewableMovable>>();
		aRouter.distributeViewableComponent(this);
	}
	@Override
	public void updateComponentData(ComponentData aComponentData) {
		myMaxDistance = Double.parseDouble(aComponentData.getFields().get("myMaxDistance"));
		explodesAtMaxDistance = Boolean.parseBoolean(aComponentData.getFields().get("explodesAtMaxDistance"));
		
		explodesOnGoal = Boolean.parseBoolean(aComponentData.getFields().get("explodesOnGoal"));
		removeOnGoal = Boolean.parseBoolean(aComponentData.getFields().get("removeOnGoal"));
		
		myTurnSpeed = Double.parseDouble(aComponentData.getFields().get("myTurnSpeed"));
		myMoveSpeed = Double.parseDouble(aComponentData.getFields().get("myMoveSpeed"));
		myMovementCalc = myMovement.getStrategyFactory().newStrategy(aComponentData.getFields().get("myMovementCalc"));
	}
}
