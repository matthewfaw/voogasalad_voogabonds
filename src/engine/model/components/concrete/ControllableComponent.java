package engine.model.components.concrete;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.components.AbstractComponent;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.ControllableSystem;
//import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import gamePlayerView.gamePlayerView.Router;
import javafx.util.Pair;
import utility.Point;
/**
 * 
 * @author owenchung
 *
 */
public class ControllableComponent extends AbstractComponent{
	@Hide
	private ControllableSystem myControllableSystem;
	@Hide
	private PhysicalSystem myPhysicalSystem;
	@Hide
	private CollisionDetectionSystem myCollisionDetectionSystem;
//	private DamageDealingSystem myDamageDealingSystem;
	@Hide
	private MovementSystem myMovementSystem;
	@Hide
	private Router router;
	
	public ControllableComponent(ControllableSystem controllableSystem, 
			CollisionDetectionSystem collisionDetectionSystem, 
			PhysicalSystem physicalSystem,
			HealthSystem healthSystem,
			MovementSystem movementSystem,
			/*DamageDealingSystem damageDealingSystem,*/ 
			ComponentData componentData,
			Router router) {
		super(router);
		myControllableSystem = controllableSystem;
		myPhysicalSystem = physicalSystem;
		myCollisionDetectionSystem = collisionDetectionSystem;
//		myDamageDealingSystem = damageDealingSystem;
		myMovementSystem = movementSystem;
		myControllableSystem.attachComponent(this);
	}
	

	public void move(String movement) {
		PhysicalComponent p = myPhysicalSystem.get(this);
		MoveableComponent m = myMovementSystem.get(this);
		Pair<Double,Point> nextposition = getNextPosition(p, m, movement);
		p.setPosition(nextposition);
		myCollisionDetectionSystem.checkCollision(p);
	}
	
	public void fire() {
		
	}
	// TODO : NEED TO CHECK TERRAIN TYPE

	private Pair<Double,Point> getNextPosition(PhysicalComponent p, MoveableComponent m, String movement) {
		Point nextposition;
		Double nextheading;
		if (movement == "Right") {
			nextposition =  new Point(p.getPosition().getX() + m.getMoveSpeed(), p.getPosition().getY());
			nextheading = 0.0;
		}
		else if (movement == "Left") {
			nextposition =  new Point(p.getPosition().getX() - m.getMoveSpeed(), p.getPosition().getY());
			nextheading = 180.0;
			
		}
		else if (movement == "Up") {
			nextposition =  new Point(p.getPosition().getX() , p.getPosition().getY() + m.getMoveSpeed());
			nextheading = 270.0;
		}
		else if (movement == "Down") {
			nextposition =  new Point(p.getPosition().getX() , p.getPosition().getY() - m.getMoveSpeed());
			nextheading = 90.0;
		}
		else {
			nextposition = p.getPosition();
			nextheading = p.getHeading();
		}
		return new Pair<Double, Point>(nextheading, nextposition);
		
	}


	@Override
	public void delete() {
		myControllableSystem.detachComponent(this);
	}


	@Override
	public void distributeInfo() {
		// TODO Auto-generated method stub
		
	}
	
}
