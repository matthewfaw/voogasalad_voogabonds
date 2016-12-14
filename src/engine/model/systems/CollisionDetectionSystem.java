package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.CollidableComponent;
import engine.model.components.concrete.PhysicalComponent;

/**
 * A system to manage collision detection in the game
 * Entities with the proper components can register with the
 * Collision detection system so that collisions may be reported
 * 
 * This class is an observer to Physical components
 * 
 * This class is observable by any system
 *
 */
public class CollisionDetectionSystem extends AbstractSystem<CollidableComponent> {
	
	public CollisionDetectionSystem() {
		
	}
	/**
	 * Checks if a physical component collides with anything in
	 * it's collision radius.
	 * @param the physical component that changed position
	 */
	public void checkCollision(PhysicalComponent movedPhysical) {
		CollidableComponent movedCollidable = get(movedPhysical);
		if (movedCollidable != null) {
			for (CollidableComponent unmovedCollidable: getComponents())	
				movedCollidable.checkCollision(unmovedCollidable);
		}
	}
	
	public CollidableComponent get(IComponent c) {
		return getComponent(c);
	}

}
