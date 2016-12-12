package engine.model.components.concrete;

import authoring.model.ComponentData;
import engine.model.components.AbstractComponent;
import engine.model.systems.CollisionDetectionSystem;
import engine.model.systems.ControllableSystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
import utility.Point;
/**
 * 
 * @author owenchung
 *
 */
public class ControllableComponent extends AbstractComponent{
	private ControllableSystem myControllableSystem;
	private PhysicalSystem myPhysicalSystem;
	private CollisionDetectionSystem myCollisionDetectionSystem;
	private DamageDealingSystem myDamageDealingSystem;
	private MovementSystem myMovementSystem;
	
	public ControllableComponent(ControllableSystem controllableSystem, 
			CollisionDetectionSystem collisionDetectionSystem, 
			PhysicalSystem physicalSystem,
			HealthSystem healthSystem,
			MovementSystem movementSystem,
			DamageDealingSystem damageDealingSystem, 
			ComponentData componentData) {
		myPhysicalSystem = physicalSystem;
		myCollisionDetectionSystem = collisionDetectionSystem;
		myDamageDealingSystem = damageDealingSystem;
		myMovementSystem = movementSystem;
		
	}
	

	public void move(String movement) {
		PhysicalComponent p = myPhysicalSystem.get(this);
		MoveableComponent m = myMovementSystem.get(this);
		Point nextposition = getNextPosition(p, m, movement);
		p.setPosition(nextposition);
		myCollisionDetectionSystem.checkCollision(p);
	}
	
	public void fire() {
		
	}


	private Point getNextPosition(PhysicalComponent p, MoveableComponent m, String movement) {
		Point nextposition;
		switch (movement) {
			case "Right":  nextposition =  new Point(p.getPosition().getX() + m.getMoveSpeed(), p.getPosition().getY());
						   break;
			case "Left" :  nextposition =  new Point(p.getPosition().getX() - m.getMoveSpeed(), p.getPosition().getY());
						   break;
			case "Up"   :  nextposition =  new Point(p.getPosition().getX() , p.getPosition().getY() + m.getMoveSpeed());
						   break;
			case "Down" :  nextposition =  new Point(p.getPosition().getX() , p.getPosition().getY() - m.getMoveSpeed());
						   break;
			default     :  nextposition =  new Point(p.getPosition().getX() , p.getPosition().getY());
		}
		return nextposition;
		
	}


	@Override
	public void delete() {
		myControllableSystem.detachComponent(this);
	}
	
}
